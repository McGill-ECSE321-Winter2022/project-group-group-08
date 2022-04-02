import axios from 'axios';
import Quantity from '@/components/cart/Quantity.vue';
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

export default {
  name: 'Cart',

  created () {
    AXIOS.get("/cart/username",{
      params: {
        username: sessionStorage.getItem("username")
      }
    })
      .then(response => {
        this.cart = response.data;
        sessionStorage.setItem("cartId", response.data.id);
      })
      .catch(e => {
        console.log(e);
      });
  },

  data() {
    return {
      cart: {},
    };
  },

  components: {
    Quantity,
  },
}