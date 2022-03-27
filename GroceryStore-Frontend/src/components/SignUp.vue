<template>
  <div
    class="d-flex align-items-center justify-content-center"
    style="height: 500px;"
    id="Signup"
  >
    <table>
      <h2>Sign Up</h2>
      <tr>
        <td>
          <input
            style="margin-top: 6px;"
            type="text"
            v-model="email"
            placeholder="Email"
          />
        </td>
      </tr>
      <tr>
        <td>
          <input
            style="margin-top: 6px;"
            type="text"
            v-model="firstName"
            placeholder="First name"
          />
        </td>
      </tr>
      <tr>
        <td>
          <input
            style="margin-top: 6px;"
            type="text"
            v-model="lastName"
            placeholder="Last name"
          />
        </td>
      </tr>
      <tr>
        <td>
          <input
            style="margin-top: 6px;"
            type="text"
            v-model="phoneNumber"
            placeholder="Phone number"
          />
        </td>
      </tr>
      <tr>
        <td>
          <input
            style="margin-top: 6px;"
            type="text"
            v-model="address"
            placeholder="Address"
          />
        </td>
      </tr>
      <tr>
        <td>
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
          <button
            style="margin-top: 15px;"
            class="btn btn-light"
            @click="
              signUp(
                email,
                firstName,
                lastName,
                phoneNumber,
                address,
                username,
                password
              )
            "
          >
            Sign Up
          </button>
        </td>
      </tr>
    </table>
  </div>
</template>
<script src="./signup.js"></script>
