import axios, { Axios } from "axios";
var config = require("../../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl =
  "http://" + config.dev.backendHost + ":" + config.dev.backendPort;

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
        console.log(this.image);
        AXIOS.get("/getRoleByPerson/", {
          params: {
            personEmail: this.email
          }
        })
          .then(response => {
            var id = response.data.id;
            AXIOS.get("/manager/" + id, {}, {})
              .then(response => {
                this.userRole = "Manager";
                sessionStorage.setItem("role", "manager");
              })
              .catch(e => {
                this.error = e;
                console.log(e.response.data.message);
              });
            AXIOS.get("/customer/" + id, {}, {})
              .then(response => {
                this.userRole = "Customer";
                sessionStorage.setItem("role", "customer");
              })
              .catch(e => {
                this.error = e;
                console.log(e.response.data.message);
              });
            AXIOS.get("/employee/" + id, {}, {})
              .then(response => {
                this.userRole = "Employee";
                sessionStorage.setItem("role", "employee");
              })
              .catch(e => {
                this.error = e;
                console.log(e.response.data.message);
              });
          })
          .catch(e => {
            this.error = e;
            console.log(e.response.data.message);
          });
      })
      .catch(e => {
        this.error = e;
        console.log(e.response.data.message);
      });
  },

  methods: {
      /**
       * displayImage
       * Display Profile Pic
       */
    displayImage: function() {
      console.log("img: " + this.image);
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
          console.log(errorMsg);
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
          console.log(errorMsg);
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
          console.log(errorMsg);
          this.isError = true;
        });
    }
  }
};
