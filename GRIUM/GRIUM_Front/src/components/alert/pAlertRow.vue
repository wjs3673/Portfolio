<template>
  <div>
    <!-- 사진 -->
    <card>
      <tr class="b-table">
        <td style="background-color: white">
          <b-col sm>
            <div class="fontcolor">{{ name }} 님이 앨범에서 신고했습니다.</div>
          </b-col>
        </td>
        <td style="background-color: white">
          <b-col sm>
            <div class="fontcolor">PhotoNo. {{ photo_id }}</div>
          </b-col>
        </td>
      </tr>
    </card>
  </div>
</template>

<script>
import Card from "../Cards/Card.vue";
export default {
  components: { Card },
  name: "alertrow",
  props: {
    name: { type: String },
    photo_id: { type: Number }
  }
};
</script>
<style>
.fontcolor {
  color: black;
}
</style>
