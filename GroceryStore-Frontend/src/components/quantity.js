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
  name: 'Cart',
  data() {
    return {
      quantities: []
    };
  },

  props: {
    cartId: {
      type: Number,
      default: -1
    }
  },

  created () {
    console.log(cartId);
    AXIOS.get("quantity/cartId/" + cartId)
      .then(response => {
        this.quantities = response.data;
      })
      .catch(e => {
        console.log(e);
      });
  },


  components: {
    Item,
  }

}
