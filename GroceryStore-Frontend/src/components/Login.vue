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
            <!-- Username and Password Input -->
            <input
              style="margin-top: 6px;"
              type="text"
              v-model="username"
              placeholder="Username"
            />
          </td>
        </tr>
        <tr>
          <td>
            <input
              style="margin-top: 6px;"
              type="password"
              v-model="password"
              placeholder="Password"
            />
          </td>
        </tr>
        <tr>
          <td>
            <!-- Login Button --> 
            <button
              style="margin-top: 8px;"
              class="btn btn-light"
              v-bind:disabled="!username || !password"
              @click="login(username, password)"
            >
              Login
            </button>
          </td>
        </tr>
        <tr>
          <td>
            <!-- Create new Account Redirect -->
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
