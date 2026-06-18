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
		let img = btn.dataset.img;
		
		document.getElementById("modal-title").textContent = title;
		document.getElementById("modal-content1").value = content1;
		document.getElementById("modal-content2").value = content2;
		document.getElementById("modal-content3").value = content3;
		document.getElementById('modal-img').src = img;
		
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