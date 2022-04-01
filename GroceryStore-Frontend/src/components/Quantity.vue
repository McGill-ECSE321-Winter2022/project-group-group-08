<template>
    <table  class="elevation-1" >
            <th>
                <td class="table-text">| Item Name |</td>
                <td class="table-text">| Price |</td>
                <td class="table-text">| Quantity |</td>
            </th>
            <!-- item goes here -->
            <tbody>
                <Item 
                  v-for="quantity in quantities" 
                  :key="quantity.id" 
                  :itemId="quantity.item.id"
                  :itemName="quantity.item.name"
                  :itemPrice="quantity.item.price"
                  :itemQuantity="quantity.count"
                ></Item>
            </tbody>
        </table> 
</template>

<script src="./quantity.js"></script>
