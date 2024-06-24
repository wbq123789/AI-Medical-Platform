import { createRouter, createWebHistory } from 'vue-router'
import { unauthorized } from "@/net";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'welcome',
            component: () => import('@/views/WelcomeView.vue'),
            children: [
                {
                    path: '',
                    name: 'welcome-login',
                    component: () => import('@/views/welcome/LoginPage.vue')
                }
            ]
        }, {
            path: '/index',
            name: 'index',
            component: () => import('@/views/IndexView.vue'),
            children:[
                {
                    path: '',
                    name: 'index-content',
                    component: () => import('@/views/content.vue'),
                    children:[
                        {
                            path: '',
                            name: 'doctorPanel',
                            component: () => import('@/components/DoctorPanel.vue'),
                        },
                        {
                            path: '/patients',
                            name: 'patients',
                            component: () => import('@/components/patients.vue'),
                            children:[
                                {
                                    path: '/analyse',
                                    name: 'analyse',
                                    component: () => import('@/components/DoctorPanel.vue')
                                }
                            ]
                        }
                    ]
                }
            ]
        }
    ]
})

router.beforeEach((to, from, next) => {
    const isUnauthorized = unauthorized()
    if(to.name.startsWith('welcome') && !isUnauthorized) {
        next('/index')
    } else if(to.fullPath.startsWith('/index') && isUnauthorized) {
        next('/')
    } else {
        next()
    }
})

export default router
