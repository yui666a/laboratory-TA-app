export default {
  name: "SeatInfo",
  template: `
    <div v-if="seat" class="seat-info">
      <div> ics{{ seatId }} </div>
      <div v-if="seat.handStatus">
        <span> 挙手 </span>
      </div>
      <div v-else><span>&nbsp;</span></div>
    </div>

    <div v-else class="seat-info sign-out">
      <div> ics{{ seatId }} </div>
      <div><span>&nbsp;</span></div>
    </div>`,
  props: { seatId: String, seat: Object },
};
