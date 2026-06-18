<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日記の登録</title>
<link>
<style>
/* これは評価の星のやつ */
	.stars span{
	  display: flex;               /* 要素をフレックスボックスにする */
	  flex-direction: row-reverse; /* 星を逆順に並べる */
	  justify-content: flex-end;   /* 逆順なので、左寄せにする */
	}

	.stars input[type='radio']{
	  display: none;               /* デフォルトのラジオボタンを非表示にする */
	}

	.stars label{
	  color: #D2D2D2;              /* 未選択の星をグレー色に指定 */
	  font-size: 30px;             /* 星の大きさを30pxに指定 */
	  padding: 0 5px;              /* 左右の余白を5pxに指定 */
	  cursor: pointer;             /* カーソルが上に乗ったときに指の形にする */
	}

	.stars label:hover,
	.stars label:hover ~ label,
	.stars input[type='radio']:checked ~ label{
	  color: #F8C601;              /* 選択された星以降をすべて黄色にする */
	}
</style>
</head>

<body>
<!-- ヘッダー -->
<main>
	<!-- ↓h1全画面共通！↓ -->
	<h1 class="home">日記の登録</h1>              <!-- ↓調べる -->
	<form action="/a1/user/diary-regist" method="post" enctype="multipart/form-data">
	
		<div>
			<div>
				日付天気気温
			</div>
		</div>
		
		<div>
			<div>
				テーマ
				<%-- <select name="theme">
					<c:forEach var="tm" items="${themeList}">サーブレットで付けた名前が入る
						<option value="${tm.theme_id}">${tm.theme}</option><!-- 選ばれたvalueの中身が(番号付けて)情報として送られる -->
					</c:forEach>
				</select> --%>
			</div>
			<div>
				スタンプ
				<%-- <div class="stamp">
					<c:forEach var="sl" items="${stampList}">
						<input type="hidden" name="stamp_id" value="${sl.stamp_id}"><!-- 送る用のスタンプID -->
						<div>
							<img src="${sl.stamp_path}" alt="">
						</div>
					</c:forEach>
				</div> --%>
				
			</div>
		</div>
		
		<div>
			<div>
				
				<div class="review">
  		<p>満足度</p>
  		<div class="stars">
		    <span>
		      <input id="1" type="radio" name="review"><label for="review01">★</label>
		      <input id="2" type="radio" name="review"><label for="review02">★</label>
		      <input id="3" type="radio" name="review"><label for="review03">★</label>
		      <input id="4" type="radio" name="review"><label for="review04">★</label>
		      <input id="5" type="radio" name="review"><label for="review05">★</label>
		    </span>
 		 </div>
	</div>
			</div>
		</div>
		
		<div>
			<div>
				画像<input type="file" name="image" accept="image/*" onchange="previewImage(this);"><br>
				<canvas id="preview" ></canvas><br>
			</div>
		</div>
		
		<div>
			本文<br><textarea name="diary" maxlength="300"></textarea>
		</div>
		
		<div>
			<input type="submit" name="regist" value="登録" ><br>
		</div>
		
	</form>
	
</main>
<!-- フッター -->
</body>
<script>
	function previewImage(obj){

		var fileReader = new FileReader();

		// 読み込み後に実行する処理
		fileReader.onload = (function() {

			// canvas にプレビュー画像を表示
			var canvas = document.getElementById('preview');
			var ctx = canvas.getContext('2d');
			var image = new Image();
			image.src = fileReader.result;
			console.log(fileReader.result) // ← (確認用)

			image.onload = (function () {
				canvas.width = image.width;
				canvas.height = image.height;
				ctx.drawImage(image, 0, 0);
			});
		});
		// 画像読み込み
		fileReader.readAsDataURL(obj.files[0]);
		console.log(fileReader.result) // ← (確認用)null
	}
	


</script>
</html>