import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import http from "@/common/axios.js";
import router from '@/routers/routers.js'

// 에러 : module namespace not found in mapActions(): houseStore
// => Vuex를 생성해서 넘겨주는 것이 아닌, 객체를 생성해서, store에 module에서 가져다 붙이는 방식
// export default new Vuex.Store({ : 이 처럼 사용하면 에러

const houseStore = {
  namespaced: true,
  state: {
    currentHouseIndex: -1, // 현재 선택된 집의 번호
    houses: [],
    housesDeal: [],
    housesRand: [],

    limit: 30,
    offset: 0,
    searchWord: "",

    totalHouseListCount: 0,
    totalHouseDealListCount: 0,
  },
  actions: {
    // 그냥 검색 목록 가져오기
    getHouseList(context) {
      http
      .get("/houses", {
        // get query string
        params: {
          limit: context.state.limit,
          offset: context.state.offset,
          searchWord: context.state.searchWord,
        },
      })
      .then( ({data}) => {
        if(data.result == "login") {
          router.push("/login");
        } else {
          // console.log("Action : 아파트 목록 호출");
          // console.log(data);
          context.commit("SET_HOUSE_LIST", data.list);
          context.commit("SET_HOUSE_TOTAL_LIST_COUNT", data.count);
        }
      })
      .catch( error => {
        console.log(error);
      });
    },

    // 동으로 검색한 목록 가져오기
    getHouseDongList(context, params) {
      // console.log("getHouseDongList 호출됨")
      // console.log(params.dongWord);
      // console.log(params.order);
      // console.log(params.orderColumn);

      http
      .post("/houses/dong", 
        { // json객체로 보냄
          gugunCode: params.gugunCode,
          searchWord: params.dongWord,
          order: params.order,
          orderColumn: params.orderColumn,
        },
      )
      .then( ({data}) => {
        if(data.result == "login") {
          router.push("/login");
        } else {
          // console.log(data);

          if(data.count === 0) {
            context.commit("SET_HOUSE_LIST", [{ 
              img : "기본이미지.jpg", 
              AptName : "검색된 매물이 없습니다.",
            },]);
            context.commit("SET_HOUSE_TOTAL_LIST_COUNT", data.count);
            return;
          }

          context.commit("SET_HOUSE_LIST", data.list);
          context.commit("SET_HOUSE_TOTAL_LIST_COUNT", data.count);
        }
      })
      .catch( error => {
        console.log(error);
      });
    },
    // 거래 내역 가져오기
    getHouseDealList(context, houseNo) {
      http
      .get(`/houses/${houseNo}`, {
        params: {
          no: houseNo,
        },
      })
      .then( ({data}) => {
        if(data.result == "login") {
          router.push("/login");
        } else {
          //console.log(data);

          context.commit("SET_HOUSE_DEAL_LIST", data.dealList);
          context.commit("SET_HOUSE_DEAL_TOTAL_LIST_COUNT", data.count);
        }
      })
      .catch( error => {
        console.log(error);
      });
    },
    // 판매중이거나 취소됨 상태의 별점 4점이상의 랜덤하게 4개
    getHouseListRand(context, limit) {
      http
      .get(`/houses/rand${limit}`, {
        params: {
          searchWord: 4.0,
          limit: 4,
        },
      })
      .then( ({data}) => {
        if(data.result == "login") {
          router.push("/login");
        } else {
          //console.log(data);

          context.commit("SET_HOUSE_LIST_RAND", data.houseDealExpandList);
        }
      })
      .catch( error => {
        console.log(error);
      });
    },

    // 상태로 검색하기
    getHouseListStatus(context, params) {
      http
      .post("/houses/status", {
        gugunCode: params.gugunCode,
        searchWord: params.dongWord, // 동
        status: params.status, // 상태
        order: params.order,
      })
      .then( ({data}) => {
        if(data.result == "login") {
          router.push("/login");
        } else {
          // console.log("Action : 아파트 목록 호출");
          // console.log(data);
          if(data.count === 0) {
            context.commit("SET_HOUSE_LIST", [{ 
              img : "기본이미지.jpg", 
              AptName : "검색된 매물이 없습니다.",
            },]);
            context.commit("SET_HOUSE_TOTAL_LIST_COUNT", data.count);
            return;
          }

          context.commit("SET_HOUSE_LIST", data.list);
          context.commit("SET_HOUSE_TOTAL_LIST_COUNT", data.count);
        }
      })
      .catch( error => {
        console.log(error);
      });
    },
    // 최근 거래목록 2개 (현재 안씀)
    getHouseDealRecentList(context, no) {
      http
      .get(`/houses/recent${no}`, {
        params: {
          no: no,
        },
      })
      .then( ({data}) => {
        if(data.result == "login") {
          router.push("/login");
        } else {
          console.log(data);
          context.commit("SET_HOUSE_LIST_RAND_DEAL", data.dealList);
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
    SET_SEARCH_WORD: (state, searchWord) => {
      state.searchWord = searchWord;
    },
    SET_HOUSE_LIST: (state, houses) => {
      state.houses = [];
      state.houses = houses;
    },
    SET_HOUSE_DEAL_LIST: (state, housesDeal) => {
      state.housesDeal = [];
      state.housesDeal = housesDeal;
    },
    SET_HOUSE_LIST_RAND: (state, housesRand) => {
      state.housesRand = [];
      state.housesRand = housesRand;
    },
    SET_HOUSE_TOTAL_LIST_COUNT: (state, count) => {
      state.totalHouseListCount = count;
    },
    SET_HOUSE_DEAL_TOTAL_LIST_COUNT: (state, count) => {
      state.totalHouseDealListCount = count;
    },
    SET_CURRENT_HOUSEL_INDEX: (state, houseIndex) => {
      state.currentHouseIndex = houseIndex;
    }
  },
}

export default houseStore;