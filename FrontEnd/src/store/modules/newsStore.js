import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

// import http from "@/common/axios.js";
// import router from '@/routers/routers.js'

const newsStore = {
  namespaced : true,
  state : {
    newsData:{}
  },
  mutations :{
    setNews(state, data){
      state.newsData = data;
    },
    setIsLoading(state, bool){
      state.isLoading = bool;
    }
  },
  actions : {
    fetchNews({commit}) {
      commit("setIsLoading", true)
      fetch('https://newsapi.org/v2/top-headlines?country=kr&apiKey=a1e255be46a844b8a81e7e4caf5b984d')
      .then(function(response) {
        // console.log(response);
        return response.json();
      })
      .then(function(myJson) {
        setTimeout(()=>{return commit("setIsLoading", false)},3000)
        commit("setNews", myJson)
        // console.log(myJson)
      });
    },
  }
}

export default newsStore;