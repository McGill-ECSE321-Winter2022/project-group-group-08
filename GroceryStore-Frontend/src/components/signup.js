import axios from "axios";
var config = require("../../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl = "" + config.build.backendHost;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
  name: "SignUp",

  created: function() {
    // Initializing accounts from backend
    AXIOS.get("/getAllAccounts")
      .then(response => {
        this.accounts = response.data;
      })
      .catch(e => {
        this.errorSignup = e;
      });
  },
  data() {
    return {
      // Set Variables
      userRoles: [],
      persons: [],
      accounts: [],
      email: "",
      firstName: "",
      lastName: "",
      address: "",
      phoneNumber: "",
      username: "",
      password: "",
      inTown: "",
      errorSignup: "",
      response: []
    };
  },

  methods: {
      /**
       * signUp
       * Create account and person
       * @param {*} image 
       * @param {*} email 
       * @param {*} phoneNumber 
       * @param {*} firstName 
       * @param {*} lastName 
       * @param {*} address 
       * @param {*} username 
       * @param {*} password 
       * @param {*} inTown 
       */
    signUp: function(
      image,
      email,
      phoneNumber,
      firstName,
      lastName,
      address,
      username,
      password,
      inTown
    ) {
      AXIOS.post(
        "/createPerson/" + email,
        {},
        {
          params: {
            image: image,
            firstName: firstName,
            lastName: lastName,
            phoneNumber: phoneNumber,
            address: address
          }
        }
      )
        .then(response => {
          this.persons.push(response.data);
          this.image = "";
          this.firstName = "";
          this.lastName = "";
          this.phoneNumber = "";
          this.address = "";
          var yes = document.getElementById("inTown");
          var no = document.getElementById("notInTown");
          if (yes.checked == true) {
            inTown = true;
          } else if (no.checked == true) {
            inTown = false;
          }
          AXIOS.post(
            "/createAccount/" + username,
            {},
            {
              params: {
                password: password,
                inTown: inTown,
                totalPoints: 0,
                personEmail: email
              }
            }
          )
            .then(response => {
              this.accounts.push(response.data);
              AXIOS.post(
                "/cart/",
                {},
                {
                  params: {
                    date: "2022-04-07",
                    accountUsername: this.username
                  }
                }
              )
                .then(response => {
                  sessionStorage.setItem("cartId", response.data.id);
                })
                .catch(e => {
                  this.errorSignup = e.response.data;
                });
            })
            .catch(e => {
              this.errorSignup = e.response.data;
            });
          var userRole = document.getElementById("userRole").selectedOptions[0]
            .value;
          if (userRole === "Manager") {
            AXIOS.post(
              "/manager",
              {},
              {
                params: {
                  personEmail: email
                }
              }
            )
              .then(response => {
                this.accounts.push(response.data);
                this.email = "";
                this.userRole = "";
                this.$router.push({
                  path: `/Login`
                });
              })
              .catch(e => {
                this.errorSignup = e;
              });
          } else if (userRole === "Employee") {
            AXIOS.post(
              "/employee",
              {},
              {
                params: {
                  personEmail: email
                }
              }
            )
              .then(response => {
                this.accounts.push(response.data);
                this.email = "";
                this.userRole = "";
                this.$router.push({
                  path: `/Login`
                });
              })
              .catch(e => {
                this.errorSignup = e.response.data;
              });
          } else if (userRole === "Customer") {
            AXIOS.post(
              "/customer",
              {},
              {
                params: {
                  tierClass: "Bronze",
                  ban: false,
                  personEmail: email
                }
              }
            )
              .then(response => {
                this.accounts.push(response.data);
                this.email = "";
                this.userRole = "";
                this.$router.push({
                  path: `/Login`
                });
              })
              .catch(e => {
                this.errorSignup = e.response.data;
              });
          }
        })
        .catch(e => {
          this.errorSignup = e.response.data;
        });
    }
  }
};
