export default {
  name: "SeatInfo",
  template: `
    <div v-if="seat" class="seat-info">
      <div> ics{{ seatId }} </div>
      <div>
        <span> {{statusMap.get(seat.helpStatus)}} </span>
      </div>
    </div>

    <div v-else class="seat-info sign-out">
      <div> ics{{ seatId }} </div>
      <div><span>&nbsp;</span></div>
    </div>`,
  props: { seatId: String, seat: Object },
  data() {
    return {
      statusMap: new Map([
        ["Troubled", "挙手"],
        ["Supporting", "対応中"],
        ["None", " "],
      ]),
    };
  },
};
