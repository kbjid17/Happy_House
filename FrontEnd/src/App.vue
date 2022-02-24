<template>
  <div id="app">
    <side-bar id="sidebar-style" ref="sidebar"/>
    <main class="main-wrapper" ref="main">
      <nav-bar ref="navbar"/>
      <router-view />
    </main>

  </div>
</template>

<script>
import NavBar from "@/views/NavBar.vue";
import SideBar from "@/views/SideBar.vue";
// import FooterVue from "@/views/FooterVue.vue";

export default {
  name: "App",
  components: {
    NavBar,
    SideBar,
    // FooterVue,
  },
  mounted() {
    // 뷰에서의 접근 방법
    const sidebarNavWrapper = this.$refs.sidebar.$refs.aside; // SideBar
    const overlay = this.$refs.sidebar.$refs.overlay; // SideBar
    const menuToggleButton = this.$refs.navbar.$refs.menuToggle; // NavBar
    const menuToggleButtonIcon = this.$refs.navbar.$refs.menuToggleI; // NavBar
    const mainWrapper = this.$refs.main; // App.vue

    // const sidebarNavWrapper = SideBar.querySelector(".sidebar-nav-wrapper"); // sidebar
    // const mainWrapper = this.querySelector(".main-wrapper"); // App.vue
    // const menuToggleButton = NavBar.querySelector("#menu-toggle"); // NavBar
    // const menuToggleButtonIcon = NavBar.querySelector("#menu-toggle i"); // NavBar
    // const overlay = SideBar.querySelector(".overlay"); // SideBar

    menuToggleButton.addEventListener("click", () => {
      sidebarNavWrapper.classList.toggle("active");
      overlay.classList.add("active");
      mainWrapper.classList.toggle("active");

      if (document.body.clientWidth > 1200) {
        if (menuToggleButtonIcon.classList.contains("lni-chevron-left")) {
          menuToggleButtonIcon.classList.remove("lni-chevron-left");
          menuToggleButtonIcon.classList.add("lni-menu");
        } else {
          menuToggleButtonIcon.classList.remove("lni-menu");
          menuToggleButtonIcon.classList.add("lni-chevron-left");
        }
      } else {
        if (menuToggleButtonIcon.classList.contains("lni-chevron-left")) {
          menuToggleButtonIcon.classList.remove("lni-chevron-left");
          menuToggleButtonIcon.classList.add("lni-menu");
        }
      }
    });
    overlay.addEventListener("click", () => {
      sidebarNavWrapper.classList.remove("active");
      overlay.classList.remove("active");
      mainWrapper.classList.remove("active");
    });
  }
};
</script>

<style>
.main-wrapper {
  z-index: 0;
}

#sidebar-style {
  z-index: 100;
}

/* SCROLL */
::-webkit-scrollbar { width: 10px; }
/* 스크롤바의 width */
::-webkit-scrollbar-track { 
  background-color: #f0f0f0; 
  border-radius: 5px; 
}
/* 스크롤바의 전체 배경색 */
::-webkit-scrollbar-thumb { 
    background: #B2EBF4; 
    border-radius: 5px;
}
/* 스크롤바 색 */
::-webkit-scrollbar-button { display: none; }
/* 위 아래 버튼 (버튼 없애기를 함) */

</style>
