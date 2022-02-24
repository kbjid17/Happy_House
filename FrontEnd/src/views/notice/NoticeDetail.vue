<template>
  <div class = "row container-fluid my-5">
    
                <div class="card-style-3 mb-30">
                  <div class="card-content">
                    <h4>{{notice.noticeSeq}}. {{notice.title}}</h4>
                    <br>
                    작성자 : <b>{{notice.userName}} </b> | 작성일시 : {{notice.regDt}}
                    <hr>
                    <p>
                     {{notice.content}}
                    </p>
                    <hr>
                    <button v-show="login.userName == notice.userName" @click="confignotice" class="btn btn-sm btn-warning my-2">수정</button>
                    <button v-show="login.userName == notice.userName" @click="deletenotice(notice.noticeSeq)" class="btn btn-sm btn-danger mx-2">삭제</button>
                    <button @click="gonotice" class="btn btn-sm btn-secondary my-2">목록</button>
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
const noticeStore = "noticeStore";
const userStore = "userStore";
export default {

computed: {
...mapState(noticeStore,["notice"]),
...mapState(userStore,["login"]),
},
methods: {
  gonotice() {
    this.$router.push("/notices");
  },
  confignotice() {
    alert("config");
    this.$router.push("/noticeUpdate");
  },
  deletenotice(noticeSeq) {
    alert("delete");
    http.delete(
      '/notices/'+noticeSeq,
    )
    .then(({data}) => {
      console.log(data);
    })
    .catch((error) => {
      console.log("DeleteModalVue: error ");
        console.log(error);
    })
    this.$router.go(this.$router.push("/notices"));
    // this.$router.push("/notices");
  },
}
}
</script>

<style>

</style>