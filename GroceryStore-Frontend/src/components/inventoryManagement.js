import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

export default {
    name: "inventoryManagement",

    created: function() {
        AXIOS.get("/item/all")
            .then(response => {
                console.log("here");
                this.items = response.data;
                console.log(response.data);
                for (item in items){
                    itemName[item.id] = item.name;
                    price[item.id] = item.price;
                    point[item.id] = item.point;
                    returnPolicy[item.id] = item.returnPolicy;
                    inStoreQuantity[item.id] = item.inStoreQuantity;
                }
            })
            .catch(e => {
                this.errorItems = e;
            });
    },

    data() {
        return {
            items: [],
            id: "",
            itemName: {},
            price: {},
            point: {},
            returnPolicy: {},
            pickup: "",
            inStoreQuantity: {},
            itemId: "",
            itemName2: "",
            itemPrice: "",
            itemPoint: "",
            itemReturnPolicy: "",
            itemPickup: "",
            itemInStoreQuantity: "",
            errorItems: "",
            response: []
        };
    },

    methods: {
        createItems: function(itemName, price, point, returnPolicy, pickup, inStoreQuantity){
            AXIOS.post(
                "/item/", {}, {
                    params:{
                        itemName: itemName,
                        price: price,
                        point: point,
                        returnPolicy: returnPolicy,
                        pickup: pickup,
                        inStoreQuantity: inStoreQuantity
                    }
                }
            ).then(response => {
                this.items.push(response.data);
                this.itemName = "";
                this.price = "";
                this.point = "";
                this.returnPolicy = "";
                this.pickup="";
                this.inStoreQuantity="";
            }).catch(e => {
                console.log(e);
            });
        },

        updateItemAttributes: function(id, itemName, price, point, returnPolicy, pickup, inStoreQuantity){
            AXIOS.patch("/item/update/"+id, {},{ 
                    params: {
                        itemName: itemName,
                        price: price,
                        point: point,
                        returnPolicy: returnPolicy,
                        pickup: pickup,
                        inStoreQuantity: inStoreQuantity
                    }
                }
            ).then(response => {
                console.log(response.data);
                AXIOS.get("/item/all")
                .then(response2 => {
                    this.items = response2.data;
                    for (item in items){
                        itemName[item.id] = item.name;
                        price[item.id] = item.price;
                        point[item.id] = item.point;
                        returnPolicy[item.id] = item.returnPolicy;
                        inStoreQuantity[item.id] = item.inStoreQuantity;
                    }
                })
                .catch(e => {
                    this.errorItems = e;
                });
            })
            .catch(error => {
                console.log(error.response.data);
                var errorMsg = error
                if ( error.response ) {
                    errorMsg = error.response.data
                }
            });
        },
        deleteItem: function() {
            AXIOS.delete("/item/delete"+id)
                .then(response => {
                    this.$router.push({ path: `/` });
                })
                .catch(e => {
                    var errorMsg = e.response.data.message;
                    console.log(errorMsg);
                });
        },

        addItemToInventory: function(itemId, itemName2, itemPrice, itemPoint, itemReturnPolicy, itemPickup, itemInStoreQuantity){
            AXIOS.post(
                "/item/", {}, {
                    params:{
                        itemName2: itemName2,
                        itemPrice: itemPrice,
                        itemPickup: itemPoint,
                        itemReturnPolicy: itemReturnPolicy,
                        itemPickup: itemPickup,
                        itemInStoreQuantity: itemInStoreQuantity
                    }
                }
            ).then(response => {
                this.items.push(response.data);
                this.itemName2 = "";
                this.itemPrice = "";
                this.itemPoint = "";
                this.itemReturnPolicy = "";
                this.itemPickup="";
                this.itemInStoreQuantity="";
            }).catch(e => {
                console.log(e);
            });
        },
      
    }
};