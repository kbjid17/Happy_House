<template> 


<div class="page-container"> 

  <div class="card-style mb-30">
    <h6 class="mb-25">My Chat App</h6>
    <div class="input-style-1">
      <label>상담창</label>
      <textarea  class="container-fluid text " v-model="textarea" disabled v-auto-scroll-bottom rows="5"></textarea>
    </div>
    <!-- end textarea -->
    <div class="input-style-3">
      <textarea v-on:keyup.enter="sendMessage" placeholder="내용을 입력하세요." rows="5" v-model="message"></textarea>
      <span class="icon"
        ><i class="lni lni-text-format"></i
      ></span>
    </div>
    <!-- end textarea -->
    <button         @click="sendMessage"
                    id="menu-toggle"
                    class="main-btn primary-btn btn-hover"
                  >
                    <i class="lni lni-chevron-right me-2"></i> input
                  </button>
  </div> 
      
  </div> 
</template>

<script> 
import { mapState } from 'vuex';
const userStore = "userStore";
export default { 
  name: 'Chat', created() {
    this.$socket.on('chat', (data)=> { 
      this.textarea += data.message + "\n" 
      }) 
    }, 
    data() { 
      return { 
        textarea: "", 
        message: '', 
        }
      },
      computed : {
        ...mapState(userStore,["login"])
      },
    methods: { 
      sendMessage () { 
        this.$socket.emit('chat',{
          message: this.message 
          }); 
          
          this.textarea += this.login.userName + " : " +  this.message + "\n" 
          this.message = '' 
          } 
        } 
      } 
</script>

<style> 
.md-app { 
  height: 800px; 
  border: 1px solid rgba(#000, .12);
} 
.md-textarea { 
  height: 300px; 
  }
  .md-primary {
    text-align: center;
    background: yellowgreen;
  }
  .md-textarea {
    background: #ffffff;
  }
  </style>