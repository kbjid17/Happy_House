<template>
<!-- ========== header start ========== -->
  <header class="header">
    <div class="container-fluid">
      <div class="row">
        <div class="col-lg-5 col-md-5 col-6">
          <div class="header-left d-flex align-items-center">
            <div class="menu-toggle-btn mr-20">
              <button id="menu-toggle" class="main-btn primary-btn btn-hover" ref="menuToggle">
                <i class="lni lni-chevron-left me-2" ref="menuToggleI"></i> Menu
              </button>
            </div>
            <div class="header-search d-none d-md-flex">
              <form action="#" v-on:submit.prevent>
                <!-- 사이트마다 동적으로 검색 기능을 바꿔가면서 진행하고 싶음! -->
                <input v-on:keyup.enter="search" v-model="searchWord" type="text" placeholder="Search..." />
                <button @click ="search"><i class="lni lni-search-alt"></i></button>
              </form>
            </div>
          </div>
        </div>
        <div class="col-lg-7 col-md-7 col-6">
          <div class="header-right" v-show="login.isLogin">
            <!-- notification start -->
            <div class="notification-box ml-15 d-none d-md-flex">
              <button
                class="dropdown-toggle"
                type="button"
                id="notification"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                <i class="lni lni-alarm"></i>
                <span>2</span> 
                <!-- 알림창 몇개 왔는지 나타내주는 것. 
                추후 공지사항이 올라오면 동적으로 확인 안한 공지사항 표시
                 notice_user_read로 확인하면 될듯 하다. -->
              </button>
              <ul
                class="dropdown-menu dropdown-menu-end"
                aria-labelledby="notification"
              >
                <li>
                  <a href="#">
                    <div class="image">
                      <img src="@/assets/images/lead/lead-6.png" alt="" />
                    </div>
                    <div class="content">
                      <h6>
                        John Doe
                        <span class="text-regular">
                          comment on a product.
                        </span>
                      </h6>
                      <p>
                        Lorem ipsum dolor sit amet, consect etur adipiscing
                        elit Vivamus tortor.
                      </p>
                      <span>10 mins ago</span>
                    </div>
                  </a>
                </li>
                <li>
                  <a href="#">
                    <div class="image">
                      <img src="@/assets/images/lead/lead-1.png" alt="" />
                    </div>
                    <div class="content">
                      <h6>
                        Jonathon
                        <span class="text-regular"> like on a product. </span>
                      </h6>
                      <p>
                        Lorem ipsum dolor sit amet, consect etur adipiscing
                        elit Vivamus tortor.
                      </p>
                      <span>10 mins ago</span>
                    </div>
                  </a>
                </li>
              </ul>
            </div>
            <!-- notification end -->
            <!-- message start, 챗봇과 연동하면 좋을 부분 -->
            <div class="header-message-box ml-15 d-none d-md-flex">
              <button
                class="dropdown-toggle"
                type="button"
                id="message"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                <i class="lni lni-envelope"></i>
                <span>3</span>
                <!-- 공지사항 부분과 마찬가지로 메세지가 오면 동적으로 확인. -->
              </button>
              <ul
                class="dropdown-menu dropdown-menu-end"
                aria-labelledby="message"
              >
                <li>
                  <a href="#">
                    <div class="image">
                      <img src="@/assets/images/lead/lead-5.png" alt="" />
                    </div>
                    <div class="content">
                      <h6>Jacob Jones</h6>
                      <p>Hey!I can across your profile and ...</p>
                      <span>10 mins ago</span>
                    </div>
                  </a>
                </li>
                <li>
                  <a href="#">
                    <div class="image">
                      <img src="@/assets/images/lead/lead-3.png" alt="" />
                    </div>
                    <div class="content">
                      <h6>John Doe</h6>
                      <p>Would you mind please checking out</p>
                      <span>12 mins ago</span>
                    </div>
                  </a>
                </li>
                <li>
                  <a href="#">
                    <div class="image">
                      <img src="@/assets/images/lead/lead-2.png" alt="" />
                    </div>
                    <div class="content">
                      <h6>Anee Lee</h6>
                      <p>Hey! are you available for freelance?</p>
                      <span>1h ago</span>
                    </div>
                  </a>
                </li>
              </ul>
            </div>
            <!-- message end -->

            <!-- profile start -->
            <div class="profile-box ml-15">
              <button
                class="dropdown-toggle bg-transparent border-0"
                type="button"
                id="profile"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                <div class="profile-info">
                  <div class="info">
                    <h6>{{login.userName}}</h6>
                    <div class="image">
                      <img
                        :src="profileImg()"
                        alt=""
                      />
                      <span class="status"></span>
                    </div>
                  </div>
                </div>
                <i class="lni lni-chevron-down"></i>
              </button>
              <ul
                class="dropdown-menu dropdown-menu-end"
                aria-labelledby="profile"
              >
                <li>
                  <a @click="moveMyPage" href="#"> <i class="lni lni-user"></i> View Profile </a>
                </li>
                <li>
                  <a @click="moveNotification" href="#">
                    <i class="lni lni-alarm"></i> Notifications
                  </a>
                </li>
                <li>
                  <a href="#"> <i class="lni lni-inbox"></i> Messages </a>
                </li>
                <li>
                  <a href="#" @click="logoutUser"><i class="lni lni-exit" ></i> Sign Out </a>
                </li>
              </ul>
            </div>
            <!-- profile end -->
          </div>
        </div>
      </div>
    </div>
  </header>
