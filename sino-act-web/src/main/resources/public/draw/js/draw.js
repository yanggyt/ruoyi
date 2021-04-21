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
    console.log(drawCode);
    $.ajax({
        type: "POST",
        url: contextRootPath+"/draw/describe",
        data: {actCode: drawCode},
        dataType: "json",
        success: function(data){
            var desc = removeHTMLTag(data.drawRule.drawdescribe);
            var desc1 = escape2Html(desc);
            console.log(desc1);
            $(".contBox").append(desc1);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log('网络异常', textStatus, errorThrown);
        }
    });
    $('.rule').show();
}
//转义替换
/*移除HTML标签代码*/
function removeHTMLTag(str) {
    str = str.replace(/<\/?[^>]*>/g,''); //去除HTML tag
    str = str.replace(/[ | ]*\n/g,'\n'); //去除行尾空白
    //str = str.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
    //str=str.replace(/ /ig,'');//去掉
    return str;
}
function escape2Html(str) {
    var arrEntities={'lt':'<','gt':'>','nbsp':' ','amp':'&','quot':'"'};
    return str.replace(/&(|lt|gt|nbsp|amp|quot);/ig,function(all,t){return arrEntities[t];});
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
            if (!data.record) {
                return;
            }
            console.log(data.record);
            $.each(data.record, function (i, n) {
                var ptype = n.prizetype;
                var status = n.status;
                var iscenter = 0;
                var record = $('.prize_li').clone();
                $(record).css('display', 'inherit');
                record.removeClass('prize_li');
                record.find('.pname').html(n.prizename);
                record.find('.time').html(n.createtimestamp);
                $(record).attr('val', n.prizecode);
                $(record).attr('flow', n.drawtranseqno);
                $(record).attr('ptype', n.prizetype);
                $('.popList').append(record);
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
            if (data.record) {
                $.each(data.record, function (i, n) {
                    if (n.phone) {
                        content = content + ('<li>恭喜 '+n.phone+' 获得'+n.prizename+'</li>');
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
    var boolean = $("#protocol").prop("checked");
    if (!boolean){
        tip('请勾选协议');
        return ;
    }

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
  /*  uname = getEntryptPwd(uname);
    phone = getEntryptPwd(phone);*/
    var data = $("#addressId").serialize();
    $.ajax({
        url: contextRootPath+"/draw/saveAddress",
        dataType: "json",
        type: "post",
        data:  data,
        success: function(data){
            if (data.respCode == '1') {
                if ('integral' == prizeType) {

                } else if ('materialObject' == prizeType) {

                } else {

                }
                $('.register').show();
            } else {
                $('.register').show();
                tip(data.respMsg);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log('网络异常', textStatus, errorThrown);
        }
    });

}