<template>
  <div
    v-loading="loading"
    element-loading-text="登录中..."
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.6)"
    class="login-container"
  >
    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
      auto-complete="on"
      label-position="left"
    >
      <!-- 头像区域 -->
      <div v-if="TxStatus" class="avatar-box">
        <img src="@/assets/img/touxiang.jpeg" alt="" />
      </div>

      <div class="title-container">
        <h3 class="title">图书管理系统</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <i class="el-icon-user" />
        </span>
        <el-input
          ref="username"
          v-model="loginForm.username"
          placeholder="Username"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>

      <el-form-item prop="password">
        <div class="svg-container">
          <i class="el-icon-unlock"></i>
        </div>
        <el-input
          :key="passwordType"
          ref="password"
          v-model="loginForm.password"
          :type="passwordType"
          placeholder="Password"
          name="password"
          tabindex="2"
          auto-complete="on"
          @keyup.enter.native="handleLogin"
        />
        <span class="show-pwd" @click="showPwd">
          <i
            :class="
              passwordType === 'password' ? 'el-icon-lock' : 'el-icon-unlock'
            "
          ></i>
        </span>
      </el-form-item>
      <div>
        <el-button
          type="primary"
          style="width: 100%; margin-bottom: 20px"
          @click.native.prevent="handleLogin"
          >登录</el-button
        >
      </div>
    </el-form>
  </div>
</template>

<script>
//引入api
import { login } from "@/api/user/login";
export default {
  name: "Login",
  data() {
    return {
      // 头像状态
      TxStatus: true,
      loginForm: {
        username: "",
        password: "",
      },
      // 登录规则
      loginRules: {
        username: [
          { required: true, trigger: "blur", message: "用户名不能为空" },
        ],
        password: [
          { required: true, trigger: "blur", message: "密码不能为空" },
          {
            min: 6,
            max: 13,
            message: "密码长度在 6 到 13 个字符",
            trigger: "blur",
          },
        ],
      },
      loading: false,
      passwordType: "password",
      redirect: undefined,
    };
  },
  watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true,
    },
  },
  methods: {
    showPwd() {
      if (this.passwordType === "password") {
        this.passwordType = "";
      } else {
        this.passwordType = "password";
      }
      this.$nextTick(() => {
        this.$refs.password.focus();
      });
    },
    // 登录业务
    handleLogin() {
      this.$refs.loginForm.validate((valid) => {
        // 如果符合验证规则
        if (valid) {
          //登录操作
          this.$store
            .dispatch("Login", {
              user: this.loginForm.username,
              passwd: this.loginForm.password,
            }).then((res) => {
                this.$user.setUser(res.data);
                this.$message.success("登录成功");
                this.$auth.setAuthorization(true);
                this.$router.push("/Home");
            }).catch(err => {
              this.$message.error(err.msg);
            });

      }});
    },
    // 注册业务
    register() {
      this.$router.push({ name: "register" });
    },
  },
};
</script>
<style lang="scss">
$bg: #283443;
$light_gray: #fff;
$cursor: #fff;
@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;

.login-container {
  position: fixed;
  top: 0;
  min-height: 100%;
  width: 100%;
  overflow: hidden;
  background: url("https://api.dujin.org/pic/ghibli");
  background-size: 100% 100%;
  // 头像
  .avatar-box {
    margin: 0 auto;
    width: 120px;
    height: 120px;
    border-radius: 50%;
    border: 1px solid #409eff;
    box-shadow: 0 0 10px #409eff;
    position: relative;
    bottom: 20px;
    transition: all 0.4s linear;

    i {
      font-weight: bolder;
    }

    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
    }
  }

  .avatar-box:hover {
    transform: rotate(360deg);
  }

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 16px;
    text-align: center;
    color: #000;
    margin-bottom: 10px;
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .svg-container:hover {
    background: $bg;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 30px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: 500;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
</style>
