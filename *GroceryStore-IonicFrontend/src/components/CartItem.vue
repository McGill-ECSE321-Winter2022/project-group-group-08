<script>
import axios from 'axios';
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = "" + config.build.backendHost;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

/**
 * presents the item data
 */
export default {
  name: 'CartItem',

  props: {
    itemId: Number,
    itemImage: String,
    itemName: String,
    itemPrice: Number,
    itemQuantity: Number,
    quantityId: Number,
  },
  methods: {

    /**
     * deleteItem
     * Deletes item from database
     * @param {*} qId 
     */
    deleteItem: function (qId) {
      AXIOS.delete("/quantity/delete/" + qId)
        .catch(e => {
          var errorMsg = e.response.data.message;
        }).finally(() => this.$emit('quantityUpdate'));
    },

    /**
     * changeQuantity
     * Update quantity for given item
     * @param {*} amount 
     * @param {*} qId 
     * @param {*} iId 
     */
    changeQuantity: function (amount, qId, iId) {
      AXIOS.patch("/quantity/update/" + qId, {}, {
        params: {
          count: amount,
          cartId: sessionStorage.getItem("cartId"),
          itemId: iId
        }
      })
        .catch(e => {
        }).finally(() => this.$emit('quantityUpdate'));
      
    },
 }
};
</script>
<template>
 <div class="an">
   <p style="text-align:center;font-size: 30px;" ><b>{{ itemName }}</b></p>&nbsp;
    <img :src="itemImage" style="width: 120px; margin: 10px;">
    
    <p style="text-align:left; font-size: 25px;">
      Price: ${{ itemPrice }}
      <span style="margin-left: 20px;float:right;font-size: 25px;">
       Qty: {{ itemQuantity }}
    </span>
    </p>
 
    <button class="btn btn-light" @click="changeQuantity(itemQuantity - 1, quantityId, itemId)" style="padding: 10px;font-size: 25px;font-weight: 700;">-</button>&nbsp;
    <button class="btn btn-light" @click="changeQuantity(itemQuantity + 1, quantityId, itemId)" style="padding: 10px;font-size: 25px;font-weight: 700;">+</button> &nbsp;
    <button class="btn btn-light" @click="deleteItem(quantityId)" style="padding: 10px;font-size: 25px;">Del</button>
 
  </div>
</template>

<style scoped>

.an {
  margin: 20px;
  padding: 40px;
  background-color: rgb(240, 240, 240);
  border-radius: 20px;

}
</style>
