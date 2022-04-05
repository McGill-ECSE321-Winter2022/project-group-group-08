<template>
  <!-- Show Cart Info -->
  <div id="cart">
    <br />
    <h3>Here's your cart:</h3>
    <div class="checkout">
      <button class="btn btn-light" @click="onCheckout()">
        Checkout and Pay
      </button>
    </div>
    <br />
    <select name="type" id="type">
      <option value="Pickup" key="Pickup">Pickup</option>
      <option value="Delivery" key="Delivery">Delivery</option>
    </select>
    <div class="cMiddle">
      <Quantity :cartId="cart.id" ref="quantityRef"></Quantity>
    </div>
    <!-- Checkout Button -->
  </div>
</template>

<script src="./cart.js"></script>
<style scoped>
.cMiddle {
  display: flex;
  justify-content: center;
}
</style>
