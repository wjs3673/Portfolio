<template>
  <card title="글 수정">
    <br />
    <div class="form-group">
      <label for="list.title">제목</label> &nbsp;
      <input
        type="text"
        class="form-control"
        id="list.title"
        ref="list.title"
        v-model="list.title"
      />
    </div>
    <br />
    <div class="form-group">
      <label for="list.writer">작성자</label> &nbsp;
      <input
        type="text"
        class="form-control"
        id="list.writer"
        ref="list.writer"
        v-model="list.writer"
        :readonly="true"
      />
    </div>
    <br />
    <div class="form-group">
      <label for="list.content">내용</label> &nbsp;
      <textarea
        type="context"
        class="form-control"
        id="list.content"
        ref="list.content"
        v-model="list.content"
        rows="10"
        cols="40"
      ></textarea>
    </div>
    <br />

    <div class="text-right">
      <label>
        <input type="checkbox" v-model="list.secret" @change="checked" />
        비밀글
      </label>
      &nbsp;
      <button class="btn btn-dark" @click="checkHandler">수정</button>
      &nbsp;
      <button class="btn btn-dark" @click="moveList">목록</button>
      &nbsp;
    </div>
    <br />
  </card>
</template>

<script>
import axios from "axios";

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "BoardUpdate",
  data: function() {
    return {
      list: {
        board_id: "",
        title: "",
        writer: "",
        board_dt: "",
        content: "",
        secret: ""
      },
      go: this.$route.query.board_id
      // URL 쿼리를 받아 오는 방식
      // go: this.$route.params.board_id

      // 보낸 쿼리를 받아오는 방식
    };
  },
  created() {
    axios
      .get(`${SERVER_URL}/board/callmodi/${this.go}`)
      .then(res => {
        this.list = res.data.board;
        console.log(res.data);
      })
      .catch(() => {
        console.log(this.list.board_id);
        console.log("에러가 발생했습니다.");
      });
  },
  methods: {
    moveList() {
      this.$router.push("/boardlist");
    },
    checked() {
      if (this.list.secret == true) {
        this.list.secret = 1;
        console.log(this.list.secret);
      } else if (this.list.secret == false) {
        this.list.secret = 0;
        console.log(this.list.secret);
      }
    },
    checkHandler() {
      let err = true;
      let msg = "";
      !this.list.title &&
        ((msg = "제목을 입력해주세요"),
        (err = false),
        this.$refs.title.focus());
      err &&
        !this.list.content &&
        ((msg = "내용을 입력해주세요"),
        (err = false),
        this.$refs.content.focus());
      if (!err) alert(msg);
      else this.createHandler();
    },

    createHandler() {
      axios
        .put(`${SERVER_URL}/board/modify`, {
          board_id: this.list.board_id,
          title: this.list.title,
          writer: this.list.writer,
          board_dt: this.list.board_dt,
          content: this.list.content,
          secret: this.list.secret
        })
        .then(({ data }) => {
          let msg = "수정이 완료되었습니다";
          alert(msg);
          this.moveList();
        });
    }
  }
};
</script>

<style scoped></style>
