<template>
  <table class="table table-bordered w-20">
    <tr>
      <th>글쓴이</th>
      <td>
        <input
          type="text"
          class="form-control"
          id="name"
          ref="name"
          placeholder="이름을 입력하세요"
          v-model="name"
        />
      </td>
    </tr>
    <tr>
      <th>내용</th>
      <td>
        <textarea
          type="text"
          class="form-control"
          id="text"
          ref="text"
          placeholder="내용을 입력하세요"
          v-model="text"
        ></textarea>
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <button class="btn btn-primary" @click="checkHandler">
          등록
        </button>
      </td>
    </tr>
  </table>
</template>
<script>
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "board-Form",
  props: {
    type: { type: String },
    noq: { type: Number }
  },
  data: function() {
    return {
      noa: "",
      userid: "",
      name: "",
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
        !this.text &&
        ((msg = "내용을 입력해주세요"), (err = false), this.$refs.text.focus());
      if (!err) alert(msg);
      //else this.type == 'create' ? this.createHandler() : this.updateHandler();
      else this.createHandler();
    },

    createHandler() {
      axios
        .post(`${SERVER_URL}qna/a`, {
          noq: this.noq,
          userid: this.name,
          name: this.name,
          title: this.title,
          text: this.text,
          regDate: this.regDate
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data == 0) {
            alert(msg);
          }
          this.$emit("update");
          this.name = "";
          this.text = "";
        })
        .catch(() => {
          alert("등록 처리시 에러가 발생했습니다.");
        });
    }
  }
};
</script>
