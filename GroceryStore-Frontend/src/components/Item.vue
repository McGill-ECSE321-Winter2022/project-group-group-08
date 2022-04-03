
<template>
  <tr :key="curId">
    <td class="table-text"> <img :src=itemImage style="width:50px;"></td>
    <td class="table-text">{{ itemName }}</td>
    <td class="table-text">{{ itemPrice }}</td>
	<td class="table-text">  </td>
	<button v-if="validUser" class="btn btn-light" @click="addToCartZ(curId)"> Add to cart </button>
  </tr>
</template>
<script src="./item.js"></script>
