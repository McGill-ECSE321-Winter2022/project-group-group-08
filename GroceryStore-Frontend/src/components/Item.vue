<template>
  <tr :key="itemId">
    <td class="table-text">{{ itemName }}</td>
    <td class="table-text">{{ itemPrice }}</td>
    <td class="table-text">{{ itemQuantity }}</td>
    <!-- <input
      style=""
      type="text"
      v-model="newQuantity"
      :placeholder="[[newQuantity]]"
    />
    <button class="btn btn-light" @click="updateQuantity(newQuantity)">
      Update Quantity
    </button>
    <button class="btn btn-light" @click="deleteItem(item.id)">
      Delete Item
    </button> -->
  </tr>
</template>

<script src="./item.js"></script>
