/*!

 =========================================================
 * Vue Paper Dashboard - v2.0.0
 =========================================================

 * Product Page: http://www.creative-tim.com/product/paper-dashboard
 * Copyright 2019 Creative Tim (http://www.creative-tim.com)
 * Licensed under MIT (https://github.com/creativetimofficial/paper-dashboard/blob/master/LICENSE.md)

 =========================================================

 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

 */
import Vue from "vue";
import "./plugins/axios";
import App from "./App";
import router from "./router";
import store from "./store";

import PaperDashboard from "./plugins/paperDashboard";
import "vue-notifyjs/themes/default.css";

import BootstrapVue from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

import Vuetify from "vuetify";
import "vuetify/dist/vuetify.min.css";

Vue.use(BootstrapVue);

Vue.use(PaperDashboard);

Vue.use(require("vue-moment"));

Vue.use(Vuetify);

/* eslint-disable no-new */
new Vue({
  vuetify: new Vuetify(),
  router,
  store,
  render: h => h(App)
}).$mount("#app");
