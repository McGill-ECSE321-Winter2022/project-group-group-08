<template>
  <!--Show Grocery Store Business Hours -->
  <tr :key="businessHourId">
    <td class="table-text">{{ day }}</td>
    <td v-if="working" class="table-text">{{ startTime }}</td>
    <td v-if="working" class="table-text">{{ endTime }}</td>
    <td v-if="!working" class="table-text">-</td>
    <td v-if="!working" class="table-text">-</td>
  </tr>
</template>

<script src="./businessHour.js"></script>
