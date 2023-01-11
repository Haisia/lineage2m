<template>
  <TopMenu></TopMenu>
  <router-view></router-view>
</template>

<script>
import TopMenu from "@/components/TopMenu.vue";

export default {
  components: {
    TopMenu: TopMenu,
  },
}


</script>

<style>
.top-menu{
  background: rgb(44, 34, 34);
  color: aliceblue;
  padding-left:10px;
}
</style>
