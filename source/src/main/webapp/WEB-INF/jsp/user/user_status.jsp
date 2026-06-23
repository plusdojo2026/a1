<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ユーザーステータス</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/user_sutatus.css">
</head>
<body>
<header>
<%@ include file="/WEB-INF/jsp/common/user_header.jsp" %>
</header>
<main>
<h1>あなたの日々の満足度</h1>


<c:forEach var="uib" items="${weatherList}" >
	<h2>天気ごとの満足度</h2>
	
<c:out value="${uib.sunny}" /><br>
<c:out value="${uib.cloudy}" /><br>
<c:out value="${uib.rainy}" /><br>
<c:out value="${uib.snowy}" /><br>
<h3>気温ごとの満足度</h3>
<c:out value="${uib.cold}" /><br>
<c:out value="${uib.ideal}" /><br>
<c:out value="${uib.hot}" /><br>
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