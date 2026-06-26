<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日記の編集</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common/common.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/user/diary_regist.css">
<link>
<style>
/* これは評価の星のやつ */
.stars span {
	display: flex; /* 要素をフレックスボックスにする */
	flex-direction: row-reverse; /* 星を逆順に並べる */
	justify-content: flex-end; /* 逆順なので、左寄せにする */
}

.stars input[type='radio'] {
	display: none; /* デフォルトのラジオボタンを非表示にする */
}

.stars label {
	color: #D2D2D2; /* 未選択の星をグレー色に指定 */
	font-size: 30px; /* 星の大きさを30pxに指定 */
	padding: 0 5px; /* 左右の余白を5pxに指定 */
	cursor: pointer; /* カーソルが上に乗ったときに指の形にする */
}

.stars label:hover, .stars label:hover ~ label, .stars input[type='radio']:checked 
	~ label {
	color: #F8C601; /* 選択された星以降をすべて黄色にする */
}
</style>
</head>

<body>
	<!-- ヘッダー -->
	<header>
		<%@ include file="/WEB-INF/jsp/common/user_header.jsp"%>
	</header>
	<main>
		<!-- ↓h1全画面共通！↓ -->
		<h2 class="home">日記を編集する</h2>
		<!-- ↓調べる -->
		<form action="/a1/user/diary-update" method="post"
			enctype="multipart/form-data">

			<div>
				<div>
					<input type=hidden name="userId" value="${userId}">
				</div>
			</div>

			<div>
				<div>
					<input type=hidden name="diary_id" value="${diary.diaryId}">
				</div>
			</div>

			<div class="tenki">

				<div class="date">
					<p>日付 ${date}</p>
				</div>

				<div class="wetherondo">
					<div class="weather">
						<p>
							天気:<input type=hidden name="weatherCode" id="weather"
								value="${diary.weatherCode}">${diary.weatherCode}<span
								id="weatherCode"></span>
						</p>
					</div>

					
						<!-- <input type="hidden" name="temperature-min">
					<input type="hidden" name="temperature-max"> -->
						<!-- はhidden inputタグ（データ送る用の箱、表示はまた別） -->
						<p>
							最低気温は<span id="mn">${diary.tempMin}</span>
							<input type="hidden" name="tempMin" value="${diary.tempMin}" id="temperature-min">
						</p>
						<p>
							最高気温は<span id="mx">${diary.tempMax}</span>
							<input type="hidden" name="tempMax" value="${diary.tempMax}" id="temperature-max">
						</p>
					
				</div>
			</div>

			<div class="tmsl">
				<div class="theme-area">
					テーマ <select name="theme">
						<c:forEach var="tm" items="${themesList}">
							<!-- サーブレットで付けた名前が入る -->
							<option value="${tm.themeId}">${tm.theme}</option>
							<!-- 選ばれたvalueの中身が(番号付けて)情報として送られる -->
						</c:forEach>
					</select>
				</div>
				<div class="stamp-area">
					<!-- スタンプ -->
					<div>
						<div>
							<input type="hidden" id="modal-stamp-id" name="stamp">
							<div id="stamp-button">
								<p>
									スタンプ選択<img
										src="${pageContext.request.contextPath}/img/arrow_down.svg"
										alt="">
								</p>
							</div>
							<div class="stamps hidden">
								<c:forEach var="stamp" items="${stampList}">
									<div class="stamp" data-id="${stamp.stampId}" data-path="${stamp.stampPath}">
										<img
											src="${pageContext.request.contextPath}/img/${stamp.stampPath}"
											alt="">
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
					<%--  <div class="stamp">スタンプ
					<c:forEach var="sl" items="${stampList}">
					<option value=""></option>
						<input type="hidden" name="stamp_id" value="${sl.stampId}"><!-- 送る用のスタンプID -->
						<div>
							<img src="${pageContext.request.contextPath}/img/${sl.stampPath}" alt="">
						</div>
					</c:forEach>
				</div> --%>

				</div>
			</div>

			<div class="satisfaction">
				<div>

					<div class="review">
						<p id="stars">満足度</p>
						<div class="stars">
							<!-- <input type=hidden name="satisfaction"> -->
							<span> <input id="1" type="radio" name="satisfaction"
								value="5" <c:if test="${diary.satisfaction == 5}">checked</c:if>><label
								for="1">★</label> <input id="2" type="radio" name="satisfaction"
								value="4" <c:if test="${diary.satisfaction == 4}">checked</c:if>><label
								for="2">★</label> <input id="3" type="radio" name="satisfaction"
								value="3" <c:if test="${diary.satisfaction == 3}">checked</c:if>><label
								for="3">★</label> <input id="4" type="radio" name="satisfaction"
								value="2" <c:if test="${diary.satisfaction == 2}">checked</c:if>><label
								for="4">★</label> <input id="5" type="radio" name="satisfaction"
								value="1" <c:if test="${diary.satisfaction == 1}">checked</c:if>><label
								for="5">★</label>
							</span>
						</div>
					</div>
				</div>
			</div>

			<div>
				<div class="img-section">
					画像<input type="file" name="image" accept="image/*"
						onchange="previewImage(this);"><br>
					<canvas id="preview"></canvas>
					<br>
				</div>
			</div>

			<div>
				本文<br>
				<textarea name="diary" maxlength="300" class="input-textarea">${diary.diary}</textarea>
			</div>

			<div>
				<input type="submit" name="regist" value="更新" class="button"><br>
			</div>

		</form>

	</main>
	<!-- フッター -->
	<footer>
		<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
	</footer>
	<script src="${pageContext.request.contextPath}/js/common.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>

	<script>
		/* const star1 = document.getElementById('star1');
		function b1() {
			star1.style.color='#F8C601';
		} */

		function previewImage(obj) {

			var fileReader = new FileReader();

			// 読み込み後に実行する処理
			fileReader.onload = (function() {

				// canvas にプレビュー画像を表示
				var canvas = document.getElementById('preview');
				var ctx = canvas.getContext('2d');
				var image = new Image();
				image.src = fileReader.result;
				console.log(fileReader.result) // ← (確認用)

				image.onload = (function() {
					canvas.width = image.width;
					canvas.height = image.height;
					ctx.drawImage(image, 0, 0);
				});
			});
			// 画像読み込み
			fileReader.readAsDataURL(obj.files[0]);
			console.log(fileReader.result) // ← (確認用)null
		};

		// 読み込み後に実行する処理
		window.onload = (function() {

			// canvas にプレビュー画像を表示
			var canvas = document.getElementById('preview');
			var ctx = canvas.getContext('2d');
			var image = new Image();

			image.onload = (function() {
				canvas.width = image.width;
				canvas.height = image.height;
				ctx.drawImage(image, 0, 0);
			});

			console.log("file");
			image.src = '${pageContext.request.contextPath}/img/${diary.image}';

		});
		
		//jQueryのDOM
		$(document).ready(function(){
			
			// ステッカー選択画面表示のtoggle
			$("#stamp-button").on('click', function(){
				$('.stamps').toggleClass('hidden');
			});
			
			// 選択されていたスタンプを初期値とする
			$(".stamp[data-path='${diary.stampPath}']").addClass('selected');
			
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