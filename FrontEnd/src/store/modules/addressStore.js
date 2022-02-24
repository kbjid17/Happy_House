import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import http from "@/common/axios.js";

// 에러 : module namespace not found in mapActions(): houseStore
// => Vuex를 생성해서 넘겨주는 것이 아닌, 객체를 생성해서, store에 module에서 가져다 붙이는 방식
// export default new Vuex.Store({ : 이 처럼 사용하면 에러

const houseStore = {
  namespaced: true,
  state: {
    dongName: "",
    dongLat: "",
    dongLng: "",
    sidos: [{ value: null, text: "시/도" }],
    guguns: [{ value: null, text: "구/군" }],
    dongs: [{ value: null, text: "동" }],
  },
  actions: {
    getSidoList(context) {
      http
      .get('/sido')
      .then( ({data}) => {
        //console.log(data);
        context.commit("SET_SIDO_LIST", data);
      })
      .catch( error => {
        console.log(error);
      });
    },
    getGugunList(context, sidoCode) {
      http
      .get(`/gugun/${sidoCode}`)
      .then( ({data}) => {
        //console.log(data);
        context.commit("SET_GUGUN_LIST", data);
      })
      .catch( error => {
        console.log(error);
      });
    },
    getDongList(context, gugunCode) {
      http
      .get(`/dong/${gugunCode}`)
      .then( ({data}) => {
        // console.log(data);
        context.commit("SET_DONG_LIST", data);
      })
      .catch( error => {
        console.log(error);
      });
    },
    getDongLatLng(context, dongName) {
      http
      .get(`/latlng/${dongName}`)
      .then( ({data}) => {
        // console.log(data);
        context.commit("SET_DONG_NAME", data.dongName);
        context.commit("SET_DONG_LAT", data.lat);
        context.commit("SET_DONG_LNG", data.lng);
      })
      .catch( error => {
        console.log(error);
      });
    },
  },
  mutations: {
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "시/도" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "구/군" }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ value: null, text: "동" }];
    },

    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },

    SET_DONG_NAME: (state, dongName) => {
      state.dongName = dongName;
    },
    SET_DONG_LAT: (state, dongLat) => {
      state.dongLat = dongLat;
    },
    SET_DONG_LNG: (state, dongLng) => {
      state.dongLng = dongLng;
    },
  },
}

export default houseStore;