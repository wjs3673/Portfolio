<template>
  <nav class="navbar navbar-expand-lg navbar-light">
    <div class="container-fluid">
      <div class="row">
        <a class="navbar-brand" href="#">{{ routeName }}</a>

        <!-- <div class="navbar-brand" v-if="this.route === 'Photo'">GALLERY</div>
        <div class="navbar-brand" v-if="this.route === 'BoardList'">LETTER</div>
        <div class="navbar-brand" v-if="this.route === 'GoneList'">
          CONDOLENCE
        </div>
        <div class="navbar-brand" v-if="this.route === 'Application'">
          APPLICATION
        </div>
        <div class="navbar-brand" v-if="this.route === 'GoneDetail'">
          CONDOLENCE OFFICER
        </div> -->

        <div v-if="routeName === 'Photo' || routeName ==='BoardList'">

          <button
            v-on:click="onUpload()"
            style="border: none;margin-left:100px;margin-top:20px;display:absolute"
            class="btn btn-dark"
            @click="goto"
            v-if="routeName === 'Photo'"
          >
            GO TO BOARD
          </button>
          <button
            v-on:click="onUpload()"
            style="border: none;margin-left:100px;margin-top:20px;display:absolute"
            class="btn btn-dark"
            @click="goto"
            v-else
          >
            GO TO PHOTO
          </button>
        </div>
        <div v-else></div>

      </div>
      <button
        class="navbar-toggler navbar-burger"
        type="button"
        @click="toggleSidebar"
        :aria-expanded="$sidebar.showSidebar"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-bar"></span>
        <span class="navbar-toggler-bar"></span>
        <span class="navbar-toggler-bar"></span>
      </button>
    </div>
    <div class="collapse navbar-collapse">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <drop-down
            class="nav-item"
            title=" "
            title-classes="nav-link"
            icon="ti-bell"
          >
            <router-link to="/alert" class="nav-link">
              신고 관리
            </router-link>
          </drop-down>
        </li>
        <li class="nav-item">
          <drop-down
            class="nav-item"
            title=" "
            title-classes="nav-link"
            icon="ti-user"
          >
            <router-link to="/login" class="nav-link" v-if="getName === ''">
              LOGIN
            </router-link>
            <div v-else>
              <router-link to="/user/profile" class="nav-link">
                MY PAGE
              </router-link>
              <router-link @click.native="logout" to="/" class="nav-link">
                LOGOUT
              </router-link>
            </div>
          </drop-down>
        </li>
      </ul>
    </div>
  </nav>
</template>
<script>
import { mapGetters } from "vuex";
import Button from "../../components/Button.vue";
import ToggleButtonpage from "../../components/ToggleButtonpage.vue";

export default {
  components: {
    ToggleButtonpage,
    Button
  },
  computed: {
    ...mapGetters([
      "getAccessToken",
      "getUserid",
      "getName",
      "getNumber",
      "getAddress",
      "getRoll"
    ]),
    routeName() {
      const { name } = this.$route;
      return this.capitalizeFirstLetter(name);
    }
  },
  data() {
    return {
      activeNotifications: false,
      route: ""
    };
  },
  methods: {
    logout: function() {
      this.$store.dispatch("LOGOUT").then(() => {
        this.$router.replace("/login");
      });
    },
    capitalizeFirstLetter(string) {
      return string.charAt(0).toUpperCase() + string.slice(1);
    },
    toggleNotificationDropDown() {
      this.activeNotifications = !this.activeNotifications;
    },
    closeDropDown() {
      this.activeNotifications = false;
    },
    toggleSidebar() {
      this.$sidebar.displaySidebar(!this.$sidebar.showSidebar);
    },
    hideSidebar() {
      this.$sidebar.displaySidebar(false);
    },
    goto() {
      // this.active = value;
      if (this.routeName === "BoardList") {
        this.$router.push("/album/photo");
        // alert(this.route);
      } else {
        this.$router.push("/boardlist");
        // alert(this.route);
      }
    }
  }
};
</script>
<style></style>
