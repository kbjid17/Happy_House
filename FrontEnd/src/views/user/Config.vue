<template>
  <div>
    <section class="signin-section">
        <div class="container-fluid">
          <!-- ========== title-wrapper start ========== -->
          <div class="title-wrapper pt-30">
            <div class="row align-items-center">
              <div class="col-md-6">
                <div class="title mb-30">
                  <h2>Profile Config</h2>
                </div>
              </div>
              <!-- end col -->
              <div class="col-md-6">
                <div class="breadcrumb-wrapper mb-30">
                  <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                      <li class="breadcrumb-item">
                        <a href="#0">Dashboard</a>
                      </li>
                      <li class="breadcrumb-item"><a href="#0">Auth</a></li>
                      <li class="breadcrumb-item active" aria-current="page">
                        Config
                      </li>
                    </ol>
                  </nav>
                </div>
              </div>
              <!-- end col -->
            </div>
            <!-- end row -->
          </div>
          <!-- ========== title-wrapper end ========== -->

          <div class="row g-0 auth-row">
            <div class="col-lg-6">
              <div class="auth-cover-wrapper bg-primary-100">
                <div class="auth-cover">
                  <div class="title text-center">
                    <h1 class="text-primary mb-10">Get Started</h1>
                    <p class="text-medium">
                      Start creating the best possible user experience
                      <br class="d-sm-block" />
                      for you customers.
                    </p>
                  </div>
                  <div class="cover-image">
                    <img src="assets/images/auth/signin-image.svg" alt="" />
                  </div>
                  <div class="shape-image">
                    <img src="assets/images/auth/shape.svg" alt="" />
                  </div>
                </div>
              </div>
            </div>
            <!-- end col -->
            <div class="col-lg-6">
              <div class="signup-wrapper">
                <div class="form-wrapper">
                  <h6 class="mb-15">Sign Up Form</h6>
                  <p class="text-sm mb-25">
                    Start creating the best possible user experience for you
                    customers.
                  </p>
                    <div class="row">
                      <div class="col-12">
                        <div class="input-style-1">
                          <label>Name</label>
                          {{login.userName}}
                        </div>
                      </div>
                      <!-- end col -->
                      <div class="col-12">
                        <div class="input-style-1">
                          <label>Email</label>
                          {{login.userEmail}}
                        </div>
                      </div>
                      <!-- end col -->
                      <div class="col-12">
                        <div class="input-style-1">
                          <label>Password</label>
                          <input type="password" v-model="login.userPassword" placeholder="Password" />
                        </div>
                      </div>
                      <!-- end col -->
                      <div class="col-12">
                        <div class="input-style-1">
                          <label>img</label>
                          {{login.userProfileImageUrl}}
                          <button class = "main-btn text-center btn-hover primary-btn">config</button>
                        </div>
                      </div>
                      <!-- end col -->
                      <div class="col-12">
                        <div class="form-check checkbox-style mb-30">
                          <input
                            class="form-check-input"
                            type="checkbox"
                            value=""
                            id="checkbox-not-robot"
                          />
                          <label
                            class="form-check-label"
                            for="checkbox-not-robot"
                          >
                            I'm not robot</label
                          >
                        </div>
                      </div>
                      <!-- end col -->
                      <div class="col-12">
                        <div
                          class="
                            button-group
                            d-flex
                            justify-content-center
                            flex-wrap
                          "
                        >
                          <button
                            class="
                              main-btn
                              primary-btn
                              btn-hover
                              w-100
                              text-center
                            "
                            @click="configUser"
                          >
                            Sign Up
                          </button>
                          <br>
                          <button
                            class="
                              main-btn
                              secondry-btn
                              btn-hover
                              w-100
                              text-center
                            "
                            @click="deleteUser"
                          >
                            탈퇴
                          </button>
                        </div>
                      </div>
                    </div>
                    <!-- end row -->
                  
                </div>
              </div>
            </div>
            <!-- end col -->
          </div>
          <!-- end row -->
        </div>
      </section>
  </div>
</template>

<script>

import Vue from 'vue';
import VueAlertify from 'vue-alertify'; 
Vue.use(VueAlertify);

import http from "@/common/axios.js";
import { mapMutations, mapState } from 'vuex';


const userStore = "userStore";
export default {
  name: "config",
  computed: {
    ...mapState(userStore,["login","deleteMember"]), //
  },
  methods: {
    ...mapMutations(userStore,["SET_CONFIG","SET_DELETE"]),
    configUser() {
      console.log("config");
      console.log(this.login.userPassword) // 받아옴
      http.put(
        "/users/" + this.login.userSeq,
        {
          userSeq: this.login.userSeq,
          userPassword: this.login.userPassword,
          userProfileImageUrl: this.login.userProfileImageUrl,
        }
      )
      .then(({data}) => {
        console.log("config success");
        console.log(data);
        
        this.SET_CONFIG({userPassword: data.userPassword, userProfileImageUrl: data.userProfileImageUrl});
        //1) login state에 있는 구조 가져옴 => 2) config state값에 집어넣음 -> update 진행

        this.$router.push("/mypage")
      })
      .catch( error => {
        console.log("configVue: error : ");
        console.log(error);
        if( error.response.status == '404'){
          this.$alertify.error('check your input value.');
        }else{
          this.$alertify.error('Some Problem occered from server.');
        }
          
      });
    },
    deleteUser() {
      console.log("delete")
      console.log("/users/" + this.login.userSeq)
      http.delete(
        "/users/" + this.login.userSeq,
        {
          userSeq : this.login.userSeq,
        }
      ).then(({data}) => {
        console.log("delete success");
        console.log(data);

        this.SET_DELETE({userSeq: data.userSeq})
        // this.$store.commit(
        //   'SET_DELETE',
        //   { isDelete: true, userSeq: data.userSeq}
        // );

        this.$router.push("/login")
      })
      .catch( error => {
        console.log("configVue: error : ");
        console.log(error);
        if( error.response.status == '404'){
          this.$alertify.error('check your input value.');
        }else{
          this.$alertify.error('Some Problem occered from server.');
        }
      })
    }
  }
}
</script>

<style>

</style>