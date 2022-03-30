<template>
  <div id="profile">
    <div id="header">
      <h3>Profile Title - {{ userRole }}</h3>
      <h3>{{ firstName + " " + lastName }}</h3>
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
        <h5>In Town : {{ inTown }}</h5>
      </div>
      <div id="totalPoints">
        <h5>Your Points : {{ totalPoints }}</h5>
      </div>
      <button type="button" class="btn btn-light" v-on:click="isShow = !isShow">
        Update Profile
      </button>
      <button
        type="button"
        class="btn btn-light"
        v-on:click="deleteAccount()"
        id="deleteAccountButton"
      >
        Delete Profile
      </button>
      <div v-show="isShow" id="update">
        <table class="center" style="margin-top: 8px;">
          <tr>
            <td>
              <input
                type="text"
                v-model="newFirstName"
                placeholder="new First Name"
              />
            </td>
            <td>
              <input
                type="text"
                v-model="newLastName"
                placeholder="new Last Name"
              />
            </td>
            <td>
              <input
                type="text"
                v-model="newPhoneNumber"
                placeholder="new Phone Number"
              />
            </td>
          </tr>
          <tr>
            <td>
              <input
                type="text"
                v-model="newAddress"
                placeholder="new Address"
              />
            </td>
            <td>
              <input
                type="text"
                v-model="newPassword"
                placeholder="new Password"
              />
            </td>
            <td>
              <input
                type="text"
                v-model="newInTown"
                placeholder="new In Town"
              />
            </td>
          </tr>
          <tr>
            <td></td>
            <td>
              <button
                style="margin-top: 8px;"
                class="btn btn-light"
                v-on:click="update()"
              >
                Update
              </button>
            </td>
          </tr>
        </table>

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
.center {
  margin-left: auto;
  margin-right: auto;
}
</style>
