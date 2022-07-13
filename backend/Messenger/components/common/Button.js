export default {
  name: "ButtonComponent",
  props: {
    value: String,
    onClick: Function,
  },
  template: `<div class="button-wrapper">
    <input type="button" @click="onClick()" :value="value" />
  </div>`,
};
