/*************************页面加载完成就加载一次*********************** */
$(getCard());
/*************************这是选择分类标签******************************** */
$(".body .tag-nav").on('click',function(e){
    let $target = $(e.target);
    if(e.target.tagName == 'LI'){
        $target.addClass('clicked').siblings().removeAttr('class');
	}
	getCard();
})
/***************************这是排序标签*********************** */
$(" .body .sort-nav").on('click',function(e){
    let $target = $(e.target);
    if(e.target.tagName == 'LI'){
		$target.addClass('clicked').siblings().removeAttr('class');
		$(".body .shop-nav").children().eq($target.index()).addClass('clicked').siblings().removeAttr('class');
	}
	getCard();
})
/******************* 这是选择时间价格优先的按钮***************************/
$(".body .shop-nav").on('click',function(e){
    let $target = $(e.target);
    if(e.target.tagName == 'DIV'){
		$target.addClass('clicked').siblings().removeClass('clicked');
		$(" .body .sort-nav").children().eq($target.index()).addClass('clicked').siblings().removeClass('clicked');
	}
	getCard();
})
/***********************这是左侧固定*********************************** */
{
	let $info = $(".body .left")
	let $sort = $(" .body .sort-nav li");
	const top = $info.offset().top;
	$(document).scroll(function () {
		if ($(this).scrollTop() > top) {
			if (!$info.attr('id')) {
				$info.attr('id', "top");
			}
			$sort.removeClass('hide');
		}
		else if ($(this).scrollTop() < top) {
			if ($info.attr('id')) {
				$info.removeAttr('id', "top");
				$sort.addClass('hide');
			}
		}
	});
}
//*********************** */ 这是发送请求的方法*****************************/
function getCard (){
	let type = $(".body .tag-nav li.clicked").index()+"";
	let sort = $(".body .sort-nav li.clicked").index()+"";
	switch(sort){
		case "0" :
			sort = "timeUp";
			break;
		case "1" :
			sort = "timeDown";
			break;
		case "2" :
			sort = "priceUp";
			break;
		case "3" :
			sort = "priceDown";
			break;

	}
	//showCard(data);
	console.log({"type":type,"sort":sort})
	$.getJSON("/MutualAid/GetGoodsCards",{"type":type,"sort":sort},function(data){
		showCard(data);
	})
}
/***************************这是每次点击加载卡片的方法 **********************/
function showCard(data){
	$('.right .shop').html('');
	for (let i = 1; i <= data['count']; i++) {
		let card = 'card' + i;
		let obj = data[card];
		let {
		"goodsImg":goodsImg,
        "stuNum": stuNum,
        "goodsId": goodsId,
        "goodsIntro":goodsIntro,
        "goodsPrice": goodsPrice,
        "time":time,
        "title": title,
        "userName":userName,
        "goodsName":goodsName,
        "goodsType":goodsType
		} = obj;
		let text = `<li><div class="pic"><img src="${goodsImg}" alt="这是物品帅照"></div><div class="title">${title}</div><div class="bottom"><div class="name">用户：${userName}</div><div class="price">RMB：${goodsPrice}</div></div></li>`
		$('.right .shop').append(text);
	}
}