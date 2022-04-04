import axios from 'axios';
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

/**
 * presents the item data
 */
export default {
  name: 'Item',

  props: {
    itemId: Number,
    itemName: String,
    itemPrice: Number,
    itemQuantity: Number,
    // itemPoint: Number,
    // inStoreQuantity: Number,
    quantityId: Number,
  },
  methods: {

    deleteItem: function (qId) {
      AXIOS.delete("/quantity/delete/" + qId)
        .catch(e => {
          var errorMsg = e.response.data.message;
          console.log(errorMsg);
        }).finally(() => this.$emit('quantityUpdate'));
    },

    changeQuantity: function ( amount, qId, iId) {
      // inStoreQuantity+=quantity;
      AXIOS.patch("/quantity/update/" + qId, {}, {
        params: {
          count: amount,
          cartId: sessionStorage.getItem("cartId"),
          itemId: iId
        }
      })
        .catch(e => {
          console.log(e);
        }).finally(() => this.$emit('quantityUpdate'));
    },
  }
};