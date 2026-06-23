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
<!--<%@ include file="/WEB-INF/jsp/common/user_header.jsp" %>-->
</header>
<main class="none">
<div class="login">
<h1>ログイン</h1>
<form action="/a1/login" method="post" >
<div>
	<div>
	メールアドレス<br>
	<input type="text" name="mail" class="form"><br>
	</div>
	<div>
	パスワード<br>
	<input type="password" name="pass" class="form"><br>
	</div>
	<div>
	<input type="submit" name="login" value="ログイン" class="login-button">
	</div>
</div>
 <div class="e-msg">${msg}</div>
</form>
<div>
<span></span>
<div>
    <a href="/a1/login/regist">会員登録はこちら</a><br>
    <a href="/a1/login/password-reset">パスワードを忘れた方はこちら</a><br>
</div>
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