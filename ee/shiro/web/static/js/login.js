new Vue({
    data() {
        return {
            username: '',
            password: '',
            code: '',
            timestamp: new Date().getTime(),
        }
    },
    methods: {
        codeFlush() {
            this.timestamp = new Date().getTime();
        }
    }
}).$mount('#app');