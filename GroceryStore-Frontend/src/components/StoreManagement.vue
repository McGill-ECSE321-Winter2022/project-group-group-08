<template>
  <div class="d-flex align-items-center justify-content-center" style="height: 700px;" id = "StoreManagement">
  
  <table>
    <h1> Store Management </h1>
    <h4> Modify Store Opening Hours </h4> 
    <!-- Display Data Component for Store Opening Hours -->
    <v-data-table class="elevation-1">
      <th> Day </th>
      <th>Start Time</th>
      <th>End Time</th>
      <th>Working</th>
      <tbody>
        <tr v-for="hour in hours" :key="hour.id">
          <td class="table-text">{{hour.day}}</td>
          <td>
            <input 
              style="margin-top: 6px;"
              type="text"
              v-model="startTime[hour.id]"
              :placeholder="[[ hour.startTime.substring(0,5) ]]"
            />
          </td>
          <td>
            <input 
              style="margin-top: 6px;"
              type="text"
              v-model="endTime[hour.id]"
              :placeholder="[[ hour.endTime.substring(0,5) ]]"
            />
          </td>
          <td>
            <input type="checkbox" id="pickup" v-model="hour.working"/>      
          </td>                                                                                                                       
          <button
            class="btn btn-light"
            @click="updateStoreHours(hour.id, hour.day, startTime[hour.id], endTime[hour.id], hour.working);"
              >
            Update
          </button>
        </tr>  

      </tbody>
    </v-data-table>
   
  <div id = "ModifyEmployeeDiscount">
   <h4> Modify Store Information </h4>
        <!-- <input
          style="margin-top: 6px;"
          type="text"
          v-model="storename"
          placeholder="Store Name"
        /> -->
        <v-data-table class="elevation-1">
        <tr>
          <td>
            <!-- Modify Address Input box-->
            <label>Address: </label>
          </td>
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
            <!-- Modify Employee Discount Input Box-->
            <label>Employee Discount: </label>
          </td>
          <td>
            <input
              style="margin-top: 6px;"
              type="text"
              v-model="employeeDiscount"
              placeholder="New Employee Discount"
            />
          </td>
        </tr>
        <tr>
          <!-- Update Info Button -->
          <button
            class="btn btn-light"
            @click="updateEmployeeDiscount(storename, address, employeeDiscount);"
          >
              Update
          </button>
        </tr>
        </v-data-table>
  </div>
 </table>

  
 </div>
</template>

<script src="./storeManagement.js"> </script>
<style> </style>