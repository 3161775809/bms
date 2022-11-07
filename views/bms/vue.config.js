const { defineConfig } = require("@vue/cli-service");
const path = require("path");
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      [process.env.VUE_APP_API]: {
        // 真实的后端服务器地址
        target: "http://127.0.0.1:8082",
        ws: true,
        // 控制请求头中的host
        changeOrigin: true,
        // 将代理前缀名称替换为后端的真实前缀名称
        pathRewrite: { "/dev-api": "" },
      },
    },
  }
});
