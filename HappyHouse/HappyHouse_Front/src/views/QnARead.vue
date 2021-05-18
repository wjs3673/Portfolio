<template>
  <div>
    <detail
      :no="item.no"
      :title="item.title"
      :id="item.id"
      :regtime="item.regtime"
      :content="item.content"
    />
  </div>
</template>

<script>
import http from '@/util/http-common';
import Detail from '@/components/QnADetail.vue';

export default {
  name: 'qnaread',
  components: {
    Detail,
  },
  data: function() {
    return {
      item: {},
    };
  },
  created() {
    http.get(`/qna/${this.$route.query.no}`).then(({ data }) => {
      this.item = data;
      console.dir(data);
    });
  },
};
</script>

<style></style>
