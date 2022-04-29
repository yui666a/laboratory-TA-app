<template>
  <div class="waiting-list-section">
    <div class="waiting-list-area">
      <!-- vue for statement -->
      <div
        class="waiting-person"
        v-for="waiting in waitingLi"
        :key="waiting.studentId"
      >
        <div class="waiting-person-pc"> {{ waiting.pcName }} </div>
        <div class="waiting-person-student"> {{ waiting.studentName }}</div>
      </div>
    </div>
    <div class="hand-button-area">
      <input type="button" class="" value="手を上げる" />
    </div>
  </div>
</template>

<script>
export default {
  name: "WaitingList",
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

<style scoped></style>
