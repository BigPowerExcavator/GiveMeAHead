
$(
	$.getJSON('/MutualAid/PersonalCenter', function (data) {
		$('main .content-l .name-r').html(`${data.trueName}`);
		$('main .content-l .tel-r').html(`${data.phone}`);
		$('main .content-l .domc-r').html(`${data.userDomc}`);
		$.getJSON('/MutualAid/GetPersonalCenterCard', function (data) {

			let fix = 0,
				exchange = 0,
				express = 0,
				help = 0,
				typeName;
			for (let i = 1; i <= data.count; i++) {
				let card = 'card' + i;
				let obj = data[card];
				let {
					date: date,
					state: state,
					content: content,
					type: type
				} = obj;
				date = parseInt(date);
				let year = new Date(date).getFullYear();
				let month = new Date(date).getMonth() + 1;
				let day = new Date(date).getDate();
				date = `${year}-${month}-${day}`;
				switch (obj.type) {
					case "fix":
						fix++;
						typeName = "报修";
						break;
					case "exchange":
						exchange++;
						typeName = "闲置交易";
						break;
					case "express":
						express++;
						typeName = "快递代拿";
						break;
					case "help":
						help++;
						typeName = "求助";
						break;
				}
				let text = `<li class = "${type}"><div class="card"><div class="head">${typeName}</div><div class="body"><div class="content-wrap"><span>交易内容：</span><span class="content">${content}</span></div><div class="bottom"><div class="date-wrap"><span>日期：</span><span class="date">${date}</span></div><div class="state-wrap"><span>交易进度：</span><span class="state">${state}</span></div></div></div></div></li>`;
				$('.all .count').text(data["count"]);
				$('.fix .count').text(fix);
				$('.exchange .count').text(exchange);
				$('.express .count').text(express);
				$('.help .count').text(help);
				$('.card-wrap').append(text);
			}
		})
	})
)
$('main .content-r .sum ul').on('click', function (e) {
	let target = e.target;
	while (target.tagName.toLowerCase() != 'li') {
		target = target.parentNode;
	}
	$(target).children().css({
		"background-color": "transparent"
	}).parent().siblings().children().css({
		"background-color": "white"
	})
	switch (target.className) {
		case 'all':
			$('.card-wrap li').show();

			break;
		case 'fix':
			$('.card-wrap li').filter('.fix').show()
			$('.card-wrap li').not('.fix').hide();
			break;
		case 'exchange':
			$('.card-wrap li').filter('.exchange').show()
			$('.card-wrap li').not('.exchange').hide();
			break;
		case 'express':
			$('.card-wrap li').filter('.express').show()
			$('.card-wrap li').not('.express').hide();
			break;
		case 'help':
			$('.card-wrap li').filter('.help').show()
			$('.card-wrap li').not('.help').hide();
			break;
	}
})
/******************这是监听滚动的事件******************************** */
{
	let $info = $('.content-l>div:last');
	const top = $info.offset().top;
	$(document).scroll(function () {
		if ($(this).scrollTop() > top) {
			if (!$info.attr('id')) {
				$info.attr('id', "info-wrap-fixed");
			}
		}
		else if ($(this).scrollTop() < top) {
			if ($info.attr('id')) {
				$info.removeAttr('id', '"info-wrap-fixed"');
			}
		}
	});
}
/*************************这是退出按钮***************************** */
$('header .exit-icon').on('click', function () {
	sessionStorage.setItem('status','no');
	window.location.href = "index.html";
})
/***************************这是选择性别************************** */
{
	let $sex = $('.change .input-wrap .sex')
	$sex.on('click', function (e) {
		if ($(this).css('height') == '50px') {
			$(this).css({
				'height': '100px'
			})
		} else {
			$(this).css({
				'height': '50px'
			})
		}
		$(this).on('click', function (event) {
			console.log($(event.target).index());
			if ($(event.target).index() == 1) {
				let $sexClass = $(this).children().eq(0).attr('class');
				let $text = $(this).children().eq(0).text();
				let $thisSiblings = $(this).children().eq(1);
				let $SsexClass = $thisSiblings.attr('class');
				let $Stext = $thisSiblings.text();
				console.log($text, $Stext)
				$sex.children().eq(0).removeAttr('class').attr("class", $sexClass);
				$sex.children().eq(1).removeAttr('class');
				// console.log($text,$Stext)
				// $(this).removeAttr().attr($Ssex);
				// $thisSiblings.removeAttr().attr($sex);
			}
			// if($(this).index() == 0){
			// 	$sex.css({
			// 		'height':'50px'
			// 	})
			// 	console.log("nan")
			// }else{
			// 	console.log("nv")
			// 	let $sex = $(this).attr('class');
			// 	let $text = $(this).text();
			// 	let $thisSiblings = $(this).siblings();
			// 	let $Ssex = $thisSiblings.attr('class');
			// 	let $Stext = $thisSiblings.text();
			// 	console.log($text,$Stext)
			// 	$(this).removeAttr().attr($Ssex);
			// 	$thisSiblings.removeAttr().attr($sex);
			// }
			// setTimeout(() => {	
			// $thisSiblings.remove();
			// $(".input-wrap .sex").append($(`<div class="${$sex}">${$text}</div>`));
			// }, 500);
		})
		e.stopPropagation();
		$(document).on('click', function () {
			$sex.css({
				'height': '50px'
			})
		});
	})
}
/***************************这是修改信息的弹窗************************ */
$(".change-btn").on('click', function () {
	$(".change").show().on('click', function (e) {
		$(this).hide();
	}).find('.window').on('click', function (e) {
		e.stopPropagation();
	})
})
/**************************这是回车登陆的方法*************************** */
let enter = (obj, fn) => {
	$(obj).on("keyup", function (e) {
		if (e.keycode == '13') {
			fn();
		}
	})
}

