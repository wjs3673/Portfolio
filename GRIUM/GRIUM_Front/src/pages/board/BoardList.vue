<template>
  <div>
    <card title="예비게시판" sub-title="고인에게 전하고픈 말">
      <div class="text-right" style="margin-right : 20px">
        <button class="btn btn-dark" @click="insert">등록</button> &nbsp;
      </div>
      <div>
        <p></p>
        <div v-if="lists.length != 0">
          <table class="table table-active">
            <list-row
              v-for="list in lists"
              :key="list.board_id"
              :board_id="list.board_id"
              :title="list.title"
              :writer="list.writer"
              :board_dt="list.board_dt"
              id="my-table"
              :per-page="paginations.listSize"
              :current-page="currentPage"
            />
          </table>
        </div>
        <div v-else>
          <table class="table table-active">
            <tbody>
              <tr class="table-info" align="center">
                <td style="background-color: white">작성된 글이 없습니다.</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div class="Page" align="center">
        <nav aria-label="Page navigation">
          <ul class="pagination">
            <li class="page-item">
              <input
                type="button"
                class="page-link"
                @click="prevPage"
                style="width:40px;text-align:center; color:black;"
                value="<"
              />
            </li>

            <li
              class="page-item"
              v-for="(list, idx) in this.listmaker"
              v-bind:key="idx"
            >
              <input
                type="button"
                class="page-link"
                @click="movePage"
                v-bind:value="idx + 1"
                style="width:40px;text-align:center; color:black;"
              />
              <input
                type="text"
                placeholder="listData"
                v-bind:value="idx + 1"
                @change="updateList"
                disabled
                style="display:none; color:black;"
              />
            </li>
            <li class="page-item">
              <input
                type="button"
                class="page-link"
                @click="nextPage"
                style="width:40px;text-align:center; color:black;"
                value=">"
              />
            </li>
          </ul>
        </nav>
      </div>
    </card>
  </div>
</template>
<script>
import ListRow from "@/components/board/BoardRow.vue";
import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "BoardList",
  computed: {
    rows() {
      return this.paginations.listCnt;
    }
  },
  components: {
    ListRow
  },
  data: function() {
    return {
      listmaker: 0,
      prevnext: 0,
      lists: {
        board_id: 0,
        title: "",
        writer: "",
        board_dt: ""
      },
      paginations: {
        listSize: "",
        startPage: "",
        listCnt: ""
      },
      currentPage: 1,
      perPage: "",
      user_id: "",
      gone_id: ""
    };
  },
  created() {
    this.user_id = this.$store.getters["getUserid"];
    this.gone_id = this.$store.getters["getGone"];

    axios
      .get(`${SERVER_URL}/board/makelist`, {
        params: {
          gone_id: this.gone_id,
          user_id: this.user_id
        }
      })
      .then(res => {
        this.lists = res.data.list;
        this.paginations = res.data.pagination;
        this.listmaker = parseInt(
          this.paginations.listCnt / this.paginations.listSize + 1
        );

        console.log(res.data.list);
        console.log(res.data.pagination);
      })
      .catch(() => {
        console.log("실패");
      });
  },
  methods: {
    movePage(event) {
      var updatedText = event.target.value;
      this.currentPage = updatedText;
      this.prevnext = updatedText;

      axios
        .get(`${SERVER_URL}/board/list/${this.currentPage}`, {
          params: {
            gone_id: this.gone_id,
            user_id: this.user_id
          }
        })
        .then(res => {
          this.lists = res.data.list;
          console.log(res.data.list);

          this.list = parseInt(
            this.paginations.listCnt / this.paginations.listSize
          );
        })
        .catch(() => {
          console.log("FAILURE!!");
        });
    },
    prevPage() {
      if (this.prevnext > 1) {
        this.prevnext -= 1;
        this.currentPage = this.prevnext;
        // alert(this.prevnext);
        axios
          .get(`${SERVER_URL}/board/list/${this.currentPage}`, {
            params: {
              gone_id: this.gone_id,
              user_id: this.user_id
            }
          })
          .then(res => {
            this.lists = res.data.list;
            console.log(res.data.list);

            this.list = parseInt(
              this.paginations.listCnt / this.paginations.listSize
            );
          })
          .catch(() => {
            console.log("FAILURE!!");
          });
      }
    },
    nextPage() {
      if (this.prevnext <= this.list) {
        this.prevnext++;
        this.currentPage = this.prevnext;
        alert(this.prevnext);
        axios
          .get(`${SERVER_URL}/board/list/${this.currentPage}`, {
            params: {
              gone_id: this.gone_id,
              user_id: this.user_id
            }
          })
          .then(res => {
            this.lists = res.data.list;
            console.log(res.data.list);

            this.list = parseInt(
              this.paginations.listCnt / this.paginations.listSize
            );
          })
          .catch(() => {
            console.log("FAILURE!!");
          });
      }
    },
    updateList: function(event) {
      var updatedText = event.target.value;
      this.currentPage = updatedText;
    },
    insert() {
      this.$router.push("/boardinsert");
    },
    MovePage: function(page) {
      console.log("안녕안녕");
      console.log(this.currentPage);

      this.currentPage = page;
      console.log(page);

      axios
        .get(`${SERVER_URL}/board/list/${this.currentPage}`, {
          params: {
            gone_id: this.gone_id,
            user_id: this.user_id
          }
        })
        .then(res => {
          this.lists = res.data.list;
          console.log(res.data.list);
        })
        .catch(() => {
          console.log("실패");
        });
    }
  }
};
</script>
<style>
table {
  text-align: center;
  width: 94%;
  margin: 0, 3%;
}
tr {
  height: 30px;
}
#thead {
  height: 50px;
}
.page-item {
  color: black;
}
</style>
