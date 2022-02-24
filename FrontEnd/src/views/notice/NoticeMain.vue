<template>
   <div class="container-fluid my-5">
      <div class="row">
              <div class="col-lg-12">
                <div class="card-style mb-30">
                  <h6 class="mb-10">서버 관련 공지사항입니다.</h6>
                  <p class="text-sm mb-20">
                    For basic styling—light padding and only horizontal
                    dividers—use the class table.
                  </p>
                  <div class="table-wrapper table-responsive">
                    <table class="table">
                      <thead>
                        <tr>
                          <th><h6>#</h6></th>
                          <th><h6>제목</h6></th>
                          <th><h6>작성자</h6></th>
                          <th><h6>작성일시</h6></th>
                          <th><h6>조회수</h6></th>
                        </tr>
                        <!-- end table row-->
                      </thead>
                      <tbody> <!-- 2.listGetters를 통해 화면에 글 목록을 뿌리려고 함 -->
                        <tr style="cursor:pointer" v-for="(notice, index) in listGetters" @click="noticeDetail(notice.noticeSeq)" v-bind:key="index">
                          <td>{{ notice.noticeSeq}}</td>
                          <td>{{ notice.noticeTitle }}</td>
                          <td>{{ notice.userName }}</td>
                          <td>{{ makeDateStr(notice.noticeRegDt.date.year, notice.noticeRegDt.date.month, notice.noticeRegDt.date.day, '.') }}</td>            
                          <td>{{ notice.noticeReadcount }}</td>
                        </tr>
                      </tbody>
                    </table>

                     <pagination v-on:call-parent="movePage"></pagination>
                     <button v-show="clsfMatch(login.userClsf)" class="btn btn-sm btn-primary" @click="write">글쓰기</button>
                    <!-- end table -->
                  </div>
                </div>
                <!-- end card -->
              </div>
              <!-- end col -->
            </div>
            <!-- end row -->
  </div>
</template>

<script>
import http from "@/common/axios.js";
  import util from "@/common/util.js";

 const noticeStore = "noticeStore";
 const userStore = "userStore";

  import Pagination from "@/views/Pagination.vue";
  // 삭제
  import Vue from 'vue';
  import VueAlertify from 'vue-alertify'; 
  import { mapActions, mapMutations, mapState, mapGetters } from 'vuex';
  Vue.use(VueAlertify);
  let param = {
      limit : 10,
      offset : 0,
      searchWord : "",
    }; // param이 하드코딩 되어 있는 상태. 어떻게 고치지?
 

export default {
  name: 'notices',
  components : {Pagination},
  created(){
    // 1. 리스트를 서버에서 못가져온 상태.
   
    this.noticeList(
      param,
      (response) => {
        // console.log("1241251"+param.limit);
        this.articles = response.data;
        //  console.log("1241251"+param.limit);
      },
      (error) => {
        console.log(error);
      }
    );
  },
  computed : {
    ...mapState(noticeStore,["notice"]),
    ...mapState(userStore,["login"]),
    ...mapGetters(noticeStore,["getNoticeList"]), // 3.store에서 선언한 getters 사용
    
     listGetters(){ 
      return this.getNoticeList; // 4. 위의 선언한 getters : getNoticeList를 호출
    },
  },
  methods: {
    ...mapMutations(noticeStore,["SET_NOTICE_MOVE_PAGE","SET_NOTICE_DETAIL"]),
    ...mapActions(noticeStore,["noticeList"]),
     noticePrint(){
        //  console.log(this.state.noticeStore.notice);
      //  console.log(this.boardList);
       console.log(this.login.isLogin);
      // this.$store.dispatch('boardList');  
         
      this.noticeList( //noticPrint : this.noticeList
      param,
      (response) => {
        this.articles = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
    },
    clsfMatch(userClsf) {
      if(userClsf == 0) {
        return true;
      }
      else {
        return false;
      }
    },
    // pagination
    movePage(pageIndex){
      console.log("NoticeMainVue : movePage : pageIndex : " + pageIndex );
  
      // store commit 으로 변경
      // this.offset = (pageIndex - 1) * this.listRowCount;
      // this.currentPageIndex = pageIndex;
      // this.$boardStore.commit( 'SET_BOARD_MOVE_PAGE', pageIndex );
      this.SET_NOTICE_MOVE_PAGE(pageIndex);

      this.noticePrint();
    },

    // util
    makeDateStr : util.makeDateStr,
    write() {
      if(!this.login.isLogin) {
        alert("로그인 후, 게시판 글 쓰기 및 다양한 기능을 활용해 보세요!")
        this.$router.push("/Login");
        console.log(this.login.isLogin)
      }else {
        this.$router.push("/NoticeInsert");
      }
      
    },
    noticeDetail(noticeSeq){
      // console.log("boardId : " + boardId);
      http.get(
        '/notices/'+noticeSeq,
      )
      .then(({data}) =>{
        console.log(data)
        if(this.login.isLogin) {
          this.SET_NOTICE_DETAIL({
              noticeSeq : data.dto.noticeSeq,
              noticeId: data.dto.noticeId,
              noticeTitle: data.dto.noticeTitle,
              noticeContent: data.dto.noticeContent,
              userName: data.dto.userName,
              noticeRegDt: this.makeDateStr(data.dto.noticeRegDt.date.year, data.dto.noticeRegDt.date.month, data.dto.noticeRegDt.date.day, '.'),
              fileList: data.dto.fileList,
              sameUser: data.dto.sameUser, // not data.dto.sameUser
          });
          this.$router.push("/noticeDetail");
        }
        else {
          this.$router.push("/login");
        }
      })
      .catch((error) => {
        console.log("DetailModalVue: error ");
        console.log(error);
      })
    }
  }


}
</script>

<style>
.table {
  border: 3em;
  border-color: black;
}
</style>