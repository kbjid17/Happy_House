<template>
  <section>
    <div id="ref">
      <div id="map"></div>
      <div class="button-group"></div>
    </div>
  </section>
</template>

<script>
import "@/common/common.css";
import util from "@/common/util.js";

import { mapState, mapMutations } from "vuex";

const houseStore = "houseStore";
const addressStore = "addressStore";
const commerceStore = "commerceStore";


export default {
  name: "KakaoMap",
  data() {
    return {
      SERVICE_KEY: "",
      map: null,
      houseMarkers: [],
      houseOverlay: [],
      commerceMarkers: [],
      commerceOverlay: [],
    };
  },
  created() {
    this.SERVICE_KEY = process.env.VUE_APP_KAKAO_MAP_API_KEY;
    // this.SET_HOUSE_LIST([]);
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");

      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${this.SERVICE_KEY}`;
      document.head.appendChild(script);
    }

    this.$nextTick(function () {
      // 전체 화면내용이 렌더링된 후에 아래의 코드가 실행됩니다.
      this.displayHouseMarker(); // 화면이 넘어왔을 때 마크 다시 그리기
    })
    
  },
  computed: {
    ...mapState(houseStore, ["houses", "currentHouseIndex"]),
    ...mapState(commerceStore, ["commerces", "isVisibleCommerce"]),
    ...mapState(addressStore, ["dongLat", "dongLng"]),
  },
  watch: {
    // 위도 값이 바뀌면 호출
    dongLat: function () {
      // console.log("watch호출");
      this.panTo();
    },

    // 선택한 집이 바뀌면 호출
    currentHouseIndex: function() {
      // console.log("currentHouseIndex 변경 값 ==> " + this.currentHouseIndex);
      // console.log(this.houseOverlay);
      this.hideMarkers( this.houseOverlay );
      this.showMarkers( [ this.houseOverlay[this.currentHouseIndex] ] );
      // console.log("변경후");
      // console.log( this.houseOverlay );
    },

    // 집 목록들이 바뀌면 호출
    houses: function () {
      // 새로운 마커들 생성 전에 지우기
      this.hideMarkers(this.houseMarkers);
      this.houseMarkers = []; // 배열 비우기

      // 새로운 오버레이들 생성 전에 지우기
      this.hideMarkers(this.houseOverlay);
      this.houseOverlay = []; // 배열 비우기

      // 마커와 오버레이 생성 표시
      this.displayHouseMarker();
    },

    // 상가 목록이 바뀌면 호출
    commerces: function () {
      // 새로운 마커들이 보이기 전에 지우고 다시 보이게
      this.hideMarkers(this.commerceMarkers);
      this.commerceMarkers = []; // 배열 비우기

      // 새로운 오버레이들이 보이기 전에 지우고 다시 보이게
      this.hideMarkers(this.commerceOverlay);
      this.commerceOverlay = []; // 배열 비우기

      // 마커와 오버레이 생성 및 표시
      this.displayCommerceMarker();

      // 상가 목록 보인다는 표시
      this.SET_IS_VISIBLE_COMMERCE(true);
      
      //console.log(this.isVisibleCommerce);
    },

    // 상가목록 지우기 -> 상가목록이 false가 되면 호출
    isVisibleCommerce: function () {
      if( this.isVisibleCommerce == true ) return;

      this.hideMarkers(this.commerceMarkers);
      this.hideMarkers(this.commerceOverlay);
    }
  },
  methods: {
    ...mapMutations(commerceStore, ["SET_IS_VISIBLE_COMMERCE"]),
    ...mapMutations(houseStore, ["SET_HOUSE_LIST"]),

    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.5012743, 127.039585),
        level: 3,
      };
      this.map = new kakao.maps.Map(container, options);
    },

    // 이동할 좌표로 부드럽게 이동
    panTo() {
      // console.log("지도 중심 이동 호출됨");

      // 이동할 위도 경도 위치를 생성합니다
      var moveLatLng = new kakao.maps.LatLng(this.dongLat, this.dongLng);

      // 지도 중심을 부드럽게 이동시킵니다
      // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
      this.map.panTo(moveLatLng);
    },

    displayHouseMarker() {
      for (var i = 0; i < this.houses.length; i++) {
        var imageSrc = require("@/assets/images/map/집.png");

        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(50, 50);

        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: new kakao.maps.LatLng(
            this.houses[i].lat,
            this.houses[i].lng
          ), // 마커를 표시할 위치
          image: markerImage, // 마커 이미지
        });

        var customOverlay = this.createdHouseCustomOveray(i);

        kakao.maps.event.addListener(
          marker,
          "click",
          this.makeOverListener(this.map, customOverlay)
        );

        this.houseMarkers.push(marker);
        this.houseOverlay.push(customOverlay);
      }
    },

    createdHouseCustomOveray(i) {
      var customOverlay = new kakao.maps.CustomOverlay({
          position: new kakao.maps.LatLng(
            this.houses[i].lat,
            this.houses[i].lng
          ),
          xAnchor: 0.3,
          yAnchor: 1.1,
        });

        /**************************** 카카오 맵 API에서 제공한 방법 ****************************/

        // var content = `<div class="overlaybox">
        //     <div class="first">
        //       <img style="width:247px; height:136px;" 
        //         src=${require("@/assets/images/house/" + this.houses[i].img)} />
        //       <img id="close-btn" class="close" src="${require("@/assets/images/map/close.png")}" 
        //       ref="closeBtn${i}" />
        //       <div class="movietitle text">${this.houses[i].AptName}</div>
        //     </div>
        //     <ul>
        //       <li class="li-odd">
        //         <span class="li-title">주소</span>
        //         <span class="li-value">
        //         ${this.houses[i].city + " " + this.houses[i].gugun + " " 
        //         + this.houses[i].dong + " " + this.houses[i].jibun}</span>
        //       </li>
        //       <li class="li-even">
        //         <span class="li-title">평균시세</span>
        //         <span class="li-value">${ util.convertMoney( this.houses[i].dealAmount ) }</span>
        //       </li>
        //       <li class="li-odd">
        //         <span class="li-title">평균평수</span>
        //         <span class="li-value">${ util.convertArea( this.houses[i].area ) }</span>
        //       </li>
        //       <li class="li-even">
        //         <span class="li-title">건축년도</span>
        //         <span class="li-value">${this.houses[i].buildYear + "년"}</span>
        //       </li>
        //       <li class="li-odd">
        //         <span class="li-title">별점</span>
        //         <span class="li-value"></span>
        //       </li>
        //     </ul>
        //   </div>`;
        /************************************************************************************/

        /*********** DOM 객체에 직접 이벤트 연결하려면 DOM 객체를 생성해서 해야함 **********************/
        var content = document.createElement('div');
        content.classList.add('overlaybox');
        content.style.cssText = 'z-index: 100';

        var divFirst = document.createElement('div');
        divFirst.classList.add('first');

        var houseImage = document.createElement('img');
        houseImage.style.cssText = 'width:247px; height:136px';
        houseImage.src = require(`@/assets/images/house/${this.houses[i].img}`);

        var closeBtn = document.createElement('img');
        closeBtn.id = 'close-btn';
        closeBtn.classList.add('close');
        closeBtn.src = require("@/assets/images/map/close.png");

        // 고생한부분 - 여기에 직접 함수를 쓰면 첫번째 오버레이만 꺼지고 나머지는 이벤트 작동을 하지 않음
        // methods에 선언된 makeOutListener 호출해 함수를 받아서 넣어주면 오버레이들이 다 꺼지고 작동가능
        // 뷰에서는 함수에 함수를 직접적으로 넣으면 안되고 methods쪽에서 불러서 넣어줘야 하는 형식인가 봄 -> 예측
        // 함수를 여기서 쓰니 바깥에 놔두라는 에러도 남.
        closeBtn.onclick = this.makeOutListener(customOverlay);

        var movietitle = document.createElement('div');
        movietitle.innerText = this.houses[i].AptName;
        movietitle.classList.add('movietitle', 'text');

        content.appendChild(divFirst);
        divFirst.appendChild(houseImage);
        divFirst.appendChild(closeBtn);
        divFirst.appendChild(movietitle);

        var ulDom = document.createElement('ul');

        var liAddress = this.liCreate('li-odd', '주소', this.convertAddress(this.houses[i]) );
        var liDealAmount = this.liCreate('li-even', '평균시세', util.convertMoney( this.houses[i].dealAmount ) );
        var liArea = this.liCreate('li-odd', '평균평수', util.convertArea( this.houses[i].area ) );
        var liBuildYear = this.liCreate('li-even', '건축년도', this.houses[i].buildYear + "년" );
        var liRating = this.liCreateStar( this.houses[i].rating );

        ulDom.appendChild(liAddress);
        ulDom.appendChild(liDealAmount);
        ulDom.appendChild(liArea);
        ulDom.appendChild(liBuildYear);
        ulDom.appendChild(liRating);
        content.appendChild(ulDom);
        /************************************************************************************/

        // customOverlay에 생성한 DOM객체 연결
        customOverlay.setContent(content);

        // 들어간 html 확인용
        // console.log(customOverlay.cc); 

        return customOverlay;
    },

    displayCommerceMarker() {
      for (var i = 0; i < this.commerces.length; i++) {
        var imageSrc = require(`@/assets/images/map/${this.commerces[i].branch}.png`);

        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(50, 50);

        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: new kakao.maps.LatLng(
            this.commerces[i].lat,
            this.commerces[i].lng
          ), // 마커를 표시할 위치
          image: markerImage, // 마커 이미지
        });

        var customOverlay = this.createdCommerceCustomOveray(i);

        kakao.maps.event.addListener(
          marker,
          "click",
          this.makeOverListener(this.map, customOverlay)
        );

        this.commerceMarkers.push(marker);
        this.commerceOverlay.push(customOverlay);
      }
    },

    createdCommerceCustomOveray(i) {
      var customOverlay = new kakao.maps.CustomOverlay({
          position: new kakao.maps.LatLng(
            this.commerces[i].lat,
            this.commerces[i].lng
          ),
          xAnchor: 0.3,
          yAnchor: 1.1,
        });

        var content = document.createElement('div');
        content.classList.add('overlaybox');
        content.style.cssText = 'z-index: 100';

        var divFirst = document.createElement('div');
        divFirst.classList.add('first');

        var commerceImage = document.createElement('img');
        commerceImage.style.cssText = 'width:247px; height:136px';
        commerceImage.src = require(`@/assets/images/map/${this.commerces[i].branch}.png`);

        var closeBtn = document.createElement('img');
        closeBtn.id = 'close-btn';
        closeBtn.classList.add('close');
        closeBtn.src = require("@/assets/images/map/closeblack.png");

        closeBtn.onclick = this.makeOutListener(customOverlay);

        var movietitle = document.createElement('div');
        movietitle.innerText = this.commerces[i].titleName;
        movietitle.classList.add('movietitle', 'text');

        content.appendChild(divFirst);
        divFirst.appendChild(commerceImage);
        divFirst.appendChild(closeBtn);
        divFirst.appendChild(movietitle);

        var ulDom = document.createElement('ul');

        var liLocationName = this.liCreate('li-odd', '지점명', this.commerces[i].locationName );
        var liAddress = this.liCreate('li-even', '주소', this.commerces[i].address );
        var liBranch = this.liCreate('li-odd', '업종', this.commerces[i].branch );
        var liBranchDetail = this.liCreate('li-even', '상세', this.commerces[i].branchDetail );
        var liRating = this.liCreateStar( this.commerces[i].rating );

        ulDom.appendChild(liLocationName);
        ulDom.appendChild(liAddress);
        ulDom.appendChild(liBranch);
        ulDom.appendChild(liBranchDetail);
        ulDom.appendChild(liRating);
        content.appendChild(ulDom);
        /************************************************************************************/

        // customOverlay에 생성한 DOM객체 연결
        customOverlay.setContent(content);

        // 들어간 html 확인용
        // console.log(customOverlay.cc); 

        return customOverlay;
    },

    // 커스텀 오버레이를 여는 함수 연결
    makeOverListener(map, customOverlay) {
      return function () {
        customOverlay.setMap(map);
      };
    },

    // 커스텀 오버레이를 닫는 함수 연결
    makeOutListener(customOverlay) {
      return function () {
        customOverlay.setMap(null);
      }
    },

    // 배열에 추가된 마커들을 지도에 표시하거나 지우는 함수입니다
    setMarkers(markers, map) {
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(map);
        }            
    },

    // "마커 보이기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에 표시하는 함수입니다
    showMarkers(markers) {
        this.setMarkers(markers, this.map)    
    },

    // "마커 감추기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에서 삭제하는 함수입니다
    hideMarkers(markers) {
        this.setMarkers(markers, null);    
    },

    // 주소값 돌려주는 필터 함수
    convertAddress(house) {
      return house.city + " " + house.gugun + " " + house.dong + " " + house.jibun
    },

    // 리스트 생성 함수
    liCreate(classValue, titleText, valueText) {
      var tmpLi = document.createElement('li');
      var titleSpan = document.createElement('span');
      var valueSpan = document.createElement('span');

      tmpLi.classList.add(classValue);

      titleSpan.classList.add('li-title');
      titleSpan.innerText = titleText;

      valueSpan.classList.add('li-value');
      valueSpan.innerText = valueText;

      tmpLi.appendChild(titleSpan);
      tmpLi.appendChild(valueSpan);

      return tmpLi;
    },

    liCreateStar(rating) {
      var tmpLi = document.createElement('li');
      var titleSpan = document.createElement('span');
      var ratingDiv = document.createElement('div');
      var fillStar = document.createElement('div');
      var baseStar = document.createElement('div');

      tmpLi.classList.add("li-odd");

      titleSpan.classList.add('li-title', 'pe-10');
      titleSpan.innerText = '별점';

      ratingDiv.classList.add('li-value', 'star-ratings');

      fillStar.classList.add('star-ratings-fill', 'space-x-2', 'text-lg');

      fillStar.style.cssText = `width: ${util.convertRating(rating)}`;

      baseStar.classList.add('star-ratings-base', 'space-x-2', 'text-lg');

      for(var i = 0; i < 5; i++) {
        var fillSpan = document.createElement('span');
        var baseSpan = document.createElement('span');

        fillSpan.innerText = "★";
        baseSpan.innerText = "★";

        fillStar.appendChild(fillSpan);
        baseStar.appendChild(baseSpan);
      }

      ratingDiv.appendChild(fillStar);
      ratingDiv.appendChild(baseStar);

      tmpLi.appendChild(titleSpan);
      tmpLi.appendChild(ratingDiv);

      return tmpLi;
    },

  },
};
</script>

