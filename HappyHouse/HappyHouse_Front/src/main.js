import Vue from 'vue';
import './plugins/axios';
import App from './App.vue';
import router from './router';
import 'bootstrap/dist/css/bootstrap.min.css';
import store from './store';
import * as VueGoogleMaps from 'vue2-google-maps';
import VueCookie from 'vue-cookie';

Vue.config.productionTip = false;

Vue.use(VueCookie);
Vue.use(VueGoogleMaps, {
  load: {
    key: 'AIzaSyA_sZ7b3Byd5yoJLdXF5KARqHzZI3kCKQ4',
    libraries: 'places', // necessary for places input
  },
});

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');
