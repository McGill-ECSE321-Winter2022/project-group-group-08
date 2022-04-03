/**
 * presents the item data
 */
export default {
  name: 'BusinessHour',

  props: {
    // Set Props
    businessHourId: Number,
    day: String,
    startTime: String,
    endTime: String,
    working: Boolean
  },
};