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
inputBox.addEventListener("change", function () {
    var reader = new FileReader();
    reader.readAsDataURL(inputBox.files[0]);//发起异步请求
    reader.onload = function (e) {
        //读取完成后，数据保存在对象的result属性中
        let text = `<div class="pic-wrap"><img src="${this.result}" alt="我是物品帅照"></div>`
        $('.pic-wrap:last-child').before(text);
        console.log(inputBox.files[0].name);
        $.ajax({
            type: "POST",
            url: "url",
            data: { "src": this.result },
            dataType: "json",
            success: function (data) {
                alert('done');
            },
            error:
                console.log()
        });
    }
})
/*********************这是提交按钮******************************** */
$('.commit').on('click',function(){
    let $input=$('.input-wrap input');
    let [title,type,massage,price,tel] = [$input.eq(0).val(),$input.eq(1).val(),$input.eq(2).val(),$input.eq(3).val(),$input.eq(4).val()]
    console.log({
        "title":title,
        "type":type,
        "massage":massage,
        "price":price,
        "tel":tel
    })
    $.getJSON('url',{
        "title":title,
        "type":type,
        "massage":massage,
        "price":price,
        "tel":tel
    },function(){
        alert(ok);
    })
})