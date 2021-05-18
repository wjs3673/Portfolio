<template>
  <div>
    <b-dropdown-item v-if="type == 'sido'" @click="setSido">{{
      data.sido_name
    }}</b-dropdown-item>
    <b-dropdown-item v-if="type == 'gugun'" @click="setGugun">{{
      data.gugun_name
    }}</b-dropdown-item>
    <b-dropdown-item v-if="type == 'dong'" @click="setDong">{{
      data.dong
    }}</b-dropdown-item>
  </div>
</template>

<script>
export default {
  name: 'detail',
  props: {
    type: { type: String },
    data: { type: Object },
  },
  methods: {
    setSido: function() {
      this.$emit('select-sido', this.data);
    },
    setGugun: function() {
      this.$emit('select-gugun', this.data);
    },
    setDong: function() {
      this.$emit('select-dong', this.data);
    },
  },
};
</script>
