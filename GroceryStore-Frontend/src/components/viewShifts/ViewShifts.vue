<template>
    <div class= "d-flex align-items-center justify-content-center" id="View Shifts" style="height:500px;">
        <div>Name: {{ employee.person.firstName + " " + employee.person.lastName }}</div>
        <!-- Employee Shifts Data Table -->
        <table class="elevation-1">
            <th>
                <td class="table-text">| Day |</td>
                <td class="table-text">| Start Time |</td>
                <td class="table-text">| End Time |</td>
            </th>
            <tbody>
                <BusinessHour
                  v-for="hour in businesshours"
                  :key="hour.id"
                  :businessHourId="hour.id"
                  :day="hour.day"
                  :startTime="hour.startTime.substring(0,5)"
                  :endTime="hour.endTime.substring(0,5)"
                ></BusinessHour>
            </tbody>
        </table>
    </div>
</template>
<script src="./viewshifts.js"></script>

