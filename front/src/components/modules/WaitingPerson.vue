<template>
  <div class="waiting-person">
    <div>
      <span class="waiting-person-pc">{{ waitingPerson.pcName }}</span>
      <span class="waiting-person-time"> 00:00</span>
    </div>
    <div class="waiting-person-student">{{ waitingPerson.studentName }}</div>
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
  padding: 5px;
  margin: 5px;
  border: 1px solid black;
  border-radius: 10px;
  text-align: center;
}
</style>
