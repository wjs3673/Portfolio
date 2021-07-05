<template>
  <div class="col-md-12">
    <div class="card card-container">
      <img
        id="profile-img"
        src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
        class="profile-img-card"
      />
      <form name="form" @submit.prevent>
        <div v-if="!successful">
          <!-- userid; username; userpwd; email; address; -->

          <div class="form-group">
            <label for="email">E-mail</label>
            <input
              type="text"
              class="form-control"
              name="email"
              placeholder="Enter your Email"
              v-model="user.email"
            />
            <div class="alert-danger" v-if="submitted && errors.has('email')">
              {{ errors.first("email") }}
            </div>
          </div>
          <div class="form-group">
            <button class="btn btn-primary btn-block" @click="makeauth">
              인증번호 발송
            </button>
          </div>
          <div class="form-group">
            <label for="authnum">Auth Number</label>
            <input
              type="text"
              class="form-control"
              name="authnum"
              placeholder="인증번호를 입력하세요."
              v-model="authnum"
            />
          </div>
          <div class="form-group">
            <button class="btn btn-primary btn-block" @click="checkauth">
              확인
            </button>
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input
              type="password"
              class="form-control"
              name="password"
              placeholder="Enter your Password"
              v-model="user.password"
              :disabled="!isauth"
            />
            <div
              class="alert-danger"
              v-if="submitted && errors.has('password')"
            >
              {{ errors.first("password") }}
            </div>
          </div>

          <div class="form-group">
            <label for="name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              placeholder="Enter your Name"
              v-model="user.name"
              :disabled="!isauth"
            />
            <div class="alert-danger" v-if="submitted && errors.has('name')">
              {{ errors.first("name") }}
            </div>
          </div>
          <div class="form-group">
            <label for="phone">PhoneNumber</label>
            <input
              type="text"
              class="form-control"
              name="phone"
              placeholder="'-' 없이 입력하세요"
              v-model="user.phone"
              :disabled="!isauth"
            />
            <div class="alert-danger" v-if="submitted && errors.has('phone')">
              {{ errors.first("phone") }}
            </div>
          </div>
          <div class="form-group">
            <button class="btn btn-primary btn-block" @click="regist">
              Sign Up
            </button>
          </div>
        </div>
      </form>

      <div
        class="alert"
        :class="successful ? 'alert-success' : 'alert-danger'"
        v-if="message"
      >
        {{ message }}
      </div>
    </div>
  </div>
</template>

<script>
// import func from "../../../vue-temp/vue-editor-bridge";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "register",
  data() {
    return {
      user: {
        email: "",
        password: "",
        name: "",
        phone: "",
      },
      submitted: false,
      successful: false,
      message: "",
      istrue: false,
      isauth: false,
      authnum: "",
    };
  },
  // mounted() {
  //   if (this.loggedIn) {
  //     this.$router.push('/');
  //   }
  // },
  methods: {
    regist: function () {
      let user = this.user;
      axios
        .post(`${SERVER_URL}/user/signup`, user)
        .then((response) => {
          this.$router.push("/login");
        })
        .catch(({ message }) => {
          this.msg = message;
        });
    },
    makeauth: function () {
      let user = this.user;
      axios
        .post(`${SERVER_URL}/user/checkmail`, user)
        .then((response) => {
          console.log(response.data);
          this.istrue = response.data;
          alert("인증번호가 발송되었습니다.");
        })
        .catch(({ message }) => {
          this.msg = message;
        });
    },
    checkauth: function () {
      axios
        .get(`${SERVER_URL}/user/authemail`, {
          params: {
            email: this.user.email,
            auth_num: this.authnum,
          },
        })
        .then((response) => {
          console.log(response.data);
          this.isauth = response.data;
          if (this.isauth) {
            alert("인증에 성공하였습니다.");
          } else {
            alert("인증 실패. 다시 시도해주세요.");
          }
        })
        .catch(({ message }) => {
          this.msg = message;
        });
    },
    // checkpass: function() {
    //   var pw = $("#password").val();
    //   var num = pw.search(/[0-9]/g);
    //   var eng = pw.search(/[a-z]/gi);
    //   var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

    //   if (pw.length < 10 || pw.length > 20) {
    //     alert("10자리 ~ 20자리 이내로 입력해주세요.");
    //     return false;
    //   } else if (pw.search(/\s/) != -1) {
    //     alert("비밀번호는 공백 없이 입력해주세요.");
    //     return false;
    //   } else if (
    //     (num < 0 && eng < 0)
    //     (eng < 0 && spe < 0)
    //     (spe < 0 && num < 0)
    //   ) {
    //     alert("영문,숫자, 특수문자 중 2가지 이상을 혼합하여 입력해주세요.");
    //     return false;
    //   } else {
    //     console.log("통과");
    //   }
    // }
  },
};
</script>

<style scoped>
label {
  display: block;
  margin-top: 10px;
}

.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}

.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}
</style>
