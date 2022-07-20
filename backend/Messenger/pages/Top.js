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
  data() {
    return {
      isStudent: true,
      isMobile: this.getIsMobile(),
    };
  },
  methods: {
    getIsMobile() {
      let userAgent = window.navigator.userAgent.toLowerCase();
      return !(
        userAgent.indexOf("iphone") == -1 &&
        userAgent.indexOf("ipad") == -1 &&
        userAgent.indexOf("android") == -1 &&
        userAgent.indexOf("mobile") == -1
      );
    },
  },
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
