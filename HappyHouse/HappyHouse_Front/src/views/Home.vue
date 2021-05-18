<template>
  <div class="container home" align="center">
    <div class="header">
      <span id="bluecolor">어떤 집</span><span id="blackcolor">에서</span>
      <div id="blackcolor">살고 싶으신가요?</div>
    </div>
    <!-- <img src="@/assets/home_img.jpg" /> -->
    <div>
      <b-dropdown
        :text="sido.sido_name"
        variant="outline-primary"
        class="m-2 scroll"
      >
        <dropdown-item
          v-for="(sido_for, index) in sidos"
          :key="`${index}_sidos`"
          :type="'sido'"
          :data="sido_for"
          @select-sido="getGuguns"
        />
      </b-dropdown>

      <b-dropdown
        :text="gugun.gugun_name"
        variant="outline-primary"
        class="m-2 scroll"
      >
        <dropdown-item
          v-for="(gugun_for, index) in guguns"
          :key="`${index}_guguns`"
          :type="'gugun'"
          :data="gugun_for"
          @select-gugun="getDongs"
        />
      </b-dropdown>

      <b-dropdown
        :text="dong.dong"
        variant="outline-primary"
        class="m-2 scroll"
      >
        <dropdown-item
          v-for="(dong_for, index) in dongs"
          :key="`${index}_dongs`"
          :type="'dong'"
          :data="dong_for"
          @select-dong="getHouseInfos"
        />
      </b-dropdown>
      <router-link
        class="btn btn-primary"
        :to="{
          name: 'Search',
          params: { sido: sido, gugun: gugun, dong: dong },
        }"
        >찾기</router-link
      >
    </div>
    <br />
  </div>
</template>

<script>
// @ is an alias to /src
import http from '@/util/http-common';
import DropdownItem from '@/components/DropdownItem.vue';
export default {
  name: 'Home',
  components: { DropdownItem },
  data() {
    return {
      sido: [{ sido_name: '시/도' }][0],
      gugun: [{ gugun_name: '구/군' }][0],
      dong: [{ dong: '동' }][0],
      sidos: [],
      guguns: [],
      dongs: [],
    };
  },
  methods: {
    getSidos: function() {
      this.sidos = [];
      this.guguns = [];
      this.dongs = [];
      http
        .get('/map/sido')
        .then(({ data }) => {
          this.sidos = data;
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    },
    getGuguns: function(sido) {
      this.sido = sido;
      this.guguns = [];
      this.dongs = [];
      this.gugun = [{ gugun_name: '구/군' }][0];
      this.dong = [{ dong: '동' }][0];
      http
        .get('/map/gugun/' + sido.sido_code)
        .then(({ data }) => {
          this.guguns = data;
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    },
    getDongs: function(gugun) {
      this.gugun = gugun;
      this.dongs = [];
      this.dong = [{ dong: '동' }][0];
      http
        .get('/map/dong/' + gugun.gugun_code)
        .then(({ data }) => {
          this.dongs = data;
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    },
    getHouseInfos: function(dong) {
      //동 정보 변경
      this.dong = dong;
    },
  },
  created() {
    this.getSidos();
  },
};
</script>
<style>
.header #bluecolor {
  font-size: 70px;
  color: rgb(0, 108, 255);
  font-family: 'Noto Sans KR', sans-serif;
}
.header #blackcolor {
  font-size: 50px;
  color: rgba(11, 11, 49, 0.63);
  font-family: 'Noto Sans KR', sans-serif;
  font-weight: 100;
}
</style>
