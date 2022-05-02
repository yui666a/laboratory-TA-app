<template>
  <div class="waiting-person">
    <div class="waiting-person-pc">{{ waitingPerson.pcName }}</div>
    <div class="waiting-person-student">{{ waitingPerson.studentName }}</div>
    <div class="waiting-person-time">00:00:00</div>
  </div>
</template>

<script>
export default {
  name: "WaitingPerson",
  props: {
    waitingPerson: Object,
  },
  // data() {},
};
</script>

<style scoped>
.waiting-person {
  width: 200px;
  padding: 5px;
  margin: 5px;
  border: 1px solid black;
  border-radius: 10px;
  text-align: center;
}
</style>
