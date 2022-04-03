
<template>
  <div
    class="d-flex align-items-center justify-content-center"
    style="height: 800px"
    id="Shifts"
  >
    <table>
      <h2>Shift Management</h2>
      <tr>
        <!-- Search Filter Component -->
        <select name="filter" id="filter" @change="filterToggle($event)">
          <option value="blank" key="blank"></option>
          <option value="Employee" key="Employee">Employee ID</option>
          <option value="Name" key="Name">Name</option>
          <option value="Day" key="Day">Day</option>
          <option value="Working" key="Working">Working</option>
        </select>
      </tr>

      <!-- Search Filter Component - Employee ID -->
      <tr v-if="filter == 'Employee'">
        <input
          style="margin-top: 6px"
          type="text"
          v-model="employeeId"
          placeholder="Employee ID"
        />
        <button class="btn btn-light" @click="getEmployeeHour(employeeId)">
          Search
        </button>
      </tr>
      <!-- Search Filter Component - Name -->
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
          class="btn btn-light"
          @click="getEmployees(firstName, lastName)"
        >
          Search
        </button>
      </tr>
      <!-- Search Filter Component - Day -->
      <tr v-if="filter == 'Day'">
        <select name="filter" id="filter" @change="getHourByDay($event)">
          <option value="Sunday" key="Sunday">Sunday</option>
          <option value="Monday" key="Monday">Monday</option>
          <option value="Tuesday" key="Tuesday">Tuesday</option>
          <option value="Wednesday" key="Wednesday">Wednesday</option>
          <option value="Thursday" key="Thursday">Thursday</option>
          <option value="Friday" key="Friday">Friday</option>
          <option value="Saturday" key="Saturday">Saturday</option>
        </select>
      </tr>
      <!-- Search Filter Component - Working -->
      <tr v-if="filter == 'Working'">
        <select name="filter" id="filter" @change="getHourByWorking($event)">
          <option value="True" key="True">True</option>
          <option value="False" key="False">False</option>
        </select>
      </tr>

      <!-- Display Table Component -->
      <v-data-table class="elevation-1">
        <!-- <tr>
                <td class="table-text">| Employee Name |</td>
                <td class="table-text">| Day |</td>
                <td class="table-text">| Start Time |</td>
                <td class="table-text">| End Time |</td>
        </tr> -->
        <div v-if="showHours">
          <tr v-for="hour in hours" :key="hour.id">
            <td class="table-text">
              {{
                hour.employee.person.firstName +
                " " +
                hour.employee.person.lastName
              }}
            </td>
            <td class="table-text">{{ hour.day }}</td>
            <input
              style=""
              type="text"
              v-model="startTime[hour.id]"
              :placeholder="[[hour.startTime.substring(0, 5)]]"
            />
            <input
              style="width: 80"
              type="text"
              v-model="endTime[hour.id]"
              :placeholder="[[hour.endTime.substring(0, 5)]]"
            />
            <input type="checkbox" id="inTown" v-model="hour.working" />
            <button
              class="btn btn-light"
              @click="
                updateHour(
                  hour.id,
                  hour.day,
                  startTime[hour.id],
                  endTime[hour.id],
                  hour.working,
                  hour.employee.id
                )
              "
            >
              Update
            </button>
          </tr>
        </div>
        <div v-if="!showHours">
          <tr v-for="e in employees" :key="e.email">
            <td class="table-text">{{ e.firstName + " " + e.lastName }}</td>
            <button
              class="btn btn-light"
              @click="viewEmployeeHourByEmail(e.email)"
            >
              View
            </button>
          </tr>
        </div>
      </v-data-table>
    </table>
  </div>
</template>
<script src="./shifts.js"></script>
<style scoped>
.input {
  width: 400px;
}
</style>
