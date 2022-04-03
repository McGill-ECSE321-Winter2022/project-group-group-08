import axios, { Axios } from "axios";
var config = require("../../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl = "http://" + config.dev.backendHost + ":" + config.dev.backendPort;

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
    name: "receipts",

    created: function() {
        AXIOS.get("/receipt/all")
        .then(response => {
            console.log(response.data)
            this.receipts = response.data;
            for (receipt in receipts){
                receiptStatus[receipt.receiptNum] = receipt.receiptStatus;
            }
        })
        .catch(e => {
            this.errorReceipts = e;
        });
    },
    data() {
        return {
            receipts: [],
            receiptStatus:[],
            errorReceipts:"",
            filter: "",
            showReceipts: true
        };
    },

    methods: {
        updateReceipt: function(receiptNum, cartId, receiptStatus, receiptType){
            AXIOS.patch("/receipt/update/"+receiptNum, {},{ 
                    params: {
                        cartId: cartId,
                        receiptStatus: receiptStatus,
                        receiptType: receiptType,
                    }
                }
            ).then(response => {
                AXIOS.get("/receipt/all")
                .then(response2 => {
                    this.receipts = response2.data;
                    for (receipt in receipts){
                        receiptStatus[receipt.receiptNum] = receipt.receiptStatus;
                    }
                })
                .catch(e => {
                    this.errorReceipts = e;
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
        getReceiptByCart: function(cartId){
            AXIOS.patch("/receipt/getReceiptsByCart/", {},{ 
                    params: {
                        cartId: cartId
                    }
                }
            ).then(response => {
                this.receipts = response.data;
                for (receipt in receipts){
                    receiptStatus[receipt.receiptNum] = receipt.receiptStatus;
                }
            })
            .catch(error => {
                console.log(error.response.data);
                var errorMsg = error
                if ( error.response ) {
                    errorMsg = error.response.data
                }
            });
        },
        getReceiptByStatus: function(event){
            AXIOS.get("/receipt/getWithStatus/", { 
                    params: {
                        status: event.target.value
                    }
                }
            ).then(response => {
                this.receipts = response.data;
                for (receipt in receipts){
                    receiptStatus[receipt.receiptNum] = receipt.receiptStatus;
                }
            })
            .catch(error => {
                console.log(error.response.data);
                var errorMsg = error
                if ( error.response ) {
                    errorMsg = error.response.data
                }
            });
        },
        getReceiptByType: function(event){
            AXIOS.get("/receipt/getWithType/", { 
                    params: {
                        type: event.target.value
                    }
                }
            ).then(response => {
                this.receipts = response.data;
                for (receipt in receipts){
                    receiptStatus[receipt.receiptNum] = receipt.receiptStatus;
                }
            })
            .catch(error => {
                console.log(error.response.data);
                var errorMsg = error
                if ( error.response ) {
                    errorMsg = error.response.data
                }
            });
        },
        getAccountByUsername: function(username){
            AXIOS.get("/getAccountByUsername/"+username, { 
                
            }
            ).then(response => {
                console.log(response.data);
                AXIOS.get("/receipt/getReceiptsByUsername/", {},{ 
                    params: {
                        username: response.data.username
                    }
                }).then(response => {
                    this.receipts = response.data;
                    for (receipt in receipts){
                        receiptStatus[receipt.receiptNum] = receipt.receiptStatus;
                    }
                })
                .catch(error => {
                    console.log(error.response.data);
                    var errorMsg = error
                    if ( error.response ) {
                        errorMsg = error.response.data
                    }
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
        filterToggle(event){
            this.filter = event.target.value;
            console.log(this.filter);
            if(this.filter == "blank"){
                this.showReceipts = true;
                AXIOS.get("/receipt/all")
                .then(response2 => {
                    this.receipts = response2.data;
                    for (receipt in receipts){
                        receiptStatus[receipt.receiptNum] = receipt.receiptStatus;
                    }
                })
                .catch(e => {
                    this.errorReceipts = e;
                });
            }
            if(this.filter == "Name"){
                this.showReceipts = false;
            }else{
                this.showReceipts = true;
            }
        }
    //     getRecieptByStatus: function(event){
    //         AXIOS.get("/receipt/day/"+event.target.value
    //         ).then(response => {
    //             this.hours = response.data;
    //             for (hour in hours){
    //                 startTime[hour.id] = hour.startTime;
    //                 endTime[hour.id] = hour.endTime;
    //             }
    //         })
    //         .catch(error => {
    //             var errorMsg = error
    //             if ( error.response ) {
    //                 errorMsg = error.response.data
    //             }
    //         });
    //     },
    //     filterToggle(event){
    //         this.filter = event.target.value;
    //         console.log(this.filter);
    //         if(this.filter == "blank"){
    //             AXIOS.get("/businesshour/allEmployee")
    //             .then(response => {
                    
    //                 this.hours = response.data;
    //                 for (hour in hours){
    //                     startTime[hour.id] = hour.startTime;
    //                     endTime[hour.id] = hour.endTime;
    //                 }
    //             })
    //             .catch(e => {
    //                 this.errorHours = e;
    //             });
    //         }
    //         if(this.filter == "Name"){
    //             this.showHours = false;
    //         }else{
    //             this.showHours = true;
    //         }
    //     }
    }
};