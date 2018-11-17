/*************************页面加载完成就加载一次*********************** */
$(getCard());
/*************************这是选择分类标签******************************** */
$(".body .tag-nav").on('click', function (e) {
	let $target = $(e.target);
	if (e.target.tagName == 'LI') {
		$target.addClass('clicked').siblings().removeAttr('class');
	}
	getCard();
})
/***************************这是排序标签*********************** */
$(" .body .sort-nav").on('click', function (e) {
	let $target = $(e.target);
	if (e.target.tagName == 'LI') {
		$target.addClass('clicked').siblings().removeAttr('class');
		$(".body .shop-nav").children().eq($target.index()).addClass('clicked').siblings().removeAttr('class');
	}
	getCard();
})
/******************* 这是选择时间价格优先的按钮***************************/
$(".body .shop-nav").on('click', function (e) {
	let $target = $(e.target);
	if (e.target.tagName == 'DIV') {
		$target.addClass('clicked').siblings().removeClass('clicked');
		$(" .body .sort-nav").children().eq($target.index()).addClass('clicked').siblings().removeClass('clicked');
	}
	getCard();
})
/*********************这是同步***************************** */
$(".search-nav").on('click', function (e) {
	let $target = $(e.target);
	if (e.target.tagName == 'LI') {
		$target.addClass('clicked').siblings().removeClass('clicked');
		$(" .body .tag-nav").children().eq($target.index()).addClass('clicked').siblings().removeClass('clicked');
	}
})
/***********************这是左侧固定*********************************** */
{
	let $info = $(".body .left")
	let $sort = $(" .body .sort-nav li");
	const top = $info.offset().top;
	$(document).scroll(function () {
		if ($(this).scrollTop() > top) {
			if (!$info.attr('id')) {
				$info.attr('id', "top");
			}
			$sort.removeClass('hide');
			gotop();
		} else if ($(this).scrollTop() < top) {
			if ($info.attr('id')) {
				$info.removeAttr('id', "top");
				$sort.addClass('hide');
			}
			$(".toTop").remove();
		}
	});
}
/****************************这是回到顶部**************************** */
function gotop(){
	if (!$("body").find('.toTop').attr('class')) {
		let toTop = '<div class="toTop"></div>'
		$("body").prepend(toTop);
	
	$('.toTop').css({
		"background-image":"url('../img/goTop.png')",
		"background-size":" 100%" ,
		"border": "2px solid #fff",
		"height": "0px",
		"width": "0px",
		"border-radius":" 50%" ,
		"position": "fixed",
		"right": "100px",
		"bottom": "100px",
		"transition": "0.5s"
	})
}
	
		$('.toTop').css({
			"height": "100px",
			"width": "100px",
			"right": "50px",
			"bottom": "50px"
		})

	$('.toTop').on('click', function () {
		window.scrollTo(0, 0);
	})
}
//*********************** */ 这是发送请求的方法*****************************/
function getCard() {
	data = {
		"card1": {
			"formId": "80",
			"img": {
				"img1": "/MutualAid/img/goods/手表.jpg"
			},
			"price": "140",
			"count": 1,
			"time": "2018-11-11",
			"title": "手表",
			"type": "数码用品",
			"userName": "杨大力",
			"content": "Apple Watch 4手表"
		},
		"count": 1
	}
	let type = $(".body .tag-nav li.clicked").index()+"";
	let sort = $(".body .sort-nav li.clicked").index()+"";
	switch(sort){
		case "1" :
			sort = "timeUp";
			break;
		case "0" :
			sort = "timeDown";
			break;
		case "3" :
			sort = "priceUp";
			break;
		case "2" :
			sort = "priceDown";
			break;

	}
	let search = $(".input-wrap input").val();
	if(search){
		$.getJSON("/MutualAid/GetFindGoodsCards", {
			"type": type,
			"sort": sort,
			"search":search
		}, function (data) {
			showCard(data);
		})
	}else{
		$.getJSON("/MutualAid/GetGoodsCards", {
			"type": type,
			"sort": sort
		}, function (data) {
			showCard(data);
		})
	}
}
/***************************这是每次点击加载卡片的方法 **********************/
function showCard(data) {
	$('.right .shop').html('');
	for (let i = 1; i <= data['count']; i++) {
		let card = 'card' + i;
		let obj = data[card];
		let {
			"goodsImg": goodsImg,
			"stuNum": stuNum,
			"goodsId": goodsId,
			"goodsIntro": goodsIntro,
			"goodsPrice": goodsPrice,
			"time": time,
			"title": title,
			"userName": userName,
			"goodsName": goodsName,
			"goodsType": goodsType
		} = obj;
		let text = `<li name="${goodsId}"><div class="pic"><img src="${goodsImg}" alt="这是物品帅照"></div><div class="title">${title}</div><div class="bottom"><div class="name">用户：${userName}</div><div class="price">RMB：${goodsPrice}</div></div></li>`
		$('.right .shop').append(text);

	}
	$('.shop li').on('click', function () {
		let name = $(this).attr('name');
		window.location.href = `massage.html?${name}`;
	})
}
/**************************这是申请闲置************************* */
$(".apply").on('click', function () {
	window.location.href = "apply.html";
})
/******************************这是顶部栏****************************** */
$("header .index").on('click', function () {
	window.location.href = "../index.html";
})
$("header .fix").on('click', function () {
	window.location.href = "../fix.html";
})
$("header .exchange").on('click', function () {
	window.location.href = "index.html";
})
$("header .userInfo-icon").on('click', function () {
	window.location.href = "../userInfo.html";
})
$(".back").on('click', function () {
	window.location.href = "index.html";
})
/*************************这是退出按钮***************************** */
$('header .exit-icon').on('click', function () {
	sessionStorage.setItem('status', 'no');
	window.location.href = "../index.html";
})
/**************************这是搜索类型 *******************************/
$('.search-type').on('click',function(e){
    $('.search-nav').slideToggle(500).children().on('click',function(){
        let $this = $(this);  
        $(this).addClass('clicked').siblings().removeClass('clicked');
        $(this).parent().slideUp(500);
        $('.search-type').html($this.html()+'▼');
    });
})
/*********************搜索按钮***************************** */
$('.search-icon').on('click',function(){
	getCard();
})
/***********************回车搜索**************** */
let keyEnter = (obj, fn) => {
	$(obj).on("keyup", function (e) {
		if (e.which == '13') {
			fn();
        }
    })
};
keyEnter('.input-wrap input',getCard)