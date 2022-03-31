<template>
  <div id="profile">
    <div id="header">
      <h3>Profile Title - {{ userRole }}</h3>
      <h3>{{ firstName + " " + lastName }}</h3>
      <hr />
    </div>
    <button type="button" class="btn btn-light" v-on:click="isShow = !isShow">
        Update Profile
      </button>
    <div id="body" v-show="!isShow">
      <h5>Username : {{ username }}</h5>
      <h5>Email : {{ email }}</h5>
      <div id="phoneNumber">
        <h5>Phone Number : {{ phoneNumber }}</h5>
      </div>
      <div id="address">
        <h5>Address : {{ address }}</h5>
      </div>
      <div id="password">
        <h5>Password : {{ password }}</h5>
      </div>
      <div id="inTown">
        <h5>In Town : {{ inTown }}</h5>
      </div>
      <div id="totalPoints">
        <h5>Your Points : {{ totalPoints }}</h5>
      </div>
    </div>

    <div id="body" v-show="isShow">
      <h5>Username : {{ username }}</h5>
      <h5>Email : {{ email }}</h5>
      <div id="firstName" class="Row">
        <h5 class="Column">First Name : </h5>
        <input
          class="Column"
          type="text"
          v-model="newFirstName"
          :placeholder=" [[ firstName ]]"
        />
      </div>
      <div id="lastName" class="Row">
        <h5 class="Column">Last Name : </h5>
        <input
          class="Column"
          type="text"
          v-model="newLastName"
          :placeholder="[[ lastName ]]"
        />
      </div>
      <div id="phoneNumber" class="Row">
        <h5 class="Column">Phone Number : </h5>
        <input
          class="Column"
          type="text"
          v-model="newPhoneNumber"
          :placeholder="[[ phoneNumber ]]"
        />
      </div>
      <div id="address" class="Row">
        <h5 class="Column">Address : </h5>
        <input
          class="Column"
          type="text"
          v-model="newAddress"
          :placeholder="[[ address ]]"
        />
      </div>
      <div id="password" class="Row">
        <h5 class="Column">Password : </h5>
        <input
          class="Column"
          type="password"
          v-model="newPassword"
          placeholder="New Password"
        />
      </div>
      <div id="inTown" class="Row">
        <h5 class="Column">In Town : </h5>
        <input
          class="Column"
          type="text"
          v-model="newInTown"
          :placeholder=" [[ inTown ]]"
        />
      </div>
      <div id="totalPoints">
        <h5>Your Points : {{ totalPoints }}</h5>
      </div>
      
      <button
        type="button"
        class="btn btn-light"
        v-on:click="deleteAccount()"
        id="deleteAccountButton"
      >
        Delete Profile
      </button>
      <button
        class="btn btn-light"
        v-on:click="update()"
      >
        Update
      </button>
    </div>
    <div v-show="deleteError" id="error">
      <H5>Delete failed: outstanding Customer balance and/or reservation(s)</H5>
    </div>
    <div v-show="isError" id="error">
      <H5>Your password must have at least 8 characters and a capital letter</H5
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
    width:200px;
}
</style>
