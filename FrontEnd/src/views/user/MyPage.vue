<template>
  <div>
    <section class="signin-section">
      <div class="container-fluid mt-3">
        <div class="mb-3">
          <h1 class="h3 d-inline align-middle">Profile</h1>
          <router-link class="badge bg-dark text-white ms-2" to="/Config">
            config
          </router-link>
        </div>
        <div class="row">
          <div class="col-md-4 col-xl-3">
            <div class="card mb-3 user-profile">
              <div class="card-header">
                <h5 class="card-title mb-0">회원 정보</h5>
              </div>
              <div class="card-body text-center">
                <img
                  v-bind:src="profileImg(login.userProfileImageUrl)"
                  alt=""
                  class="img-fluid rounded-circle mb-2"
                  width="128"
                  height="128"
                />
                <h5 class="card-title mb-0">{{ login.userName }}</h5>
                <div class="text-muted mb-2">
                  분류 : {{ clsfMatch(login.userClsf) }}
                </div>
            </div>
            <hr class="my-0" />
            <div class="card-body left-side-card">
              <h5 class="h6 card-title">관심 지역(구역)</h5>
              <div class="card-body">
                <a href="#" class="badge bg-primary fs-6 me-2 my-2"></a>
                <a href="#" class="badge bg-primary fs-6 me-2 my-2">청운동</a>
                <a href="#" class="badge bg-primary fs-6 me-2 my-2">삼성동</a> <br/>
                <a href="#" class="badge bg-primary fs-6 me-2 my-2">강남</a>
                <a href="#" class="badge bg-primary fs-6 me-2 my-3">판교</a>
                <a href="#" class="badge bg-primary fs-6 me-2 my-2">동대문</a><br />
                <a href="#" class="badge bg-primary fs-6 me-2 my-2">송파구</a>
              </div>
            </div>
            <hr class="my-0" />
            <div class="card-body left-side-card">
              <h5 class="h6 card-title mb-3">거래중인 매물</h5>
              <ul class="list-unstyled">
                <li class="mb-3"><a href="#">순영웰라이빌1</a></li>
                <li class="mb-3"><a href="#">e편한세상신촌1단지</a></li>
                <li class="mb-3"><a href="#">사당롯데캐슬2차</a></li>
                <li class="mb-3"><a href="#">방배아크로타워</a></li>
              </ul>
            </div>
          </div>
          </div>
          <!-- 매물 목록 -->
          <div class="col-md-8 col-xl-9">
            <div class="card interest-news">
              <div class="card-header">
                <h5 class="card-title mb-0">관심 매물 목록</h5>
              </div>
              <div class="card-body h-100">
                <table class="table top-selling-table table-hover">
              <thead>
                <tr>
                  <th>
                    <h6 class="text-sm text-medium text-center">매물</h6>
                  </th>
                  <th class="min-width">
                    <h6 class="text-sm text-medium text-center">건축년도</h6>
                  </th>
                  <th class="min-width">
                    <h6 class="text-sm text-medium text-center">별점</h6>
                  </th>
                  <th class="min-width">
                    <h6 class="text-sm text-medium text-center">상태</h6>
                  </th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(interest, index) in userInterestList" v-bind:key="index">
                  <th class="min-width text-center">
                    <div class="product">
                      <div class="image">
                        <img v-bind:src="houseImg(interest.img)" alt="" />
                      </div>
                      <p class="text-sm fs-6">{{ interest.AptName }}</p>
                    </div>
                  </th>
                  <th class="min-width text-center">
                    <span v-if="interest.buildYear">{{interest.buildYear + "년"}}</span>
                  </th>
                  <th class="min-width text-center">
                    <span v-if="interest.rating" class="star-ratings">
                        <div 
                          class="star-ratings-fill space-x-2 text-lg"
                          :style="{ width: convertRating(interest.rating) }"
                        >
                          <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
                        </div>
                        <div class="star-ratings-base space-x-2 text-lg">
                          <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
                        </div>
                    </span>
                  </th>
                  <th class="min-width text-center">
                    <span v-bind:class="houseStatus(interest.status)" >{{interest.status}}</span>
                  </th>
                  <th class="min-width text-center">
                    <div class="action">
                      <button @click="confirmWithTitle(interest.AptName, interest.no)" class="text-danger">
                        <i class="lni lni-trash-can"></i>
                      </button>
                    </div>
                  </th>
                </tr>
              </tbody>
            </table>
              </div>
            </div>
            <!-- 뉴스 -->
            <div class="card interest-news">
              <div class="card-header">
                <h5 class="card-title mb-0">뉴스</h5>
              </div>
              <div class="card-body h-100">
                <div
                  class="d-flex align-items-start"
                  v-for="(articles, index) in newsData.articles"
                  v-bind:key="index"
                >
                  <img
                    v-bind:src="newsImg(index)"
                    width="36"
                    height="36"
                    class="rounded-circle me-2"
                    alt="Vanessa Tucker"
                  />
                  <div class="flex-grow-1">
                    <small class="float-end text-navy"
                      >{{ articles.author }} from {{ articles.source.name }}
                    </small>
                    <a href="#" @click="moveNews(index)"
                      ><strong>{{ articles.title }}</strong></a
                    ><br />
                    <small class="text-muted">{{ articles.publishedAt }}</small
                    ><br />
                    <hr />
                  </div>
                </div>
                <!-- <div class="d-grid">
										<a href="#"  class="btn btn-primary">Load more</a>
									</div> -->
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";

