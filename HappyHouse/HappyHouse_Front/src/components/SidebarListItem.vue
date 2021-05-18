<template>
  <div>
    <div v-if="type == 'apt'">
      <table class="table">
        <colgroup>
          <col :style="{ width: '40%' }" />
          <col :style="{ width: '60%' }" />
        </colgroup>
        <tr>
          <td>
            <span v-if="id != ''">
              <b-icon
                v-if="bookmark"
                icon="bookmark-heart-fill"
                scale="2"
                variant="danger"
                @click="toggleBookmark"
              ></b-icon>
              <b-icon
                v-else
                icon="bookmark"
                scale="2"
                variant="dark"
                @click="toggleBookmark"
              ></b-icon>
            </span>
          </td>
          <td>
            <img
              class="buildingImg"
              :src="require(`@/assets/img/${imageName}.jpg`)"
            />
          </td>
        </tr>
        <tr>
          <td>건물명</td>
          <td>{{ building.aptName }}</td>
        </tr>
        <tr>
          <td>완공년도</td>
          <td>{{ building.buildYear }}</td>
        </tr>
        <tr>
          <td>지번</td>
          <td>{{ building.jibun }}</td>
        </tr>
      </table>
    </div>
    <div v-else-if="type == 'residence'">
      <table class="table">
        <colgroup>
          <col :style="{ width: '40%' }" />
          <col :style="{ width: '60%' }" />
        </colgroup>
        <tr>
          <td>
            <span v-if="id != ''">
              <b-icon
                v-if="bookmark"
                icon="bookmark-heart-fill"
                scale="2"
                variant="danger"
                @click="toggleBookmark"
              ></b-icon>
              <b-icon
                v-else
                icon="bookmark"
                scale="2"
                variant="dark"
                @click="toggleBookmark"
              ></b-icon>
            </span>
          </td>
          <td>
            <img
              class="buildingImg"
              :src="require(`@/assets/img/다세대주택.jpg`)"
            />
          </td>
        </tr>
        <tr>
          <td>건물명</td>
          <td>{{ building.buildingName }}</td>
        </tr>
        <tr>
          <td>완공년도</td>
          <td>{{ building.buildYear }}</td>
        </tr>
        <tr>
          <td>지번</td>
          <td>{{ building.bunji }}</td>
        </tr>
        <tr>
          <td>타입</td>
          <td>{{ building.type }}</td>
        </tr>
      </table>
    </div>
    <hr />
  </div>
</template>

<script>
import http from '@/util/http-common';
// import axios from 'axios';
// const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
  name: 'sidebarListItem',
  data() {
    return {
      bookmark: false,
      images: [
        '건양하늘터',
        '경희궁의아침',
        '경희궁자이',
        '광화문풍림스페이스본',
        '교북동경희궁자이',
        '동성아파트',
        '무악동인왕산아이파크',
        '무악동현대',
        '숭인동롯데캐슬천지인',
        '숭인동삼전솔하임',
        '숭인동숭인한양LEEPS',
        '숭인동종로센트레빌',
        '숭인동종로중흥S클래스',
        '숭인동종로청계힐스테이트',
        '신동아블루아광화문',
        '아남',
        '창신동덕산',
        '창신동두산',
        '창신동창신쌍용',
        '평창동갑을',
        '평창동삼성',
        '평창동엘리시아',
        '현대뜨레비앙',
        '효성쥬얼리시티',
        'CS타워',
      ],
      imageName: 'no_image',
      bookmarkNo: 0,
    };
  },
  props: {
    type: { type: String },
    building: { type: Object },
    id: { type: String },
    marked: { type: Boolean },
    dongName: { type: String },
  },
  mounted() {
    if (this.type == 'apt') {
      for (let i = 0; i < this.images.length; ++i) {
        if (this.building.aptName.indexOf(this.images[i]) >= 0) {
          this.imageName = this.images[i];
          break;
        }
      }
    }
    this.bookmark = this.marked;
  },

  methods: {
    toggleBookmark() {
      this.bookmark = !this.bookmark;
      this.saveBookmark();
    },

    saveBookmark() {
      if (this.bookmark) {
        http
          // .get(`bookmark/insert`, { bookmark })
          .get(
            `bookmark/insert/${this.id}/${this.dongName}/${this.building.no}/${
              this.type == 'apt'
                ? this.building.aptName
                : this.building.buildingName
            }`
          )
          .then((response) => {
            this.bookmarkNo = response.data;
          })
          .catch(() => {
            alert('에러가 발생했습니다.');
          });
      } else {
        http
          .get(`bookmark/delete/${this.bookmarkNo}`)
          .then(() => {})
          .catch(() => {
            alert('에러가 발생했습니다.');
          });
      }
    },
  },
};
</script>
<style>
.buildingImg {
  width: 200px;
  border-radius: 2px;
  box-shadow: 1px 1px 3px 1px rgba(0, 0, 0, 0.5);
  transition: width 1s;
}
</style>
