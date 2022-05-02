<template>
  <div class="waiting-list-section">
    <div class="waiting-list-area">
      <div
        v-for="waiting in waitingLi"
        :key="waiting.studentId"
      >
        <WaitingPerson :waitingPerson="waiting" />
      </div>
    </div>
    <div class="hand-button-area">
      <input type="button" class="" value="手を上げる" />
    </div>
  </div>
</template>

<script>
import WaitingPerson from "./WaitingPerson.vue";

export default {
  name: "WaitingList",
  components: {
    WaitingPerson,
  },
  data() {
    return {
      waitingLi: [
        {
          pcName: "ics810",
          studentId: "123456",
          studentName: "安倍　晋三",
        },
        {
          pcName: "ics821",
          studentId: "123457",
          studentName: "ヨシダ　トミオ",
        },
        {
          pcName: "ics831",
          studentId: "123458",
          studentName: "マツシタ　マサヤ",
        },
        {
          pcName: "ics833",
          studentId: "123459",
          studentName: "アソウ　タロウ",
        },
        {
          pcName: "ics844",
          studentId: "123460",
          studentName: "アイソ　ヒトシ",
        },
      ],
    };
  },
};
</script>

<style scoped>

  </style>
