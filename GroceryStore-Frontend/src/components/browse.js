import axios from "axios";
import Item from "@/components/Item.vue";
var config = require("../../config");

// var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
// var backendUrl =
//   "http://" + config.dev.backendHost + ":" + config.dev.backendPort;
var frontendUrl;
var backendUrl;
if (process.env.NODE_ENV === "production") {
    console.log("prod env");
    frontendUrl = "https://" + config.build.host + ":" + config.build.port;
    backendUrl =
        "https://" + config.build.backendHost + ":" + config.build.backendPort;
} else if (process.env.NODE_ENV === "development") {
    console.log("dev env");
    frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
    backendUrl =
        "http://" + config.dev.backendHost + ":" + config.dev.backendPort;
}

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
    data() {
        return {
            // Set Variables
            items: [],
            validUser: sessionStorage.getItem("validUser"),
            items: [],
            role: sessionStorage.getItem("role")
        };
    },

    created() {
        // Initialize items from Backend
        AXIOS.get("item/all/")
            .then(response => {
                this.items = response.data;
            })
            .catch(e => {});
    },

    components: {
        Item
    }
};