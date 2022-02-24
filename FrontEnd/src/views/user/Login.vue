<template>
  <div>
    <section class="signin-section">
        <div class="container-fluid">
          <!-- ========== title-wrapper start ========== -->
          <div class="title-wrapper pt-30">
            <div class="row align-items-center">
              <div class="col-md-6">
                <div class="title mb-30">
                  <h2>Sign in</h2>
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
                        Sign in
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
                    <h1 class="text-primary mb-10">Welcome Back</h1>
                    <p class="text-medium">
                      Sign in to your Existing account to continue
                    </p>
                  </div>
                  <div class="cover-image">
                    <img src="@/assets/images/auth/signin-image.svg" alt="" />
                  </div>
                  <div class="shape-image">
                    <img src="@/assets/images/auth/shape.svg" alt="" />
                  </div>
                </div>
              </div>
            </div>
            <!-- end col -->
            <div class="col-lg-6">
              <div class="signin-wrapper">
                <div class="form-wrapper">
                  <h6 class="mb-15">Sign In Form</h6>
                  <p class="text-sm mb-25">
                    Start creating the best possible user experience for you
                    customers.
                  </p>
                  <div class="row">
                      <div class="col-12">
                        <div class="input-style-1">
                          <label>Email</label>
                          <input type="email" v-model="login.userEmail" placeholder="Email" />
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
                      <div class="col-xxl-6 col-lg-12 col-md-6">
                        <div class="form-check checkbox-style mb-30">
                          <input
                            class="form-check-input"
                            type="checkbox"
                            value=""
                            id="checkbox-remember"
                          />
                          <label
                            class="form-check-label"
                            for="checkbox-remember"
                          >
                            Remember me next time</label
                          >
                        </div>
                      </div>
                      <!-- end col -->
                      <div class="col-xxl-6 col-lg-12 col-md-6">
                        <div
                          class="
                            text-start text-md-end text-lg-start text-xxl-end
                            mb-30
                          "
                        >
                          <a href="#0">Forgot Password?</a>
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
                            @click="loginUser"
                          >
                            Sign In
                          </button>
                        </div>
                      </div>
                    </div>
                    <!-- end row -->
                  <div class="singin-option pt-40">
                    <p class="text-sm text-medium text-center text-gray">
                      Easy Sign In With
                    </p>
                    <div
                      class="
                        button-group
                        pt-40
                        pb-40
                        d-flex
                        justify-content-center
                        flex-wrap
                      "
                    >
                     
                    </div>
                    <p class="text-sm text-medium text-dark text-center">
                      Don’t have any account yet?
                      <router-link to="/register">Create an account</router-link>
                    </p>
                  </div>
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
import {mapMutations, mapState} from "vuex";

const userStore = "userStore";
import Vue from 'vue';
import VueAlertify from 'vue-alertify'; //안되는 경우가 있음. 문제시 해결 필요
Vue.use(VueAlertify);

import http from "@/common/axios.js";

export default {
  name: 'Login',
  date() {
    return {
      userEmail : "",
      userPassword : ""
    }
  },
  computed: {
    ...mapState(userStore,["login"]),
  },
  methods: {
    ...mapMutations(userStore,["SET_LOGIN"]),
    loginUser() {
      http.post(
        "/users/login",
        {
          userEmail: this.login.userEmail,
          userPassword: this.login.userPassword,
        },
      )
      .then(({ data }) => {
        console.log("LoginVue: data : ");
        console.log(data);
        // console.log(this.userEmail);
        // console.log(this.userPassword); 왜 안나오지?
        
        this.SET_LOGIN({isLogin: true, userSeq: data.dto.userSeq, userName: data.dto.userName, 
        userProfileImageUrl: data.dto.userProfileImageUrl,userClsf : data.dto.userClsf}) 

        
        // this.userStore.commit(
        //   'SET_LOGIN',
        //   { }
        // );
        console.log(this.login.isLogin)
        // board 로 이동
        this.$router.push("/home")
      })
      .catch( error => {
        console.log("LoginVue: error : ");
        console.log(this.userEmail);
        console.log(this.userPassword);
        console.log(error);
        if( error.response.status == '500'){
          this.$alertify.error('Please check your email or password.');
        }else{
          this.$alertify.error('Some Problem occered from server.');
        }
          
      });
    }
  }
};
</script>

<style>
</style>