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
            v-model="firstName"
            placeholder="First name"
          />
        </td>
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
            v-model="address"
            placeholder="Address"
          />
        </td>
        <td>
          <input
            style="margin-top: 6px;"
            type="text"
            v-model="username"
            placeholder="Username"
          />
        </td>
         <td>
          <input
            style="margin-top: 6px;"
            type="text"
            v-model="image"
            placeholder="Link"
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
          <label for="inTown"> Do you live in town? </label>
          <br />
          <label>Yes</label>
          <input type="checkbox" id="inTown" value="true" />
          <label>No</label>
          <input type="checkbox" id="notInTown" value="false" />
        </td>
        <td>
          <label for="Roles"> Choose your account role </label>
          <br />
          <select name="userRole" id="userRole">
            <option value="blank"></option>
            <option value="Manager">Manager</option>
            <option value="Employee">Employee</option>
            <option value="Customer">Customer</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>
          <button
            style="margin-top: 15px;"
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
      <span v-if="errorSignup" style="color:red">Error: {{ errorSignup }}</span>
    </p>
  </div>
</template>
<script src="./signup.js"></script>
