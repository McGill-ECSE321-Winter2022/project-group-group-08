import axios from "axios";
var config = require("../../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl = "" + config.build.backendHost;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
  name: "storeManagement",

  created: function() {
    // Initializing opening hours from backend
    AXIOS.get("/getOpeningHours/Whole Foods")
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
    // Initializing store info from backend
    AXIOS.get("/grocerystoresystem/Whole Foods")
      .then(response => {
        this.storename = response.data.storeName;
        this.address = response.data.address;
        this.employeeDiscount = response.data.employeeDiscount;
      })
      .catch(e => {
        this.errorHours = e;
      });
  },

  data() {
    return {
      // Set Variables
      customers: [],
      hours: [],
      id: "",
      day: "",
      startTime: {},
      endTime: {},
      working: "",
      employeeId: "",
      storename: "",
      address: "",
      employeeDiscount: "",
      errorHours: "",
      response: []
    };
  },

  methods: {
      /**
       * createStoreHours
       * Set the working days and working hours of the store
       * @param {*} storename 
       * @param {*} day 
       * @param {*} working 
       * @param {*} startTime 
       * @param {*} endTime 
       */
    createStoreHours: function(storename, day, working, startTime, endTime) {
      AXIOS.post(
        "/businesshour/groceryStoreSystem",
        {},
        {
          params: {
            day: day,
            startTime: startTime,
            endTime: endTime,
            working: working,
            storename: storename
          }
        }
      )
        .then(response => {
          this.hours.push(response.data);
          this.day = "";
          this.startTime = "";
          this.endTime = "";
          this.working = "";
          this.storename = "";
        })
        .catch(e => {
        });
    },
    /**
     * updateStoreHours
     * Update the working days and working hours of the store
     * @param {*} id 
     * @param {*} day 
     * @param {*} startTime 
     * @param {*} endTime 
     * @param {*} working 
     * @param {*} storename 
     * @param {*} employeeId 
     */
    updateStoreHours: function(
      id,
      day,
      startTime,
      endTime,
      working,
      oldStartTime,
      oldEndTime
    ) {
      if(startTime == undefined){
        startTime = oldStartTime;
      }
      if(endTime == undefined){
        endTime = oldEndTime;
      }
      AXIOS.patch(
        "/businesshour/update/" + id,
        {},
        {
          params: {
            day: day,
            startTime: startTime,
            endTime: endTime,
            working: working,
            groceryStoreSystemName: "Whole Foods",
            employeeId: -1
          }
        }
      )
        .then(response => {
          AXIOS.get("/getOpeningHours/Whole Foods")
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
        })
        .catch(error => {
          var errorMsg = error;
          if (error.response) {
            errorMsg = error.response.data;
          }
        });
    },
    /**
     * updateEmployeeDiscount
     * Update the employees discount
     * @param {*} storename 
     * @param {*} address 
     * @param {*} employeeDiscount 
     */
    updateEmployeeDiscount: function(storename, address, employeeDiscount) {
      AXIOS.patch(
        "/grocerystoresystem/update/" + storename,
        {},
        {
          params: {
            storename: storename,
            address: address,
            employeeDiscount: employeeDiscount
          }
        }
      )
        .then(response => {
          this.employeeDiscount = response.data.employeeDiscount;
          this.storeName = response.data.storeName;
          this.address = response.data.address;
        })
        .catch(e => {
          var errorMsg = e.response.data.message;
        });
    }
  }
};
