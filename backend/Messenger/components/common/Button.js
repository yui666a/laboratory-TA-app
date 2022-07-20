export default {
  name: "ButtonComponent",
  props: {
    value: String,
    onClick: Function,
    variant: String,
  },
  template: `<div class="button-wrapper">
    <input type="button"
      @click="onClick()"
      :value="value"
      :class="[variant === 'primary' ? 'primary' : 'secondary']"
    />
  </div>`,
};
