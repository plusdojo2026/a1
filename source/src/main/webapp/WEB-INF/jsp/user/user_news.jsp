<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>お知らせ</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/user_news.css">
</head>
<body>
<header>
<%@ include file="/WEB-INF/jsp/common/user_header.jsp" %>
</header>
<main>
<p>お知らせ</p>
<c:forEach var="news" items="${newsList}">

	<button class="modalOpen" data-title="お知らせ詳細" data-content1="${news.subject}" data-content2="${news.text}" data-content3="＜＜お知らせ一覧に戻る"  >${news.subject}</button>
	<p>${news.submittedAt}</p>
	</c:forEach>
	
	
	
	

<div class="modal-bg">
    <div class="modal">
        <div class="modal-content">
        	<!-- modal-contentの中は自由に変更してください。 -->
            <p id="modal-title">モーダルの内容</p>
            
            <!-- .closeのものがクリックされると、モーダルが閉じます。 -->
            <div class="close-btn close">
                <div>
                <span></span>
                <span></span>
                </div>
            </div>
            <form action="" method="post" id="modal-form">
            <p>
                <label for="subject">件名<span>*</span><span id="subject-alt"></span></label><br>
                <input type="text" name="subject" id="modal-content1" readonly>
            </p>
            <p>
                <label for="message">本文<span>*</span><span id="message-alt"></span></label><br>
                <textarea name="message" id="modal-content2" readonly></textarea>
            </p>
           
            
        	</form>
        	<!-- .closeのものがクリックされると、モーダルが閉じます。 -->
        	<a class="modalClose close">モーダルを閉じる</a>
        	<!-- modal-contentここまで -->
        </div>
    </div>
</div>
	</main>
	<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script src="${pageContext.request.contextPath}/js/modal.js"></script>
</body>
</html>