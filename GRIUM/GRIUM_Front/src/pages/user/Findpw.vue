<template>
  <card title="findpw" :sub-title="`${message}`">
    <form @submit.prevent>
      <div class="row">
        <div class="col-md-5">
          <fg-input
            type="text"
            id="email"
            v-model="user.email"
            required
            placeholder="아이디 입력...."
          ></fg-input>
        </div>
      </div>

      <div class="row">
        <div class="col-md-5">
          <fg-input
            type="text"
            id="name"
            v-model="user.name"
            required
            placeholder="이름 입력...."
            @keypress.enter="findpw"
          ></fg-input>
        </div>
      </div>
    </form>
    <b-button type="button" variant="dark" class="m-1" @click="findpw"
      >비밀번호 찾기</b-button
    >
    <b-button type="button" variant="dark" class="m-1" @click="cancel"
      >취소</b-button
    >
  </card>
</template>

<script>
import Vue from "vue";
import VueRouter from "vue-router";
import Card from "@/components/Cards/Card.vue";

Vue.use(VueRouter);

export default {
  components: { Card },
  name: "findpw",
  data: function() {
    return {
      user: {
        email: "",
        name: ""
      },
      message: ""
    };
  },
  // computed: {
  //   nextRoute() {
  //     return this.$route.params.nextRoute ? this.$route.params.nextRoute : '';
  //   },
  // },
  methods: {
    cancel: function() {
      this.$router.replace("/login");
    },
    findpw: function() {
      // LOGIN 액션 실행
      // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
      this.$store
        .dispatch("FINDPW", this.user)
        .then(() => this.$router.push('/user/findpwsuccess'))
        //.then(({ message }) => (this.message = "성공입니다 이메일 보십숑"))
        // .then(() => this.$router.replace(/${this.nextRoute}))
        .catch(
          ({ message }) => (this.message = "아이디와 이름을 확인해주세요.")
        );
    },
  }
};
</script>
