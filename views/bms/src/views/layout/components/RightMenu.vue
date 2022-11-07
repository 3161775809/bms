<template>
  <el-menu
    class="menu"
    :collapse="isCollapse"
    :unique-opened="true"
    text-color="white"
    background-color="#545C65"
    :default-active="this.$route.path"
    :router="true"
  >
    <div class="title">
      <!-- Logo -->
      <span v-if="!isCollapse" href="#"> 图书管理系统 </span>
      <img
        v-else
        src="@/assets/img/logo.png"
        style="width: 50px; height: 50px; margin-top: 10px"
      />
    </div>
    <div v-for="menu in menuList" :key="menu.path">
      <el-menu-item v-if="menu.children.length == 0" :index="menu.path">
        <i :class="menu.icon"></i>
        <span slot="title">{{ menu.title }}</span>
      </el-menu-item>
      <el-submenu v-else :key="menu.path" :index="menu.path">
        <template slot="title">
          <i :class="menu.icon"></i>
          <span v-if="!isCollapse">{{ menu.title }}</span>
        </template>
        <div v-for="child in menu.children" :key="child.path">
          <el-menu-item :index="child.path">
            <template slot="title">
              <i :class="child.icon"></i>
              <span>{{ child.title }}</span>
            </template>
          </el-menu-item>
        </div>
      </el-submenu>
    </div>
  </el-menu>
</template>

<script>
export default {
  name: "RightMenu",
  props: ["isCollapse"],
  data() {
    return {
      menuList: [
        {
          path: "/Home",
          title: "首页",
          icon: "el-icon-house",
          children: [],
        },
        {
          path: "/User",
          title: "用户",
          icon: "el-icon-user",
          children: [],
        },
        {
          path: "2",
          title: "图书管理",
          icon: "el-icon-menu",
          children: [
            {
              title: "图书列表",
              path: "/BookList",
              children: [],
              icon: "el-icon-menu",
            },
            {
              path: "/BookTypeList",
              title: "图书类别",
              icon: "el-icon-s-operation",
              children: [],
            },
            {
              path: "/Borrow",
              title: "借阅信息",
              icon: "el-icon-tickets",
              children: [],
            },
          ],
        },
        {
          path: "/Borrower",
          title: "借阅者管理",
          icon: "el-icon-menu",
          children: []
        },
      ],
    };
  },
  methods: {},
};
</script>

<style lang="scss" scoped>
.menu:not(.el-menu--collapse) {
  width: 160px;
  min-height: 100%;
  text-align: left;
}
.el-menu--collapse {
  min-height: 100%;
}

.title {
  line-height: 80px;
  text-align: center;
  color: white;
}
</style>
