<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>テーマ</h1>

<div>追加</div>

<div>編集</div>
//登録モーダル
    <p><input type="hidden" value="theme_id">
　　　<textarea>本文</textarea><br>
<input type="radio" name="choice" value="yes">日替わり指定<br>
<input type="radio" name="choice" value="no">通常指定<br>

　　　　　<input type="submit" value="登録"><br></p>


<c:forEach var="tm" items="${themeList}">
<option>${tm.theme}</option>

//編集モーダル

   
    <p>
    <input type="hidden" value="theme_id">
<input type="text">件名<br>
　　　<textarea>本文</textarea><br>
<input type="submit"value="編集"><br>
　　　　　</p>
</c:forEach>
</body>
</html>






