<template>
  <div>
    <card title=" " align="right">
      <div class="btn-group">
        <button class="btn" @click="Rip">
          <img
            v-if="riptype == 0"
            src="@/assets/img/애도안눌렀을때.png"
            alt="애도"
          />
          <img
            v-else-if="riptype == 1"
            src="@/assets/img/애도눌렀을때.png"
            alt="애도"
          />
        </button>
      </div>
      <div class="btn-group">
        <button class="btn" @click="Alert">
          <img
            v-if="alerttype == 0"
            src="@/assets/img/report_basic.png"
            alt="신고"
          />
          <img
            v-else-if="alerttype == 1"
            src="@/assets/img/report_msc.png"
            alt="신고"
          />
        </button>
      </div>
      <board-detail
        :board_id="list.board_id"
        :title="list.title"
        :writer="list.writer"
        :board_dt="list.board_dt"
        :content="list.content"
      />
    </card>
    <div class="form-group" align="center">
      <button
        class="btn btn-dark button"
        @click="moveUpdate"
        v-if="isAuth == true"
      >
        게시글 수정
      </button>
      <button
        class="btn btn-dark button"
        @click="moveDelete"
        v-if="isAuth == true"
      >
        게시글 삭제
      </button>
      <!-- </router-link> -->
      <button class="btn btn-dark button" @click="moveList">목록으로</button>
    </div>
  </div>
</template>
<script>
import BoardDetail from "@/components/board/BoardDetail.vue";
import axios from "axios";

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "boardread",
  components: {
    BoardDetail
  },
  data: function() {
    return {
      list: { board_id: "", title: "", writer: "", board_dt: "", content: "" },
      riptype: "",
      alerttype: "",
      isAuth: "",
      user_id: "",
      gone_id:""
    };
  },
  created() {
    this.user_id = this.$store.getters["getUserid"];
    this.gone_id = this.$store.getters["getGone"];
    console.log(this.user_id);
    
    axios
      .get(`${SERVER_URL}/board/getboard/${this.$route.query.board_id}`, {
        params: {
          gone_id: this.gone_id,
          user_id: this.user_id
        }
      })
      .then(res => {
        this.list = res.data.board;
        console.log(res.data.board);
        this.riptype = res.data.TYPE;
        this.alerttype = res.data.TYPE2;
        console.log(res.data);
        this.isAuth = res.data.isAuth;
      });
  },
  methods: {
    moveList() {
      this.$router.push("/boardlist");
    },
    moveUpdate() {
      console.log(this.list.board_id);
      let go = this.list.board_id;
      //  단순 URL 파라미터 전달방식
      this.$router.push(`/boardupdate?board_id=${go}`);
      // router.push({name: "BoardUpdate", params: {"board_id": this.list.board_id}});
      // this.$router.push(`/boardupdate?board_id=${go}`);
      //쿼리를 보내는 방식
    },
    moveDelete() {
      axios
        .delete(`${SERVER_URL}/board/delete`, {
          data: {
            board_id: this.list.board_id,
            title: this.list.title,
            writer: this.list.writer,
            board_dt: this.list.board_dt,
            content: this.list.content,
            secret: this.list.secret
          }
        })
        .then(({ data }) => {
          let msg = "삭제가 완료되었습니다.";
          if (data === "success") {
            msg = "삭제가 완료되었습니다.";
          }
          alert(msg);
          this.$router.push("/boardlist");
        })
        .catch(() => {
          console.log("삭제 처리시 에러가 발생했습니다.");
        });
    },
    Rip() {
      let ids = { board_id: this.list.board_id, user_id: this.user_id };

      axios
        .post(`${SERVER_URL}/board/rip`, ids)
        .then(response => {
          this.riptype = response.data.TYPE;
          console.log(this.riptype);
          if (this.riptype == 1) {
            alert("애도합니다.");
          } else if (this.riptype == 0) {
            alert("취소되었습니다.");
          }
        })
        .catch(() => {
          alert("등록 처리시 에러가 발생했습니다.");
        });
    },
    Alert() {
      let ids = {
        board_id: this.list.board_id,
        user_id: this.user_id,
        gone_id: this.gone_id
      };

      axios
        .post(`${SERVER_URL}/board/alert`, ids)
        .then(response => {
          this.alerttype = response.data.TYPE;
          console.log(this.alerttype);
          if (this.alerttype == 1) {
            alert("신고 등록이 되었습니다.");
          } else if (this.alerttype == 0) {
            alert("신고 등록이 취소되었습니다.");
          }
        })
        .catch(() => {
          alert("등록 처리시 에러가 발생했습니다.");
        });
    }
  }
};
</script>

<style>
.btn-group .btn {
  border: none;
}
.btn-group .btn:hover {
  background-color: rgba(255, 255, 255, 0.5);
  border: none;
}
img {
  height: 25px;
  width: 25px;
  border: none;
}
</style>
