<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップ画面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/top.css">
</head>
<body>
	<c:if test="${ご褒美デイなら}">
		
	</c:if>
	<h1>紫陽花Dialy</h1>
	<div>
		今日のテーマ：<c:out value="${サーブレットで設定した名前}"></c:out>
	</div>
	<div>
		今日の天気<c:out value="${ }"></c:out>
	</div>
	<div>
		今日の気温<c:out value="${ }"></c:out>
	</div>
	<div>
		連続記録<c:out value="${ }"></c:out>
	</div>
	<div>
		一年前の今日の日記<c:out value="${ }"></c:out>
		<a href="date_details.jsp">続きを読む>></a>
	</div>
	<div>
		お知らせ<c:out value="${ }"></c:out>
		<a href="user_news.jsp">お知らせ一覧>></a>
	</div>
</body>
</html>