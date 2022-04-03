import axios from "axios";
var config = require("../../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl =
  "http://" + config.dev.backendHost + ":" + config.dev.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
  name: "storeManagement",

  created: function() {
    // Initializing opening hours from backend
    AXIOS.get("/getOpeningHours/My Local Shop")
      .then(response => {
        console.log("here");
        this.hours = response.data;
        console.log(response.data);
        for (hour in hours) {
          startTime[hour.id] = hour.startTime;
          endTime[hour.id] = hour.endTime;
        }
      })
      .catch(e => {
        this.errorHours = e;
      });
    // Initializing store info from backend
    AXIOS.get("/grocerystoresystem/My Local Shop")
      .then(response => {
        console.log("here2");
        console.log(response.data);
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
          console.log(e);
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
      storename,
      employeeId
    ) {
      AXIOS.patch(
        "/businesshour/update/" + id,
        {},
        {
          params: {
            day: day,
            startTime: startTime,
            endTime: endTime,
            working: working,
            groceryStoreSystemName: "My Local Shop",
            employeeId: -1
          }
        }
      )
        .then(response => {
          console.log(response.data);
          AXIOS.get("/businesshour/store")
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
          console.log(error.response.data);
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
          console.log(errorMsg);
        });
    }
  }
};
