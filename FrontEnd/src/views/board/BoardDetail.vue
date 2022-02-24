<template>
  <div class = "row container-fluid my-5">
                <div class="card-style-3 mb-30">
                  <div class="card-content">
                    <h4>{{board.boardId}}. {{board.title}}</h4>
                    <br>
                    작성자 : <b>{{board.userName}} </b> | 작성일시 : {{board.regDt}}
                    <hr>
                    <p>
                     {{board.content}}
                    </p>
                    <hr>
                    <button v-if="login.userName == board.userName" @click="configBoard" class="btn btn-sm btn-warning my-2">수정</button>
                    <button v-if="login.userName == board.userName" @click="deleteBoard(board.boardId)" class="btn btn-sm btn-danger mx-2">삭제</button>
                    <button @click="goBoard" class="btn btn-sm btn-secondary my-2">목록</button>
                  </div>
                </div>
              <!-- end col -->
  </div>
</template>

<script>
import Vue from 'vue';
import VueAlertify from 'vue-alertify';
Vue.use(VueAlertify);

import { mapState } from 'vuex';
import http from "@/common/axios.js";
const boardStore = "boardStore";
const userStore = "userStore";
export default {

computed: {
...mapState(boardStore,["board"]),
...mapState(userStore,["login"]),
},
methods: {
  goBoard() {
    this.$router.push("/boards");
  },
  configBoard() {
    alert("config");
    this.$router.push("/boardUpdate");
  },
  deleteBoard(boardId) {
    alert("delete");
    http.delete(
      '/boards/'+boardId,
    )
    .then(({data}) => {
      console.log(data);
    })
    .catch((error) => {
      console.log("DetailModalVue: error ");
        console.log(error);
    })
    this.$router.go(this.$router.push("/boards"));
    // this.$router.push("/boards");
  },
}
}
</script>

<style>

</style>