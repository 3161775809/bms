<template>
  <div class="container">
    <el-carousel :interval="5000" height="650px" arrow="always">
      <el-carousel-item v-for="(item, index) in imgList" :key="index">
        <el-image :src="require(`../../assets/img/carousel/${index + 1}.jpeg`)" fit="fill"></el-image>
      </el-carousel-item>
    </el-carousel>
    <el-row class="btmBox" :gutter="0">
      <el-col :span="6" :offset="0">
        <user-info />
      </el-col>
      <el-col :span="18">
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
  data() {
    return {
      imgList: [
        { url: '@/assets/img/carousel/1.jpeg' },
        { url: '@/assets/img/carousel/2.jpeg' },
        { url: '@/assets/img/carousel/3.jpeg' },
        { url: '@/assets/img/carousel/4.jpeg' },
        { url: '@/assets/img/carousel/5.jpeg' }
      ]
    }
  }
};
</script>

<style lang="scss" scoped>
.container {
  .bookInfo {
    margin-top: 20px;
    min-width: 400px;
  }

  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 600px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }

  .btmBox {
    margin-top: 80px;
  }
}
</style>
