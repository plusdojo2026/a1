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
	<h1>アンケート</h1>
	<p>お意見・ご要望をお寄せください。</p>
	<div>
		<form action="/a1/user/survey" method="post" id="survey-form">
			<p>
				<label for="subject">件名</label>
				<input type="text" name="subject" id="subject" autofocus>
			</p>
			<p>
				<label for="text">本文</label>
				<textarea name="text" id="text"></textarea>
			</p>
			<p id="alt-message">${message}</p>
			<p>
				<input type="submit" value="送信">
			</p>
		</form>
	</div>
</main>
<footer>
<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</footer>
</body>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script>
'user strict';
document.getElementById('survey-form').onsubmit = function(event) {
	let subject = document.getElementById('subject').value;
	let text = document.getElementById('text').value;
	let altMessage = document.getElementById('alt-message');

	if (subject === '' || text === '') {
		event.preventDefault();
		if (subject === '' && text === '') {
			altMessage.textContent = '件名、本文を入力してください';
		} else if (subject === '') {
			altMessage.textContent = '件名を入力してください';
		} else if (text === '') {
			altMessage.textContent = '本文を入力してください';
		} else {
			altMessage.textContent = '';
		}
		
		
	}
}

</script>
</html>