<template>

<!-- ========== section start ========== -->
<section class="container-fluid signin-section row">
  <div class="container-fluid">
    <!-- ========== title-wrapper start ========== -->
    <div class="title-wrapper pt-30">
      <div class="row align-items-center">
        <div class="col-md-6">
          <div class="title mb-30">
            <h2>House Sales List</h2>
          </div>
        </div>
      </div>
      <!-- end row -->
    </div>

    <!-- 랜덤한 매물 목록 불러오기 -->
    <!-- ========== title-wrapper end ========== -->
    <div class="row">
      <div v-for="(rand, index) in housesRand" :key="index" class="col-xl-3 col-lg-4 col-sm-6">
        <div class="icon-card mb-30">
          <div class="image">
            <img v-bind:src="houseImg(rand.img)" alt="" style="width:120px; height:80px" />
          </div>
          <div class="ml-10 content">
            <h6 class="mb-10">{{rand.AptName}}</h6>
            <h4 class="text-bold mb-10">{{convertMoney(rand.recentDealAmount)}}</h4>
            
            <p v-if="rand.dealDiffPercent == '0'" class="text-sm text-dark">
              ㅡ {{rand.dealDiffPercent}}%
              <span class="text-gray">({{convertDays(rand.diffDays)}})</span>
            </p>
            <p v-else-if="rand.dealDiffPercent > 0" class="text-sm text-success">
              <i class="lni lni-arrow-up"></i> {{rand.dealDiffPercent}}%
              <span class="text-gray">({{convertDays(rand.diffDays)}})</span>
            </p>
            <p v-else class="text-sm text-danger">
              <i class="lni lni-arrow-down"></i> {{rand.dealDiffPercent}}%
              <span class="text-gray">({{convertDays(rand.diffDays)}})</span>
            </p>

          </div>
        </div>
        <!-- End Icon Cart -->
      </div>
      <!-- End Col -->

    </div>
    <!-- End Row -->

    
    <div class="row">
      <!-- 하우스 리스트 테이블 -->
      <div class="col-lg-8">
        <div class="card-style mb-30">
          <div class="title d-flex flex-wrap justify-content-between align-items-center " >
            
            <div class="left">
              <h6 class="text-medium mb-30">Search House List</h6>
            </div>
            
            <!-- 구 군/구 동 선택 -->
            <div class="row">
              <div class="col">
                <div class="select-style-1">
                  <div class="select-position select-sm">
                    <select v-model="order" class="light-bg">
                      <!-- change 이벤트 걸기 -->
                      <option value="asc">오름차순</option>
                      <option value="desc">내림차순</option>
                    </select>
                  </div>
                </div>
              </div>

              <div class="col">
                <div class="select-style-1">
                  <div class="select-position select-sm">
                    <select v-model="orderColumn" class="light-bg">
                      <!-- change 이벤트 걸기 -->
                      <option value="0">정렬기준</option> <!-- 아무것도 선택안하고 검색시 이름순 검색 -->
                      <option value="1">이름</option>
                      <option value="2">건축년도</option>
                      <option value="3">평균가격</option>
                      <option value="4">평균평수</option>
                      <option value="5">판매중인것</option>
                      <option value="6">취소된것</option>
                      <option value="7">계약중인것</option>
                      <option value="8">계약된것</option>
                    </select>
                  </div>
                </div>
              </div>

              <div class="col">
                <div class="select-style-1">
                  <div class="select-position select-sm">
                    <select
                    v-model="sidoCode"
                    @change="gugunList"
                    class="light-bg">
                      <option v-for="(sido, index) in sidos" v-bind:key="index" v-bind:value="sido.value">
                        {{sido.text}}
                      </option>
                    </select>
                  </div>
                </div>
              </div>
              <div class="col">
                <div class="select-style-1">
                  <div class="select-position select-sm">
                    <select
                    v-model="gugunCode"
                    @change="dongList"
                    class="light-bg">
                      <option v-for="(gugun, index) in guguns" v-bind:key="index" v-bind:value="gugun.value">
                        {{gugun.text}}
                      </option>
                    </select>
                  </div>
                </div>
              </div>
              <div class="col">
                <div class="select-style-1">
                  <div class="select-position select-sm">
                    <select
                    v-model="dongWord"
                    @change="searchDongHouseList"
                    class="light-bg">
                      <option v-for="(dong, index) in dongs" v-bind:key="index">
                        {{dong.text}}
                      </option>
                    </select>
                  </div>
                </div>
              </div>
            </div>
            <!-- 시 구/군 동 Title -->

          </div>
          <!-- End Title -->

          <div class="table-responsive houseDetail">
            <table class="table top-selling-table table-hover">
              <thead>
                <tr>
                  <th>
                    <h6 class="text-sm text-medium text-center">매물</h6>
                  </th>
                  <th class="min-width">
                    <h6 class="text-sm text-medium text-center">평균시세</h6>
                  </th>
                  <th class="min-width">
                    <h6 class="text-sm text-medium text-center">평균평수</h6>
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
                </tr>
              </thead>
              <tbody>
                <tr v-for="(house, index) in houses" v-bind:key="index" @click="getDealList(house.no, index)" style="cursor: pointer;">
                  <th class="min-width text-center">
                    <div class="product">
                      <div class="image">
                        <img v-bind:src="houseImg(house.img)" alt="" />
                      </div>
                      <p class="text-sm fs-6">{{ house.AptName }}</p>
                    </div>
                  </th>
                  <th class="min-width text-center">
                    <span v-text="convertMoney(house.dealAmount)"></span>
                  </th>
                  <th class="min-width text-center">
                    <span v-text="convertArea(house.area)"></span>
                  </th>
                  <th class="min-width text-center">
                    <span v-if="house.buildYear">{{house.buildYear + "년"}}</span>
                  </th>
                  <th class="min-width text-center">
                    <span v-if="house.rating" class="star-ratings">
                        <div 
                          class="star-ratings-fill space-x-2 text-lg"
                          :style="{ width: convertRating(house.rating) }"
                        >
                          <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
                        </div>
                        <div class="star-ratings-base space-x-2 text-lg">
                          <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
                        </div>
                    </span>
                  </th>
                  <th class="min-width text-center">
                    <span v-bind:class="houseStatus(house.status)" >{{house.status}}</span>
                  </th>
                </tr>
              </tbody>
            </table>
            <!-- End Table -->
          </div>
        </div>
      </div>
      <!-- End Col -->

      <div class="col-lg-4">
        <div class="card-style mb-30">
          <div class="title d-flex flex-wrap justify-content-between align-items-center" >
            
            <div class="left">
              <h5 class="text-large mb-30 fs-5">{{convertHouseName()}}</h5>
            </div>

          </div>
          
          <!-- End Title -->
          <div class="table-responsive-deal">
            <table class="table top-selling-table">
              <thead>
                <tr>
                  <th class="min-width">
                    <h6 class="text-sm text-medium text-center">거래일</h6>
                  </th>
                  <th class="min-width">
                    <h6 class="text-sm text-medium text-center">평수</h6>
                  </th>
                  <th class="min-width">
                    <h6 class="text-sm text-medium text-center">층</h6>
                  </th>
                  <th class="min-width">
                    <h6 class="text-sm text-medium text-center">거래금액</h6>
                  </th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(deal, index) in housesDeal" :key="index">
                  <td>
                    <p class="text-sm text-center">{{makeDateStr(deal.dealYear, deal.dealMonth, deal.dealDay)}}</p>
                  </td>
                  <td>
                    <p class="text-sm text-center">{{convertArea(deal.area)}}</p>
                  </td>
                  <td>
                    <p class="text-sm text-center">{{deal.floor + "층"}}</p>
                  </td>
                  <td>
                    <p class="text-sm text-center">{{convertMoney(deal.dealAmount)}}</p>
                  </td>
                </tr>
              </tbody>
            </table>
            <!-- End Table -->
          </div>
        </div>
      </div>
      <!-- End Col -->
    </div>
    <!-- End Row -->

  </div>
  <!-- end container -->
