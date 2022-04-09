import axios from 'axios';
import BusinessHour from '@/components/viewShifts/businessHour.vue'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = "" + config.build.backendHost;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});
    
export default{
    name:'ViewShifts',

    created() {
        // Get current employee
        AXIOS.get("/businesshour/employee/"+sessionStorage.getItem("employeeId"), {
        })
        .then(response => {
            this.businesshours = response.data;
            if (this.businesshours.length > 0) {
                this.employee = this.businesshours[0].employee;
            }
        })
        .catch(e=> {
        });
    },
    
    data() {
        return{
            // Set Variables
            employee: {},
            businesshours: []
        }; 
    },
    components: {
        BusinessHour,
    },
    
    computed: {
        /**
         * workingShifts
         * Returns employees work shifts
         * @returns working hours
         */
        workingShifts: function() {
            const hours = this.businesshours.filter(hour => hour.working)
            return hours;
        }
    }
}
