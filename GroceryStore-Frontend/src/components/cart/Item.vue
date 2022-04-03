<template>
 <div class="an">
   <p style="text-align:center;"><b>{{ itemName }}</b></p>&nbsp;
    <img :src="itemImage" style="width: 80px; margin: 10px;">
    
    <p style="text-align:left;">
      Price: ${{ itemPrice }}
      <span style="float:right;">
       Qty: {{ itemQuantity }}
    </span>
    </p>
 
    <button class="btn btn-light" @click="changeQuantity(itemQuantity - 1, quantityId, itemId)">-</button>&nbsp;
    <button class="btn btn-light" @click="changeQuantity(itemQuantity + 1, quantityId, itemId)">+</button> &nbsp;
    <button class="btn btn-light" @click="deleteItem(quantityId)">Del</button>
 
  </div>
</template>

<script src="./item.js"></script>
<style scoped>

.an {
  margin: 20px;
  padding: 40px;
  background-color: rgb(240, 240, 240);
  border-radius: 20px;

}
</style>