<template>
  <div class="container" align="center">
    <div class="col-lg-8" align="center">
      <div class="form-group" align="center">
        <h1>{{ title }}</h1>
      </div>
      <div class="form-group" style="float:left">
        <label>{{ writer }}</label>
      </div>
      <div class="form-group" style="float:right">
        <label>{{ board_dt }} </label>
      </div>
    
      <div class="form-group" align="left">
        <br>
        <br>
        <br>
        <p>{{ content }}</p>
      </div>
    </div>
  </div>

</template>

<script>

export default {
  name: "boarddetail",
  props: {
    board_id: { type: Number },
    title: { type: String },
    writer: { type: String },
    board_dt: { type: String },
    content: { type: String }
  }
  
};
</script>
<style>
.button {
  margin: 0 2pt;
}
</style>
