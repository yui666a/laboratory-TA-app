export default {
  name: "LoginForm",
  data() {
    return {
      studentId: "",
      password: "",
    };
  },
  template: `  <div id="login-form">
    <div class="title">Sign in</div>

    <form>
      <div class="form" id="id">
        <input
          class="input-text"
          type="text"
          placeholder=""
          name="studentId"
          autofocus=""
          v-model="studentId"
        />
        <label>学籍番号 ID No. (8桁)</label>
        <span class="focus_line"></span>
      </div>

      <div class="form" id="password">
        <input
          class="input-text"
          type="password"
          placeholder=""
          v-model="password"
          maxlength="20"
        />
        <label>パスワード Password</label>
        <span class="focus_line"></span>
      </div>
      <button type="submit" class="signin-button">Sign In</button>
    </form>
  </div>`,
};
