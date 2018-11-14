/*************************这是选择分类标签******************************** */
$(".body .tag-nav").on('click',function(e){
    let $target = $(e.target);
    if(e.target.tagName == 'LI'){
        $target.addClass('clicked').siblings().removeAttr('class');
    }
})
/******************* 这是选择时间价格优先的按钮***************************/
$(".body .shop-nav").on('click',function(e){
    let $target = $(e.target);
    if(e.target.tagName == 'DIV'){
        $target.addClass('clicked').siblings().removeAttr('class');
    }
})
/***********************这是左侧固定*********************************** */
{
    let $info = $(".body .left")
	const top = $info.offset().top;
	$(document).scroll(function () {
		if ($(this).scrollTop() > top) {
			if (!$info.attr('id')) {
				$info.attr('id', "top");
			}
		}
		else if ($(this).scrollTop() < top) {
			if ($info.attr('id')) {
				$info.removeAttr('id', "top");
			}
		}
	});
}