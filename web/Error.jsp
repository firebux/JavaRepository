<%--
  Created by IntelliJ IDEA.
  User: foundwant
  Date: 2020/2/24
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>

</body>
<center>
    <h2>
        用户登录错误
    </h2>
    <div>
        <%=request.getAttribute("ErrorMsg")%>
    </div>

</center>
</html>
