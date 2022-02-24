<template>
<section class="container-fluid signin-section">

  <button id="table-toggle-button" type="button" class="btn btn-info"
  data-bs-toggle="collapse" data-bs-target="#houseList" aria-expanded="false" aria-controls="houseList"></button>

  <!-- 매물 목록 -->
  <div>
    <house-table id="houseList"/>
  </div>

  <!-- 카카오 맵 -->
  <div>
    <kakao-map/>
  </div>
</section>
  
</template>

<script>
import HouseTable from "@/views/house/HouseTable.vue"
import KakaoMap from "@/views/Map.vue"

import Vue from 'vue';
import VueAlertify from 'vue-alertify'; 
Vue.use(VueAlertify);

export default {
  components: {
    HouseTable, KakaoMap
  },
  data() {
    return {
    }
  },
};
</script>

<style>
#houseList {
  position: absolute; /* 부모 상대경로 */
  top: 40px; /* 위에서 부터 10px정도 떨어져라 */
  left: 50px;

  min-width: 300px;
  padding: 10px;
  z-index: 100;

  /* opacity: 0.5; */

  background-color:rgba(255, 255, 255, 0.9); /* alpha값 동시에 지정 */
}

#table-toggle-button {
  position: absolute; /* 부모 상대경로 */
  top: 0px; /* 위에서 부터 10px정도 떨어져라 */
  left: 50px;

  z-index: 200;

  border-radius: 30px;

  color: rgba(100, 192, 221);
  background-color:rgba(203, 225, 255, 0.8);
}

#table-toggle-button:after {
  content: "숨기기";
}

#table-toggle-button.collapsed:after {
  content: "보이기";
}

</style>