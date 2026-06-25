<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>カレンダー</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/user/calendar.css">
</head>
<body>
<header>
<%@ include file="/WEB-INF/jsp/common/user_header.jsp" %>
</header>
<main>

	<h1>カレンダー</h1>
	<!-- カレンダーが表示されるエリア -->
	<div id="calendar"></div>

</main>
<footer>
<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</footer>
<!-- flatpickr JS（CDNから読み込み） -->
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<!-- 日本語化 -->
<script src="https://cdn.jsdelivr.net/npm/flatpickr/dist/l10n/ja.js"></script>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script src="${pageContext.request.contextPath}/js/calendar.js"></script>
<script>
	'use strict';
	// ここから個別処理
	// ==============================
    // 予定がある日付をSetで持っておく
    // ==============================
    const scheduleDates = new Set();
    <c:forEach var="item" items="${scheduleList}">
        scheduleDates.add("D" + "${item.date}");
    </c:forEach>
    
    // ==============================
    // Step1: flatpickrでカレンダーを描画する
    //        onDayCreate で各セルに id="D20260608" 形式のidをつける
    //        onChange   でクリック時に予定あり/なしで飛び先を分岐する
    // ==============================
    flatpickr("#calendar", {
        inline: true,
        locale: "ja",
        dateFormat: "Y-m-d",

        // 各日付セルが生成されるたびに呼ばれる
        onDayCreate: function(dObj, dStr, fp, dayElem) {

            // UTC基準で年月日を取得してidを生成する
            const date = dayElem.dateObj;
            const y = date.getUTCFullYear();
            const m = String(date.getUTCMonth() + 1).padStart(2, '0');
            const d = String(date.getUTCDate() + 1).padStart(2, '0');

            // セルのidに "D20260608" 形式でセットする
            // 数字始まりのidはCSSで扱えないので先頭に "D" をつけている
            dayElem.id = "D" + y + "-" + m + "-" + d;
        },

        // 日付をクリックしたときに呼ばれる
        onChange: function(selectedDates) {
            const date = selectedDates[0];

            // UTC基準で "2026-06-08" 形式のキーを生成する
            const y = date.getUTCFullYear();
            const m = String(date.getUTCMonth() + 1).padStart(2, '0');
            const d = String(date.getUTCDate() + 1).padStart(2, '0');
            const key = y + '-' + m + '-' + d;

            window.location.href = '/a1/user/date-details?date=' + key;
        },
        
        // 月が変更されたときに呼ばれる
        onMonthChange: function(selectedDates, dateStr, instance) {
        	renderCalendar();
    	},
    	
        // 年が変更されたときに呼ばれる
        onYearChange: function(selectedDates, dateStr, instance) {
        	renderCalendar();
        }
    });

    // ==============================
    // Step2: JSTLのforEachでArrayListをループして
    //        各セルに予定ラベル（span）をappendChildで追加する
    //        リンクではなくspanにすることでflatpickrのクリックと競合しない
    //        飛び先はonChangeで制御する
    // ==============================
    
    function renderCalendar() {
    	// ブラウザの復元時にselectedが残らないように
    	document
    		.querySelectorAll(".flatpickr-day.selected")
    		.forEach(day => day.classList.remove("selected"));
    	
    	// それぞれの予定を追加
    	<c:forEach var="item" items="${scheduleList}">
        (function() {
            // id="D20260608" のセルを取得する
            const cell = document.getElementById("D${item.date}");

            // 表示中の月以外の日付はDOMにないのでnullチェックする
            if (!cell) return;

            // spanで予定ラベルを作ってセルに追加する
            const span = document.createElement("span");
            span.className   = "schedule-item"; 	// CSSのスタイルを適用
            span.textContent = "${item.schedule}";	// 予定本文
            cell.appendChild(span);
            // cell.style.backgroundColor
        })();
        </c:forEach>
        
        // 日記が存在する日付の背景をスタンプ画像に変換
        <c:forEach var="diaryView" items="${diaryViewList}">
        (function() {
            // id="D20260608" のセルを取得する
            const cell = document.getElementById("D${diaryView.date}");

            // 表示中の月以外の日付はDOMにないのでnullチェックする
            if (!cell) return;

            // spanで予定ラベルを作ってセルに追加する
            cell.style.backgroundImage = "linear-gradient(rgba(253,246,238,0.3), rgba(253,246,238,0.3)),url(${pageContext.request.contextPath}/img/${diaryView.stampPath})"
            
        })();
        </c:forEach>
        
        // 予定が4つ以上存在する場合の処理
        // 予定がある日付をループしてそれぞれ確認する
        for (const date of scheduleDates) {
        	// 予定のある日付の要素を取得する
        	 const cell = document.getElementById(date);

            // 表示中の月以外の日付はDOMにないのでnullチェックする
            if (!cell) return;

        	// 予定のリストを取得する
        	 const schedules = cell.querySelectorAll(".schedule-item");
        	// 予定の数を数える
            const count = schedules.length;
        	if (count > 4) {
        		schedules[3].textContent = "+" + (count - 3) + " more";
        		for (let i = 4; i < count; i++) {
        			schedules[i].style.display = "none";
        		}
        	}
        }
    }
    
    window.addEventListener("pageshow", function() {
    	renderCalendar();
    });
    
</script>
</body>
</html>