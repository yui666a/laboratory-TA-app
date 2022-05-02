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
          <div
            style="width: 8em; margin: 7px"
            v-if="row === 1 && column !== 2 && column !== 4 && column !== 9"
          ></div>
          <SeatInfo
            :seatId="seat"
            :seat="seats[seat]"
            v-bind:class="{
              mr50: row === 1 && (column === 2 || column === 4 || column === 9),
              ml50: row === 1 && column !== 2 && column !== 4 && column !== 9,
            }"
            v-bind:style="[column === 9 && row === 8 ? 'display: none;' : '']"
          />
          <div
            style="width: 8em; margin: 7px"
            v-if="row === 1 && (column === 2 || column === 4)"
          ></div>
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
.seating-chart-area {
  overflow-x: scroll;
  overflow-y: scroll;
}
.seat-column {
  display: flex;
}
.seat-row {
  display: flex;
}
.ml50 {
  margin-left: 50px;
}
.mr50 {
  margin-right: 50px;
}
</style>
