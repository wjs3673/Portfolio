<template>
  <div class="col-md-12">
    <div class="card card-container">
      <img
        id="profile-img"
        src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
        class="profile-img-card"
      />
      <form name="form" @submit.prevent>
        <div>
          <div class="form-group">
            <label for="photo">Photo</label>
            <input
              type="file"
              @change="onFileSelected"
              ref="files"
              id="files"
              name="upfile"
              multiple="multiple"
            />
          </div>
          <div class="form-group">
            <label for="name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              placeholder="Enter Name"
              v-model="gone.gone_nm"
            />
          </div>
          <div class="form-group">
            <label for="birth">Birth</label>
            <input
              type="date"
              class="form-control"
              name="birth"
              v-model="gone.birth_dt"
            />
          </div>
          <div class="form-group">
            <label for="death">Death</label>
            <input
              type="date"
              class="form-control"
              name="death"
              v-model="gone.death_dt"
            />
          </div>

          <div class="form-group">
            <label for="funeral">Funeal</label>
            <b-form inline>
              <label class="sr-only" for="inline-form-input-name">Name</label>
              <b-form-input
                id="inline-form-input-name"
                class="mb-2 mr-sm-2 mb-sm-0"
                placeholder="Search"
                v-model="region"
              ></b-form-input>

              <b-form v-b-modal.modal-1 @click="getfuneral">Search</b-form>
            </b-form>

            <!-- 모달 -->
            <b-modal ref="my-modal" id="modal-1" title="Search Result">
              <div v-if="lists.length != 0">
                <table class="list_table">
                  <colgroup>
                    <col width="10%" />
                    <col width="40%" />
                    <col width="50%" />
                  </colgroup>
                  <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                  </tr>
                  <tr
                    v-for="list in lists"
                    class="nicecolor"
                    :key="list.search_id"
                  >
                    <td v-html="list.search_id"></td>
                    <td v-html="list.search_nm" @click="regist(list)"></td>
                    <td v-html="list.search_addr"></td>
                  </tr>
                </table>
              </div>
              <div v-else>
                <table class="table table-active">
                  <tbody>
                    <tr class="table-info" align="center">
                      <td>작성된 글이 없습니다.</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </b-modal>
          </div>
          <!-- <div class="form-group">
            <label for="address">Address</label>
            <input
              type="text"
              class="form-control"
              name="address"
              :placeholder="funeral.funeral_adr"
              v-model="funeral.funeral_adr"
            />
          </div> -->

          <div class="form-group">
            <label for="start">시작일</label>
            <input
              type="date"
              class="form-control"
              name="start"
              v-model="procedure.start_dt"
            />
          </div>
          <div class="form-group">
            <label for="end">종료일</label>
            <input
              type="date"
              class="form-control"
              name="end"
              v-model="procedure.end_dt"
            />
          </div>
          <div class="form-group">
            <label for="enter">입관일</label>
            <input
              type="date"
              class="form-control"
              name="enter"
              v-model="procedure.enter_dt"
            />
          </div>
          <div class="form-group">
            <label for="leave">발인일</label>
            <input
              type="date"
              class="form-control"
              name="leave"
              v-model="procedure.leave_dt"
            />
          </div>
          <div class="form-group">
            <label for="kind">Kind</label>
            <select name="kind" v-model="procedure.kind" data-title="선택">
              <option value="0">선택</option>
              <option value="1">2일장</option>
              <option value="2">3일장</option>
              <option value="3">기독교</option>
              <option value="4">불교</option>
              <option value="5">천주교</option>
            </select>
          </div>
          <div class="form-group">
            <label for="open">Open</label>
            <select name="open" v-model="procedure.open" data-title="선택">
              <option value="0">비공개</option>
              <option value="1">공개</option>
            </select>
          </div>
          <div class="form-group">
            <label for="bank">Bank</label>
            <input
              type="text"
              class="form-control"
              name="bank"
              placeholder="은행명을 입력해주세요."
              v-model="condelance.bank"
            />
          </div>
          <div class="form-group">
            <label for="holder">Holder</label>
            <input
              type="text"
              class="form-control"
              name="holder"
              placeholder="예금주를 입력해주세요."
              v-model="condelance.holder"
            />
          </div>
          <div class="form-group">
            <label for="account">Account</label>
            <input
              type="text"
              class="form-control"
              name="account"
              placeholder="계좌번호를 입력해주세요. (-제외)"
              v-model="condelance.account"
            />
          </div>
          <div class="form-group">
            <button class="btn btn-primary btn-block" @click="onUpload">
              Application
            </button>
          </div>
          <div class="form-group">
            <button class="btn btn-primary btn-block" @click="cancle">
              Cancle
            </button>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
