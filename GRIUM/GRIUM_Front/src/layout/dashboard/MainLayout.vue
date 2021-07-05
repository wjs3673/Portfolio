<template>
  <div class="wrapper">
    <side-bar>
      <template slot="links">
        <sidebar-link to="/main" name="Main" icon="ti-panel" />
        <!-- <sidebar-link to="/user/profile" name="My page" icon="ti-user" /> -->
        <sidebar-link to="/gone/list" name="장례 페이지" icon="ti-user" />
        <!-- <sidebar-link to="/album/photo" name="Album" icon="ti-view-list-alt" /> -->

        <!-- 신청페이지 -->
        <sidebar-link
          to="/user/application"
          name="신청페이지"
          icon="ti-pencil-alt2"
        />

        <!-- 예비 게시판 추가 -->
        <!-- <sidebar-link
          to="/boardlist"
          name="예비 게시판"
          icon="ti-pencil-alt2"
        /> -->

        <!-- 신고 페이지 -->
        <!-- <sidebar-link to="/alert" name="신고" icon="ti-bell" /> -->

        <content-footer></content-footer>
      </template>
      <mobile-menu>
        <drop-down
          class="nav-item"
          title="알림"
          title-classes="nav-link"
          icon="ti-bell"
        >
          <a class="dropdown-item" href="#"></a>
          <a class="dropdown-item" href="#">...</a>
          <a class="dropdown-item" href="#">...</a>
          <a class="dropdown-item" href="#">...</a>
        </drop-down>
        <li class="nav-item">
          <a href="#" class="nav-link">
            <i class="ti-user"></i>
            <p>로그인</p>
          </a>
        </li>
        <li class="divider"></li>
      </mobile-menu>
    </side-bar>
    <div class="main-panel">
      <top-navbar></top-navbar>

      <dashboard-content @click.native="toggleSidebar"> </dashboard-content>
    </div>
  </div>
</template>
<style lang="scss"></style>
<script>
import TopNavbar from "./TopNavbar.vue";
import ContentFooter from "./ContentFooter.vue";
import DashboardContent from "./Content.vue";
import MobileMenu from "./MobileMenu";

export default {
  components: {
    TopNavbar,
    ContentFooter,
    DashboardContent,
    MobileMenu
  },
  methods: {
    toggleSidebar() {
      if (this.$sidebar.showSidebar) {
        this.$sidebar.displaySidebar(false);
      }
    }
  }
};
</script>
