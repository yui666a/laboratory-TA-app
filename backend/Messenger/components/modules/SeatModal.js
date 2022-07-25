export default {
  name: "SeatModal",
  template: `
    <div class="modal-wrapper">
      <div class="overlay" @click="$emit('overlyClicked')"></div>
      <div class="modal-small">
        <!-- TODO: fix pcId -->
        <span class="pcId">ics{{ seatId }}</span><br />
        <!-- TODO: fix studentName -->
        <!-- <span class="studentName">前田 康弘</span><br />-->
        <button class="support-button" @click="support"> {{ buttonText }} </button>
      </div>
    </div>
    `,
  props: { seatId: String, seat: Object },
  data() {
    return {
      buttonText:
        seat.helpStatus === "Supporting" ? "対応しました" : "行きます！",
    };
  },
  methods: {
    support() {
      // TODO: fix url
      axios.post("/Messenger/v1/support/" + this.seatId).then((response) => {
        // 送信後，自分の状態を確認する
        let selectedSeat = response.data.filter(
          (seat) => seat.pcId === seatId
        )[0];
        this.buttonText =
          selectedSeat.helpStatus === "Supporting"
            ? "対応しました"
            : "行きます！";
        this.$emit("overlyClicked");
      });
    },
  },
};
