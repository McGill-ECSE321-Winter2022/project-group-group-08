<template>
  <!--Show Grocery Store Business Hours -->
  <tr :key="businessHourId">
    <td class="table-text">{{ day }}</td>
    <td class="table-text">{{ startTime }}</td>
    <td class="table-text">{{ endTime }}</td>
  </tr>
</template>

<script src="./businessHour.js"></script>
