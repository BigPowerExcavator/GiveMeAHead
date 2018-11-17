﻿
$(
	
	$.getJSON('/MutualAid/PersonalCenter', function (data) {
		$('main .content-l .name-r').html(`${data.trueName}`);
		$('main .content-l .tel-r').html(`${data.phone}`);
		$('main .content-l .domc-r').html(`${data.userDomc}`);
		$('.change input').eq(0).val(data.stuNum);
		$('.change input').eq(1).val(data.userName);
		$('.change input').eq(2).val(data.trueName);
		$('.change input').eq(3).val(data.phone);
		$('.change input').eq(4).val(data.dormitory);
		userId = data.stuNum;
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
				let text = `<li class = "${type} "><div class="card"><div class="head">${typeName}</div><div class="body"><div class="content-wrap"><span>交易内容：</span><span class="content">${content}</span></div><div class="bottom"><div class="date-wrap"><span>日期：</span><span class="date">${date}</span></div><div class="state-wrap"><span>交易进度：</span><span class="state">${state}</span></div></div></div></div></li>`;
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
			gotop();
		}
		else if ($(this).scrollTop() < top) {
			if ($info.attr('id')) {
				$info.removeAttr('id', '"info-wrap-fixed"');
			}
			$('.toTop').remove();
		}
	});
}
/*************************这是退出按钮***************************** */
$('header .exit-icon').on('click', function () {
	sessionStorage.setItem('status', 'no');
	window.location.href = "index.html";
})
/***************************这是选择性别************************** */
$('.sex').on('click',function(){
	$('.sex-wrap').slideToggle();
	$('.sex-wrap div').on('click',function(){
		$('.sex-wrap').slideUp();
		let $this = $(this);
		$('.sex').html($this.html());
	})
})
/***************************这是修改信息的弹窗************************ */
$(".change-btn").on('click', function () {
	$(".change").show().on('click', function (e) {
		$(this).hide();
	}).find('.window').on('click', function (e) {
		e.stopPropagation();
	})
})
/*************************这是修改密码的弹窗**************************** */
$(".change-pwd-btn").on('click', function () {
	$(".change-pwd").show().on('click', function (e) {
		$(this).hide();
	}).find('.window').on('click', function (e) {
		e.stopPropagation();
	})
})
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
$("header .exchange").on('click', function () {
    window.location.href = "exchange/index.html";
})
$(".exit-icon").on("click", function () {
	sessionStorage.setItem('status', "no");
	window.location.href = "index.html";
	$userInfo.remove();
	$login = $("<div class = 'btn login'>登录</div>");
	$(".header .right").prepend($login);
})

/****************************这是修改信息的按钮************************** */
let keyEnter = (obj, fn) => {
	$(obj).on("keyup", function (e) {
		if (e.which == '13') {
			fn();
		}
	})
};
$('.change .button').on('click', register);
keyEnter(".change input:last", register)

function register() {
	let registerId = $('.change input').eq(0).val();
	let nickName = $('.change input').eq(1).val();
	let registerName = $('.change input').eq(2).val();
	let registerTel = $('.change input').eq(3).val();
	let registerDomc = $('.change input').eq(4).val();
	let registerSex = $('.input-wrap .sex').html();
	let regId = /^\d{9}$/;
	let regTel = /^\d{11}$/;
	if (regId.test(registerId)) {
		if (regTel.test(registerTel)) {
			if (registerName == '') {
				layer.msg('名字不能为空')
				//名字为空
			} else {
				let user = {
					stuNum: registerId,
					userName: nickName,
					trueName: registerName,
					dormitory: registerDomc,
					sex: registerSex,
					phone: registerTel
				}
				$.ajax({
					url: '/MutualAid/UpdateUsers',
					data: JSON.stringify(user),
					dataType: 'json',
					method: 'POST',
					success: function (data) {
						if (data['changeUser'] == 'true') {
							window.location.reload();
						}else{
							layer.msg('请稍后重试');
						}
					},
					error: function () {
						alert('error');
					}
				})
			}
		} else {
			layer.msg('手机必须为11位数字');
			// 手机必须为11位数字
		}
	} else {
		layer.msg('提示账号必须为9位数得学号');
		//提示账号必须为9位数得学号
	}
}
/*************************只是修改密码确认按钮******************** */
{
let [x,y,z] = [1,1,1];

$('.change-pwd .button').on('click', changePwd);
keyEnter(".change-pwd input:last", changePwd);
let $input = $('.change-pwd input');
$input.eq(0).on('blur',function(){
	let oldpwd = {"flag":"false","oldPasswd": $input.eq(0).val()};

		$.getJSON('/MutualAid/UpdatePasswd',oldpwd,function(data){

			if(data['state'] != 'true'){
				layer.msg('原密码输入有误');
				x = 0;
			}
		})
	})
	$input.eq(1).on('blur',function(){
		// 验证新密码是否符合格式
		let regPwd = /^[^\s]+$/
		if(!regPwd.test($(this).val())){
			layer.msg('请输入正确的密码格式')
			x = 0;
		}
	})
	$input.eq(2).on('blur',function(){
		// 验证源密码是否正确
		if($input.eq(2).val()!=$input.eq(1).val()){
			layer.msg('前后密码不一致');
			x = 0;
		}
	})
function changePwd(){
	if(x == 1){
		if(y == 1){
			if(z == 1){
				let newpwd = {"flag":"true","newPasswd":$input.eq(1).val()};
				$.getJSON("/MutualAid/UpdatePasswd",newpwd,function(data){
					if(data['newState'] = true){
						window.location.reload();
					}else{
						layer.msg('服务器错误');
					}
				})
			}else{
				layer.msg('前后密码不一致');
			}
		}else{
			layer.msg('请输入正确的密码格式')
		}
	}else{
	layer.msg('原密码输入有误');
	}
}
}
/**************************调试用的***********************************/
/*let data = {
	card1: {
		date: 1,
		state: 1,
		content: 1,
		type: "fix"

	},
	card2: {
		date: 2,
		state: 1,
		content: 3,
		type: "fix"

	},
	card3: {
		date: 3,
		state: 5,
		content: 5,
		type: 'exchange'

	},
	card4: {
		date: 4,
		state: 1,
		content: 1,
		type: "fix"

	},
	card5: {
		date: 5,
		state: 1,
		content: 3,
		type: "fix"

	},
	card6: {
		date: 6,
		state: 5,
		content: 5,
		type: 'exchange'

	},
	card7: {
		date: 7,
		state: 1,
		content: 1,
		type: "express"

	},
	card8: {
		date: 8,
		state: 1,
		content: 3,
		type: "help"

	},
	card9: {
		date: 9,
		state: 5,
		content: 5,
		type: 'exchange'

	},
	"count": 9
};*/
function a(data) {
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

}
a(data);
/****************************这是回到顶部**************************** */
function gotop(){
	if (!$("body").find('.toTop').attr('class')) {
		let toTop = '<div class="toTop"></div>'
		$("body").prepend(toTop);
	
	$('.toTop').css({
		"background-image":"url('img/goTop.png')",
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