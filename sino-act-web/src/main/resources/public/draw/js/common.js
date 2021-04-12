// 我的奖品点击关闭事件
$(".prizeDelect").click(function(){
    $('.myPrize').hide();
    // $(".myPrize").css({ "opacity":0,"height":'0px' });
    // $("body").removeClass("popupBox");

    // setTimeout(function(){
    // 	$(".myPrize").css({ "opacity":1,"height":'100%' });
    // },3000)
});
// 登记点击关闭事件
$(".regDelect").click(function(){
    $('.register').hide();
    // $(".register").css({ "opacity":0,"height":'0px' });
    // $("body").removeClass("popupBox");
    // setTimeout(function(){
    // 	$(".register").css({ "opacity":1,"height":'100%' });
    // },3000)
});
//继续抽奖
$(".popBtn1").click(function(){
    console.log('继续抽奖');
    $('.register').hide();
    // $(".register").css({ "opacity":0,"height":'0px' });
    // $("body").removeClass("popupBox");
    // setTimeout(function(){
    // 	$(".register").css({ "opacity":1,"height":'100%' });
    // },3000)
});
$(".popBtn2").click(function(){
    console.log('继续抽奖');
    $('.winPrize').hide();
    // $(".winPrize").css({ "opacity":0,"height":'0px' });
    // $("body").removeClass("popupBox");
    // setTimeout(function(){
    // 	$(".winPrize").css({ "opacity":1,"height":'100%' });
    // },3000)
});
$(".popBtn3").click(function(){
    // console.log('提交信息');
    // $('.goods').hide();
    // $(".goods").css({ "opacity":0,"height":'0px' });
    // $(".register").css({ "opacity":1,"height":'100%' });

    // setTimeout(function(){
    // 	$(".goods").css({ "opacity":1,"height":'100%' });
    // },3000)
});
// 中奖
$(".winDelect").click(function(){
    $('.winPrize').hide();
    // $(".winPrize").css({ "opacity":0,"height":'0px' });
    // $("body").removeClass("popupBox");
    // setTimeout(function(){
    // 	$(".winPrize").css({ "opacity":1,"height":'100%' });
    // },3000)
});
//
$(".goodsDelect").click(function(){
    $('.goods').hide();
    // $(".goods").css({ "opacity":0,"height":'0px' });
    // $("body").removeClass("popupBox");
    // setTimeout(function(){
    // 	$(".goods").css({ "opacity":1,"height":'100%' });
    // },3000)
});
//活动规则
$(".ruleDelect").click(function(){
    $('.rule').hide();
    // $(".rule").css({ "opacity":0,"height":'0px' });
    // $("body").removeClass("popupBox");
    // setTimeout(function(){
    // 	$(".rule").css({ "opacity":1,"height":'100%' });
    // },3000)
});