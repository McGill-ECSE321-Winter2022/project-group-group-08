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
    getEmployeeByID(employeeID) {
      AXIOS.get("/employee/".concat(employeeID))
        .then(response => {
          this.employees = response.data;
        })
        .catch(e => {
          this.errorEmployees = e;
        });
    },
    getEmployeeByName(firstName, lastName) {
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
          console.log(response.data);
        })
        .catch(error => {
          var errorMsg = error;
          if (error.response) {
            errorMsg = error.response.data;
          }
        });
    },
    getEmployeeByEmail(email) {
      AXIOS.get("/employee/email/".concat(email))
        .then(response => {
          this.employees = response.data;
        })
        .catch(e => {
          this.errorEmployees = e;
        });
    },
    fireEmployee: function(employeeID) {
      AXIOS.delete("/employee/delete/".concat(employeeID)).catch(e => {
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
