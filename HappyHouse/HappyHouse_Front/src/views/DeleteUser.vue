<template>
  <div>삭제중...</div>
</template>

<script>
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'deleteUser',
  created() {
    console.log('회원정보삭제' + this.$route.query.id);
    axios
      .delete(`${SERVER_URL}/user/${this.$route.query.id}`)
      .then(({ data }) => {
        let msg = '회원정보 삭제 처리시 문제가 발생했습니다.';
        if (data === 'success') {
          msg = '회원정보 삭제가 완료되었습니다.';
        }
        alert(msg);
        this.$store.dispatch('LOGOUT').then(() => this.$router.replace('/'));
      })
      .catch(() => {
        alert('회원정보 삭제 처리시 에러가 발생했습니다.');
      });
  },
};
</script>

<style></style>
