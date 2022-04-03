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
        signUp: function(
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
                            this.errorSignup = e.response.data;
                        });
                    var userRole = document.getElementById("userRole").selectedOptions[0]
                        .value;
                    if (userRole === "Employee") {
                        AXIOS.post(
                                "/employee", {}, {
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
                                this.errorSignup = e.response.data;
                            });
                    } else if (userRole === "Customer") {
                        AXIOS.post(
                                "/customer", {}, {
                                    params: {
                                        tierClass: "Bronze",
                                        ban: false,
                                        personEmail: email
                                    }
                                }
                            )
                            .then(response => {
                                console.log("here");
                                this.accounts.push(response.data);
                                this.email = "";
                                this.userRole = "";
                                this.$router.push({
                                    path: `/Profile/${this.account.username}`
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