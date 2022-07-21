export default {
  name: "SeatModal",
  template: `
    <div class="modal-wrapper">
      <div class="overlay" @click="$emit('overlyClicked')"></div>
      <div class="modal-small">
        <!-- TODO: fix pcId -->
        <span class="pcId">{{ seat.pcId }}</span><br />
        <!-- TODO: fix studentName -->
        <!-- <span class="studentName">前田 康弘</span><br />-->
        <button class="support-button" @click="support">行きます！</button>
      </div>
    </div>
    `,
  props: { seat: Object },
  methods: {
    support() {
      // TODO: fix url
      axios
        .post("/Messenger/v1/support/" + pcId.substring(3))
        .then((response) => {
          console.log("v1/support/" + pcId.substring(3) + " success");
          this.$emit("overlyClicked");
        });
    },
  },
};
