import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});
    
export default{
    name:'ViewShifts',

    created: function () {
        AXIOS.get("/employees")
        .then(response => {
            this.employees=response.date
        })
        .catch(e=> {
            this.errorEmployee=e;
        });
    },

    data(){
        return{
            employees: [],
            businesshours: [],
            id: '',
            response:[]
        }; 
    },
    
    methods: {
        viewShifts: function (id,person){
        AXIOS.post(
        "/businesshour/"+id,{},{
            params:{
                id:id,
            }
        }
        )
        .then(response => {
            this.id="";
        })
        .catch(e => {
            var errorMsg = "No businesshour with such id exist";
            console.log(errorMsg);
            this.errorEmployee=this.errorMsg;
        });
    }
}
}