import axios from "axios";
var config = require("../../config");

// var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
// var backendUrl =
//     "http://" + config.dev.backendHost + ":" + config.dev.backendPort;
var frontendUrl;
var backendUrl;
if (process.env.NODE_ENV === "production") {
    console.log("prod env");
    frontendUrl = "https://" + config.build.host + ":" + config.build.port;
    backendUrl =
        "https://" + config.build.backendHost + ":" + config.build.backendPort;
} else if (process.env.NODE_ENV === "development") {
    console.log("dev env");
    frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
    backendUrl =
        "http://" + config.dev.backendHost + ":" + config.dev.backendPort;
}

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
    name: "Login",

    created: function() {
        // Initialize Accounts from Backend
        AXIOS.get("/getAllAccounts")
            .then(response => {
                this.Accounts = response.data;
            })
            .catch(e => {
                this.errorLogin = e;
            });
    },

    data() {
        return {
            // Set Variables
            account: {},
            accounts: [],
            username: "",
            password: "",
            errorLogin: "",
            response: []
        };
    },
    methods: {
        /**
         * signUp
         * Redirect to signup page
         */
        signUp: function() {
            this.$router.push({ path: `/SignUp` });
        },
        /**
         * login
         * Setup data for user view
         * @param {*} username
         * @param {*} password
         */
        login: function(username, password) {
            AXIOS.get("/loginAccount/", {
                    params: {
                        username: username,
                        password: password
                    }
                })
                .then(response => {
                    this.accounts.push(response.data);
                    this.username = "";
                    this.password = "";
                    this.errorLogin = "";
                    this.account = this.accounts[this.accounts.length - 1];
                    sessionStorage.setItem("username", username);
                    sessionStorage.setItem("validUser", true);

                    AXIOS.get("/cart/getWithUsername/", {
                            params: {
                                username: sessionStorage.getItem("username")
                            }
                        })
                        .then(response2 => {
                            console.log(response2.data);
                            sessionStorage.setItem("cartId", response2.data.id);
                            AXIOS.get("/getRoleByPerson/", {
                                    params: {
                                        personEmail: response2.data.account.person.email
                                    }
                                })
                                .then(response => {
                                    var id = response.data.id;
                                    AXIOS.get("/manager/" + id, {}, {})
                                        .then(response => {
                                            this.userRole = "Manager";
                                            sessionStorage.setItem("role", "manager");
                                            location.reload();
                                        })
                                        .catch(e => {
                                            this.error = e;
                                        });
                                    AXIOS.get("/customer/" + id, {}, {})
                                        .then(response => {
                                            this.userRole = "Customer";
                                            sessionStorage.setItem("role", "customer");
                                            location.reload();
                                        })
                                        .catch(e => {
                                            this.error = e;
                                        });
                                    AXIOS.get("/employee/" + id, {}, {})
                                        .then(response => {
                                            this.userRole = "Employee";
                                            sessionStorage.setItem("role", "employee");
                                            sessionStorage.setItem("employeeId", response.data.id);
                                            location.reload();
                                        })
                                        .catch(e => {
                                            this.error = e;
                                        });
                                    this.$router.push({
                                        path: `/Profile/${this.account.username}`
                                    });
                                })
                                .catch(e => {
                                    this.error = e;
                                });
                        })
                        .catch(e => {
                            this.errorLogin = e.response.data;
                        });
                })
                .catch(e => {
                    this.errorLogin = e.response.data;
                });
        }
    }
};