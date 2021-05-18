<template>
  <div>삭제중...</div>
</template>

<script>
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'delete',
  created() {
    console.log(this.$route.query.no);
    axios
      .delete(`${SERVER_URL}/notice/${this.$route.query.no}`)
      .then(({ data }) => {
        let msg = '삭제 처리시 문제가 발생했습니다.';
        if (data === 'success') {
          msg = '삭제가 완료되었습니다.';
        }
        alert(msg);
        this.$router.push('/notice');
      })
      .catch(() => {
        alert('삭제 처리시 에러가 발생했습니다.');
      });
  },
};
</script>

<style></style>
