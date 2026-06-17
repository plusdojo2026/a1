<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>管理者アンケート</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/admin_survey.css">
</head>
<body>
<header>
<%@ include file="/WEB-INF/jsp/common/admin_header.jsp" %>
</header>
<main>
<c:forEach var="survey" items="${survey_list}">
	<div>
	<div>日付：${survey.filledDate}</div>
	<div>${survey.subject}</div>
	<div>${survey.text}</div>
	</div>
</c:forEach>
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