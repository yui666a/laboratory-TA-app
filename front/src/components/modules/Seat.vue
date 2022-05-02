<template>
  <div v-if="seat" class="seat-info">
    <span> ics{{ seatId }} </span><br />
    <span> {{ seat.studentName }} </span>
  </div>
  <div v-else class="seat-info sign-out">
    <span> ics{{ seatId }} </span><br />
    <span> - </span>
  </div>
</template>

<script>
export default {
  name: "SeatInfo",
  props: {
    seatId: String,
    seat: Object,
  },
  // computed: {},
  // data() {},
};
</script>

<style scoped>
.seat-info {
  /* TODO: 要検証 */
  width: 8em;

  margin: 3px;
  padding:3px;
  border: 1px solid black;
  border-radius: 5px;
}
.sign-out{
  opacity: 0.3;
}
</style>
