<template>
    <!-- Item Quantity Display Data -->
    <table  class="elevation-1" >
            <th>
                <td class="table-text">| Image |</td>
                <td class="table-text">| Item Name |</td>
                <td class="table-text">| Price |</td>
                <td class="table-text">| Quantity |</td>
            </th>
            <tbody>
                <Item 
                  v-for="quantity in sortedQuantities" 
                  :key="quantity.id" 
                  :itemId="quantity.item.id"
                  :itemName="quantity.item.name"
                  :itemPrice="quantity.item.price"
                  :itemQuantity="quantity.count"
                  :quantityId="quantity.id"
                  @quantityUpdate="onUpdate"
                ></Item>
            </tbody>

        </table> 
</template>

<script src="./quantity.js"></script>
