<%--
<!DOCTYPE html>
<html>
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src = "http://code.jquery.com/jquery-Latest.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h2>Home</h2>
    <button type = "button" class = "btn btn-primary">login</button>
</div>

</body>
</html>--%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c"	uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>Insert title here</title>
</head>
<body>
<c:if test = '${empty sessionScope.id }'>
    <script>
        location.href = "loginForm.net";
    </script>
</c:if>

<h2>로그인 되었습니다.</h2><a href = "logout.net">로그아웃</a>
<hr>
<c:if test = "${sessionScope.id == 'admin' }">
    <c:out value = "관리자모드!"/><br>
    <a href = "list.net">회원명단</a><br>
</c:if>
</body>
</html>