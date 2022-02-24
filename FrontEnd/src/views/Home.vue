<template>
  <section class="signin-section">
    <!-- start of header-content -->
    <div class="header-content">
      <div class="container">
        <div class="row">
          <div class="col-lg-12">
            <h1 class="h1-large">An amazing house is waiting for you</h1>
            <a class="btn-solid-lg h1-large" href="#" @click="moveRegister"
              >Sign Up</a
            >
          </div>
          <!-- end of col -->
        </div>
        <!-- end of row -->
      </div>
      <!-- end of container -->
    </div>
    <!-- end of header-content -->

    <!-- Video Background -->
    <div id="background-alpha" class="container-fluid"></div>
    <video
      autoplay
      class="container-fluid"
      loop
      muted
      id="video-background"
      playsinline
    >
      <source v-bind:src="videoSrc" type="video/mp4" />
    </video>
    <!-- end of video background -->
  </section>
</template>

<script>
export default {
  data() {
    return {
      videoSrc: require("../assets/images/video/main-background-video1.mp4"),
    };
  },
  mounted() {
    // 이미지 경로는 require를 붙이면 원본 경로로 가게 되는데
    // 비디오의 경우는 require를 붙여서 확인 해보면 다른 경로로 가게 된다.
    // 이 부분에 대해서는 이미지, 비디오 처리가 다른 부분이 있는듯, 확인이 필요함.
    let ranNum = Math.floor(Math.random() * 3) + 1;
    // console.log(ranNum);
    let fileName = `main-background-video${ranNum}.mp4`;
    this.videoSrc = require("../assets/images/video/" + fileName);
    // console.log(this.videoSrc);
  },
  methods: {
    moveRegister() {
      if (this.$route.path !== "/register") this.$router.push("/register");
    },
  },
};
</script>

<style>
section {
  position: relative;
}

section #video-background {
  position: absolute;
  top: 0;
  bottom: 0;
  min-width: 100%;
  min-height: 100%;
  width: auto;
  height: auto;
  z-index: -100;
}

section .header-content {
  padding-top: 20rem;
  padding-bottom: 9rem;
  background-size: cover;
  text-align: center;
}

section .h1-large {
  margin-bottom: 2.25rem;
  color: #f4f4f4;
  font-size: 4.5rem;
  line-height: 5rem;
  z-index: 200;

  color: black;
  text-shadow:
  -2px -2px 0 rgb(255, 255, 255),
  2px -2px 0 rgb(255, 255, 255),
  -2px 2px 0 rgb(255, 255, 255),
  2px 2px 0 rgb(255, 255, 255);  
}

/* 비디오에 투명도 주기 */
/* #background-alpha {
   position: absolute;
   top: 0;
   bottom: 0;
   min-width: 100%;
   min-height: 100%;
   width: auto;
   height: auto;
   z-index: 100;
	
	background-size: cover;
} */
</style>
