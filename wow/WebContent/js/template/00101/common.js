
/* ///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	Gnb Function	

/////////////////////////////////////////////////////////////////////////////////////////////////////////// */
$(document).ready(function() {
	js_gnb ();
});
function js_gnb(){
	var dtxt01 = $(".depth01").text();
	var dtxt02 = $(".depth02").text();
	var dtxt03 = $(".depth03").text();
	var dtxt04 = $("#contents .path strong").text();
	
	var gnb_obj = $("#nav > #gnb > ul");
		gnb_obj.intervals = "";
		gnb_obj.li = gnb_obj.find(">li"); 
		gnb_obj.li.a = gnb_obj.li.find(">a");
		gnb_obj.li.ul = gnb_obj.li.find(">ul");
		gnb_obj.li.ul.li = gnb_obj.li.ul.find(">li"); 
		gnb_obj.li.ul.li.a = gnb_obj.li.ul.li.find(">a");
		gnb_obj.h = $("#header #nav"); 
		gnb_obj.blind = $("#nav > #blind"); 
		
	//default
	gnb_obj.li.each(function(e){
		$(this).addClass("num"+(e+1));	
	});
	setTimeout(function(){gnb_def();},100);
	
	gnb_obj.mouseenter(function(){
		clearTimeout(gnb_obj.intervals);
	});	
	
	gnb_obj.mouseleave(function(){
		gnb_obj.intervals = setTimeout(function(){
			gnb_def(gnb_obj);
		},300);
	});
		
	gnb_obj.li.a.on("mouseover focus",function(){
		var idx = gnb_obj.li.index($(this).parent());
		gnb_open(idx);
		// 메뉴 마우스 오버 시, 검색영역 닫기
		$('#header .search').hide();
	});
	
	gnb_obj.li.ul.mouseenter(function(){
		gnb_obj.li.find(">a.ov").removeClass("ov");
		$(this).siblings("a").addClass("ov");
		gnb_obj.li.ul.not($(this)).removeClass("ov");
		$(this).addClass("ov");
	});
	
	gnb_obj.li.ul.li.a.on("mouseover focus",function(){
		gnb_obj.li.find(">a.ov").removeClass("ov");
		$(this).parent().parent().siblings("a").addClass("ov");
		gnb_obj.li.find(">ul.ov").removeClass("ov");
		$(this).parent().parent().addClass("ov");
	});	
	
	gnb_obj.li.eq(4).find(">ul>li").last().find(">a").on("focusout",function(){
		gnb_obj.intervals = setTimeout(function(){
			gnb_def(gnb_obj);
		},300);
	});
	
	function gnb_def(){
		gnb_obj.li.find("a").removeClass("ov");
		if(dtxt01.length!=0){
			gnb_obj.li.a.removeClass("ov");
			gnb_obj.li.a.each(function(){
				var dt = $(this).text();
				if(dt==dtxt01){
					$(this).addClass("ov");
				}
			});
			if(dtxt02.length!=0){
				gnb_obj.li.ul.li.a.find(">strong").each(function(){
					var dt = $(this).text();
					if(dt==dtxt02){
						$(this).parent("a").addClass("ov");
					}
				});
			}
		}
		gnb_obj.li.ul.stop().animate({"opacity":0},150,function(){$(this).hide();});
		gnb_obj.h.stop().animate({"height":80+"px"},300);
	}
	
	function gnb_open(idx){
		gnb_obj.li.find(">a").removeClass("ov");
		gnb_obj.li.eq(idx).find(">a").addClass("ov");
		gnb_obj.li.find(">ul").removeClass("ov");
		gnb_obj.li.eq(idx).find(">ul").addClass("ov");
		
		gnb_obj.maxH = 0;
		for(var i=0; i<gnb_obj.li.size(); i++){
			gnb_obj.maxH = Math.max(gnb_obj.maxH,gnb_obj.li.eq(i).find(">ul").removeAttr("style").innerHeight());
		}
		gnb_obj.li.ul.height(gnb_obj.maxH).show().stop().animate({"opacity":1},300);
		gnb_obj.maxH = gnb_obj.maxH + 80;
		gnb_obj.h.stop().animate({"height":gnb_obj.maxH+"px"},300);
		gnb_obj.blind.stop().animate({"height":100+"%"},300);
	}
	
	
	//Slide_map
	$('<div id="slide_map"><div class="box"><strong class="title">전체메뉴</strong><div class="binds"></div><a href="#" class="close">닫기</a></div><span class="blind"></span></div>').prependTo($("#wrap"));
	gnb_obj.clone(false).appendTo($("#slide_map >.box > .binds"));
	/*$(".site_code").clone(false).appendTo($("#slide_map >.box > .binds"));*/
	
	$(".mob_btn").click(function(){
		$("body,html").stop().animate({"scrollTop":"0"},500,function(){
			$("#slide_map").show().stop().animate({"opacity":1},300,function(){
				$(this).find(">.box").stop().animate({"right":0},300);
			});
			$("#wrap").height(940);
			$("#slide_map").height($(document).height());		
		});
		/* 리사이즈시, 모바일 메뉴 닫기 */
		$(window).resize(function(){
			if($("#slide_map").css('display')=='block'){
				$("#slide_map").find(">.box").stop().animate({"right":-100+"%"},300,function(){ 
					$(this).parent().stop().animate({"opacity":0},300,function(){
						$(this).hide();	
					});
					$("#wrap, #slide_map").removeAttr("style");
				});	
			}
		});
		return false;
	});

	$("#slide_map .box .close").click(function(){
		$("#slide_map").find(">.box").stop().animate({"right":-100+"%"},300,function(){
			$(this).parent().stop().animate({"opacity":0},300,function(){
				$(this).hide();	
			});
			$("#wrap, #slide_map").removeAttr("style");
		});	
		return false;
	});

	$(window).resize(function(){
		if($("#slide_map").is(":visible")){
			$("#wrap").height(940);
			$("#slide_map").height($(document).height());
		} else {
			$("#wrap, #slide_map").removeAttr("style");	
		}
	});	
	
	//Mobile Menu
	var mob_gnb_obj = $("#slide_map"); 
		mob_gnb_obj.box = mob_gnb_obj.find(">.box"); 
		mob_gnb_obj.box.gnb = mob_gnb_obj.box.find(">.binds"); 
		mob_gnb_obj.box.gnb.ul = mob_gnb_obj.box.gnb.find(">ul");
		mob_gnb_obj.box.gnb.ul.li = mob_gnb_obj.box.gnb.ul.find(">li");
		mob_gnb_obj.box.gnb.ul.li.a = mob_gnb_obj.box.gnb.ul.li.find(">a");
		mob_gnb_obj.box.gnb.ul.li.ul = mob_gnb_obj.box.gnb.ul.li.find(">ul");
		mob_gnb_obj.box.gnb.ul.li.ul.li = mob_gnb_obj.box.gnb.ul.li.ul.find(">li");
		mob_gnb_obj.box.gnb.ul.li.ul.li.a = mob_gnb_obj.box.gnb.ul.li.ul.li.find(">a");	
		mob_gnb_obj.box.gnb.ul.li.ul.li.ul = mob_gnb_obj.box.gnb.ul.li.ul.li.find(">ul");
		mob_gnb_obj.box.gnb.ul.li.ul.li.ul.li = mob_gnb_obj.box.gnb.ul.li.ul.li.ul.find(">li");
		mob_gnb_obj.box.gnb.ul.li.ul.li.ul.li.a = mob_gnb_obj.box.gnb.ul.li.ul.li.ul.li.find(">a");
		mob_gnb_obj.box.gnb.ul.li.ul.li.ul.li.ul = mob_gnb_obj.box.gnb.ul.li.ul.li.ul.li.find(">ul");
		mob_gnb_obj.box.gnb.ul.li.ul.li.ul.li.ul.li = mob_gnb_obj.box.gnb.ul.li.ul.li.ul.li.ul.find(">li");
		mob_gnb_obj.box.gnb.ul.li.ul.li.ul.li.ul.li.a = mob_gnb_obj.box.gnb.ul.li.ul.li.ul.li.ul.li.find(">a");
		
	//def
	mob_def();
	function mob_def(){
		mob_gnb_obj.box.find("a").removeClass("ov");
		if(dtxt01.length!=0){
			mob_gnb_obj.box.gnb.ul.li.a.removeClass("ov");
			mob_gnb_obj.box.gnb.ul.li.a.each(function(){
				var dt = $(this).text();
				if(dt==dtxt01){
					$(this).addClass("ov").siblings("ul").slideDown();
				}
			});
			if(dtxt02.length!=0){
				mob_gnb_obj.box.gnb.ul.li.ul.li.a.find(">strong").each(function(){
					var dt = $(this).text();
					if(dt==dtxt02){
						$(this).parent().addClass("ov").siblings("ul").slideDown();
					}
				});
				if(dtxt03.length!=0){
					mob_gnb_obj.box.gnb.ul.li.ul.li.ul.li.a.find(">strong").each(function(){
						var dt = $(this).text();
						if(dt==dtxt03){
							$(this).parent().addClass("ov").siblings("ul").slideDown();
						}
					});
					if(dtxt04.length!=0){
						mob_gnb_obj.box.gnb.ul.li.ul.li.ul.li.ul.li.a.find(">strong").each(function(){
							var dt = $(this).text();
							if(dt==dtxt04){
								$(this).parent().addClass("ov");
							}
						});
					}
				}
			}
		}
	}
	
	mob_gnb_obj.box.gnb.ul.li.a.click(function(){
		if(mob_gnb_obj.box.gnb.ul.li.ul.is(":animated")) return false;
		if($(this).siblings("ul").size() != 0){
			mob_gnb_obj.box.gnb.ul.li.a.not(this).removeClass("ov").siblings("ul").slideUp();
			$(this).toggleClass("ov").siblings("ul").slideToggle();
			return false;	
		} else {
			return true;	
		}
	});
	
	mob_gnb_obj.box.gnb.ul.li.ul.li.a.click(function(){
		if(mob_gnb_obj.box.gnb.ul.li.ul.li.ul.is(":animated")) return false;
		if($(this).siblings("ul").size() != 0){
			mob_gnb_obj.box.gnb.ul.li.ul.li.a.not(this).removeClass("ov").siblings("ul").slideUp();
			$(this).toggleClass("ov").siblings("ul").slideToggle();
			return false;
		} else {
			return true;	
		}
	});
	
	mob_gnb_obj.box.gnb.ul.li.ul.li.ul.li.a.click(function(){
		if(mob_gnb_obj.box.gnb.ul.li.ul.li.ul.li.ul.is(":animated")) return false;
		if($(this).siblings("ul").size() != 0){
			mob_gnb_obj.box.gnb.ul.li.ul.li.ul.li.a.not(this).removeClass("ov").siblings("ul").slideUp();
			$(this).toggleClass("ov").siblings("ul").slideToggle();
			return false;
		} else {
			return true;	
		}
	});
}

