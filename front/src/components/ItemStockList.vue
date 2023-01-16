<template>
  <div class="left-search-bar">
    <div class="use-border item-stock-search-container">
      <div class="use-border item-stock-search-cond-item-name">
        <span style="font-size: 40px">이름 : </span>
        <input v-model="itemStockSearchCond.search_keyword" style="height: 50%; font-size: 30px">
        <img src="src/assets/magnifier.PNG" @click="itemStockSearchAsFirstPage(itemStockSearchCond)"
             style="width: 30px; height: 30px; margin-left: 1.3%">

        <br><span v-if="recommendKeywords.length!==0" style="font-size: 30px">추천 검색어 :
      <select v-model="itemStockSearchCond.search_keyword" name="recommendKeyword" id="recommendKeyword"
              style="height: 30%; font-size: 20px">
        <option v-for="(keyword,i) in recommendKeywords" :key="i"
                :value="keyword.recommendKeyword">{{ keyword.recommendKeyword }}</option>
      </select>
      </span>
      </div>
      <div class="use-border item-stock-search-cond-item-enchant-level">
        <span style="font-size: 40px">강화 :</span>
        <input v-model="itemStockSearchCond.from_enchant_level"
               style="height: 50%; width: 35px; font-size: 30px; margin-left: 1.3%; margin-right: 1.3%">
        <span style="font-size: 40px">~</span>
        <input v-model="itemStockSearchCond.to_enchant_level"
               style="height: 50%; width: 35px; font-size: 30px; margin-left: 1.3%; margin-right: 1.3%">
      </div>

      <div class="use-border item-stock-search-cond-item-server">
        <span style="font-size: 40px">월드 :</span>
        <select v-model="itemStockSearchCond.server_id" name="condServerId" id="condServerId"
                style="height: 50%; font-size: 30px; margin-left: 1.3%">
          <option v-for="(world,i) in worldList" :key="i" :value="world.id">{{ world.name }}</option>
        </select>
      </div>
      <div class="use-border item-stock-search-preset">
        <span style="font-size: 40px">프리셋 : 구현예정</span>
        <!--      <select v-model="itemStockSearchCond.server_id" name="condServerId" id="condServerId" style="height: 50%; font-size: 30px; margin-left: 1.3%">-->
        <!--        <option v-for="(world,i) in worldList" :key="i" :value="world.id">{{ world.name }}</option>-->
        <!--      </select>-->
      </div>
    </div>
  </div>

  <div class="use-border item-stock-list-container">
    <table style="border-collapse: collapse; width: 100%">
      <thead>
      <tr>
        <th>번호</th>
        <th v-for="(column,i) in itemStockTableColumnList" :key="i">{{ column.name }}</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(result,i) in itemStockSearchResult.contents" :key="i" style="border-bottom: cornflowerblue solid;">
        <td style="text-align: center; width: 4%">
          {{ (itemStockSearchResult.pagination.page - 1) * itemStockSearchResult.pagination.size + i + 1 }}
        </td>
        <td style="width: 10%; text-align: center">
          <img :src="result.image" style="width: 40px; height: 40px;">
        </td>
        <td>
          <div v-for="(grade, i) in itemGradeInfo" :key="i" style="padding-left: 8%">
          <span v-if="result.enchantLevel!==0 && result.enchantLevel!=null && result.grade === grade.name"
                :style="{'color' : grade.color}">
            +{{ result.enchantLevel }}
          </span>
          <span v-if="result.grade === grade.name" :style="{'color' : grade.color}">
            <router-link :to="'/item-stock/'+result.itemId">{{ result.itemName }}</router-link>
          </span>
          </div>
        </td>
        <td style="width: 15%; text-align: center">
          <div v-for="(grade, i) in itemGradeInfo" :key="i">
            <span v-if="result.grade === grade.name" :style="{'color' : grade.color}">{{ result.grade }}</span>
          </div>
        </td>
        <td style="width: 20%; padding-left: 5%">
          <img src="src/assets/diamond.PNG" style="width: 25px; height: 25px">
          {{ result.nowMinUnitPrice }}
        </td>
        <td style="width: 100px; text-align: center">{{ result.serverName }}</td>
      </tr>
      </tbody>
    </table>
    <div class="text-center">
      <v-pagination
        v-model="itemStockSearchCond.page"
        :length="itemStockSearchResult.pagination.lastPage"
        :total-visible="10"
        @click="itemStockSearch(itemStockSearchCond);;"
      ></v-pagination>
    </div>

  </div>
