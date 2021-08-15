<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header.jsp" %>
            <div>
                <el-link icon="el-icon-user-solid" class="username">用户:${sessionScope.username}</el-link>
                <el-divider></el-divider>
                <el-link :underline="false" type="primary" href="${pageContext.request.contextPath}/home">用户主页</el-link>
            </div>
        </el-col>
    </el-row>
</div>
<script>
    new Vue({}).$mount('#app')
</script>
<%@include file="footer.jsp"%>
