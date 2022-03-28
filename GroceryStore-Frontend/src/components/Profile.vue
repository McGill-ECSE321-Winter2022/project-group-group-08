<template>
  <div id="profile">
    <div id="header">
      <h3>User Profile - {{ accountType }}</h3>
      <h3>{{ name + " " + familyname }}</h3>
      <hr />
    </div>
    <div id="body">
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
        <h5>Location : {{ inTown }}</h5>
      </div>
      <div id="totalPoints">
        <h5>Your Points : {{ totalPoints }}</h5>
      </div>
      <button type="button" class="btn btn-light" v-on:click="isShow = !isShow">
        Update Account
      </button>
      <button
        type="button"
        class="btn btn-light"
        v-on:click="deleteAccount()"
        id="deleteAccountButton"
      >
        Delete Account
      </button>
      <div v-show="isShow" id="update-account">
        <div>
          <input type="text" v-model="newAddress" placeholder="new address" />
          <button
            type="button"
            class="btn btn-light"
            v-bind:disabled="!newAddress"
            v-on:click="updateAddress(newAddress)"
          >
            Update
          </button>
        </div>

        <div>
          <input type="text" v-model="newPassword" placeholder="new Password" />
          <button
            type="button"
            class="btn btn-light"
            v-bind:disabled="!newPassword"
            v-on:click="updatePassword(newPassword)"
          >
            Update
          </button>
        </div>
        <div v-show="deleteError" id="error">
          <H5
            >Delete failed: outstanding Customer balance and/or
            reservation(s)</H5
          >
        </div>
        <div v-show="isError" id="error">
          <H5
            >Your password must have at least 8 characters and a capital
            letter</H5
          >
        </div>
      </div>
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
</style>
