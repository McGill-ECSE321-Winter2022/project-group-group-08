
<template>
  <tr :key="itemId">
    <td class="table-text">{{ itemName }}</td>
    <td class="table-text">{{ itemPrice }}</td>
	<td class="table-text">  </td>
	<button class="btn btn-light" @click="addToCartZ(curId)"> Add to cart </button>
  </tr>
</template>
<script src="./item.js"></script>
