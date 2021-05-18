<template>
  <tr class="table-info">
    <td colspan="4" style="background-color: white;">
      <strong>{{ hospital.city }}</strong>
    </td>
    <td colspan="4" style="background-color: white;">
      <strong>{{ hospital.gugun }}</strong>
    </td>
    <td colspan="4" style="background-color: white;">
      <strong>{{ hospital.hospital_name }}</strong>
    </td>
    <td colspan="4" style="background-color: white;">
      <strong>{{ hospital.address }}</strong>
    </td>
    <td colspan="4" style="background-color: white;">
      <strong>{{ hospital.phone_number }}</strong>
    </td>
  </tr>
</template>

<script>
export default {
  name: 'coronaListItem',
  props: {
    hospital: { type: Object },
  },
};
</script>
