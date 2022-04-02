import Vue from 'vue';
import Router from 'vue-router';
import Hello from '@/components/Hello';
import Cart from '@/components/cart/Cart.vue';
import ViewShifts from '@/components/viewShifts/ViewShifts.vue';

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
      path: "/viewshifts",
      name: "ViewShifts",
      component : ViewShifts
    }
  ]
})
