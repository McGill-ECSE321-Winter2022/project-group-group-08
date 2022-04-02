
<template>
  <div
    class="d-flex align-items-center justify-content-center"
    style="height: 500px"
    id="viewemployee"
  >
    <table>
      <h2>View Employees</h2>
      <tr>
        <select name="filter" id="filter" @change="filterToggle($event)">
          <option value="blank" key="blank"></option>
          <option value="EmployeeID" key="employeeID">Employee ID</option>
          <option value="Name" key="name">Name</option>
          <option value="Email" key="email">Email</option>
        </select>
      </tr>

      <tr v-if="filter == 'EmployeeID'">
        <input
          style="margin-top: 6px"
          type="text"
          v-model="employeeID"
          placeholder="Employee ID"
        />
        <button size="sm"
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
        <button
          class="filter button"
          @click="getEmployeeByEmail(email)"
        >
          Search
        </button>
      </tr>

      <v-data-table class="elevation-1">
        <tr v-for="employee in employees" :key="employee.ID">
          <td class="table-text">{{ employeeID }}</td>
          <td class="table-text">{{ firstName }}</td>
          <td class="table-text">{{ lastName }}</td>
          <td class="table-text">{{ email }}</td>
          <td>
            <button
              class="fire button"
              @click="fireEmployee(employeeID)"
            >
              Fire Employee
            </button>
          </td>
        </tr>
      </v-data-table>
    </table>
  </div>
</template>

<script src="./viewemployees.js"></script>

<style scoped>
.input {
  width: 400px;
}
</style>
