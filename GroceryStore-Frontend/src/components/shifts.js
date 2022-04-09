import axios, { Axios } from "axios";
var config = require("../../config");

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
    name: "shifts",

    created: function() {
        // Initializing business hours from backend
        AXIOS.get("/businesshour/allEmployee")
            .then(response => {
                this.hours = response.data;
                for (hour in hours) {
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
            // Set Variables
            hours: [],
            employees: [],
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
        /**
         * createEmployeeHours
         * Sets the working days and working hours of an employee
         * @param {*} employeeId
         * @param {*} day
         * @param {*} working
         * @param {*} startTime
         * @param {*} endTime
         */
        createEmployeeHours: function(
            employeeId,
            day,
            working,
            startTime,
            endTime
        ) {
            AXIOS.post(
                    "/businesshour/employee", {}, {
                        params: {
                            employeeId: employeeId,
                            day: day,
                            working: working,
                            startTime,
                            startTime,
                            endTime: endTime
                        }
                    }
                )
                .then(response => {
                    this.hours.push(response.data);
                    this.employeeId = "";
                    this.day = "";
                    this.working = "";
                    this.startTime = "";
                    this.endTime = "";
                })
                .catch(e => {
                    var error =
                        "An error has occured in createEmployeeHours in file shifts.js line 73";
                });
        },
        /**
         * updateHour
         * Updates the working days and working hours of an employee
         * @param {*} id
         * @param {*} day
         * @param {*} startTime
         * @param {*} endTime
         * @param {*} working
         * @param {*} employeeId
         */
        updateHour: function(
            id,
            day,
            startTime,
            endTime,
            working,
            employeeId,
            oldStartTime,
            oldEndTime
        ) {
            if (startTime == undefined) {
                startTime = oldStartTime;
            }
            if (endTime == undefined) {
                endTime = oldEndTime;
            }
            AXIOS.patch(
                    "/businesshour/update/" + id, {}, {
                        params: {
                            day: day,
                            startTime: startTime,
                            endTime: endTime,
                            working: working,
                            employeeId: employeeId,
                            groceryStoreSystemName: ""
                        }
                    }
                )
                .then(response => {
                    AXIOS.get("/businesshour/allEmployee")
                        .then(response2 => {
                            this.hours = response2.data;
                            for (hour in hours) {
                                startTime[hour.id] = hour.startTime;
                                endTime[hour.id] = hour.endTime;
                            }
                        })
                        .catch(e => {
                            this.errorHours = e;
                        });
                })
                .catch(error => {
                    var errorMsg = error;
                    if (error.response) {
                        errorMsg = error.response.data;
                    }
                });
        },
        /**
         * getEmployees
         * Takes a first name and a last name and  returns the corresponding employees
         * @param {*} firstName
         * @param {*} lastName
         */
        getEmployees: function(firstName, lastName) {
            this.showHours = false;
            if (firstName == undefined) {
                firstName = "";
            }
            if (lastName == undefined) {
                lastName = "";
            }
            AXIOS.get("/getPersonsByFirstNameLastNameContainingIgnoreCase/", {
                    params: {
                        firstName: firstName,
                        lastName: lastName
                    }
                })
                .then(response => {
                    this.employees = response.data;
                })
                .catch(error => {
                    var errorMsg = error;
                    if (error.response) {
                        errorMsg = error.response.data;
                    }
                });
        },
        /**
         * viewEmployeeHourByEmail
         * Takes an email and returns the corresponding employee
         * @param {*} email
         */
        viewEmployeeHourByEmail: function(email) {
            AXIOS.get("/employee/email/" + email, {})
                .then(response => {
                    this.employeeId = response.data.id;
                    AXIOS.get("/businesshour/employee/" + response.data.id)
                        .then(response2 => {
                            this.showHours = true;
                            this.hours = response2.data;
                            for (hour in hours) {
                                startTime[hour.id] = hour.startTime;
                                endTime[hour.id] = hour.endTime;
                            }
                        })
                        .catch(error => {
                            var errorMsg = error;
                            if (error.response) {
                                errorMsg = error.response.data;
                            }
                        });
                })
                .catch(error => {
                    var errorMsg = error;
                    if (error.response) {
                        errorMsg = error.response.data;
                    }
                });
        },
        /**
         * getEmployeeHour
         * Take an ID and return the corresponding employee's working hours
         * @param {*} id
         */
        getEmployeeHour: function(id) {
            AXIOS.get("/businesshour/employee/" + id)
                .then(response => {
                    this.hours = response.data;
                    for (hour in hours) {
                        startTime[hour.id] = hour.startTime;
                        endTime[hour.id] = hour.endTime;
                    }
                })
                .catch(error => {
                    var errorMsg = error;
                    if (error.response) {
                        errorMsg = error.response.data;
                    }
                });
        },
        /**
         * getHourByDay
         * Get the working hours for a specific day
         * @param {*} event
         */
        getHourByDay: function(event) {
            AXIOS.get("/businesshour/day/" + event.target.value)
                .then(response => {
                    this.hours = response.data;
                    for (hour in hours) {
                        startTime[hour.id] = hour.startTime;
                        endTime[hour.id] = hour.endTime;
                    }
                })
                .catch(error => {
                    var errorMsg = error;
                    if (error.response) {
                        errorMsg = error.response.data;
                    }
                });
        },
        /**
         * getHourByWorking
         * Get the working hours of all working employees
         * @param {*} event
         */
        getHourByWorking: function(event) {
            AXIOS.get("/businesshour/working/" + event.target.value)
                .then(response => {
                    this.hours = response.data;
                    for (hour in hours) {
                        startTime[hour.id] = hour.startTime;
                        endTime[hour.id] = hour.endTime;
                    }
                })
                .catch(error => {
                    var errorMsg = error;
                    if (error.response) {
                        errorMsg = error.response.data;
                    }
                });
        },
        /**
         * filterToggle
         * Resets the pages data when the filter button is used
         * @param {*} event
         */
        filterToggle(event) {
            this.filter = event.target.value;
            if (this.filter == "blank") {
                AXIOS.get("/businesshour/allEmployee")
                    .then(response => {
                        this.hours = response.data;
                        for (hour in hours) {
                            startTime[hour.id] = hour.startTime;
                            endTime[hour.id] = hour.endTime;
                        }
                    })
                    .catch(e => {
                        this.errorHours = e;
                    });
            }
            if (this.filter == "Name") {
                this.showHours = false;
            } else {
                this.showHours = true;
            }
        }
    }
};