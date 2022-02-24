<template>
  <div class="card-style mb-30">
    <div
      class="title d-flex flex-wrap align-items-center justify-content-between"
    >
      <div class="left">
        <h6 class="text-medium mb-30 fs-4">Sales List</h6>
      </div>
      <div class="right ml-30">
        <div class="select-style-1">
          <div class="select-position select-sm">
            <select v-model="order" class="light-bg">
              <!-- change 이벤트 걸기 -->
              <option value="asc">오름차순</option>
              <option value="desc">내림차순</option>
            </select>
          </div>
        </div>
        <!-- end select -->
      </div>
      <div class="right">
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
        <!-- end select -->
      </div>
    </div>
    <!-- End Title -->

    <!-- 구 군/구 동 선택 -->
    <div class="row">
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

    <div class="table-responsive houseTable">
      <table class="table top-selling-table table-hover">

        <tbody>
          <tr v-for="(house, index) in houses" v-bind:key="index" @click="moveHouseLatLng(index)" style="cursor: pointer;">
            <td>
              <div class="product">
                <div class="image">
                  <img v-bind:src="houseImg(house.img)" alt="" />
                </div>
                <p class="text-sm fs-6">{{ house.AptName }}</p>
              </div>
            </td>
            <td>
              <span v-bind:class="houseStatus(house.status)" >{{house.status}}</span>
            </td>
            <td>
              <div class="action justify-content-end">
                <button class="more-btn ml-10 dropdown-toggle" id="moreAction1" data-bs-toggle="dropdown" aria-expanded="false">
                  <i class="lni lni-more-alt"></i>
                </button>
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="moreAction1" style="">
                  <li class="dropdown-item">
                    <a href="#" class="text-gray">관심 매물로 등록</a>
                  </li>
                  <li class="dropdown-item">
                    <a @click="getCommerceList(index);" href="#" class="text-gray">근처 상가들 보기</a>
                  </li>
                  <li class="dropdown-item">
                    <a @click="deleteCommerceList();" href="#" class="text-gray">근처 상가들 지우기</a>
                  </li>
                  <li class="dropdown-item">
                    <a href="#" class="text-gray">평점 매기기</a>
                  </li>
                </ul>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
      <!-- End Table -->
    </div>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";

const houseStore = "houseStore";
const addressStore = "addressStore"
const commerceStore = "commerceStore"

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
    ...mapState(addressStore, ["sidos", "guguns", "dongs", "dongLat", "dongLng"]),
    ...mapState(houseStore, ["houses"]),

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
    }
  },
  created() {
    this.sidoList();
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
    ...mapActions(houseStore, ["getHouseList", "getHouseDongList", "getHouseDealList", "getHouseListStatus"]),
    ...mapActions(addressStore, ["getSidoList", "getGugunList", "getDongList", "getDongLatLng"]),
    ...mapActions(commerceStore, ["getCommerceDongList", "getCommerceLatLngList"]),
    ...mapMutations(houseStore, ["SET_CURRENT_HOUSEL_INDEX"]),
    ...mapMutations(addressStore, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST", "CLEAR_DONG_LIST", "SET_DONG_LAT", "SET_DONG_LNG"]),
    ...mapMutations(commerceStore, ["SET_IS_VISIBLE_COMMERCE"]),

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

        this.getDongLatLng(this.dongWord);
      }
    },
    // 집 - 위도, 경도로 이동
    moveHouseLatLng(index) {
      // 검색된 매물이 없는데 클릭시 undefined, undefined를 찾아가서 카카오맵이 정지
      if(this.houses[index].AptName ===  "검색된 매물이 없습니다.") return;

      console.log("index : " + index);
      this.SET_DONG_LAT(this.houses[index].lat);
      this.SET_DONG_LNG(this.houses[index].lng);
      this.SET_CURRENT_HOUSEL_INDEX(index);
    },

    // 클릭한 집 기준 약 500m 근방에 상가들 검색
    getCommerceList(index) {
      this.getCommerceLatLngList({
        startLat: this.houses[index].lat - 167/36000,
        endLat: this.houses[index].lat * 1 + 167/36000, // 문자열로 처리되어 붙어버림, 간단하게 * 1을 곱해서 숫자로 바꿔줌
        startLng: this.houses[index].lng - 208/36000, 
        endLng: this.houses[index].lng * 1 + 208/36000,
      });
    },

    // 상가목록 지우기
    deleteCommerceList() {
      this.SET_IS_VISIBLE_COMMERCE(false);
    },
  },
};
</script>

<style>
.table-responsive.houseTable {
  max-height: 600px;
  overflow-y: scroll; /* scroll : 범위를 벗어나면 스크롤, hidden은 아예 안보이게, x, y축 조정 가능 */
}

tr:nth-child(even) {
  background-color: #F4FDFF;
}
</style>
