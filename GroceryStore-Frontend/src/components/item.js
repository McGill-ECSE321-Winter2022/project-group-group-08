import axios from "axios";
import Item from "@/components/Item.vue";
var config = require("../../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl = "" + config.build.backendHost;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
  data() {
    return {
      // Set Variables
      items: [],
      cart: null,
      items: null,
      quant2: [],
      quantityNum: null
    };
  },

  created() {
    var hard = sessionStorage.getItem("username");

    // Initialize items from backend
    AXIOS.get("item/all/")
      .then(response => {
        this.items = response.data;
      })
      .catch(e => {});

    // Get Person
    AXIOS.get("cart/getWithUsername", {
      params: {
        username: hard
      }
    })
      .then(response => {
        this.cart = response.data;
        sessionStorage.setItem("cartId", response.data.id);
      })
      .catch(e => {
        AXIOS.post("cart", {
          params: {
            date: Date()
              .toISOString()
              .slice(0, 10),
            accountUsername: hard
          }
        })
          .then(response => {
            this.cart = response.data;
            sessionStorage.setItem("cartId", response.data.id);
          })
          .catch(e => {});
      });
  },

  props: {
	  // Set Props
    curId: Number,
    itemImage: String,
    itemName: String,
    itemPrice: Number,
	role: String,
	itemPoints: Number,
  },

  methods: {
	/**
	 * addToCartZ
	 * Add item to user cart
	 * @param {*} input 
	 */
    addToCartZ: function(input) {
      AXIOS.get("quantity/cartId/" + sessionStorage.getItem("cartId"))
        .then(response => {
          this.quant2 = response.data;
          var found = false;
          for (let i = 0; i < this.quant2.length; i++) {
            if (this.quant2[i].item.id == this.curId) {
              this.items = this.quant2[i].item;
              this.quantityNum = this.quant2[i].count;

              this.quantityNum = this.quantityNum + 1;
              AXIOS.patch(
                "/quantity/update/" + this.quant2[i].id,
                {},
                {
                  params: {
                    count: this.quantityNum,
                    itemId: input,
                    cartId: this.cart.id
                  }
                }
              )
                .then(response => {})
                .catch(e => {});
              found = true;
              break;
            }
          }

          if (!found) {
            AXIOS.post(
              "/quantity/",
              {},
              {
                params: {
                  count: 1,
                  itemId: input,
                  cartId: sessionStorage.getItem("cartId")
                }
              }
            )
              .then(response => {})
              .catch(e => {});
          }
        })
        .catch(e => {});
    }
  }
};
