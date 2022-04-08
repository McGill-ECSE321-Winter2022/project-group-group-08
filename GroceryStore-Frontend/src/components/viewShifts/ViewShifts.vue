<template>
    <div class= "d-flex align-items-center justify-content-center" id="View Shifts" style="height:500px;">
        <div class="outside">
            <!-- Employee Shifts Data Table -->
            <table class="elevation-1">
                <h4>Name: {{ employee.person.firstName + " " + employee.person.lastName }}</h4>
                <v-data-table class="elevation-1">
                    <tr>
                        <td class="table-text">Day</td>
                        <td class="table-text">Start Time</td>
                        <td class="table-text">End Time</td>
                    </tr>
                    <tbody>
                        <BusinessHour
                            v-for="hour in businesshours"
                            :key="hour.id"
                            :businessHourId="hour.id"
                            :day="hour.day"
                            :startTime="hour.startTime.substring(0,5)"
                            :endTime="hour.endTime.substring(0,5)"
                            :working="hour.working"
                        ></BusinessHour>
                    </tbody>
                </v-data-table>
            </table>
        </div>
    </div>
</template>
<script src="./viewshifts.js"></script>
<style scoped>
.outside {
  background-color: rgb(241, 241, 241);
  border-radius: 5%;
  padding: 10px;
}
td {
  width: 160px;
}
</style>

