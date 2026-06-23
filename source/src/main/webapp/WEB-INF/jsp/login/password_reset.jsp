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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login/password_reset.css">
</head>
<body>
<header>
<!--<%@ include file="/WEB-INF/jsp/common/user_header.jsp" %>-->
</header>
<main>
<h1>パスワード再設定</h1>
<form id="login_form" action="<c:url value="/login/password-reset" />" method="post" >
<div>
	<div>
	<input type="text" name="mail" placeholder="メールアドレス">
	<input type="text" name="name" placeholder="名前"><br>
	</div>
	<div>
	<input type="password" name="pass" placeholder="パスワード"><br>
	</div>
	<div>
	<input type="password" name="pass2" id="pass2" placeholder="パスワード（確認）">
	<div id="error_message"></div>
	</div>
	
	<input type="submit" name="update" value="再設定">
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
	/* HTML要素をオブジェクトとして取得する */
	const formObj = document.getElementById('login_form');
	const errorMessageObj = document.getElementById('error_message');

	formObj.addEventListener('submit', function(event) { 
	//毎回エラーメッセージををリセットする
	errorMessageObj.innerHTML = '';
	
	//メールアドレス空欄を確認
	if (formObj.mail.value === '') {
	    errorMessageObj.innerHTML += '※メールアドレスを入力してください。<br>';
	    event.preventDefault();
	
	}
	//名前空欄を確認
	if (formObj.name.value === '') {
		    errorMessageObj.innerHTML += '※名前を入力してください。<br>';
		    event.preventDefault();
	//パスワード空欄を確認
	}
	if (formObj.pass.value === '' || formObj.pass2.value === '') {
			    errorMessageObj.innerHTML += '※パスワードを入力してください。<br>';
			    event.preventDefault();
	//パスワード不一致を確認		    
	}if (formObj.pass.value !== formObj.pass2.value) {
			    errorMessageObj.innerHTML += '※パスワードが違います。<br>';
			    event.preventDefault();
	//すべての妨げがなかったらpass2を送れないようにする
	}if(!event.defaultPrevented){
		formObj.pass2.disabled = true;
	}
	
	});
			
</script>
</body>
</html>