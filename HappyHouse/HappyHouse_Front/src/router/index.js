import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';
import Notice from '../views/Notice.vue';
import Search from '../views/Search.vue';

// notice
import Read from '../views/Read.vue';
import Create from '../views/Create.vue';
import Update from '../views/Update.vue';
import Delete from '@/views/Delete.vue';

// user
import Login from '../views/Login.vue';
import Join from '../views/Join.vue';
import Info from '../views/Info.vue';
import UpdateUser from '../views/UpdateUser.vue';
import DeleteUser from '@/views/DeleteUser.vue';

// QnA
import QnA from '../views/QnA.vue';
import QnARead from '../views/QnARead.vue';
import QnACreate from '../views/QnACreate.vue';
import QnAUpdate from '../views/QnAUpdate.vue';
import QnADelete from '@/views/QnADelete.vue';

import Corona from '@/views/Corona.vue';

import store from '../store';

Vue.use(VueRouter);

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const requireAuth = () => (to, from, next) => {
  const nextRoute = to.path;

  if (store.getters.getAccessToken) {
    return next();
  } else next('/login' + nextRoute);
};

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/notice',
    name: 'Notice',
    component: Notice,
  },
  {
    path: '/search',
    name: 'Search',
    component: Search,
  },
  {
    path: '/search/:sido/:gugun/:dong',
    name: 'Search',
    component: Search,
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/me',
    name: 'Me',
    component: Login,
    beforeEnter: requireAuth(),
  },
  {
    path: '/read',
    name: 'read',
    component: Read,
  },
  {
    path: '/create',
    name: 'create',
    component: Create,
  },
  {
    path: '/update',
    name: 'update',
    component: Update,
  },
  {
    path: '/delete',
    name: 'delete',
    component: Delete,
  },
  {
    path: '/join',
    name: 'join',
    component: Join,
  },
  {
    path: '/info',
    name: 'info',
    component: Info,
  },
  {
    path: '/updateUser',
    name: 'updateUser',
    component: UpdateUser,
  },
  {
    path: '/deleteUser',
    name: 'deleteUser',
    component: DeleteUser,
  },
  {
    path: '/qna',
    name: 'qna',
    component: QnA,
  },
  {
    path: '/qnaread',
    name: 'qnaread',
    component: QnARead,
  },
  {
    path: '/qnacreate',
    name: 'qnacreate',
    component: QnACreate,
  },
  {
    path: '/qnaupdate',
    name: 'qnaupdate',
    component: QnAUpdate,
  },
  {
    path: '/qnadelete',
    name: 'qnadelete',
    component: QnADelete,
  },
  {
    path: '/corona',
    name: 'corona',
    component: Corona,
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
