<template>
  <div>
    <!-- ======== sidebar-nav start =========== -->
    <aside class="sidebar-nav-wrapper" ref="aside">
      <div class="navbar-logo">
        <a @click="moveHome" href="#">
          <!-- 로고랑 타이틀 바꾸기 -->
          <img src="@/assets/images/logo/logo.png" alt="logo" />
        </a>
        <hr>
      </div>
      <nav class="sidebar-nav">
        <ul>
          <li v-if="!login.isLogin" class="nav-item nav-item-has-children">
            <a
              href="#"
              class="collapsed"
              data-bs-toggle="collapse"
              data-bs-target="#ddmenu_1"
              aria-controls="ddmenu_1"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span class="icon">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="22"
                  height="22"
                  fill="none"
                  viewBox="0 0 16 16"
                  class="bi bi-box-arrow-in-right"
                >
                  <path
                    fill-rule="evenodd"
                    d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0v-2z"
                  />
                  <path
                    fill-rule="evenodd"
                    d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"
                  />
                </svg>
              </span>
              <span class="text">Sign in</span>
            </a>
            <ul id="ddmenu_1" class="collapse dropdown-nav">
              <li>
                <a @click="moveLogin" href="#"> Sign In </a>
              </li>
              <li>
                <a @click="moveRegister" href="#"> Sign Up </a>
              </li>
            </ul>
          </li>
          <span v-if="!login.isLogin" class="divider"><hr /></span>

          <li class="nav-item">
            <a @click="moveNotice" href="#">
              <span class="icon">
                <svg
                  width="22"
                  height="22"
                  fill="none"
                  viewBox="0 0 16 16"
                  class="bi bi-bell"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <path
                    d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zM8 1.918l-.797.161A4.002 4.002 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4.002 4.002 0 0 0-3.203-3.92L8 1.917zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5.002 5.002 0 0 1 13 6c0 .88.32 4.2 1.22 6z"
                  />
                </svg>
              </span>
              <span class="text">Notifications</span>
            </a>
          </li>
          <span class="divider"><hr /></span>

          <li class="nav-item">
            <a @click="moveHouseDetail" href="#">
              <span class="icon">
                <svg
                  width="22"
                  height="22"
                  viewBox="0 0 16 16"
                  fill="none"
                  class="bi bi-layout-text-window"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <path
                    d="M3 6.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm0 3a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm.5 2.5a.5.5 0 0 0 0 1h5a.5.5 0 0 0 0-1h-5z"
                  />
                  <path
                    d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm12 1a1 1 0 0 1 1 1v1H1V2a1 1 0 0 1 1-1h12zm1 3v10a1 1 0 0 1-1 1h-2V4h3zm-4 0v11H2a1 1 0 0 1-1-1V4h10z"
                  />
                </svg>
              </span>
              <span class="text"> House Sales </span>
            </a>
          </li>
          <span class="divider"><hr /></span>

          <li class="nav-item">
            <a @click="moveHouseList" href="#">
              <span class="icon">
                <svg
                  width="22"
                  height="22"
                  fill="none"
                  class="bi bi-map"
                  viewBox="0 0 16 16"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <path
                    fill-rule="evenodd"
                    d="M15.817.113A.5.5 0 0 1 16 .5v14a.5.5 0 0 1-.402.49l-5 1a.502.502 0 0 1-.196 0L5.5 15.01l-4.902.98A.5.5 0 0 1 0 15.5v-14a.5.5 0 0 1 .402-.49l5-1a.5.5 0 0 1 .196 0L10.5.99l4.902-.98a.5.5 0 0 1 .415.103zM10 1.91l-4-.8v12.98l4 .8V1.91zm1 12.98 4-.8V1.11l-4 .8v12.98zm-6-.8V1.11l-4 .8v12.98l4-.8z"
                  />
                </svg>
              </span>
              <span class="text">Map</span>
            </a>
          </li>
          <span class="divider"><hr /></span>

          <li class="nav-item">
            <a @click="moveBoard" href="#">
              <span class="icon">
                <svg
                  width="22"
                  height="22"
                  fill="white"
                  class="bi bi-file-text"
                  viewBox="0 0 16 16"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <path
                    d="M5 4a.5.5 0 0 0 0 1h6a.5.5 0 0 0 0-1H5zm-.5 2.5A.5.5 0 0 1 5 6h6a.5.5 0 0 1 0 1H5a.5.5 0 0 1-.5-.5zM5 8a.5.5 0 0 0 0 1h6a.5.5 0 0 0 0-1H5zm0 2a.5.5 0 0 0 0 1h3a.5.5 0 0 0 0-1H5z"
                  />
                  <path
                    d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2zm10-1H4a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1z"
                  />
                </svg>
              </span>
              <span class="text">Board</span>
            </a>
          </li>
          <span class="divider"><hr /></span>

          <li class="nav-item">
            <a @click="moveChat" href="#">
              <span class="icon">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="22"
                  height="22"
                  fill="white"
                  class="bi bi-question-circle"
                  viewBox="0 0 16 16"
                >
                  <path
                    d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"
                  />
                  <path
                    d="M5.255 5.786a.237.237 0 0 0 .241.247h.825c.138 0 .248-.113.266-.25.09-.656.54-1.134 1.342-1.134.686 0 1.314.343 1.314 1.168 0 .635-.374.927-.965 1.371-.673.489-1.206 1.06-1.168 1.987l.003.217a.25.25 0 0 0 .25.246h.811a.25.25 0 0 0 .25-.25v-.105c0-.718.273-.927 1.01-1.486.609-.463 1.244-.977 1.244-2.056 0-1.511-1.276-2.241-2.673-2.241-1.267 0-2.655.59-2.75 2.286zm1.557 5.763c0 .533.425.927 1.01.927.609 0 1.028-.394 1.028-.927 0-.552-.42-.94-1.029-.94-.584 0-1.009.388-1.009.94z"
                  />
                </svg>
              </span>
              <span class="text">Service</span>
            </a>
          </li>
          <span class="divider"><hr /></span>

        </ul>
      </nav>
    </aside>
    <div class="overlay" ref="overlay"></div>
    <!-- ======== sidebar-nav end =========== -->
  </div>
