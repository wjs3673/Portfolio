<template>
  <footer class="footer">
    <div class="container-fluid d-flex flex-wrap">
      <nav>
        <ul>
          <li>
            <router-link :to="{ path: '/admin' }"> </router-link>
          </li>
        </ul>
      </nav>
      <div class="copyright-center">
        <p>
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
          <br />
          &nbsp;&nbsp;&nbsp;&nbsp;
          <img src="@/assets/img/insta.png" @click="insta" />
          &nbsp;
          <img src="@/assets/img/twi.png" @click="twi" />
          &nbsp;
          <img src="@/assets/img/notion.png" @click="notion" />
          &nbsp;
          <br />
          with by FOUR TEAM
        </p>
      </div>
    </div>
  </footer>
</template>
<script>
export default {
  methods: {
    twi() {
      window.open("https://twitter.com/grium_ssafy");
    },
    insta() {
      window.open("https://www.instagram.com/ssafy_fls/");
    },
    notion() {
      window.open("https://www.notion.so/GIT-FLOW-941628b953104c0882f6a4cb889e7981");
    }
  }
};
</script>
<style>
.copyright-center{
  color: white;
}
</style>
