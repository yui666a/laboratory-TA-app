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
      ></div>
      <div v-else class="seat-info mobile sign-out"></div>
      <SeatModal
        v-if="isDisplayedModal"
        :seat="seat"
        @overlyClicked="overlyClicked"
      ></SeatModal>
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
    displayModal(){
      this.isDisplayedModal = true;
    },
  },
};
