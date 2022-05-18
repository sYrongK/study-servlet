<%--
  Created by IntelliJ IDEA.
  User: somac
  Date: 2022/05/18
  Time: 10:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>개발 중</title>
</head>
<body>
<div>
    <div><h1>네이버 로그인 개발 중</h1></div>
    <%--<a href="${pageContext.request.contextPath}/login.sso">
        <img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/>
    </a>--%>
    <%
        String clientId = "L_6CaDTEasPFMDKOIrvu";//애플리케이션 클라이언트 아이디값";
        String redirectURI = URLEncoder.encode("http://localhost:8080/callback.sso", "UTF-8");
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();
        String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
        apiURL += "&client_id=" + clientId;
        apiURL += "&redirect_uri=" + redirectURI;
        apiURL += "&state=" + state;
        session.setAttribute("state", state);
    %>
    <a href="<%=apiURL%>"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>
</div>
</body>
</html>
