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
    $.getJSON('url', function (data) {
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

    }
}
/****************************这是页面刚开始加载卡片*************************** */
$(
    $.getJSON('url', function (data) {
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
    $.getJSON('url', {"state":"0"},function (data) {
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
    $.getJSON('url', {"state":"0"},function (data) {
        cardshow(data);
    })
})
$('.content-r .fix-bar div').eq(1).on('click',function(){   
    $.getJSON('url', {"state":"2"},function (data) {
        cardshow(data);
    })
})
$('.content-r .fix-bar div').eq(2).on('click',function(){   
    $.getJSON('url', {"state":"1"},function (data) {
        cardshow(data);
    })
})
$('.content-r .fix-bar div').eq(3).on('click',function(){   
    $.getJSON('url', {"state":"3"},function (data) {
        cardshow(data);
    })
})
/*************************记载用户基本信息******************************* */
$(
    $.getJSON('url', function (data) {
        $('main .content-l .name-r').html(`${data.userName}`);
        $('main .content-l .tel-r').html(`${data.phone}`);
        $('main .content-l .domc-r').html(`${data.dormitory}`);
        $('.change input').eq(0).val(data.stuNum);
        $('.change input').eq(1).val(data.userName);
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