import Vue from "vue";
import Router from "vue-router";
import Hello from "@/components/Hello.vue";
import Login from "@/components/Login.vue";
import SignUp from "@/components/SignUp.vue";
import Profile from "@/components/Profile.vue";

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
        }
    ]
});