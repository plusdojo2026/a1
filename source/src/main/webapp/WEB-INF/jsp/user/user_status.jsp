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



<h2>天気ごとの満足度</h2>
<c:forEach var="uib" items="${weatherList}" >
<c:if test="${not empty uib.sunny}">
晴れの日の満足度平均:<c:out value="${uib.sunny}" /><br>
</c:if>
<c:if test="${not empty uib.cloudy}">
曇りの日の満足度平均:<c:out value="${uib.cloudy}" /><br>
</c:if>
<c:if test="${not empty uib.rainy}">
雨の日の満足度平均:<c:out value="${uib.rainy}" /><br>
</c:if>
<c:if test="${not empty uib.snowy}">
雪の日の満足度平均:<c:out value="${uib.snowy}" /><br>
</c:if>
</c:forEach>

<h3>気温ごとの満足度</h3>
<c:forEach var="uib" items="${templist}" >
    寒い日の満足度平均: <c:out value="${uib.cold}" /><br>
    普通の日の満足度平均: <c:out value="${uib.ideal}" /><br>
    暑い日の満足度平均：<c:out value="${uib.hot}" /><br>
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