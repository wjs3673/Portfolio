<template>
  <div class="row">
    <drop-down title="꽃">
      <img src="@/assets/img/condol_basic.png" alt="" @click="chatFlower1">
      <br>
      <img src="@/assets/img/black-ribbon.png" alt="" @click="chatFlower2">
      <br>
      <img src="@/assets/img/wreath.png" alt="" @click="chatFlower3">
    </drop-down>
    <input class="col-2" placeholder="이름" :value="chat.writer" @input="inputWriter">
    <input class="col-5" placeholder="한마디" :value="chat.content" @input="inputContent">
    <button class="btn btn-dark button col-3" @click="makeChat">등록</button>
  </div>
</template>

<script>
import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "ChatInput",
  props: {
    gone_id: String,
  },
  data() {
    return {
      chat: {
        chat_dt: "",
        chat_id: "",
        content: "",
        flag: 0,
        flower: 0,
        gone_id: "",
        writer: ""
      },
    //   options: [
    //     { text: '1', value: '@/assets/condol_basic.png' },
    //     { text: '2', value: '@/assets/condol_basic.png' },
    //     { text: '3', value: '@/assets/condol_basic.png' }
    //   ]
    }
  },
  methods: {
    chatFlower1() {
      this.chat.flower = 1;
      console.log(this.chat.flower)
    },
    chatFlower2() {
      this.chat.flower = 2;
      console.log(this.chat.flower)
    },
    chatFlower3() {
      this.chat.flower = 3;
      console.log(this.chat.flower)
    },
    inputWriter(e) {
      this.chat.writer = e.target.value
    },
    inputContent(e) {
      this.chat.content = e.target.value
    },
    makeChat() {
      this.chat.gone_id = this.gone_id
      console.log(this.chat)
      axios
      .post(`${SERVER_URL}/chat/insert`, this.chat)
      .then(res => {
        console.log(res)
        console.log("성공")
        window.location.reload();
      })
      .catch(() => {
          console.log("실패")
      })
    }
  }
}
</script>

<style>

</style>