<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header.jsp" %>
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
<%@include file="footer.jsp" %>
