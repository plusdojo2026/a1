<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>日記・予定詳細ページ</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <h1>日記・予定詳細ページ</h1>
    <!-- 予定タブ -->
    <h2>予定</h2>
    <div>
        <h3>[日付]の予定</h3>
        <button>追加</button><br>
    <c:forEach var="" items="${}">
        ${予定}
        <button>編集</button>
        <button>削除</button><br>
    </c:forEach>
    </div>
    <!-- 日記閲覧タブ -->
    <h2>日記閲覧</h2>
    <div>
        <h3>[日付]の日記</h3>

        <!--該当日記データが無い場合-->
        <c:if test="${empty diary}">
            <p>日記は登録されていません。</p>
        </c:if>

        <!--該当する日記データがある場合-->
        <div>
            テーマ:${diary.theme}
            <img src="${diary.stamp}">
            天気:${diary.weather} ${diary.temp_max}℃/${dairy.temp_min}℃
        </div>

        <div>[満足度]</div>

        <div>
        	<img src="${diary.image}">
        </div>

        <div>${diary.diary}</div>
        
    </div>
</body>
</html>