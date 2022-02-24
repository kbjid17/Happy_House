import Vue from "vue";
import App from "./App.vue";
import router from "./routers/routers.js";
import store from "./store/store.js";
// template에 포함된 bootstrap 사용(npm으로 설치한 bootstrap 사용하니 디자인 적용이 안됨.)
import "./assets/css/bootstrap.min.css";
import "./assets/css/lineicons.css";
import "./assets/css/materialdesignicons.min.css";
import "./assets/css/fullcalendar.css";
import "./assets/css/main.css";
// template에 포함된 javascript 사용
import "./assets/js/bootstrap.bundle.min.js";

//소켓 통신을 위해 추가
import VueMaterial from 'vue-material' 
import Directives from '../plugin/directives'
import io from "socket.io-client"

const socket = io('http://localhost:3000');

Vue.prototype.$socket = socket; 

Vue.config.productionTip = false;
Vue.use(VueMaterial)
Vue.use(Directives)
new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
