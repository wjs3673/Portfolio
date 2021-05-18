<template>
  <body>
    <div
      class="w3-sidebar w3-bar-block w3-card w3-animate-left"
      :style="sidebarStyle"
      id="mySidebar"
    >
      <button class="btn btn-primary" @click="w3_close()">
        Close ×
      </button>
      <br />
      <p>검색 하고 결과 내보내는 곳</p>
    </div>

    <div id="main" :style="mainStyle">
      <div class="w3-teal">
        <button
          id="openNav"
          class="btn btn-primary"
          @click="w3_open()"
          :style="navStyle"
        >
          ☰
        </button>
        <div class="w3-container">
          <h1>My Page</h1>
        </div>
      </div>

      <div class="w3-container">
        <p>지도가 있을 곳입니다~</p>
      </div>
    </div>
  </body>
</template>

<script></script>

<script>
export default {
  data() {
    return {
      mainMarginLeft: '0%',
      sidebarDisplay: 'none',
      navDisplay: 'inline-block',
    };
  },
  computed: {
    mainStyle() {
      return {
        marginLeft: this.mainMarginLeft,
      };
    },
    sidebarStyle() {
      return {
        width: this.width,
        display: this.sidebarDisplay,
      };
    },
    navStyle() {
      return { display: this.navDisplay };
    },
  },
  methods: {
    w3_open() {
      this.mainMarginLeft = '25%';
      this.width = '25%';
      this.sidebarDisplay = 'block';
      this.navDisplay = 'none';
    },
    w3_close() {
      this.mainMarginLeft = '0%';
      this.sidebarDisplay = 'none';
      this.navDisplay = 'inline-block';
    },
  },
};
</script>
