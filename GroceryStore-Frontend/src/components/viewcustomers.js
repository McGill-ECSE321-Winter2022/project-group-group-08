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
      curCustomer: "",
      curCustomerFirstName: "",
      curCustomerLastName: "",
      curCustomerEmail: "",
      errorCustomers: "",
      response: [],
      filter: ""
    };
  },

  methods: {
    banCustomer: function(customerID) {
      AXIOS.patch("/customer/update/".concat(customerID), {
        params: {
            tierClass: tierClass,
            ban: ban,
            personEmail: personEmail
        }
      })
        .catch(e => {
          var error = e.response.data.message;
          console.log(error);
        });
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
