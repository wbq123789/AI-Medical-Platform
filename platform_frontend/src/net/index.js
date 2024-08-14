import axios from "axios";
import {ElMessage} from "element-plus";

const authItemName = "authorize"

const accessHeader = () => {
    return {
        'Authorization': `Bearer ${takeAccessToken()}`
    }
}

const defaultError = (error) => {
    console.error(error)
    ElMessage.error('发生了一些错误，请联系管理员')
}

const defaultFailure = (message, status, url) => {
    console.warn(`请求地址: ${url}, 状态码: ${status}, 错误信息: ${message}`)
    ElMessage.warning(message)
}

function takeAccessToken() {
    const str = localStorage.getItem(authItemName) || sessionStorage.getItem(authItemName);
    if(!str) return null
    const authObj = JSON.parse(str)
    if(new Date(authObj.expire) <= new Date()) {
        deleteAccessToken()
        ElMessage.warning("登录状态已过期，请重新登录！")
        return null
    }
    return authObj.token
}

function storeAccessToken(remember, token, expire){
    const authObj = {
        token: token,
        expire: expire
    }
    const str = JSON.stringify(authObj)
    if(remember)
        localStorage.setItem(authItemName, str)
    else
        sessionStorage.setItem(authItemName, str)
}

function deleteAccessToken() {
    localStorage.removeItem(authItemName)
    sessionStorage.removeItem(authItemName)
}

function internalPost(url, data, headers, success, failure, error = defaultError){
    axios.post(url, data, { headers: headers }).then(({data}) => {
        if(data.code === 200)
            success(data.data)
        else
            failure(data.message, data.code, url)
    }).catch(err => error(err))
}

function internalGet(url, headers, success, failure, error = defaultError){
    axios.get(url, { headers: headers }).then(({data}) => {
        if(data.code === 200)
            success(data.data)
        else
            failure(data.message, data.code, url)
    }).catch(err => error(err))
}

function login(username, password, remember, success, failure = defaultFailure){
    internalPost('/api/auth/login', {
        username: username,
        password: password
    }, {
        'Content-Type': 'application/x-www-form-urlencoded'
    }, (data) => {
        storeAccessToken(remember, data.token, data.expire)
        ElMessage.success(`登录成功，欢迎 ${data.username} 来到我们的系统`)
        success(data)
    }, failure)
}

function post(url, data, success, failure = defaultFailure) {
    internalPost(url, data, accessHeader() , success, failure)
}

function logout(success, failure = defaultFailure){
    get('/api/auth/logout', () => {
        deleteAccessToken()
        ElMessage.success(`退出登录成功，欢迎您再次使用`)
        success()
    }, failure)
}

function get(url, success, failure = defaultFailure) {
    internalGet(url, accessHeader(), success, failure)
}

function unauthorized() {
    return !takeAccessToken()
}

function getList(type,success, failure = defaultFailure){
    get(`/api/fisco/getData?AgencyId=${type}`, (data)=>{
        success(data)
    })
}

function getModel(type1,type2,type3,type4){
    downloadModel(type1,type2,type3,type4)
}

function downloadModel(GroupId,AgencyId,File_id,Round) {
    // 构造请求的URL
    const url = new URL('http://localhost:8080/api/fisco/Model');
    // 设置请求参数
    url.searchParams.append('GroupId', GroupId);
    url.searchParams.append('AgencyId', AgencyId);
    url.searchParams.append('File_id', File_id);
    url.searchParams.append('Round', Round);

    // 发起fetch请求
    fetch(url)
        .then(response => {
            // 确认请求成功
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.blob(); // 读取二进制数据
        })
        .then(blob => {
            // 使用Blob构造可下载的URL
            const url = window.URL.createObjectURL(blob);
            // 创建一个<a>元素用于触发下载
            const a = document.createElement('a');
            a.style.display = 'none'; // 隐藏元素
            a.href = url;  // 设置下载的URL
            a.download = File_id; // 设置下载时的文件名
            document.body.appendChild(a);  // 添加元素到页面中
            a.click();  // 触发下载
            // 清理：下载完成后，释放URL并移除<a>元素
            window.URL.revokeObjectURL(url);
            document.body.removeChild(a);
        })
        .catch(error => {
            console.error('Error during fetch:', error);
        });
}

function getBlockAndTransactionNumber(type,success, failure = defaultFailure){
    get(`/api/fisco/getBlockAndTransactionNumber?GroupId=${type}`, (data)=>{
        success(data)
    })
}

function getLength(type,success, failure = defaultFailure){
    get(`/api/fisco/getLength?AgencyId=${type}`, (data)=>{
        success(data)
    })
}

export {post,get,login,logout,internalGet,internalPost,unauthorized,getList,getModel,getBlockAndTransactionNumber,getLength}
