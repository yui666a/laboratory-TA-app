import SeatingChart from "../components/modules/SeatingChart.js";
import SeatingChartMobile from "../components/modules/SeatingChartMobile.js";
import WaitingList from "../components/modules/WaitingList.js";

export default {
  name: "TopPage",
  components: {
    SeatingChart,
    SeatingChartMobile,
    WaitingList,
  },
  template: `
  <div class="main mobile" v-if="!isStudent && isMobile">
    <SeatingChartMobile />
  </div>
  <div class="main" v-else>
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
  mounted() {
    axios
      .get("/Messenger/v1/whoami")
      .then((response) => {
        this.isStudent = response.data.isStudent;
      })
      .catch((error) => {
        // TODO basically, should true
        this.isStudent = false;
        console.error(error);
      });
  },
};
