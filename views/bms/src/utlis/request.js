import axios from 'axios'
import { Loading,Message,MessageBox } from 'element-ui'
import router from '@/router/index'
// 创建axios实例
const service = axios.create({
 baseURL: process.env.VUE_APP_API, // api的base_url
 timeout: 5000, // 请求超时时间
 headers: {
  'Content-Type': 'application/json'
 }
})

let loading = null;
//调用api时开启加载动画
function startLoading() {
 loading = Loading.service({
   lock: true,
   text: '拼命加载中....',
   background: 'rgba(0,0,0,0.7)'
 })
}
//关闭加载动画
function endLoading() {
 loading.close();
}


// 设置 post、get默认 Content-Type
// service.defaults.headers.post['Content-Type'] = 'application/json'
// service.defaults.headers.get['Content-Type'] = 'application/json'

// request拦截器
service.interceptors.request.use(config => {
 //加载动画
 startLoading();
//  // 如果当前已有token，则在请求头中加上
//  if (auth.getAuthorization()) {
//    //设置统一的请求头
//    config.headers['Authorization'] = auth.getAuthorization()// 让每个请求携带token--['X-Token']为自定义key 请根据实际情况自行修改
//  }
 return config
}, error => {
 // Do something with request error
 Message({
   message: error,
   type: 'error'
 })
 Promise.reject(error)
})

// respone拦截器
service.interceptors.response.use(
 /**
 * 下面的注释为通过response自定义code来标示请求状态，当code返回如下情况为权限有问题，登出并返回到登录页
 * 如通过xmlhttprequest 状态码标识 逻辑可写在下面error中
 */ 
 response => {
   const res = response.data;
   if (response.status != 200) {
      
     return Promise.reject('error');
   } 
     //结束加载动画
     endLoading();
     return response;
 },
 error => {
   endLoading();
   //根据status码来判断token是否过期，若过期则退出登录并返回登录页
   Message({
     message: error,
     type: 'error'
   })
   return Promise.reject(error)
 })

//加载到vue实例上，成为全局方法

export const request = service