<template>
  <!-- <v-carousel cycle height="800" hide-delimiter-background show-arrows-on-hover>
    <v-carousel-item v-for="(slide, i) in slides" :key="i">
      <v-sheet :color="colors[i]" height="100%">
        <v-row class="fill-height" align="center" justify="center">
          <div class="display-3">
            <h3>{{ slide }}</h3>
          </div>
        </v-row>
      </v-sheet>
    </v-carousel-item>
  </v-carousel> -->
  <v-carousel
    cycle
    height="800"
    :show-arrows="false"
    hide-delimiter-background
    show-arrows-on-hover
  >
    <!-- <v-carousel-item v-for="(item, i) in items.text" :key="i" :src="item.src"></v-carousel-item>
      <v-row class="fill-height" align="center" justify="center">
        <div class="display-3">
          <h3>{{ item.text }}</h3>
        </div>
      </v-row>
    </v-carousel-item> -->
    <v-carousel-item v-for="(slide, i) in slides" :key="i">
      <v-sheet :color="colors.src" height="100%">
        <v-row class="fill-height" align="center" justify="center">
          <div class="display-3">
            <!-- <img src="https://cdn.pixabay.com/photo/2021/02/05/18/05/paper-5985445_960_720.jpg" alt=""> -->
            <h2>{{ slide }}</h2>
          </div>
        </v-row>
      </v-sheet>
    </v-carousel-item>
  </v-carousel>
</template>

<script>
  export default {
    data () {
      return {
        colors: [
          'dark',
          'dark',
          'dark',
          'dark',
        ],
        slides: [
          '“ 한 사람을 이세상에서 떠나보내는 것 ',
          '그리고,',
          '	한 사람을 이 세상에서 기억되게 하는 것 ”',
          '그 것이 “그리움” 이 해낼 일입니다.',
        ],
      }
    },
  }

</script>

// export default {
//   data() {
//     return {
//       colors: ["dark", "dark", "dark", "dark"],
//       slides: [
//         "“ 한 사람을 이세상에서 떠나보내는 것 ",
//         "그리고,",
//         "	한 사람을 이 세상에서 기억되게 하는 것 ”",
//         "그 것이 “그리움” 이 해낼 일입니다."
//       ],
//       items: [
//         {
//           src:
//             "https://cdn.pixabay.com/photo/2021/02/05/18/05/paper-5985445_960_720.jpg",
//           text: "“ 한 사람을 이세상에서 떠나보내는 것 "
//         },
//         {
//           src:
//             "https://cdn.pixabay.com/photo/2021/02/05/18/05/paper-5985445_960_720.jpg",
//           text: "그리고,"
//         },
//         {
//           src:
//             "https://cdn.pixabay.com/photo/2021/02/05/18/05/paper-5985445_960_720.jpg",
//           text: "	한 사람을 이 세상에서 기억되게 하는 것 ”"
//         },
//         {
//           src:
//             "https://cdn.pixabay.com/photo/2021/02/05/18/05/paper-5985445_960_720.jpg",
//           text: "그 것이 “그리움” 이 해낼 일입니다."
//         }
//       ]
//     };
//   }
// };
// </script>
