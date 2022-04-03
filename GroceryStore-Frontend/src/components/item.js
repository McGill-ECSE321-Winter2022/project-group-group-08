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
	  items: null,
	  quant2: [],
	  quantityNum: null,
    };
  },
  
  created () {
	  var hard = sessionStorage.getItem("username");
		
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
			console.log("doggy");
			this.cart = response.data;
			sessionStorage.setItem("cartId", response.data.id)
		})
      .catch(e => {
		console.log(e.response.data);
		console.log("doggy2");
				
				AXIOS.post("cart", {
				params: {
					date: Date().toISOString().slice(0, 10),
					accountUsername: hard
					}
				})
				.then(response => {
					this.cart = response.data;
					sessionStorage.setItem("cartId", response.data.id)
				})
				.catch(e => {
					console.log(e);
				});
	  });
  },

  props: {
    curId: Number,
    itemName: String,
    itemPrice: Number,
	validUser: Boolean
  },

  methods:  {
		
		
		addToCartZ: function(input){
				
				AXIOS.get("quantity/cartId/"+sessionStorage.getItem("cartId"))
				.then(response => {
					this.quant2 = response.data;
					var found = false;
					for (let i = 0; i < this.quant2.length; i++) { 

					if (this.quant2[i].item.id== this.curId){
						console.log("found exisiting quant");
						
						this.items = this.quant2[i].item;
						this.quantityNum = this.quant2[i].count;

						this.quantityNum = this.quantityNum +1;
						AXIOS.patch("/quantity/update/" + this.quant2[i].id, {},{
						params: {
							count:  this.quantityNum,
							itemId: input,
							cartId: this.cart.id,
							}
						})
						.then(response => {
							console.log(response.data);
						
						})
						.catch(e => {
							console.log(e);
						});
						found = true;
						break;
					}
				}

				if (!found){
					AXIOS.post("/quantity/", {},{
					params: {
						count: 1,
						itemId: input,
						cartId: this.cart.id,
						}
					})
					.then(response => {
						console.log(response.data);
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

