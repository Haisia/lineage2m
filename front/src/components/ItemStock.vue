<template>
  <div class="rounded-lg use-border item-dictionary-detail-container">
    <div class="use-border item-dictionary-detail-head">
      <div class="use-border item-dictionary-detail-picture">
        <img :src="itemInfoList.image" style="width: 100%; height: 100%; object-fit: cover;">
      </div>
      <div class="use-border item-dictionary-detail-item-name">
        <br><br>
        <div class="print-item-name" v-for="(grade,i) in itemGradeInfo" :key="i">
          <span v-if="itemInfoList.gradeName === grade.name" :style="{'color' : grade.color}">
            <span v-if="enchantLevel!==0">+{{ enchantLevel }}</span>
            {{ itemInfoList.itemName }}
          </span>
        </div>
      </div>
      <div class="use-border item-dictionary-detail-grade">
        <br>
        <div class="print-item-grade-name" v-for="(grade,i) in itemGradeInfo" :key="i">
          <p v-if="itemInfoList.gradeName === grade.name" :style="{'color' : grade.color}">
            {{ itemInfoList.gradeName }}
          </p>
        </div>
      </div>
      <div class="use-border item-dictionary-detail-category">
        <br>
        <p class="print-item-category">{{ itemInfoList.tradeCategoryName }}</p>
      </div>
    </div>

    <div class="use-border"
         :class="{
      'item-dictionary-detail-attribute-container':itemInfoList.itemOptions!==null,
      'item-dictionary-detail-attribute-skill-book':itemInfoList.itemOptions==null,
         }">
      <span class="print-item-attribute">월드 :</span>
      <select v-model="itemPriceCond.server_id" name="condServerId" id="condServerId"
              style="height: 50%; font-size: 30px; margin-left: 1.3%">
        <option v-for="(world,i) in worldList" :key="i" :value="world.id">{{ world.name }}</option>
      </select>

      <p class="print-item-attribute">최근거래가격 : <img src="/assets/diamond.PNG" style="width: 25px; height: 25px">{{ itemPriceInfo.last.unitPrice !==null? itemPriceInfo.last.unitPrice : 0 }}</p>
      <p class="print-item-attribute">현재최저가격 : <img src="/assets/diamond.PNG" style="width: 25px; height: 25px">{{ itemPriceInfo.now.unitPrice !==null? itemPriceInfo.now.unitPrice : 0 }}</p>
      <p class="print-item-attribute">평균가격 : <img src="/assets/diamond.PNG" style="width: 25px; height: 25px">{{ itemPriceInfo.avg.unitPrice !==null? itemPriceInfo.avg.unitPrice : 0 }}</p>
      <p class="print-item-attribute">최소가격 : <img src="/assets/diamond.PNG" style="width: 25px; height: 25px">{{ itemPriceInfo.min.unitPrice !==null? itemPriceInfo.min.unitPrice : 0 }}</p>
      <p class="print-item-attribute">최대가격 : <img src="/assets/diamond.PNG" style="width: 25px; height: 25px">{{ itemPriceInfo.max.unitPrice !==null? itemPriceInfo.max.unitPrice : 0 }}</p>
    </div>

    <div class="use-border item-dictionary-detail-option-container" v-if="itemInfoList.itemOptions!==null">
      <p class="print-item-option">
        강화레벨 :
        <select v-model="enchantLevel" name="condEnchantLevel" id="condEnchantLevel"
                style="font-size: 20px; padding: 2px 5px; border: #2c3e50 solid">
          <option v-for="i in 14" :key="i" :value="i-1">{{ i - 1 }}</option>
        </select>
      </p>
      <span class="print-item-option" v-for="(option,i) in itemInfoList.itemOptions" :key="i">
        {{ option.optionName }} : {{ option.display }}
        <span v-if="option.extraDisplay !== ''" style="color: red">
        ({{ option.extraDisplay }})
        </span>
        <p style="font-weight: bold">
          {{ option.description }}
        </p>
      </span>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import itemGradeInfo from "@/assets/ItemGradeInfo";
import worldList from "@/assets/WorldList";

