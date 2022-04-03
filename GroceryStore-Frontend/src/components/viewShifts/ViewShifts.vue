<template>
    <div class= "d-flex align-items-center justify-content-center" id="View Shifts" style="height:500px;">
        <div>Name: {{ employee.personDto.firstName + " " + employee.personDto.lastName }}</div>
        <!-- Employee Shifts Data Table -->
        <table class="elevation-1">
            <th>
                <td class="table-text">| Day |</td>
                <td class="table-text">| Start Time |</td>
                <td class="table-text">| End Time |</td>
            </th>
            <tbody>
                <BusinessHour
                  v-for="hour in workingShifts"
                  :key="hour.id"
                  :businessHourId="hour.id"
                  :day="hour.day"
                  :startTime="hour.starTime"
                  :endTime="hour.endTime"
                ></BusinessHour>
            </tbody>
        </table>
    </div>
</template>
<script src="./viewshifts.js"></script>

