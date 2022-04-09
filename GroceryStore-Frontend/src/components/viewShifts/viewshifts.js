import axios from "axios";
import BusinessHour from "@/components/viewShifts/businessHour.vue";
var config = require("../../../config");

// var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
// var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
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
    name: "ViewShifts",

    created() {
        // Get current employee
        AXIOS.get(
                "/businesshour/employee/" + sessionStorage.getItem("employeeId"), {}
            )
            .then(response => {
                this.businesshours = response.data;
                if (this.businesshours.length > 0) {
                    this.employee = this.businesshours[0].employee;
                }
            })
            .catch(e => {});
    },

    data() {
        return {
            // Set Variables
            employee: {},
            businesshours: []
        };
    },
    components: {
        BusinessHour
    },

    computed: {
        /**
         * workingShifts
         * Returns employees work shifts
         * @returns working hours
         */
        workingShifts: function() {
            const hours = this.businesshours.filter(hour => hour.working);
            return hours;
        }
    }
};