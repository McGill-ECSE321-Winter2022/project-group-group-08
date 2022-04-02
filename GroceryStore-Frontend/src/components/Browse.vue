<template>

    <table class="elevation-1" >
		
            <th>
                <td class="table-text">Item Name</td>
                <td class="table-text">Price |</td>
            </th>
            <!-- item goes here -->
            <tbody>
                <Item 
                  v-for="item in items" v-bind:key="item.id" 
                  :curId="item.id"
                  :itemName="item.name"
                  :itemPrice="item.price"
                ></Item>
            </tbody>
        </table> 
</template>
<script src="./browse.js"></script>