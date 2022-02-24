import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import http from "@/common/axios.js";
import router from '@/routers/routers.js'

const board= {
  namespaced: true,
  state: {
    board: {
      // list
      list: [],
      limit: 10,
      offset: 0,
      searchWord: "",

      // pagination
      listRowCount: 10,
      pageLinkCount: 10,
      currentPageIndex: 1,

      totalListItemCount: 0,

      // detail, update, delete
      boardId: 0,
      title: "",
      content: "",
      userName: "",
      regDt: {},
      readCount: 0,
      fileList: [],
      sameUser: false,
    },
  },
  mutations: {

    //board
    SET_BOARD_LIST(state, list) {
      state.board.list = list;
    },

    SET_BOARD_TOTAL_LIST_ITEM_COUNT(state, count) {
      state.board.totalListItemCount = count;
    },

    SET_BOARD_MOVE_PAGE(state, pageIndex) {
      console.log(state.board.offset);
      console.log(pageIndex);
      state.board.offset = (pageIndex - 1) * state.board.listRowCount;
      console.log(state.board.offset);
      state.board.currentPageIndex = pageIndex;
      console.log(state.board.currentPageIndex);
    },
    SET_BOARD_INSERT(state,payload) {
      state.board.boardId = payload.boardId;
      state.board.title = payload.title;
      state.board.content = payload.content;
      state.board.userName = payload.userName;
      state.board.regDt = payload.regDt;
      state.board.fileList = payload.fileList;
    },
    SET_BOARD_DETAIL(state, payload) {
      console.log("setboardDetail");
      state.board.boardId = payload.boardId;
      state.board.title = payload.title;
      state.board.content = payload.content;
      state.board.userName = payload.userName;
      state.board.regDt = payload.regDt;
      state.board.fileList = payload.fileList;
      state.board.sameUser = payload.sameUser;
    },
    // for UpdateModal title v-modal
    SET_BOARD_TITLE(state, title) {
      state.board.title = title;
    },
    
  },
  actions: {
    boardList(context) {
      console.log("boardList");
      // this.state.board.searchWord= "";
      // console.log(this.state.board.searchWord);
      console.log(this.state.boardStore.board);
      console.log(this.state.boardStore.board.limit);
      // console.log(this.state.board.offset);
      console.log(this.state.boardStore.searchWord);

      http
        .get("/boards", {
          // get query string
          params: {
            limit: this.state.boardStore.board.limit,
            offset: this.state.boardStore.board.offset,
            searchWord: this.state.boardStore.board.searchWord,
          },
        })
        .then(({ data }) => {
          console.log("BoardMainVue: data : ");
          console.log(data);
          if (data.result == "login") {
            router.push("/login");
          } else {
            context.commit("SET_BOARD_LIST", data.list);
            context.commit("SET_BOARD_TOTAL_LIST_ITEM_COUNT", data.count);
          }
        });
    },
  },
  getters : {
    //board
    getBoardList: function (state) {
      console.log('getBoardList');
      return state.board.list;
    },
    // pagination
    getBoardPageCount: function (state) {
      return Math.ceil(
        state.board.totalListItemCount / state.board.listRowCount
      );
    },
    getBoardStartPageIndex: function (state) {
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
    getBoardEndPageIndex: function (state, getters) {
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
    getBoardPrev: function (state) {
      if (state.board.currentPageIndex <= state.board.pageLinkCount) {
        return false;
      } else {
        return true;
      }
    },
    getBoardNext: function (getters) {
      if (getters.getEndPageIndex >= getters.getPageCount) {
        return false;
      } else {
        return true;
      }
    },
  },
}
export default board;