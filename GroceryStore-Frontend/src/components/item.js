import axios from 'axios';
import Item from "@/components/Item.vue";
var config = require('../../config')

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl ="http://" + config.dev.backendHost + ":" + config.dev.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});


export default {
   data() {
    return {
	  items: [],
	  cart: null,
	  quantity: null,
    };
  },
  
  created () {
	  var hard = "testAccount";
    AXIOS.get("item/all/")
      .then(response => {
        this.items = response.data;
      })
      .catch(e => {
        console.log(e);
	  });

	AXIOS.get("cart/getWithUsername", {
			params: {
				username: hard
			}
		})
      .then(response => {
		this.cart = response.data;
		if (this.cart == null){
			AXIOS.post("cart/createCart", {
			params: {
				date: Date().toISOString().slice(0, 10),
				username: hard
				}
			})
			.then(response => {
				this.cart = response.data;
			})
			.catch(e => {
				console.log(e);
			});
		}
      })
      .catch(e => {
        console.log(e);
	  });
  },

  props: {
    itemId: Number,
    itemName: String,
    itemPrice: Number,
  },

  methods:  {
		
		
		addToCartZ: function(input){
			console.log(input);
			AXIOS.get("/quantity/itemId/"+input)
			.then(response => {
				this.quanity = response.data;
				if (this.quanity == null){
					AXIOS.post("/quantity", {
					params: {
						count: 1,
						itemId: input,
						cartId: this.card.id,
						}
					})
					.then(response => {
						this.cart = response.data;
					})
					.catch(e => {
						console.log(e);
					});
				}

			})
			.catch(e => {
				console.log(e);
			});
		}

  }
	

};

