<template>
    <div id="cart">
        <Quantity 
            :cartId ="cart.id"
        ></Quantity>
        <div class="checkout">
            <button class="button">Checkout and Pay</button>
        </div>
    </div>
</template>

<script src="./cart.js">
    import Quantity from './Quantity';
</script>
<style>
</style>
