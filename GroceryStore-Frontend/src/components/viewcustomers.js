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
  name: "viewcustomers",

  created: function() {
    // Initializing employees from backend
    AXIOS.get("/customers/")
      .then(response => {
        this.customers = response.data;
      })
      .catch(e => {
        this.errorCustomers = e;
      });
  },
  data() {
    return {
      // Set Variables
      customers: [],
      customerID: "",
      firstName: "",
      lastName: "",
      email: "",
      errorCustomers: "",
      response: [],
      filter: "",
      newTier: ""
    };
  },

  methods: {
    /**
     * getCustomerByID
     * Takes an ID and returns the corresponding customer
     * @param {*} customerID
     */
    getCustomerByID(customerID) {
      AXIOS.get("/customer/".concat(customerID))
        .then(response => {
          this.customers = response.data;
        })
        .catch(e => {
          this.errorCustomers = e;
        });
    },
    /**
     * getCustomerByName
     * Takes a first name and last name and returns the corresponding customer
     * @param {*} firstName
     * @param {*} lastName
     */
    getCustomerByName(firstName, lastName) {
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
          this.customers = response.data;
          console.log(response.data);
        })
        .catch(error => {
          var errorMsg = error;
          if (error.response) {
            errorMsg = error.response.data;
          }
        });
    },
    /**
     * getCustomerByEmail
     * Takes an email and returns the corresponding customer
     * @param {*} email
     */
    getCustomerByEmail(email) {
      AXIOS.get("/customer/email/".concat(email))
        .then(response => {
          this.customers = response.data;
        })
        .catch(e => {
          this.errorCustomers = e;
        });
    },
    /**
     * banCustomer
     * Sets the ban variables of a customer as true
     * @param {*} customerID
     * @param {*} customerTier
     * @param {*} customerBan
     * @param {*} email
     */
    banCustomer: function(customerID, customerTier, customerBan, email) {
      AXIOS.patch(
        "/customer/update/".concat(customerID),
        {},
        {
          params: {
            tierClass: customerTier,
            ban: customerBan,
            personEmail: email
          }
        }
      )
        .then(response => {
          console.log("here");
          AXIOS.get("/customers/")
            .then(response2 => {
              this.customers = response2.data;
            })
            .catch(e2 => {
              this.errorCustomers = e2;
            });
        })
        .catch(e => {
          var error = e.response.data.message;
          console.log(error);
        });
    },
    /**
     * filterToggle
     * Resets the pages data when the filter button is used
     * @param {*} event
     */
    filterToggle(event) {
      this.filter = event.target.value;
      console.log(this.filter);
      if (this.filter == "blank") {
        AXIOS.get("/customers/")
          .then(response => {
            this.customers = response.data;
          })
          .catch(e => {
            this.errorCustomers = e;
          });
      }
    }
  }
};
