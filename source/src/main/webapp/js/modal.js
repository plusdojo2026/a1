'use strict'

//モーダルオープン
//.modal-bgをmodalに格納
let modal = document.querySelector('.modal-bg');

//#modalOpenをクリックしたとき
let openBtn = document.querySelectorAll('.modalOpen');
openBtn.forEach(function(btn){

	//modalOpenクラスが付いた要素をクリックしたら
	btn.onclick = function() {
		// 要素の受け取り
		let title = btn.dataset.title;
		let content1 = btn.dataset.content1;
		let content2 = btn.dataset.content2;
		let content3 = btn.dataset.content3;
		let stampId = btn.dataset.stampId;
		let img = btn.dataset.img;
		let radio = btn.dataset.radio;
		
		// HTML要素を変数に代入
		let modalTitle = document.getElementById("modal-title");
		let modalContent1 = document.getElementById("modal-content1");
		let modalContent2 = document.getElementById("modal-content2");
		let modalContent3 = document.getElementById("modal-content3");
		let modalStampId = document.getElementById("modal-stamp-id");
		let modalImg = document.getElementById('modal-img');
		let modalRadio = document.querySelectorAll('.modal-radio');
		
		if (modalTitle !== null || modalTitle === "") modalTitle.textContent = title;
		if (modalContent1 !== null || modalContent1 === "")modalContent1.value = content1;
		if (modalContent2 !== null || modalContent2 === "")modalContent2.value = content2;
		if (modalContent3 !== null || modalContent3 === "")modalContent3.value = content3;
		if (modalStampId !== null || modalStampId === "") {
			modalStampId.value = stampId;
			const selectedStamp = document.querySelector('.stamp[data-id="' + stampId +'"]');
			// selectedStamp.classList.add('selected');
		}
		if (modalImg !== null || modalTitle === "")modalImg.src = img;
		modalRadio.forEach(function(radioBtn) {
			if (radioBtn.value === radio) radioBtn.checked = true;
		});
		
        //.modal-bgにactiveクラスを追加
        modal.classList.add("active");
    }
});


//モーダルクローズ
//closeクラスがついた要素をcloseBtnに格納
let closeBtn = document.querySelectorAll('.close');

//closeクラスがついた要素をforEachで一致したリストへアクセス
//btnにはcloseBtnの各要素が入る
closeBtn.forEach(function(btn){

    //closeクラスが付いた要素をクリックしたら
    btn.onclick = function (){
	
        //.modal-bgのactiveクラスを外す
        modal.classList.remove("active");
    }
});