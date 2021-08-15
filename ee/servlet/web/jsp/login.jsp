<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Servlet</title>
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/static/favicon.ico">
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <script src="https://unpkg.com/vue/dist/vue.js"></script>
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
</head>
<body>
<div id="app">
    <el-row>
        <el-col :span="12" :offset="6">
            <div>
                <img src="${pageContext.request.contextPath}/static/img/servlet.png" width="750" height="300"
                     alt="Servlet">
            </div>
            <el-divider></el-divider>
        </el-col>
        <el-col :span="12" :offset="6">
            <div>
                当前在线人数: ${applicationScope.ONLINE_COUNT}
            </div>
            <el-divider></el-divider>
        </el-col>
        <el-col :span="12" :offset="6">
            <div>
                <el-link :underline="false" type="primary" href="${pageContext.request.contextPath}/">首页</el-link>
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <el-alert
                            title="${requestScope.tip}"
                            type="error">
                    </el-alert>
                    <el-input placeholder="请输入账号" type="text" v-model="username" name="username">
                        <template slot="prepend">账号</template>
                    </el-input>
                    <el-input placeholder="请输入密码" type="password" v-model="password" name="password">
                        <template slot="prepend">密码</template>
                    </el-input>
                    <el-input placeholder="请输入验证码" type="text" v-model="code" name="code">
                        <template slot="prepend">
                            验证码
                        </template>
                        <el-image :src="'${pageContext.request.contextPath}/code/'+timestamp" fit="fill"
                                  slot="suffix"></el-image>
                        <el-button slot="append" @click="codeFlush">
                            刷新
                        </el-button>
                    </el-input>
                    <el-button type="primary" native-type="submit">登录</el-button>
                </form>
            </div>
        </el-col>
    </el-row>
</div>
<script src="${pageContext.request.contextPath}/static/js/login.js"></script>
</body>
</html>