</section>
<!-- ========== section end ========== -->
</template>

<script>
import "@/common/common.css";

import { mapActions, mapMutations, mapState } from "vuex";

const houseStore = "houseStore";
const addressStore = "addressStore"

export default {
  name: "HouseTable",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongWord: '동',
      order: 'asc',
      orderColumn: 0,
      orderColumnStr: [ '', 'h.AptName', 'h.buildyear', 'd.dealAmount', 'd.area', '판매중', '취소됨', '계약중', '계약됨' ],
    }
  },
  computed: {
    ...mapState(addressStore, ["sidos", "guguns", "dongs"]),
    ...mapState(houseStore, ["houses", "housesDeal", "housesRand", "currentHouseIndex",]),

    houseImg() {
      return (img) => {
        // computed에 parameter를 받는 방법, 하지만 method를 사용 권장
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
  },
  created() {
    this.sidoList();

    this.getHouseListRand(4);

    if(this.currentHouseIndex > -1)
      this.getDealList(this.houses[this.currentHouseIndex].no, this.currentHouseIndex);
  },
  watch: {
    order: function() {
      this.searchDongHouseList(); // 정렬 순서가 바뀌면 다시 호출
    },
    orderColumn: function() {
      this.searchDongHouseList(); // 정렬 기준이 바뀌면 다시 호출
    },
  },
  methods: {
    ...mapActions(houseStore, ["getHouseList", "getHouseDongList", "getHouseDealList", "getHouseListRand", "getHouseListStatus"]),
    ...mapActions(addressStore, ["getSidoList", "getGugunList", "getDongList"]),
    ...mapMutations(addressStore, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST", "CLEAR_DONG_LIST"]),
    ...mapMutations(houseStore, ["SET_CURRENT_HOUSEL_INDEX"]),

    sidoList() {
      this.CLEAR_SIDO_LIST();
      this.getSidoList();
    },
    gugunList() {
      this.gugunCode = null;
      this.CLEAR_GUGUN_LIST();
      if (this.sidoCode) this.getGugunList(this.sidoCode);
    },
    dongList() {
      this.dongWord = '동';
      this.CLEAR_DONG_LIST();
      if (this.gugunCode) this.getDongList(this.gugunCode);
    },
    searchDongHouseList() {
      // console.log(this.dongWord);

      // 상단 기본 동 클릭시 잘못 검색하는 것 방지
      if(this.dongWord !== "동") {
        if(this.orderColumn == 0) this.orderColumn = 1;

        // console.log(this.order);
        // console.log(this.orderColumn);

        if(this.orderColumn <= 4) {
          this.getHouseDongList({
            gugunCode: this.gugunCode,
            dongWord: this.dongWord, 
            order: this.order, 
            orderColumn: this.orderColumnStr[this.orderColumn],
          });
        } else {
          this.getHouseListStatus({
            gugunCode: this.gugunCode,
            dongWord: this.dongWord, 
            order: this.order, 
            status: this.orderColumnStr[this.orderColumn],
          });
        }
      }
    },

    // 거래 목록 가져오기
    getDealList(houseNo, index) {

      if(this.houses[index].AptName == "검색된 매물이 없습니다.") return;
      
      // console.log("houseNo : " + houseNo);
      this.getHouseDealList(houseNo);
      this.SET_CURRENT_HOUSEL_INDEX(index);
    },

    convertMoney(money) {
      if( !money ) return;

      money = money.replace(",", "");

      money /= 1000;
      money.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
      return Math.round(money * 100) / 100 + "억원"
    },
    convertArea(area) {
      if( !area ) return;

      area *= 0.3025; // 평수 계산
      return Math.round(area * 100) / 100 + "평"
    },
    convertRating(rating) {
      if( !rating ) return;

      rating *= 20; // 0 ~ 100%값으로 바꿈
      // return값에 1.5를 더하여 주는 이유는 half star일 시 미세하게 절반이 안되어보여서 보여지는 값을 조정
      return rating + 1.5 + '%';
    },
    makeDateStr(year, month, day){
      return year + '.' + ( (month < 10) ? '0' + month : month ) + '.' + ( (day < 10) ? '0' + day : day );
    },
    convertHouseName() {
      // console.log(this.houses[this.currentHouseIndex])

      if( this.currentHouseIndex < 0 )
        return "Recent Sales List";
      
      return this.houses[this.currentHouseIndex].AptName + " 거래목록";
    },

    convertDays(day) {
      if(day == -1) return "";

      else if(day >= 365) return Math.ceil(day / 365) + " years ago"

      else if(day >= 30) return Math.ceil(day / 30) + " months age"

      else return day + " days ago";
    }
  },
};
</script>

<style>
.table-responsive.houseDetail {
  overflow-y: scroll; /* scroll : 범위를 벗어나면 스크롤, hidden은 아예 안보이게, x, y축 조정 가능 */
  min-height: 600px;
  max-height: 600px;
}

.table-responsive-deal {
  overflow-y: scroll;
  min-height: 620px;
  max-height: 620px;
}

tr:nth-child(even) {
  background-color: #F4FDFF;
}
</style>