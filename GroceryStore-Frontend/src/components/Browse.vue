<template>

   
           
                <div class="grid-container" style = "width: 100%;">
                    <Item 
                    v-for="item in items" 
                    :key="item.name" 
                    :curId="item.id"
                    :itemImage="item.image"
                    :itemName="item.name"
                    :itemPrice="item.price"
                    :role="role"
                    ></Item>
                
                </div>
                
      
</template>
<script src="./browse.js"></script>
<style scoped>
.grid-container {
  width: 100%;
  column-width: 300px;
  display: inline-block;
}
</style>