<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日記の登録</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/diary_regist.css">
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
<header>
<%@ include file="/WEB-INF/jsp/common/user_header.jsp" %>
</header>
<main>
	<!-- ↓h1全画面共通！↓ -->
	<h2 class="home">日記を登録する</h2>              <!-- ↓調べる -->
	<form action="/a1/user/diary-regist" method="post" enctype="multipart/form-data">
	
		<div>
			<div>
				<input type=hidden name="userId">
			</div>
		</div>
		
		<div class="tenki">
			
				<div class="date">
					<p>日付 ${date}</p>
				</div>
			
			<div class="weture">
				<div class="weather">
					<p>天気:<input type=hidden name="weatherCode" id="weather"><span id="weatherCode"></span></p>
				</div>
				
				<div>
					<!-- <input type="hidden" name="temperature-min">
					<input type="hidden" name="temperature-max"> -->
						<!-- はhidden inputタグ（データ送る用の箱、表示はまた別） -->
						<p>最低気温は<span id="mn"></span><input type = "hidden" name="tempMin"  id="temperature-min">
						最高気温は<span id="mx"></span><input type = "hidden" name="tempMax" id="temperature-max"></p>
				</div>
			</div>
		</div>

		<div class="tmsl">
			<div>
				テーマ
				<select name="theme">
					<c:forEach var="tm" items="${themesList}"><!-- サーブレットで付けた名前が入る -->
						<option value="${tm.themeId}">${tm.theme}</option><!-- 選ばれたvalueの中身が(番号付けて)情報として送られる -->
					</c:forEach>
				</select>
			</div>
			<div>
				<!-- スタンプ -->
				<div class="stamp">
				
            	<p id=sticker-buttom>スタンプ<img src="img/arrow_down.svg" alt=""></p>
            	<input type="hidden" id="selected-id" name="stamp-id" value="1">
            	
            	<div class="stickers">
            		<c:forEach var ="sl" items="${stampList}">
            			<div class="sticker" data-id="${sticker.stickerId}">
            				<label><input type="radio" name="stamp" value="${sl.stampId}">
            					<img src="${pageContext.request.contextPath}/img/${sl.stampPath}" alt="">
            				</label>
            			</div>
            		</c:forEach>
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
  		<div class="stars"><!-- <input type=hidden name="satisfaction"> -->
		    <span>
		      <input id="1" type="radio" name="satisfaction" value="1"><label for="1">★</label>
		      <input id="2" type="radio" name="satisfaction" value="2"><label for="2">★</label>
		      <input id="3" type="radio" name="satisfaction" value="3"><label for="3">★</label>
		      <input id="4" type="radio" name="satisfaction" value="4"><label for="4">★</label>
		      <input id="5" type="radio" name="satisfaction" value="5"><label for="5">★</label>
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
		
		<div class="input-textarea">
			本文<br><textarea name="diary" maxlength="300"></textarea>
		</div>
		
		<div class="button">
			<input type="submit" name="regist" value="登録" ><br>
		</div>
		
	</form>
	
</main>
<!-- フッター -->
<footer>
<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</footer>
<script src="${pageContext.request.contextPath}/js/common.js"></script>

<script>

	/* const star1 = document.getElementById('star1');
	function b1() {
		star1.style.color='#F8C601';
	} */
	
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
	
	let temperatureMax = null;
	let temperatureMin = null;
	let weatherCode = null;
	const url = "https://api.open-meteo.com/v1/forecast?latitude=35.6895&longitude=139.6917&daily=temperature_2m_max,temperature_2m_min,weather_code&models=jma_seamless&timezone=Asia%2FTokyo"

	function getSimpleWeatherCode(rawWeatherCode) {
	    if (rawWeatherCode === 0 || rawWeatherCode === 1) {
	        // 0（晴れ）
	        return 0;
	    } else if (rawWeatherCode >= 2 && rawWeatherCode <= 49) {
	        // 1（曇り）
	        return 1;
	    } else if (rawWeatherCode >= 50 && rawWeatherCode <= 69 || rawWeatherCode >=80 && rawWeatherCode <= 99) {
	        // 2（雨）
	        return 2;
	    } else if (rawWeatherCode >=70 && rawWeatherCode <= 79) {
	        // 3（雪）
	        return 3;
	    } else {
	        // その他
	        return 4;
	    }


	}

	// asyncとawaitは非同期処理を同期処理のような見た目で書くための仕組み
	async function getWetherInf() {
	    const response = await fetch(url);
	    const data = await response.json();

	    weatherCode= getSimpleWeatherCode(data.daily.weather_code[0]);
	    temperatureMax = data.daily.temperature_2m_max[0];
	    temperatureMin = data.daily.temperature_2m_min[0];
	}

	async function main() {
	    await getWetherInf();
	    // weatherCodeという名前の変数に天気番号が入ってます。（0:晴れ、1:曇り、2:雨、3:雪、4:その他）
	    // temperatureMaxという名前の変数に最高気温が入ってます。
	    // temperatureMinという名前の変数に最低気温が入ってます。

	    // 確認用
	    console.log(weatherCode);
	    console.log(temperatureMax);
	    console.log(temperatureMin);

	    // この下にテキストを表示させる処理を書いてください。
	    //天気を数字から文字にする
	    let weather = null;
	    switch (weatherCode) {
	    	case 0:
	    		weather = '晴れ';
	    		break;
	    	case 1:
	    		weather = '曇り';
	    		break;
	    	case 2:
	    		weather = '雨';
	    		break;
	    	case 3:
	    		weather = '雪';
	    		break;
	    	default:
	    		weather = 'その他';
	    }
	    
		document.getElementById("weatherCode").textContent = weather;
		document.getElementById("mx").textContent = temperatureMax;
		document.getElementById("temperature-max").value = temperatureMax;
		document.getElementById("mn").textContent = temperatureMin;
		document.getElementById("temperature-min").value = temperatureMin;
		document.getElementById("weather").value = weatherCode;
	}

	main();

	
	
</script>
</body>
</html>