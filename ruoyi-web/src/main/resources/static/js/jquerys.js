//表单输入
function form_label() {
    $(".inp").on("input", function() {
        var v = $(this).find('textarea');
        var vN = $(this).find('textarea').val();
        v.focus(function() {
            v.siblings('label').css("display", "none");
        });
        v.blur(function() {
            if (vN == null || vN == "") {
                v.siblings('label').css("display", "block");
            } else {
                v.siblings('label').css("display", "none");
            }
        });
    }).trigger("input");
    $(".inp").on("input", function() {
        var v = $(this).find('input');
        var vN = $(this).find('input').val();
        v.focus(function() {
            v.siblings('label').css("display", "none");
        });
        v.blur(function() {
            if (vN == null || vN == "") {
                v.siblings('label').css("display", "block");
            } else {
                v.siblings('label').css("display", "none");
            }
        });
    }).trigger("input");
    $(".inp textarea").each(function() {
        var vN = $(this).val();
        if (vN == null || vN == "") {
            $(this).siblings('label').css("display", "block");
        } else {
            $(this).siblings('label').css("display", "none");
        }
    });
    $(".inp input").each(function() {
        var vN = $(this).val();
        if (vN == null || vN == "") {
            $(this).siblings('label').css("display", "block");
        } else {
            $(this).siblings('label').css("display", "none");
        }
    })
}

// 单选选中按钮样式
function inp_radio() {
    $(".inp_radio").each(function() {
        var _this = $(this);
        if (_this.is(":checked")) {
            _this.parent('.fmbut_radio').addClass("active");
        } else {
            _this.parent('.fmbut_radio').removeClass("active");
        }
    })
    $(".inp_radio").click(function() {
        $(".inp_radio").each(function() {
            var _this = $(this);
            if (_this.is(":checked")) {
                _this.parent('.fmbut_radio').addClass("active");
            } else {
                _this.parent('.fmbut_radio').removeClass("active");
            }
        })
    })
}

// 复选选中按钮样式
function inp_checkbox() {
    $(".inp_checkbox").each(function() {
        var _this = $(this);
        if (_this.is(":checked")) {
            _this.parent('.fmbut_checkbox').addClass("active");
        } else {
            _this.parent('.fmbut_checkbox').removeClass("active");
        }
    })
    $(".inp_checkbox").click(function() {
        $(".inp_checkbox").each(function() {
            var _this = $(this);
            if (_this.is(":checked")) {
                _this.parent('.fmbut_checkbox').addClass("active");
            } else {
                _this.parent('.fmbut_checkbox').removeClass("active");
            }
        })
    })
}
//头部搜索
var _con = $('.search_bj ');
var _con2 = $('.seach_btn ');
_con2.click(function(event) {
    if ($(this).hasClass('cur')) {
        _con.slideUp(400);
        $(this).removeClass('cur');
    } else {
        $(this).addClass('cur');
        _con.slideDown(400);
    }
});


//导航按钮
function nav_but() {
    $('.navToggle').click(function() {
        if ($(".nav").is(":hidden")) {
            $(this).addClass("active");
            $(".nav").slideDown();
        } else {
            $(this).removeClass("active");
            $(".nav").slideUp();
        }
    });
}

// banner 视频
function ban_video() {
    var changeHeight = $(window).height()
    var changeWidth = $(window).width();
    $(".ban_video").height(changeHeight);
    if (changeHeight / changeWidth < 0.416) {
        $(".ban_video").css({ "width": changeWidth, "height": changeWidth * 0.416 });
    } else if (changeHeight / changeWidth > 0.416) {
        $(".ban_video").css({ "width": changeHeight / 0.416, "height": changeHeight });
    }
}

// 返回顶部
function initScrollToTop() {
    $("html,body").animate({ scrollTop: 0 }, 1000);
}

// 锚点平滑跳转
function a_mao() {
    $('a[href*=#],area[href*=#]').click(function() {
        if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
            var $target = $(this.hash);
            $target = $target.length && $target || $('[name=' + this.hash.slice(1) + ']');
            if ($target.length) {
                var targetOffset = $target.offset().top;
                $('html,body').animate({
                        scrollTop: targetOffset
                    },
                    1000);
                return false;
            }
        }
    });
}

// 页面滚动
$(window).scroll(function() {
    var hHeight = $(window).height();
    var top = $(window).scrollTop();
    if (top > hHeight) {

    } else {

    }
});

function winscroll() {
    var top = $(window).scrollTop();
    var navboxtop = $("div").offset().top;
    var hHeight = top / navboxtop;
    $('.ind_cont').css('background', 'rgba(255,255,255,' + hHeight + ')');
}

