$(
    () => {
        if (sessionStorage.getItem('status') == "yes") {
            $login = $('.header .login');
            $register = $('.header .$register');
            $register.remove();
            $login.remove();
            $userInfo = $("<div class = 'btn userInfo'>个人中心</div>");
            $(".header .right").prepend($userInfo);
            $(".header .right .userInfo").on('click', function () {
                window.location.href = "userInfo.html";
            })
        }
    }
)
/****************************这是轮播图************************************** */
let index = 1;
let scroll = (x) => {
    $('.content-r ul.bg').css({
        top: (x - 1) * -700 + 'px'
    })
    $('.content-r ul.text').css({
        marginTop: (x - 1) * -100 + 'px'
    })
    $('.content-l li').eq(index - 1).attr('id', 'hover').siblings().removeAttr('id', 'hover');
}
let inter = () => {
    index++;
    while (index == 7) {
        index = 1;

    }
    scroll(index);
}
let timer = setInterval(inter, 5000);
$('.content-l li').each(function () {
    $(this).on('mouseenter', function () {
        index = $(this).index() + 1;
        scroll(index);
        clearInterval(timer);
    }).on('mouseleave', function () {
        timer = setInterval(inter, 5000);
    })
})
/******************************这是登录弹出按钮****************************** */
$('.header .login').on('click', loginWindow)

function loginWindow() {
    clearInterval(timer);
    let $shadow = $('div.shadow');
    $shadow.show();
    $shadow.find('.window').on('click', function (e) {
        e.stopPropagation();
    })
    $shadow.on('click', function (e) {
        clearInterval(timer);
        timer = setInterval(inter, 5000);
        $shadow.hide();
        windowUp();
        e.stopPropagation();
    });
}
$('.header .register').on('click', function () {
    clearInterval(timer);
    let $shadow = $('div.shadow');
    windowDown();
    $shadow.show();
    $shadow.find('.window').on('click', function (e) {
        e.stopPropagation();
    })
    $shadow.on('click', function (e) {
        clearInterval(timer);
        timer = setInterval(inter, 5000);
        $shadow.hide();
        windowUp();
        e.stopPropagation();
    })
});
$('.input-wrapper .massage a').on('click', windowDown);
$('.register-wrapper .massage a').on('click', windowUp)

function windowDown() {
    $('.window').css('margin-top', '100px');
    $('.window-bg').css('height', '680px').find('.input-wrapper').css('display', 'none').next().css('display', 'block');
}

function windowUp() {
    $('.shadow .window').css('margin-top', '200px');
    $('.window-bg').css('height', '420px').find('.register-wrapper').css('display', 'none').prev().css('display', 'block');
}

/*******************************************这是登录按钮******************************/
$('.input-wrapper .button').on('click', enter);
let keyEnter = (obj, fn) => {
    $(obj).on("keyup", function (e) {
        if (e.which == '13') {
            fn();
        }
    })
};
keyEnter(".input-wrap.bottom input", enter);

function enter() {
    let loginId = $('.input-wrapper .top input').val();
    let loginPwd = $('.input-wrapper .bottom input').val();
    let regId = /^\d{9}$/;
    let regPwd = /^[^\s]+$/;
    if (regId.test(loginId)) {
        if (regPwd.test(loginPwd)) {
            let user = {
                stuId: loginId,
                password: loginPwd
            }
            console.log(JSON.stringify(user));
            $.ajax({
                url: '/MutualAid/UserLogin',
                status: '',
                data: JSON.stringify(user),
                dataType: 'json',
                method: 'POST',
                success: function (data) {

                    if (data['status'] == '1001') {
                        sessionStorage.setItem('status', 'yes');
                        layer.msg('登录成功');
                        $('.shadow').hide();
                        windowUp();
                        $login = $('.header .login');
                        $register = $('.header .register');
                        $register.remove();
                        $login.remove();
                        $userInfo = $("<div class = 'btn userInfo'>个人中心</div>");
                        $(".header .right").prepend($userInfo);
                        $(".header .right .userInfo").on('click', function () {
                            window.location.href = "userInfo.html";
                        })
                    } else if (data['status'] == '1002') {
                        layer.msg('账号或密码错误');
                    } else {
                        layer.msg(data)
                    }
                },
                error: function () {
                    alert('error');
                }
            })
        } else {
            layer.msg('提升密码必须为字母数字标点符号组合');
            //提升密码必须为字母数字标点符号组合
        }
    } else {
        layer.msg('提示账号必须为9位数得学号');
        //提示账号必须为9位数得学号
    }
}
/**************************************这是注册按钮**********************************/


$('.register-wrapper .button').on('click', register);
keyEnter(".code-wrap input", register)

