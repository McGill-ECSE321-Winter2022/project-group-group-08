import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import itemInventory from '@/components/InventoryManagement.vue'
import storeManagement from '@/components/StoreManagement.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/itemInventory',
      name: 'itemInventory',
      component: itemInventory
    },
    {
      path: '/storeManagement',
      name: 'storeManagement',
      component: storeManagement
    }
  ]
})
