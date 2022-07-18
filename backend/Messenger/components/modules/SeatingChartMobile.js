import SeatInfoMobile from "./SeatInfoMobile.js";

export default {
  name: "SeatingChartMobile",
  components: {
    SeatInfoMobile,
  },
  template: `  <div class="seating-chart-section">
    <div class="seating-chart-area">
      <div v-for="column of 9" :key="column" class="seat-column">
        <div
          v-for="row of 8"
          :key="row"
          class="seat-row"
          :set="(seat = '8' + ((column - 1) * 8 + row).toString().padStart(2, '0'))"
        >
          <div
            style="width: calc(90vw / 10); margin: 2px"
            v-if="row === 1 && column !== 2 && column !== 4 && column !== 9"
          ></div>
          <SeatInfoMobile
            :seatId="seat"
            :seat="seats[seat]"
            :isStudent="isStudent"
            v-bind:style="[column === 9 && row === 8 ? 'display: none;' : '']"
          />
          <div
            style="width: calc(90vw / 10); margin: 2px"
            v-if="row === 1 && (column === 2 || column === 4)"
          ></div>
        </div>
      </div>
    </div>
  </div>
  `,
  data() {
    return {
      seats: {},
      isStudent: true,
    };
  },
  mounted() {
    // 1秒ごとに更新
    setInterval(() => {
      axios.get("/Messenger/v1/active-seats").then((response) => {
        const activeSeats = response.data.filter((seat) => seat.isLogin);
        let tmpSeats = {};
        activeSeats.map((seat) => {
          tmpSeats = { ...tmpSeats, [seat.pcId.substring(3)]: seat };
        });
        this.seats = tmpSeats;
      });
    }, 1000);

    axios
      .get("/Messenger/v1/whoami")
      .then((response) => {
        this.isStudent = response.data.isStudent;
      })
      .catch((error) => {
        // TypeError: Cannot read properties of null (reading 'isStudent')
        this.isStudent = false;
      });
  },
};
