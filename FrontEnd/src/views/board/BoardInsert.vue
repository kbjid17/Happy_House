<template>
  <div class="row container-fluid my-5">
              <div class="card-style mb-30">
                  <h6 class="mb-25">Textarea</h6>
                  <div class="input-style-1">
                    <label>제목</label>
                    <textarea v-model="title" placeholder="Message" rows="1"></textarea>
                  </div>
                  <!-- end textarea -->
                  <div class="input-style-1">
                    <label>내용</label>
                    <textarea v-model="content" placeholder="Message" rows="8"></textarea>
                    <button @click="insert" class="btn btn-sm btn-primary">등록</button>
                    <button @click="Back" class="btn btn-sm btn-secondary">취소</button>
                  </div>
                  <!-- end textarea -->
              </div>
  </div>
</template>

<script>
import http from "@/common/axios.js";
  // import util from "@/common/util.js";

  // import Pagination from "@/views/Pagination.vue";
  // 삭제
  import Vue from 'vue';
  import VueAlertify from 'vue-alertify'; 
import { mapState } from 'vuex';
  Vue.use(VueAlertify);

const userStore = "userStore";
// const boardStore = "boardStore";
export default {
  name: 'boardInsert',
  // components : {Pagination},
  data() {
    return {
      title: '',
      content : '',
      attachFile: false,
      fileList: [],
    }
  },
  computed : {
    ...mapState(userStore,["login"]),
    // ...mapState(boardStore,["board"]),
  },
  methods: {
    changeFile(fileEvent) {
        if( fileEvent.target.files && fileEvent.target.files.length > 0 ){

          for( var i=0; i<fileEvent.target.files.length; i++ ){
            const file = fileEvent.target.files[i];
            this.fileList.push(URL.createObjectURL(file));
          }
        }
      },
    Back() {
      this.$router.push("/Boards")
    },
    insert() {
      var formData = new FormData();
      formData.append("title",this.title);
      formData.append("content",this.content);

      var attachFiles = document.querySelector("#inputFileUploadInsert");
      console.log(attachFiles);
      // var cnt = attachFiles.files

      http.post(
        '/boards',
        formData,
        { headers: { 'Content-Type': 'multipart/form-data' } })
      .then(({data}) => {
        console.log(data);
        if(this.login.isLogin) {
          this.$router.push("/boards");
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

</style>