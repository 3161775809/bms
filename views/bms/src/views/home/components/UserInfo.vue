<template>
  <!-- Header content -->

  <el-col :span="6" :offset="0">
    <div class="userInfo circleBg">
      <el-row type="flex" :gutter="20">
        <el-col :span="6" :offset="1">
          <img src="@/assets/img/user2-160x160.jpg" />
        </el-col>
        <el-col style="position: relative" :span="18" :offset="0">
          <div class="info">
            <div class="name">{{ username }}</div>
            <div class="type">{{ type }}</div>
          </div>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <div class="areaInfo">
        <div class="date">
          <span>上次登录时间：</span>
          <span style="margin-left: 20px">2021-10-20</span>
        </div>
        <div class="area">
          <span>上次登录地点： </span>
          <span style="margin-left: 20px">上海</span>
        </div>
      </div>
    </div>
  </el-col>
</template>

<script>
export default {
  name: "UserInfo",
  data() {
    return {};
  },
  computed: {
    username() {
      return this.$store.getters.User.user;
    },
    type() {
      return this.$store.getters.User.isadmin ? "超级管理员" : "用户";
    },
  },
};
</script>

<style lang="scss" scoped>
.userInfo {
  min-width: 400px;
  min-height: 260px;
  height: 30vh;
  padding-top: 20px;
  box-sizing: border-box;

  img {
    height: 18vh;
    border-radius: 50%;
  }

  .info {
    display: flex;
    flex-direction: column;
    align-items: center;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);

    .name {
      font-size: 34px;
    }

    .type {
      color: #8492a6;
      font-size: 14px;
    }
  }

  .areaInfo {
    display: flex;
    flex-direction: column;
    font-size: 12px;
    color: #8492a6;
    justify-items: center;

    div {
      display: flex;
      justify-content: start;
    }

    .date {
      padding: 10px;
      position: relative;
    }

    .area {
      padding-left: 10px;
      padding-right: 10px;
    }
  }
}

.circleBg {
  border-radius: 8px;
  background: white;
}
</style>
