$(function() {
    new WOW().init();

    // 滚动条
    $(".party_pcont").mCustomScrollbar({
        theme: "dark-thin",
        advanced: {
            autoExpandHorizontalScroll: true
        }
    });
    // 导航
    $(window).scroll(function() {
        if ($(window).scrollTop() > 0) {
            $('.header').addClass('tophide');
        } else {
            $('.header').removeClass('tophide');
        }
    });

});
//头部搜索
var _con = $('.search_bj');
var _con2 = $('.schear');
_con2.click(function(event) {
    if ($(this).hasClass('cur')) {
        _con.slideUp(400);
        $(this).removeClass('cur');
    } else {
        $(this).addClass('cur');
        _con.slideDown(400);
    }
});

// banner

var swiper_banner = new Swiper('.swiper_banner', {
    spaceBetween: 0,
    centeredSlides: false,
    loop: true,
    speed: 800,
    autoplay: {
        delay: 5000,
        disableOnInteraction: false,
    },
    pagination: {
        el: '.swiper-pagination',
        // type: 'fraction',
        clickable: true,
    },
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },

    paginationClickable: true,
    observer: true,
    observeParents: true,
});
// 律师团队

var lstd_swipet = new Swiper('.lstd_swipet', {
    slidesPerView: 2,
    slidesPerColumn: 2,
    spaceBetween: 30,
    slidesPerColumnFill: 'row',

    pagination: {
        el: '.swiper-pagination',
        clickable: true,
    },
    breakpoints: {
        //当宽度大于等于640
        1600: {
            spaceBetween: 30,

        },
        //当宽度大于等于640
        1602: {
            spaceBetween: 40,

        },


    },
    navigation: {
        nextEl: '.lsd_prev',
        prevEl: '.lsd_next',
    },
    paginationClickable: true,
    observer: true,
    observeParents: true,
});
// 合同模板
var htmb_swiper = new Swiper('.htmb_swiper', {
    slidesPerView: 5,
    spaceBetween: 30,
    navigation: {
        nextEl: '.htmb_prev',
        prevEl: '.htmb_next',
    },
    paginationClickable: true,
    observer: true,
    observeParents: true,
});

// 视频

$(" .shipin_xs").click(function() {
    var _thisData = $(this).data("vdosrc");
    $("#vdoBiao").show();
    $(".vdoBg").addClass("vis");
    $(".vdoBox").addClass("vis");
    $("#vdoBiao").attr({
        src: _thisData
    })
    $("#vdoBiao").get(0).play();
})

$(".xskc_r .shipin_xs").click(function() {
    var _thisData = $(this).data("vdosrc");
    $("#vdoBiao").show();
    $(".vdoBg").addClass("vis");
    $(".vdoBox").addClass("vis");
    $("#vdoBiao").attr({
        src: _thisData
    })
    $("#vdoBiao").get(0).play();
})

$(".vdoBox .tanXx").click(function() {
        $(".vdoBg").removeClass("vis");
        $(".vdoBox").removeClass("vis");
        $("#vdoBiao").attr({
            src: ""
        })
        $("#vdoBiao").hide();
        $("#vdoBiao").get(0).pause();
    })
    // 返回顶部
function initScrollToTop() {
    $("html,body").animate({ scrollTop: 0 }, 1000);
}
// 滚动条
// $(".ckilc").click(function() {
//     $(".zzc").show();
//     $(".ystk").show();
//     setTimeout(function() {
//         $(".party_pcont").mCustomScrollbar({
//             theme: "dark-thin",
//             advanced: {
//                 autoExpandHorizontalScroll: true
//             }
//         }, 100);
//     })
// })
// $(".colock").click(function() {
//     $(".zzc").hide();
//     $(".ystk").hide();
//     $(".party_pcont").mCustomScrollbar("destroy");
// })
// 底部表单
$('.layui-form .inp1').focus(function() {
    $(this).parent(".item").addClass("on");
});
$('.layui-form  .inp1').blur(function() {
    if ($(this).val() == '') {
        $(this).parent('.item').removeClass('on');
    }
});

// C1律师团队

var Ctow_swipet = new Swiper('.Ctow_swipet', {
    slidesPerView: 3,

    spaceBetween: 30,
    loop: false,
    pagination: {
        el: '.swiper-pagination',
        clickable: true,
    },

    pagination: {
        el: '.pag_ld_swiper .swiper-pagination',
        clickable: true,
    },
    navigation: {
        nextEl: '.ctow_next',
        prevEl: '.ctow_prev',
    },
    paginationClickable: true,
    observer: true,
    observeParents: true,
});

// D1

var submenu_siper = new Swiper('.submenu_siper', {
    slidesPerView: 8,
    spaceBetween: 70,

    navigation: {
        nextEl: '.menu-button-next',
        prevEl: '.menu-button-prev',
    },
});

$(document).ready(function() {
    $(".submenu").each(function() {
        var _thisIdx = $(this).find(".swiper-slide.active").index();
        submenu_siper.slideTo(_thisIdx, 0, false);
    });
});

// 常见问题

var cjwt_swipet = new Swiper('.cjwt_swipet', {
    slidesPerView: 2,
    spaceBetween: -1,
    pagination: {
        el: '.swiper-pagination.cjwt_ld_oag',
        clickable: true,
    },

    navigation: {
        nextEl: '.cjwt_prev',
        prevEl: '.cjwt_next',
    },
    paginationClickable: true,
    observer: true,
    observeParents: true,
});