<template>
  <!-- <div class="row"> -->
    <!-- <tr class="" v-for="(chat, idx) in chatList" :key="idx">
      <td>
        <b-col>
          <img class="" v-if="chat.flower == 1" src="@/assets/img/condol_basic.png" alt="">
          <img class="" v-else-if="chat.flower == 2" src="@/assets/img/black-ribbon.png" alt="">
          <img class="" v-else src="@/assets/img/wreath.png" alt="">
        </b-col>
      </td>
      <td>
        <b-col>
          {{ chat.writer }}
        </b-col>
      </td>
      <td>
        <b-col>
          {{ chat.content }}
        </b-col>
      </td>
    </tr> -->
    <!-- <div class="col-12 d-flex" v-for="(chat, idx) in chatList" :key="idx">
       <div class="">
        <img class="" v-if="chat.flower == 1" src="@/assets/img/condol_basic.png" alt="">
        <img class="" v-else-if="chat.flower == 2" src="@/assets/img/black-ribbon.png" alt="">
        <img class="" v-else src="@/assets/img/wreath.png" alt="">
      </div>
      <div class="">{{ chat.writer }} | </div>
      <div class="">{{chat.content }}</div>
    </div> -->


<!-- 한빈 테스트용 -->
    <v-simple-table  height="300px">
    <template v-slot:default>
      <thead colspan="12">
        <tr class="b-table" v-for="(chat, idx) in chatList" :key="idx">
          <th id="flower">
            <img
              class=""
              v-if="chat.flower == 1"
              src="@/assets/img/condol_basic.png"
              alt=""
            />
            <img
              class=""
              v-else-if="chat.flower == 2"
              src="@/assets/img/black-ribbon.png"
              alt=""
            />
            <img class="" v-else src="@/assets/img/wreath.png" alt="" />
          </th>
          <th id="contents" style="text-align:left;width:5px">
            <strong>{{ chat.writer }}</strong>
          </th>

          <th id="contents2" style="text-align:left;width:15px;">
            <h5 style="font-size:12px;color:#87ceeb   ">{{ chat.content }}</h5>
          </th>
        </tr>
      </thead>
    </template></v-simple-table
  >
  <!-- </div> -->
</template>

<script>
import axios from "axios";
import Card from '../Cards/Card.vue';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  components: { Card },
  name: "ChatList",
  props: {
    gone_id: String,
  },
  data() {
    return {
      chatList: {},
    }
  },
  created() {
    setTimeout(() => {
      axios
      .get(`${SERVER_URL}/chat/list`, {
          params: {gone_id:this.gone_id}
      })
      .then(res => {
        console.log(res)
        this.chatList = res.data.chatList
      })
      .catch(() => {
        console.log("채팅 목록 가져오기 실패")
      })
    }, 100);
  }
}
</script>

<style>
  .chatheight{
    height: 450px;
  }
</style>
