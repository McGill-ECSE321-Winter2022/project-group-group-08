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
    // Initializing employees from backend
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
      // Set variables
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
    /**
     * getEmployeeByID
     * Takes an ID and returns the corresponding employee
     * @param {*} employeeID 
     */
    getEmployeeByID(employeeID) {
      AXIOS.get("/employee/".concat(employeeID))
        .then(response => {
          this.employees = response.data;
        })
        .catch(e => {
          this.errorEmployees = e;
        });
    },
    /**
     * getEmployeeByName
     * Takes a first name and a last name and  returns the corresponding employee
     * @param {*} firstName 
     * @param {*} lastName 
     */
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
        })
        .catch(error => {
          var errorMsg = error;
          if (error.response) {
            errorMsg = error.response.data;
          }
        });
    },
    /**
     * getEmployeeByEmail
     * Takes an email and returns the corresponding employee
     * @param {*} email 
     */
    getEmployeeByEmail(email) {
      AXIOS.get("/employee/email/".concat(email))
        .then(response => {
          this.employees = response.data;
        })
        .catch(e => {
          this.errorEmployees = e;
        });
    },
    /**
     * fireEmployee
     * Takes an ID and removes the corresponding employee from the database
     * @param {*} employeeID 
     */
    fireEmployee: function(employeeID) {
      AXIOS.delete("/employee/delete/".concat(employeeID)).catch(e => {
        var error = e.response.data.message;
      }).then(response => {
        AXIOS.get("/employees/")
        .then(response2 => {
          this.employees = response2.data;
        })
        .catch(e => {
          this.errorEmployees = e;
        });
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
