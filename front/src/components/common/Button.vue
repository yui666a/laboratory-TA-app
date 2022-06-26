<template>
  <div class="button-wrapper">
    <input type="button" @click="onClick()" :value="value" />
  </div>
</template>

<script>
export default {
  name: "ButtonComponent",
  props: {
    value: String,
    onClick: Function
  },
};
</script>

<style scoped>
input[type="button"]{
  /* width: 100%; */
  width: 200px;
  height: 3em;
  border-radius: 0.5em;
  border:none;
  background: #57f;
}
</style>
