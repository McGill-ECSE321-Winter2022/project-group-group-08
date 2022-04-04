<template>
    <!-- Show Cart Info -->
    <div id="cart">
        <br>
        <h3>Here's your cart:</h3>
         <div class="checkout">
            <button class="btn btn-light" @click="onCheckout(cart.id)">Checkout and Pay</button>
        </div>
        <div class="cMiddle"> 
        <Quantity  :cartId ="cart.id"></Quantity>
        </div>
        <!-- Checkout Button -->
       
    </div>
</template>

<script src="./cart.js">
</script>
<style scoped>

.cMiddle {
  display: flex;
  justify-content: center;

}
</style>