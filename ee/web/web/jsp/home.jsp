<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header.jsp" %>
<div>
    <el-tabs v-model="tabName">
        <el-tab-pane label="用户主页" name="home">
            <el-link icon="el-icon-user-solid" class="username">用户:${sessionScope.username}</el-link>
            <el-divider></el-divider>
            <el-link :underline="false" type="primary" href="${pageContext.request.contextPath}/">首页</el-link>
            <el-link :underline="false" type="danger" href="${pageContext.request.contextPath}/logout">退出</el-link>
        </el-tab-pane>
        <el-tab-pane label="身份管理" name="role">
            <el-link :underline="false" type="primary" href="${pageContext.request.contextPath}/home/guest">普通用户
            </el-link>
            <el-link :underline="false" type="primary" href="${pageContext.request.contextPath}/home/admin">管理员
            </el-link>
        </el-tab-pane>
        <el-tab-pane label="Json响应" name="json">
            <el-link :underline="false" type="primary" href="${pageContext.request.contextPath}/home/json">json
            </el-link>
        </el-tab-pane>
        <el-tab-pane label="文件上传" name="fileUpload">
            <el-upload
                    class="upload-demo"
                    ref="upload"
                    :on-success="uploadSuccess"
                    :on-error="uploadError"
                    action="${pageContext.request.contextPath}/home/upload"
                    :auto-upload="false"
                    multiple>
                <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器
                </el-button>
                <div slot="tip" class="el-upload__tip">只能上传不超过500kb的文件</div>
            </el-upload>
        </el-tab-pane>
        <el-tab-pane label="文件下载" name="fileDownload">
            <el-link :underline="false" type="primary" href="${pageContext.request.contextPath}/home/download">下载
            </el-link>
        </el-tab-pane>
        <el-tab-pane label="发送邮件" name="sendMail">
            <form action="${pageContext.request.contextPath}/home/mail" method="post">
                <el-alert
                        title="${requestScope.tip}"
                        type="error">
                </el-alert>
                <el-input placeholder="请输入邮箱" type="text" v-model="mail" name="mail">
                    <template slot="prepend">邮箱</template>
                </el-input>
                <el-input placeholder="请输入密码" type="text" v-model="subject" name="subject">
                    <template slot="prepend">主题</template>
                </el-input>
                <el-input placeholder="请输入正文" type="text" v-model="content" name="content">
                    <template slot="prepend">
                        正文
                    </template>
                </el-input>
                <el-button type="primary" native-type="submit">发送</el-button>
            </form>
        </el-tab-pane>
        <el-tab-pane label="聊天大厅" name="customerService">
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <el-badge :value="num" :max="99" v-show="num!==0" class="item">
                        <el-button @click="setCurrent">新的消息</el-button>
                    </el-badge>
                    <el-button style="float: right; padding: 3px 0" type="text" @click="clearMessages">清空聊天记录
                    </el-button>
                    <el-input style="float: right; padding: 3px 0"
                              v-model="messageKey"
                              placeholder="输入关键字搜索">
                        <el-select v-model="searchType" slot="prepend" placeholder="请选择">
                            <el-option label="用户名" value="username"></el-option>
                            <el-option label="消息" value="content"></el-option>
                        </el-select>
                    </el-input>
                </div>
                <el-table
                        ref="singleTable"
                        :data="messages.filter(item=>!messageKey||(item[searchType].indexOf(messageKey))!=-1)"
                        height="350"
                        style="width: 100%"
                        highlight-current-row
                        :row-class-name="tableRowClassName"
                        :default-sort="{prop: 'timestamp', order: 'descending'}">
                    <el-table-column
                            label="时间">
                        <template slot-scope="scope">
                            {{timestampToTime(scope.row.timestamp)}}
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="username"
                            label="用户">
                    </el-table-column>
                    <el-table-column
                            prop="content"
                            label="消息">
                    </el-table-column>
                </el-table>
                <el-divider><i class="el-icon-mobile-phone"></i></el-divider>
                <div>
                    <el-input
                            type="textarea"
                            :autosize="{ minRows: 4, maxRows: 4}"
                            placeholder="请输入内容"
                            show-word-limit
                            maxlength="80"
                            resize="none"
                            v-model="postData.content"
                            :disabled="!disabled">
                    </el-input>

                    <div style="margin: 20px 0;"></div>

                    <el-button type="success" @click="sendMessage" :disabled="!disabled">发送</el-button>
                </div>
            </el-card>
        </el-tab-pane>
    </el-tabs>
</div>
</el-col>
</el-row>
</div>
<script>
    let websocket = null;
    const app = new Vue({
        data() {
            return {
                tabName: 'home',
                mail: null,
                subject: null,
                content: null,
                disabled: false,
                messages: [],
                num: 0,
                searchType: 'username',
                messageKey: null,
                postData: {
                    username: '${sessionScope.username}',
                    content: null,
                    timestamp: null,
                }
            }
        },
        mounted: function () {
            this.connect();
        },
        methods: {
            submitUpload() {
                this.$refs.upload.submit();
            },
            uploadSuccess(response, file, fileList) {
                this.$notify.success({
                    title: '系统通知: ',
                    message: '上传成功',
                });
            },
            uploadError(err, file, fileList) {
                this.$notify.error({
                    title: '系统通知: ',
                    message: err.message,
                });
            },
            connect() {
                websocket = new WebSocket("ws://localhost:8080${pageContext.request.contextPath}/websocket/${sessionScope.token}");
                websocket.onopen = (event) => {
                    this.disabled = true;
                };
                websocket.onclose = (event) => {
                    this.disabled = false;
                };
                websocket.onmessage = (event) => {
                    this.onMessage(event);
                };
                websocket.onerror = (event) => {
                };
            },
            tableRowClassName({row, rowIndex}) {
                if (row.username === this.postData.username) {
                    return 'success-row';
                }
                return '';
            },
            setCurrent() {
                this.$refs.singleTable.setCurrentRow(this.messages[this.num - 1]);
                this.num = 0;
            },
            clearMessages() {
                this.messages = [];
            },
            timestampToTime(cjsj) {
                let date = new Date(cjsj)
                let Y = date.getFullYear() + '-'
                let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
                let D = date.getDate() + ' '
                let h = date.getHours() + ':'
                let m = date.getMinutes() + ':'
                let s = date.getSeconds()
                return Y + M + D + h + m + s
            },
            onMessage(event) {
                let data = JSON.parse(event.data);
                if (data.username !== this.postData.username) {
                    this.$notify.success({
                        title: '系统通知: ',
                        message: '新的消息',
                    });
                    this.num++;
                }
                this.messages.unshift(data);
            },
            sendMessage() {
                this.postData.timestamp = new Date().getTime();
                websocket.send(JSON.stringify(this.postData));
            }
        }
    }).$mount('#app')
</script>
<style>
    .el-select .el-input {
        width: 100px;
    }
</style>
<%@include file="footer.jsp" %>
