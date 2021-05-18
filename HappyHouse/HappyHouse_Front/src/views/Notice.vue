<template>
  <div class="container notice" align="center">
    <div class="header">
      <h1>Notice</h1>
    </div>
    <table class="table table-borderless">
      <tr>
        <td align="right">
          <button
            type="button"
            class="btn btn-outline-primary"
            v-if="getUserId == 'ssafy'"
            @click="movePage"
          >
            글쓰기
          </button>
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
  <!-- <table>
      <tr>
        <td>${navigation.navigator}</td>
      </tr>
    </table> -->
</template>

<script>
// @ is an alias to /src
import ListRow from '@/components/Row.vue';
import http from '@/util/http-common';
import { mapGetters } from 'vuex';

export default {
  name: 'Notice',
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
    http
      .get('/notice/list')
      .then(({ data }) => {
        this.lists = data;
      })
      .catch(() => {
        alert('에러가 발생했습니다.');
      });
  },
  methods: {
    movePage() {
      this.$router.push('/create');
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
