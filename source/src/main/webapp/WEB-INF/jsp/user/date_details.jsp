<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>日記・予定詳細ページ</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/date_details.css">
<style>

.star {
	position: relative;
	display: inline-block;
}

.star::before {
	content: "★★★★★";
	color: #cccccc;
}

.star::after {
	content: "★★★★★";
	position: absolute;
	width: var(--starWidth);	/*取得した満足度によって変化する*/
	z-index: 1;
	top: 0;
	left: 0;
	overflow: hidden;
	white-space: nowrap;
	color: #ffcf32;
}

	
</style>
</head>
<body>
<header>
<%@ include file="/WEB-INF/jsp/common/user_header.jsp" %>
</header>
<main>

	<h2>日記・予定詳細ページ</h2	>
	<p></p>
	<c:out value="${msg}"></c:out>
<div class="tabs">
<input id="all" type="radio" name="tab_item" checked>
<label class="tab_item" for="all">予定</label>
<input id="programming" type="radio" name="tab_item">
<label class="tab_item" for="programming">日記</label>
	<!-- 予定タブ -->
	<div class="tab_content" id="all_content">
		<div>
		    <h3>${date}の予定</h3>
		    <p>
		    <button class="modalOpen"
		    	data-title="予定の登録"
		    	data-content2=""
		    	data-content3="登録">
		    	予定の登録
		    </button>
		    </p>
			<!-- 予定一覧 -->
			<c:forEach var="item" items="${scheList}">
			<form method="POST" action="date-details">
				<input type="hidden" name="scheduleId" value="${item.scheduleId}" />
				<input type="hidden" name="date" value="${date}">
		    <div class="border">
			    <div class="list">
			    	<c:out value="${item.schedule}" />
		    	</div>
			    <div style="float: left;">
			    	<input type="button" class="modalOpen submit-btn"
			    		value="編集"
			    		data-title="予定の編集"
			    		data-content1="${item.scheduleId}"
			    		data-content2="${item.schedule}"
			    		data-content3="編集">
			    	<input type="submit" name="submit" value="削除" class="submit-btn">
			    </div>
		    </div>
		    </form>
			</c:forEach>
		</div>
	</div>
	  
	<!-- .modalOpenを持つボタンがクリックされると、モーダルが表示されます。 -->
	<!-- .modal-bgの中身がモーダル表示されます。 -->
	<div class="modal-bg">
	    <div class="modal">
	        <div class="modal-content">
	        	<!-- modal-contentの中は自由に変更してください。 -->
	            <!-- ×ボタン -->
	            <div class="close-btn close">
	                <div>
	                <span></span>
	                <span></span>
	                </div>
	            </div>
			    <form method="post" action="date-details">
			    	<h3 id="modal-title"></h3>
			    	<input type="hidden" name="scheduleId" id="modal-content1">
			    	<input type="hidden" name="date" value="${date}">
			    	<input type="text" name="schedule" id="modal-content2" class="input-text">
			    	<input type="submit" name="submit" id="modal-content3" class="button">
			    </form>
	        	<!-- .closeのものがクリックされると、モーダルが閉じます。 -->
	        	<a class="modalClose close">モーダルを閉じる</a>
	        	<!-- modal-contentここまで -->
	        </div>
	    </div>
	</div>
	
    <!-- 日記閲覧タブ -->
    <div class="tab_content" id="programming_content">
	    <div>
	        <h3>${date}の日記</h3>
	
	        <!--該当日記データが無い場合-->
	        <c:forEach var="d" items="${diary}">
	        <c:if test="${empty d.diary or empty diary}">
	            <p>日記は登録されていません。</p>
	            <p>
	            	<!-- 今日の日付とカレンダーページから送られた日付が一致する場合日記登録ページを表示 -->
					<button id="hide" onclick="location.href='/a1/user/diary-regist'">
						登録する
	        		</button>
	            </p>
	        </c:if>
			</c:forEach>
	        <!--該当する日記データがある場合-->
	        <c:forEach var="d" items="${diary}">
	        <c:if test="${not empty d.diary}">
	            <p>テーマ:${d.theme}</p>
	            <p><img src="${pageContext.request.contextPath}/img/${d.stampPath}" class="stamp"></p>
	            <p>
	            	天気:
	            	<div id="weather" data-weather-code="${d.weatherCode}"></div>
	            	${d.tempMax}℃/${d.tempMin}℃
	            </p>
	        	<span class="star" id="satisfaction" data-satisfaction="${d.satisfaction}"><span></span></span>
		        <p><c:if test="${not empty d.image}"><img src="${pageContext.request.contextPath}/img/${d.image}"></c:if></p>
		        <p>${d.diary}</p>
	        <p>
	        	<!-- 今日の日付とカレンダーページから送られた日付が一致する場合日記編集ボタンを表示 -->
	        	<button id="hide" onclick="location.href='/a1/user/diary-update'">
					編集する
	        	</button>
	        </p>
	        </c:if>
	        </c:forEach>
	    </div>
    </div>
    </div>
</main>
<footer>
<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</footer>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script src="${pageContext.request.contextPath}/js/modal.js"></script>
<script>
'use strict';
// ここから個別処理
//今日かどうかの判定
	const selectDate = "${date}";
	const cDate = "${today}";
	console.log("クリック："+ selectDate + "今日：" + cDate);
	
	document.getElementById("hide").style.visibility ="hidden";
	const hide = document.getElementById("hide");
	console.log(hide);
	if (selectDate == cDate) {
		hide.style.visibility = "visible";
	}
	
if (${not empty diary}) {
	//天気を数字から文字にする
	let weather = null;
	
	const weatherC = document.getElementById("weather");
	//console.log("C：" + weatherC);
	
	let weatherCode = weatherC.dataset.weatherCode;
	console.log("コード：" + weatherCode);
	
	switch (weatherCode) {
		case "0":
			weather = '晴れ';
			break;
		case "1":
			weather = '曇り';
			break;
		case "2":
			weather = '雨';
			break;
		case "3":
			weather = '雪';
			break;
		default:
			weather = 'その他';
	}
	   
	//console.log("天気：" + weather);
	document.getElementById("weather").textContent = weather;
	
//満足度表示
	const sat = document.getElementById("satisfaction");
	let satisfaction = sat.dataset.satisfaction;
	console.log("満足度：" + satisfaction);
	
	satisfaction *= 20;
	console.log("満足度星：" + satisfaction);
	
	sat.style.setProperty('--starWidth', satisfaction + "%");
	console.log(${satisfaction} + "%");
}

</script>
</body>
</html>