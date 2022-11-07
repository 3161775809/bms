
var auth = {
    getAuthorization() {
      return JSON.parse(localStorage.getItem('Authorization'));
    },
    setAuthorization(Authorization) {
      localStorage.setItem('Authorization', Authorization);
    }
  }
  export const Auth = auth
  export default {
    install: function (vue) {
      vue.prototype.$auth = auth
    }
}