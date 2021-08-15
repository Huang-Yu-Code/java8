<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Shiro</title>
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/static/favicon.ico">
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <script src="https://unpkg.com/vue/dist/vue.js"></script>
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/index.css">
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
                <el-link icon="el-icon-user-solid" class="username">用户:${sessionScope.username}</el-link>
                <el-divider></el-divider>
                <el-link :underline="false" type="primary" href="${pageContext.request.contextPath}/home">用户主页</el-link>
                <el-link :underline="false" type="primary"
                         href="${pageContext.request.contextPath}/home/${sessionScope.role}/read">
                    权限:create
                </el-link>
                <el-link :underline="false" type="primary"
                         href="${pageContext.request.contextPath}/home/${sessionScope.role}/read">
                    权限:read
                </el-link>
                <el-link :underline="false" type="primary"
                         href="${pageContext.request.contextPath}/home/${sessionScope.role}/update">权限:update
                </el-link>
                <el-link :underline="false" type="primary"
                         href="${pageContext.request.contextPath}/home/${sessionScope.role}/delete">权限:delete
                </el-link>
            </div>
        </el-col>
    </el-row>
</div>
<script>
    new Vue({}).$mount('#app')
</script>
</body>
</html>
