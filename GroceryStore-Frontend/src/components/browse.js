import axios from 'axios';
import Item from "@/components/Item.vue";
var config = require('../../config')

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl ="http://" + config.dev.backendHost + ":" + config.dev.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

export default {

  data() {
    return {
	  items: [],
    };
  },

  created () {
    AXIOS.get("item/all/")
      .then(response => {
        this.items = response.data;
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
	  });

	
  },

	
  components: {
    Item,
  }

}