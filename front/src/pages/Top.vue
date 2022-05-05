<template>
  <div class="main">
    <SeatingChart />
    <WaitingList />
  </div>
</template>

<script>
import SeatingChart from "../components/modules/SeatingChart.vue";
import WaitingList from "../components/modules/WaitingList.vue";

export default {
  name: "TopPage",
  components: {
    SeatingChart,
    WaitingList,
  },
};
</script>

<style>
.main {
  width: 100%;
  display: flex;
}
</style>
