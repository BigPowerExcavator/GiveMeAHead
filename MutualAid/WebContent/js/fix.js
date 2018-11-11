/*********************************这是加载我的保修的按钮********************* */
$(
    $.ajax({
        url: '/MutualAid/GetNewestRepairCard',
        dataType: 'json',
        method: 'GET',
        success: function (data) {
            $('.fix-shadow .left .bottom').text(`${data.Docm}`);
            $('.fix-shadow .middle .bottom').text(`${data.content}`);
            $('.fix-shadow .right .bottom').text(`${data.ranking}`);
        },
        error: function () {
            $('.fix-shadow .left .bottom').text(`无`);
            $('.fix-shadow .middle .bottom').text(`无`);
            $('.fix-shadow .right .bottom').text(`无`);
        }
    })
)
/********************************z这是登出按钮************************************ */
$('header .exit-icon').on('click', function () {
    sessionStorage.setItem('status', 'no');
    window.location.href = "index.html";
})
/***************************************这是保修时间的按钮************************ */
$('.application-shadow .show').on('click', function (e) {
        $day = $(this).siblings();
        $day.slideToggle(500);
        e.stopPropagation();
        $day.on('click', function (e) {
            e.stopPropagation();
        })
        $(document).on('click', function () {
            $day.slideUp(700);
        })
        $(".application-shadow .day>li>div").on('click', slidedown)

        function slidedown(e) {
            $this = $(this);
            $this.unbind();
            let day$li = $(this).parent();
            $this.on('click', slideup);

            function slideup() {
                day$li.find(".time").slideUp(500);
                $this.unbind();
                $(".application-shadow .day>li>div").on('click', slidedown);
            }
            day$li.attr('id', "show");
            day$li.siblings().removeAttr('id').find('.time').slideUp(500);
            day$li.find(".time").slideDown(500).on('click', function (e) {
                let $target = $(e.target);
                $target.attr('id', 'show');
                $target.siblings().removeAttr('id');
                let time = $target.text();
                let day = $(this).siblings().text();
                $('.application-shadow .show').text(`${day}~${time}`);
                $day.slideUp(700);
            });
            e.stopPropagation();
            $(document).on('click', function () {
                $day.slideUp(700);
            })
        }
})

/********************************这是保修提交按钮************************************** */
$('.application-shadow .commit').on('click', function () {
    let $input = $('.application-shadow input');
    let $day = $('.application-shadow .day>li#show').index() + 1;
    let $time = $('.application-shadow .day>li#show .time li#show').index() + 1;
    let $num = ($day) * 10 + $time;
    let $domc = $input.eq(0).val();
    let $name = $input.eq(1).val();
    let $tel = $input.eq(2).val();
    let $main = $input.eq(3).val();

    if ($domc) {
        if ($name) {
            if ($tel) {
                if ($main) {
                    if ($day > 0 && $time > 0) {
                        $('.application-shadow').css('display', 'none');
                        $('.complete-shadow').css('display', 'block');
                        submit();
                    } else {
                        layer.msg('请选择正确上门日期');
                    }
                } else {
                    layer.msg('请填报内容');
                }
            } else {
                layer.msg('请填电话号码');
            }
        } else {
            layer.msg('请填写宿舍号');
        }
    } else {
        layer.msg('请填写宿舍号');
    }

    function submit() {
        let fix = {
            domc: $input.eq(0).val(),
            name: $input.eq(1).val(),
            tel: $input.eq(2).val(),
            content: $input.eq(3).val(),
            time: $num,
            date: new Date().getTime() + ''

        }
        console.log(JSON.stringify(fix));
        $.ajax({
            url: '/MutualAid/WriteRepairCard',
            data: JSON.stringify(fix),
            dataType: 'json',
            method: 'POST',
            success: function (data) {
                console.log(data);
                if (data['status'] == '1301') {
                    layer.msg('保修成功');
                } else {
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
$('.fix-shadow .button').on('click', function () {
    $('.fix-shadow').css('display', 'none');
    $('.application-shadow').css('display', 'block');
})
/*************************这是返回上面的按钮************************* */
$('.application-shadow .return').on('click', function () {
    $('.application-shadow').css('display', 'none');
    $('.fix-shadow').css('display', 'block');
})
/************************这是提交完成后的按钮***************************** */
$('.complete-shadow .return').on('click', function () {
    $('.complete-shadow').css('display', 'none');
    $('.fix-shadow').css('display', 'block');
});
$('.complete-shadow .commit').on('click', function () {
    $('.complete-shadow').hide();
})
$('body').on('click', () => {
    setTimeout(() => {
        $('.msg').css('display', 'block').html('a');

    }, 10);
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