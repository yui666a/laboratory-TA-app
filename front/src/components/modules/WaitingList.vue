<template>
  <div class="waiting-list-section">
    <div class="waiting-list-area">
      <div v-for="waiting in waitingLi" :key="waiting.studentId">
        <WaitingPerson :waitingPerson="waiting" />
      </div>
    </div>
    <Button :value="buttonText" :onClick="onClickHandButton" />
  </div>
</template>

<script>
import WaitingPerson from "./WaitingPerson.vue";
import Button from "../common/Button.vue";

export default {
  name: "WaitingList",
  components: {
    WaitingPerson,
    Button,
  },
  data() {
    return {
      buttonText: "手を挙げる",
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
  methods: {
    onClickHandButton() {
      // TODO: implement me
      console.log("button was clicked");
    },
  },
};
</script>

<style scoped>
.waiting-list-section {
  width: 20%;
}
</style>
