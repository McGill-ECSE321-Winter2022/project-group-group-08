<template>
    <div id="cart">
        <Quantity 
            :cartId ="cart.id"
            ref="quantityRef"
        ></Quantity>
        <div class="checkout">
            <button class="btn btn-light" @click="onCheckout(cart.id)">Checkout and Pay</button>
        </div>
    </div>
</template>

<script src="./cart.js">
</script>
