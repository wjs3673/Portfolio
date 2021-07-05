<template>
  <tr class="table-info">
    <td colspan="1" style="background-color: white">
      <strong>{{ search_id }}</strong>
    </td>
    <td colspan="3">
      <strong>{{ search_nm }}</strong>
    </td>
    <td colspan="8" style="background-color: white">
      <strong>{{ search_addr }}</strong>
    </td>
  </tr>
</template>

<script>
export default {
  name: "funeralrow",
  props: {
    search_id: { type: Number },
    search_nm: { type: String },
    search_addr: { type: String }
  }
};
</script>
