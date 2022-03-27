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
    name: "Login",

    created: function() {
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
            account: {},
            accounts: [],
            accountUsername: "",
            accountPassword: "",
            errorLogin: "",
            response: []
        };
    },
    methods: {
        signUp: function() {
            this.$router.push({ path: `/SignUp` });
        },
        login: function(accountUsername, accountPassword) {
            AXIOS.get("/loginAccount/")
                .then(response => {
                    this.accounts.push(response.data);
                    this.errorLogin = "";
                    this.account = this.accounts[this.accounts.length - 1];
                    this.$router.push({ path: `/MainPage` });
                })
                .catch(e => {
                    var errorMsg = "Invalid username or password";
                    console.log(e);
                    this.errorLogin = errorMsg;
                });
        }
    }
};