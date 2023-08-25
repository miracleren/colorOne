import qs from 'qs'

//1. 先引入axios依赖包
import axios from 'axios'
import {getToken} from '@/utils/system/token'
import store from '@/frame/store'

axios.defaults.headers['Content-Type'] = 'application/json'

//2. axios创建对象
const service = axios.create({
    baseURL: '/api', //管理后台要使用的接口的基地址
    timeout: 8000, //超时时间
    paramsSerializer: (params) => qs.stringify(params, {indices: false, skipNulls: true})
})

//3. 定义前置拦截器，请求拦截器，请求发送出去之前触发的
service.interceptors.request.use((config) => {
    // 请求携带自定义token
    if (getToken()) {
        config.headers['Authorization'] = 'Bearer ' + getToken()
    }

    //config 接口请求的配置信息
    return config
}, (error) => {
    //报错的是时候抛出一个报错的信息
    return Promise.reject(error)
})

//4. 定义后置拦截器,响应拦截器, 服务器响应回来数据之前触发，
service.interceptors.response.use((response) => {
    //默认成功状态
    const code = response.data.code || 200
    if (code === 401) {
        //登录状态已过期，您可以继续留在该页面，或者重新登录
        //window.$message.error('401：' + response.data.msg)
        window.$dialog.warning({
            title: '警告',
            content: '当前登录状态已过期，是否重新登录？',
            positiveText: '确定',
            negativeText: '关闭',
            onPositiveClick: () => {
                store.dispatch('userLoginOut').then(() => {
                    location.reload()
                })
            }
        })
        return Promise.reject('error 401')
    } else if (code !== 200) {
        window.$message.error('异常：' + response.data.msg)
        return Promise.reject('error 200')
    } else
        //响应回来的数据操作
        return response.data
}, (error) => {
    //报错的是时候抛出一个报错的信息
    window.$message.error('interceptors：' + error)
    return Promise.reject(error)
})

//5. 抛出对象的信息
export default service
