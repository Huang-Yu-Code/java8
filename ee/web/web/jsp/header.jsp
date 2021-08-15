<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Web</title>
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/static/favicon.ico">
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <script src="https://unpkg.com/vue/dist/vue.js"></script>
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
</head>
<body>
<div id="app">
    <el-row>
        <el-col :span="12">
            <div>
                <img src="${pageContext.request.contextPath}/static/img/servlet.png" width="750" height="300"
                     alt="Servlet">
                <img src="${pageContext.request.contextPath}/static/img/shiro.png" width="239" height="85" alt="Shiro">
                <img src="${pageContext.request.contextPath}/static/img/mybatis.png" width="350" height="88"
                     alt="Mybatis">
            </div>
            <el-divider></el-divider>
        </el-col>
        <el-col :span="12">
            <div>
                当前在线人数: ${applicationScope.ONLINE_COUNT}
            </div>
            <el-divider></el-divider>