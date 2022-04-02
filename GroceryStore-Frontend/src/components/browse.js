import axios from 'axios';
import Item from "@/components/Item.vue";
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

export default {
  name: 'Item',
  data() {
    return {
      items: []
    };
  },


  created () {
    console.log(items);
    AXIOS.get("getAllItems()")
      .then(response => {
        this.items = response.data;
      })
      .catch(e => {
        console.log(e);
      });
  },


  components: {
    Item,
  }

}