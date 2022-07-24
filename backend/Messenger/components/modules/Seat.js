export default {
  name: "SeatInfo",
  template: `
    <div
      v-if="seat"
      class="seat-info"
      v-bind:class="{
        troubled: seat.helpStatus === 'Troubled' && (!isStudent || myPcId === seatId),
        supporting: seat.helpStatus === 'Supporting' && (!isStudent || myPcId === seatId),
      }"
    >
      <div> ics{{ seatId }} </div>
      <div v-if="isStudent && myPcId !== seatId">
        <span>&nbsp;</span>
      </div>
      <div v-else>
        <span> {{statusMap.get(seat.helpStatus)}} </span>
      </div>
    </div>

    <div v-else class="seat-info sign-out">
      <div> ics{{ seatId }} </div>
      <div><span>&nbsp;</span></div>
    </div>`,
  props: { myPcId: String, seatId: String, seat: Object, isStudent: Boolean },
  data() {
    return {
      statusMap: new Map([
        ["Troubled", "挙手"],
        ["Supporting", "対応中"],
        ["None", "　"],
      ]),
    };
  },
};
