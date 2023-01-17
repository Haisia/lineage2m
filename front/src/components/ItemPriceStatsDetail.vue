<template>
  <div class="item-price-stats-container" v-for="(item, i) in itemPriceStats" :key="i">
    <div v-if="item.lastPrice!==0">
    <p style="font-size: 50px">{{item.createDate}}  :  <img src="/assets/diamond.PNG" style="width: 50px; height: 50px">{{item.lastPrice}}</p>
    </div>
    <div v-else>
      <p style="font-size: 50px">{{item.createDate}}  :  해당일자 거래내역 없음.</p>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ItemPriceStatsDetail",
  data() {
    return {
      itemPriceStats : [
        {
          itemId : Number,
          createDate : Date,
          lastPrice : Number,
          enchantLevel: Number,
          worldId: Number,
          worldName: String,
        },
      ],
    }
  },
  created() {
    let itemId= this.$route.params.itemId;
    let params = {
      enchantLevel: 0, worldId:1001,
    };
    axios.get('http://localhost:8080/itemPriceStats/'+itemId, {
      params: params,
    }).then((result) => {
      this.itemPriceStats = result.data;
      console.log(this.itemPriceStats);
    }).finally(() => {
    });
  },
}
</script>

<style>
.item-price-stats-container{
  width: 100%;
  text-align: center;
}

</style>
