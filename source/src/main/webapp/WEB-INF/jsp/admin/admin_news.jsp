<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>お知らせ</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/admin_news.css">
</head>
<body>
<header>
<%@ include file="/WEB-INF/jsp/common/admin_header.jsp" %>
</header>
<main>
<h1>お知らせ</h1>
<button class="modalOpen" data-title="お知らせのの追加" data-content1="" data-content2="" data-content3="登録"   >追加</button>

<c:forEach var="news" items="${newsList}">
	<p>${news.subject}</p>
	
	<button class="modalOpen" data-title="お知らせのの編集" data-content1="${news.subject}" data-content2="${news.text}" data-content3="保存"   >編集</button>


<c:if test="${news.isDisplay==0}">
	<input type=button name="message1" class="" value="送信">
</c:if>
<c:if test="${news.isDisplay==1}">
	<input type=button name="message1"class="" value="送信取り消し">
</c:if>
<input type="submit" value="削除">
	</c:forEach>
	
	<!-- モーダルウィンドウ部分 -->
<!-- .modal-bgの中身がモーダル表示されます。 -->
<div class="modal-bg">
    <div class="modal">
        <div class="modal-content">
        	<!-- modal-contentの中は自由に変更してください。 -->
            <p id="modal-title"></p>
            
            <!-- .closeのものがクリックされると、モーダルが閉じます。 -->
            <div class="close-btn close">
                <div>
                <span></span>
                <span></span>
                </div>
            </div>
            <form action="" method="post" id="modal-form">
            	<p>
                	<label for="subject">お知らせ<span></span><span id="subject-alt"></span></label><br>
               		
                 	<input type="text" name="subject" id="modal-content1" autofocus>
            	</p>
             	
         		<p>
         			<textarea id="modal-content2" name="text" ></textarea>
         		</p>
    	<input type="hidden"  name="newsId"  value="${newsId}">
  				<p>
                <input type="submit" id="modal-content3" name="submit"><br>
            	</p> 
            	 	</form>         
          </div>
           		</div>
        </div>
       
</main>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script src="${pageContext.request.contextPath}/js/modal.js"></script>

</body>
</html>