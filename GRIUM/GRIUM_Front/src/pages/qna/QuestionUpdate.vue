<template>
  <card title="수정">
    <div class="form-group">
      <label for="name">이름</label> &nbsp;
      <input
        type="text"
        class="form-control"
        id="name"
        ref="name"
        v-model="name"
        readonly
      />
    </div>
    <br />
    <div class="form-group">
      <label for="title">제목</label> &nbsp;
      <input
        type="text"
        class="form-control"
        id="title"
        ref="title"
        v-model="title"
      />
    </div>
    <br />
    <div class="form-group">
      <label for="text">내용</label> &nbsp;
      <textarea
        type="text"
        class="form-control"
        id="text"
        ref="text"
        v-model="text"
      ></textarea>
    </div>
    <br />
    <div class="text-right">
      <button class="btn btn-primary" @click="checkHandler">수정</button> &nbsp;
      <button class="btn btn-primary" @click="moveList">목록</button>
    </div>
    <br />
  </card>
</template>

<script>
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
  name: "board-Form",
  props: {
    type: { type: String }
  },
  data: function() {
    return {
      noq: "",
      userid: "",
      name: "",
      title: "",
      text: "",
      regDate: ""
    };
  },
  methods: {
    checkHandler() {
      let err = true;
      let msg = "";
      !this.name &&
        ((msg = "이름을 입력해주세요"), (err = false), this.$refs.name.focus());
      err &&
        !this.title &&
        ((msg = "제목을 입력해주세요"),
        (err = false),
        this.$refs.title.focus());
      err &&
        !this.text &&
        ((msg = "내용을 입력해주세요"), (err = false), this.$refs.text.focus());
      if (!err) alert(msg);
      //else this.type == 'create' ? this.createHandler() : this.updateHandler();
      else this.updateHandler();
    },

    updateHandler() {
      axios
        .put(`${SERVER_URL}/qna/q`, {
          noq: this.noq,
          userid: this.userid,
          name: this.name,
          title: this.title,
          text: this.text,
          regDate: this.regDate
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data > 0) {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        })
        .catch(() => {
          alert("수정 처리시 에러가 발생했습니다.");
        });
    },
    moveList() {
      this.$router.push("/qna/list");
    }
  },

  created() {
    axios
      .get(`${SERVER_URL}/qna/q/${this.$route.query.noq}`)
      .then(({ data }) => {
        this.noq = data.noq;
        this.userid = data.userid;
        this.regDate = data.regDate;
        this.name = data.name;
        this.title = data.title;
        this.text = data.text;
      })
      .catch(() => {
        alert("에러가 발생했습니다.");
      });
  }
};
</script>

<style scoped></style>
