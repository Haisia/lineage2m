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
</style>
