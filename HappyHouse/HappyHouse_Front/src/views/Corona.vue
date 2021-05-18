<template>
  <div class="container notice" align="center">
    <div class="header">
      <h1>Corona Hospital</h1>
    </div>

    <div v-if="hospitals.length != 0">
      <table class="table table-active">
        <colgroup>
          <col :style="{ width: '5%' }" />
          <col :style="{ width: '5%' }" />
          <col :style="{ width: '20%' }" />
          <col :style="{ width: '20%' }" />
          <col :style="{ width: '20%' }" />
        </colgroup>
        <tr>
          <th colspan="4">시/도</th>
          <th colspan="4">구/군</th>
          <th colspan="4">병원</th>
          <th colspan="4">주소</th>
          <th colspan="4">전화번호</th>
        </tr>
        <corona-list-item
          v-for="(hospital, index) in hospitals"
          :key="`${index}_hospitals`"
          :hospital="hospital"
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

    <b-button
      variant="outline-primary"
      class="button"
      @click="movePage(false)"
      v-if="pageNum != 0"
      >이전으로</b-button
    >
    <span class="padding"
      ><strong>{{ pageNum + 1 }}</strong></span
    >
    <b-button
      variant="outline-primary"
      class="button"
      @click="movePage(true)"
      v-if="pageNum != maxPage - 1"
      >다음으로</b-button
    >
  </div>
</template>

<script>
// @ is an alias to /src
import CoronaListItem from '@/components/CoronaListItem.vue';
import http from '@/util/http-common';

export default {
  name: 'Corona',
  components: { CoronaListItem },
  data() {
    return {
      pageNum: 0,
      hospitals: [],
      maxPage: 0,
    };
  },
  created() {
    this.getHospital();

    http
      .get(`/hospital/count`)
      .then(({ data }) => {
        this.maxPage = data;
      })
      .catch(() => {
        alert('에러가 발생했습니다.');
      });
  },
  methods: {
    movePage(dir) {
      this.pageNum += dir ? 1 : -1;

      this.getHospital();
    },

    getHospital() {
      http
        .get(`/hospital/selectAll/${this.pageNum}`)
        .then(({ data }) => {
          this.hospitals = data;
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    },
  },
};
</script>
<style>
.button {
  margin: 0 2pt;
}

.padding {
  padding: 0 10pt;
}
</style>
