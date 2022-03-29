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
        createPerson: function(email, firstName, lastName, phoneNumber, address) {
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
                    this.firstName = "";
                    this.lastName = "";
                    this.phoneNumber = "";
                    this.address = "";
                    var newPerson = this.persons[this.persons.length - 1];
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
        },

        signUp: function(email, username, password, inTown) {
            var yes = document.getElementById("inTown");
            var no = document.getElementById("notInTown");
            if (yes.checked == true) {
                inTown = true;
            } else if (no.checked == true) {
                inTown = false;
            }
            AXIOS.post(
                    "/createAccount/" + username, {}, {
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
                    this.username = "";
                    this.password = "";
                    this.inTown = "";
                    this.account = this.accounts[this.accounts.length - 1];
                })
                .catch(e => {
                    var errorMsg = "Invalid username or password";
                    console.log(e);
                    this.errorSignup = errorMsg;
                });
            var userRole = document.getElementById("userRole").selectedOptions[0]
                .value;
            if (userRole === "Manager") {
                AXIOS.post(
                        "/manager", {}, {
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
                            path: `/Profile/${this.account.username}`
                        });
                    })
                    .catch(e => {
                        var errorMsg = "Invalid username or password";
                        console.log(e);
                        this.errorSignup = errorMsg;
                    });
            } else if (userRole === "Employee") {
                console.log("Employee");
            } else if (userRole === "Customer") {
                console.log("Customer");
            }
        }
    }
};