</template>

<script>
const userStore = "userStore";
import { mapState } from 'vuex';

import Vue from 'vue';
import VueAlertify from 'vue-alertify';
 
Vue.use(VueAlertify);

export default {
  computed: {
    ...mapState(userStore,["login"]),
  },
  methods: {
    alertifyError: function() {
      this.$alertify.error('로그인하고 이용해주시길 바랍니다.');
    },

    moveHome() {
      // 같은 경로로 가려고 하면 에러가 난다. 같은 경로로 못가게
      if (this.$route.path !== "/home") this.$router.push("/home");
    },
    moveLogin() {
      if (this.$route.path !== "/login") this.$router.push("/login");
    },
    moveRegister() {
      if (this.$route.path !== "/register") this.$router.push("/register");
    },
    moveNotice() {
      if ( !this.login.isLogin ) {
        this.alertifyError();
        this.$router.push("/login");
      }
      else if(this.$route.path !== "/notices") this.$router.push("/notices");
    },
    moveHouseDetail() {
      if ( !this.login.isLogin ) {
        this.alertifyError();
        this.$router.push("/login");
      }
      else if (this.$route.path !== "/houseDetail") this.$router.push("/houseDetail");
    },
    moveHouseList() {
      if ( !this.login.isLogin ) {
        this.alertifyError();
        this.$router.push("/login");
      }
      else if (this.$route.path !== "/houseList") this.$router.push("/houseList");
    },
    moveBoard() {
      if ( !this.login.isLogin ) {
        this.alertifyError();
        this.$router.push("/login");
      }
      else if (this.$route.path !== "/boards") this.$router.push("/boards");
    },
    moveChat() {
      if ( !this.login.isLogin ) {
        this.alertifyError();
        this.$router.push("/login");
      }
      else if (this.$route.path !== "/chat") this.$router.push("/chat");
    },
  },
};
</script>

<style></style>
