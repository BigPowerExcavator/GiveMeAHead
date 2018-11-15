$('.content-r li.fix').on('click', function () {
    $('.fix-bar').slideDown(500);
}).siblings().on('click', function () {
    $('.fix-bar').slideUp(500);
    $('.fix-bar>div').removeAttr('class').eq(0).attr('class', "hover");
})
$('.fix-bar>div').on('click', function () {
    $(this).attr('class', "hover").siblings().removeAttr('class');
}).eq(0).attr('class', "hover");
/**********************选择保修的状态******************************** */
$('.fix-bar>div').on('click', function () {
    $(this).attr('class', "hover").siblings().removeAttr('class');

}).eq(0).attr('class', "hover");
/********************* 加载卡片数目********************************** */
$(
    $.getJSON('/MutualAid/AdminGetCardsCountServlet', function (data) {
        let {
            allCount: all,
            fixCount: fix,
            exchangeCount: exchange,
            expressCount: express,
            helpCount: help
        } = data;

        $('.all .count').text(all);
        $('.fix .count').text(fix);
        $('.exchange .count').text(exchange);
        $('.express .count').text(express);
        $('.help .count').text(help);

    })
)
/*****************************这是加载的方法**************** */
function cardshow(data) {
    $('.card-wrap').html('');
    for (let i = 1; i <= data.count; i++) {
        let card = 'card' + i;
        let obj = data[card];
        let {
            dormtory: dormtory,
            date: date,
            userTel: userTel,
            userId: userId,
            doorTime: doorTime,
            userName: userName,
            content: content,
            formId: formId,
            state:state
        } = obj;
        date = parseInt(date);
        let year = new Date(date).getFullYear();
        let month = new Date(date).getMonth() + 1;
        let day = new Date(date).getDate();
        date = `${year}-${month}-${day}`;
        let text;
        switch (state) {
            case "0":
                text = `<li class="state state${state}" name="${formId}"><div class="head">处理状态</div><div class="body"><div class="top"><div class="left"><div class="name">报修人：${userName}</div><div class="domc">宿舍：${dormtory}</div><div class="doorTime">上门时间：${doorTime}</div></div><div class="right"><div class="id">学号：${userId}</div><div class="tel">电话：${userTel}</div><div class="date">日期：${date}</div></div></div><div class="bottom">${content}</div><div class="button"><div class="button-l add">添加处理</div><div class="button-r special">特殊处理</div></div></div></li>`
                break;
            case "1":
                text = `<li class="state state${state}" name="${formId}"><div class="head">处理状态</div><div class="body"><div class="top"><div class="left"><div class="name">报修人：${userName}</div><div class="domc">宿舍：${dormtory}</div><div class="doorTime">上门时间：${doorTime}</div></div><div class="right"><div class="id">学号：${userId}</div><div class="tel">电话：${userTel}</div><div class="date">日期：${date}</div></div></div><div class="bottom">${content}</div><div class="button"><div class="button-m  done">已完成</div></div></div></li>`
                break;
            case "2":
                text = `<li class="state state${state}" name="${formId}"><div class="head">处理状态</div><div class="body"><div class="top"><div class="left"><div class="name">报修人：${userName}</div><div class="domc">宿舍：${dormtory}</div><div class="doorTime">上门时间：${doorTime}</div></div><div class="right"><div class="id">学号：${userId}</div><div class="tel">电话：${userTel}</div><div class="date">日期：${date}</div></div></div><div class="bottom">${content}</div><div class="button"><div class="button-l handle">完成处理</div><div class="button-r special">特殊处理</div></div></div></li>`
                break;
            case "3":
                text = `<li class="state state${state}" name="${formId}"><div class="head">处理状态</div><div class="body"><div class="top"><div class="left"><div class="name">报修人：${userName}</div><div class="domc">宿舍：${dormtory}</div><div class="doorTime">上门时间：${doorTime}</div></div><div class="right"><div class="id">学号：${userId}</div><div class="tel">电话：${userTel}</div><div class="date">日期：${date}</div></div></div><div class="bottom">${content}</div><div class="button"><div class="button-m handle">完成处理</div></div></div></li>`
                break;
        }
        
        $('.card-wrap').append(text);
        stateButton(state);
    }
}
/****************************这是对应的按键的方法**************************** */
function stateButton(state){
   let url = '/MutualAid/AdminChangeStateServlet'; //因为都是同一个url，所以你改这个就好了哈
   $($(`.card-wrap .state${state}`).on('click', function (e) {
       $this = $(this);
       let $target = $(e.target);
       let name = $this.attr('name');
       console.log($target.attr('class'));
       let  hide = function(after){
           $.getJSON(url,{"formId":`${name}`,"state":`${state}`,"afterState":`${after}`});
          $this.slideUp(500);
          setTimeout(() => {
              $this.remove();
          }, 500);
       }
       if($target.attr('class') == "button-l add"){
           hide("2");
       }else if($target.attr('class') == "button-r special"){
           hide("3");
       }else if($target.attr('class') == "button-l handle"){
           hide("1");
       }else if($target.attr('class') == "button-m handle"){
           hide("1");
       }
       e.stopPropagation();
   }))
}
/****************************这是页面刚开始加载卡片*************************** */
$(
    $.getJSON('/MutualAid/AdminCetRepairCards', function (data) {
        data = {
            "card1": {
                "dormtory": " 1234#55",
                "date": "1541313848000",
                "userTel": "13060670964",
                "doorTime": '星期一 8:00~10:00',
                "userId": "173441",
                "userName": 'jiayu',
                "content": "我要修灯泡",
                "formId": "123",
                "state": "0"
            },
            "card2": {
                "dormtory": " 1234#55",
                "date": "1541313848000",
                "userTel": "13060670964",
                "doorTime": '星期一 8:00~10:00',
                "userId": "173441",
                "userName": 'jiayu',
                "content": "我要修灯泡",
                "formId": "123",
                "state": "1"
            },
            "card3": {
                "dormtory": " 1234#55",
                "date": "1541313848000",
                "userTel": "13060670964",
                "doorTime": '星期一 8:00~10:00',
                "userId": "173441",
                "userName": 'jiayu',
                "content": "我要修灯泡",
                "formId": "123",
                "state": "2"
            },
            "card4": {
                "dormtory": " 1234#55",
                "date": "1541313848000",
                "userTel": "13060670964",
                "doorTime": '星期一 8:00~10:00',
                "userId": "173441",
                "userName": 'jiayu',
                "content": "我要修灯泡",
                "formId": "123",
                "state": "3"
            },
            "count": "4"
        }
        cardshow(data);
        })
)
/****************************这是页面加载未处理的卡片 ***********************/
$('.content-r .fix').on('click',function(){
    $.getJSON('/MutualAid/AdminCetRepairCards', {"state":"0"},function (data) {
    //     data = {
    //         "card1": {
    //             "dormtory": " 1234#55",
    //             "date": "1541313848000",
    //             "userTel": "13060670964",
    //             "doorTime": '星期一 8:00~10:00',
    //             "userId": "173441",
    //             "userName": 'jiayu',
    //             "content": "我要修灯泡",
    //             "formId": "123",
    //             "state": "0"
    //         },
    //         "count": "4"
    //     }
        cardshow(data);
    })
})
/*****************************这是处理状态的四个按钮******************** */
$('.content-r .fix-bar div').eq(0).on('click',function(){   
    $.getJSON('/MutualAid/AdminCetRepairCards', {"state":"0"},function (data) {
        cardshow(data);
    })
})
$('.content-r .fix-bar div').eq(1).on('click',function(){   
    $.getJSON('/MutualAid/AdminCetRepairCards', {"state":"2"},function (data) {
        cardshow(data);
    })
})
$('.content-r .fix-bar div').eq(2).on('click',function(){   
    $.getJSON('/MutualAid/AdminCetRepairCards', {"state":"1"},function (data) {
        cardshow(data);
    })
})
$('.content-r .fix-bar div').eq(3).on('click',function(){   
    $.getJSON('/MutualAid/AdminCetRepairCards', {"state":"3"},function (data) {
        cardshow(data);
    })
})
/*************************记载用户基本信息******************************* */
$(
    $.getJSON('/MutualAid/AdminCenter', function (data) {
        $('main .content-l .name-r').html(`${data.adminName}`);
        $('main .content-l .tel-r').html(`${data.phone}`);
        $('main .content-l .domc-r').html(`${data.adminNum}`);
        $('.change input').eq(0).val(data.adminNum);
        $('.change input').eq(1).val(data.adminName);
        $('.change input').eq(2).val(data.trueName);
        $('.change input').eq(3).val(data.phone);
        $('.change input').eq(4).val(data.dormitory);
        userId = data.stuNum;
    })
)
/**************************点击对应类型，其他的收起来********************** */
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
})
/************************************************ */
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
	let regId = /^\d{9}$/;
	let regTel = /^\d{11}$/;
	if (regId.test(registerId)) {
		if (regTel.test(registerTel)) {
			if (registerName == '') {
				layer.msg('名字不能为空')
				//名字为空
			} else {
				let user = {
					adminNum: registerId,
					adminName: nickName,
					trueName: registerName,
					sex: "女",
					phone: registerTel
				}
				$.ajax({
					url: '/MutualAid/UpdateAdminInfo',   //这是修改信息的url
					data: JSON.stringify(user),
					dataType: 'json',
					method: 'POST',
					success: function (data) {
						if (data['changeAdmin'] == "true") {
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
	console.log(oldpwd);
		$.getJSON('/MutualAid/UpdatePassword',oldpwd,function(data){     //这是失去焦点验证原密码的url
			console.log(data['state']);
			if(data['state'] != "true"){
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
				$.getJSON("/MutualAid/UpdatePassword",newpwd,function(data){     //这是修改密码的url
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
/***************************这是信息初始加载********************************** */
$(
    $.getJSON('/MutualAid/AdminCenter', function (data) {    //这里填加载信息的url
		$('main .content-l .name-r').html(`${data.adminName}`);
		$('main .content-l .tel-r').html(`${data.phone}`);
		$('main .content-l .domc-r').html(`${data.adminNum}`);
		$('.change input').eq(0).val(data.adminNum);
		$('.change input').eq(1).val(data.adminName);
		$('.change input').eq(2).val(data.trueName);
		$('.change input').eq(3).val(data.phone);
        $('.change input').eq(4).val(data.dormitory);
    })
)