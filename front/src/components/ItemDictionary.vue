<template>

  <div class="search-bar">
    <div class="item-dictionary-search-cond-name" style="margin-left: 18%">
      <div style="float:left; width:7%; font-size: 40px">이름:</div>
      <div style="float:left; width:15%">
        <v-text-field v-model="itemDictionaryCond.itemName"></v-text-field>
      </div>
    </div>

    <div style="width: auto; float: left">
      <div class="item-dictionary-search-cond-enchantLevel">
        <label for="condEnchantLevel" style="font-family: sans-serif; font-size: 1rem; padding-right: 10px;">강화레벨
          :</label>
        <select v-model="itemDictionaryCond.enchantLevel" name="condEnchantLevel" id="pet-condEnchantLevel" style="font-size: 0.9rem; padding: 2px 5px;">
          <option v-for="i in 14" :key="i" :value="i-1">{{ i - 1 }}</option>
        </select>
      </div>

      <div class="item-dictionary-search-cond-grade">
        <label for="condGrade-select" style="font-family: sans-serif; font-size: 1rem; padding-right: 10px;">등급
          :</label>
        <select v-model="itemDictionaryCond.grade" name="condGrade" id="condGrade" style="font-size: 0.9rem; padding: 2px 5px;">
          <option :value="null">전체</option>
          <option v-for="(itemGrade,i) in itemGradeInfo" :key="i" :value="itemGrade.grade">{{ itemGrade.name }}</option>
        </select>
      </div>

      <div class="item-dictionary-search-cond-tradeCategoryName">
        <label for="condGrade-select" style="font-family: sans-serif; font-size: 1rem; padding-right: 10px;">분류
          :</label>
        <select v-model="itemDictionaryCond.tradeCategoryName" name="tradeCategoryName" id="tradeCategoryName" style="font-size: 0.9rem; padding: 2px 5px;">
          <option :value="null">전체</option>
          <option v-for="(category,i) in itemTradeCategoryInfo" :key="i" :value="category.name">{{ category.name }}
          </option>
        </select>
      </div>
    </div>

    <div style="width: auto">
      <img src="/src/assets/magnifier.PNG" @click="itemDictionarySearch(itemDictionaryCond)" style="width: 80px; position: fixed"/>
    </div>

  </div>


  <div style="float:left; width: 90%; margin-left: 5%">
    <v-table density="compact" class="item-dictionary-table">
      <thead>
      <tr>
        <th><p>번호</p></th>
        <th class="text-left" v-for="(field, i) in itemDictionarySearchResultField" :key="i">
          <p>{{ field.name }}</p>
        </th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(result,i) in itemDictionaryResult.itemInfoList" :key="i">
        <td>{{ itemDictionaryResult.pagination.offset + i + 1 }}</td>
        <td>{{ result.itemName }}</td>
        <td>{{ result.enchantLevel }}</td>
        <td>{{ result.gradeName }}</td>
        <td>{{ result.tradeCategoryName }}</td>
      </tr>
      </tbody>
    </v-table>

    <div class="text-center">
      <v-pagination
        v-model="nowPage"
        :length="lastPageNum"
        :total-visible="10"
      ></v-pagination>
    </div>
  </div>


</template>

<script>
import axios from "axios";
import itemDictionarySearchResultField from "@/assets/ItemDictionarySearchResultField";
import itemGradeInfo from "@/assets/ItemGradeInfo";
import itemTradeCategoryInfo from "@/assets/ItemTradeCategoryInfo";

export default {
  name: "ItemDictionary",
  data() {
    return {
      nowPage: 1,
      lastPageNum: 20,
      itemDictionarySearchResultField: itemDictionarySearchResultField,
      itemDictionaryCond: {
        itemName: null, grade: null, tradeCategoryName: null, enchantLevel: 0, optionName: null, page: 1, size: 15,
      },
      itemDictionaryResult: {
        itemInfoList: [{
          itemId: Number,
          itemName: String,
          enchantLevel: Number,
          grade: String,
          gradeName: String,
          image: String,
          tradeCategoryName: String
        }],
        pagination: {total: Number, offset: Number, limit: Number,},
      },
      itemGradeInfo: itemGradeInfo,
      itemTradeCategoryInfo: itemTradeCategoryInfo,
    }
  },

  watch: {
    nowPage(newPage, oldPage) {
      this.itemDictionaryCond.page = this.nowPage;
      this.itemDictionarySearch(this.itemDictionaryCond);
    }
  },

  created() {
    let params = {
      enchantLevel: 0, page: 1, size: 15,
    };
    axios.get('http://localhost:8080/itemDictionary/search', {
      params: params,
    }).then((result) => {
      this.itemDictionaryResult = result.data;
      this.setLastPageNum(result.data.pagination.total, result.data.pagination.limit);
    }).finally(() => {
    });
  },

  methods: {
    setLastPageNum(total, limit) {
      this.lastPageNum = Math.ceil(total / limit);
    },

    itemDictionarySearch(params) {
      axios.get('http://localhost:8080/itemDictionary/search', {
        params: params,
      }).then((result) => {
        this.itemDictionaryResult = result.data;
        this.setLastPageNum(result.data.pagination.total, result.data.pagination.limit);
      }).finally(() => {
      });
    },
  },
}
</script>

<style>
.item-dictionary-table {
  margin-left: 15%;
  margin-right: 15%;
}
</style>
