<template>
  <div id="app">
    <navbar></navbar>
    <router-view />
  </div>
</template>

<script>
import Navbar from '@/components/Navbar.vue';
import Vue from 'vue';

//mapGetters 등록
import { mapGetters } from 'vuex';

//BootStrap 등록
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue';

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

export default {
  name: 'App',
  components: {
    Navbar,
  },

  data() {
    return {
      isLogin: false,
    };
  },
  computed: {
    ...mapGetters([
      'getAccessToken',
      'getUserId',
      'getUserName',
      'getSidebarDetail',
    ]),
  },
  methods: {
    onClickLogout() {
      this.$store
        .dispatch('LOGOUT')
        .then(() => this.$router.replace('/').catch(() => {}));
    },
  },
};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap');

#app {
  /* font-family: Avenir, Helvetica, Arial, sans-serif; */
  font-family: 'Noto Sans KR', sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
* {
  box-sizing: border-box;
}

body {
  font-family: 'Noto Sans KR', sans-serif;
  padding: 10px;
}

/*아파트 검색*/
.numberis {
  background-color: #004e66;
}

.dealmoney {
  background-color: #fcbe32;
}

.yearof {
  background-color: #004e66;
}

/*코로나 검색*/

/* .table-info {
} */
.address {
  background-color: #e1eef6;
}
.dayday {
  background-color: #fdc23e;
}
.sat {
  background-color: #ff7761;
  color: white;
}
.sun {
  background-color: #274555;
  color: white;
}

/* Header/Blog Title */
.home .header {
  padding: 100px;
  text-align: center;
}

.header {
  padding: 30px;
  text-align: center;
}
.header h1 {
  font-size: 70px;
  color: rgb(0, 108, 255);
  font-family: 'Noto Sans KR', sans-serif;
  font-weight: 100;
}

/*QnA*/
.QnANotice {
  color: rgb(0, 108, 255);
  font-family: 'Noto Sans KR', sans-serif;
  font-weight: 500;
}

/* Style the top navigation bar */
.topnav {
  overflow: hidden;
  border-radius: 10px;
}

/* Style the topnav links */
.topnav a {
  color: rgb(0, 108, 255);
  padding: 14px 16px;
  text-decoration: none;
  font-family: 'Noto Sans KR', sans-serif;
  font-weight: 400;
}

/* Change color on hover */
.topnav a:hover {
  overflow: hidden;
  border-radius: 30px;
  background-color: #ddd;
  color: rgb(0, 108, 255);
}

b-navbar-brand {
  color: rgb(0, 108, 255);
  padding: 14px 16px;
  text-decoration: none;
  font-family: 'Noto Sans KR', sans-serif;
  font-weight: 400;
}
/* Create two unequal columns that floats next to each other */
/* Left column */
.leftcolumn {
  float: left;
  width: 75%;
}

/* Right column */
.rightcolumn {
  float: left;
  width: 25%;
  background-color: #f1f1f1;
  padding-left: 20px;
}

/* Fake image */
.fakeimg {
  background-color: #aaa;
  width: 100%;
  padding: 20px;
}

/* Add a card effect for articles */
.card {
  background-color: white;
  padding: 20px;
  margin-top: 20px;
}

/* Clear floats after the columns */
.row:after {
  content: '';
  display: table;
  clear: both;
}

/* Footer */
.footer {
  padding: 20px;
  text-align: center;
  background: #ddd;
  margin-top: 20px;
}

/* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other */
@media screen and (max-width: 800px) {
  .leftcolumn,
  .rightcolumn {
    width: 100%;
    padding: 0;
  }
}

/* Responsive layout - when the screen is less than 400px wide, make the navigation links stack on top of each other instead of next to each other */
@media screen and (max-width: 400px) {
  .topnav a {
    float: none;
    width: 100%;
  }
}
</style>
