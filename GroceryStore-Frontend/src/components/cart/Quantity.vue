<template>
    <!-- Item Quantity Display Data -->
    <table  class="elevation-1" >
            <tbody>
                <Item 
                  v-for="quantity in sortedQuantities" 
                  :key="quantity.id" 
                  :itemId="quantity.item.id"
                  :itemName="quantity.item.name"
                  :itemImage="quantity.item.image"
                  :itemPrice="quantity.item.price"
                  :itemQuantity="quantity.count"
                  :quantityId="quantity.id"
                  @quantityUpdate="onUpdate"
                ></Item>
            </tbody>

        </table> 
</template>

<script src="./quantity.js"></script>
