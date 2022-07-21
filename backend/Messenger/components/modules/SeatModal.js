export default {
  name: "SeatModal",
  template: `
    <div>
      <div class="overlay" @click="$emit('overlyClicked')"></div>
      <div class="modal-small">
        <span class="pcId">ics804</span>
        <span class="studentName">前田 康弘</span>
        <button class="support-button" @click="support">行きます！</button>
      </div>
    </div>
    `,
  props: { seat: Object },
  methods: {
    support: function (event) {
      // TODO: fix url
      axios.post("/Messenger/v1/call/" + pcId.substring(3)).then((response) => {
        console.timeLog("成功！TODO:実装してください！");
      });
    },
  },
};
