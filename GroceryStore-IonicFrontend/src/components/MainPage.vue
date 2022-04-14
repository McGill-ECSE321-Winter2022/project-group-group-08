
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
  name: "mainpage",
  created: function() {
    // Setup Grocery Store System
    AXIOS.post(
      "grocerystoresystem",
      {},
      {
        params: {
          storename: "Whole Foods",
          address: "123 McGill Avenue",
          employeeDiscount: "25"
        }
      }
    )
      .then(response => {

        AXIOS.get("/getOpeningHours/" + response.data.storeName, {}, {})
          .then(response => {
            this.openingsHours = response.data;
          })
          .catch(e => {});
      })
      .catch(e => {});
    // Create Person
    AXIOS.post(
      "/createPerson/" + "marwan.kanaan@mcgill.ca",
      {},
      {
        params: {
          firstName: "Marwan",
          lastName: "Kanaan",
          phoneNumber: "9999999999",
          address: "13 McConnell Engineering Building",
          image:
            "https://pbs.twimg.com/profile_images/483697632003371008/YDdxZqsm_400x400.jpeg"
        }
      }
    )
      .then(response => {
        AXIOS.post(
          "/createAccount/" + "BigBoss",
          {},
          {
            params: {
              password: "I am the best",
              inTown: true,
              totalPoints: 0,
              personEmail: "marwan.kanaan@mcgill.ca"
            }
          }
        )
          .then(response => {
            AXIOS.post(
              "/cart/",
              {},
              {
                params: {
                  date: "2022-04-07",
                  accountUsername: "BigBoss"
                }
              }
            )
              .then(response2 => {
                sessionStorage.setItem("cartId", response2.data.id);
              })
              .catch(e => {
                this.errorSignup = e.response.data;
              });
          })
          .catch(e => {});

        AXIOS.post(
          "/manager",
          {},
          {
            params: {
              personEmail: "marwan.kanaan@mcgill.ca"
            }
          }
        )
          .then(response => {})
          .catch(e => {});
      })
      .catch(e => {});
  },

  data() {
    return {
      // Set Variables
      msg: "Welcome!",
      email: "group8@mail.mcgill.ca",
      adress: "123 McGill Avenue",
      number: "514-100-1313",
      errorOpeningsHours: "",
      openingsHours: [],
      backTest: backendUrl,
      validUser: sessionStorage.getItem("validUser")
    };
  },
  methods: {
    /**
     * getAllOpeningsHours
     * Set Opening Hours of Store
     */
    getAllOpeningsHours: function() {
      AXIOS.get("/getAllOpeningsHours")
        .then(response => {
          // JSON responses are automatically parsed.
          this.openingsHours = response.data;
        })
        .catch(e => {
          this.errorOpeningsHours = e;
        });
    },
    /**
     * goLogin
     * Redirect to login page
     */
    goLogin: function() {
      this.$router.push({ path: `/Login` });
    },
    /**
     * goSignUp
     * Redirect to signup page
     */
    goSignUp: function() {
      this.$router.push({ path: `/Signup` });
    }
  }
};

</script>
<template>
  <div class="mainpage">
    <br>
    <div>
      <img src="./../assets/wholefoods.png" width="180px" />
    </div>
    <br>
    
<div class="wrapper ">
  <h1 style="margin-bottom: 25px;">{{ msg }}</h1>

        <!-- Login Redirect Button -->
        <button
          style="margin-bottom: 25px; font-size: 26px; padding: 10px; background-color: #c8e6cc;"
          class="btn btn-light"
          v-on:click="goLogin()"
        >
          Login
        </button>
     
      
        <!-- Sign Up Redirect Button -->
        <button
          style="margin-bottom: 25px; font-size: 26px; padding: 6px;background-color: #c8e6cc;"
          class="btn btn-light"
          v-on:click="goSignUp()"
        >
           Signup
    </button>
   
     <br>
    <!-- Display Opening Hours Data -->
    <h3 style="text-align: center">Opening Hours</h3>
    <br>
    <v-table :data="openingsHours" align="center">
      <thead slot="head">
        <th style="padding: 12px">DAY</th>
        <th style="padding: 12px">START TIME</th>
        <th style="padding: 12px">END TIME</th>
      </thead>
      <tbody slot="body">
        <tr
          v-for="openingsHour in openingsHours"
          :key="openingsHour.openingDay"
        >
          <td>{{ openingsHour.day }}</td>
          <td v-if="openingsHour.working">{{ openingsHour.startTime.substring(0, 5) }}</td>
          <td v-if="openingsHour.working">{{ openingsHour.endTime.substring(0, 5) }}</td>
          <td v-if="!openingsHour.working">-</td>
          <td v-if="!openingsHour.working">-</td>
        </tr>
      </tbody>
      <br>
    </v-table>
    </div>

    <!-- About Us Section -->
    <div>
      <br>
      <h2>About us</h2>
      <br>
      <p style="padding: 20px;">Get hungry for local, organic, plant-based & more: see today's sales, browse products by special diet, find recipes, get delivery and pick up & order online. Be whole, shop at Whole Foods today.</p>
      <p><b>Our Email: </b>{{ email }}</p>
      <p><b>Address: </b>{{ adress }}</p>
      <p><b>Phone number: </b>{{ number }}</p>
      <br>
    </div>

</template>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.wrapper {
  display: grid;
  /* grid-template-columns: 50% 50%; */
  background-color: #fff;
  border: 1px;
  border-color: red;
  padding-left: 80px;
  padding-right: 80px;
  
}


h1,
h2,
h3 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
v-table {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}
td {
  border: 1px solid #ddd;
  padding: 8px;
}
th {
  border: 1px solid #ddd;
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: center;
  background-color: #00674B;
  color: white;
}
</style>
