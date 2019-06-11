import config from '@/common/config'
import axios from 'axios'
import ElementUI from 'element-ui'

let ajax = {}

let axiosInstance = axios.create({
  baseURL: config.backEndUrl,
  timeout: 30000,
  withCredentials: true
})

function innerMessage (type, message) {
  ElementUI.Message({
    showClose: true,
    duration: 2000,
    message: message,
    type: type
  })
}

axiosInstance.interceptors.response.use(response => {
  let code = response.data.code
  let message = response.data.message
  let data = response.data.data

  if (code === -1) {
    innerMessage('info', '需要登录')
    setTimeout(() => window.history.go(-1), 250)
    return Promise.reject(new Error('ERROR_NO_LOGIN'))
  } else if (code === 1) {
    innerMessage('error', '错误: ' + message)
    return Promise.reject(new Error('ERROR_FAIL'))
  } else if (code === 2) {
    innerMessage('error', '没有权限')
    return Promise.reject(new Error('ERROR_NO_PERMISSION'))
  }

  return data
}, error => {
  if (typeof (error.response) === 'undefined') {
    innerMessage('error', '与后端服务器通信失败')
  } else if (error.response.status >= 400) {
    innerMessage('error', 'HTTP错误: ' + error.response.status)
  }
  return Promise.reject(error)
})

const dataHandle = response => {
  return response
}

ajax.innerGet = function () {
  return axiosInstance.get.apply(this, arguments).then(dataHandle)
}

ajax.innerPost = function () {
  return axiosInstance.post.apply(this, arguments).then(dataHandle)
}

ajax.get = function (url) {
  return ajax.innerGet(url)
}

ajax.get = function (url, params) {
  return ajax.innerGet(url, {
    params: params
  })
}

ajax.post = function (url, data) {
  return ajax.innerPost(url, data, {
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export default ajax
