<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップ画面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/top.css">
</head>
<body>
	<c:if test="${ご褒美デイなら}">
		
	</c:if>
	<h1>紫陽花Dialy</h1>
	<div>
		今日のテーマ：<c:out value="${サーブレットで設定した名前}"></c:out>
	</div>
	<div>
		今日の天気<c:out value="${ }"></c:out>
	</div>
	<div>
		今日の気温
		<img id = "weather_img" src = デフォルト画像>
	</div>
	<div>
		連続記録<c:out value="${ }"></c:out>
	</div>
	<div>
		一年前の今日の日記<c:out value="${ }"></c:out>
		<a href="date_details.jsp">続きを読む>></a>
	</div>
	<div>
		お知らせ<c:out value="${ }"></c:out>
		<a href="user_news.jsp">お知らせ一覧>></a>
	</div>
</body>
<script>
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

}

main();
</script>
</html>