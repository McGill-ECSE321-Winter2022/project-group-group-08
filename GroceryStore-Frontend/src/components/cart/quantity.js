import axios from "axios";
import Item from "@/components/cart/Item.vue";
var config = require("../../../config");

// var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
// var backendUrl =
//   "http://" + config.dev.backendHost + ":" + config.dev.backendPort;
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

export default {
    name: "Quantity",
    data() {
        return {
            // Set Variables
            quantities: [],
            loading: false
        };
    },

    created() {
        this.loading = true;
        // Get Item Quantities
        AXIOS.get("quantity/cartId/" + sessionStorage.getItem("cartId"))
            .then(response => {
                this.quantities = response.data;
            })
            .catch(e => {})
            .finally(() => (this.loading = false));
    },

    methods: {
        emitEventChanged() {
            this.$emit("CustomEventInputChanged", this.quantities);
        },
        /**
         * onUpdate
         * Update Item Quantities
         */
        onUpdate() {
            this.loading = true;
            AXIOS.get("quantity/cartId/" + sessionStorage.getItem("cartId"))
                .then(response => {
                    this.quantities = response.data;
                })
                .catch(e => {
                    console.log(e);
                })
                .finally(() => (this.loading = false));
        },

        onClear(cId) {
            this.loading = true;
            AXIOS.get("quantity/cartId/" + cId)
                .then(response => {
                    this.quantities = response.data;
                })
                .catch(e => {
                    console.log(e);
                })
                .finally(() => (this.loading = false));
        }
    },

    components: {
        Item
    },

    computed: {
        /**
         * sortedQuantities
         * Sort items by quantity
         * @returns
         */
        sortedQuantities: function() {
            function compare(a, b) {
                if (a.item.name < b.item.name) return -1;
                if (a.item.name > b.item.name) return 1;
                return 0;
            }
            return this.quantities.sort(compare);
        }
    }
};