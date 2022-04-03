import axios, { Axios } from "axios";
var config = require("../../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl = "http://" + config.dev.backendHost + ":" + config.dev.backendPort;

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
    name: "shifts",

    created: function() {
        AXIOS.get("/businesshour/allEmployee")
            .then(response => {
                this.hours = response.data;
                for (hour in hours){
                    startTime[hour.id] = hour.startTime;
                    endTime[hour.id] = hour.endTime;
                }
            })
            .catch(e => {
                this.errorHours = e;
            });
    },
    data() {
        return {
            hours: [],
            employees:[],
            id: "",
            day: "",
            startTime: {},
            endTime: {},
            working: "",
            employeeId: "",
            groceryStoreSystemName: "",
            errorHours: "",
            response: [],
            filter: "",
            showHours: true
        };
    },

    methods: {
        createEmployeeHours: function(employeeId, day, working, startTime, endTime){
            AXIOS.post(
                "/businesshour/employee", {}, {
                    params:{
                        employeeId: employeeId,
                        day: day,
                        working: working,
                        startTime, startTime,
                        endTime: endTime
                    }
                }
            ).then(response => {
                this.hours.push(response.data);
                this.employeeId = "";
                this.day = "";
                this.working = "";
                this.startTime = "";
                this.endTime = "";
            }).catch(e => {
                var error = "An error has occured in createEmployeeHours in file shifts.js line 73"
                console.log(error);
            });
        },
        updateHour: function(id, day, startTime, endTime, working, employeeId){
            AXIOS.patch("/businesshour/update/"+id, {},{ 
                    params: {
                        day: day,
                        startTime: startTime,
                        endTime: endTime,
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
        getEmployees: function(firstName, lastName){
            this.showHours = false;
            if(firstName == undefined){
                firstName = "";
            }
            if(lastName == undefined){
                lastName = "";
            }
            AXIOS.get("/getPersonsByFirstNameLastNameContainingIgnoreCase/", { 
                    params: {
                        firstName: firstName,
                        lastName: lastName
                    }
                }
            ).then(response => {
                this.employees = response.data;
            })
            .catch(error => {
                var errorMsg = error
                if ( error.response ) {
                    errorMsg = error.response.data
                }
            });
        },
        viewEmployeeHourByEmail: function(email){
            console.log("here");
            AXIOS.get("/employee/email/"+email, {}
            ).then(response => {
                console.log("here!");
                this.employeeId = response.data.id;
                AXIOS.get("/businesshour/employee/"+response.data.id
                ).then(response2 => {
                    this.showHours = true;
                    this.hours = response2.data;
                    for (hour in hours){
                        startTime[hour.id] = hour.startTime;
                        endTime[hour.id] = hour.endTime;
                    }
                })
                .catch(error => {
                    var errorMsg = error
                    if ( error.response ) {
                        errorMsg = error.response.data
                    }
                });
            })
            .catch(error => {
                var errorMsg = error
                if ( error.response ) {
                    errorMsg = error.response.data
                }
            });
        },
        getEmployeeHour: function(id){
            console.log("here2");
            AXIOS.get("/businesshour/employee/"+id
            ).then(response => {
                this.hours = response.data;
                for (hour in hours){
                    startTime[hour.id] = hour.startTime;
                    endTime[hour.id] = hour.endTime;
                }
            })
            .catch(error => {
                var errorMsg = error
                if ( error.response ) {
                    errorMsg = error.response.data
                }
            });
        },
        getHourByDay: function(event){
            AXIOS.get("/businesshour/day/"+event.target.value
            ).then(response => {
                this.hours = response.data;
                for (hour in hours){
                    startTime[hour.id] = hour.startTime;
                    endTime[hour.id] = hour.endTime;
                }
            })
            .catch(error => {
                var errorMsg = error
                if ( error.response ) {
                    errorMsg = error.response.data
                }
            });
        },
        getHourByWorking: function(event){
            AXIOS.get("/businesshour/working/"+event.target.value
            ).then(response => {
                this.hours = response.data;
                for (hour in hours){
                    startTime[hour.id] = hour.startTime;
                    endTime[hour.id] = hour.endTime;
                }
            })
            .catch(error => {
                var errorMsg = error
                if ( error.response ) {
                    errorMsg = error.response.data
                }
                console.log(errorMsg)
            });
        },
        filterToggle(event){
            this.filter = event.target.value;
            console.log(this.filter);
            if(this.filter == "blank"){
                AXIOS.get("/businesshour/allEmployee")
                .then(response => {
                    
                    this.hours = response.data;
                    for (hour in hours){
                        startTime[hour.id] = hour.startTime;
                        endTime[hour.id] = hour.endTime;
                    }
                })
                .catch(e => {
                    this.errorHours = e;
                });
            }
            if(this.filter == "Name"){
                this.showHours = false;
            }else{
                this.showHours = true;
            }
        }
    }
};