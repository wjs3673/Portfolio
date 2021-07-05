<template>
  <div>
    <nav aria-label="Page navigation">
      <ul class="pagination">
        <li class="page-item">
          <input
            type="button"
            class="page-link"
            @click="prevPage"
            style="width:40px;text-align:center"
            value="<"
          />
        </li>

        <li class="page-item" v-for="(list, idx) in this.list" v-bind:key="idx">
          <input
            type="button"
            class="page-link"
            @click="movePage"
            v-bind:value="idx + 1"
            style="width:40px;text-align:center"
          />
          <input
            type="text"
            placeholder="listData"
            v-bind:value="idx + 1"
            @change="updateList"
            disabled
            style="display:none"
          />
        </li>
        <li class="page-item">
          <input
            type="button"
            class="page-link"
            @click="nextPage"
            style="width:40px;text-align:center"
            value=">"
          />
        </li>
      </ul>
    </nav>
    <div class="row">
      <div class="col-sm-6 col-md-3">
        <card class="cardsize">
          <div class="text-center">
            <img
              id="filechange"
              alt="PHOTO"
              v-on:click="fileopenclick"
              style="width: 50%; height:50%"
              src="@/assets/img/plus_unpush.png"
            />

            <div>
              <input
                style="display: none"
                type="text"
                placeholder="userid"
                v-bind:value="this.user.email"
                v-on:input="updateUserid"
                disabled
              />
              <input
                style="display: none"
                type="text"
                placeholder="writer"
                v-bind:value="this.user.writer"
                v-on:input="updateWriter"
                disabled
              />
              <input
                style="display: none"
                type="text"
                placeholder="secret"
                v-bind:value="this.user.secret"
                disabled
              />
            </div>
            <input
              style="display: none"
              type="file"
              @change="onFileSelected"
              ref="files"
              id="files"
              name="upfile"
              multiple="multiple"
            />
          </div>
          <div class="text-center" style="margin-top: 25px">
            <p>
              <strong
                >If you press the
                <mark style="background-color: #aad4ff">plus button</mark>,
                <br />you can register photos.</strong
              >
            </p>
            <div>
              <button
                v-on:click="onUpload()"
                style="border: none"
                class="btn btn-dark"
              >
                UPLOAD
              </button>
            </div>
            <br />
            <p><mark>SECRET</mark></p>
            <p style="color: grey; font-size: 12px">
              If you want secret version, Switch the button
            </p>

            <div>
              <ToggleButton
                id="onoff"
                :defaultState="true"
                v-on:change="triggerEvent"
              />
            </div>
          </div> </card
        ><!--카드 마지막-->
      </div>
      <div
        class="col-sm-6 col-md-3"
        v-for="(gall, idx) in gallery"
        v-bind:key="idx"
      >
        <card class="cardsize">
          <div class="text-center">
            <div class="text-center">
              <img
                src="@/assets/img/close.png"
                @click="DeletePhoto(gall)"
                alt="X"
              />
            </div>
            <div id="demo" class="carousel slide" data-ride="carousel">
              <div class="carousel-inner">
                <div
                  class="carousel-item active"
                  v-for="(photo, idx) in gall.gaddress"
                  v-bind:key="idx"
                >
                  <div>
                    <img
                      :src="photo.server_path"
                      alt="SORRY"
                      class="photosize"
                    />
                  </div>
                </div>

                <!-- <div class="carousel-item ">
                <div>
                  <img src="@/assets/img/condol_push.png" alt="PHOTO" />
                </div>
              </div> -->
              </div>
              <h6 style="margin-bottom:15px;">
                <mark style="background-color:#eef3ff"
                  ><b>E-mail : </b>{{ gall.email }}</mark
                >
              </h6>
              <!-- <a
              class="carousel-control-prev"
              href="#ab"
              role="button"
              data-slide="prev"
            >
              <span class="prev"></span>
            </a>

            <a
              class="carousel-control-next"
              href="#ab"
              role="button"
              data-slide="next"
            >
              <span class="next"></span>
            </a> -->
            </div>
          </div>
          <hr />

          <div class="text-center" style="height: 30px">
            <!-- <button
            class="btn-danger"
            @click="DeletePhoto(gall)"
            style="width:60px;height:30px;"
          >
            X
          </button> -->
            <div class="btn-group">
              <a class="btn" @click="Alert(gall)">
                <img
                  v-if="gall.alerttype == 0"
                  src="@/assets/img/report_basic.png"
                  alt="신고"
                  style="margin: 15px"
                />
                <img
                  v-else-if="gall.alerttype == 1"
                  src="@/assets/img/report_msc.png"
                  alt="신고"
                  style="margin: 15px"
                />
              </a>
            </div>

            <div class="btn-group">
              <a class="btn" @click="Rip(gall)">
                <img
                  v-if="gall.riptype == 0"
                  src="@/assets/img/condol_unpush.png"
                  alt="photo_condol"
                  style="height: 25px; width: 25px; margin: 15px"
                />
                <img
                  v-else-if="gall.riptype == 1"
                  src="@/assets/img/condol_push.png"
                  alt="photo_condol"
                  style="height: 25px; width: 25px"
                />
              </a>
            </div>
          </div>
        </card>
      </div>
      <div></div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Card from "../../components/Cards/Card.vue";
