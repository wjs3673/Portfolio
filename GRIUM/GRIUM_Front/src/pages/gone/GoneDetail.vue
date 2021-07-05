<template>
  <div class="row">
    <div class="col-8">
      <card class="height">
        <div class="text-center">
          <img :src="goneData.gone.photo_path" alt="" class="mainphoto" />
          <br />
          <h3>
            <strong>故 {{ goneData.gone.gone_nm }} 님</strong>
          </h3>
        </div>
        <!-- <div class="d-flex text-center">
          <card class="col-4">
            별세 일시 :
            {{ $moment(goneData.gone.death_dt).format("YYYY-MM-DD") }}
          </card>
          <card class="col-4">
            생년 월일 :
            {{ $moment(goneData.gone.birth_dt).format("YYYY-MM-DD") }}
          </card>
          <card class="col-4"> 상주 : {{ goneData.user.name }} </card>
        </div> -->

        <v-simple-table  height="192px">
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left">
                  <mark style="background-color: #aad4ff">PERSONAL</mark>
                </th>
                <th class="text-left">
                  <mark style="background-color: #aad4ff">FUNERAL</mark>
                </th>
                <th class="text-left">
                  <mark style="background-color: #aad4ff">ACCOUNT</mark>
                </th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td class="text-left">
                  <B>별세 일시 :</B><br />&nbsp;&nbsp;
                  {{ $moment(goneData.gone.death_dt).format("YYYY-MM-DD") }}
                </td>

                <td class="text-left">
                  <B>장례식장 이름 :</B><br />&nbsp;&nbsp;
                  {{ goneData.funeral.funeral_nm }}
                </td>
                <td class="text-left">
                  <B>입금 은행 :</B><br />&nbsp;&nbsp;
                  {{ goneData.condolance.bank }}
                </td>
              </tr>
              <tr>
                <td class="text-left">
                  <B>생년 월일 :</B><br />&nbsp;&nbsp;
                  {{ $moment(goneData.gone.birth_dt).format("YYYY-MM-DD") }}
                </td>
                <td class="text-left">
                  <B>장례식장 주소 :</B><br />&nbsp;&nbsp;
                  {{ goneData.funeral.funeral_adr }}
                </td>
                <td class="text-left">
                  <B> 계좌 번호 :</B><br />&nbsp;&nbsp;
                  {{ goneData.condolance.account }}
                </td>
              </tr>
              <tr>
                <td class="text-left">
                  <B>상주 : </B><br />&nbsp;&nbsp;
                  {{ goneData.user.name }}
                </td>
                <td></td>
                <td class="text-left">
                  <B>예금주 :</B> <br />&nbsp;&nbsp;
                  {{ goneData.condolance.holder }}
                </td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
        <!-- <div class="d-flex">
          <div class="col-6">
            <span>장례식장 이름 : {{ goneData.funeral.funeral_nm }}</span>
            <br />
            <span>장례식장 주소 : {{ goneData.funeral.funeral_adr }}</span>
          </div>
          <div class="col-6">
            <span>입금 은행 : {{ goneData.condolance.bank }}</span>
            <br />
            <span>계좌 번호 : {{ goneData.condolance.account }}</span>
            <br />
            <span>예금주 : {{ goneData.condolance.holder }}</span>
          </div>
        </div> -->
      </card>
    </div>
    <div class="col-4">
      <card class="height">
        <ChatList class="chatheight" :gone_id="gone_id" />
        <ChatInput :gone_id="gone_id" />
      </card>
    </div>
    <div class="col-12">
      <br />
      <card class="text-center">
        <div v-if="goneData.gallery.length == 0">
          <h3>아직 등록된 사진이 없습니다.</h3>
        </div>
        <div v-else>
          <card
            class="left mr-2"
            v-for="(gall, idx) in goneData.gallery"
            :key="idx"
          >
            <div v-if="gall.secret == 0">
              <img
                :src="gall.gaddress[0].server_path"
                alt=""
                width="150px"
                height="150px"
              />
              <p>{{ gall.writer }}</p>
            </div>
          </card>
        </div>
        <button class="btn btn-dark button" @click="GoPhoto">
          갤러리 보기
        </button>
      </card>
    </div>
  </div>
</template>

<script>
import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

import ChatInput from "@/components/chat/ChatInput";
import ChatList from "@/components/chat/ChatList";

export default {
  name: "GoneDetail",
  components: {
    ChatInput,
    ChatList
  },
  data: function() {
    return {
      goneData: {},
      gone_id: ""
    };
  },
  created() {
    let gone = this.$route.query.gone_id;
    this.$store.dispatch("createGone", gone);
    axios
      .get(`${SERVER_URL}/gone/list/${this.$store.state.gone_id}`)
      .then(res => {
        console.log(res.data);
        this.goneData = res.data;
        this.gone_id = res.data.gone.gone_id;
      })
      .catch(() => {
        console.log("실패");
      });
    // $route.query.gone_id
  },
  methods: {
    GoPhoto() {
      this.$router.replace("/album/photo");
    }
  }
};
</script>

<style>
.height {
  height: 500px;
}
.chatheight {
  height: 400px;
}
.left {
  float: left;
}
.mainphoto {
  height: 200px;
  width: 200px;
}
</style>
