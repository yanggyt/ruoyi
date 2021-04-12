var drawCode = getParameter('drawCode');
var prizeType = null;
var prizeCode = null;
function rtn() {
    let rtnBtn = getParameter('rtn');
    if (rtnBtn) {
        location.href = decodeURIComponent(rtnBtn);
    } else {
        history.back();
    }
}
function drawrule() {
    console.log('rule');
    $('.rule').show();
}
function tip(msg) {
    $('.tip_copy>p').text(msg);
    $('.tip_copy').show();
    setTimeout(function(){
        $('.tip_copy').hide();
    },1500);
}
function myprizes() {
    $('.popList').html('');
    $('.myPrize').show();
    $.ajax({
        type: "POST",
        url: contextRootPath+"/draw/prizes.action",
        data: {drawCode: drawCode},
        dataType: "json",
        success: function(data){
            if (!data.prizes) {
                return;
            }
            console.log(data.prizes);
            $.each(data.prizes, function (i, n) {
                var ptype = n.prizeType;
                var status = n.status;
                var iscenter = 0;
                var prize = $('.prize_li').clone();
                $(prize).css('display', 'inherit');
                prize.removeClass('prize_li');
                prize.find('.pname').html(n.prizeName);
                prize.find('.time').html(n.drawTime);
                $(prize).attr('val', n.prizeCode);
                $(prize).attr('flow', n.gatewayFlow);
                $(prize).attr('ptype', ptype);
                $('.popList').append(prize);
            });
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log('网络异常', textStatus, errorThrown);
        }
    });

}
$('.pop_height').css({
    'max-height': $(window).height() * 0.45 + 'px'
});
$(window).resize(function () {
    $('.pop_height').css({
        'max-height': $(window).height() * 0.45 + 'px'
    })
});
function readtext(obj) {
    var pcode = $(obj).parent().parent('li').attr('val');
    var text = prizeTexts.get(pcode);
    $(obj).parent().next('.read_text').html(text);
    $(obj).parent().next('i').toggle();
}
function readinfo(obj) {
    var ptype = $(obj).parent().parent('li').attr('ptype');
    var pcode = $(obj).parent().parent('li').attr('val');
    var flow = $(obj).parent().parent('li').attr('flow');
    var pname = $(obj).parent().parent('li').find('.pname').text();
    setPrizeInfo(ptype, pcode, pname, flow);
    $('.smak_prize').hide();
    $('.material').show();
}
function setPrizeInfo(ptype, pcode, cue, flow) {
    prizeCode = pcode;
    prizeType = ptype;
    $('.flow').val(flow);
    let src = $('img[pcode="'+pcode+'"]').attr('src');
    if (ptype == 'materialObject') {
        $('.goods').find('.cue').text(cue);
        $('.goods').find('.pimg').attr('src', src);
    } else if (ptype == 'pcoupon') {
        $('.winPrize').find('.cue').text(cue);
        $('.winPrize').find('.remark').text('电子码将以短信的形式发送到您的手机号上，请注意查收');
        $('.winPrize').find('.pimg').attr('src', src);
    } else {
        $('.winPrize').find('.cue').text(cue);
        $('.winPrize').find('.remark').text('将在2小时内发放至您的一账通绑定银行卡中，请注意查收');
        $('.winPrize').find('.pimg').attr('src', src);
    }
}
function prizes() {
    $.ajax({
        type: "POST",
        url: contextRootPath+"/draw/prizes.action",
        data: {drawCode: drawCode, isAll: 1},
        dataType: "json",
        success: function(data){
            var content = '';
            if (data.prizes) {
                $.each(data.prizes, function (i, n) {
                    if (n.mobile) {
                        content = content + ('<li>恭喜 '+n.mobile+' 获得'+n.prizeName+'</li>');
                    }
                });
            }
            $(".myscroll ul").html(content);
            $('.myscroll').myScroll({
                speed: 100, //数值越大，速度越慢
                rowHeight: 24//li的高度
            });
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log('网络异常', textStatus, errorThrown);
        }
    });

}
function saveAddr() {
    var uname = $('.uname').val();
    var phone = $('.phone').val();
    var addr = $('.addr').val();
    if ('integral' == prizeType) {
    } else if ('materialObject' == prizeType) {
        if (!uname) {
            tip('请输入收货人姓名');
            return;
        }
        if (!phone) {
            tip('请输入收货人手机号码');
            return;
        }
        if (!addr) {
            tip('请输入收货人地址');
            return;
        }
    } else {
    }
    var flow = $('.flow').val();
    uname = getEntryptPwd(uname);
    phone = getEntryptPwd(phone);
    $.ajax({
        type: "POST",
        url: contextRootPath+"/draw/saveAddress.action",
        data: {drawCode: drawCode, flow:flow, uname: uname, phone: phone, addr: addr},
        dataType: "json",
        success: function(data){
            if (data.respCode == '1') {
                if ('integral' == prizeType) {
                } else if ('materialObject' == prizeType) {
                } else {
                }
                $('.goods').hide();
                $('.register').show();
            } else {
                tip(data.respMsg);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log('网络异常', textStatus, errorThrown);
        }
    });

}