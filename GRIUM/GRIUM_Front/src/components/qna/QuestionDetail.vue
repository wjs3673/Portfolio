<template>
  <div>
    <table class="table table-bordered w-100">
      <tr>
        <th>번호</th>
        <td>{{ noq }}</td>
      </tr>
      <tr>
        <th>글쓴이</th>
        <td>{{ name }}</td>
      </tr>
      <tr>
        <th>제목</th>
        <td>{{ title }}</td>
      </tr>
      <tr>
        <th>날짜</th>
        <td>{{ getFormatDate(regDate) }}</td>
      </tr>
      <tr>
        <td colspan="2">{{ text }}</td>
      </tr>
    </table>

    <br />

    <div class="text-center">
      <router-link to="/qna/list">
        <button class="btn btn-primary">
          목록
        </button></router-link
      >
      <b-button disabled size="lg" variant="white"></b-button>

      <router-link :to="'/qna/update?noq=' + noq">
        <b-button variant="success">
          수정
        </b-button></router-link
      >
      <b-button disabled size="lg" variant="white"></b-button>

      <router-link :to="'/qna/delete?noq=' + noq"
        ><b-button variant="danger">삭제</b-button></router-link
      >
    </div>
  </div>
</template>

<script>
import moment from 'moment';
export default {
  name: 'QuestionDetail',
  props: {
    noq: { type: Number },
    name: { type: String },
    title: { type: String },
    text: { type: String },
    regDate: { type: String },
  },
  methods: {
    getFormatDate(regtime) {
      return moment(new Date(regtime)).format('YYYY.MM.DD');
    },
  },
};
</script>
