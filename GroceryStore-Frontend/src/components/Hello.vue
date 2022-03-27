<template>
  <div class="hello">
    <div>
      <img src="./../assets/GroceryStoreLogo.png" width="180px" />
    </div>
    <h1 style="margin-bottom: 25px;">{{ msg }}</h1>
    <ul>
      <li>
        <button
          style="margin-bottom: 25px;"
          class="btn btn-light"
          v-on:click="goLogin()"
        >
          Login
        </button>
      </li>
      <li>
        <button
          style="margin-bottom: 25px;"
          class="btn btn-light"
          v-on:click="goSignUp()"
        >
          Signup
        </button>
      </li>
    </ul>

    <h3 style="text-align: center">Grocery Store Opening Hours</h3>

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
          <td>{{ openingsHour.openingDay }}</td>
          <td>{{ openingsHour.startTime }}</td>
          <td>{{ openingsHour.endTime }}</td>
        </tr>
      </tbody>
    </v-table>
    <hr />
    <h2>About us</h2>
    <li>{{ email }}</li>
    <li>{{ adress }}</li>
    <li>{{ number }}</li>
  </div>
</template>

<script>
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
  name: "hello",
  created: function() {
    AXIOS.get("/getAllOpeningsHours")
      .then(response => {
        // JSON responses are automatically parsed.
        this.openingsHours = response.data;
      })
      .catch(e => {
        this.errorOpeningsHours = e;
      });
  },
  data() {
    return {
      msg: "Welcome to Your Local Grocery Store",
      email: "Email: group8@mail.mcgill.ca",
      adress: "Address: 123 McGill Avenue",
      number: "Call: 514-100-1313",
      errorOpeningsHours: "",
      openingsHours: []
    };
  },
  methods: {
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
    goLogin: function() {
      this.$router.push({ path: `/Login` });
    },
    goSignUp: function() {
      this.$router.push({ path: `/Signup` });
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
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
  background-color: #000;
  color: white;
}
</style>
