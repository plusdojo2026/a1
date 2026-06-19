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


<!-- 以下をコピーして使ってください。 -->
<!-- .modalOpenを持つボタンがクリックされると、モーダルが表示されます。 -->
<button class="modalOpen" data-title="テーマの追加" data-content1="" data-content2="" data-content3="登録" data-img="" data-radio="">追加</button>
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
                <label for="subject">本文<span>*</span><span id="subject-alt"></span></label><br>
               
                 <textarea name="subject" id="modal-content1" autofocus></textarea><br>
            </p>
             <p>
              
                <input class="modal-radio" type="radio" name="choice" value="0">日替わり指定<br>
<input class="modal-radio" type="radio" name="choice" value="1">通常指定<br>
            </p>
           
//スタンプ指定モーダル
            <button class="modalOpen" data-title="" data-content1="" data-content2="" data-content3="" data-img="">スタンプ指定</button>
<!-- .modal-bgの中身がモーダル表示されます。 -->
<div class="modal-bg">
    <div class="modal">
        <div class="modal-content">
        	<!-- modal-contentの中は自由に変更してください。 -->
            <p id="modal-title">スタンプ指定</p>
            
            <!-- .closeのものがクリックされると、モーダルが閉じます。 -->
            <div class="close-btn close">
                <div>
                <span></span>
                <span></span>
                </div>
            </div>
           
                
            
            
             <div class="stamp-area" id="modal-content1">
            <input type="hidden" id="selected-id" name="データを送るときに付ける名前" value="${themes.stamp_id}">
            	<p id="modal-content1">スタンプ<img src="" alt=""></p>
            	<div id="data-content2">
            		<c:forEach var ="stamp" items="${stamp_list}">
            			<div class="stamp" data-id="${stamp_id}">
            			<img src="${stamp_id}" alt="">
            			</div>
            		</c:forEach>
            		</div>
            		</div>
        
        	<!-- .closeのものがクリックされると、モーダルが閉じます。 -->
        	<a class="modalClose close">モーダルを閉じる</a>
        	<!-- modal-contentここまで -->
        </div>
    </div>
</div>
  <p>
                <input type="submit" id="modal-content3"><br>
            </p>          
           </form>
           
        	<!-- .closeのものがクリックされると、モーダルが閉じます。 -->
        	<a class="modalClose close">モーダルを閉じる</a>
        	<!-- modal-contentここまで -->
        </div>
    </div>
</div>

<c:forEach var="tm" items="${themeList}">
	<option>${tm.theme}</option>
<!-- 以下をコピーして使ってください。 -->
<!-- .modalOpenを持つボタンがクリックされると、モーダルが表示されます。 -->
<button class="modalOpen" data-title="テーマの編集" data-content1="${tm.theme}" data-content2="" data-content3="保存" data-img="">編集</button>

</c:forEach>
</main>
<footer>
<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</footer>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script src="${pageContext.request.contextPath}/js/modal.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>

<script>// jQueryのDOM
$(document).ready(function(){
	
	// ステッカー選択画面表示のtoggle
	$("#sticker-buttom").on('click', function(){
		$('.stickers').toggleClass('hidden');
	});
	
	// 最初のスタンプを初期値とする
	$(".sticker").first().addClass('grey-background');
	
	// スタンプをクリックしたときの処理を追加する
	$('.sticker').on('click', function(){
		// 全ての要素の背景を白にする
		$('.sticker').removeClass('grey-background');
		// クリックされた要素の背景をグレーにする
		$(this).addClass('grey-background');
		
		// クリックされた要素のidをフォームのvalueに代入する
		$('#selected-id').val($(this).data('id'));
	});
});
'use strict';
// ここから個別処理

</script>
</body>
</html>