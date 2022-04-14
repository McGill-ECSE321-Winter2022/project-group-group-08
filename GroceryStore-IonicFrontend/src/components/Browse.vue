<script>
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

</script>
<template> 
  <div style="margin: 50px;">
    <br>
    <h1> Our Products </h1>
    <h5> Shop with confidence, Shop at Whole Foods</h5>
    <br>
      <div class="grid-container" style = "width: 100%;">
        
          <Item 
          v-for="item in items" 
          :key="item.name" 
          :curId="item.id"
          :itemImage="item.image"
          :itemPoints="item.point"
          :itemName="item.name"
          :itemPrice="item.price"
          :role="role"
          ></Item>
      
      </div>
      </div>
      
</template>

<style scoped>
.grid-container {
  width: 100%;
  justify-content: center;
  column-width: 300px;
  grid-column-gap: 0vw;
  
  /* below is better maintainably */
  display: flex;
  flex-wrap: wrap;

}

</style>