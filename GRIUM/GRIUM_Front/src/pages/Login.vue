<template>
  <card title="Login" :sub-title="`${message}`">
    <form @submit.prevent>
      <div class="row">
        <div class="col-md-5">
          <fg-input
            type="text"
            id="email"
            v-model="email"
            required
            placeholder="이메일 입력..."
            v-bind:class="{
              error: error.email,
              complete: !error.email && email.length !== 0
            }"
          ></fg-input>
          <div class="error-text" v-if="error.email">{{ error.email }}</div>
          <!-- v-bind, div error text추가 -->
        </div>
      </div>

      <div class="row">
        <div class="col-md-5">
          <fg-input
            type="password"
            id="password"
            v-model="password"
            required
            placeholder="비밀번호 입력..."
            @keypress.enter="login"
            v-bind:class="{
              error: error.password,
              complete: !error.password && password.length !== 0
            }"
          ></fg-input>
          <div class="error-text" v-if="error.password">
            {{ error.password }}
          </div>
          <!-- v-bind, div error text추가 -->
        </div>
      </div>
      <div class="row">
        <div class="col-md-5">
          <b-button type="button" variant="link" class="m-1" @click="findpw">
            비밀번호를 잊으셨나요?</b-button
          >
        </div>
      </div>
    </form>
    <b-button type="button" variant="dark" class="m-1" @click="login"
      >로그인</b-button
    >
    <b-button type="button" variant="dark" class="m-1" @click="regist"
      >회원가입</b-button
    >
  </card>
</template>

<script>
import Vue from "vue";
import VueRouter from "vue-router";
import Card from "@/components/Cards/Card.vue";

// 이메일과 비밀번호를 검사하는 npm 프로그램 import
import PV from "password-validator";
import * as EmailValidator from "email-validator";

Vue.use(VueRouter);

export default {
  components: { Card },
  name: "Login",
  created() {
    this.passwordSchema
      // 최소 길이 2
      .is()
      .min(2)
      // 최대 길이 100
      .is()
      .max(100)
      // 1 자리 이상
      .has()
      .digits(1)
      // 스페이스바 금지
      .has()
      .not()
      .spaces();
    // 대문자가 있어야 함
    // .has().uppercase()
    // 소문자가 있어야 함
    // .has().lowercase()
    // 만들면 안 되는 비밀번호 - 블랙리스트 등록
    // .is().not().oneOf(['블랙리스트비번', '블랙리스트비번']);
  },
  data: function() {
    return {
      email: "",
      password: "",
      message: "",

      // 비밀번호 검사, error 검사
      passwordSchema: new PV(),
      error: {
        email: false,
        passowrd: false
      },
      isSubmit: false,
      component: this
    };
  },
  // computed: {
  //   nextRoute() {
  //     return this.$route.params.nextRoute ? this.$route.params.nextRoute : '';
  //   },
  // },

  // 계속 지켜보는 게 watch : 바뀔 때마다 확인함
  watch: {
    password: function(v) {
      this.checkForm();
    },
    email: function(v) {
      this.checkForm();
    }
  },

  methods: {
    // checkForm() 추가
    checkForm() {
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else this.error.email = false;

      if (
        this.password.length >= 0 &&
        !this.passwordSchema.validate(this.password)
      )
        this.error.password = "영문, 숫자 포함 8자리 이상이어야 합니다.";
      else this.error.password = false;

      let isSubmit = true;
      Object.values(this.error).map(v => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    // 여기까지 추가 완료
    regist: function() {
      this.$router.replace("/user/register");
    },
    login: function() {
      // 로그인을 위한 let 추가 : dispatch 에 this.user-> user
      let {email, password} = this;
      let user = { email, password};

      // LOGIN 액션 실행
      // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
      this.$store
        .dispatch("LOGIN", user)
        .then(() => this.$router.push(`/main`))
        // .then(() => this.$router.replace(`/${this.nextRoute}`))
        .catch(
          ({ message }) => (this.message = "아이디/비밀번호를 확인해주세요.")
        );
    },
    findpw: function() {
      this.$router.replace("/user/findpw");
    }
  },
  // created() {
  //   this.$store
  //     .dispatch("LOGOUT")
  //     .then(() => this.$router.replace("/login").catch(() => {}));
  // }
};
</script>

<style scope>
#login-div {
  text-align: center;
}
</style>
