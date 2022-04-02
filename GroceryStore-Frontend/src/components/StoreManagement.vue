<template>
  <div class="d-flex align-items-center justify-content-center" style="height: 500px;" id = "StoreManagement">
  <h1> Store Management </h1>
    <h2> Modify Grocery Store Business Hours </h2>
    <p> Please enter the grocery store name: </p>
        <input
          style="margin-top: 6px;"
          type="text"
          v-model="groceryStoreName"
          placeholder="Grocery Store Name"
        />
        <p> Please enter the day to update: </p>
        <select name="filter" id="filter" @change="getHourByDay($event)">
          <option value="Sunday" key="Sunday">Sunday</option>
          <option value="Monday" key="Monday">Monday</option>
          <option value="Tuesday" key="Tuesday">Tuesday</option>
          <option value="Wednesday" key="Wednesday">Wednesday</option>
          <option value="Thursday" key="Thursday">Thursday</option>
          <option value="Friday" key="Friday">Friday</option>
          <option value="Saturday" key="Saturday">Saturday</option>
          </select>
        <p> Please enter the new start time: </p>
         <input
                style="margin-top: 6px;"
                type="text"
                v-model="startTime"
                placeholder="Start Time"
          />
         <p> Please enter the new end time: </p>
            <input
                style="margin-top: 6px;"
                type="text"
                v-model="endTime"
                placeholder="End Time"
            />                                                                                                                                              /
            <button
                class="btn btn-light"
                @click="updateStoreHours(groceryStoreName, day, startTime, endTime);"
            >
                Update Grocery Store Opening Hours
            </button>

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

<script> </script>
<style> </style>