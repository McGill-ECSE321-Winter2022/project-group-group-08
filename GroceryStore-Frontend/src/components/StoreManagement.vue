<template>
  <div class="d-flex align-items-center justify-content-center" style="height: 700px;" id = "StoreManagement">
  <h1> Store Management </h1>
  <table>
    <h2> Modify Local Grocery Store Opening Hours </h2> 
     <tr>
        <th>Day</th>
        <th>Start Time</th>
        <th>End Time</th>
        <th>Working</th>
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
                <input type="checkbox" id="pickup" v-model="hour.working"/>                                                                                                                             
                <button
                class="btn btn-light"
                @click="updateStoreHours(hour.id, hour.day, startTime[hour.id], endTime[hour.id], hour.working);"
                 >
                Update
                 </button>
            </tr>  
 </table>

  <div id = "ModifyEmployeeDiscount">
   <h2> Modify Employee Discount </h2>
     <input
          style="margin-top: 6px;"
          type="text"
          v-model="storename"
          placeholder="Store Name"
        />
          <input
          style="margin-top: 6px;"
          type="text"
          v-model="address"
          placeholder="Address"
        />
       <input
          style="margin-top: 6px;"
          type="text"
          v-model="employeeDiscount"
          placeholder="New Employee Discount"
        />
        <button
          class="btn btn-light"
          @click="updateEmployeeDiscount(storename, address, employeeDiscount);"
        >
            Update Employee Discount
        </button>
  </div>
 </div>
</template>

<script src="./storeManagement.js"> </script>
<style> </style>