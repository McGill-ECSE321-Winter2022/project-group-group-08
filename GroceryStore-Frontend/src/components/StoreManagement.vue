<template>
  <div class="d-flex align-items-center justify-content-center" style="height: 500px;" id = "StoreManagement">
  <h1> Store Management </h1>
  <table>
    <h2> Modify Local Grocery Store Opening Hours </h2> 
     <tr>
        <th>Day</th>
        <th>Start Time</th>
        <th>End Time</th>
      </tr>
   <tr v-for="hour in hours" :key="hour.id">
                <td class="table-text">{{hour.day}}</td>
               <input 
                style="margin-top: 6px;"
                type="text"
                v-model="startTime[hour.id]"
                :placeholder="[[ hour.startTime.substring(0,5) ]]"
               />
                <input 
                style="margin-top: 6px;"
                type="text"
                v-model="endTime[hour.id]"
                :placeholder="[[ hour.endTime.substring(0,5) ]]"
               />
                <input type="checkbox" id="open" v-model="hour.working" />                                                                                                                             /
                <button
                class="btn btn-light"
                @click="updateStoreHours(hour.id, hour.day, startTime[hour.id], endTime[hour.id], hour.working);"
                 >
                Update Grocery Store Opening Hours
                 </button>
            </tr>  
 </table>

   <v-data-table class="elevation-1">
            <tr>
                <td class="table-text">| Day |</td>
                <td class="table-text">| Start Time |</td>
                <td class="table-text">| End Time |</td>
            </tr>
            <tr v-for="hour in hours" :key="hour.id">
                <td class="table-text">{{hour.day}}</td>
               <input 
                style="margin-top: 6px;"
                type="text"
                v-model="startTime[hour.id]"
                :placeholder="[[ hour.startTime.substring(0,5) ]]"
               />
                <input 
                style="margin-top: 6px;"
                type="text"
                v-model="endTime[hour.id]"
                :placeholder="[[ hour.endTime.substring(0,5) ]]"
               />
                <input type="checkbox" id="open" v-model="hour.working" />                                                                                                                             /
                <button
                class="btn btn-light"
                @click="updateStoreHours(hour.id, hour.day, startTime[hour.id], endTime[hour.id], hour.working);"
                 >
                Update Grocery Store Opening Hours
                 </button>
            </tr>   

        </v-data-table>


  <div id = "ModifyEmployeeDiscount">
   <h2> Modify Employee Discount </h2>
       <input
          style="margin-top: 6px;"
          type="text"
          v-model="employeeDiscount"
          placeholder="New Employee Discount"
        />
        <button
          class="btn btn-light"
          @click="updateEmployeeDiscount(employeeDiscount);"
        >
            Update Employee Discount
        </button>
  </div>
 </div>
</template>

<script src="./storeManagement.js"> </script>
<style> </style>