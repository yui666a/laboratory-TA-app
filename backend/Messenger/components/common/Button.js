export default {
  name: "ButtonComponent",
  props: {
    value: String,
    onClick: Function,
    buttonIsClicked: String,
  },
  template: `<div class="button-wrapper">
    <input type="button"
      @click="onClick()"
      :value="value"
      :class="[buttonIsClicked === 'clicked' ? 'clicked' : '']"
    />
  </div>`,
};
