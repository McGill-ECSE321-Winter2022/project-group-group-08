<script>
import axios, { Axios } from "axios";
var config = require("../../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl = "" + config.build.backendHost;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
  props: ["usernameP"],
  data() {
    return {
      // Set Variables
      image: "",
      person: {},
      userRole: "",
      firstName: "",
      lastName: "",
      username: this.usernameP,
      email: "",
      password: "",
      address: "",
      phoneNumber: "",
      inTown: "",
      totalPoints: "",
      newFirstName: "",
      newLastName: "",
      newPhoneNumber: "",
      newPassword: "",
      newAddress: "",
      newInTown: "",
      error: "",
      isShow: false,
      isError: false,
      deleteError: false
    };
  },
  created: function() {
    // Initialize Account Info
    AXIOS.get("/getAccountByUsername/" + this.usernameP)
      .then(response => {
        this.image = response.data.person.image;
        this.person = response.data.person;
        this.firstName = response.data.person.firstName;
        this.lastName = response.data.person.lastName;
        this.email = response.data.person.email;
        this.password = response.data.password;
        this.address = response.data.person.address;
        this.phoneNumber = response.data.person.phoneNumber;
        this.inTown = response.data.inTown;
        this.totalPoints = response.data.totalPoints;
        this.userRole = sessionStorage.getItem("role").toUpperCase();
      })
      .catch(e => {
        this.error = e;
      });
  },

  methods: {
      /**
       * displayImage
       * Display Profile Pic
       */
    displayImage: function() {
      displayImage(this.image);
    },
    /**
     * deleteAccount
     * Delete Account from database 
     */
    deleteAccount: function() {
      AXIOS.delete("/deletePerson/" + this.email)
        .then(response => {
          this.$router.push({ path: `/` });
        })
        .catch(e => {
          var errorMsg = e.response.data.message;
          this.deleteError = true;
        });
    },
    /**
     * update
     * Update Account Info
     */
    update: function() {
      var password1 = this.newPassword;
      if (this.newPassword === "") {
        password1 = this.password;
      }
      var inTown1 = this.newInTown;
      if (this.newInTown === "") {
        inTown1 = this.inTown;
      }
      AXIOS.put(
        "/updateAccount/" + this.usernameP,
        {},
        {
          params: {
            password: password1,
            inTown: inTown1,
            totalPoints: this.totalPoints,
            personEmail: this.email
          }
        }
      )
        .then(response => {
          this.isError = false;
          this.inTown = response.data.inTown;
          this.password = response.data.password;
          this.newPassword = "";
          this.newInTown = "";
          this.isShow = false;
        })
        .catch(e => {
          var errorMsg = e.response.data.message;
          this.isError = true;
        });
      var image1 = this.image;
      var firstName1 = this.newFirstName;
      if (this.newFirstName === "") {
        firstName1 = this.firstName;
      }
      var lastName1 = this.newLastName;
      if (this.newLastName === "") {
        lastName1 = this.lastName;
      }
      var phoneNumber1 = this.newPhoneNumber;
      if (this.newPhoneNumber === "") {
        phoneNumber1 = this.phoneNumber;
      }
      var address1 = this.newAddress;
      if (this.newAddress === "") {
        address1 = this.address;
      }
      AXIOS.put(
        "/updatePerson/" + this.email,
        {},
        {
          params: {
            image: image1,
            firstName: firstName1,
            lastName: lastName1,
            phoneNumber: phoneNumber1,
            address: address1
          }
        }
      )
        .then(response => {
          this.isError = false;
          this.image = response.data.image;
          this.firstName = response.data.firstName;
          this.lastName = response.data.lastName;
          this.phoneNumber = response.data.phoneNumber;
          this.address = response.data.address;
          this.newFirstName = "";
          this.newLastName = "";
          this.newPhoneNumber = "";
          this.newAddress = "";
          this.image = "";
        })
        .catch(e => {
          var errorMsg = e.response.data.message;
          this.isError = true;
        });
    }
  }
};

