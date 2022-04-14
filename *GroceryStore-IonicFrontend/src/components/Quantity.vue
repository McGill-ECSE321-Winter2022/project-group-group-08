<script>

import axios from "axios";
import CartItem from "@/components/CartItem.vue";
var config = require("../../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl = "" + config.build.backendHost;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
  name: "Quantity",
  data() {
    return {
      // Set Variables
      quantities: [],
      loading: false
    };
  },

  created() {
    this.loading = true;
    // Get Item Quantities
    AXIOS.get("quantity/cartId/" + sessionStorage.getItem("cartId"))
      .then(response => {
        this.quantities = response.data;
      })
      .catch(e => {})
      .finally(() => (this.loading = false));
  },

  methods: {
    emitEventChanged(){
      this.$emit('CustomEventInputChanged', this.quantities);
    },
    /**
     * onUpdate
     * Update Item Quantities
     */
    onUpdate() {
      this.loading = true;
      AXIOS.get("quantity/cartId/" + sessionStorage.getItem("cartId"))
        .then(response => {
          this.quantities = response.data;
        })
        .catch(e => {
          console.log(e);
        }).finally(() => this.loading = false);
      },

      onClear(cId) {
        this.loading = true;
        AXIOS.get("quantity/cartId/" + cId)
        .then(response => {
          this.quantities = response.data;
        })
        .catch(e => {
          console.log(e);
        }).finally(() => this.loading = false);
    }
  },

  components: {
    CartItem
  },

  computed: {
    /**
     * sortedQuantities
     * Sort items by quantity
     * @returns 
     */
    sortedQuantities: function() {
      function compare(a, b) {
        if (a.item.name < b.item.name) return -1;
        if (a.item.name > b.item.name) return 1;
        return 0;
      }
      return this.quantities.sort(compare);
    }
  }
};

</script>


<template>
    <!-- Item Quantity Display Data -->
    <table  class="elevation-1" >
            <tbody>
                <CartItem 
                  v-for="quantity in sortedQuantities" 
                  :key="quantity.id" 
                  :itemId="quantity.item.id"
                  :itemName="quantity.item.name"
                  :itemImage="quantity.item.image"
                  :itemPrice="quantity.item.price"
                  :itemQuantity="quantity.count"
                  :itemPoint="quantity.item.point"
                  :inStoreQuantity="quantity.item.inStoreQuantity"
                  :quantityId="quantity.id"
                  @quantityUpdate="onUpdate"
                ></CartItem>
            </tbody>

        </table> 
</template>

