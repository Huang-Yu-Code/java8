<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header.jsp" %>
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
<%@include file="footer.jsp"%>