<!-- ========== header end ========== -->
</template>

<script>
let param = {
      limit : 10,
      offset : 0,
      searchWord : "",
    };
import { mapActions, mapState, mapMutations } from 'vuex';

const userStore ="userStore";
const boardStore ="boardStore";
const noticeStore ="noticeStore";
const houseStore = "houseStore";

export default {
  data() {
    return {
      searchWord : "",
    }
  },
  computed: {
    ...mapState(userStore,["login"]),
    ...mapState(boardStore,["board"]),
    ...mapState(noticeStore,["notice"]),

    profileImg() {
      //프로필 이미지를 매칭
      return (img) => {
        if (
          this.login.userProfileImageUrl === "" ||
          this.login.userProfileImageUrl === undefined
        ) {
          console.log(img);
          this.login.userProfileImageUrl = "noProfile.png";
        }
        // console.log(img)
        return require("@/assets/images/profile/" + this.login.userProfileImageUrl);
      };
    },
  },
  created() {
  },
  methods: {
    ...mapActions(userStore,["logout"]),
    ...mapActions(boardStore,["boardList"]),
    ...mapActions(noticeStore,["noticeList"]),
    ...mapActions(houseStore, ["getHouseList"]),
    ...mapMutations(houseStore, ["SET_SEARCH_WORD"]),

    moveMyPage() {
      if(this.$route.path!=='/myPage') 
        this.$router.push('/myPage');
    },
    moveNotification() {
      if(this.$route.path!=='/notices') 
        this.$router.push('/notices');
    },
    logoutUser() {
      this.logout(this.login);
      console.log(this.$route.path)
       this.$router.push('/login');
    },

    search() {
      // console.log(this.searchWord)
      // console.log(this.$route.path)
      switch(this.$route.path) { //위치에 따라 다른 검색 가능
        case "/boards": { 
          this.board.searchWord = this.searchWord
          param.searchWord = this.searchWord
          this.boardList();
          param.searchWord = ""
          this.notice.search = ""
          break;
        }
        case "/notices": { 
          this.notice.searchWord = this.searchWord
          param.searchWord = this.searchWord
          console.log("--------------------------------")
          console.log(param.searchWord);
          this.noticeList();
          param.searchWord = ""
          this.notice.searchWord = ""
          break;
        }
        case "/houseDetail": {
          this.SET_SEARCH_WORD(this.searchWord);
          this.getHouseList();
          break;
        }
        case "/houseList": {
          this.SET_SEARCH_WORD(this.searchWord);
          this.getHouseList();
          break;
        }
      }
    }
  }
};
</script>

<style>
</style>