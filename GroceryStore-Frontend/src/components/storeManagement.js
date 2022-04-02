import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

export default {
    name: "storeManagement",

    created: function() {
        AXIOS.get("/businesshour/store")
            .then(response => {
                console.log("here");
                this.hours = response.data;
                console.log(response.data);
                for (hour in hours){
                    startTime[hour.id] = hour.startTime;
                    endTime[hour.id] = hour.endTime;
                }
            })
            .catch(e => {
                this.errorHours = e;
            });
    },
    newHour: {
        day: 'Monday',
        startTime: '9:00',
        endTime: '11:00'
    },
    errorHours: '',

    data() {
        return {
            hours: [],
            id: "",
            day: "",
            startTime: {},
            endTime: {},
            working: "",
            // employeeId: "",
            // groceryStoreSystemName: "",
            employeeDiscount: "",
            errorHours: "",
            response: []
        };
    },

    // props: {
    //     day: String,
    //     startTime: String,
    //     endTime: String,
    //     working: 
    // },

    methods: {
        // createStoreHours: function(groceryStoreSystemName, day, working, startTime, endTime){
        //     AXIOS.post(
        //         "/grocerystoresystem/storeHours" + groceryStoreSystemName, {}, {
        //             params:{
        //                 day: day,
        //                 working: working,
        //                 startTime: startTime,
        //                 endTime: endTime
        //             }
        //         }
        //     ).then(response => {
        //         this.hours.push(response.data);
        //         this.day = "";
        //         this.working = "";
        //         this.startTime = "";
        //         this.endTime = "";
        //     }).catch(e => {
        //         console.log(e);
        //     });
        // },
        updateStoreHours: function(id, day, startTime, endTime, working, storeName){
            AXIOS.patch("/businesshour/update/"+id, {},{ 
                    params: {
                        day: day,
                        startTime: startTime,
                        endTime: endTime,
                        working: working,
                        groceryStoreSystemName: storeName,
                        employeeId: ""
                    }
                }
            ).then(response => {
                console.log(response.data);
                // AXIOS.get("/grocerystoresystem/storeManagement")
                // .then(response2 => {
                //     this.hours = response2.data;
                //     for (hour in hours){
                //         startTime[hour.id] = hour.startTime;
                //         endTime[hour.id] = hour.endTime;
                //     }
                // })
                // .catch(e => {
                //     this.errorHours = e;
                // });
            })
            .catch(error => {
                console.log(error.response.data);
                var errorMsg = error
                if ( error.response ) {
                    errorMsg = error.response.data
                }
            });
        },
        // updateEmployeeDiscount: function(employeeDiscount){
        //     AXIOS.patch("/grocerystoresystem/employeeDiscount/", {},{ 
        //         params: {
        //             day: day,
        //             startTime: startTime,
        //             endTime: endTime,
        //             working: working,
        //         }
        //     }
        // )
        // }
        
    }
};