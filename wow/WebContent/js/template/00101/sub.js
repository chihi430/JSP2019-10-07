//sns
$(function () {
    $('.snsBox button.btnShare').click(function() {
        $(this).next().slideToggle(300);
    });

});

// document ready
$(function(){
	listsList();
	faqList();
	LNB();
});

// 리스트
function listsList(){
	$('.listDetail .view > a').off('click');
	$('.listDetail .view > a').on('click' , function(){
		var title = $(this).parent('.view');
		if (title.hasClass('on')){
			$(".listDetail .view").removeClass("on");
		} else {
			title.addClass("on");
			$(".listDetail .view").not(title).removeClass("on");
		}
	});
}
// 자주묻는질문(FAQ)
function faqList(){
	$('.faqDetail .faq > a').off('click');
	$('.faqDetail .faq > a').on('click' , function(){
		var title = $(this).parent('.faq');
		if (title.hasClass('on')){
			$(".faqDetail .faq").removeClass("on");
		} else {
			title.addClass("on");
			$(".faqDetail .faq").not(title).removeClass("on");
		}
	});
}

// 왼쪽메뉴 190128 수정
function LNB() {
	var $lnb = $(".lnb");
	var lnbLink = $('.lnb a');
	var current = $.trim($('.location strong').text());
	var paCurrent = $.trim($('.location .navi_0').text());

	if ($lnb.size() > 0) {
		
		lnbLink.each(function() {
			if ( $(this).text() == current ) {
            	$lnb.find(">li.on").removeClass('on');
                $(this).parent().addClass('on');

                if ( $(this).parent().parent().hasClass('subLnb')) {
                    $(this).parents('.subLnb').parent().addClass('on').children("ul").slideDown(0);;
                }
                
            } else if( $(this).text() == paCurrent ) {
            	$lnb.find(">li.on").removeClass('on');
            	$(this).parent().addClass('on');

                if ( $(this).parent().parent().hasClass('subLnb')) {
                    $(this).parents('.subLnb').parent().addClass('on').children("ul").slideDown(0);;
                }
            }
        });

		var $on = $lnb.find(">li.on");
		var idx = $on.index();

		if ($(window).width() <= 1002) {
			$('#menu nav').show().delay(1000).slideUp(300, function() {
				$(this).removeAttr('style');
			});
		}

		$('#menu h2').click(function() {
			if ($(window).width() <= 1002) {
				$(this).toggleClass('on').next().slideToggle(300);
			}
		});

		$lnb.find(">li>a").bind(
				'click',
				function() {
					if ($(this).siblings("ul").size() > 0) {
						$(this).next('ul').slideToggle(300).parent().addClass(
								"on").siblings("li").removeClass("");
						return false;
					}
				});

		if (idx >= 0) {
			$('#menu').mouseleave(
					function() {
						if ($(window).width() > 800) {
							$('.lnb').children('li').eq(idx).addClass('on')
									.children('ul').slideDown(500).parent()
									.siblings('li').removeClass('on');
						}
					});
		}

		$('.lnb').blur(
				function() {
					if ($(window).width() > 1002) {
						$('.lnb').children('li').eq(idx).addClass('on')
								.children('ul').slideDown(300).parent()
								.siblings('li').removeClass('on')
								.children('ul').slideUp(300);
					}
				});
	}
}

// 왼쪽메뉴 : 하위메뉴 있는 메뉴만 아이콘 표시
$(function () {
	function LNBcheck() {
		$("#menu ul.lnb li ul.subLnb").parent("li").addClass('row');
		$("#menu ul.lnb > li > a").click(function () {
			$(this).parent().toggleClass("on");
			$(this).parent().addClass("on");
			$(this).parent().siblings().removeClass('on');
			//return false;	
		});
	}	
	LNBcheck();
});