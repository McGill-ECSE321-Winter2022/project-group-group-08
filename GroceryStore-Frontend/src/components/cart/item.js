import axios from "axios";
var config = require("../../../config");

// var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
// var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
var frontendUrl;
var backendUrl;
if (process.env.NODE_ENV === "production") {
    console.log("prod env");
    frontendUrl = "https://" + config.build.host + ":" + config.build.port;
    backendUrl =
        "https://" + config.build.backendHost + ":" + config.build.backendPort;
} else if (process.env.NODE_ENV === "development") {
    console.log("dev env");
    frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
    backendUrl =
        "http://" + config.dev.backendHost + ":" + config.dev.backendPort;
}

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { "Access-Control-Allow-Origin": frontendUrl }
});

/**
 * presents the item data
 */
export default {
    name: "Item",

    props: {
        itemId: Number,
        itemImage: String,
        itemName: String,
        itemPrice: Number,
        itemQuantity: Number,
        quantityId: Number
    },
    methods: {
        /**
         * deleteItem
         * Deletes item from database
         * @param {*} qId
         */
        deleteItem: function(qId) {
            AXIOS.delete("/quantity/delete/" + qId)
                .catch(e => {
                    var errorMsg = e.response.data.message;
                })
                .finally(() => this.$emit("quantityUpdate"));
        },

        /**
         * changeQuantity
         * Update quantity for given item
         * @param {*} amount
         * @param {*} qId
         * @param {*} iId
         */
        changeQuantity: function(amount, qId, iId) {
            AXIOS.patch(
                    "/quantity/update/" + qId, {}, {
                        params: {
                            count: amount,
                            cartId: sessionStorage.getItem("cartId"),
                            itemId: iId
                        }
                    }
                )
                .catch(e => {})
                .finally(() => this.$emit("quantityUpdate"));
        }
    }
};