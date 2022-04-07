
<template>
  <div
    class="d-flex align-items-center justify-content-center"
    style="height: 500px"
    id="viewemployee"
  >
    <table>
      <h2>View Employees</h2>
      <br>
      <!-- !!! Implement if have time -->
      <!-- Search Filter Component -->
      <tr>
        <!-- <select name="filter" id="filter" @change="filterToggle($event)">
          <option value="blank" key="blank"></option>
          <option value="EmployeeID" key="employeeID">Employee ID</option>
          <option value="Name" key="name">Name</option>
          <option value="Email" key="email">Email</option>
        </select> -->
      </tr>

      <tr v-if="filter == 'EmployeeID'">
        <input
          style="margin-top: 6px"
          type="text"
          v-model="employeeID"
          placeholder="Employee ID"
        />
        <button
          size="sm"
          class="filter button"
          @click="getEmployeeByID(employeeID)"
        >
          Search
        </button>
      </tr>

      <tr v-if="filter == 'Name'">
        <input
          style="margin-top: 6px"
          type="text"
          v-model="firstName"
          placeholder="First Name"
        />
        <input
          style="margin-top: 6px"
          type="text"
          v-model="lastName"
          placeholder="Last Name"
        />
        <button
          class="filter button"
          @click="getEmployeeByName(firstName, lastName)"
        >
          Search
        </button>
      </tr>
      
      <tr v-if="filter == 'Email'">
        <input
          style="margin-top: 6px"
          type="text"
          v-model="email"
          placeholder="Email"
        />
        <button class="filter button" @click="getEmployeeByEmail(email)">
          Search
        </button>
      </tr>

      <!-- Display Table Component -->
      <v-data-table class="elevation-1" style="border-spacing: 0 3rem;">
        <th>Employee ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <tbody>
        <tr v-for="employee in employees" :key="employee.id">
          <td class="table-text">{{ employee.id }}</td>
          <td class="table-text">{{ employee.person.firstName }}</td>
          <td class="table-text">{{ employee.person.lastName }}</td>
          <td class="table-text">{{ employee.person.email }}</td>
          <td>
            <button class="fire button" @click="fireEmployee(employee.id)">
              Fire
            </button>
          </td>
        </tr>
        </tbody>
      </v-data-table>
    </table>
  </div>
</template>

<script src="./viewemployees.js"></script>

<style scoped>
.input {
  width: 400px;
}
td {
  width: 160px;
}


</style>
