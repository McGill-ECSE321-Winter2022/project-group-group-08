<template>
  <div id="Login">
    <div
      class="d-flex align-items-center justify-content-center"
      style="height: 500px;"
      id="LoginCustomer"
    >
      <table>
        <h2>Login Menu</h2>
        <tr>
          <td>
            <input
              style="margin-top: 6px;"
              type="text"
              v-model="accountUsername"
              placeholder="Username"
            />
          </td>
        </tr>
        <tr>
          <td>
            <input
              style="margin-top: 6px;"
              type="password"
              v-model="accountPassword"
              placeholder="Password"
            />
          </td>
        </tr>
        <tr>
          <td>
            <button
              style="margin-top: 8px;"
              class="btn btn-light"
              v-bind:disabled="!accountUsername || !accountPassword"
              @click="login(Username, Password)"
            >
              Login
            </button>
          </td>
        </tr>
        <tr>
          <td>
            <button
              style="margin-top: 8px;"
              class="btn btn-light"
              v-on:click="signUp()"
            >
              Create new Account
            </button>
          </td>
        </tr>
      </table>

      <p>
        <span v-if="errorLogin" style="color:red">Error: {{ errorLogin }}</span>
      </p>
    </div>
  </div>
</template>
<script src="./login.js"></script>
<style></style>
