<template>
  <div>
    <b-sidebar
      id="sidebar-right"
      class="sidebar_header"
      title="상세정보"
      backdrop-variant="dark"
      width="400px"
      right
      backdrop
      shadow
    >
      <b-tabs content-class="mt-3">
        <b-tab title="아파트" active>
          <div class="px-3 py-2">
            <div v-if="apts.length > 0">
              <sidebarListItem
                v-for="(apt, index) in apts"
                :key="`${index}_apts`"
                type="apt"
                :building="apt"
                :id="getUserId"
                :dongName="dongName"
                :marked="isMarked(apt)"
                @click.native="detail(apt, 'apt')"
              ></sidebarListItem>
            </div>
            <div v-else>
              아파트 정보가 조회되지 않았습니다.
            </div>
          </div>
        </b-tab>
        <b-tab title="연립다세대">
          <div class="px-3 py-2">
            <div v-if="residences.length > 0">
              <sidebarListItem
                v-for="(residence, index) in residences"
                :key="`${index}_residences`"
                type="residence"
                :building="residence"
                :id="getUserId"
                :dongName="dongName"
                :marked="isMarked(residence)"
                @click.native="detail(residence, 'residence')"
              ></sidebarListItem>
            </div>
            <div v-else>
              연립다세대 정보가 조회되지 않았습니다.
            </div>
          </div></b-tab
        >
      </b-tabs>
    </b-sidebar>
  </div>
</template>
<script>
import sidebarListItem from '@/components/SidebarListItem.vue';
import http from '@/util/http-common';
import { mapGetters } from 'vuex';
export default {
  name: 'Sidebar',
  components: {
    sidebarListItem,
  },
  data() {
    return { bookmarks: [] };
  },
  computed: {
    ...mapGetters(['getUserId']),
  },
  props: {
    apts: { type: Array },
    residences: { type: Array },
    dongName: { type: String },
  },

  watch: {
    dongName: function() {
      if (this.getUserId != '') {
        this.getBookmarks();
      }
    },
  },
  methods: {
    detail(building, type) {
      this.$emit('mapDetail', building, type);
    },

    getBookmarks: function() {
      http
        .get(`/bookmark/get/${this.getUserId}/${this.dongName}`)
        .then(({ data }) => {
          this.bookmarks = data;
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    },

    isMarked: function(building) {
      let name =
        building.aptName != undefined
          ? building.aptName
          : building.buildingName;

      for (let i = 0; i < this.bookmarks.length; ++i) {
        if (this.bookmarks[i].buildingName == name) {
          return true;
        }
      }
      return false;
    },
  },
};
</script>
