<template>
  <div>
    <b-sidebar
      id="sidebar-right-detail"
      class="sidebar_header"
      :title="title"
      width="380px"
      left
      shadow
      no-header
      v-model="show"
    >
      <p class="text-right" style="padding:10pt">
        <a @click="closeSidebar" style="margin:10pt 10pt">X</a>
      </p>
      <h3>{{ title }}</h3>
      <sidebar-list-item-detail
        v-for="(d, index) in detail"
        :key="`${index}_detail`"
        :type="type"
        :building="d"
      ></sidebar-list-item-detail>
    </b-sidebar>
  </div>
</template>
<script>
import SidebarListItemDetail from '@/components/SidebarListItemDetail.vue';
import { mapGetters } from 'vuex';
export default {
  name: 'SidebarDetail',
  components: { SidebarListItemDetail },
  data() {
    return {
      show: false,
    };
  },
  computed: {
    ...mapGetters(['getSidebarDetail']),
  },
  props: {
    type: { type: String },
    title: { type: String },
    detail: { type: Array },
  },
  watch: {
    getSidebarDetail() {
      if (this.show) this.show = false;
      else this.show = true;
    },
  },
  methods: {
    closeSidebar() {
      this.$store.commit('SidebarClose');
    },
  },
};
</script>
