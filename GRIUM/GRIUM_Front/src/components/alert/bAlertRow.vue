<template>
  <div>
    <card>
      <tr class="b-table">
        <td style="background-color: white">
          <b-col sm>
            <router-link :to="`/boardread?board_id=${board_id}`">
              <div class="fontcolor">
                {{ name }} 님이 게시판에서 신고했습니다.
              </div>
            </router-link>
          </b-col>
        </td>
        <td style="background-color: white">
          <b-col sm>
            <router-link :to="`/boardread?board_id=${board_id}`">
              <div class="fontcolor">BoardNo. {{ board_id }}</div>
            </router-link>
          </b-col>
        </td>
        <td style="background-color: white">
          <b-col sm>
            <router-link :to="`/boardread?board_id=${board_id}`">
              <div class="fontcolor">
                {{ content }}
              </div>
            </router-link>
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
    board_id: { type: Number },
    content: { type: String }
  }
};
</script>
<style>
.fontcolor {
  color: black;
}
</style>
