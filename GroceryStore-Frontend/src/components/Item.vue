<template>
  <tr :key="itemId">
    <td class="table-text">{{ itemName }}</td>
    <td class="table-text">{{ itemPrice }}</td>
    <td class="table-text">{{ itemQuantity }}</td>
    <button class="btn btn-light" @click="changeQuantity(itemQuantity - 1, quantityId, itemId)">-</button>
    <button class="btn btn-light" @click="changeQuantity(itemQuantity + 1, quantityId, itemId)">+</button> 
    <button class="btn btn-light" @click="deleteItem(quantityId)">Delete Item</button>
  </tr>
</template>

<script src="./item.js"></script>
