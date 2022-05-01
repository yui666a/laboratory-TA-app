<template>
  <div class="seating-chart-section">
    <div class="seating-chart-area">
      <div v-for="column of 9" :key="column" class="seat-column">
        <div
          v-for="row of 8"
          :key="row"
          class="seat-row"
          :set="(seat = '8' + ((column - 1) * 8 + row).toString().padStart(2, '0'))"
        >
          <SeatInfo :seatId="seat" :seat="seats[seat]"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SeatInfo from "./Seat.vue";

export default {
  name: "SeatingChart",
  components: {
    SeatInfo,
  },
  data() {
    return {
      // sample data
      seats: {
        801: {
          pcName: "ics801",
          studentId: "123456",
          studentName: "まつもと ただのぶ",
        },
        802: {
          pcName: "ics802",
          studentId: "123457",
          studentName: "あいそ",
        },
        803: {
          pcName: "ics801",
          studentId: "123456",
          studentName: "まつもと ただのぶ",
        },
        804: {
          pcName: "ics802",
          studentId: "123457",
          studentName: "あいそ",
        },
        805: {
          pcName: "ics801",
          studentId: "123456",
          studentName: "まつもと ただのぶ",
        },
        870: {
          pcName: "ics802",
          studentId: "123457",
          studentName: "あいそ",
        },
      },
    };
  },
};
</script>

<style scoped>
.seating-chart-area{
  overflow-x: scroll;
  overflow-y: scroll;
}
.seat-column {
  display: flex;
}
</style>
