<template>
  <card title=" ">
    <div class="form-group">
      <label for="title">제목</label> &nbsp;
      <input
        type="text"
        class="form-control"
        id="title"
        ref="title"
        placeholder="제목을 입력하세요"
        v-model="title"
      />
    </div>
    <br />
    <div class="form-group">
      <label for="writer">작성자</label> &nbsp;
      <input
        type="text"
        class="form-control"
        id="writer"
        ref="writer"
        v-model="writer"
        :readonly="true"
      />
    </div>
    <br />
    <div class="form-group">
      <label for="content">내용</label> &nbsp;
      <textarea
        type="context"
        class="form-control"
        id="content"
        ref="content"
        placeholder="내용을 입력하세요"
        v-model="content"
        rows="10"
        cols="40"
      ></textarea>
    </div>
    <br />

    <div class="text-right">
      <label>
        <input type="checkbox" v-model="secret" @change="checked" />
        비밀글
      </label>
      &nbsp;
      <button class="btn btn-dark" @click="checkHandler">
        등록
      </button>
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
  name: "boardinsert",
  props: {
    type: { type: String }
  },
  data: function() {
    return {
      gone_id: "",
      board_id: "",
      title: "",
      writer: "",
      board_dt: "",
      content: "",
      secret: "",
      user_id: ""
    };
  },
  methods: {
    checkHandler() {
      let err = true;
      let msg = "";
      !this.title &&
        ((msg = "제목을 입력해주세요"),
        (err = false),
        this.$refs.title.focus());
      err &&
        !this.content &&
        ((msg = "내용을 입력해주세요"),
        (err = false),
        this.$refs.content.focus());
      if (!err) alert(msg);
      else this.createHandler();
    },

    createHandler() {
      let {
        gone_id,
        board_id,
        title,
        writer,
        board_dt,
        content,
        secret,
        user_id
      } = this;
      let board = {
        gone_id,
        board_id,
        title,
        writer,
        board_dt,
        content,
        secret,
        user_id
      };
      
      console.log(this.gone_id);
      console.log(this.user_id);

      axios
        .post(`${SERVER_URL}/board/insert`, board)
        .then(({ data }) => {
          let msg = "등록이 완료되었습니다";
          alert(msg);
          this.moveList();
        })
        .catch(() => {
          alert("등록 처리시 에러가 발생했습니다.");
        });
    },
    moveList() {
      this.$router.push("/boardlist");
    },
    checked() {
      if (this.secret == true) {
        this.secret = 1;
        console.log(this.secret);
      } else if (this.secret == false) {
        this.secret = 0;
        console.log(this.secret);
      }
    }
  },
  created() {
    this.writer = this.$store.getters["getName"];
    this.user_id = this.$store.getters["getUserid"];
    this.gone_id = this.$store.getters["getGone"];
    console.log(this.writer);
  }
};
</script>
