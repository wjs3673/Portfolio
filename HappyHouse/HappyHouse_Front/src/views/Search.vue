<template>
  <div class="container search" align="center">
    <div class="header">
      <h1>Search</h1>
    </div>
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
      <b-button
        v-if="this.dong.dong != '동'"
        v-b-toggle.sidebar-right
        variant="primary"
        >상세정보 보기</b-button
      >
    </div>
    <br />
    <div v-if="this.dong.dong != '동'">
      <h4>금액별(100만원)</h4>
      <vue-range-slider
        :bg-style="bgStyle"
        :tooltip-style="tooltipStyle"
        :process-style="processStyle"
        v-model="value"
        :min="min"
        :max="max"
        :enable-cross="enableCross"
        width="300pt"
      ></vue-range-slider>
      <b-form-checkbox-group
        id="mapCheckbox"
        v-model="selected"
        :options="options"
        name="mapCheckbox"
      ></b-form-checkbox-group>

      <!-- 날씨 -->
      <main>
        <!-- <div class="date">{{ dateBuilder() }}</div> -->
        <br />
        <div>
          <table>
            <tr>
              <td rowspan="4">
                <h4 class="weather_title">
                  검색지역<br />실시간<br />
                  날씨정보
                </h4>
              </td>
              <td class="weather_head">날짜</td>
              <td>{{ dateBuilder() }}</td>
            </tr>
            <tr>
              <td class="weather_head">기온</td>
              <td>{{ Math.round(weather.main.temp) }}°c</td>
            </tr>
            <tr>
              <td class="weather_head">습도</td>
              <td>{{ Math.round(weather.main.humidity) }}%</td>
            </tr>
            <tr>
              <td class="weather_head">날씨</td>
              <td>
                <img
                  :src="
                    `http://openweathermap.org/img/wn/${weather.weather[0].icon}.png`
                  "
                  alt=""
                />
              </td>
            </tr>
          </table>

          <!-- <div class="weather-box">
            <div >기온 : {{ Math.round(weather.main.temp) }}°c</div>
            <div class="weather">{{ weather.weather[0].main }}</div>
          </div> -->
        </div>
      </main>
      <!-- end 날씨 -->
    </div>
    <sidebar
      :apts="apts"
      :residences="residences"
      :dongName="dong.dong"
      @mapDetail="getMapDetail"
    ></sidebar>
    <sidebar-detail
      :detail="detail"
      :type="detailType"
      :title="detailName"
    ></sidebar-detail>
    <br />
    <google-map
      :aptdeals="aptdeals"
      :apts="apts"
      :residences="residences"
      :value="value"
      @mapDetail="getMapDetail"
    />
  </div>
</template>
<style
  href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css"
></style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
// @ is an alias to /src
import axios from 'axios';
import http from '@/util/http-common';
import DropdownItem from '@/components/DropdownItem.vue';
import GoogleMap from '@/components/GoogleMap';
import Sidebar from '@/components/Sidebar.vue';
import SidebarDetail from '@/components/SidebarDetail.vue';

import 'vue-range-component/dist/vue-range-slider.css';
import VueRangeSlider from 'vue-range-component';

const API_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
const API_URL =
  'http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev';
