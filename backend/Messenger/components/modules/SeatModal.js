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
        <button class="support-button" @click="support">行きます！</button>
      </div>
    </div>
    `,
  props: { seatId: String, seat: Object },
  methods: {
    support() {
      // TODO: fix url
      axios
        .post("/Messenger/v1/support/" + this.seatId)
        .then((response) => {
          this.$emit("overlyClicked");
        });
    },
  },
};
