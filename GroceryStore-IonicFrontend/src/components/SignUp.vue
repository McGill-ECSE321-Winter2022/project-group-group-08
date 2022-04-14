
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

</script>

<template>
  <div
    class="d-flex align-items-center justify-content-center"
    style="height: 500px"
    id="Signup"
  >
    <table>
      <tr>
        <h2>Sign Up</h2>
      </tr>
      <tr>
        <!-- Input Email Box -->
        <td class="table-text">Email:</td>
        <input
          style="margin-top: 6px"
          type="text"
          v-model="email"
          placeholder="Email"
        />
      </tr>
      <tr>
        <!-- Display Profile Pic -->
        <td class="table-text">Profile pic:</td>
        <input
          style="margin-top: 6px"
          type="text"
          v-model="image"
          placeholder="Link"
        />
      </tr>
      <tr>
        <!-- Input Phone Number Box -->
        <td class="table-text">Phone Number:</td>
        <input
          style="margin-top: 6px"
          type="text"
          v-model="phoneNumber"
          placeholder="Phone number"
        />
      </tr>
      <tr>
        <!-- Input First Name Box -->
        <td class="table-text">First Name:</td>
        <input
          style="margin-top: 6px"
          type="text"
          v-model="firstName"
          placeholder="First name"
        />
      </tr>
      <tr>
        <!-- Input Last Name Box -->
        <td class="table-text">Last Name:</td>
        <input
          style="margin-top: 6px"
          type="text"
          v-model="lastName"
          placeholder="Last name"
        />
      </tr>
      <tr>
        <!-- Input Address Box -->
        <td class="table-text">Address:</td>
        <input
          style="margin-top: 6px"
          type="text"
          v-model="address"
          placeholder="Address"
        />
      </tr>
      <tr>
        <!-- Input Username Box -->
        <td class="table-text">Username:</td>
        <input
          style="margin-top: 6px"
          type="text"
          v-model="username"
          placeholder="Username"
        />
      </tr>
      <tr>
        <!-- Input Password Box -->
        <td class="table-text">Password:</td>
        <input
          style="margin-top: 6px"
          type="password"
          v-model="password"
          placeholder="Password"
        />
      </tr>

      <tr>
        <!-- InTown CheckBox -->
        <td class="table-text">Do you live in town?</td>
        <label>Yes </label>
        <input type="checkbox" id="inTown" value="true" />
        <label>No </label>
        <input type="checkbox" id="notInTown" value="false" />
      </tr>
      <tr>
        <!-- Role Selection -->
        <td class="table-text">Choose your account role</td>
        <select name="userRole" id="userRole">
          <option value="blank"></option>
          <option value="Employee">Employee</option>
          <option value="Customer">Customer</option>
        </select>
      </tr>
      <tr>
        <td>
          <!-- SignUp Button -->
          <button
            style="margin-top: 15px"
            class="btn btn-light"
            @click="
              signUp(
                image,
                email,
                phoneNumber,
                firstName,
                lastName,
                address,
                username,
                password,
                inTown,
                userRole
              )
            "
          >
            Sign Up
          </button>
        </td>
      </tr>
    </table>

    <p>
      <span v-if="errorSignup" style="color: red"
        >Error: {{ errorSignup }}</span
      >
    </p>
  </div>
</template>
