<script>
import axios from "axios";
import Quantity from "@/components/Quantity.vue";
var config = require("../../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl = "" + config.build.backendHost;

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
    name: "Cart",

    created() {
        //Get User from Backend
        AXIOS.get("/cart/username", {
                params: {
                    username: sessionStorage.getItem("username")
                }
            })
            .then(response => {
                this.cart = response.data;
                sessionStorage.setItem("cartId", response.data.id);
            })
            .catch(e => {});
    },

    data() {
        return {
            // Set Variable
            cart: {}
        };
    },

    components: {
        Quantity
    },
    methods: {
        onCheckout() {
            AXIOS.post(
                    "receipt", {}, {
                        params: {
                            cartId: sessionStorage.getItem("cartId"),
                            receiptStatus: "Processed",
                            receiptType: document.getElementById("type").value
                        }
                    }
                )
                .then(response => {})
                .catch(e => {
                    console.log(e);
                });
            AXIOS.delete(
                    "/quantity/delete/cartId/" + sessionStorage.getItem("cartId"), {
                        params: {
                            buyItems: true
                        }
                    }
                )
                .then(response => {
                    this.cart = response.data;
                    sessionStorage.setItem("cartId", response.data.id);
                    return AXIOS.get("/cart/username", {
                        params: {
                            username: sessionStorage.getItem("username")
                        }
                    });
                })
                .then(response => {
                    this.cart = response.data;
                    sessionStorage.setItem("cartId", response.data.id);
                })
                .catch(e => {
                    console.log(e);
                })
                .finally(() =>
                    this.$refs.quantityRef.onClear(sessionStorage.getItem("cartId"))
                );
        }
    }
};
</script>


<template>
  <!-- Show Cart Info -->
  <div id="cartitem">
    <br />
    <h3>Here's your cart:</h3>
    <div class="checkout">
      <button style="padding: 20px;font-size: 20px;" class="btn btn-light" @click="onCheckout()">
        Checkout and Pay
      </button>
    </div>
    <br />
    <select name="type" id="type" style="padding: 12px;width: 160px;">
      <option value="Pickup" key="Pickup" style="font-size: 50px;">Pickup</option>
      <option value="Delivery" key="Delivery" style="font-size: 50px;">Delivery</option>
    </select>
    <div class="cMiddle">
      <Quantity :cartId="cart.id" ref="quantityRef"></Quantity>
    </div>
    <!-- Checkout Button -->
  </div>
</template>

<style scoped>
.cMiddle {
  display: flex;
  justify-content: center;
}
</style>
