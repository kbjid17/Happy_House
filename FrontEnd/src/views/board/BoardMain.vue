<template>
   <div class="container-fluid my-5">
      <div class="row">
              <div class="col-lg-12">
                <div class="card-style mb-30">
                  <h6 class="mb-10">자유 게시판</h6>
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
                      <tbody>
                        <tr style="cursor:pointer" v-for="(board, index) in listGetters" @click="boardDetail(board.boardId)" v-bind:key="index">
                          <td>{{ board.boardId }}</td>
                          <td>{{ board.title }}</td>
                          <td>{{ board.userName }}</td>
                          <td>{{ makeDateStr(board.regDt.date.year, board.regDt.date.month, board.regDt.date.day, '.') }}</td>            
                          <td>{{ board.readCount }}</td>
                        </tr>
                      </tbody>
                    </table>

                     
                    <!-- end table -->
                  </div>
                  <pagination v-on:call-parent="movePage"></pagination>
                     <button  class="btn btn-sm btn-primary" v-if="login.isLogin" @click="write">글쓰기</button>
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

 const boardStore = "boardStore";
 const userStore = "userStore";

  import Pagination from "@/views/Pagination.vue";
  // 삭제
  import Vue from 'vue';
  import VueAlertify from 'vue-alertify'; 
  import { mapActions, mapMutations, mapState, mapGetters } from 'vuex';
  Vue.use(VueAlertify);
  // let param = {
  //     limit : 10,
  //     offset : 0,
  //     searchWord : "",
  //   }; // param이 하드코딩 되어 있는 상태. 어떻게 고치지?
 

export default {
  name: 'boards',
  components : {Pagination},
  created(){
    this.boardList();
  },
  computed : {
    ...mapState(boardStore,["board"]),
    ...mapState(userStore,["login"]),
    ...mapGetters(boardStore,["getBoardList"]),
    
     listGetters(){
      return this.getBoardList;
    },
  },
  methods: {
    ...mapMutations(boardStore,["SET_BOARD_MOVE_PAGE","SET_BOARD_DETAIL"]),
    ...mapActions(boardStore,["boardList"]),
     boardPrint(){
      //  console.log(this.boardList);
       console.log(this.login.isLogin);
      // this.$store.dispatch('boardList');  
         
      this.boardList();
    },
    // pagination
    movePage(pageIndex){
      console.log("BoardMainVue : movePage : pageIndex : " + pageIndex );
  
      // store commit 으로 변경
      // this.offset = (pageIndex - 1) * this.listRowCount;
      // this.currentPageIndex = pageIndex;
      // this.$boardStore.commit( 'SET_BOARD_MOVE_PAGE', pageIndex );
      this.SET_BOARD_MOVE_PAGE(pageIndex);

      // this.boardPrint();
    },

    // util
    makeDateStr : util.makeDateStr,
    write() {
      if(!this.login.isLogin) {
        alert("로그인 후, 게시판 글 쓰기 및 다양한 기능을 활용해 보세요!")
        this.$router.push("/Login");
        console.log(this.login.isLogin)
      }else {
        this.$router.push("/BoardInsert");
      }
      
    },
    boardDetail(boardId){
      // console.log("boardId : " + boardId);
      http.get(
        '/boards/'+boardId,
      )
      .then(({data}) =>{
        console.log(data)
          this.SET_BOARD_DETAIL({
              boardId: data.dto.boardId,
              title: data.dto.title,
              content: data.dto.content,
              userName: data.dto.userName,
              regDt: this.makeDateStr(data.dto.regDt.date.year, data.dto.regDt.date.month, data.dto.regDt.date.day, '.'),
              fileList: data.dto.fileList,
              sameUser: data.dto.sameUser, // not data.dto.sameUser
          });
          this.$router.push("/boardDetail");
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