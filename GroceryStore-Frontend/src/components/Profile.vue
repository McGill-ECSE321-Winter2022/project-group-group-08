<template>
  <div id="profile">
    <div id="header">
      <br>
      
      <h3>Hey, {{ firstName + " " + lastName }}</h3>
      <p>Account Type - {{ userRole }}</p>
      <hr />
    </div>
   
    <!-- Display Account Info -->
    <h4> Account info: </h4>
  <br>
    <div id="body" v-show="!isShow">
      <img :src="image" style="width: 100px; clip-path: circle();" />
      <br>
      <br>
      <h6><b>Username:</b> {{ username }}<b>   Email:</b> {{ email }}</h6>
       <br>
      <div id="phoneNumber">
        <h6><b>Phone Number:</b> {{ phoneNumber }}</h6>
      </div>
      <div id="address">
        <h6><b>Address:</b> {{ address }}</h6>
      </div>
      <div id="password">
        <h6><b>Password:</b> {{ password }}</h6>
      </div>
      <div id="inTown">
        <h6><b>In Town:</b> {{ inTown }}</h6>
      </div>
      <div id="totalPoints">
        <h6><b>Your Points:</b> {{ totalPoints }}</h6>
      </div>
    </div>
    <br>
 <button type="button" class="btn btn-light" v-on:click="isShow = !isShow">
      Update Profile
    </button>
    <!-- Display Update Account Inputs -->
    <div id="body" v-show="isShow">
      <h5>Username : {{ username }}</h5>
      <h5>Email : {{ email }}</h5>
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
        <h5 class="Column">Last Name :</h5>
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
      <div id="totalPoints">
        <h5>Your Points : {{ totalPoints }}</h5>
      </div>

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
<script src="./profile.js"></script>
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
