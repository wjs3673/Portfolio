<template>
  <div class="container" align="center">
    <div class="col-lg-6" align="center">
      <div class="form-group">
        <!-- <div class="form-group" align="left">
          <label for="id">작성자:</label>
          <input
            type="text"
            class="form-control"
            name="id"
            id="id"
            v-model="id"
          />
        </div> -->
        <div class="form-group" align="left">
          <label for="title">제목:</label>
          <input
            type="text"
            class="form-control"
            id="title"
            name="title"
            v-model="title"
          />
        </div>
        <div class="form-group" align="left">
          <label for="content">내용:</label>
          <textarea
            class="form-control"
            rows="15"
            v-model="content"
            id="content"
            name="content"
          ></textarea>
        </div>
      </div>
      <div class="text-right">
        <button @click="clear" class="btn btn-warning">초기화</button>
        <button
          class="btn btn-primary button"
          v-if="type == 'create'"
          @click="checkHandler"
        >
          등록
        </button>
        <button class="btn btn-primary button" v-else @click="checkHandler">
          수정
        </button>
        <button class="btn btn-primary button" @click="moveList">목록</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'board-Form',
  props: {
    type: { type: String },
  },
  data: function() {
    return {
      no: '',
      id: 'ssafy',
      title: '',
      content: '',
      regtime: '',
    };
  },
  methods: {
    checkHandler() {
      let err = true;
      let msg = '';
      !this.id &&
        ((msg = '작성자를 입력해주세요'), (err = false), this.$refs.id.focus());
      err &&
        !this.title &&
        ((msg = '제목 입력해주세요'), (err = false), this.$refs.title.focus());
      err &&
        !this.content &&
        ((msg = '내용 입력해주세요'),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else this.type == 'create' ? this.createHandler() : this.updateHandler();
    },
    createHandler() {
      axios
        .post(`${SERVER_URL}/notice/create`, {
          id: this.id,
          title: this.title,
          content: this.content,
        })
        .then(({ data }) => {
          let msg = '등록 처리시 문제가 발생했습니다.';
          if (data === 'success') {
            msg = '등록이 완료되었습니다.';
          }
          alert(msg);
          this.moveList();
        })
        .catch(() => {
          alert('등록 처리시 에러가 발생했습니다.');
        });
    },
    updateHandler() {
      axios
        .put(`${SERVER_URL}/notice/${this.no}`, {
          no: this.no,
          regtime: this.regtime,
          id: this.id,
          title: this.title,
          content: this.content,
        })
        .then(({ data }) => {
          let msg = '수정 처리시 문제가 발생했습니다.';
          if (data === 'success') {
            msg = '수정이 완료되었습니다.';
          }
          alert(msg);
          this.moveList();
        })
        .catch(() => {
          alert('수정 처리시 에러가 발생했습니다.');
        });
    },
    moveList() {
      this.$router.push('/notice');
    },
    clear() {
      this.content = '';
      this.title = '';
    },
  },
  created() {
    if (this.type === 'update') {
      axios
        .get(`${SERVER_URL}/notice/${this.$route.query.no}`)
        .then(({ data }) => {
          this.no = data.no;
          this.regtime = data.regtime;
          this.id = data.id;
          this.title = data.title;
          this.content = data.content;
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    }
  },
};
</script>
<style>
.button {
  margin: 0 2pt;
}
</style>
