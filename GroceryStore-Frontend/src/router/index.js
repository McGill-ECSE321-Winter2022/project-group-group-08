import Vue from 'vue';
import Router from 'vue-router';
import Hello from '@/components/Hello';
import Cart from '@/components/Cart.vue';
import Item from '@/components/Item.vue';
import Quantity from '@/components/Quantity.vue';
// import ViewShift from '@/components/ViewShift';

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: "/cart",
      name: "Cart",
      component : Cart
    },
    {
      path: "/item",
      name: "Item",
      component : Item
    },
    {
      path: "/quantity",
      name: "Quantity",
      component : Quantity
    }
    // {
    //   path: "/ViewShifts",
    //   name: "ViewShifts",
    //   component : ViewShift
    // }
  ]
})
