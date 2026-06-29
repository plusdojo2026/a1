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
 <style>
    /* モーダルの背景（暗い部分） */
    .modal-background {
      display: none; /* 最初は非表示 */
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(0,0,0,0.5);
      z-index: 10;
    }

    /* モーダルの本体 */ 
    .modal-content {
      background-color: white;
      width: 300px;
      height:600px;
      margin: 100px auto;
      padding: 20px;
      border-radius: 10px;
      text-align: center;
      z-index: 11;
    }

    /* 閉じるボタン */
    .close-btn {
      margin-top: 10px;
    }
    
  </style>
</head>
<body>
<header>
<%@ include file="/WEB-INF/jsp/common/admin_header.jsp" %>
</header>
<main>
<h1>お知らせ</h1>
<button class="modalOpen" data-title="お知らせのの追加" data-content1="" data-content2="" data-content3="登録" onclick="openModal2()" >追加</button>

<c:forEach var="news" items="${newsList}">
	<p>${news.subject}</p>
	
	<button class="modalOpen" data-title="お知らせのの編集" data-content1="${news.subject}" data-content2="${news.text}" data-content3="保存"  onclick="openModal1('${news.text}','${news.subject}','${news.newsId }')">編集</button>

 <form action="" method="post" >
 <input type="hidden"   name="subject"  value="${news.subject}">
 <input type="hidden"  id="modal-content4 " name="newsId"  value="${news.newsId}">
<input type="submit" name="submit" value="削除">
</form>

<form action="" method="post" >
 <input type="hidden"   name="subject"  value="${news.subject}">
<input type="hidden"  id="modal-content4 " name="newsId"  value="${news.newsId}">
<%-- <c:if test="${news.isDisplay==0}">
	<input type=button name="message1" class="" value="送信">
</c:if>
<c:if test="${news.isDisplay==1}">
	<input type=button name="message1"class="" value="送信取り消し">
</c:if> --%>
</form>
	</c:forEach>
	
	<!-- モーダルウィンドウ部分 -->
<!-- .modal-bgの中身がモーダル表示されます。 -->

<!-- <div class="modal-bg"> -->
   <!--  <div class="modal"> -->
     <div id="modal" class="modal-background">
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
               		<input type="hidden" name="newsId" value="" id="newsId">
                 	<input type="text" name="subject" id="subject" autofocus value="">
            	</p>
             	
         		<p>
         			<textarea  name="text" id="text" >  </textarea>
         		</p>
    	<input type="hidden"  id="modal-content4 " name="newsId"  value="${newsId}">
  				<p>
                <input type="submit" id="bt" name="submit"><br>
            	</p> 
            	 	</form>         
          </div>
           		</div>
     <!--    </div> -->
      <!--  </div> -->
      
</main>
<footer>
<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</footer>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<%-- <script src="${pageContext.request.contextPath}/js/modal.js"></script> --%>
 <script>
    // モーダル表示
    function openModal1(tx,subject,id) {
    	
    	document.getElementById("newsId").value=id;
    	document.getElementById("subject").value=subject;
    	document.getElementById("text").value=tx; 
    	document.getElementById("bt").value="保存";    	
    	
        document.getElementById("modal").style.display = "block";
    }
    function openModal2() {
    	document.getElementById("newsId").value="";
    	document.getElementById("subject").value="";
    	document.getElementById("text").value=""; 
    	document.getElementById("bt").value="登録";    	
    	
        document.getElementById("modal").style.display = "block";
    }

    // モーダル非表示
    function closeModal() {
      document.getElementById("modal").style.display = "none";
    }
    //モーダルを閉じる
    document.querySelector('.close-btn.close').addEventListener('click', function() {
        document.getElementById('modal').style.display = 'none';
    });
  </script>
  
</body>
</html>