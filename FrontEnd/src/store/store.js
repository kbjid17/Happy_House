import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

// import talkStore from "@/store/modules/talkStore.js";
import userStore from "@/store/modules/userStore.js";
import noticeStore from "@/store/modules/noticeStore.js";
import boardStore from "@/store/modules/boardStore.js";
import houseStore from "@/store/modules/houseStore.js";
import addressStore from "@/store/modules/addressStore.js"
import commerceStore from "@/store/modules/commerceStore.js"
import newsStore from "@/store/modules/newsStore.js"

export default new Vuex.Store({
  modules: {
    // talkStore,
    boardStore,
    noticeStore,
    userStore,
    houseStore,
    addressStore,
    commerceStore,
    newsStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    })
  ],
  
  state: {
    board: {
        // pagination
      listRowCount: 10,
      pageLinkCount: 10,
      currentPageIndex: 1,

      totalListItemCount: 0,
    }
      
  },

  getters : {
    // pagination
    getPageCount: function (state) {
      return Math.ceil(
        state.board.totalListItemCount / state.board.listRowCount
      );
    },
    getStartPageIndex: function (state) {
      if (state.board.currentPageIndex % state.board.pageLinkCount == 0) {
        //10, 20...맨마지막
        return (
          (state.board.currentPageIndex / state.board.pageLinkCount - 1) *
            state.board.pageLinkCount +
          1
        );
      } else {
        return (
          Math.floor(state.board.currentPageIndex / state.board.pageLinkCount) *
            state.board.pageLinkCount +
          1
        );
      }
    },
    getEndPageIndex: function (state, getters) {
      let ret = 0;
      if (state.board.currentPageIndex % state.board.pageLinkCount == 0) {
        //10, 20...맨마지막
        ret =
          (state.board.currentPageIndex / state.board.pageLinkCount - 1) *
            state.board.pageLinkCount +
          state.board.pageLinkCount;
      } else {
        ret =
          Math.floor(state.board.currentPageIndex / state.board.pageLinkCount) *
            state.board.pageLinkCount +
          state.board.pageLinkCount;
      }
      // 위 오류나는 코드를 아래와 같이 비교해서 처리
      return ret > getters.getPageCount ? getters.getPageCount : ret;
    },
    getPrev: function (state) {
      if (state.board.currentPageIndex <= state.board.pageLinkCount) {
        return false;
      } else {
        return true;
      }
    },
    getNext: function (getters) {
      if (getters.getEndPageIndex >= getters.getPageCount) {
        return false;
      } else {
        return true;
      }
    },
  }
});