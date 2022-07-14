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
     * TODO: pcIdを取得できなかった場合の処理を追加
     */
    axios.get("/Messenger/v1/whoami").then((response) => {
      sessionStorage.setItem("pcId", response.data.pcId);
      // ログインしていなかった場合，ログイン画面に遷移
      if(!response.data.isLogin){
        window.location.href = '/Messenger/login.html'
      }
    });
  },
};
