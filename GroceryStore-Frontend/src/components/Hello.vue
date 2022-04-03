<template>
  <div class="hello">
    <br>
    <div>
      <img src="./../assets/wholefoods.png" width="180px" />
    </div>
    <br>
    <h1 style="margin-bottom: 25px;">{{ msg }}</h1>
    <ul v-if="!validUser">
      <li>
        <!-- Login Redirect Button -->
        <button
          style="margin-bottom: 25px;"
          class="btn btn-light"
          v-on:click="goLogin()"
        >
          Login
        </button>
      </li>
      <li>
        <!-- Sign Up Redirect Button -->
        <button
          style="margin-bottom: 25px;"
          class="btn btn-light"
          v-on:click="goSignUp()"
        >
          Signup
        </button>
      </li>
    </ul>

     <hr />
     <br>
<div class="wrapper ">
  <div>
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
   <br><br>
    </div>
    <!-- About Us Section -->
    <div>
    <h2>About us</h2>
     <br>
     <p>Get hungry for local, organic, plant-based & more: see today's sales, browse products by special diet, find recipes, get delivery and pick up & order online. Be whole, shop at Whole Foods today.</p>
    <p><b>Our Email: </b>{{ email }}</p>
    <p><b>Address: </b>{{ adress }}</p>
    <p><b>Phone number: </b>{{ number }}</p>
    <br>
    </div>
    </div>
    <br>
     </div>

</template>

<script src="./hello.js"></script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.wrapper {
  display: grid;
  grid-template-columns: 50% 50%;
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
