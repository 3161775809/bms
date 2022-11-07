import Vue from "vue";
import Router from "vue-router";
Vue.use(Router);

const router = new Router({
  mode: "history", // 访问路径不带#号 (有两种模式 hash模式 与 history模式)
  linkActiveClass: "mylinkActiveClass", // 修改路由激活时添加的类名, 默认类名router-link-active
  routes: [
    {
      path: "/Login",
      name: "登录",
      component: () => import("@/views/rl/Login"),
    },
    {
      path: "/",
      name: "框架",
      component: () => import("@/views/layout/layout"),
      children: [
        {
          path: "/BookList",
          name: "图书列表",
          component: () => import("@/views/book/BookList"),
        },
        {
          path: "/BookTypeList",
          name: "图书类别",
          component: () => import("@/views/book/BookTypeList"),
        },
        {
          path: "/Borrow",
          name: "借阅信息",
          component: () => import("@/views/book/Borrow"),
        },
        {
          path: "/Home",
          name: "首页",
          component: () => import("@/views/home/Home"),
        },
        {
          path: "/User",
          name: "用户",
          component: () => import("@/views/user/User")
        },
        {
          path: "/Borrower",
          name: "借阅者管理",
          component: () => import("@/views/borrorwer/Borrower")
        }
      ],
    },
    {
      path: "*",
      name: "404",
      component: () => import("@/views/errPage/errPage"),
    },
  ],
});

router.beforeEach((to, from, next) => {
  // to and from are both route objects. must call `next`.

  if (
    // 检查用户是否已登录
    !JSON.parse(localStorage.getItem("Authorization")) &&
    // ❗️ 避免无限重定向
    to.name !== "登录"
  ) {
    localStorage.getItem("Authorization");
    // 将用户重定向到登录页面
    next({ path: "Login", name: "登录" });
  }
  next();
});

export default router;
