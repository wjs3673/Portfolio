<template>
  <form>
    <div class="row">
      <div class="col-md-5">
        <label>임시 비밀번호를 발급해드렸습니다.</label>
        <fg-input type="text" :readonly="true" v-model="user.password">
        </fg-input>
        <p>발급된 비밀번호로 로그인 후 수정 바랍니다.</p>
      </div>
    </div>

    <div class="row">
      <div class="col-md-6">
        <b-button variant="dark" @click="home" class="col-md-6"
          >홈으로</b-button
        >

        <b-button variant="dark" @click="login" class="col-md-6"
          >로그인</b-button
        >
      </div>
    </div>
  </form>
</template>


<script>
import axios from "axios";

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  data() {
    return {
      user: {
        password: "",
      },
    };
  },
  methods: {
    home: function () {
      this.$router.replace("/main");
    },
        login: function () {
      this.$router.replace("/login");
    },
  },
  created() {
    // axios
    //   .get(${SERVER_URL}/user/info)
    //   .then((response) => {
    //     this.user.password = response.data.user.password;
    //   })
    //   .catch(() => {
    //     this.$store.dispatch("LOGOUT").then(() => {
    //       this.$router.replace("/");
    //     });
    //   });
    this.user.password = this.$store.getters["getPassword"];
  },
};
</script>
