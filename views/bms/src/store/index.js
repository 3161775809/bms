import Vue from "vue";
import Vuex from "vuex";

// 该指令必须在 store 创建之前执行
Vue.use(Vuex);
import { login } from "@/api/user/login";
import { Auth } from "@/utlis/auth";
import { User } from "@/utlis/user";
// Actions(行动): 处理交互行为
const actions = {
  // context: 简化版的 store；    value: 发送过来的数据
  changeDispatch(context, value) {
    // 将数据 commit 给 mutations
    // 设置 2 个实参: commit 中的方法名 & 发送的数据
  },
  //登录
  Login(context, value) {
    return new Promise((resolve, reject) => {
      login(value).then((res) => {
        if (res.data.code == "200") {
          context.commit("setUser", res.data.data);
          resolve(res.data);
        } else {
          reject(res.data);
        }
      });
    });
  },
  //退出登录
  LoginOut(context, value) {
    //设置store中的user为空
    context.commit("setUser",{});
    Auth.setAuthorization(false);
    User.setUser({});
  }
};

// Mutations(变化): 修改 state 中的数据
const mutations = {
  // state: 存储的数据；    value: 发送的数据
  changeCommit(state, value) {
    // 修改 state 中存储的数据
    // 修改后，页面会重新渲染
  },
  setUser(state, value) {
    state.user = value;
  },
};
//Getters(获得对应state的值): 获取state中的数据
const getters = {
  //state: 存储区的数据
  User(state) {
    return state.user;
  },
};

// State: 用于存储数据
const state = { user: {} };

// 创建并导出 store
export default new Vuex.Store({
  actions,
  mutations,
  state,
  getters,
});
