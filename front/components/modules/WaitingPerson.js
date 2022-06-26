export default {
  name: "WaitingPerson",
  props: {
    waitingPerson: Object,
  },
  template: `
   <div class="waiting-person">
    <div>
      <span class="waiting-person-pc">{{ waitingPerson.pcName }}</span>
      <span class="waiting-person-time"> 00:00</span>
    </div>
    <div class="waiting-person-student">{{ waitingPerson.studentName }}</div>
  </div>
  `,
  // data() {},
};
