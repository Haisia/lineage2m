<template style="margin: 0">
  <div class="background">
    <div class="searchBar">
      <div class="setStartPage">시작페이지로</div>
      <div class="logoAndSearch">
        <a class="siteLogo" href="http://192.168.0.42:8080/"
          ><img :src="siteLogo"
        /></a>
        <div class="searchInputBox">
          <input class="searchInput" />
        </div>

        <div class="searchIcon">
          <a href="#"><img :src="searchIcon" /></a>
        </div>
      </div>
    </div>

    <div class="menu">
      <span
        class="menuList"
        v-for="(menu, i) in menus"
        :key="i"
        @click="function () {}"
        >{{ menu }}
      </span>
    </div>

    <div class="mailList">
      <!-- <span @click="isModal = true">{{ mailData[0].title }} <br /></span> -->
      <span
        class="mailTitle"
        @click="
          isModal = true;
          userClickedIndex = i;
        "
        v-for="(mail, i) in mailData"
        :key="i"
        >- {{ mailData[i].title }} <br
      /></span>
    </div>

    <transition name="openModal">
      <div class="mailDetail" v-if="isModal == true">
        <span>{{ mailData[userClickedIndex].id }}<br /></span>
        <span>{{ mailData[userClickedIndex].title }}<br /></span>
        <span>{{ mailData[userClickedIndex].content }}<br /></span>
        <span>{{ mailData[userClickedIndex].from }}<br /></span>
        <button @click="isModal = false">닫기</button>
      </div>
    </transition>
  </div>
</template>

<script>
import siteLogo from "./assets/siteLogo.png";
import searchIcon from "./assets/searchIcon.png";
import mailData from "./assets/mailData.js";

export default {
  name: "App",
  components: {},
  methods: {
    logging() {
      console.log("menu = " + this.menu);
    },
  },
  data() {
    return {
      menus: ["Mail", "Cafe", "Shop"],
      siteLogo: siteLogo,
      searchIcon: searchIcon,
      mailData: mailData,
      isModal: false,
      userClickedIndex: 0,
    };
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
  margin-top: 60px;
}
.background {
  overflow: auto;
  height: 2000px;
}
.setStartPage {
  text-align: right;
}
.searchBar {
  height: 180px;
}
.menuList {
  font-size: 32px;
  text-align: center;
}
.siteLogo {
  float: left;
  margin: 20px 0;
}
.searchInputBox {
  float: left;
  border: rgba(67, 236, 34, 0.77) solid 3px;
  margin: 40px 0;
}
.searchInput {
  height: 50px;
  width: 600px;
  font-size: 25px;
  border: white solid 1px;
  outline: none;
  margin: 0 10px;
}
.searchIcon {
  float: left;
  margin: 35px 0;
}
.menu {
  border-top: solid 2px silver;
  border-bottom: solid 2px silver;
  text-align: center;
}
.menuList {
  color: rgba(67, 236, 34, 1);
}

.mailTitle {
  border-bottom: dashed 2px silver;
  margin-left: 650px;
  left: 0;
  right: 0;
}
.mailDetail {
  border: black solid 2px;
  border-radius: 10px;
  /* position: fixed; */
  position: fixed;
  top: 10vw;
  left: 30vw;
  right: 30vw;
  bottom: 10vw;
  padding-top: 30px;
  padding-left: 40px;
}
.openModal-enter-from {
  opacity: 0;
}
.openModal-enter-active {
  transition: all 1s;
}
.openModal-enter-to {
  opacity: 1;
}
</style>
