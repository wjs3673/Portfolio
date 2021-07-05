<template>
  <card title="Q&A" sub-title="질문을 공유하고 답해주세요.">
    <div>
      <b-table
        striped
        hover
        :items="items"
        :fields="fields"
        @row-clicked="rowClick"
      >
      </b-table>
    </div>
    <div class="text-right" style="margin-right : 20px">
      <button class="btn btn-primary" @click="movePage">등록</button>
    </div>
  </card>
</template>
<script>
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
  data: function() {
    return {
      fields: ["noq", "title", "name", "regDate"],
      items: []
    };
  },
  created() {
    axios
      .get(`${SERVER_URL}/qna/q`)
      .then(({ data }) => {
        this.items = data;
      })
      .catch(() => {
        // console.log("catch");
      });
  },
  methods: {
    movePage() {
      this.$router.push("/qna/regist");
    },
    rowClick(items) {
      this.$router.push("/qna/read?noq=" + items.noq);
    }
  }
  // components: {
  //   QLine,
  // },
};
</script>
<style>
table {
  text-align: center;
  width: 94%;
  margin: 0, 3%;
}
tr {
  height: 30px;
}
#thead {
  height: 50px;
}
</style>
