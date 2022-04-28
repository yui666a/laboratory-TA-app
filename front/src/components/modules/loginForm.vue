<template>
  <div id="login-form">
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
  </div>
</template>

<script>
export default {
  name: "LoginForm",
  data() {
    return {
      studentId: "",
      password: "",
    };
  },
};
</script>

<style scoped>
#login-form {
  width: 280px;
  padding: 10px;
  border-radius: 30px;
  box-shadow: 0 0 10px gray;
  text-align: center;
}

.title {
  font-size: 3rem;
}

.form {
  text-align: start;
  margin: 0 1rem 1rem;
}

.signin-button {
  width: 80%;
  font-size: 1.5em;
  height: 2em;
  border-radius: 1em;
  background: rgb(29, 172, 194);
  color: white;
  border: none;
}

/* 以下，テキストボックスのアニメーション */
.form {
  position: relative;
  width: 80%;
  margin: 40px 3%;
}

.form input[type="text"],
.form input[type="password"] {
  font: 15px/24px sans-serif;
  box-sizing: border-box;
  width: 100%;
  letter-spacing: 1px;
  padding-left: 4em;
}

.form input[type="text"]:focus,
.form input[type="password"]:focus {
  outline: none;
}

.input-text {
  padding: 4px 0;
  border: 0;
  border-bottom: 1px solid #1b2538;
  background-color: transparent;
}

.input-text ~ .focus_line {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 0;
  height: 2px;
  transition: 0.4s;
  background-color: rgb(29, 172, 194);
}

.input-text:focus ~ .focus_line,
.form.input-text ~ .focus_line {
  width: 100%;
  transition: 0.4s;
}

.input-text ~ label {
  position: absolute;
  z-index: -1;
  top: 4px;
  left: 0;
  width: 100%;
  transition: 0.3s;
  letter-spacing: 0.5px;
  color: #aaaaaa;
}

.input-text:focus ~ label,
.form.input-text ~ label {
  font-size: 12px;
  top: -16px;
  transition: 0.3s;
  color: rgb(29, 172, 194);
}
</style>
