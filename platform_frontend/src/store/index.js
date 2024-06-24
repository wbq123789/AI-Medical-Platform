import {defineStore} from "pinia";

export const useStore = defineStore('general', {
    state:()=>{
        return {
            user:{
                id: -1,
                username: '',
                role: ''
            }
        }
    }
})