<template>
    <div class= "d-flex align-items-center justify-content-center" id="View Shifts" style="height:500px;">
        <v-data-table class="elevation-1">
            <th>
                <td class="table-text">| Employee Name |</td>
                <td class="table-text">| Day |</td>
                <td class="table-text">| Start Time |</td>
                <td class="table-text">| End Time |</td>
            </th>
            <tr v-for="hour in hours" :key="hour.id">
                <td class="table-text">{{hour.employee.personDto.firstName + " " + hour.employee.personDto.lastName}}</td>
                <td class="table-text">{{hour.day}}</td>
                <td class="table-text">{{hour.startTime}}</td> 
                <td class="table-text">{{hour.endTime}} </td> 
            </tr>
        </v-data-table>
    </div>
</template>
<script src="./viewshifts.js"></script>
<style>
</style>