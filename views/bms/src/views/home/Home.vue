<template>
  <div class="container">
    <el-row :gutter="0">
      <el-col :span="6" :offset="0">
        <user-info />
      </el-col>
      <el-col :span="15" :offset="3">
        <book-info />
      </el-col>
    </el-row>
    <el-row :gutter="0">
      <el-col class="bookInfo" :span="12" :offset="0"> </el-col>
      <el-col :span="12" :offset="0"></el-col>
    </el-row>
  </div>
</template>

<script>
import UserInfo from "@/views/home/components/UserInfo";
import BookInfo from "@/views/home/components/BookInfo";
export default {
  name: "Home",
  components: {
    UserInfo,
    BookInfo,
  },
};
</script>

<style lang="scss" scoped>
.container {
  .bookInfo {
    margin-top: 20px;
    min-width: 400px;
  }

}
</style>
