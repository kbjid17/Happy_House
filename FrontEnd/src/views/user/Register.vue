<template>
  <div>
    <section class="signin-section">
        <div class="container-fluid">
          <!-- ========== title-wrapper start ========== -->
          <div class="title-wrapper pt-30">
            <div class="row align-items-center">
              <div class="col-md-6">
                <div class="title mb-30">
                  <h2>Sign up</h2>
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
                        Sign up
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
                          <input type="text" v-model="register.userName" placeholder="Name" />
                        </div>
                      </div>
                      <!-- end col -->
                      <div class="col-12">
                        <div class="input-style-1">
                          <label>Email</label>
                          <input type="email" v-model="register.userEmail" placeholder="Email" />
                        </div>
                      </div>
                      <!-- end col -->
                      <div class="col-12">
                        <div class="input-style-1">
                          <label>Password</label>
                          <input type="password" v-model="register.userPassword" placeholder="Password" />
                        </div>
                      </div>
                      <!-- end col -->
                      <div class="col-12">
                        <div class="input-style-1">
                          <label>Password 확인</label>
                          <input type="password" v-model="passwordReview" placeholder="Password" />
                        </div>
                      </div>
                      <!-- end col -->
                      <div class="mb-3">
                        <div class="form-check form-check-inline" v-for="(code, index) in codeList" :key="index">
                          <input class="form-check-input" :value="code.code" type="radio" v-model="register.userClsf">
                          <label class="form-check-label">{{code.codeName}}</label>
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
                            @click="registerUser"
                          >
                            Sign Up
                          </button>
                        </div>
                      </div>
                    </div>
                    <!-- end row -->
                  <div class="singup-option pt-40">
                    <p class="text-sm text-medium text-center text-gray">
                      Easy Sign Up With
                    </p>
                    
                    <p class="text-sm text-medium text-dark text-center">
                      Already have an account? <router-link to="/login"> Sign In </router-link>
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
import VueAlertify from 'vue-alertify'; 
Vue.use(VueAlertify);

import http from "@/common/axios.js";

export default {
  name: "Register",
  data() {
    return {
      groupCode: '001',
      codeList: [],
      userClsf: '001',
      passwordReview: '',
    }
  },
  computed: {
    ...mapState(userStore,["register"]),
  },
  methods: {
    ...mapMutations(userStore,["SET_REGISTER"]),
    registerUser() {
      console.log("register");
      // console.log(this.$store.state.register.userPassword);
      // if(this.passwordReview != this.$store.state.register.userPassword) {
      //   alert("비밀번호 확인하세요");
      // }
      http.post(
        "/users",
        {
          userName: this.register.userName,
          userEmail: this.register.userEmail,
          userPassword: this.register.userPassword,
          userClsf : this.register.userClsf
        }
      )
      .then(({data}) => {
        console.log("register success");
        console.log(data);
        console.log(this.passwordReview)
        this.SET_REGISTER({userName: data.userName, userEmail: data.userEmail, userPassword: data.userPassword, userClsf : data.userClsf});
        // this.$store.commit(
        //   'SET_REGISTER',
        //   {  }
        // );

        this.$router.push("/login")
      })
      .catch( error => {
        console.log("LoginVue: error : ");
        console.log(error);
        if( error.response.status == '404'){
          this.$alertify.error('Please check your email or password.');
        }else if(this.passwordReview != this.register.userPassword) {
          this.$alertify.error("비밀번호를 확인하세요!")
        }else{
          this.$alertify.error('Some Problem occered from server.');
        }
          
      });
    }
  },
  created(){
    http.get(
      "/codes",
      {
        // get query string
        params: {
          groupCode: this.groupCode
        }
      })
      .then(({ data }) => {
        console.log("RegisterVue: data : ");
        console.log(data);
        if( data.result == 'login' ){
          this.$router.push("/login")
        }else{
          this.codeList = data;
        }
    });
  }
}
</script>

<style>

</style>