<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>アンケート</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/user_survey.css">
</head>
<body>
<header>
<%@ include file="/WEB-INF/jsp/common/user_header.jsp" %>
</header>
<main>
<form action="a1/login" method="post" >
<div>
	<div>
	メールアドレス<input type="text" name="mail"><br>
	</div>
	<div>
	名前<input type="text" name="name"><br>
	</div>
	<div>
	パスワード<input type="password" name="pass"><br>
	</div>
	
	<input type="submit" name="login" value="ログイン">
</div>
 <div>${msg}</div>
 </form>
</main>
<footer>
<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</footer>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script>
'use strict';
// ここから個別処理
</script>
</body>
</html>