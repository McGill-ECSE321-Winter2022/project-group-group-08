import Vue from "vue";
import Router from "vue-router";
import Hello from "@/components/Hello";
import Login from "@/components/Login.vue";
import SignUp from "@/components/SignUp.vue";
import Profile from "@/components/Profile.vue";
import Shifts from "@/components/Shifts.vue";
import ViewCustomers from "@/components/ViewCustomers.vue";
import ViewEmployees from "@/components/ViewEmployees.vue";

Vue.use(Router);

export default new Router({
    routes: [{
            path: "/",
            name: "Hello",
            component: Hello
        },
        {
            path: "/Login",
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
            path: "/viewcustomers",
            name: "ViewCustomers",
            component: ViewCustomers
        },
        {
            path: "/viewemployees",
            name: "ViewEmployees",
            component: ViewEmployees
        }
    ]
});