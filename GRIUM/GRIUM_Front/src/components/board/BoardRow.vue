<template>
  <tr class="b-table">
    <td style="background-color: white">
      <b-col sm>
      <router-link :to="`/boardread?board_id=${board_id}`">
        <strong>{{ board_id }}</strong>
      </router-link>
      </b-col>
    </td>
    <td style="background-color: white">
      <b-col sm>
      <router-link :to="`/boardread?board_id=${board_id}`">
        <strong>{{ title }}</strong>
      </router-link>
      </b-col>
    </td>

    <td style="background-color: white">
      <b-col sm>
        {{ writer }}
      </b-col>
    </td>

        <td style="background-color: white">
      <b-col sm>
      {{ board_dt }}
      </b-col>
    </td>

  </tr>
</template>

<script>
export default {
  name: "boardrow",
  props: {
    board_id: { type: Number },
    title: { type: String },
    writer: { type: String },
    board_dt: { type: String },
    content: { type: String }
  }
};
</script>
