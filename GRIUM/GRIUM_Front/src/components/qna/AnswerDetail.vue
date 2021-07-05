<template>
  <div>
    <table class="table table-bordered w-100">
      <!-- <table class="table table-bordered w-20"> -->
      <tr>
        <th>글쓴이</th>
        <td>{{ name }}</td>
        <th>날짜</th>
        <td>{{ getFormatDate(regDate) }}</td>
      </tr>
      <tr>
        <td colspan="4">
          {{ text }}
        </td>
      </tr>
      <tr>
        <td colspan="4">
          <b-button variant="danger" @click="deleteHandler">삭제</b-button>
        </td>
      </tr>
    </table>

    <br />
  </div>
</template>

<script>
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
import moment from "moment";
export default {
  name: "AnswerDetail",
  props: {
    noa: { type: Number },
    noq: { type: Number },
    name: { type: String },
    text: { type: String },
    regDate: { type: String }
  },
  methods: {
    getFormatDate(regtime) {
      return moment(new Date(regtime)).format("YYYY.MM.DD");
    },

    deleteHandler() {
      axios
        .delete(`${SERVER_URL}/qna/a/${this.noa}`, {
          noq: this.noq,
          userid: this.name,
          name: this.name,
          title: this.title,
          text: this.text,
          regDate: this.regDate
        })
        .then(({ data }) => {
          let msg = "삭제 처리시 문제가 발생했습니다.";
          if (data == 0) {
            alert(msg);
          }
          this.$emit("delete");
        })
        .catch(() => {
          alert("삭제 처리시 에러가 발생했습니다.");
        });
    }
  }
};
</script>
<style>
th {
  width: 10%;
  text-align: center;
}
</style>
