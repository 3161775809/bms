<template>
  <div class="hold-transition skin-purple sidebar-mini">
    <el-container>
      <el-aside width="200">
        <right-menu :isCollapse="isCollapse" style="overflow: hidden" />
      </el-aside>
      <el-container>
        <el-header class="main-header">
          <page-header @setcollapse="setcollapse" />
        </el-header>
        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
//将组件引入进来
import PageHeader from "./components/PageHeader.vue";
import RightMenu from "./components/RightMenu.vue";
export default {
  name: "layout",
  components: {
    RightMenu,
    PageHeader,
  },
  methods: {
    setcollapse(collapse) {
      this.isCollapse = collapse;
    },
  },
  created() {
    if (this.$user.getUser() != null) {
      this.$store.commit("setUser", this.$user.getUser());
    }
  },
  data() {
    return {
      isCollapse: false,
    };
  },
};
</script>
<style scoped>
.el-main {
  height: calc(100vh - 70px);
}
</style>
