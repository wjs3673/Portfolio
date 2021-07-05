import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

// 새로고침을 했을 때 로그인이 풀리는 걸 방지하는 npm 설치 프로그램
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default new Vuex.Store({
  // 플러그인으로 설치해준다.
  plugins: [
    createPersistedState()
  ],
  state: {
    accessToken: null,
    email: "",
    name: "",
    password: "",
    phone: "",
    photo: "",
    // roll : "",
    gone_id: "",
    userid: ""

  },
  getters: {
    getAccessToken(state) {
      return state.accessToken;
    },
    getEmail(state) {
      return state.email;
    },
    getName(state) {
      return state.name;
    },
    getPassword(state) {
      return state.password;
    },
    getPhone(state) {
      return state.phone;
    },
    getPhoto(state) {
      return state.photo;
    },
    // getRoll(state) {
    //   return state.roll;
    // },
    getGone(state) {
      return state.gone_id;
    },
    getUserid(state) {
      return state.userid;
    }
  },
  mutations: {
    LOGIN(state, payload) {
      state.accessToken = payload["auth-token"];
      state.email = payload["email"];
      state.name = payload["name"];
      state.password = payload["password"];
      state.phone = payload["phone"];
      // state.photo = payload["photo"];
      // state.roll = payload["roll"];
      state.userid = payload["userid"];
    },
    LOGOUT(state) {
      state.accessToken = null;
      state.email = "";
      state.name = "";
      state.password = "";
      state.phone = "";
      // state.photo = "";
      // state.roll = "";
      state.gone_id = "";
      state.userid = "";
    },
    FINDPW(state, payload) {
      state.accessToken = payload["auth-token"];
      state.email = payload["email"];
      state.name = payload["name"];
      state.password = payload["password"];
      // state.roll = payload["roll"];
    },
    UPDATE(state, payload) {
      state.accessToken = payload["auth-token"];
      state.email = payload["email"];
      state.name = payload["name"];
      state.phone = payload["phone"];
      // state.roll = payload["roll"];
    },
    CREATE_GONE(state, gone_id) {
      state.gone_id = gone_id;
    }
  },
  actions: {
    LOGIN(context, user) {
      return axios
        .post(`${SERVER_URL}/user/confirm/login`, user)
        .then(response => {
          context.commit("LOGIN", response.data);
          if (`${response.data["auth-token"]}` == "undefined") reject();
          axios.defaults.headers.common[
            "auth-token"
          ] = `${response.data["auth-token"]}`;
          sessionStorage.setItem(
            "auth-token",
            `${response.data["auth-token"]}`
          );
          sessionStorage.setItem("email", `${response.data["email"]}`);
          sessionStorage.setItem("passwoard", `${response.data["password"]}`);
          sessionStorage.setItem("userid", `${response.data["userid"]}`);
        })
        .catch(() => {
          reject();
        });
    },
    // getMemberInfo(context, user) {
    //   let token = localStorage.getItem(
    //     "auth-token",
    //     `${response.data["auth-token"]}`
    //   );
    //   axios
    //   .get(`${SERVER_URL}/user/confirm/login`, token)
    //   .then(response => {
    //     let userInfo = {
    //       email: `${response.data["email"]}`,
    //       passwoard: `${response.data["password"]}`
    //     }
    //     context.commit("LOGIN", response.data);
    //   })
    //   .catch(() => {
    //     reject();
    //   });




    // },

    LOGOUT(context) {
      context.commit("LOGOUT");
      axios.defaults.headers.common["auth-token"] = undefined;
      sessionStorage.removeItem("auth-token");
      // sessionStorage.removeItem("email", `${response.data["email"]}`);
      // sessionStorage.removeItem("passwoard", `${response.data["password"]}`);
    },

    FINDPW(context, user) {
      return axios
        .post(`${SERVER_URL}/user/findpw`, user)
        .then(response => {
          context.commit("FINDPW", response.data);
          axios.defaults.headers.common[
            "auth-token"
          ] = `${response.data["auth-token"]}`;
          sessionStorage.setItem(
            "auth-token",
            `${response.data["auth-token"]}`
          );
        })
        .catch(() => {
          reject();
        });
    },
    UPDATE(context, user) {
      return axios
        .post(`${SERVER_URL}/user/update`, user)
        .then(response => {
          context.commit("UPDATE", response.data);
          if (`${response.data["auth-token"]}` == "undefined") reject();
          axios.defaults.headers.common[
            "auth-token"
          ] = `${response.data["auth-token"]}`;
          sessionStorage.setItem(
            "auth-token",
            `${response.data["auth-token"]}`
          );
        })
        .catch(() => {
          reject();
        });
    },
    // gone_id 가져오기
    createGone({ commit }, gone_id) {
      commit('CREATE_GONE', gone_id)
    }

  },
  modules: {}
});
