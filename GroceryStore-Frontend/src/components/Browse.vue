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
                    :itemName="item.name"
                    :itemPrice="item.price"
                    :role="role"
                    ></Item>
                
                </div>
                </div>
      
</template>
<script src="./browse.js"></script>
<style scoped>
.grid-container {
  width: 100%;
  column-width: 300px;
  grid-column-gap: 1vw;
  display: inline-block;
}
</style>