import SeatingChart from "../components/modules/SeatingChart.js";
import WaitingList from "../components/modules/WaitingList.js";

export default {
  name: "TopPage",
  components: {
    SeatingChart,
    WaitingList,
  },
  template: `
  <div class="main">
    <SeatingChart />
    <WaitingList />
  </div>
`,
  beforeCreate: function () {
    /**
     * pcIdをsessionStorageに保存
     */
    axios
      .get("/Messenger/v1/whoami")
      .then((response) => {
        response.data.pcId
          ? sessionStorage.setItem("pcId", response.data.pcId)
          : console.log("pcId is not found");
        // ログインしていなかった場合，ログイン画面に遷移
        // TODO: 必要なくなっている可能性があるため，コメントアウト中．確認後削除する．
        // if (!response.data.isLogin) {
        //   window.location.href = "/Messenger/login.html";
        // }
      })
      .catch((error) => {
        console.log("pcIdを取得できませんでした");
        console.error(error);
      });
  },
};
