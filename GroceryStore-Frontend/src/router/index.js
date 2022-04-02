import Vue from "vue";
import Router from "vue-router";
import Hello from "@/components/Hello.vue";
import Item from "@/components/Item.vue";
import Browse from "@/components/Browse.vue";
// import ViewShift from '@/components/ViewShift';

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
        }
        // {
        //   path: "/ViewShifts",
        //   name: "ViewShifts",
        //   component : ViewShift
        // }
    ]
});