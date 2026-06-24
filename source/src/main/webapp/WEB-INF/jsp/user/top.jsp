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
<main>
<header>
<%@ include file="/WEB-INF/jsp/common/user_header.jsp" %>
</header>
<!--	<c:if test="${ご褒美デイなら}">
		
	</c:if>    -->
	<h1>紫陽花Dialy</h1>
	<div>
		今日のテーマ：<c:out value="${theme}"></c:out>
	</div>
	<div>
		今日の天気
		<img id = "weather_img" src ="${pageContext.request.contextPath}/img/sunny.png">
	</div>
	<div>
		今日の最高/最低気温<br>
		<div><span id=temp_max></span>/<span id=temp_min></span>℃</div>
	</div>

	<div>
		一年前の今日の日記
        <c:if test="${empty diary}">
            <p>日記は登録されていません。</p>
            <p><!-- 今日の日付とカレンダーページから送られた日付が一致する場合日記登録ページを表示 --></p>
        </c:if>
        
 		<!--該当する日記データがある場合-->
		<c:if test="${not empty diary}">
			<c:out value="${pastDiary}"></c:out>
		</c:if>
		<a href="date_details.jsp">続きを読む>></a>
	</div>
</main>
</body>
<footer>
<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</footer>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script src="${pageContext.request.contextPath}/js/modal.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
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
		const weatherImg = dpcument.getElementById;
	    switch (weatherCode) {
	    	case 0:
	    		weatherImg.src = "${pageContext.request.contextPath}/img/sunny.png";
	    		break;
	    	case 1:
	    		weatherImg.src = "${pageContext.request.contextPath}/img/cloudy.png";
	    		break;
	    	case 2:
	    		weatherImg.src = "${pageContext.request.contextPath}/img/rainy.png";
	    		break;
	    	case 3:
	    		weatherImg.src = "${pageContext.request.contextPath}/img/snowy.png";
	    		break;
	    	default:
	    		weatherImg.src = "${pageContext.request.contextPath}/img/sunny.png";
	    }	   
	    
		document.getElementById("temp_max").textContent = temperatureMax;
		document.getElementById("temp_min").textContent = temperatureMin;

}

main();
</script>
</html>