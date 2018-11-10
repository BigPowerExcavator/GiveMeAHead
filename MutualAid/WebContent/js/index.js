// $('.button').on('click',function(){
//   let stuId = $('.top input').val();
//   let password = ('.bottom input').val();
//     console.log(x);
//     if(stuId != '' && password != ''){
//        
//     }
// })
// let id = $('.input-wrapper .top input').val();
// let pwd = $('.input-wrapper .bottom input').val();
// let regId = /^[0-9]{9,9}$/; 
// if(regId.test(id)){
//     console.log(1)
// }else{
//     console.log(2)
// }
// $('.input-wrapper .button').on('click',function(){
//   let id = $('.input-wrapper .top input').val();
//   let pwd = $('.input-wrapper .bottom input').val();
//   let regId = /^\d{9}$/;
//   let regPwd = /[\s]+/;
//     if(regId.test(id)){
//         if(regPwd.test(pwd)){
//             //发送请求
//         }else{
//             //提升密码必须为字母数字标点符号组合
//         }
//     }else{
//             //提示账号必须为9位数得学号
//     }
// })
/********************************************这是登录弹出按钮****************************************** */
$('header .user-btn').on('click', function () {
    let $shadow = $('div.shadow');
      $shadow.show();
      $shadow.find('.window').on('click', function (e) {
          e.stopPropagation();
      })
      $shadow.on('click', function (e) {
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
  /*******************************************这是保修弹出按钮************************* */
  $('header li.fix').on('click',function(){
      $('.fix-shadow').css('display','block').on('click',function(){
        $(this).css('display','none');
      }).find('.window').on('click',function(e){
        e.stopPropagation();
      })
      $.get('/MutualAid/GetRepairCardUnfinished',function(data){
          let obj = JSON.parse(data);
          if(obj.status == '1402'){
            layer.msg('您尚未登陆');
          }else if(status == '1401'){
            let i = obj.data.num;
            for(let j = 0; j < i; j++){
                let fixObj = obj.data.fix1;
                $('.fix-shadow .left .bottom').html(`${fixObj.domc}`);
                $('.fix-shadow .middle .bottom').html(`${fixObj.content}`);
                $('.fix-shadow .right .bottom').html(`${fixObj.rank}`);
            }
          }
      })
  })

  /*******************************************这是登录按钮******************************/
$('.input-wrapper .button').on('click', function () {
  let loginId = $('.input-wrapper .top input').val();
  let loginPwd = $('.input-wrapper .bottom input').val();
  let regId = /^\d{9}$/;
  let regPwd = /^[^\s]+$/;
    if (regId.test(loginId)) {
        if (regPwd.test(loginPwd)) {
          let user = {
                stuId:loginId,
                password:loginPwd
            }
            console.log(JSON.stringify(user));
            $.ajax({
                url: '/MutualAid/UserLogin',
                status:'',
                data:JSON.stringify(user),
                dataType: 'json',
                method: 'POST',
                success: function (data) {
             
                    if(data['status'] == '1001'){
                        layer.msg('登录成功');
                        $('.shadow').hide();
                        windowUp();
                    }else if(data['status'] == '1002'){
                        layer.msg('账号或密码错误');
                    }else{
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
})
/**************************************这是注册按钮**********************************/
$('.register-wrapper .button').on('click', function () {
  let registerId = $('.register-wrapper .top input').val();
  let $input = $('.register-wrapper .bottom input');
  let registerPwd = $input.eq(0).val();
  let registerCheckPwd = $input.eq(1).val();
  let registerName = $input.eq(2).val();
  let registerTel = $input.eq(3).val();
  let registerCode = $input.eq(4).val();
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
                            data:JSON.stringify(user),
                            dataType: 'json',
                            method: 'POST',
                            success: function (data) {
                                if(data['status'] == '1101'){
                                    layer.msg('注册成功');
                                    $('.shadow').hide();
                                    windowUp();
                                }else if(data['status'] == '1102'){
                                    layer.msg('账号已经存在');
                                }else if(data['status'] == '1104'){
                                    layer.msg('验证码错误')
                                }else{
                                    layer.msg('服务器出错，注册无法完成')
                                }
                            },
                            error: function () {
                                alert('error');
                            }
                        })
                        //发送请求
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
})
/***************************************这是验证码按钮******************************** */
$('.code-img').on('click',function(){
    let img = $(this).find('img')
    let date = new Date().getTime();
    img.attr('src','/MutualAid/GetRegisterCode?='+date);
    console.log(img.attr('src'));
})
/***************************************这是保修时间的按钮************************ */
$('.application-shadow .fix-time').on('click',function(e){
    let $fixTime=$(this);
    let $show = $fixTime.find('.show');
    $fixTime.css('overflow','visible').find('.fix-time-wrap').css('overflow-y','scroll');
    $fixTime.find('li').css('display','block').parent().on('click',function(event){
        let $li = $(event.target);
        $li.attr('class','show').siblings().removeAttr('class','show')
    });
    e.stopPropagation();
    $fixTime.find('li').on('click',function(e){
        show();
    })
    $('.application-shadow .window').on('click',show);
    $('.application-shadow .time').on('click',function(){
        setTimeout(() => {
            show();
        }, 0);
    })
    function show(){
        $fixTime.css('overflow','hidden').find('.fix-time-wrap').css('overflow-y','hidden').find('li').css('display','none');
        $show.css('display','block');
    }
})
/********************************这是保修提交按钮************************************** */
$('.application-shadow .commit').on('click',function(){
    let $input = $('.application-shadow input');
    let $day = $('.application-shadow .day>li').index($('.application-shadow .day>li.show'));
    let $time = $('.application-shadow .time>li').index($('.application-shadow .time>li.show'))+1;
    let $num = $day*10 + $time;
    console.log($num)
    let $domc = $input.eq(0).val();
    let $name = $input.eq(1).val();
    let $tel = $input.eq(2).val();
    let $main = $input.eq(3).val();
    
    if($domc){
            if($name){
                if($tel){
                    if($main){
                        if($day > 0 && $time > 0){
                              submit();
                            $('.application-shadow').css('display','none');
                            $('.complete-shadow').css('display','block').on('click',function(){
                                $(this).css('display','none');
                              }).find('.window').on('click',function(e){
                                e.stopPropagation();
                              });
                        }else{
                            console.log($num)
                            layer.msg('请选择正确上门日期');
                        }
                    }else{
                        layer.msg('请填报内容');
                    }
                }else{
                    layer.msg('请填电话号码');
                }
            }else{
                layer.msg('请填写宿舍号');
            }
        }else{
            layer.msg('请填写宿舍号');
        }
    function submit(){
        let fix ={
            domc : $input.eq(0).val(),
            name : $input.eq(1).val(),
            tel: $input.eq(2).val(),
            content : $input.eq(3).val(),
            time : $num,
            date:new Date().getTime()+''
            
        }
        console.log(JSON.stringify(fix));
        $.ajax({
            url:'/MutualAid/WriteRepairCard',
            data:JSON.stringify(fix),
            dataType: 'json',
            method: 'POST',
            success: function (data) {
            	console.log(data);
                if(data['status'] == '1301'){
                    layer.msg('保修成功');
                }else{
                    layer.msg('服务器出错，注册无法完成')
                }
            },
            error: function () {
                alert('error');
            }
        })
    }
})
/**********************这是我的报修的创建新报修的按钮***************/
$('.fix-shadow .button').on('click',function(){
    $('.fix-shadow').css('display','none');
    $('.application-shadow').css('display','block').on('click',function(){
        $(this).css('display','none');
      }).find('.window').on('click',function(e){
        e.stopPropagation();
      });
})
/*************************这是返回上面的按钮************************* */
$('.application-shadow .return').on('click',function(){
    $('.application-shadow').css('display','none');
    $('.fix-shadow').css('display','block').on('click',function(){
        $(this).css('display','none');
      }).find('.window').on('click',function(e){
        e.stopPropagation();
      });
})
/************************这是提交完成后的按钮***************************** */
$('.complete-shadow .return').on('click',function(){
    $('.complete-shadow').css('display','none');
    $('.fix-shadow').css('display','block').on('click',function(){
        $(this).css('display','none');
      }).find('.window').on('click',function(e){
        e.stopPropagation();
      });
});
$('.complete-shadow .commit').on('click',function(){
    $('.complete-shadow').hide();
})
$('.complete-shadow').on('click',function(){
    $(this).hide();
})
$('body').on('click',()=>{
    setTimeout(() => {
      $('.msg').css('display','block').html('a');
        
    }, 10);
})