</script>

<template>
  <div id="profile">
    <div id="header">
      <br>
      
      <h3>Hey, {{ firstName + " " + lastName }}</h3>
      <p>Account Type - {{ userRole }}</p>
      <hr />
    </div>
   
    <!-- Display Account Info -->
    <h3> Account info: </h3>
  <br>
    <div id="body" v-show="!isShow">
      <img :src="image" style="width: 200px; clip-path: circle();" />
      <br>
      <br>
      <h5><b>Username:</b> {{ username }}<b>   Email:</b> {{ email }}</h5>
       <br>
      <div id="phoneNumber">
        <h5><b>Phone Number:</b> {{ phoneNumber }}</h5>
      </div>
      <div id="address">
        <h5><b>Address:</b> {{ address }}</h5>
      </div>
      <div id="password">
        <h5><b>Password:</b> {{ password }}</h5>
      </div>
      <div id="inTown">
        <h5><b>In Town:</b> {{ inTown }}</h5>
      </div>
      <div id="totalPoints">
        <h5><b>Your Points:</b> {{ totalPoints }}</h5>
      </div>
      <button type="button" class="btn btn-light" v-on:click="isShow = !isShow">
      Update Profile
    </button>
    </div>
    <br>
 
    <!-- Display Update Account Inputs -->
    <div id="body" v-show="isShow">
      
      <h5>Username : {{ username }}</h5>
      <h5>Email : {{ email }}</h5>
      <br>
      <div id="firstName" class="Row">
        <h5 class="Column">First Name :</h5>
        <input
          class="Column"
          type="text"
          v-model="newFirstName"
          :placeholder="[[firstName]]"
        />
      </div>
      <div id="lastName" class="Row">
        <h5 class="Column">Last Name :</h5>
        <input
          class="Column"
          type="text"
          v-model="newLastName"
          :placeholder="[[lastName]]"
        />
      </div>
      <div id="imageLink" class="Row">
        <h5 class="Column">Image Link :</h5>
        <input class="Column" type="text" v-model="image" :placeholder="link" />
      </div>
      <div id="phoneNumber" class="Row">
        <h5 class="Column">Phone Number :</h5>
        <input
          class="Column"
          type="text"
          v-model="newPhoneNumber"
          :placeholder="[[phoneNumber]]"
        />
      </div>
      <div id="address" class="Row">
        <h5 class="Column">Address :</h5>
        <input
          class="Column"
          type="text"
          v-model="newAddress"
          :placeholder="[[address]]"
        />
      </div>
      <div id="password" class="Row">
        <h5 class="Column">Password :</h5>
        <input
          class="Column"
          type="password"
          v-model="newPassword"
          placeholder="New Password"
        />
      </div>
      <div id="inTown" class="Row">
        <h5 class="Column">In Town :</h5>
        <input
          class="Column"
          type="text"
          v-model="newInTown"
          :placeholder="[[inTown]]"
        />
      </div>
      <br>
      <div id="totalPoints">
        <h5>Your Points : {{ totalPoints }}</h5>
      </div>
      <br>

      <!-- Delete Account Button -->
      <button
        type="button"
        class="btn btn-light"
        v-on:click="deleteAccount()"
        id="deleteAccountButton"
      >
        Delete Profile
      </button>
      <button class="btn btn-light" v-on:click="update()">Update</button>
    </div>
    <div v-show="deleteError" id="error">
      <H5>Delete failed: outstanding Customer balance and/or reservation(s)</H5>
    </div>
    <div v-show="isError" id="error">
      <H5
        >Your password must have at least 8 characters and a capital letter</H5
      >
    </div>
  </div>
</template>
<style>
#update-account {
  margin-top: 10px;
}
#error {
  margin-top: 12px;
  color: rgb(184, 17, 17);
}
.center {
  margin-left: auto;
  margin-right: auto;
}
.Row {
  display: flex;
  justify-content: center;
  margin-top: 1%;
}
.Column {
  display: table-cell;
  width: 200px;
}
</style>
