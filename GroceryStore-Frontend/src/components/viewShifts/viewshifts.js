import axios from 'axios';
import BusinessHour from '@/components/viewShifts/businessHour.vue'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});
    
export default{
    name:'ViewShifts',

    created() {
        AXIOS.get("/businesshour/employeeId", {
            params: {
                id: sessionStorage.getItem("employeeId")
            }
        })
        .then(response => {
            this.businesshours = response.data;
            if (this.businesshours.length > 0) {
                this.employee = this.businesshours[0].employee;
            }
        })
        .catch(e=> {
            console.log(e);
        });
    },
    
    data() {
        return{
            employee: {},
            businesshours: []
        }; 
    },
    components: {
        BusinessHour,
    },
    
    computed: {
        workingShifts: function() {
            const hours = this.businesshours.filter(hour => hour.working)
            console.log(hours);
            return hours;
        }
    }
}