export default {
  name: "ItemStock",
  data() {
    return {
      worldList: worldList,
      itemGradeInfo: itemGradeInfo,
      enchantLevel: 0,
      itemDictionaryCond: {
        itemId: this.$route.params.itemId, enchantLevel: 0,
      },
      itemPriceCond: {
        server_id: 1001, enchant_level: 0,
      },
      itemPriceInfo: {
        itemId: Number,
        serverId: Number,
        enchantLevel: Number,
        last: {
          world: Boolean,
          unitPrice: Number,
        },
        now: {
          serverId: Number,
          serverName: String,
          world: Boolean,
          unitPrice: Number,
        },
        min: {
          serverId: Number,
          serverName: String,
          world: Boolean,
          unitPrice: Number,
        },
        max: {
          serverId: Number,
          serverName: String,
          world: Boolean,
          unitPrice: Number,
        },
        avg: {
          unitPrice: Number,
          world: Boolean,
        },
      },
      itemInfoList: {
        itemId: Number,
        itemName: String,
        enchantLevel: Number,
        grade: String,
        gradeName: String,
        image: String,
        tradeCategoryName: String,
        attribute: {
          droppable: Boolean,
          tradeable: Boolean,
          storable: Boolean,
          enchantable: Boolean,
          collectionCount: Number,
          weight: Number,
          description: String,
          safeEnchantLevel: Number,
          materialName: String,
        },
        itemOptions: [
          {
            optionName: String,
            display: String,
            extraDisplay: String,
            description: String,
          },
        ],
      },
    }
  },
  methods: {
    itemDictionarySearch(params) {
      axios.get('http://localhost:8080/itemDictionary/search', {
        params: params,
      }).then((result) => {
        result.data.itemInfoList[0].attribute.weight/=10000;
        this.itemInfoList = result.data.itemInfoList[0];
      }).finally(() => {
      });
    },
    itemPriceSearch(params) {
      axios.get('http://localhost:8080/market/items/'+this.$route.params.itemId+'/price', {
        params: params,
      }).then((result) => {
        this.itemPriceInfo = result.data;
      }).finally(() => {
      });
    }

  },
  watch: {
    enchantLevel() {
      this.itemDictionaryCond.enchantLevel = this.enchantLevel;
      this.itemPriceCond.enchant_level = this.enchantLevel;
      this.itemDictionarySearch(this.itemDictionaryCond);

      this.itemPriceInfo = {};
      this.itemPriceSearch(this.itemPriceCond);
    },

    'itemPriceCond.server_id': function () {
      this.itemPriceSearch(this.itemPriceCond);
    },



  },
  created() {
    const itemId = this.$route.params.itemId;
    this.enchantLevel = 0;
    let params = {
      enchantLevel: 0, itemId: itemId,
    };
    let priceParam = {
      item_id: this.$route.params.itemId, enchant_level: 0, server_id : 1001,
    };

    axios.get('http://localhost:8080/itemDictionary/search', {
      params: params,
    }).then((result) => {
      var description = result.data.itemInfoList[0].attribute.description;
      const removeReg = new RegExp("<.*>");
      result.data.itemInfoList[0].attribute.weight/=10000;
      result.data.itemInfoList[0].attribute.description = description.replace(removeReg,"");
      this.itemInfoList = result.data.itemInfoList[0];
    }).finally(() => {
    });

    axios.get('http://localhost:8080/market/items/'+itemId+'/price', {
      params: priceParam,
    }).then((result) => {
      this.itemPriceInfo = result.data;
    }).finally(() => {
    });
  },
}
</script>

<style>
.item-dictionary-detail-container {
  margin-top: 1%;
  margin-left: 20%;
  margin-right: 20%;
  margin-bottom: 1%;
  min-height: 85%;
  overflow: auto;
}

.use-border {
  border: #2c3e50 solid;
}

.item-dictionary-detail-head {
  margin-top: 2%;
  margin-left: 3%;
  margin-right: 3%;
  height: 266px;
}

.item-dictionary-detail-picture {
  float: left;
  width: 25%;
  height: 100%;
}

.item-dictionary-detail-item-name {
  display: block;
  float: left;
  margin-left: 5%;
  width: 40%;
  height: 100%;
}

.item-dictionary-detail-grade {
  float: left;
  width: 30%;
  height: 50%;
}

.item-dictionary-detail-category {
  float: left;
  width: 30%;
  height: 50%;
}

.item-dictionary-detail-attribute-container {
  float: left;
  margin-top: 2%;
  margin-left: 3%;
  margin-right: 3%;
  width: 30%;
  min-height: 55%;
}
.item-dictionary-detail-attribute-skill-book{
  margin: 2% 3% 2% 3%;
}

.item-dictionary-detail-option-container {
  float: left;
  margin-top: 2%;
  margin-right: 3%;
  margin-bottom: 2%;
  width: 61%;
  min-height: 55%;
}

.print-item-name {
  text-align: center;
  font-size: 50px;
}

.print-item-grade-name {
  text-align: center;
  font-size: 50px;
}

.print-item-category {
  text-align: center;
  font-size: 40px;
}

.print-item-attribute {
  font-size: 25px;
}

.print-item-option {
  font-size: 20px;
}
.item-dictionary-detail-skill-book{
  float: left;
  width: 94%;
  height: 100%;
  margin: 2% 3% 2% 3%;
}


</style>





