import axios, { Axios } from "axios";
var config = require("../../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl =
  "http://" + config.dev.backendHost + ":" + config.dev.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
  name: "viewemployees",

  created: function() {
    AXIOS.get("/employees/")
      .then(response => {
        this.employees = response.data;
      })
      .catch(e => {
        this.errorEmployees = e;
      });
  },
  data() {
    return {
      employees: [],
      employeeID: "",
      firstName: "",
      lastName: "",
      email: "",
      errorEmployees: "",
      response: [],
      filter: ""
    };
  },

  methods: {
    fireEmployee: function(employeeID) {
      AXIOS.delete("/employee/delete".concat(employeeID)).catch(e => {
        var error = e.response.data.message;
        console.log(error);
      });
    },

    filterToggle(event) {
      this.filter = event.target.value;
      console.log(this.filter);
      if (this.filter == "blank") {
        AXIOS.get("/employees/")
          .then(response => {
            this.employees = response.data;
          })
          .catch(e => {
            this.errorEmployees = e;
          });
      }
    }
  }
};
