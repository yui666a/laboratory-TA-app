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
  mounted: function () {
    /**
     * pcIdをsessionStorageに保存
     * TODO: pcIdを取得できなかった場合の処理を追加
     */
    axios.get("/Messenger/v1/whoami").then((response) => {
      const pcId = response.data.pcId;
      sessionStorage.setItem("pcId", pcId);
    });
  },
};
