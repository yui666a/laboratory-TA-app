<template>
  <SeatingChart />
  <!-- TODO: remove comment after feature/004-waiting-list-design is merged -->
  <!-- <WaitingList /> -->
</template>

<script>
import SeatingChart from "../components/modules/SeatingChart.vue";
// import WaitingList from "front/src/components/modules/waitingList.vue";

export default {
  name: "TopPage",
  components: {
    SeatingChart,
    // WaitingList,
  },
};
</script>

<style></style>
