import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import http from "@/common/axios.js";
import router from '@/routers/routers.js'

const houseStore = {
  namespaced: true,
  state: {
    isVisibleCommerce: false,
    commerces: [],

    searchWord: "",

    totalCommerceListCount: 0,
  },
  actions: {
    // 동으로 검색한 목록 가져오기
    getCommerceDongList(context, dong) {
      http
      .get("/houses/dong", { 
        params: {
          searchWord: dong,
        }
      })
      .then( ({data}) => {
        if(data.result == "login") {
          router.push("/login");
        } else {
          // console.log(data);

          context.commit("SET_COMMERCE_LIST", data.list);
          context.commit("SET_COMMERCE_TOTAL_LIST_COUNT", data.count);
        }
      })
      .catch( error => {
        console.log(error);
      });
    },
    
    // 위도,경로 로 검색한 목록 가져오기
    getCommerceLatLngList(context, params) {
      // console.log(params);

      http
      .post("/commerce/latlng", 
        { // json객체로 보냄
          startLng: params.startLng,
          endLng: params.endLng,
          startLat: params.startLat,
          endLat: params.endLat,
        },
      )
      .then( ({data}) => {
        if(data.result == "login") {
          router.push("/login");
        } else {

          context.commit("SET_COMMERCE_LIST", data.list);
          context.commit("SET_COMMERCE_TOTAL_LIST_COUNT", data.count);
        }
      })
      .catch( error => {
        console.log(error);
      });
    },
  },
  getters: {

  },
  mutations: {
    SET_COMMERCE_LIST: (state, commerces) => {
      state.commerces = [];
      state.commerces = commerces;
    },
    SET_COMMERCE_TOTAL_LIST_COUNT: (state, count) => {
      state.totalCommerceListCount = count;
    },
    SET_IS_VISIBLE_COMMERCE: (state, status) => {
      state.isVisibleCommerce = status;
    }
  },
}

export default houseStore;