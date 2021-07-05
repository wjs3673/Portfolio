<template>
  <card title="check" :sub-title="`${message}`">
    <div class="row">
      <div class="col-md-5">
        <fg-input
          type="text"
          label="email"
          :readonly="true"
          v-model="user.email"
        >
        </fg-input>
      </div>
    </div>

    <div class="row">
      <div class="col-md-6">
        <fg-input
          type="password"
          label="Password Confirm"
          placeholder="본인의 비밀번호를 입력해주세요"
          v-model="user.password"
        >
        </fg-input>
      </div>
    </div>

    <div class="row">
      <div class="col-md-6">
        <button @click="check" class="col-md-6 btn btn-dark button"
          >본인확인</button
        >
      </div>
    </div>
  </card>
</template>

<script>
import axios from "axios";

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  data() {
    return {
      user: {
        email: ""
      },
      message: ""
    };
  },
  methods: {
    check: function() {
      // LOGIN 액션 실행
      // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
      this.$store
        .dispatch("LOGIN", this.user)
        .then(() => this.$router.push(`/user/update`))
        // .then(() => this.$router.replace(`/${this.nextRoute}`))
        .catch(({ message }) => (this.message = "비밀번호를 확인해주세요."));
    }
  },
  created() {
    this.user.email = this.$store.getters["getEmail"];
  }
};
</script>

<style></style>
