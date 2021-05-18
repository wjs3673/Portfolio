<template>
  <div class="container" align="center">
    <div class="col-lg-6" align="center">
      <form id="loginform" method="post" action="">
        <input type="hidden" name="act" value="login" /> <br />
        <div class="form-group" align="left">
          <label for=""><strong>글번호 : </strong> {{ no }}</label>
        </div>
        <div class="form-group" align="left">
          <label for=""><strong>작성자 : </strong> {{ id }}</label>
        </div>
        <div class="form-group" align="left">
          <label for=""><strong>작성일 : </strong> {{ regtime }}</label>
        </div>
        <div class="form-group" align="left">
          <label for=""><strong>제목 : </strong>{{ title }}</label>
        </div>
        <div class="form-group" align="left">
          <label for=""><strong>내용 : </strong>{{ content }}</label>
        </div>
        <div class="form-group" align="center">
          <router-link :to="'/qnaupdate?no=' + no"
            ><button class="btn btn-primary button">
              게시글 수정
            </button></router-link
          >
          <router-link :to="'/qnadelete?no=' + no"
            ><button class="btn btn-primary button">
              게시글 삭제
            </button></router-link
          >
          <button class="btn btn-primary button" @click="moveList">
            목록으로
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import moment from 'moment';
export default {
  name: 'qnadetail',
  props: {
    no: { type: Number },
    title: { type: String },
    id: { type: String },
    regtime: { type: String },
    content: { type: String },
  },
  methods: {
    getFormatDate(regtime) {
      return moment(new Date(regtime)).format('YYYY.MM.DD HH:mm:ss');
    },
    moveList() {
      this.$router.push('/qna');
    },
  },
};
</script>
<style>
.button {
  margin: 0 2pt;
}
</style>
