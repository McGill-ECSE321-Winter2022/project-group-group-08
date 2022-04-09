import axios from "axios";
import Item from "@/components/Item.vue";
var config = require("../../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl = "" + config.build.backendHost;

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
