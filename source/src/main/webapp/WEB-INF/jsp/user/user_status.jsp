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
<main class="stp">
<h1>あなたの日々の満足度</h1>
<c:forEach var="uib" items="${weatherList}" >
<c:if test="${uib.sunny !=0 || uib.cloudy !=0 || uib.rainy !=0 || uib.snowy !=0}">
<h2>天気ごとの満足度</h2>

<c:if test="${uib.sunny !=0 }">
晴れの日の満足度平均:<c:out value="${uib.sunny}" /><br>
</c:if>
<c:if test="${uib.cloudy !=0 }">
曇りの日の満足度平均:<c:out value="${uib.cloudy}" /><br>
</c:if>
<c:if test="${uib.rainy !=0 }">
雨の日の満足度平均:<c:out value="${uib.rainy}" /><br>
</c:if>
<c:if test="${uib.snowy !=0 }">
雪の日の満足度平均:<c:out value="${uib.snowy}" /><br>
</c:if>
</c:if>
</c:forEach>

<c:forEach var="uib" items="${templist}" >
<c:if test="${uib.cold !=0 || uib.ideal !=0 || uib.hot !=0 }">
<h3>気温ごとの満足度</h3>
<c:if test="${uib.cold !=0 }">
寒い日の満足度平均: <c:out value="${uib.cold}" /><br>
</c:if>
<c:if test="${uib.ideal !=0 }">
普通の日の満足度平均: <c:out value="${uib.ideal}" /><br>
</c:if>
<c:if test="${uib.hot !=0 }">
暑い日の満足度平均：<c:out value="${uib.hot}" /><br>
</c:if>    
</c:if> 
</c:forEach>

<c:if test="${empty weatherList && empty templist}">
データが存在しません。
</c:if>

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