$(function(){
    
    /////////////////////////////////////////////////////////////////////////////////////
    /* Slick Option */
    /*
        infinite: true, //양방향 무한 모션
        speed:300, // 슬라이드 스피드
        autoplay: true, //자동플레이 유무( false시 자동플레이 안됨 )
        autoplaySpeed:4000, // 자동플레이 스피드
        slidesToShow: 3, //한 화면에 보여줄 아이템수
        slidesToScroll: 3 // 한번에 슬라이드 시킬 아이템 개수
        arrows: false, //좌우 화살 버튼 노출 여부 ( false 시 안보임 )        
        dots: true, // 하단 paging버튼 노출 여부ㅊ
        infinite: true, // 양방향 무한 모션
        speed: 500, // 모션 스피드
        fade: true, //페이드모션 실행 여부
    */       
    /////////////////////////////////////////////////////////////////////////////////////
    
    // 비주얼 슬라이드
    /* 슬라이드 추가 시, 활성
    $(".visual").slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        arrows: false,
        fade: true,
        asNavFor: '.slogan' //슬로건 슬라이드와 연결 			
    });   
    */ 	
    
    // 슬로건 슬라이드
    /* 슬라이드 추가 시, 활성
    $('.slogan').slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        dots: true,
        arrows: false,
        asNavFor: '.visual' //비주얼 슬라이드와 연결 		
    });    
    */
    
    // 메인비주얼 슬라이드 정지
    $('#visualStop').click(function(){
        $(this).fadeOut(100);
        $('#visualPlay').delay(100).fadeIn(100);
        $(".slogan").slick('slickPause');
        $(".visual").slick('slickPause');
    });
    
    // 메인비주얼 슬라이드 재생
    $('#visualPlay').click(function(){
        $(this).fadeOut(100);
        $('#visualStop').delay(100).fadeIn(100);
        $(".slogan").slick('slickPlay');
        $(".visual").slick('slickPlay');
    });  


});


/* ///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	학사일정	

/////////////////////////////////////////////////////////////////////////////////////////////////////////// */
$(document).ready(function(){		
	var param = "#schedule";
	var obj = ".sche_list>li";
	var btn = param+" .control";
	var interval = 5000; 
	var speed = 0;
	var viewSize = 1;
	var moreSize = 0;
	var dir = "x";
	var data = 0;
	var auto = true;
	var hover = false;
	var method = "easeInOutCubic";
	var op1 = false;		 
	stateScrollObj(param,obj,btn,interval,speed,viewSize,moreSize,dir,data,auto,hover,method,op1);
});


/* ///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	배너존	

/////////////////////////////////////////////////////////////////////////////////////////////////////////// */
$(document).ready(function(){		
	var param = "#mbanner";
	var obj = ".obj>li";
	var btn = param+" .control";
	var interval = 5000; 
	var speed = 0;
	var viewSize = 1;
	var moreSize = 0;
	var dir = "x";
	var data = 0;
	var auto = true;
	var hover = false;
	var method = "easeInOutCubic";
	var op1 = false;		 
	stateScrollObj(param,obj,btn,interval,speed,viewSize,moreSize,dir,data,auto,hover,method,op1);
});
