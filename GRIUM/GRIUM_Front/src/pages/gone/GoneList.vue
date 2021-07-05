<template>
  <div class="row">
    <card class="mr-3 size" v-for="gone in goneList" :key="gone.gone_id">
      <div class="text-center">
        <img
          :src="gone.photo_path"
          alt=""
          width="100px"
          height="100px"
          class="listphoto"
        />
        <br />
        <strong>故 {{ gone.gone_nm }} 님</strong>
        <p>삼가 고인의 명복을 빕니다.</p>
        <p>별세 일시 : {{ $moment(gone.death_dt).format("YYYY-MM-DD") }}</p>
        <router-link :to="`/gonedetail?gone_id=${gone.gone_id}`">
          <button class="btn btn-dark button">
            조문관으로 이동하기
          </button>
        </router-link>
      </div>
    </card>
  </div>
</template>

<script>
import axios from "axios";
import Button from "../../components/Button.vue";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "GoneList",
  components: {
    Button
  },
  data: function() {
    return {
      goneList: {
        gone_id: "",
        user_id: "",
        death_dt: "",
        gone_nm: "",
        photo_path: ""
      }
    };
  },
  created() {
    axios
      .get(`${SERVER_URL}/gone/list`)
      .then(res => {
        this.goneList = res.data.goneList;
        console.log(res.data.goneList);
      })
      .catch(() => {
        console.log("실패");
      });
  }
};
</script>

<style>
.size {
  width: 250px;
  height: 300px;
}
.listphoto {
  width: 100px;
  height: 100px;
}
</style>
