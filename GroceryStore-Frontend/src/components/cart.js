import axios from 'axios';
import Quantity from '@/components/Quantity.vue';
var config = require('../../config')

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
        username: "testAccount"
      }
    })
    
      .then(response => {
        this.cart = response.data;
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
  } 

  // methods: {

  //   deleteItem: function () {
  //     AXIOS.delete("/delete/" + this.ItemId, {}, {
  //       params: { ItemId: ItemId }
  //     })
  //       .then(response => {
  //         this.$router.push({ path: '/' });
  //       })
  //       .catch(e => {
  //         var errorMsg = e.response.data.message;
  //         console.log(errorMsg);
  //         this.deleteError = true;
  //       });
  //   },

  //   updateQuantity: function (QuantityId) {
  //     var quantity = this.newQuantity;
  //     if (this.newQuantity === "") {
  //       quantity = this.quantity;
  //     }
  //     AXIOS.patch("/Cart/" + QuantityId, {}, {
  //       params: {
  //         QuantityId: QuantityId,
  //         count: count,
  //         CartId: CartId,
  //         ItemId: ItemId
  //       }
  //     })
  //       .then(response => {
  //         this.Quantityid = "";
  //         this.CartId = "";
  //         this.ItemId = "";
  //         this.count = response.data.count
  //       })
  //       .catch(e => {
  //         var errorMsg = "No cart with such id exist";
  //         console.log(errorMsg);
  //         this.errorCart = this.errorMsg;
  //       });
  //   },
  // }
}