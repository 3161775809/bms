
var user = {
    getUser() {
      return JSON.parse(localStorage.getItem('User'));
    },
    setUser(data) {
      localStorage.setItem('User', JSON.stringify(data))
    }
  }

  export const User = user
  export default {
    install: function (vue) {
      vue.prototype.$user = user
    }
}