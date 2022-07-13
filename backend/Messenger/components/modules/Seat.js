export default {
  name: "SeatInfo",
  template: `
    <div v-if="seat" class="seat-info">
      <span> ics{{ seatId }} </span><br />
      <span> {{ seat.studentName }} </span>
    </div>
    <div v-else class="seat-info sign-out">
      <span> ics{{ seatId }} </span><br />
      <span> - </span>
    </div>`,
  props: { seatId: String, seat: Object },
};