/* ///////////////////////////////////////////////////////////////////////////////////////////////////////////

유틸_검색

/////////////////////////////////////////////////////////////////////////////////////////////////////////// */
$(document).ready(function(){

	$('.search_btn button').click(function(){
		var confirm=$('#header .search').css('display');
		if(confirm=='block'){
			$('#header .search').fadeOut(100);
		}else{
			$('#header .search').fadeIn(100);
		};
	});
	$('.search_close button').click(function(){
		$('#header .search').hide();
	});
	
	function searchGo( frm ) {
								
		if( !frm.query.value ) {
			alert("검색어를 입력해주세요.");
			frm.query.focus();
			return false;
		}
		
		return true;
		
	}	

});

/* ///////////////////////////////////////////////////////////////////////////////////////////////////////////

푸터 슬라이딩 박스	

/////////////////////////////////////////////////////////////////////////////////////////////////////////// */
$(function(){ 
    $('#ftBtnSite').click(function(){
        if($(this).hasClass('on')){
            $(this).removeClass();
            $('.ft_site').stop().slideUp();
        }else{
            $(this).addClass('on');
            $('.ft_site').stop().slideDown();
        }       
    });
});

/* 상단이동 */
$(function(){	
	$('#moveTop a').click(function(){
		$('html').stop().animate({scrollTop: 0});
	});
});