/******************************这是顶部栏****************************** */
$("header .index").on('click', function () {
	window.location.href = "index.html";
})
$("header .fix").on('click', function () {
	window.location.href = "fix.html";
})
$("header .userInfo-icon").on('click', function () {
	window.location.href = "userInfo.html";
})
$(".exit-icon").on("click", function () {
    console.log(1)
    sessionStorage.setItem('status', "no");
    window.location.href = "index.html";
    $userInfo.remove();
    $login = $("<div class = 'btn login'>登录</div>");
    $(".header .right").prepend($login);
})
/***************************这是洪景用的****************************/
// $(
// 	$.getJSON('url', function (data) {
// 	        $('main .content-l .name-r').html(`${data.userName}`);
// 	        $('main .content-l .tel-r').html(`${data.phone}`);
// 	        $('main .content-l .domc-r').html(`${data.domc}`);
// 	})
// )

/***************************这是晓健用的*******************************/
// $.getJSON('url',function(data){

// 	        	for(let i = 1; i <= data[count]; i++){
// 	        		let card = 'card'+i;
// 	        		let obj = data[card];
// 	        		let {
// 							date:date,
// 							state:state,
// 							content:content,
// 							type:type
// 						}=obj;

// 					let text = `<li><div class="card"><div class="head">${type}</div><div class="body"><div class="content-wrap"><span>交易内容：</span><span class="content">${content}</span></div><div class="bottom"><div class="date-wrap"><span>日期：</span><span class="date">${date}</span></div><div class="state-wrap"><span>交易进度：</span><span class="state">${state}</span></div></div></div></div></li>`;
// 					$('.card-wrap').append(text);
// 	        	}
// 	        })

/**************************调试用的***********************************/
//let data = {
//	card1: {
//		date: 1,
//		state: 1,
//		content: 1,
//		type: "fix"
//
//	},
//	card2: {
//		date: 2,
//		state: 1,
//		content: 3,
//		type: "fix"
//
//	},
//	card3: {
//		date: 3,
//		state: 5,
//		content: 5,
//		type: 'exchange'
//
//	},
//	card4: {
//		date: 4,
//		state: 1,
//		content: 1,
//		type: "fix"
//
//	},
//	card5: {
//		date: 5,
//		state: 1,
//		content: 3,
//		type: "fix"
//
//	},
//	card6: {
//		date: 6,
//		state: 5,
//		content: 5,
//		type: 'exchange'
//
//	},
//	card7: {
//		date: 7,
//		state: 1,
//		content: 1,
//		type: "express"
//
//	},
//	card8: {
//		date: 8,
//		state: 1,
//		content: 3,
//		type: "help"
//
//	},
//	card9: {
//		date: 9,
//		state: 5,
//		content: 5,
//		type: 'exchange'
//
//	},
//	"count": 9
//};
//function a(data) {
//	let fix = 0,
//		exchange = 0,
//		express = 0,
//		help = 0,
//		typeName;
//	for (let i = 1; i <= data.count; i++) {
//		let card = 'card' + i;
//		let obj = data[card];
//		let {
//			date: date,
//			state: state,
//			content: content,
//			type: type
//		} = obj;
//		switch (obj.type) {
//			case "fix":
//				fix++;
//				typeName = "报修";
//				break;
//			case "exchange":
//				exchange++;
//				typeName = "闲置交易";
//				break;
//			case "express":
//				express++;
//				typeName = "快递代拿";
//				break;
//			case "help":
//				help++;
//				typeName = "求助";
//				break;
//		}
//		let text = `<li class = "${type}"><div class="card"><div class="head">${typeName}</div><div class="body"><div class="content-wrap"><span>交易内容：</span><span class="content">${content}</span></div><div class="bottom"><div class="date-wrap"><span>日期：</span><span class="date">${date}</span></div><div class="state-wrap"><span>交易进度：</span><span class="state">${state}</span></div></div></div></div></li>`;
//		$('.all .count').text(data["count"]);
//		$('.fix .count').text(fix);
//		$('.exchange .count').text(exchange);
//		$('.express .count').text(express);
//		$('.help .count').text(help);
//		$('.card-wrap').append(text);
//	}
//
//}
//a(data);

