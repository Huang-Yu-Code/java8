new Vue({
    data() {
        return {
            username: null,
            password: null,
            re_password: null,
            code: null,
            timestamp: new Date().getTime(),
        }
    },
    methods: {
        codeFlush() {
            this.timestamp = new Date().getTime();
        }
    }
}).$mount('#app');