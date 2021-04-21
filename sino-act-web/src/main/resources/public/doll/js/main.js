let drawCode = getParameter('drawCode');
let cl = getParameter('cl');
let uid = getParameter('uid');
let running = false;
let body;
//剩余次数或积分
function init(facade){
  $.ajax({
    type:'POST',
    url:contextRootPath+'/integral/active/init.do',
    data: {"drawCode": drawCode, "uid":uid, 'facade':facade},
    dataType:'json',
    success:function(data){
      let code = data.respCode;
      let msg = data.respMsg;
      if (code !== '1') {
        layerTip(msg);
        return;
      }
      let drawType = data.drawType;
      $('.drawType').val(drawType);
      $('.drawRule').html(data.drawRule);
      $('.integral').html(data.integral);
      num(drawType);
      let prizes = data.prizes;
      $.each(prizes, function (i, n) {
        let prizeCode = n.prizeCode;
        let prizeName = n.prizeName;
        let prizeImg = n.prizeImg;
        let prize = $('.prize').clone();
        prize.show();
        prize.removeClass('prize');
        prize.attr('p-code', prizeCode);
        prize.attr('p-name', prizeName);
        prize.attr('src', contextRootPath+prizeImg);
        $('.prizes').append(prize);
      });
    },
    error:function(XMLHttpRequest, textStatus, errorThrown){
      console.log(textStatus);
      layerTip('网络异常，请检查网络环境');
    }
  });
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
function initAwards(){
  $.ajax({
    type:'POST',
    url:contextRootPath+'/draw/prizes.action',
    data: {"drawCode": drawCode, isAll: 1},
    dataType:'json',
    success:function(data){
      let awards = data.record;
      if (!awards) {
        return;
      }
      $.each(awards, function (i, n) {
        let userName = n.username;
        let prizeName = n.prizename;
        let dateStr = n.createtimestamp;
        let award = $('.award').clone();
        award.show();
        award.removeClass('award');
        award.html('恭喜'+userName+'获得'+prizeName);
        $('.awards').append(award);
      });
      //中奖名单滚动
      $('.my_scroll').myScroll({
        speed: 100, //数值越大，速度越慢
        rowHeight: 38//li的高度
      });
    },
    error:function(XMLHttpRequest, textStatus, errorThrown){
      console.log(textStatus);
      layerTip('网络异常，请检查网络环境');
    }
  });
}
function myprizes(facade) {
  $('.myPrizes').html('');
  $('.pop_prize').show();
  $.ajax({
    type: "POST",
    url: contextRootPath+'/draw/prizes.action',
    data: {drawCode: drawCode, facade: facade},
    dataType: "json",
    success: function(data){
      let prizes = data.record;
      if (!prizes) {
        return;
      }
      $.each(prizes, function (i, n) {
        let ptype = n.prizetype;
        let status = n.status;
        let prizeCode = n.prizecode;
        let prize = $('.prize_li').clone();
        prize.show();
        prize.removeClass('prize_li');
        prize.find('.pname').html(n.prizename);
        prize.find('.time').html(n.createtimestamp);
        let imgSrc = $('img[p-code=' + prizeCode + ']').attr('src');
        prize.find("img").attr('src', imgSrc);
        let link = n.link;
        if (link) {
          prize.on('click', function () {
            location.href = link;
          });
        }
        $(prize).attr('val', prizeCode);
        $(prize).attr('flow', n.drawtranseqno);
        $(prize).attr('ptype', ptype);
        $('.myPrizes').append(prize);
      });
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
      console.log('网络异常', textStatus, errorThrown);
    }
  });

}
function saveAddr(type) {
  let uname = $('.uname').val();
  let idcard = $('.idcard').val();
  let phone = $('.phone').val();
  let city = $('.city').val();
  let addr = $('.addr').val();
  let flow = body.gatewayFolw;
  let prizeType = body.prizeType;
  if ('integral' == prizeType) {
  } else if ('materialObject' == prizeType) {
    if (!uname) {
      layerTip('请输入收货人姓名');
      return;
    }
    if (!idcard) {
      layerTip('请输入身份证号');
      return;
    }
    if (!phone) {
      layerTip('请输入联系电话');
      return;
    }
    if (!city) {
      layerTip('请选择所在地区');
      return;
    }
    if (!addr) {
      layerTip('请输入详细地址');
      return;
    }
  } else {
  }
  uname = getEntryptPwd(uname);
  idcard = getEntryptPwd(idcard);
  phone = getEntryptPwd(phone);
  $.ajax({
    type: "POST",
    url: contextRootPath+'/integral/active/saveUserAddress.do',
    data: {drawCode: drawCode, gatewayFlow:flow, "userName":uname,"phone":phone,"city":city,"address":addr,"type":type,"IDNumber":idcard},
    dataType: "json",
    success: function(data){
      let code = data.code;
      let msg = data.msg;
      if (code != '0') {
        layerTip(msg);
      }
      if ('integral' == prizeType) {
      } else if ('materialObject' == prizeType) {
      } else {
      }
      $('.pop_fillin').hide();
      layerTip('保存成功')
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
      console.log('网络异常', textStatus, errorThrown);
      layerTip('网络异常，请检查网络环境');
    }
  });

}
function start() {
  if (running) {
    console.log('running...');
    return;
  }
  running = true;
  $.ajax({
    type:'POST',
    async:false,
    url:contextRootPath+'/integral/active/getReward.do',
    data: {"drawCode": drawCode,"cl":cl},
    dataType:'json',
    success:function(data){
      let code = data.code;
      let msg = data.msg;
      if (code == '1') {
        layerTip(msg);
        to_login(cl);
        running = false;
        return;
      } else if (code == '-1') {
        running = false;
        layerTip('请先完善手机号');
        return;
      }
      if (code != '0') {
        running = false;
        layerTip(msg);
        return;
      }
      body = data.body;
      $(".num").html(body.available);
    },
    error:function(XMLHttpRequest, textStatus, errorThrown){
      running = false;
      console.log(textStatus);
      layerTip('网络异常，请检查网络环境');
    }
  });
}
//剩余次数或积分
function num(drawType){
  // let drawType = $('.drawType').val();
  $.ajax({
    type:'POST',
    url: contextRootPath+'/integral/active/getDrawAvailableNumber.do',
    data: {"drawCode": drawCode,"drawType":drawType},
    dataType:'json',
    success:function(data){
      if(data.code=="0"){
        $(".num").html(data.number);
      }
    },
    error:function(XMLHttpRequest, textStatus, errorThrown){
      console.log(textStatus);
      layerTip('网络异常，请检查网络环境');
    }
  });

}