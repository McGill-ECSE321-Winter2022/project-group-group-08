<template>
    <!-- Show Cart Info -->
    <div id="cart">
        <Quantity 
            :cartId ="cart.id"
        ></Quantity>
        <!-- Checkout Button -->
        <div class="checkout">
            <button class="button">Checkout and Pay</button>
        </div>
    </div>
</template>

<script src="./cart.js">
</script>
