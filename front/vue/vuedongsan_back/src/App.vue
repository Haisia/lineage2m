<template>
  <div class="menu">
    <a v-for="(menu, i) in menus" :key="i">{{ menus[i] }}</a>
  </div>

  <Discount />
  <Modal
    @closeModal="modalOpen = false"
    :modalOpen="modalOpen"
    :oneroom="oneroom"
    v-for="(oneroom, i) in onerooms"
    :key="i"
  />

  <Card
    @click="logging"
    @openModal="modalOpen = true"
    :oneroom="oneroom"
    v-for="(oneroom, i) in onerooms"
    :key="i"
  />
</template>

<script>
import data from "./assets/oneroom.js";
import Discount from "./Discount.vue";
import Card from "./Card.vue";
import Modal from "./Modal.vue";

export default {
  name: "App",

  data() {
    return {
      userClickedIndex: 0,
      onerooms: data,
      modalOpen: false,
      menus: ["Home", "Shop", "About"],
    };
  },
  methods: {
    increase() {},
    logging() {
      console.log(this.modalOpen);
    },
    logging2(a) {
      console.log(a);
    },
  },
  components: {
    Discount: Discount,
    Card: Card,
    Modal: Modal,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.menu {
  background: darkslateblue;
  padding: 15px;
  border-radius: 5px;
}
.menu a {
  color: white;
  padding: 10px;
}
.room-img {
  width: 100%;
  margin-top: 40px;
}
body {
  margin: 0;
}
div {
  box-sizing: border-box;
}
.black-bg {
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  position: fixed;
  padding: 20px;
}
.white-bg {
  width: 100%;
  background: white;
  border-radius: 8px;
  padding: 20px;
}
.modalImg {
  width: 500px;
  height: 300px;
}
.discount {
  background: #eee;
  padding: 10px;
  margin: 10px;
  border-radius: 5px;
}
</style>
