
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
            })
            .catch(e => {
              this.errorSignup = e.response.data;
            });
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
    style="height: 80%"
    id="Signup"
    
  >
  <br> 
    <table style="margin-top: 50px;">
      <tr>
        <h2>Sign Up</h2>
      </tr>
      <br>
      <tr>
        <!-- Input Email Box -->
        <input
          style="margin-top: 6px; font-size: 30px;max-width: 70%;"
          type="text"
          v-model="email"
          placeholder="Email"
        />
      </tr>
      <tr>
        <!-- Display Profile Pic -->
        <input
          style="margin-top: 6px; font-size: 30px;max-width: 70%;"
          type="text"
          v-model="image"
          placeholder="Profile Picture Link"
        />
      </tr>
      <tr>
        <!-- Input Phone Number Box -->
        <input
          style="margin-top: 6px; font-size: 30px;max-width: 70%;"
          type="text"
          v-model="phoneNumber"
          placeholder="Phone number"
        />
      </tr>
      <tr>
        <!-- Input First Name Box -->
        <input
          style="margin-top: 6px; font-size: 30px;max-width: 70%;"
          type="text"
          v-model="firstName"
          placeholder="First name"
        />
      </tr>
      <tr>
        <!-- Input Last Name Box -->
        <input
          style="margin-top: 6px; font-size: 30px;max-width: 70%;"
          type="text"
          v-model="lastName"
          placeholder="Last name"
        />
      </tr>
      <tr>
        <!-- Input Address Box -->
        <input
          style="margin-top: 6px; font-size: 30px;max-width: 70%;"
          type="text"
          v-model="address"
          placeholder="Address"
        />
      </tr>
      <tr>
        <!-- Input Username Box -->
        <input
          style="margin-top: 6px; font-size: 30px;max-width: 70%;"
          type="text"
          v-model="username"
          placeholder="Username"
        />
      </tr>
      <tr>
        <!-- Input Password Box -->
        <input
          style="margin-top: 6px; font-size: 30px;max-width: 70%;"
          type="password"
          v-model="password"
          placeholder="Password"
        />
      </tr>

      <tr>
        <!-- InTown CheckBox -->
        <br> <br>
        <label class="table-text" style="font-size: 28px;">Do you live in town?</label>
        <br><label style="font-size: 22px; margin-right: 10px;">Yes </label>
        <input type="checkbox" id="inTown" value="true" style="padding: 10px;"/><br>
        <label style="font-size: 22px; margin-right: 10px;">No </label>
        <input type="checkbox" id="notInTown" value="false" style="padding: 10px;"/>
      </tr>
      <tr>
        <td>
          <!-- SignUp Button -->
          <button
            style="margin-top: 15px;width: 200px; font-weight: 500; font-size: 26px; padding: 10px;background-color: #c8e6cc;"
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
    <br><br>
    <p>
      <span v-if="errorSignup" style="color: red"
        >Error: {{ errorSignup }}</span
      >
    </p>
    <br>
  </div>
</template>

