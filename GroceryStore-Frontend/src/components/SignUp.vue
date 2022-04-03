<template>
  <div
    class="d-flex align-items-center justify-content-center"
    style="height: 500px;"
    id="Signup"
  >
    <table>
      <tr>
        <h2>Sign Up</h2>
      </tr>
      <tr>
        <td class="table-text">Email:</td>
        <input
          style="margin-top: 6px;"
          type="text"
          v-model="email"
          placeholder="Email"
        />
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
        <td class="table-text">Phone Number:</td>
        <input
          style="margin-top: 6px;"
          type="text"
          v-model="phoneNumber"
          placeholder="Phone number"
        />
      </tr>
      <tr>
        <td class="table-text">First Name:</td>
        <input
          style="margin-top: 6px;"
          type="text"
          v-model="firstName"
          placeholder="First name"
        />
      </tr>
      <tr>
        <td class="table-text">Last Name:</td>
        <input
          style="margin-top: 6px;"
          type="text"
          v-model="lastName"
          placeholder="Last name"
        />
      </tr>
      <tr>
        <td class="table-text">Address:</td>
        <input
          style="margin-top: 6px;"
          type="text"
          v-model="address"
          placeholder="Address"
        />
      </tr>
      <tr>
        <td class="table-text">Username:</td>
        <input
          style="margin-top: 6px;"
          type="text"
          v-model="username"
          placeholder="Username"
        />
      </tr>
      <tr>
        <td class="table-text">Password:</td>
        <input
          style="margin-top: 6px;"
          type="password"
          v-model="password"
          placeholder="Password"
        />
      </tr>

      <tr>
        <td class="table-text">Do you live in town?</td>
        <label>Yes </label>
        <input type="checkbox" id="inTown" value="true" />
        <label>No </label>
        <input type="checkbox" id="notInTown" value="false" />
      </tr>
      <tr>
        <td class="table-text">Choose your account role</td>
        <select name="userRole" id="userRole">
          <option value="blank"></option>
          <option value="Employee">Employee</option>
          <option value="Customer">Customer</option>
        </select>
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
