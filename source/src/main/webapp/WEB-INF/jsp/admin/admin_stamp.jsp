<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者｜スタンプ登録</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/admin_survey.css">
</head>
<body>
	<h1>スタンプ登録</h1>
	<c:forEach var = "stamp" items = "${stampList}">
		<div>
			<input type="hidden" name="number" value="${stamp.stampId}">
			<div>
				<img src="${pageContext.request.contextPath}/img/${stamp.stampPath}">
			</div>
		</div>
	</c:forEach>
	
	<form action="<c:url value='/admin/stamp' />" method="post" enctype="multipart/form-data">
		画像:<input type="file" name="IMAGE" accept="image/*" onchange="previewImage(this);"><br>
		<canvas id="preview" style="max-width:200px;"></canvas><br>
		<input type="submit" value="送信">
	</form>
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
	console.log('${stampList}');
</script>
</html>