<template>
  <div class="rounded-lg use-border item-dictionary-detail-container">
    <div class="use-border item-dictionary-detail-head">
      <div class="use-border item-dictionary-detail-picture">
        <img :src="itemInfoList.image" style="width: 100%; height: 100%; object-fit: cover;">
      </div>
      <div class="use-border item-dictionary-detail-item-name">
        <br><br>
        <div class="print-item-name" v-for="(grade,i) in itemGradeInfo" :key="i">
          <p v-if="itemInfoList.gradeName === grade.name" :style="{'color' : grade.color}">
            {{ itemInfoList.itemName }}
          </p>
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
      <p class="print-item-attribute">제작재료 : {{ itemInfoList.attribute.materialName }}</p>
      <p class="print-item-attribute">무게 : {{ itemInfoList.attribute.weight }}</p>
      <p class="print-item-attribute">최대 안전강화 : {{ itemInfoList.attribute.safeEnchantLevel }}</p>
      <p class="print-item-attribute">컬렉션 수 : {{ itemInfoList.attribute.collectionCount }}</p>
      <p class="print-item-attribute">사망시 드랍 : {{ itemInfoList.attribute.droppable }}</p>
      <p class="print-item-attribute">강화 가능 : {{ itemInfoList.attribute.enchantable }}</p>
      <p class="print-item-attribute">보관 가능 : {{ itemInfoList.attribute.storable }}</p>
      <p class="print-item-attribute">거래 가능 : {{ itemInfoList.attribute.tradeable }}</p>
      <p class="print-item-attribute">{{ itemInfoList.attribute.description }}</p>
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

export default {
  name: "ItemDictionary",
  data() {
    return {
      itemGradeInfo: itemGradeInfo,
      enchantLevel: 0,
      itemDictionaryCond: {
        itemId: this.$route.params.itemId, enchantLevel: 0,
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
        this.itemInfoList = result.data.itemInfoList[0];
      }).finally(() => {
      });
    },
  },
  watch: {
    enchantLevel() {
      this.itemDictionaryCond.enchantLevel = this.enchantLevel;
      this.itemDictionarySearch(this.itemDictionaryCond);
    },
  },
  created() {
    const itemId = this.$route.params.itemId;
    this.enchantLevel = 0;
    let params = {
      enchantLevel: 0, itemId: itemId,
    };
    axios.get('http://localhost:8080/itemDictionary/search', {
      params: params,
    }).then((result) => {
      var description = result.data.itemInfoList[0].attribute.description;
      const removeReg = new RegExp("<.*>");
      result.data.itemInfoList[0].attribute.description = description.replace(removeReg,"");
      this.itemInfoList = result.data.itemInfoList[0];
    }).finally(() => {
      console.log(this.itemInfoList);
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