import Vue from 'vue';
import VueAlertify from 'vue-alertify'; //안되는 경우가 있음. 문제시 해결 필요
Vue.use(VueAlertify);

const userStore = "userStore";
const newsStore = "newsStore";
export default {
  data() {
    return {
      clsfCode: "",
      userSeq: "",
    };
  },
  created() {
    this.fetchNews();
    this.userSeq = this.login.userSeq;
    this.selectUserInterestHouse(this.userSeq);
  },
  computed: {
    ...mapState(userStore, ["login", "userInterestList"]),
    ...mapState(newsStore, ["newsData"]),

    newsImg() {
      return (num) => {
        if (
          this.newsData.articles[num].urlToImage === "" ||
          this.newsData.articles[num].urlToImage === null
        ) {
          this.newsData.articles[num].urlToImage =
            "/img/noProfile.0b0a90e9.png";
        }
        // console.log(this.newsData.articles[1])
        return `${this.newsData.articles[num].urlToImage}`;
      };
    },

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

    houseImg() {
      return (img) => {
        if (img === "") {
          img = "기본이미지.jpg";
        }

        return require(`@/assets/images/house/${img}`);
      };
    },

    houseStatus() {
      return (status) => {
        let classValue = "";

        switch(status) {
          case "계약됨": classValue = "success-btn"; break;
          case "계약중": classValue = "warning-btn"; break;
          case "취소됨": classValue = "close-btn"; break;
          case "판매중": classValue = "secondary-btn"; break;
        }

        return `status-btn ${classValue}`;
      }
    },

    convertRating() {
      return (rating) => {
        if( !rating ) return;

        rating *= 20; // 0 ~ 100%값으로 바꿈
        // return값에 1.5를 더하여 주는 이유는 half star일 시 미세하게 절반이 안되어보여서 보여지는 값을 조정
        return rating + 1.5 + '%';
      }
    },
  },
  methods: {
    ...mapActions(newsStore, ["fetchNews"]),
    ...mapActions(userStore, ["selectUserInterestHouse", "deleteUserInterestHouse"]),
    // ...mapMutations(newsStore,["setNews"]),
    // clsfMatch(login.clsf) {

    moveNews(num) {
      // console.log(this.newsData.articles[num].url);
      location.href = this.newsData.articles[num].url;
    },
    clsfMatch(num) {
      if (num == "0") {
        return "관리자";
      } else if (num == "1") {
        return "개인회원";
      } else if (num == "2") {
        return "기업회원";
      }
    },
    confirmWithTitle: function(aptName, houseNo) {
      this.$alertify.confirm(
        `${aptName}를 관심매물에서 삭제하시겠습니까?`,
        () => {
          this.deleteUserInterestHouse(houseNo);
          this.$alertify.success('삭제되었습니다')
          // this.userSeq = this.login.userSeq;

          // this.selectUserInterestHouse(this.userSeq);
        },
        () => {
          this.$alertify.error('취소되었습니다.')
        }
      );
    },
    // }
  },
};
</script>

<style>
.left-side-card {
  min-height: 255px;
  max-height: 255px;
  overflow-y: scroll;
}

.interest-news {
  min-height: 400px;
  max-height: 400px;
  overflow-y: scroll; /* scroll : 범위를 벗어나면 스크롤, hidden은 아예 안보이게, x, y축 조정 가능 */
  margin-bottom: 15px;
}
</style>
