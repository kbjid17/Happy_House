import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import http from "@/common/axios.js";
import router from '@/routers/routers.js'

const notice= {
  namespaced: true,
  state: {
    notice: {
      // list
      list: [], // 7. 서버에서 값을 못 받아와서 비어있음
      limit: 10,
      offset: 0,
      searchWord: "",

      // pagination
      listRowCount: 10,
      pageLinkCount: 10,
      currentPageIndex: 1,

      totalListItemCount: 0,

      // detail, update, delete
      noticeId: 0,
      noticeSeq: 0,
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

    //notice
    SET_NOTICE_LIST(state, list) {
      state.notice.list = list;
    },

    SET_NOTICE_TOTAL_LIST_ITEM_COUNT(state, count) {
      state.notice.totalListItemCount = count;
    },

    SET_NOTICE_MOVE_PAGE(state, pageIndex) {
      state.notice.offset = (pageIndex - 1) * state.notice.listRowCount;
      state.notice.currentPageIndex = pageIndex;
    },
    SET_NOTICE_INSERT(state,payload) {
      state.notice.noticeId = payload.noticeId;
      state.notice.title = payload.title;
      state.notice.content = payload.content;
      state.notice.userName = payload.userName;
      state.notice.regDt = payload.regDt;
      state.notice.fileList = payload.fileList;
    },
    SET_NOTICE_DETAIL(state, payload) {
      console.log("setnoticeDetail");
      state.notice.noticeSeq = payload.noticeSeq;
      state.notice.noticeId = payload.noticeId;
      state.notice.title = payload.noticeTitle;
      state.notice.content = payload.noticeContent;
      state.notice.userName = payload.userName;
      state.notice.regDt = payload.noticeRegDt;
      state.notice.fileList = payload.fileList;
      state.notice.sameUser = payload.sameUser;
    },
    // for UpdateModal title v-modal
    SET_NOTICE_TITLE(state, title) {
      state.notice.title = title;
    },
    
  },
  actions: {
    noticeList(context) {
      console.log("noticeList");
  // this.state.notice.noticeId= "fgdh";
  // console.log(this.state.notice.noticeId);
  // console.log(this.state.board);
  // console.log(this.state.boardStore.board.limit);
  // console.log(this.state.boardStore.board.offset);
  // console.log(this.state.boardStore.board.searchWord);

  console.log(this.state.noticeStore.notice);
  console.log(this.state.noticeStore.notice.regDt);
  console.log(this.state.noticeStore.notice.limit);
  console.log(this.state.noticeStore.notice.offset);
  console.log(this.state.noticeStore.notice.searchWord);

    

      http
        .get("/notices", {
          // get query string
          params: {
            limit: this.state.noticeStore.notice.limit,
            offset: this.state.noticeStore.notice.offset,
            searchWord: this.state.noticeStore.notice.searchWord,
          },
        })
        .then(({ data }) => {
          console.log("NoticeMainVue: data : ");
          console.log(data);
          if (data.result == "login") {
            router.push("/login");
          } else {
            context.commit("SET_NOTICE_LIST", data.list);
            context.commit("SET_NOTICE_TOTAL_LIST_ITEM_COUNT", data.count);
          }
        });
    },
  },
  getters : {
    //notice
    getNoticeList: function (state) { // 5. notiveMain.vue에서 호출됨
      console.log('getNoticeList');
      return state.notice.list; // 6. state에 저장된 list를 불러옴
    },
    // pagination
    getPageCount: function (state) {
      return Math.ceil(
        state.notice.totalListItemCount / state.notice.listRowCount
      );
    },
    getStartPageIndex: function (state) {
      if (state.notice.currentPageIndex % state.notice.pageLinkCount == 0) {
        //10, 20...맨마지막
        return (
          (state.notice.currentPageIndex / state.notice.pageLinkCount - 1) *
            state.notice.pageLinkCount +
          1
        );
      } else {
        return (
          Math.floor(state.notice.currentPageIndex / state.notice.pageLinkCount) *
            state.notice.pageLinkCount +
          1
        );
      }
    },
    getEndPageIndex: function (state, getters) {
      let ret = 0;
      if (state.notice.currentPageIndex % state.notice.pageLinkCount == 0) {
        //10, 20...맨마지막
        ret =
          (state.notice.currentPageIndex / state.notice.pageLinkCount - 1) *
            state.notice.pageLinkCount +
          state.notice.pageLinkCount;
      } else {
        ret =
          Math.floor(state.notice.currentPageIndex / state.notice.pageLinkCount) *
            state.notice.pageLinkCount +
          state.notice.pageLinkCount;
      }
      // 위 오류나는 코드를 아래와 같이 비교해서 처리
      return ret > getters.getPageCount ? getters.getPageCount : ret;
    },
    getPrev: function (state) {
      if (state.notice.currentPageIndex <= state.notice.pageLinkCount) {
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
  },
}
export default notice;