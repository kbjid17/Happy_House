import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/views/Home.vue";
import Login from "@/views/user/Login.vue"
import Register from "@/views/user/Register.vue"
import MyPage from "@/views/user/MyPage.vue";
import Config from "@/views/user/Config.vue";
import BoardMain from "@/views/board/BoardMain.vue";
import BoardInsert from "@/views/board/BoardInsert.vue";
import BoardUpdate from "@/views/board/BoardUpdate.vue";
import BoardDetail from "@/views/board/BoardDetail.vue";
import NoticeMain from "@/views/notice/NoticeMain.vue";
import NoticeDetail from "@/views/notice/NoticeDetail.vue";
import NoticeInsert from "@/views/notice/NoticeInsert.vue";
import NoticeUpdate from "@/views/notice/NoticeUpdate.vue";

import HouseDetail from "@/views/house/HouseDetail.vue"
import HouseList from "@/views/house/HouseList.vue"
import Map from "@/views/Map.vue"
import Chat from "@/views/Chat.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    component: Home,
  },
  {
    path: "/home",
    name: "Home",
    component: Home,
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/register",
    name: "Register",
    component: Register,
  },
  {
    path: "/myPage",
    name: "MyPage",
    component: MyPage,
  },
  {
    path: "/config",
    name: "Config",
    component: Config,
  },
  {
    path: "/boards",
    name: "Boards",
    component: BoardMain,
  },
  {
    path: "/boardInsert",
    name: "BoardInsert",
    component: BoardInsert,
  },
  {
    path: "/boardUpdate",
    name: "BoardUpdate",
    component: BoardUpdate,
  },
  {
    path: "/boardDetail",
    name: "BoardDetail",
    component: BoardDetail,
  },
  {
    path: "/notices",
    name: "Notices",
    component: NoticeMain,
  },
  {
    path: "/noticeInsert",
    name: "NoticeInsert",
    component: NoticeInsert,
  },
  {
    path: "/noticeUpdate",
    name: "NoticeUpdate",
    component: NoticeUpdate,
  },
  {
    path: "/noticeDetail",
    name: "NoticeDetail",
    component: NoticeDetail,
  },
  {
    path: "/Map",
    name: "Map",
    component: Map,
  },
  {
    path: "/houseList",
    name: "HouseList",
    component: HouseList,
  },
  {
    path: "/houseDetail",
    name: "HouseDetail",
    component: HouseDetail,
  },
  {
    path: "/chat",
    name: "Chat",
    component: Chat,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;