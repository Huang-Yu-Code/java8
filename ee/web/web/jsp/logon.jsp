<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header.jsp" %>
            <div>
                <el-link :underline="false" type="primary" href="${pageContext.request.contextPath}/">首页</el-link>
                <form action="${pageContext.request.contextPath}/logon" method="post">
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
                    <el-input placeholder="请输入密码" type="password" v-model="re_password" name="re_password">
                        <template slot="prepend">重复密码</template>
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
                    <el-button type="primary" native-type="submit">注册</el-button>
                </form>
            </div>
        </el-col>
    </el-row>
</div>
<script src="${pageContext.request.contextPath}/static/js/logon.js"></script>
<%@include file="footer.jsp"%>