function register() {
    let registerId = $('.register-wrapper .top input').val();
    let $input = $('.register-wrapper .bottom input');
    let registerPwd = $input.eq(0).val();
    let registerCheckPwd = $input.eq(1).val();
    let registerName = $input.eq(2).val();
    let registerTel = $input.eq(3).val();
    let registerCode = $input.eq(4).val();
    let agree = $("input[type='checkbox']").attr("checked");
    let regId = /^\d{9}$/;
    let regPwd = /^[^\s]+$/
    let regTel = /^\d{11}$/;
    if (regId.test(registerId)) {
        if (registerPwd === registerCheckPwd) {
            if (regPwd.test(registerPwd)) {
                if (regTel.test(registerTel)) {
                    if (registerName == '') {
                        layer.msg('名字不能为空')
                        //名字为空
                    } else {
                        if (agree == 'checked') {
                            // layer.msg('发送请求');
                            let user = {
                                stuId: registerId,
                                password: registerPwd,
                                name: registerName,
                                tel: registerTel,
                                code: registerCode
                            }
                            console.log(JSON.stringify(user));
                            $.ajax({
                                url: '/MutualAid/UserRegister',
                                data: JSON.stringify(user),
                                dataType: 'json',
                                method: 'POST',
                                success: function (data) {
                                    if (data['status'] == '1101') {
                                        layer.msg('注册成功');
                                        $('.shadow').hide();
                                        windowUp();
                                    } else if (data['status'] == '1102') {
                                        layer.msg('账号已经存在');
                                    } else if (data['status'] == '1104') {
                                        layer.msg('验证码错误')
                                    } else {
                                        layer.msg('服务器出错，注册无法完成')
                                    }
                                },
                                error: function () {
                                    alert('error');
                                }
                            })
                            //发送请求
                        } else {
                            layer.msg('请统一用户协议与政策');
                        }
                    }
                } else {
                    layer.msg('手机必须为11位数字');
                    // 手机必须为11位数字
                }
            } else {
                layer.msg('密码必须为字母数字标点符号组合');
                //提升密码必须为字母数字标点符号组合
            }
        } else {
            layer.msg('密码不一致');
            // 密码不一致
        }
    } else {
        layer.msg('提示账号必须为9位数得学号');
        //提示账号必须为9位数得学号
    }
}
/***************************************这是验证码按钮******************************** */
$('.code-img').on('click', function () {
    let img = $(this).find('img')
    let date = new Date().getTime();
    img.attr('src', '/MutualAid/GetRegisterCode?=' + date);
})

/******************************这是顶部栏****************************** */
$(".content .index>div").on('click', indexClick);
$(".content-r li").eq(0).on('click', indexClick);

function indexClick() {
    window.location.href = "index.html";
}
$(".content .fix>div").on('click', fixClick);
$(".content-r li").eq(1).on('click', fixClick);

function fixClick() {
    if (sessionStorage.getItem('status') == "yes") {
        window.location.href = "fix.html";
    } else {
        loginWindow();
    }
}
$(".content-r li").eq(2).on('click', unClick);
$(".content-r li").eq(3).on('click', unClick);
$(".content-r li").eq(4).on('click', unClick);
$(".content-r li").eq(5).on('click', unClick);
$(".content-l li").eq(2).on('click', unClick);
$(".content-l li").eq(3).on('click', unClick);
$(".content-l li").eq(4).on('click', unClick);
$(".content-l li").eq(5).on('click', unClick);

function unClick() {
    layer.msg('很快就开放了哦');
}
/*********************这是鼠标盘旋轮播图移动************************ */

// $(".content-r").on('mouseenter', function (e) {
//     let $target = $(e.target);
//     draft($(this), $target.children());

// })
// let draft = (content, element) => {
//     let preTop = parseInt(element.css('top'));
//     let preLeft = parseInt(element.css("left"));
//     content.on('mousemove', function (e) {
//         let x = parseInt(e.pageX - $(this).offset().left);
//         let y = parseInt(e.pageY - $(this).offset().top);
//         let moveX = preLeft + (x - 600) / 50;
//         let moveY = preTop + (y - 350) / 50;
//         let move = function (moveX,moveY) {
//             element.css("left", moveX + "px");
//             element.css("top", moveY + "px");
//         }
//         move(moveX,moveY)
//     $(".content-r").on('mouseleave',function(){
//         element.css("left", preLeft + "px");
//         element.css("top", preTop + "px");
//     })
//     });
// }

// function throttle(method, mustRunDelay, x, y) {
//     let Timer,
//         args = arguments,
//         start;
//         // console.log(x,y)
//     return function loop() {
//         console.log(1)
//         let self = this;
//         let now = Date.now();
//         if (!start) {
//             start = now;
//         }
//         if (timer) {
//             clearTimeout(Timer);
//         }
//         if (now - start >= mustRunDelay) {
//             console.log(x,y)
//             method(x,y).apply(self, args);
//             start = now;
//         } else {
//             console.log(x,y)
//             timer = setTimeout(function () {
//                 loop.apply(self, args);
//             }, 50);
//         }
//     }
// }
// let time = 0;
// function throttle(method,delay,x,y){
//     let args = arguments,
//         self = this;
//     let now = new Date().getTime();
//     if(now - time > delay){
//         time = now;
//         console.log(time)
//     }else{
//         setTimeout(() => {
//             throttle.apply(self,args);
//         }, 50);
//     }
// }