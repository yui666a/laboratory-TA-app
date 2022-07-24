import SeatModal from "./SeatModal.js";

export default {
  name: "SeatInfoMobile",
  components: {
    SeatModal,
  },
  template: `
    <div>
      <div
        v-if="seat"
        class="seat-info mobile"
        v-bind:class="{
          troubled: seat.helpStatus === 'Troubled',
          supporting: seat.helpStatus === 'Supporting',
        }"
        @click="displayModal"
      >
        <SeatModal
          v-if="isDisplayedModal"
          :seat="seat"
          :seatId="seatId"
          @overlyClicked="overlyClicked"
        ></SeatModal>
      </div>
      <div v-else class="seat-info mobile sign-out"></div>
    </div>`,
  props: { seatId: String, seat: Object, isStudent: Boolean },
  data() {
    return {
      isDisplayedModal: false,
    };
  },
  methods: {
    overlyClicked() {
      console.timeLog("オーバーレイClicked");
      this.isDisplayedModal = false;
    },
    displayModal() {
      this.isDisplayedModal = true;
    },
  },
};
