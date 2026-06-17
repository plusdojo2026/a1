<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link>
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
				<select name="theme">
					<c:forEach var="tm" items="${themeList}"><%-- サーブレットで付けた名前が入る --%>
						<option value="${tm.theme_id}">${tm.theme}</option><!-- 選ばれたvalueの中身が(番号付けて)情報として送られる -->
					</c:forEach>
				</select>
			</div>
			<div>
				スタンプ
				<div class="stamp">
					<c:forEach var="sl" items="${stampList}">
						<input type="hidden" name="stamp_id" value="${sl.stamp_id}"><!-- 送る用のスタンプID -->
						<div>
							<img src="${sl.stamp_path}" alt="">
						</div>
					</c:forEach>
				</div>
				
			</div>
		</div>
		
		<div>
			<div>
				満足度
				<input type="hidden" name="satisfaction" value=3>
			</div>
		</div>
		
		<div>
			<div>
				画像<input type="file" name="IMAGE" accept="image/*" onchange="previewImage(this);"><br>
				<canvas id="preview" ></canvas><br>
			</div>
		</div>
		
		<div>
			本文<br><textarea name="diary"></textarea>
		</div>
		
		<div colspan="2">
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