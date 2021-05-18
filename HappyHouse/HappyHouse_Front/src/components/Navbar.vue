<template>
  <b-navbar class="topnav" toggleable="lg">
    <b-navbar-brand style="color:rgb(0, 108, 255);font-weight: 500;"
      ><router-link to="/">HappyHouse</router-link></b-navbar-brand
    >
    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav>
        <router-link to="/search">Search</router-link>
        <!-- <a href="./apt/search?pg=1&key=&word=">아파트검색</a>
    <a href="./apt/searchRes?pg=1&key=&word=">주택검색</a>
    <a href="./corona/searchAll?pg=1&key=&word=">코로나검색</a>
    <a href="./hospital/searchAll?pg=1&key=&word=">병원검색</a> -->
        <router-link to="/notice">Notice</router-link>
        <router-link to="./notice/sitemap">SiteMap</router-link>
        <router-link to="/qna">Q&A</router-link>
        <router-link to="/corona">CoronaHospital</router-link>
      </b-navbar-nav>
      <b-navbar-nav class="ml-auto">
        <b-navbar-nav v-if="getAccessToken">
          <router-link to="/info" style="float:right">
            {{ getUserName }}({{ getUserId }})님 환영합니다.</router-link
          >
          <router-link to="/" @click.native="logout" style="float:right"
            >Sign out</router-link
          >
        </b-navbar-nav>
        <b-navbar-nav v-else>
          <router-link to="/login" style="float:right">Sign in</router-link>
          <router-link to="/join" style="float:right">Sign up</router-link>
        </b-navbar-nav>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
</template>

<script>
// @ is an alias to /src
//import Vue from 'vue';

//mapGetters 등록
import { mapGetters } from 'vuex';

export default {
  name: 'Navbar',
  components: {},
  data() {
    return {
      userinfo: null,
      isLogin: false,
    };
  },
  computed: {
    ...mapGetters(['getAccessToken', 'getUserId', 'getUserName']),
  },
  methods: {
    logout() {
      console.log('로그아웃호출');
      this.$store.dispatch('LOGOUT').then(() => this.$router.replace('/login'));
    },
  },
};
</script>

<style>
hr {
  border: 2px solid rgba(0, 106, 255, 0.274);
}
</style>
