<template>
  <card class="card" title="UPDATE" sub-title="내 정보 수정페이지입니다.">
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
      <div class="col-md-5">
        <fg-input type="text" label="password" v-model="user.password">
        </fg-input>
      </div>
    </div>
    <div class="row">
      <div class="col-md-5">
        <fg-input
          type="text"
          label="name"
          placeholder="name"
          v-model="user.name"
        >
        </fg-input>
      </div>
    </div>
    <div class="row">
      <div class="col-md-5">
        <fg-input
          type="phone"
          label="phone"
          placeholder="phone"
          v-model="user.phone"
        >
        </fg-input>
      </div>
    </div>

    <div class="row">
      <div class="col-md-5">
        <button @click="update" class="col-md-6 btn btn-dark">
          정보수정
        </button>
        <button @click="withdraw" class="col-md-6 btn btn-danger">
          회원탈퇴
        </button>
      </div>
    </div>
  </card>
</template>

<script>
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "update",
  data() {
    return {
      user: {
        email: "",
        name: "",
        password: "",
        phone: ""
      }
    };
  },
  methods: {
    update: function() {
      let user = this.user;
      console.log(user);
      axios
        .put(`${SERVER_URL}/user/edit`, user)
        .then(response => {
          // this.$router.replace("/user/profile");
          // this.$store.dispatch("LOGOUT").then(() => {
          this.$router.replace("/user/profile");
          // });
        })
        .catch(({ message }) => {
          this.msg = message;
        });
    },
    withdraw: function() {
      let user = this.user;
      console.log(user);
      axios
        .post(`${SERVER_URL}/user/withdraw`, user)
        .then(response => {
          // this.$router.replace("/user/profile");
          this.$store.dispatch("LOGOUT").then(() => {
            this.$router.replace("/user/register");
          });
        })
        .catch(() => {
          console.log("err");
        });
    }
  },
  created() {
    this.user.email = this.$store.getters["getEmail"];
    this.user.name = this.$store.getters["getName"];
    this.user.password = this.$store.getters["getPassword"];
    this.user.phone = this.$store.getters["getPhone"];
  }
};
</script>

<style></style>
