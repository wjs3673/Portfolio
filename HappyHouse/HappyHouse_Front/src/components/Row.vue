<template>
  <tr class="table-info">
    <td colspan="4" style="background-color: white;">
      <strong>{{ no }}</strong>
    </td>
    <td colspan="4" style="background-color: white;">
      <router-link :to="`/read?no=${no}`">
        <strong>{{ title }}</strong>
      </router-link>
    </td>
    <td colspan="4" style="background-color: white;">
      <strong>{{ id }}</strong>
    </td>
    <td colspan="4" style="background-color: white;">
      <strong>{{ getFormatDate(regtime) }}</strong>
    </td>
  </tr>
</template>

<script>
import moment from 'moment';

export default {
  name: 'row',
  props: {
    no: { type: Number },
    title: { type: String },
    id: { type: String },
    regtime: { type: String },
  },
  methods: {
    getFormatDate(regtime) {
      return moment(new Date(regtime)).format('YYYY.MM.DD');
    },
  },
};
</script>
