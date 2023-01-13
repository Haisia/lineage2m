<template>
  <v-layout ref="app">
    <div class="top-menu-bar" style="width: 100%">
      <v-app-bar color="rgb(44, 34, 34)" name="app-bar" class="justify-center">
        <div class="d-flex justify-left align-center w-100" style="color: aliceblue;">
          <p class="project-name" style=" font-weight: bold; font-size: 25px">Project - L2M</p>
          <div style="margin-left: 5%"></div>
          <a v-for="(menu,i) in menuList" :key="i" :href="menu.route" class="top-menu">{{ menu.name }}</a>
        </div>
      </v-app-bar>
    </div>
    <v-main>
      <v-card height="0px"></v-card>
    </v-main>
  </v-layout>

</template>

<script>
import menuList from '@/assets/TopMenuList'
import {useLayout} from 'vuetify'

const Child = {
  setup(props, ctx) {
    const {getLayoutItem} = useLayout()

    function print(key) {
      alert(JSON.stringify(getLayoutItem(key), null, 2))
    }

    return () => ctx.slots.default({print})
  },
}

export default {
  name: "TopMenu",
  data() {
    return {
      menuList: menuList,
    }
  },
  // components:
}

</script>

<style>
.top-menu {
  margin-left: 5px;
  margin-right: 5px;
}

a {
  color: inherit;
  text-decoration: none;
}

.project-name {
  padding-left: 2%;

  /*font*/
}

.top-menu-bar {
  position: fixed;
}

</style>
