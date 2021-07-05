<template>
  <div>
    <card title="질문">
      <q-detail
        :title="question.title"
        :userid="question.userid"
        :name="question.name"
        :regDate="question.regDate"
        :text="question.text"
        :noq="question.noq"
      />
    </card>
    <br />
    <card title="답변">
      <a-form v-on:update="refresh" :noq="question.noq"></a-form>
      <a-detail
        v-on:delete="refresh"
        v-bind="answers"
        v-for="(answer, idx) in answers"
        :key="idx"
        :userid="answer.userid"
        :name="answer.name"
        :text="answer.text"
        :regDate="answer.regDate"
        :noq="answer.noq"
        :noa="answer.noa"
      />
    </card>
  </div>
</template>
<script>
import QDetail from "@/components/qna/QuestionDetail.vue";
import ADetail from "@/components/qna/AnswerDetail.vue";
import AForm from "@/components/qna/AnswerForm.vue";

const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
  name: "read",
  components: {
    QDetail,
    ADetail,
    AForm
  },
  data: function() {
    return {
      question: {},
      answers: []
    };
  },
  methods: {
    refresh() {
      axios
        .get(`${SERVER_URL}/qna/a/${this.$route.query.noq}`)
        .then(({ data }) => {
          this.answers = data;
          console.log(data);
        });
      // window.reload();
      // this.$router.push(`/read?noq=${this.$route.query.noq}`);
    }
  },
  created() {
    axios
      .get(`${SERVER_URL}/qna/q/${this.$route.query.noq}`)
      .then(({ data }) => {
        this.question = data;
        console.dir(data);
      });
    axios
      .get(`${SERVER_URL}/qna/a/${this.$route.query.noq}`)
      .then(({ data }) => {
        this.answers = data;
        console.log(data);
      });
  }
};
</script>
