<template>
  <card class="card" title=" " sub-title="My profile" style="width:80%">
    <div class="row">
      <div class="col-md-6">
        <fg-input
          type="text"
          label="E-mail"
          :readonly="true"
          v-model="user.email"
        >
        </fg-input>
      </div>
    </div>
    <div class="row">
      <div class="col-md-6">
        <fg-input type="text" label="Name" :readonly="true" v-model="user.name">
        </fg-input>
      </div>
    </div>
    <div class="row">
      <div class="col-md-6">
        <fg-input
          type="text"
          label="Phone"
          :readonly="true"
          placeholder="-를 빼고 입력해주세요."
          v-model="user.phone"
        >
        </fg-input>
      </div>
    </div>

    <div class="row">
      <div class="col-md-6">
        <button @click="checkform" class="col-md-6 btn btn-dark button"
          >정보수정</button
        >
      </div>
    </div>
  </card>
</template>

<script>

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  data() {
    return {
      user: {
        email: "",
        name: "",
        phone: "",
        password: ""
      }
    };
  },
  methods: {
    checkform: function() {
      this.$router.replace("/user/passcheck");
    }
  },
  created() {
    this.user.email = this.$store.getters["getEmail"];
    this.user.name = this.$store.getters["getName"];
    this.user.phone = this.$store.getters["getPhone"];
    this.user.password = this.$store.getters["getPassword"];
  }
};
</script>

<style></style>