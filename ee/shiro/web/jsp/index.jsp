<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Shiro</title>
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
                <img src="${pageContext.request.contextPath}/static/img/servlet.png" width="750" height="300" alt="Servlet">
                <img src="${pageContext.request.contextPath}/static/img/shiro.png" width="239" height="85" alt="Shiro">
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
                <el-link :underline="false" type="primary" href="${pageContext.request.contextPath}/logon">注册</el-link>
                <el-link :underline="false" type="primary" href="${pageContext.request.contextPath}/login">登录</el-link>
                <el-link :underline="false" type="success" href="${pageContext.request.contextPath}/home">用户主页</el-link>
            </div>
        </el-col>
    </el-row>
</div>
<script>
    new Vue({}).$mount('#app')
</script>
</body>
</html>