import ToggleButton from "../../components/ToggleButton.vue";

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  // el: "app",
  name: "Album",
  components: {
    Card,
    ToggleButton
  },
  data() {
    return {
      active: false,
      title: "",
      files: [],
      list: 0,
      prevnext: 0,
      pages: {
        listSize: 7, //한페이지에 몇개를 보여줄껀지
        rangeSize: 10, // 초기값으로 페이지범위를 10으로 셋팅
        page: 1, //현재 페이지 번호
        range: 0, //1~10볼건지 11~20 볼건지
        listCnt: 0, //전체페이지 게시물
        pageCnt: 0,
        startPage: 0, //1번 시작인지 11번 시작인지
        startList: 0,
        endPage: 0,
        isOwner: 0,
        prev: Boolean,
        next: Boolean
      },
      user: {
        //upload할때는 user로 보내줄꺼얌
        email: "",
        writer: "",
        secret: 0,
        flag: 0,
        user_id: "",
        gone_id: ""
      },
      gallery: {
        photo_id: 0,
        gone_id: "",
        user_id: "",
        email: "",
        writer: "",
        photo_dt: "",
        count: 0,
        secret: 0,
        flag: 0,
        alerttype: 0,
        riptype: 0,
        gaddress: { server_photo: "", server_path: "", origin_photo: "" }
      },
      rip: {
        photo_id: 0,
        user_id: "",
        type: ""
      },
      alert: {
        photo_id: 0,
        user_id: "",
        read_check: "",
        type: ""
      }
      // user_id:"",
      // gone_id:""
    };
  },
  mounted() {
    var vm = this;
    this.pages.page = 1;

    axios
      .get(`${SERVER_URL}/gallery/list/${this.pages.page}`, {
        params: {
          gone_id: this.$store.state.gone_id,
          user_id: this.$store.getters["getUserid"]
        }
      })
      .then(res => {
        this.gallery = res.data.list;
        vm.pages = res.data.pagination;
        console.log(this.gallery);
        console.log("SUCCESS!!");
        this.list = parseInt(this.pages.listCnt / this.pages.listSize + 1);
      })
      .catch(() => {
        console.log("FAILURE!!");
      });
  },
  methods: {
    DeletePhoto: function(data) {
      axios
        .delete(`${SERVER_URL}/gallery/delete`, {
          data: {
            photo_id: data.photo_id,
            user_id: this.$store.getters["getUserid"], //현재로그인한사람
            gone_id: data.gone_id //고인
          }
        })
        .then(response => {
          window.location.reload();
          alert(response.data.message);
        })
        .catch(() => {
          alert("DELETE FAIL");
          console.log("err");
        });
    },
    movePage(event) {
      var updatedText = event.target.value;
      this.pages.page = updatedText;
      this.prevnext = updatedText;
      // alert(this.pages.page);
      axios
        .get(`${SERVER_URL}/gallery/list/${this.pages.page}`, {
          params: {
            gone_id: this.$store.state.gone_id,
            user_id: this.$store.getters["getUserid"]
          }
        })
        .then(res => {
          this.gallery = res.data.list;
          vm.pages = res.data.pagination;
          console.log("pagination >> " + this.pages);
          console.log(this.pages);
          console.log("gallery >>> " + this.gallery);
          console.log(this.gallery);
          console.log("SUCCESS!!");
          this.list = parseInt(this.pages.listCnt / this.pages.listSize);
        })
        .catch(() => {
          console.log("FAILURE!!");
        });
    },
    prevPage() {
      if (this.prevnext > 1) {
        this.prevnext -= 1;
        this.pages.page = this.prevnext;
        // alert(this.prevnext);
        axios
          .get(`${SERVER_URL}/gallery/list/${this.pages.page}`, {
            params: {
              gone_id: this.$store.state.gone_id,
              user_id: this.$store.getters["getUserid"]
            }
          })
          .then(res => {
            this.gallery = res.data.list;
            vm.pages = res.data.pagination;
            console.log("pagination >> " + this.pages);
            console.log(this.pages);
            console.log("gallery >>> " + this.gallery);
            console.log(this.gallery);
            console.log("SUCCESS!!");
            this.list = parseInt(this.pages.listCnt / this.pages.listSize);
          })
          .catch(() => {
            console.log("FAILURE!!");
          });
      }
    },
    nextPage() {
      if (this.prevnext < this.list) {
        this.prevnext++;
        this.pages.page = this.prevnext;
        // alert(this.prevnext);

        axios
          .get(`${SERVER_URL}/gallery/list/${this.pages.page}`, {
            params: {
              gone_id: this.$store.state.gone_id,
              user_id: this.$store.getters["getUserid"]
            }
          })
          .then(res => {
            this.gallery = res.data.list;
            vm.pages = res.data.pagination;
            console.log("pagination >> " + this.pages);
            console.log(this.pages);
            console.log("gallery >>> " + this.gallery);
            console.log(this.gallery);
            console.log("SUCCESS!!");
            this.list = parseInt(this.pages.listCnt / this.pages.listSize);
          })
          .catch(() => {
            console.log("FAILURE!!");
          });
      }
    },
    updateList: function(event) {
      var updatedText = event.target.value;
      this.pages.page = updatedText;
    },

    updateUserid: function(event) {
      var updatedText = event.target.value;
      this.gallery.email = updatedText;
    },
    updateWriter: function(event) {
      var updatedText = event.target.value;
      this.gallery.writer = updatedText;
    },
    triggerEvent(value) {
      this.active = value;
      if (this.active) {
        this.user.secret = 0;
      } else {
        this.user.secret = 1;
      }
    },
    fileopenclick() {
      this.$refs.files.click();
    },
    onFileSelected() {
      this.files = this.$refs.files.files;
      console.log(this.files);
    },
    onUpload() {
      const formData = new FormData();
      //formData.append("title", this.title);
      // formData.append("gallery",this.gallery);
      formData.append(
        "gallery",
        new Blob([JSON.stringify(this.user)], { type: "application/json" })
      );
      for (let i = 0; i < this.files.length; i++) {
        formData.append("files", this.files[i]);
        // formData.append("files", new Blob([JSON.stringify(this.files[i])],{type:"application/json"}));
      }
      axios
        .post(`${SERVER_URL}gallery/upload`, formData, {
          headers: {
            "Content-Type": "multipart/form-data"
          }
        })
        .then(function() {
          console.log(formData);
          console.log("SUCCESS!!");
          window.location.reload();
        })
        .catch(function() {
          // console.log("바뀐거야안1221바뀐거야망할");
          console.log("FAILURE!!");
        });
    },
    Rip: function(data) {
      this.rip.photo_id = data.photo_id;
      this.rip.user_id = data.user_id;
      const formData = new FormData();
      formData.append(
        "rip",
        new Blob([JSON.stringify(this.rip)], { type: "application/json" })
      );
      axios
        .post(`${SERVER_URL}gallery/algo`, formData)
        .then(response => {
          this.gallery.riptype = response.data.TYPE;
          console.log("RIP SUCCESS !!");
          if (this.gallery.riptype == 1) {
            alert("애도합니다.");
          } else if (this.gallery.riptype == 0) {
            alert("취소되었습니다.");
          }
          window.location.reload();
        })
        .catch(response => {
          console.log("RIP FAIL !!");
        });
    },
    Alert: function(data) {
      this.alert.user_id = data.user_id;
      this.alert.photo_id = data.photo_id;
      this.alert.gone_id = data.gone_id;
      console.log(this.alert.gone_id);

      const formData = new FormData();
      formData.append(
        "alert",
        new Blob([JSON.stringify(this.alert)], { type: "application/json" })
      );
      axios
        .post(`${SERVER_URL}gallery/alert`, formData)
        .then(response => {
          this.gallery.alerttype = response.data.TYPE;
          console.log("ALERT SUCCESS !!");
          if (this.gallery.alerttype == 1) {
            alert("신고 등록이 되었습니다.");
          } else if (this.gallery.alerttype == 0) {
            alert("신고 등록이 취소되었습니다.");
          }
          window.location.reload();
        })
        .catch(response => {
          console.log("ALERT FAIL !!");
        });
    }
  },
  created() {
    this.user.email = this.$store.getters["getEmail"];
    this.user.writer = this.$store.getters["getName"];
    this.user.user_id = this.$store.getters["getUserid"];
    this.user.gone_id = this.$store.getters["getGone"];
    console.log(this.$store.getters["getUserid"]);
    console.log(this.$store.getters["getGone"]);
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
  border: none;
  /* margin-right: 25px;
  margin-bottom: 10px; */
}

/* .next:after {
  content: ">";
  font-size: 50px;
  color: #95c6f7;
}

.prev:after {
  content: "<";
  font-size: 50px;
  color: #95c6f7;
} */

.cardsize {
  height: 400px;
  width: 285px;
}

.photosize {
  height: 230px;
  width: 230px;
}
</style>