import ListRow from "@/components/board/FuneralRow.vue";
export default {
  name: "appication",
  components: {
    ListRow
  },
  data: function() {
    return {
      gone: {
        user_id: "",
        gone_nm: "",
        birth_dt: "",
        death_dt: ""
      },
      funeral: {
        funeral_nm: "",
        funeral_adr: ""
      },
      procedure: {
        enter_dt: "",
        leave_dt: "",
        start_dt: "",
        end_dt: "",
        kind: 0,
        open: 0
      },
      condelance: {
        account: "",
        holder: "",
        bank: ""
      },
      lists: {
        search_id: "",
        search_nm: "",
        search_addr: ""
      },
      files: [],
      family: {
        family_nm: "",
        relation: "",
        phone: ""
      },
      region: ""
    };
  },
  methods: {
    regist: function(data) {
      console.log(data);
      this.$refs["my-modal"].hide();
      this.funeral.funeral_nm = data.search_nm;
      this.funeral.funeral_adr = data.search_addr;
      console.log(this.funeral.funeral_nm);
    },
    makeauth: function() {
      let user = this.user;
      axios
        .post(`${SERVER_URL}/user/checkmail`, user)
        .then(response => {
          console.log(response.data);
          this.istrue = response.data;
        })
        .catch(({ message }) => {
          this.msg = message;
        });
    },
    getfuneral: function() {
      axios
        .get(`${SERVER_URL}/appli/getfuneral`, {
          params: {
            region: this.region
          }
        })
        .then(response => {
          console.log("성공");
          this.lists = response.data.funeral_list;
          console.log(this.lists);
        })
        .catch(({ message }) => {
          this.msg = message;
        });
    },
    onFileSelected() {
      this.files = this.$refs.files.files;
      console.log(this.files);
    },
    cancle() {
      this.$router.push("/");
    },
    onUpload() {
      console.log("확인하기");
      console.log(this.gone.birth_dt);
      console.log(this.procedure.kind);
      const formData = new FormData();
      for (let i = 0; i < this.files.length; i++) {
        formData.append("pfile", this.files[i]);
      }
      formData.append(
        "g_dto",
        new Blob([JSON.stringify(this.gone)], { type: "application/json" })
      );
      formData.append(
        "fu_dto",
        new Blob([JSON.stringify(this.funeral)], { type: "application/json" })
      );
      formData.append(
        "p_dto",
        new Blob([JSON.stringify(this.procedure)], { type: "application/json" })
      );
      formData.append(
        "c_dto",
        new Blob([JSON.stringify(this.condelance)], {
          type: "application/json"
        })
      );
      axios
        .post(`${SERVER_URL}/appli/regist`, formData, {
          headers: {
            "Content-Type": "multipart/form-data"
          }
        })
        .then(response => {
          alert(response.data.url);
          console.log("SUCCESS!!");
          this.$router.push("/main");
        })
        .catch(function() {
          console.log("FAILURE!!");
        });
    }
  },
  created() {
    this.gone.user_id = this.$store.getters["getUserid"];
  }
};
</script>

<style scoped>
label {
  display: block;
  margin-top: 10px;
}

.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}

.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}

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
</style>
