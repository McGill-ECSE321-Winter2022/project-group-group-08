import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});
    
export default{
    name:'InventoryManagement',

    created: function () {
        AXIOS.get("/item/inventory")
        .then(response => {
            this.inventory=response.data
        })
        .catch(e=> {
            this.errorInventory=e;
        });
    },

    data(){
        return{
            items: [],
            name: "",
            price: "",
            point: "",
            returnPolicy: "",
            pickup: "",
            inStoreQuantity: "",
            filter: "",
            id: "",
            response:[]
        }; 
    },
    
    methods: {
        updateItem: function(id, name, price, point, returnPolicy, pickup, inStoreQuantity){
            AXIOS.patch("/item/update/"+id, {},{ 
                    params: {
                        name: name,
                        price: price,
                        point: endTime,
                        working: working,
                        employeeId: employeeId,
                        groceryStoreSystemName: ""
                    }
                }
            ).then(response => {
                console.log(response.data);
                AXIOS.get("/businesshour/allEmployee")
                .then(response2 => {
                    
                    this.hours = response2.data;
                    for (hour in hours){
                        startTime[hour.id] = hour.startTime;
                        endTime[hour.id] = hour.endTime;
                    }
                })
                .catch(e => {
                    this.errorHours = e;
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
}
}