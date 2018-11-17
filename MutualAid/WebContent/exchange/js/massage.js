let name = window.location.href.split("\?")[1];
$(
    $.getJSON('/MutualAid/GetGoodCardInfo',{"formId":name} ,function (data) {
        // let data = {
        //     formId: "formId",
        //     content: "我不是",
        //     price: "123",
        //     tel: "13060679064",
        //     title: "我是压力是多得",
        //     userName: "jiayu",
        //     type: "1",
        //     count: 3,
        //     img1: '../img/bg.jpg',
        //     img2: '../img/header-bg.png',
        //     img3: '../img/fix-pic.png',
        //     time: '2018-22-2'
        // }
        $('.title').html(data.title);
        $('.price').html(data.price);
        $('.type').html(data.type);
        $('.tel').html(data.phone);
        $('.time').html(data.time);
        $('.userName').html(data.userName);
        $('span.content').html(data.content);
        for (let i = 0; i < data.count; i++) {
            let img = "img" + (i + 1);
            $('.img-wrap ul').append('<li class="img"></li>')
            $('.img').eq(i).css({
                "background": `url(${data[img]})`,
                "background-size": "100%"
            })
        }
        $('.img').eq(0).addClass('clicked');
        let img = $('.img').eq(0).css("background-image").split("\"")[1]
        $('.big-img').css({
            "background": `url(${img})`,
            "background-size": "100%"
        })
    })
)
$('.img').on('click', function () {
    $(this).addClass('clicked').siblings().removeClass('clicked');
    let img = $(this).css("background-image").split("\"")[1]
    $('.big-img').css({
        "background": `url(${img})`,
        "background-size": "100%"
    })
})
$('.go-r').on('click', function () {
    let left = parseInt($('.img-wrap ul').css('left'));
    left = left - 113;
    $('.img-wrap ul').css('left', left + 'px')
})
$('.go-l').on('click', function () {
    let left = parseInt($('.img-wrap ul').css('left'));
    if (left <= -113) {
        left = left + 113;
    }
    $('.img-wrap ul').css('left', left + 'px')
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
