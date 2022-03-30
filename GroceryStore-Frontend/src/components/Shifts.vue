
<template>
  <div
    class="d-flex align-items-center justify-content-center"
    style="height: 500px;"
    id="Shifts"
  >
    <table>
      <h2>Shift Management</h2>
      <tr>
        <td>
          <input
            style="margin-top: 6px;"
            type="text"
            v-model="firstName"
            placeholder="First Name"
          />
        </td>
        <td>
          <input
            style="margin-top: 6px;"
            type="text"
            v-model="lastName"
            placeholder="lastName"
          />
        </td>
         <td>
          <input
            style="margin-top: 6px;"
            type="text"
            v-model="employeeId"
            placeholder="employeeId"
          />
        </td>
        <td>
          <button
            class="btn btn-light"
            @click="
              getEmployees(firstName, lastName);
            "
          >
            Search
          </button>
        </td>
      </tr>
       <v-data-table  class="elevation-1" >
        <!-- <tr>
                <td class="table-text">| Employee Name |</td>
                <td class="table-text">| Day |</td>
                <td class="table-text">| Start Time |</td>
                <td class="table-text">| End Time |</td>
        </tr> -->
        <tr v-for="hour in hours" :key="hour.id">
            <td class="table-text">{{hour.employee.personDto.firstName + " " + hour.employee.personDto.lastName}}</td>
            <td class="table-text">{{hour.day}}</td>
            <input
                style=""
                type="text"
                v-model="startTime[hour.id]"
                :placeholder="[[ hour.startTime.substring(0,5) ]]"
            />
            <input
                style=""
                type="text"
                v-model="endTime[hour.id]"
                :placeholder="[[ hour.endTime.substring(0,5) ]]"
            />
            <button
                class="btn btn-light"
                @click="updateHour(hour.id, hour.day, startTime[hour.id], endTime[hour.id], hour.working, hour.employee.id)"
            >
                Update
            </button>
            <!-- <td class="table-text">{{hour.day}}</td>  -->
            <!-- <td class="table-text">{{hour.startTime}}</td> 
            <td class="table-text">{{hour.endTime}} </td>  -->
        </tr>
        <!-- <tr v-for="schedule in schedules" :key="schedule.scheduleID">
            <td class="table-text">{{schedule.scheduleID}}</td>
            <td class="table-text">{{schedule.openingTime}}</td> 
            <td class="table-text">{{schedule.closingTime}}</td> 
            <td class="table-text">{{schedule.dayofWeek}} </td> 
        </tr> -->
        
    </v-data-table>
    </table>
  </div>
  
</template>
<script src="./shifts.js"></script>
