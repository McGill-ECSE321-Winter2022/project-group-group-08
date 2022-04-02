<template>

    <table class="elevation-1" >
		<h2>Login Menu</h2>
            <th>
                <td class="table-text">| Item Name |</td>
                <td class="table-text">| Price |</td>
            </th>
            <!-- item goes here -->
            <tbody>
				
                <Item 
                  v-for="item in items" 
                  :key="item.id" 
                  :itemName="item.name"
                  :itemPrice="item.price"
                ></Item>
            </tbody>
        </table> 
</template>

<script src="./browse.js"></script>