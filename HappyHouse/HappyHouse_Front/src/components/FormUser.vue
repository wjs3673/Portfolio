<template>
  <div class="container" align="center">
    <div class="col-lg-6 SignUp" align="center">
      <div class="header">
        <h1>Sign up</h1>
      </div>
      <input type="hidden" name="act" userid="act" value="join" /> <br />
      <div class="form-group" align="left">
        <label for="name"><strong>이름</strong></label>
        <input
          type="text"
          class="form-control"
          userid="username"
          name="username"
          v-model="username"
          placeholder="이름을 입력하세요..."
        />
      </div>
      <div class="form-group" align="left">
        <label for=""><strong>아이디</strong></label>
        <input
          type="text"
          class="form-control"
          userid="userid"
          name="userid"
          v-model="userid"
          placeholder="아이디를 입력하세요..."
        />
      </div>
      <div class="form-group" align="left">
        <label for=""><strong>비밀번호</strong></label>
        <input
          type="password"
          class="form-control"
          userid="userpwd"
          name="userpwd"
          v-model="userpwd"
          placeholder="비밀번호를 입력하세요..."
        />
      </div>
      <div class="form-group" align="left">
        <label for=""><strong>비밀번호재입력</strong></label>
        <input
          type="password"
          class="form-control"
          userid="pwdcheck"
          name="pwdcheck"
          placeholder="비밀번호를 다시 입력하세요..."
        />
      </div>
      <div class=" form-group" align="left">
        <label for="emailchk"><strong>이메일인증</strong></label>
        <div class="input-group">
          <input
            type="email"
            class="form-control"
            userid="emailchk"
            name="emailchk"
            v-if="type == 'join'"
            v-model="email"
            placeholder="이메일을 입력하세요..."
            required="required"
          />
          <div class="input-group-append">
            <button
              type="button"
              class="btn btn-primary button"
              userid="registerBtn"
              v-if="type == 'join'"
              @click="checkEmail"
            >
              인증받기
            </button>
          </div>
        </div>
        <br />
        <div class="input-group">
          <input
            type="email"
            class="form-control"
            userid="emailinbun"
            name="emailinbun"
            v-if="type == 'join'"
            v-model="emailinbun"
            placeholder="인증번호을 입력하세요..."
            required="required"
          />
          <div class="input-group-append">
            <button
              type="button"
              class="btn btn-primary button"
              userid="registerBtn"
              v-if="type == 'join'"
              @click="checkEmail"
            >
              인증완료
            </button>
          </div>
        </div>
      </div>
      <div class="form-group" align="left">
        <label for="tel"><strong>전화번호 </strong></label>
        <div userid="tel" class="custom-control-inline">
          <select class="form-control" userid="tel1" name="tel1" v-model="tel1">
            <option value="010" selected>010</option>
            <option value="02">02</option>
            <option value="031">031</option>
            <option value="032">032</option>
            <option value="041">041</option>
            <option value="051">051</option>
            <option value="061">061</option>
          </select>
          _
          <input
            type="text"
            class="form-control"
            userid="tel2"
            name="tel2"
            v-model="tel2"
            maxlength="4"
            placeholder=""
          />
          _
          <input
            type="text"
            class="form-control"
            userid="tel3"
            name="tel3"
            v-model="tel3"
            maxlength="4"
            placeholder=""
          />
        </div>
      </div>
      <div class="form-group" align="left">
        <label for=""><strong>주소</strong></label
        ><br />
        <input
          type="text"
          class="form-control"
          userid="address"
          name="address"
          v-model="address"
          placeholder="주소를 입력하세요..."
        />
      </div>
      <div class="form-group" align="center">
        <button
          type="button"
          class="btn btn-primary button"
          userid="registerBtn"
          v-if="type == 'join'"
          @click="checkHandler"
        >
          회원가입
        </button>
        <button class="btn btn-primary button" v-else @click="checkHandler">
          회원정보수정
        </button>
        <button @click="clear" class="btn btn-warning button">초기화</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'board-Form',
  props: {
    type: { type: String },
  },
  data: function() {
    return {
      username: '',
      userid: '',
      userpwd: '',
      tel1: '',
      tel2: '',
      tel3: '',
      address: '',
      email: '',
    };
  },
  methods: {
    checkHandler() {
      let err = true;
      let msg = '';

      if (!err) alert(msg);
      else this.type == 'join' ? this.createHandler() : this.updateHandler();
    },
    checkEmail() {
      axios
        .get(`${SERVER_URL}/email/` + this.email)
        .then(({ data }) => {
          let msg = '이메일인증 문제가 발생했습니다.';
          if (data === 'success') {
            msg = '이메일을 확인해주세요.';
          }
          alert(msg);
        })
        .catch(() => {
          alert('이메일인증 에러가 발생했습니다.');
        });
    },
    createHandler() {
      console.log(
        'createHandler' + this.tel1 + '-' + this.tel2 + '-' + this.tel3
      );
      axios
        .post(`${SERVER_URL}/user/join`, {
          name: this.username,
          id: this.userid,
          password: this.userpwd,
          phone: this.tel1 + '-' + this.tel2 + '-' + this.tel3,
          addr: this.address,
        })
        .then(({ data }) => {
          let msg = '회원등록 처리시 문제가 발생했습니다.';
          if (data === 'success') {
            msg = '회원등록이 완료되었습니다.';
          }
          alert(msg);
          this.moveLogin();
        })
        .catch(() => {
          alert('회원등록 처리시 에러가 발생했습니다.');
        });
    },
    updateHandler() {
      axios
        .put(`${SERVER_URL}/user/1`, {
          name: this.username,
          id: this.userid,
          password: this.userpwd,
          phone: this.tel1 + '-' + this.tel2 + '-' + this.tel3,
          addr: this.address,
        })
        .then(({ data }) => {
          let msg = '회원수정 처리시 문제가 발생했습니다.';
          if (data === 'success') {
            msg = '회원수정이 완료되었습니다.';
          }
          alert(msg);
          this.$router.push('/');
        })
        .catch(() => {
          alert('회원수정 처리시 에러가 발생했습니다.');
        });
    },
    moveLogin() {
      this.$router.push('/login');
    },
    clear() {
      this.username = '';
      this.userid = '';
      this.userpwd = '';
      this.tel1 = '';
      this.tel2 = '';
      this.tel3 = '';
      this.address = '';
    },
  },
  created() {
    if (this.type === 'update') {
      axios
        .get(`${SERVER_URL}/user/info`)
        .then((response) => {
          this.user = response.data.user;
          this.username = response.data.user.name;
          this.userid = response.data.user.id;
          this.userpwd = response.data.user.password;
          this.address = response.data.user.addr;
          this.tel1 = response.data.user.phone.split('-')[0];
          this.tel2 = response.data.user.phone.split('-')[1];
          this.tel3 = response.data.user.phone.split('-')[2];
        })
        .catch(() => {
          this.$store.dispatch('LOGOUT').then(() => this.$router.replace('/'));
        });
    }
  },
};
</script>
<style>
.button {
  margin: 0 2pt;
}
</style>
