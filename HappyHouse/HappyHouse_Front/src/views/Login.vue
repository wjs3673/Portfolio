<template>
  <div class="container" align="center">
    <div class="col-lg-6 login" align="center">
      <div class="header">
        <h1>Login</h1>
      </div>
      <table>
        <tr>
          <td>아이디</td>
          <td>
            <b-form-input
              type="text"
              id="id"
              v-model="user.id"
              required
              placeholder="아이디 입력...."
            />
          </td>
        </tr>
        <tr>
          <td>비밀번호</td>
          <td>
            <b-form-input
              type="text"
              id="password"
              v-model="user.password"
              required
              placeholder="비밀번호 입력...."
              @keypress.enter="login"
            />
          </td>
        </tr>
      </table>

      <br />
      <div>
        <input type="checkbox" id="idsave" v-model="idsave" />
        <label for="idsave">
          아이디저장
        </label>
      </div>
      <div>
        <b-button class="button" variant="primary" @click="login">
          로그인
        </b-button>
        <b-button class="button" variant="primary">비밀번호찾기</b-button>
      </div>
    </div>
  </div>
</template>
<script>
import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

export default {
  name: 'Login',
  data: function() {
    return {
      user: {
        id: '',
        password: '',
      },
      message: '',
      idsave: false,
    };
  },
  created() {
    this.user.id = this.$cookie.get('idsave');
    if (this.user.id != null) this.idsave = true;
  },
  computed: {
    nextRoute() {
      return this.$route.params.nextRoute ? this.$route.params.nextRoute : '';
    },
  },
  methods: {
    login: function() {
      //콘솔창 찍어보기
      console.log(this.user.id);

      if (this.idsave) {
        this.$cookie.set('idsave', this.user.id);
      } else {
        this.$cookie.delete('idsave');
      }
      // LOGIN 액션 실행
      // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
      this.$store
        .dispatch('LOGIN', this.user)
        .then(() => this.$router.replace(`/${this.nextRoute}`))
        .catch(({ message }) => (this.msg = message));
    },
  },
};
</script>

<style>
.button {
  margin: 0 2pt;
}
</style>
