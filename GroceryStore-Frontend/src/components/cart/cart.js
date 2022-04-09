import axios from "axios";
import Quantity from "@/components/cart/Quantity.vue";
var config = require("../../../config");

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