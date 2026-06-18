<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ログイン</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login/login.css">
</head>
<body>
<header>
<%@ include file="/WEB-INF/jsp/common/user_header.jsp" %>
</header>
<main>
<form action="/a1/login" method="post" >
<div>
	<div>
	メールアドレス<input type="text" name="mail"><br>
	</div>
	<div>
	パスワード<input type="password" name="pass"><br>
	</div>
	
	<input type="submit" name="login" value="ログイン">
</div>
 <div>${msg}</div>
</form>
<div>
	<div>
    <a href="AccountRegistServlet?action=regist_screen">会員登録はこちら</a><br>
    <a href="PasswordResetServlet?action=reset_screen">パスワードを忘れた方はこちら</a><br>
  </div>
	</div>
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