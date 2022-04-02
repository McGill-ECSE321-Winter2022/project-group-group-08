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

//   methods: {

//     deleteItem: function () {
//       AXIOS.delete("/delete/" + itemId, {}, {
//         params: { itemId: itemId }
//       })
//         .then(response => {
//           this.$router.push({ path: '/' });
//         })
//         .catch(e => {
//           var errorMsg = e.response.data.message;
//           console.log(errorMsg);
//         });
//     },

//     minusQuantity: function () {
//       AXIOS.patch("/quantity/update" + QuantityId, {}, {
//         params: {
//           QuantityId: QuantityId,
//           count: count,
//           CartId: CartId,
//           ItemId: ItemId
//         }
//       })
//         .then(response => {
//           itemQuantity = itemQuantity-1
//         })
//         .catch(e => {
//           console.log(e);
//         });
//     },

//     plusQuantity: function () {
//       AXIOS.patch("/quantity/update/" + QuantityId, {}, {
//         params: {
          
//         }
//       })
//         .then(response => {
//           itemQuantity = item.Quantity+1
//         })
//         .catch(e => {
//           console.log(e);
//         });
//     },
//  }
}