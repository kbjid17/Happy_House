import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import http from "@/common/axios.js";
import router from '@/routers/routers.js'

const user = {
  namespaced: true,
  state: {
    login: {
      isLogin: false,
      userSeq:"",
      userEmail: "ml2439@ssafy.com",
      userPassword: "!a12345678",
      userName: "",
      userProfileImageUrl: "",
      userClsf: "-1"
    },
    register: {
      isRegister: false,
      userSeq:"",
      userEmail: "hong@naver.com",
      userPassword: "1234",
      userName: "홍길동",
      userProfileImageUrl: "",
      userClsf: ""
    },
    config: {
      isConfig: false,
      userSeq:"",
      userEmail: "hong@naver.com",
      userPassword: "1234",
      userName: "홍길동",
      userProfileImageUrl: "",
      userClsf: ""
    },
    deleteMember: {
      userSeq:"",
      userEmail: "hong@naver.com",
      userPassword: "1234",
      userName: "홍길동",
      userProfileImageUrl: "",
    },
    userInterestList: [],
  },
  mutations: {
    SET_LOGIN(state, payload) {
      console.log(payload);
      state.login.isLogin = payload.isLogin;
      state.login.userSeq = payload.userSeq;
      state.login.userName = payload.userName;
      state.login.userClsf = payload.userClsf;
      state.login.userProfileImageUrl = payload.userProfileImageUrl;
      console.log(state.login.userClsf);
    },
    SET_REGISTER(state, payload) {
      state.register.userName = payload.userName;
      state.register.userEmail = payload.userEmail;
      state.register.userPassword = payload.userPassword;
      state.register.userProfileImageUrl = payload.userProfileImageUrl;
      state.register.userClsf = payload.userClsf;
    },
    SET_CONFIG(state, payload) {
      state.config.userName = payload.userName;
      state.config.userEmail = payload.userEmail;
      state.config.userPassword = payload.userPassword;
      state.config.userProfileImageUrl = payload.userProfileImageUrl;
      state.config.userClsf = payload.userClsf;

    },
    LOGOUT(state) {
      state.login.isLogin = false;
      state.login.userName = "";
      state.login.userPassword = "";
      state.login.userProfileImageUrl = "";
      state.login.userClsf = "-1";
      console.log(state.login.userClsf);
    },
    SET_DELETE(state,payload) {
      state.deleteMember.userSeq = payload.userSeq;
    },
    SET_USER_INTEREST_LIST(state, payload) {
      state.userInterestList = payload;
    }
  },
  actions: {
    logout({commit}) {
      commit('LOGOUT');
    },

    registUserInterestHouse(context, houseNo) {
      http
      .post(`/interests`, {
        no: houseNo,
        userSeq: this.login.userSeq,
      })
      .then( ({data}) => {
        if(data.result == "login") {
          router.push("/login");
        } else {
          //console.log("관심 아파트 등록");
          context.commit();
        }
      })
      .catch( error => {
        console.log(error);
      });
    },

    deleteUserInterestHouse(context, houseNo) {
      http
      .delete(`/interests`, {
        no: houseNo,
        userSeq: this.login.userSeq,
      })
      .then( ({data}) => {
        if(data.result == "login") {
          router.push("/login");
        } else {
          console.log("관심 아파트 삭제");
        }
      })
      .catch( error => {
        console.log(error);
      });
    },

    selectUserInterestHouse(context, userNo) {
      console.log("----------------------");
      console.log(userNo);

      http
      .get(`/interests`, {
        params: {
          userSeq: userNo,
        },
      })
      .then( ({data}) => {
        if(data.result == "login") {
          router.push("/login");
        } else {
          // console.log(data.list);
          context.commit("SET_USER_INTEREST_LIST", data.list);
        }
      })
      .catch();
    }
    
  },
  getters : {
    isLogin: function (state) {
      return state.login.isLogin;
    },
    isRegister: function (state) {
      return state.register.isRegister;
    },
  },
}


export default user;
