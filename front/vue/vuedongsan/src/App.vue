<template>
  <transition name="fade">
    <Modal
      @closeModal="modalOpen = false"
      :oneroom="onerooms[userClickedIndex]"
      :modalOpen="modalOpen"
    />
  </transition>

  <div class="menu">
    <a v-for="(menu, i) in menus" :key="i">{{ menus[i] }}</a>
  </div>

  <Discount v-if="showDiscount == true" :discountRate="discountRate" />

  <button @click="sortByPrice">가격순정렬</button>
  <button @click="sortByPriceRev">가격역순정렬</button>
  <button @click="sortByTitle">가나다순정렬</button>
  <button @click="sortBack">되돌리기</button>

  <Card
    @click="logging"
    @openModal="
      modalOpen = true;
      userClickedIndex = $event;
    "
    :oneroom="oneroom"
    :i="i"
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
      discountRate: 20,
      showDiscount: true,
      oneroomsOrigin: [...data],
      userClickedIndex: 0,
      onerooms: data,
      modalOpen: false,
      menus: ["Home", "Shop", "About"],
    };
  },
  methods: {
    sortBack() {
      this.onerooms = [...this.oneroomsOrigin];
      console.log("call - sortBack()");
    },
    sortByPrice() {
      this.onerooms.sort(function (a, b) {
        return a.price - b.price;
      });
      console.log("call - sortByPrice()");
    },
    sortByPriceRev() {
      this.onerooms.sort(function (a, b) {
        return b.price - a.price;
      });
      console.log("call - sortByPriceRev()");
    },
    sortByTitle() {
      this.onerooms.sort(function (a, b) {
        let c, d;
        if (a.title > b.title) {
          c = 1;
          d = 0;
        } else {
          c = 0;
          d = 1;
        }
        return c - d;
      });
      console.log("call - sortByTitle()");
    },
    increase() {},
    logging() {
      console.log(this.modalOpen);
    },
  },

  mounted() {
    const discountTime = setInterval(() => {
      if (this.discountRate > 1) {
        this.discountRate--;
      } else {
        this.showDiscount = false;
        console.log("this.showDiscount = false");

        clearInterval(discountTime);
      }
      console.log("call - setInterval()");
    }, 1000);

    console.log("call - mounted()");
  },

  components: {
    Discount: Discount,
    Card: Card,
    Modal: Modal,
  },
};
</script>

<style>
.fade-leave-from {
  opacity: 1;
}
.fade-leave-active {
  transition: all 1s;
}
.fade-leave-to {
  opacity: 0;
}

.fade-enter-from {
  transform: translateY(-1000px);
}
.fade-enter-active {
  transition: all 1s;
}
.fade-enter-to {
  transform: translateY(0px);
}

.start {
  opacity: 0;
  transition: all 1s;
}
.end {
  opacity: 1;
}

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
  /* width: 100%;
  margin-top: 40px; */
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