export default {
  name: 'Search',
  components: {
    DropdownItem,
    GoogleMap,
    Sidebar,
    VueRangeSlider,
    SidebarDetail,
  },
  data() {
    return {
      value: [1, 100],

      sido: [{ sido_name: '시/도' }][0],
      gugun: [{ gugun_name: '구/군' }][0],
      dong: [{ dong: '동' }][0],
      apts: [],
      residences: [],
      sidos: [],
      guguns: [],
      dongs: [],
      detail: [],
      detailType: '',
      detailName: '',
      prevChoice: 0,
      selected: ['apt', 'residence'],
      options: [
        { text: '아파트', value: 'apt' },
        { text: '연립다세대', value: 'residence' },
      ],

      api_key: 'cf6bdac118943d3a7d185f0f4248658c',
      url_base: 'https://api.openweathermap.org/data/2.5/',
      query: '',
      weather: {},
      wname: '',

      aptdeals: [],
    };
  },

  watch: {
    selected: function() {
      if (this.dong.dong != '동') {
        this.changeCheckboxInfos(this.dong);
      }
    },
    value: function() {
      // 금액에 따른 변화
    },
  },

  methods: {
    sendDongCode: function() {
      const params = {
        LAWD_CD: this.dong.dong_code,
        DEAL_YMD: '202011',
        serviceKey: decodeURIComponent(API_KEY),
      };
      axios
        .get(API_URL, {
          params,
        })
        .then((response) => {
          this.apts = response.data.response.body.items.item;
          console.log(this.apts.length);
        })
        .catch((error) => {
          console.log(error);
        });
    },

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
      this.prevChoice = 0;
      this.apts = [];
      this.residences = [];
      this.dong = dong;
      if (this.selected.length == 0) {
        alert('검색 대상을 선택해주세요.');
      } else {
        for (let i = 0; i < this.selected.length; ++i) {
          if (this.selected[i] == 'apt') {
            this.prevChoice = this.prevChoice | (1 << 0);
            this.getAptInfos(dong);
          } else if (this.selected[i] == 'residence') {
            this.prevChoice = this.prevChoice | (1 << 1);
            this.getResidenceInfos(dong);
          }
        }
      }

      //날씨 정보 입력
      if (this.sido.sido_code === '11') {
        this.query = 'seoul';
      } else if (this.sido.sido_code === '26') {
        this.query = 'busan';
      } else if (this.sido.sido_code === '27') {
        this.query = this.sido.sido_name;
      } else if (this.sido.sido_code === '28') {
        this.query = 'incheon';
      } else if (this.sido.sido_code === '29') {
        this.query = 'gwangju';
      } else if (this.sido.sido_code === '30') {
        this.query = 'daejeon';
      } else if (this.sido.sido_code === '31') {
        this.query = 'ulsan';
      }
      this.wname = this.sido.sido_name;
      this.fetchWeather();
    },

    changeCheckboxInfos: function(dong) {
      //체크박스 변화
      let choice = 0;
      if (this.selected.length == 0) {
        alert('검색 대상을 선택해주세요.');
      } else {
        for (let i = 0; i < this.selected.length; ++i) {
          if (this.selected[i] == 'apt') {
            choice = choice | (1 << 0);
          } else if (this.selected[i] == 'residence') {
            choice = choice | (1 << 1);
          }
        }
      }

      if ((this.prevChoice & (1 << 0)) == 0 && (choice & (1 << 0)) > 0) {
        //아파트체크 켜졌을 때
        this.getAptInfos(dong);
      } else if ((this.prevChoice & (1 << 0)) > 0 && (choice & (1 << 0)) == 0) {
        //아파트체크 꺼졌을 때
        this.apts = [];
      }

      if ((this.prevChoice & (1 << 1)) == 0 && (choice & (1 << 1)) > 0) {
        //연립다세대체크 켜졌을 때
        this.getResidenceInfos(dong);
      } else if ((this.prevChoice & (1 << 1)) > 0 && (choice & (1 << 1)) == 0) {
        //연립다세대체크 꺼졌을 때
        this.residences = [];
      }
      this.prevChoice = choice;
    },

    getAptInfos: function(dong) {
      http
        .get('/map/apt/' + dong.dong)
        .then(({ data }) => {
          this.apts = data;
          //console.log(data);
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });

      http
        .get('/aptdeal/' + dong.dong)
        .then(({ data }) => {
          this.aptdeals = data;
          //console.log(data);
        })
        .catch(() => {
          alert('aptdeals 에러가 발생했습니다.');
        });
    },

    getResidenceInfos: function(dong) {
      http
        .get('/map/residence/' + dong.dong)
        .then(({ data }) => {
          this.residences = data;
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    },

    getMapDetail: function(building, type) {
      let buildingName =
        building.label == undefined
          ? type == 'apt'
            ? building.aptName
            : building.buildingName
          : building.label;
      if (type == 'apt') {
        http
          .get('/map/apt/deal/' + buildingName)
          .then(({ data }) => {
            this.detail = data;
          })
          .catch(() => {
            alert('에러가 발생했습니다.');
          });
      } else {
        http
          .get('/map/residence/deal/' + buildingName)
          .then(({ data }) => {
            this.detail = data;
          })
          .catch(() => {
            alert('에러가 발생했습니다.');
          });
      }
      this.detailName = buildingName;
      this.detailType = type;
      this.$store.commit('SidebarOpen');
    },

    fetchWeather(e) {
      fetch(
        `${this.url_base}weather?q=${this.query}&units=metric&APPID=${this.api_key}`
      )
        .then((res) => {
          return res.json();
        })
        .then(this.setResults);
    },
    setResults(results) {
      this.weather = results;
    },
    dateBuilder() {
      let d = new Date();
      let months = [
        '1월',
        '2월',
        '3월',
        '4월',
        '5월',
        '6월',
        '7월',
        '8월',
        '9월',
        '10월',
        '11월',
        '12월',
      ];
      let days = [
        '일요일',
        '월요일',
        '화요일',
        '수요일',
        '목요일',
        '금요일',
        '토요일',
      ];

      let day = days[d.getDay()];
      let date = d.getDate();
      let month = months[d.getMonth()];
      let year = d.getFullYear();

      return `${year}년 ${month} ${date}일 ${day}`;
    },
  },
  created() {
    this.getSidos();
    let homeSido = this.$route.params.sido;
    let homeGugun = this.$route.params.gugun;
    let homeDong = this.$route.params.dong;

    if (homeSido != undefined) {
      if (homeGugun.gugun_name == '구/군') {
        this.getGuguns(homeSido);
      } else if (homeDong.dong == '동') {
        this.getGuguns(homeSido);
        this.getDongs(homeGugun);
      } else {
        this.getGuguns(homeSido);
        this.getDongs(homeGugun);
        this.getHouseInfos(homeDong);
      }
    }
    this.min = 0;
    this.max = 250;
    this.bgStyle = {
      backgroundColor: '#fff',
      boxShadow: 'inset 0.5px 0.5px 3px 1px rgba(0,0,0,.36)',
    };
    this.tooltipStyle = {
      backgroundColor: '#666',
      borderColor: '#666',
    };
    this.processStyle = {
      backgroundColor: '#999',
    };

    // 금액별
    this.min = 0;
    this.max = 100;
    this.enableCross = false;
  },
};
</script>
<style>
.scroll .dropdown-menu {
  max-height: 300px;
  overflow-y: auto;
}
.app-content {
  padding: 40px 15px;
}
.weather_title {
  color: rgb(0, 108, 255);
  font-family: 'Noto Sans KR', sans-serif;
  font-weight: 300;
}
.weather_head {
  padding: 3pt 10pt;
}
</style>
