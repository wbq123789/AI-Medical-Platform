import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from "axios";
import {createPinia} from "pinia";
import 'element-plus/theme-chalk/dark/css-vars.css'
//axios.defaults.baseURL = 'http://192.168.0.108:8080'
axios.defaults.baseURL = 'http://localhost:19999'
const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')
