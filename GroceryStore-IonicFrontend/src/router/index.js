import Vue from 'vue'
import Router from 'vue-router'
import MainPage from "@/components/MainPage.vue";
import Item from "@/components/Item.vue";
import CartItem from "@/components/CartItem.vue";
import Browse from "@/components/Browse.vue";
import Login from "@/components/Login.vue";
import SignUp from "@/components/SignUp.vue";
import Profile from "@/components/Profile.vue";
import Cart from '@/components/Cart.vue';
import Receipts from "@/components/Receipts.vue";

Vue.use(Router);

export default new Router({
    routes: [{
            path: "/",
            name: "MainPage",
            component: MainPage
        },
        {
            path: "/browse",
            name: "Browse",
            component: Browse
        },
        {
            path: "/item",
            name: "Item",
            component: Item
        },
        {
            path: "/cartitem",
            name: "CartItem",
            component: CartItem
        },
        {
            path: "/login",
            name: "Login",
            component: Login
        },
        {
            path: "/SignUp",
            name: "SignUp",
            component: SignUp
        },
        {
            path: "/Profile/:usernameP",
            name: "Profile",
            component: Profile,
            props: true
        },
        {
          path: "/cart",
          name: "Cart",
          component : Cart
        },
        {
            path: "/Receipts",
            name: "Receipts",
            component: Receipts
        }
    ]
});
