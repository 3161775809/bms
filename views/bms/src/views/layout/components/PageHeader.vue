<template>
  <nav class="navbar navbar-static-top">
    <el-row type="flex" justify="space-between">
      <el-col class="fold" :span="12" :offset="0">
        <i
          @click="setCollapse"
          :class="isCollapse ? 'el-icon-s-unfold' : 'el-icon-s-fold'"
        ></i>
        <span class="path">{{this.$route.name}}</span>
      </el-col>
      <el-col :span="12" :offset="0">
        <div class="right-menu">
          <el-dropdown class="avatar-container right-menu-item" trigger="click">
            <div class="avatar-wrapper">
              <img src="@/assets/img/user2-160x160.jpg" class="user-avatar" />
              <span class="username">{{ username }}</span>
            </div>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item divided>
                <span @click="loginout" style="display: block">退出</span>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div></el-col
      >
    </el-row>
  </nav>
</template>

<script>
export default {
  name: "PageHeader",
  data() {
    return {
      isCollapse: false,
    };
  },
  computed: {
    username() {
      return this.$store.getters.User.user;
    },
  },
  methods: {
    //退出登录
    loginout() {
      this.$store.dispatch("LoginOut");
      this.$router.push("/login");
    },
    //设置是否收缩菜单栏
    setCollapse() {
      this.isCollapse = !this.isCollapse;
      this.$emit("setcollapse", this.isCollapse);
    },
  },
};
</script>
<style lang="scss" scoped>
.navbar {
  line-height: 50px;
  position: relative;
  right: 1px;
  background: #333333 !important;
  .fold {
    font-size: 34px;
    color: white;
    text-align: left;
    position: relative;
    top: 13px;
    left:10px;
  }
  .username {
    color: white;
    margin-left: 10px;
    font-size: 14px;
  }

  .path {
    margin-left: 10px;
    color: white;
    font-size: 24px;
    position: relative;
    bottom: 4px;
  }
}
</style>