// DOM加载
$(function() {

    // tab切换
    function tab(tab_list, tab_div, tab_on) {
        $(tab_list).on(tab_on, function() {
            $(this).addClass('active').siblings().removeClass('active');
            $(tab_div).eq($(this).index()).addClass('vis').siblings().removeClass('vis');
        })
    }
    tab("", "", "click");

})

// 导航

$('.nav>ul>li').mouseenter(function() {
    $(this).find('._lev2').stop(0, 0).slideDown('fast');
}).mouseleave(function() {
    $(this).find('._lev2').stop(0, 0).slideUp('fast');
});

$(function() {
    new WOW().init();
    $(window).scroll(function() {
        if ($(window).scrollTop() > 0) {
            $('.pageheader').addClass('tophide');
        } else {
            $('.pageheader').removeClass('tophide');
        }
    });

});
/* 导航条 */
$(function() {

    //点击逐渐展开移动端导航
    $(".a_js").click(
        function() {
            $(".m_nav").stop(true, false).delay(0).animate({
                width: "100%",
                height: "100%"
            }, 0);
            $(".m_nav").find(".closecover").stop(true, false).delay(0).animate({
                opacity: "0.5"
            }, 300);
            $(".m_nav").find(".closeicon").stop(true, false).delay(0).animate({
                opacity: "1"
            }, 300);
            $(".m_nav").find(".m_navList").stop(true, false).delay(0).animate({
                left: "0"
            }, 300);
            $('body').css('overflow', 'hidden');
        }
    )

    //点击关闭，逐渐隐藏
    $(".a_closed").click(
        function() {
            $(".m_nav").stop(true, false).delay(300).animate({
                width: "0",
                height: "0"
            }, 0);
            $(".m_nav").find(".closecover").stop(true, false).delay(0).animate({
                opacity: "0"
            }, 300);
            $(".m_nav").find(".closeicon").stop(true, false).delay(0).animate({
                opacity: "0"
            }, 300);
            $(".m_nav").find(".m_navList").stop(true, false).delay(0).animate({
                left: "-80%"
            }, 300);
            $('body').css('overflow', 'auto');
        }
    )

    //判断是否有子标题
    $('.m_navList ul li').each(function() {
        $(this).children('.m_f_a').find('i').addClass('icon')
        if ($(this).children('.m_s_nav').find('a').length < 1) {
            $(this).children('.m_f_a').children('i').removeClass('icon');
        }
    });

    //点击图标展开关闭子导航
    $('.m_navList ul li').find('.m_f_a i').click(function() {
        $(this).parent().parent().siblings().children('.m_s_nav').slideUp();
        $(this).parent().parent().siblings().children('.m_f_a').find('i').removeClass('icon_on')
        $(this).parent().next().toggle("normal").prev().children('i').toggleClass('icon_on');
    })

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
// 滚动条
$(function() {
    $(".party_pcont").mCustomScrollbar({
        theme: "dark-thin",
        advanced: {
            autoExpandHorizontalScroll: true
        }
    });

});

// 技术优势
var jsys_swiper = new Swiper('.jsys_swiper', {
    autoplay: {
        delay: 5000,
        disableOnInteraction: false,
    },
    speed: 700,
    allowTouchMove: false,
    lazy: {
        loadPrevNext: true,
        loadPrevNextAmount: 3,
    },
    centeredSlides: true,
    spaceBetween: 40,

    loop: true,
    slidesPerView: 'auto',

    navigation: {
        nextEl: '.jsys_swiper-prev',
        prevEl: '.jsys_swiper-next',
    },
    breakpoints: {
        //当宽度大于等于320
        320: {
            spaceBetween: 5,

        },
        //当宽度大于等于480
        768: {
            spaceBetween: 10,


        },
        //当宽度大于等于640
        1024: {
            spaceBetween: 20,

        },

    },
    paginationClickable: true,
    observer: true,
    observeParents: true,
});
// window.onresize = function() {
//     jsys_swiper.update();
// }

var tjal_swiper = new Swiper('.tjal_swiper', {
    autoplay: {
        delay: 5000,
        disableOnInteraction: false,
    },
    speed: 700,

    spaceBetween: 40,

    loop: true,
    slidesPerView: 3,

    navigation: {
        nextEl: '.tjal_swiper-prev',
        prevEl: '.tjal_swiper-next',
    },
    breakpoints: {
        //当宽度大于等于320
        320: {
            spaceBetween: 5,
            slidesPerView: 1.5,

        },
        //当宽度大于等于480
        768: {
            spaceBetween: 10,


        },
        //当宽度大于等于640
        1024: {
            spaceBetween: 20,

        },
        1280: {
            spaceBetween: 40,

        },

    },
});