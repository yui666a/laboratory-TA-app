import WaitingPerson from "./WaitingPerson.js";
import Button from "../common/Button.js";

export default {
  name: "WaitingList",
  components: {
    WaitingPerson,
    Button,
  },
  template: `
    <div class="waiting-list-section">
      <div class="waiting-list-area">
        <div v-for="waiting in waitingLi" :key="waiting.studentId">
          <WaitingPerson :waitingPerson="waiting" />
        </div>
      </div>
      <div class="waiting-list-button-area">
        <Button :value="buttonText" :onClick="onClickHandButton" :buttonIsClicked="buttonIsClicked"/>
      </div>
    </div>
  `,
  data() {
    return {
      buttonText: "手を挙げる",
      buttonIsClicked: "",
      // waitingLi: [
      //   {
      //     pcName: "ics810",
      //     studentId: "123456",
      //     studentName: "安倍　晋三",
      //   },
      //   {
      //     pcName: "ics821",
      //     studentId: "123457",
      //     studentName: "ヨシダ　トミオ",
      //   },
      //   {
      //     pcName: "ics831",
      //     studentId: "123458",
      //     studentName: "マツシタ　マサヤ",
      //   },
      //   {
      //     pcName: "ics833",
      //     studentId: "123459",
      //     studentName: "アソウ　タロウ",
      //   },
      //   {
      //     pcName: "ics844",
      //     studentId: "123460",
      //     studentName: "アイソ　ヒトシ",
      //   },
      // ],
    };
  },
  methods: {
    onClickHandButton() {
      const pcId = sessionStorage.getItem("pcId");
      axios.post("/Messenger/v1/call/" + pcId.substring(3)).then((response) => {
        // 送信後，自分の状態を確認する
        let mySeat = response.data.filter((seat) => seat.pcId === pcId)[0];
        if (mySeat.pcId == pcId) {
          this.buttonText =
            mySeat.helpStatus !== "None" ? "手をさげる" : "手を挙げる";
          this.buttonIsClicked = mySeat.helpStatus === "None" ? "" : "clicked";
        }
      });
    },
  },
  mounted() {
    // 3秒ごとに更新
    setInterval(() => {
      axios
        .get("/Messenger/v1/whoami")
        .then((response) => {
          this.buttonText =
            response.data.helpStatus !== "None" ? "手を挙げる" : "手をさげる";
          this.buttonIsClicked =
            response.data.helpStatus === "None" ? "" : "clicked";
        })
        .catch((error) => {
          console.error(error);
        });
    }, 3000);
  },
};
