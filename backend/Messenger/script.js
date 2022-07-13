import TopPage from './pages/Top.js';

//ルートコンポーネント
const RootComponent = {
  components: {
    TopPage,
  },
	template: `<TopPage/>`,
};

//Vueのインスタンス
export const app = Vue.createApp(RootComponent);
app.component("TopPage", TopPage);
//アプリケーションのマウント
const elem = document.querySelector("#app");
app.mount(elem);