</template>

<script>
import worldList from "@/assets/WorldList";
import axios from "axios";
import itemStockTableColumnList from "@/assets/ItemStockTableColumnList";
import itemGradeInfo from "@/assets/ItemGradeInfo";

export default {
  name: "ItemStockList",
  data() {
    return {
      recommendKeywords: [],
      itemGradeInfo: itemGradeInfo,
      itemStockTableColumnList: itemStockTableColumnList,
      worldList: worldList,
      itemStockSearchCond: {
        search_keyword: "",
        sale: true,
        from_enchant_level: 0,
        to_enchant_level: 0,
        server_id: 1001,
        page: 1,
        size: 30,
      },
      itemStockSearchResult: {
        contents: [
          {
            itemId: Number,
            itemName: String,
            serverId: Number,
            serverName: String,
            world: Boolean,
            enchantLevel: Number,
            grade: String,
            image: String,
            nowMinUnitPrice: Number,
            avgUnitPrice: Number,
          },
        ],
        pagination: {
          page: Number,
          size: Number,
          lastPage: Number,
          total: Number,
        },
      }

    }
  },
  methods: {
    itemStockSearchAsFirstPage(params) {
      params.page = 1;
      this.itemStockSearch(params);
    },
    itemStockSearch(params) {
      axios.get('http://localhost:8080/market/items/search', {
        params: params,
      }).then((result) => {
        const contents = result.data.contents;
        for (let i = 0; i < contents.length; i++) {
          for (const gradeInfo of itemGradeInfo) {
            if (contents[i].grade === gradeInfo.grade) {
              result.data.contents[i].grade = gradeInfo.name;
              break;
            }
          }
        }
        this.itemStockSearchResult = result.data;
        window.scrollTo(0, 0);

        if (result.data.contents.length === 0) {
          this.getRecommendKeyword();
        } else {
          this.recommendKeywords = [];
        }
      }).finally(() => {
      });
    },

    getRecommendKeyword() {
      axios.get('http://localhost:8080/itemStock/recommendKeyword', {
        params: {
          search_keyword: this.itemStockSearchCond.search_keyword
        }
      }).then((result) => {
        this.recommendKeywords = result.data;
      }).finally(() => {
        console.log(this.recommendKeywords[0].recommendKeyword);
      });
    },
  },
  created() {
    let params = {
      server_id: worldList[0].id,
      sale: true,
      from_enchant_level: 0,
      to_enchant_level: 0,
      page: 1,
      size: 30,
    };
    axios.get('http://localhost:8080/market/items/search', {
      params: params,
    }).then((result) => {
      const contents = result.data.contents;
      for (let i = 0; i < contents.length; i++) {
        for (const gradeInfo of itemGradeInfo) {
          if (contents[i].grade === gradeInfo.grade) {
            result.data.contents[i].grade = gradeInfo.name;
            break;
          }
        }
      }
      this.itemStockSearchResult = result.data;
    }).finally(() => {
    });
  },
  watch: {
    'itemStockSearchCond.from_enchant_level': function () {
      if (this.itemStockSearchCond.from_enchant_level > this.itemStockSearchCond.to_enchant_level) {
        alert("최소치는 최대치를 넘을 수 없습니다.");
        this.itemStockSearchCond.from_enchant_level = this.itemStockSearchCond.to_enchant_level;
      }
    },
  },

}
</script>

<style>
.use-border {
  border: #2c3e50 solid;
}

.item-stock-list-container {
  float: left;
  margin: 3% 0 3% 4%;
  padding-left: 5%;
  padding-right: 5%;
  width: 60%;
}

.left-search-bar {
  float: left;
  margin-top: 3%;
  margin-left: 2%;
  width: 30%;
  height: 70%
}

.item-stock-search-container {
  float: left;
  position: fixed;
  margin-top: 3%;
  margin-left: 2%;
  width: 30%;
  height: 70%
}

.item-stock-search-cond-item-name {
  float: left;
  width: 100%;
  height: 20%;
  margin-top: 5%;
}

.item-stock-search-cond-item-enchant-level {
  float: left;
  width: 100%;
  height: 20%;
  margin-top: 5%;
}

.item-stock-search-cond-item-server {
  float: left;
  width: 100%;
  height: 20%;
  margin-top: 5%;
}

.item-stock-search-preset {
  float: left;
  width: 100%;
  height: 20%;
  margin-top: 5%;
}

input {
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
}

select {
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
}
</style>
