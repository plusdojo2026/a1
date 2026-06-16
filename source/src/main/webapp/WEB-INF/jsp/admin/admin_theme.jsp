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

<button id="openModal">追加</button>
//追加モーダル
<div id="modal" class="modal">
  <div class="modal-content">
    <span id="closeModal" class="close">&times;</span>

    <p><input type="hidden" value="theme_id">
　　　　<input type="text">件名<br>
　　　<textarea>本文</textarea><br>
　　　　　<input type="submit" value="登録"><br></p>
  </div>
</div>

<c:forEach var="tm" items="${themeList}">
<option>${tm.theme}</option>

//編集モーダル
<div id="modal" class="modal">
  <div class="modal-content">
    <span id="closeModal" class="close">&times;</span>
   
    <p>
    <input type="hidden" value="theme_id">
<input type="text">件名<br>
　　　<textarea>本文</textarea><br>
<input type="submit"value="編集"><br>
　　　　　</p>
</div>
</div>
</c:forEach>
</body>
</html>






