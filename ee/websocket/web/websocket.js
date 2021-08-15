new Vue({
    el: '#app',
    data() {
        return {
            disabled: true,
            websocket: null,
            message: {
                username: null,
                content: null,
            },
            users: [],
            userKey: null,
            messages: [],
            num: 0,
            currentRow: null,
            select: 'username',
            messageKey: null,
            images: [
                'img.png',
                'img2.png',
                'img3.jpg',
            ]
        }
    },
    methods: {
        setUsername() {
            const first = ['暴走', '张飞', '绝情', '胆小', '爱哭', '执着', '极品', '勇敢'];
            const middle = ['de', '的', '灬', '·', '丶', '地'];
            const last = ['皇子', '西施', '公主', '王子', '荣耀', '英雄'];
            this.message.username = first[Math.floor(Math.random() * (first.length - 1))] +
                middle[Math.floor(Math.random() * (middle.length - 1))] +
                last[Math.floor(Math.random() * (last.length - 1))]
        },
        connect() {
            const username = this.message.username;
            if (username !== null && username !== '') {
                if (typeof (WebSocket) != "function") {
                    this.$notify.error({
                        title: '系统通知: ',
                        message: '不支持WebSocket',
                    });
                    this.websocket = new SockJS("http://192.168.0.100:8080/websocket");
                }
                else {
                    this.websocket = new WebSocket("ws://localhost:8080/websocket/websocket/" + username);
                }

                this.websocket.onopen = () => {
                    this.disabled = false;
                }

                this.websocket.onclose = () => {
                    this.disabled = true;
                    this.users = [];
                }

                this.websocket.onmessage = (evt) => {
                    this.onMessage(JSON.parse(evt.data));
                }
                this.websocket.onerror = () => {
                    this.$notify.error({
                        title: '系统通知: ',
                        message: '服务器繁忙',
                        position: 'bottom-left'
                    });
                };
            } else {
                this.$notify.error({
                    title: '系统通知: ',
                    message: '用户名不能为空!',
                });
            }
        },

        disconnect() {
            this.websocket.close();
        },

        sendMessage() {
            console.log(this.message);
            if (this.message.content === null || this.messages.content === '') {
                this.$notify.error({
                    title: '系统通知: ',
                    message: '内容不能为空!',
                });
            } else {
                this.websocket.send(JSON.stringify(this.message))
            }
        },

        onMessage(data) {
            switch (data.type) {
                case 1:
                    this.usersData(data);
                    break;
                case 2:
                    this.messagesData(data);
                    break;
                case 3:
                    this.messageData(data);
                    break;
                default:
                    this.notify(data);
                    break;
            }
        },

        notify(data) {
            this.$notify.info({
                title: '系统通知: ',
                message: data['data'],
            });
        },

        usersData(data) {
            this.users = data['data'];
        },

        messagesData(data) {
            console.log(data);
            this.messages = data['data'];
        },

        audioNotify(src) {
            let audioElement = document.createElement('audio');
            audioElement.src = src;
            audioElement.preload = 'auto';
            audioElement.onended = () => {
                this.remove();
            };
            audioElement.onerror = () => {
                this.remove();
            };
            audioElement.play().then();
        },

        messageData(data) {
            console.log(data);
            if (data['data']['username'] !== this.message.username) {
                this.audioNotify('message.mp3');
                this.num++;
            }
            this.messages.unshift(data['data']);
        },

        clearMessages() {
            this.messages = [];
        },

        tableRowClassName({row, rowIndex}) {
            if (row.username === this.message.username) {
                return 'success-row';
            }
            return '';
        },

        setCurrent() {
            this.$refs.singleTable.setCurrentRow(this.messages[this.num - 1]);
            this.num = 0;
        },

        handleCurrentChange(val) {
            this.currentRow = val;
        }
    }
})