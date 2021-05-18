<template>
  <div class="container " align="center">
    <div class="header">
      <h1>Q&A</h1>
    </div>
    <table class="table table-borderless">
      <tr>
        <td align="right">
          <button
            type="button"
            class="btn btn-outline-primary"
            v-if="getUserId != ''"
            @click="movePage"
          >
            글쓰기
          </button>
          <span v-else-if="getUserId == ''" class="QnANotice"
            >로그인 후 이용해주세요</span
          >
        </td>
      </tr>
    </table>

    <div v-if="lists.length != 0">
      <table class="table table-active">
        <colgroup>
          <col :style="{ width: '5%' }" />
          <col :style="{ width: '50%' }" />
          <col :style="{ width: '10%' }" />
          <col :style="{ width: '25%' }" />
        </colgroup>
        <tr>
          <th colspan="4">글번호</th>
          <th colspan="4">제목</th>
          <th colspan="4">작성자</th>
          <th colspan="4">작성일</th>
        </tr>
        <list-row
          v-for="(list, index) in lists"
          :key="`${index}_lists`"
          :no="list.no"
          :title="list.title"
          :id="list.id"
          :regtime="list.regtime"
        />
      </table>
    </div>
    <div v-else>
      <table class="table table-active">
        <tbody>
          <tr class="table-info" align="center">
            <td>작성된 글이 없습니다.</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import ListRow from '@/components/QnARow.vue';
import axios from 'axios';
import { mapGetters } from 'vuex';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'QnA',
  components: {
    ListRow,
  },
  data() {
    return {
      userinfo: null,
      lists: [],
    };
  },
  created() {
    axios
      .get(`${SERVER_URL}/qna/list`)
      .then(({ data }) => {
        this.lists = data;
      })
      .catch(() => {
        alert('에러가 발생했습니다.');
      });
  },
  methods: {
    movePage() {
      this.$router.push('/qnacreate');
    },
  },
  computed: {
    ...mapGetters(['getUserId']),
  },
};
</script>
<style>
button {
  margin: 0 2pt;
}
</style>
