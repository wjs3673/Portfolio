<template>
  <b-container class="mt-4" v-if="user">
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-jumbotron>
          <template #header>My Page</template>

          <template #lead>
            내 정보 확인페이지입니다.
          </template>

          <hr class="my-4" />

          <b-container class="mt-4">
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">아이디</b-col
              ><b-col cols="4" align-self="start">{{ user.id }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이름</b-col
              ><b-col cols="4" align-self="start">{{ user.name }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">전화번호</b-col
              ><b-col cols="4" align-self="start">{{ user.phone }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">주소</b-col
              ><b-col cols="4" align-self="start">{{ user.addr }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
          </b-container>
          <hr class="my-4" />
          <router-link :to="'/updateUser'"
            ><b-button class="button" variant="warning"
              >정보수정</b-button
            ></router-link
          >
          <router-link :to="'/deleteUser?id=' + user.id">
            <b-button class="button" variant="danger"
              >회원탈퇴</b-button
            ></router-link
          >
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  data() {
    return {
      user: null,
    };
  },
  created() {
    axios
      .get(`${SERVER_URL}/user/info`)
      .then((response) => {
        this.user = response.data.user;
      })
      .catch(() => {
        this.$store.dispatch('LOGOUT').then(() => this.$router.replace('/'));
      });
  },
};
</script>

<style>
.button {
  margin: 0 2pt;
}
</style>
