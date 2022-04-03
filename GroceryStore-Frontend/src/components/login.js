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
            .then(response => {
              console.log(response.data);
              sessionStorage.setItem("cartId", response.data.id);
              this.$router.push({ path: `/Profile/${this.account.username}` });
              location.reload();
            })
            .catch(e => {
              this.errorLogin = e.response.data;
            });
        })
        .catch(e => {
          this.errorLogin = e.response.data;
        });
    },

    data() {
        return {
            account: {},
            accounts: [],
            username: "",
            password: "",
            errorLogin: "",
            response: []
        };
    },
    methods: {
        signUp: function() {
            this.$router.push({ path: `/SignUp` });
        },

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
                            username: sessionStorage.getItem("username"),
                        }
                    })
                    .then(response => {
                        sessionStorage.setItem("cartId", response.data.id);
                        this.$router.push({ path: `/Profile/${this.account.username}` });
                        location.reload();
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
  }
};
