<template>
  <div>
    <div v-if="type == 'apt'">
      <table class="table">
        <colgroup>
          <col :style="{ width: '40%' }" />
          <col :style="{ width: '60%' }" />
        </colgroup>
        <!-- <tr>
          <td colspan="2">
            <img
              src="`@/assets/img/${building.aptName}`"
              alt="`@/assets/img/no_image.jpg`"
            />
          </td>
        </tr> -->
        <tr>
          <td>층</td>
          <td>{{ building.floor }}</td>
        </tr>
        <tr>
          <td>금액</td>
          <td>{{ building.dealAmount }}만원</td>
        </tr>
        <tr>
          <td>등록일</td>
          <td>
            {{ building.dealYear }}-{{ building.dealMonth }}-{{
              building.dealDay
            }}
          </td>
        </tr>
      </table>
    </div>
    <div v-else-if="type == 'residence'">
      <table class="table">
        <colgroup>
          <col :style="{ width: '40%' }" />
          <col :style="{ width: '60%' }" />
        </colgroup>
        <tr>
          <td>층</td>
          <td>{{ building.floor }}</td>
        </tr>
        <tr>
          <td>금액</td>
          <td>{{ building.dealAmount }}만원</td>
        </tr>
        <tr>
          <td>평수</td>
          <td>{{ building.area }}평</td>
        </tr>
      </table>
    </div>
    <hr />
  </div>
</template>

<script>
export default {
  name: 'sidebarListItemDetail',
  props: {
    type: { type: String },
    building: { type: Object },
  },
  methods: {},
};
</script>
