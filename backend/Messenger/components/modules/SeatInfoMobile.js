export default {
  name: "SeatInfoMobile",
  template: `
    <div
      v-if="seat"
      class="seat-info mobile"
      v-bind:class="{
        troubled: seat.helpStatus === 'Troubled',
        supporting: seat.helpStatus === 'Supporting',
      }"
    >
    </div>

    <div v-else class="seat-info mobile sign-out"></div>`,
  props: { seatId: String, seat: Object, isStudent: Boolean },
};
