// Composables
import {createRouter, createWebHistory} from 'vue-router'

const routes = [
  {
    path: '/',
    component: () => import('@/layouts/default/Default.vue'),
    children: [
      {
        path: '',
        name: 'Home',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import('@/views/BaseHome.vue'),
        children: [
          {
            path: 'item-price-stats',
            name: 'ItemPriceStats',
            component: () => import('@/components/ItemPriceStats.vue'),
          },
          {
            path: 'item-price-stats/:itemId',
            name: 'ItemPriceStatsDetail',
            component: () => import('@/components/ItemPriceStatsDetail.vue'),
          },
          {
            path: 'item-stock',
            name: 'ItemStockList',
            component: () => import('@/components/ItemStockList.vue'),
          },
          {
            path: 'item-stock/:itemId',
            name: 'ItemStockDetail',
            component: () => import('@/components/ItemStock.vue'),
          },
          {
            path: 'login',
            name: 'UserLogin',
            component: () => import('@/components/UserLogin.vue'),
          },
          {
            path: 'item-dictionary',
            name: 'ItemDictionaryList',
            component: () => import('@/components/ItemDictionaryList.vue'),
          },
          {
            path: 'item-dictionary/:itemId',
            name: 'ItemDictionaryDetail',
            component: () => import('@/components/ItemDictionary.vue'),
          },
        ]
      },
    ],
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
