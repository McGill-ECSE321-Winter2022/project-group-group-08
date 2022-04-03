import Vue from 'vue'
import Router from 'vue-router'
import Hello from "@/components/Hello.vue";
import Item from "@/components/Item.vue";
import Browse from "@/components/Browse.vue";
import itemInventory from '@/components/InventoryManagement.vue'
import storeManagement from '@/components/StoreManagement.vue'
import Login from "@/components/Login.vue";
import SignUp from "@/components/SignUp.vue";
import Profile from "@/components/Profile.vue";
import Shifts from "@/components/Shifts.vue";
import Cart from '@/components/cart/Cart.vue';
import ViewShifts from '@/components/viewShifts/ViewShifts.vue';
import ViewCustomers from "@/components/ViewCustomers.vue";
import ViewEmployees from "@/components/ViewEmployees.vue";
import Receipts from "@/components/Receipts.vue";

Vue.use(Router);

export default new Router({
    routes: [{
            path: "/",
            name: "Hello",
            component: Hello
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
            path: "/Shifts",
            name: "Shifts",
            component: Shifts
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
        },
        {
            path: "/viewcustomers",
            name: "ViewCustomers",
            component: ViewCustomers
        },
        {
            path: "/viewemployees",
            name: "ViewEmployees",
            component: ViewEmployees
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
        },
        {
            path: "/Receipts",
            name: "Receipts",
            component: Receipts
        }
    ]
});
