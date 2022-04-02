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
      customers: [],
      customerID: "",
      firstName: "",
      lastName: "",
      email: "",
      errorCustomers: "",
      response: [],
      filter: ""
    };
  },

  methods: {
    getCustomerByID(customerID) {
      AXIOS.get("/customer/".concat(customerID))
        .then(response => {
          this.customers = response.data;
        })
        .catch(e => {
          this.errorCustomers = e;
        });
    },
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
    getCustomerByEmail(email) {
      AXIOS.get("/customer/email/".concat(email))
        .then(response => {
          this.customers = response.data;
        })
        .catch(e => {
          this.errorCustomers = e;
        });
    },
    banCustomer: function(customerID, customerTier, customerBan, email) {
      if (customerBan) {
        AXIOS.patch(
          "/customer/update/".concat(customerID),
          {},
          {
            params: {
              tierClass: customerTier,
              ban: false,
              personEmail: email
            }
          }
        )
          .then(response => {
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
      } else {
        AXIOS.patch(
          "/customer/update/".concat(customerID),
          {},
          {
            params: {
              tierClass: customerTier,
              ban: true,
              personEmail: email
            }
          }
        )
          .then(response => {
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
      }
    },

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
