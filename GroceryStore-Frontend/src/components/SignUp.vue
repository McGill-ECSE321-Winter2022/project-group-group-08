<template>
  <div
    class="d-flex align-items-center justify-content-center"
    style="height: 500px"
    id="Signup"
  >
    <table>
      <tr>
        <h2>Sign Up</h2>
      </tr>
      <tr>
        <!-- Input Email Box -->
        <td class="table-text">Email:</td>
        <input
          style="margin-top: 6px"
          type="text"
          v-model="email"
          placeholder="Email"
        />
      </tr>
      <tr>
        <!-- Display Profile Pic -->
        <td class="table-text">Profile pic:</td>
        <input
          style="margin-top: 6px"
          type="text"
          v-model="image"
          placeholder="Link"
        />
      </tr>
      <tr>
        <!-- Input Phone Number Box -->
        <td class="table-text">Phone Number:</td>
        <input
          style="margin-top: 6px"
          type="text"
          v-model="phoneNumber"
          placeholder="Phone number"
        />
      </tr>
      <tr>
        <!-- Input First Name Box -->
        <td class="table-text">First Name:</td>
        <input
          style="margin-top: 6px"
          type="text"
          v-model="firstName"
          placeholder="First name"
        />
      </tr>
      <tr>
        <!-- Input Last Name Box -->
        <td class="table-text">Last Name:</td>
        <input
          style="margin-top: 6px"
          type="text"
          v-model="lastName"
          placeholder="Last name"
        />
      </tr>
      <tr>
        <!-- Input Address Box -->
        <td class="table-text">Address:</td>
        <input
          style="margin-top: 6px"
          type="text"
          v-model="address"
          placeholder="Address"
        />
      </tr>
      <tr>
        <!-- Input Username Box -->
        <td class="table-text">Username:</td>
        <input
          style="margin-top: 6px"
          type="text"
          v-model="username"
          placeholder="Username"
        />
      </tr>
      <tr>
        <!-- Input Password Box -->
        <td class="table-text">Password:</td>
        <input
          style="margin-top: 6px"
          type="password"
          v-model="password"
          placeholder="Password"
        />
      </tr>

      <tr>
        <!-- InTown CheckBox -->
        <td class="table-text">Do you live in town?</td>
        <label>Yes </label>
        <input type="checkbox" id="inTown" value="true" />
        <label>No </label>
        <input type="checkbox" id="notInTown" value="false" />
      </tr>
      <tr>
        <!-- Role Selection -->
        <td class="table-text">Choose your account role</td>
        <select name="userRole" id="userRole">
          <option value="blank"></option>
          <option value="Employee">Employee</option>
          <option value="Customer">Customer</option>
        </select>
      </tr>
      <tr>
        <td>
          <!-- SignUp Button -->
          <button
            style="margin-top: 15px"
            class="btn btn-light"
            @click="
              signUp(
                image,
                email,
                phoneNumber,
                firstName,
                lastName,
                address,
                username,
                password,
                inTown,
                userRole
              )
            "
          >
            Sign Up
          </button>
        </td>
      </tr>
    </table>

    <p>
      <span v-if="errorSignup" style="color: red"
        >Error: {{ errorSignup }}</span
      >
    </p>
  </div>
</template>
<script src="./signup.js"></script>
