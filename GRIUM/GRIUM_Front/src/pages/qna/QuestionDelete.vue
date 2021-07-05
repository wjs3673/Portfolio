<template>
  <div>삭제중...</div>
</template>

<script>
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
  name: "delete",
  created() {
    axios
      .delete(`${SERVER_URL}/qna/q/${this.$route.query.noq}`)
      .then(({ data }) => {
        let msg = "삭제 처리시 문제가 발생했습니다.";
        if (data > 0) {
          msg = "삭제가 완료되었습니다.";
        }
        alert(msg);
        this.$router.push("/qna/list");
      })
      .catch(() => {
        alert("삭제 처리시 에러가 발생했습니다.");
      });
  }
};
</script>

<style></style>
