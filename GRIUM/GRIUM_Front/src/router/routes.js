import store from "@/store";

import MainLayout from "@/layout/dashboard/MainLayout.vue";

// GeneralViews
import NotFound from "@/pages/NotFoundPage.vue";

// Admin pages
import Main from "@/pages/Main.vue";

// Q&A pages
import QnaList from "@/pages/qna/QnaList.vue";
import QuestionForm from "@/pages/qna/QuestionForm";
import QnaRead from "@/pages/qna/QnaRead";
import QuestionDelete from "@/pages/qna/QuestionDelete";
import QuestionDetail from "@/components/qna/QuestionDetail";
import AnswerDetail from "@/components/qna/AnswerDetail";
import QuestionUpdate from "@/pages/qna/QuestionUpdate";

// search pages
import Photo from "@/pages/album/Photo.vue";

// user pages
import Login from "@/pages/Login.vue";
import Register from "@/pages/user/Register.vue";
import Profile from "@/pages/user/Profile.vue";
import Update from "@/pages/user/Update.vue";
import PassCheck from "@/pages/user/PassCheck.vue";
import Findpw from "@/pages/user/Findpw.vue";
import FindpwSuccess from "@/pages/user/FindpwSuccess.vue";
import Application from "@/pages/user/Application.vue";

// Board
import BoardList from "@/pages/board/BoardList.vue";
import BoardInsert from "@/pages/board/BoardInsert.vue";
import BoardRead from "@/pages/board/BoardRead.vue";
import BoardUpdate from "@/pages/board/BoardUpdate.vue";

// Gone
import GoneList from "@/pages/gone/GoneList.vue";
import GoneDetail from "@/pages/gone/GoneDetail.vue";

// Alert
import Alert from "@/pages/alert/Alert.vue";

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const requireAuth = () => (to, from, next) => {
  const nextRoute = to.path;

  if (store.getters.getAccessToken || sessionStorage.getItem("auto-token")) {
    return next();
  } else next("/login");
};

const routes = [
  {
    path: "/",
    component: MainLayout,
    redirect: "/main",
    children: [
      {
        path: "/login",
        name: "Login",
        component: Login
        // beforeEnter: requireAuth()
      },
      {
        path: "/login/:nextRoute",
        name: "LoginNextRoute",
        component: Login,
        beforeEnter: requireAuth()
      },
      {
        path: "main",
        name: "main",
        component: Main
      },
      // qna
      {
        path: "/qna/list",
        name: "QnaList",
        component: QnaList
      },
      {
        path: "/qna/read",
        name: "QnaRead",
        component: QnaRead
      },
      {
        path: "/qna/regist",
        name: "QuestionForm",
        component: QuestionForm
      },
      {
        path: "/qna/qdetail",
        name: "QuestionDetail",
        component: QuestionDetail
      },
      {
        path: "/qna/delete",
        name: "QuestionDelete",
        component: QuestionDelete
      },
      {
        path: "/qna/update",
        name: "QuestionUpdate",
        component: QuestionUpdate
      },
      {
        path: "/qna/adetail",
        name: "AnswerDetail",
        component: AnswerDetail
      },
      {
        path: "/album/photo",
        name: "Photo",
        component: Photo,
        beforeEnter: requireAuth()
      },
      // user
      {
        path: "/user/register",
        name: "Register",
        component: Register
      },
      {
        path: "/user/profile",
        name: "Profile",
        component: Profile,
        beforeEnter: requireAuth()
      },
      {
        path: "/user/update",
        name: "Update",
        component: Update,
        beforeEnter: requireAuth()
      },
      {
        path: "/user/findpw",
        name: "Findpw",
        component: Findpw
      },
      {
        path: "/user/passcheck",
        name: "PassCheck",
        component: PassCheck,
        beforeEnter: requireAuth()
      },
      {
        path: "/user/findpwsuccess",
        name: "FindpwSuccess",
        component: FindpwSuccess
      },
      {
        path: "/user/application",
        name: "Application",
        component: Application,
        beforeEnter: requireAuth()
      },
      // board
      {
        path: "/boardlist",
        name: "BoardList",
        component: BoardList,
        beforeEnter: requireAuth()
      },
      {
        path: "/boardinsert",
        name: "BoardInsert",
        component: BoardInsert
        // beforeEnter: requireAuth()
      },
      {
        path: "/boardread",
        name: "BoardRead",
        component: BoardRead
        // beforeEnter: requireAuth()
      },
      {
        path: "/boardupdate",
        name: "BoardUpdate",
        component: BoardUpdate,
        props: true
        // beforeEnter: requireAuth()
      },
      // gone
      {
        path: "/gone/list",
        name: "GoneList",
        component: GoneList
      },
      {
        path: "/gonedetail",
        name: "GoneDetail",
        component: GoneDetail,
        props: true
      },
      // alert
      {
        path: "/alert",
        name: "Alert",
        component: Alert,
        beforeEnter: requireAuth()
      },
    ]
  },
  { path: "*", component: NotFound }
];

/**
 * Asynchronously load view (Webpack Lazy loading compatible)
 * The specified component must be inside the Views folder
 * @param  {string} name  the filename (basename) of the view to load.
function view(name) {
   var res= require('../components/Dashboard/Views/' + name + '.vue');
   return res;
};**/

export default routes;
