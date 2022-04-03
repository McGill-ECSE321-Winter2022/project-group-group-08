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
                    itemImage[item.id] = item.image;
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
            itemImage: {},
            price: {},
            point: {},
            returnPolicy: {},
            pickup: "",
            inStoreQuantity: {},
            itemId: "",
            itemName2: "",
            itemImage2: "",
            newItemName: "",
            newItemImage: "",
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
        updateItemAttributes: function(id, itemName, itemImage, price, point, returnPolicy, pickup, inStoreQuantity){
            console.log("hereeee");
            AXIOS.patch("/item/update/"+id, {},{ 
                    params: {
                        name: itemName,
                        image: itemImage,
                        price: price,
                        point: point,
                        returnPolicy: returnPolicy,
                        pickup: pickup,
                        inStoreQuantity: inStoreQuantity
                    }
                }
            ).then(response => {
                console.log("here");
                console.log(response.data);
                AXIOS.get("/item/all")
                .then(response2 => {
                    this.items = response2.data;
                    for (item in items){
                        itemImage[item.id] = item.image;
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
        deleteItem: function(itemId) {
            console.log(itemId);
            AXIOS.delete("/item/delete/"+itemId)
                .then(response => {
                    AXIOS.get("/item/all")
                    .then(response2 => {
                        this.items = response2.data;
                        for (item in items){
                            itemName[item.id] = item.name;
                            itemImage[item.id] = item.image;
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
                .catch(e => {
                    var errorMsg = e.response.data.message;
                    console.log(errorMsg);
                });
        },

        addItemToInventory: function(itemName2,itemImage2, itemPrice, itemPoint, itemReturnPolicy, itemPickup, itemInStoreQuantity){
            if(itemPickup == ""){
                itemPickup = false;
            }
            AXIOS.post(
                "/item/", {}, {
                    params:{
                        name: itemName2,
                        image: itemImage2,
                        price: itemPrice,
                        point: itemPoint,
                        returnPolicy: itemReturnPolicy,
                        pickup: itemPickup,
                        inStoreQuantity: itemInStoreQuantity
                    }
                }
            ).then(response => {
                this.items.push(response.data);
                this.itemName2 = "";
                this.itemImage2 = "";
                this.itemPrice = "";
                this.itemPoint = "";
                this.itemReturnPolicy = "";
                this.itemPickup="";
                this.itemInStoreQuantity="";
                this.newItemName = "";
                this.newItemImage = "";
            }).catch(e => {
                console.log(e);
            });
        },
      
    }
};