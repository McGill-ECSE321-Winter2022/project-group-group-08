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
    name: "SignUp",

    created: function() {
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
            persons: [],
            accounts: [],
            email: "",
            firstName: "",
            lastName: "",
            address: "",
            phoneNumber: "",
            username: "",
            password: "",
            errorSignup: "",
            response: []
        };
    },

    methods: {
        signUp: function(
            email,
            firstName,
            lastName,
            phoneNumber,
            address,
            username,
            password
        ) {
            AXIOS.post(
                    "/createPerson/" + email, {}, {
                        params: {
                            firstName: firstName,
                            lastName: lastName,
                            phoneNumber: phoneNumber,
                            address: address
                        }
                    }
                )
                .then(response => {
                    this.persons.push(response.data);
                    this.email = "";
                    this.firstName = "";
                    this.lastName = "";
                    this.phoneNumber = "";
                    this.address = "";
                    var newPerson = this.persons[this.persons.length - 1];
                    this.$router.push({ path: `/MainPage/` });
                })
                .catch(e => {
                    var errorMsg =
                        "-Your first name and last name cannot contain numbers or special characters";
                    var errorMsg2 =
                        "-Your password must contain at least 8 characters and a capital letter";
                    var errorMsg3 =
                        "-Otherwise, an account with this email already exists";
                    console.log(errorMsg);
                    this.errorSignupCustomer = errorMsg;
                    this.errorSignupCustomer2 = errorMsg2;
                    this.errorSignupCustomer3 = errorMsg3;
                });
            //   AXIOS.post("/createAccount/" + username)
            //     .then(response => {
            //       this.accounts.push(response.data);
            //       this.username = "";
            //       this.password = "";
            //       var newAccount = this.accounts[this.accounts.length - 1];
            //       this.$router.push({
            //         path: "/MainPage"
            //       });
            //     })
            //     .catch(e => {
            //       var errorMsg = "Invalid username or password";
            //       console.log(e);
            //       this.errorLogin = errorMsg;
            //     });
        }
    }
};