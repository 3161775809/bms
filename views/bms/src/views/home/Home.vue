<template>
  <div class="container">
    <el-carousel :interval="5000" height="350px" arrow="always">
      <el-carousel-item v-for="(item, index) in imgList" :key="index">
        <el-image :src="require(`../../assets/img/carousel/${index + 1}.jpeg`)" fit="fill"></el-image>
      </el-carousel-item>
    </el-carousel>
    <el-row class="btmBox" :gutter="0">
      <el-col :span="8" offset="">
        <user-info class="userInfo" />
      </el-col>
      <el-col  :span="15" :offset="1">
        <book-info class="bookInfo" />
      </el-col>
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
@media screen and (max-width: 1036px) {
  .btmBox {
    width: 100%;
    display: flex;
    flex-direction: column;
  }

  .bookInfo {
    margin-top: 50px;
    position: relative;
    right: 20px;
  }
  
}
.container {
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
