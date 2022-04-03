<template>

   
           
                <div class="grid-container" style = "width: 100%;">
                    <Item 
                    v-for="item in items" :key="item.name" 
                    :curId="item.id"
                    :itemImage="item.image"
                    :itemName="item.name"
                    :itemPrice="item.price"
                    :validUser="validUser"
                    ></Item>
                
                </div>
      
</template>
<script src="./browse.js"></script>
<style scoped>
.grid-container {
  display: grid;
  grid-template-columns: 50% 50%;
}
</style>