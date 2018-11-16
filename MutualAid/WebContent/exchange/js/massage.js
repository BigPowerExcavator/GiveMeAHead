let name = window.location.href.split("\?")[1];
$(
    $.getJSON('/MutualAid/GetGoodCardInfo', {"formId":name} ,function (data) {
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
        //     img2: '../img/bg.jpg',
        //     img3: '../img/bg.jpg',
        //     time: '2018-22-2'
        // }
        $('.title').html(data.title);
        $('.price').html(data.price);
        $('.type').html(data.type);
        $('.tel').html(data.tel);
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

    })

)