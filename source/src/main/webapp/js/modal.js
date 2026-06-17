'use strict'

//モーダルオープン
//.modal-bgをmodalに格納
let modal = document.querySelector('.modal-bg');

//#modalOpenをクリックしたとき
let openBtn = document.querySelectorAll('.modalOpen');
openBtn.forEach(function(btn){

	//modalOpenクラスが付いた要素をクリックしたら
	btn.onclick = function() {
		
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