<style>
/* scoped를 해놓으니 카카오맵 커스텀 오버레이가 작동 안됨 */

#map {
  /* 최소 사이즈 지정, 너비, 높이 알아서 맞추기 */
  min-width: 400px;
  min-height: 800px;
  width: auto;
  height: auto;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}

/* 커스텀 오버레이 디자인 스타일 */

.first .close {
  position: absolute;
  top: 10px;
  right: 10px;
  width: 17px;
  height: 17px;
  z-index: 100;
}
.first .close:hover {
  cursor: pointer;
}

.overlaybox {
  position: relative;
  width: 360px;
  height: 350px;
  background-image: url("../assets/images/map/mapDetail.png");
  padding: 15px 10px;
}
.overlaybox div,
ul {
  overflow: hidden;
  margin: 0;
  padding: 0;
}
.overlaybox li {
  list-style: none;
}
.overlaybox .boxtitle {
  color: #fff;
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 8px;
}
.overlaybox .first {
  position: relative;
  width: 247px;
  height: 136px;
  margin-bottom: 8px;
}
.first .text {
  color: #fff;
  font-weight: bold;
}

.first .movietitle {
  position: absolute;
  width: 100%;
  bottom: 0;
  background: rgba(0, 0, 0, 0.4);
  padding: 7px 15px;
  font-size: 14px;
}
.overlaybox ul {
  width: 247px;
}
.overlaybox li {
  position: relative;
  margin-bottom: 5px;
  padding: 5px 10px;
  color: #000000;
  line-height: 1;
}
.overlaybox .li-odd {
  background: #d5e7f5;  
}

.overlaybox .li-even {
  background: #f3f4f5;
}

.overlaybox li span {
  display: inline-block;
}

.overlaybox li .li-title {
  font-size: 12px;
  margin-right: 5px;
}
.overlaybox li .li-value {
  font-size: 12px;
  font-weight: bold;
}

.overlaybox li:hover {
  color: #fff;
  background: #8ad3f0;
}
.overlaybox li:hover .up {
  background-position: 0 0px;
}
.overlaybox li:hover .down {
  background-position: 0 -20px;
}

</style>
