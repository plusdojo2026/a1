<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>管理者テーマ</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/admin_theme.css">
</head>
<body>
<header>
<%@ include file="/WEB-INF/jsp/common/admin_header.jsp" %>
</header>
<main>

<h1>テーマ</h1>
<button class="modalOpen" data-title="テーマの追加" data-content1="" data-content2="" data-content3="登録" data-stamp-id="" data-img="" data-radio="">追加</button>

<c:forEach var="tm" items="${themeList}">
	

	<p>${tm.theme}</p>
		<c:forEach var ="stamp" items="${stampList}">
		<c:if test="${tm.stampId==stamp.stampId}">
	            			<img src="${pageContext.request.contextPath}/img/${stamp.stampPath}"alt="">
	            		</c:if>
	            		</c:forEach>
	            		
	<button class="modalOpen" data-title="テーマの編集" data-content1="${tm.theme}" data-content2="${tm.themeId}" data-content3="保存" data-stamp-id="${tm.stampId}" data-img="" data-radio="${tm.diaryFlag}">編集</button>
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
                	<label for="subject">テーマ名<span></span><span id="subject-alt"></span></label><br>
               		
                 	<input type="text" name="theme" id="modal-content1" autofocus>
            	</p>
             	<p>
              
                	<input class="modal-radio" type="radio" name="choice" value="0">日替わり指定<br>
					<input class="modal-radio" type="radio" name="choice" value="1">通常指定<br>
            	</p>
         		<p>
         			<input type="hidden" id="modal-content2" name="themeId"  value="${themeId}">
         		</p>

            	<div class="stamp-area">
            		<input type="hidden" id="modal-stamp-id" name="stampId">
            		<div id="stamp-button">
            		<p id="modal-content1">スタンプ選択<img src="${pageContext.request.contextPath}/img/arrow_down.svg" alt=""></p>
            		</div>
            		<div class="stamps hidden">
	            		<c:forEach var ="stamp" items="${stampList}">
	            			<div class="stamp" data-id="${stamp.stampId}">
	            				<img src="${pageContext.request.contextPath}/img/${stamp.stampPath}" alt="">
	            			</div>
	            		</c:forEach>
	            	</div>
           		</div>
  				<p>
                <input type="submit" id="modal-content3" name="submit"><br>
            	</p>          
           	</form>
           
        	
        </div>
    </div>
</div>

</main>
<footer>
<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</footer>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script src="${pageContext.request.contextPath}/js/modal.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>

<script>
'use strict';
//ここから個別処理
//jQueryのDOM
$(document).ready(function(){
	
	// ステッカー選択画面表示のtoggle
	$("#stamp-button").on('click', function(){
		$('.stamps').toggleClass('hidden');
	});
	
	// 最初のスタンプを初期値とする
	$(".stamp").first().addClass('selected');
	
	// スタンプをクリックしたときの処理を追加する
	$('.stamp').on('click', function(){
		// 全ての要素の背景を白にする
		$('.stamp').removeClass('selected');
		// クリックされた要素の背景をグレーにする
		$(this).addClass('selected');
		
		// クリックされた要素のidをフォームのvalueに代入する
		$('#modal-stamp-id').val($(this).data('id'));
	});
});

</script>
</body>
</html>