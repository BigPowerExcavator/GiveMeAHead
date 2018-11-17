/********************************z这是登出按钮************************************ */
$('header .exit-icon').on('click', function () {
    sessionStorage.setItem('status', 'no');
    window.location.href = "../index.html";
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
/******************************这是按钮************************** */
$('.container .return').on('click', function () {
    window.location.href = "index.html";
})
/*********************这是添加图片************************** */
var inputBox = document.querySelector(".add-input");
inputBox.addEventListener("change", function (e) {
    let file = e.target.files[0];
    var reader = new FileReader();
    reader.readAsDataURL(file); //发起异步请求
    reader.onload = function (e) {
        //读取完成后，数据保存在对象的result属性中
        let text = `<div class="pic-wrap"><img src="${this.result}" alt="我是物品帅照"><div class="after"></div></div>`
        $('.pic-wrap:last-child').before(text);
        /**********************这是清除图片****************************** */
        $('.pic-wrap .after').on("click", function () {
            $(this).parent().remove();
        })
        let fd = new FormData();
        fd.append('file', file);
        $.ajax({
            type: 'POST',
            url: '/MutualAid/ReturnImgUrl',
            data: fd,
            processData: false, // 不会将 data 参数序列化字符串
            contentType: false, // 根据表单 input 提交的数据使用其默认的 contentType
            xhr: function () {
                var xhr = new window.XMLHttpRequest();
                xhr.upload.addEventListener("progress", function (evt) {
                    if (evt.lengthComputable) {
                        var percentComplete = evt.loaded / evt.total;
                    }
                }, false);
                return xhr;
            }
        })

    }
})
/*********************这是提交按钮******************************** */
$('.commit').on('click', function () {
    let $input = $('.input-wrap input');
    let [title, type, massage, price, tel] = [$input.eq(0).val(),$('.type-wrap li.clicked').index()+1+'' ,$input.eq(1).val(), $input.eq(2).val(), $input.eq(3).val()    ]
    
    $.getJSON('/MutualAid/ApplyIdleCard', {
        "title": title,
        "type": type,
        "massage": massage,
        "price": price,
        "tel": tel
    }, function () {
        window.location.href = "index.html";
    })
})
/****************************这是选择类型******************** */
$('.container .type').on('click',function(e){
    $('.container .type-wrap>ul').slideToggle(500).children().on('click',function(){
        let $this = $(this);  
        $(this).addClass('clicked').siblings().removeClass('clicked');
        $(this).parent().slideUp(500);
        $('.container .type').html($this.html());
    });
})