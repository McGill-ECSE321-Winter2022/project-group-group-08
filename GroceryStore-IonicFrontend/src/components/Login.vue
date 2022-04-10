<script>
import axios from "axios";
var config = require("../../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl = "" + config.build.backendHost;

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
            response: [],
            redirectToWeb: false
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
                                            this.redirectToWeb = true;
                                        })
                                        .catch(e => {
                                            this.error = e;
                                        });
                                    AXIOS.get("/customer/" + id, {}, {})
                                        .then(response => {
                                          console.log("here")
                                            redirectToWeb = false;
                                            this.userRole = "Customer";
                                            sessionStorage.setItem("role", "customer");
                                            location.reload();
                                        })
                                        .catch(e => {
                                            this.error = e;
                                        });
                                    AXIOS.get("/employee/" + id, {}, {})
                                        .then(response => {
                                            this.redirectToWeb = true;
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
</script>
<template>
  <div id="Login">
    <div
      class="d-flex align-items-center justify-content-center"
      style="height: 500px;"
      id="LoginCustomer"
    >
      <table>
        <h2>Login Menu</h2>
        <p v-if="redirectToWeb">The app currently do not support manager or employee account. Please visit our <a href="https://grocerystore-frontend2-22ws.herokuapp.com/#/">website</a>.</p>
        <tr>
          <td>
            <!-- Username and Password Input -->
            <input
              style="margin-top: 8px;"
              type="text"
              v-model="username"
              placeholder="Username"
            />
          </td>
        </tr>
        <tr>
          <td>
            <input
              style="margin-top: 8px;"
              type="password"
              v-model="password"
              placeholder="Password"
            />
          </td>
        </tr>
        <tr>
          <td>
            <!-- Login Button --> 
            <button
              style="margin-top: 12px; font-size: 20px"
              class="btn btn-light"
              v-bind:disabled="!username || !password"
              @click="login(username, password)"
            >
              Login
            </button>
          </td>
        </tr>
        <tr>
          <td>
            <!-- Create new Account Redirect -->
            <button
              style="margin-top: 12px; font-size: 20px"
              class="btn btn-light"
              v-on:click="signUp()"
            >
              Create new Account
            </button>
          </td>
        </tr>
      </table>
    </div>
    <p>
      <span v-if="errorLogin" style="color:red">Error: {{ errorLogin }}</span>
    </p>
  </div>
</template>
<style></style>