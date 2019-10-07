	var rand;
	
	//캡차 오디오 요청
	function audioCaptcha(type) {
		var kor = (type > 0) ? "lan=kor&":""; 
		$.ajax({
			url: '../captcha/CaptChaAudio.jsp',
			type: 'POST',
			dataType: 'text',
			data: 'rand=' + rand + '&ans=y',
			async: false,		
			success: function(resp) {
				var uAgent = navigator.userAgent;
				var soundUrl = '../captcha/CaptChaAudio.jsp?' + kor + 'rand=' + rand + '&ans=' + resp;
				//console.log(soundUrl);
				if (uAgent.indexOf('Trident') > -1 || uAgent.indexOf('MSIE') > -1) {
					winPlayer(soundUrl+'&agent=msie');
				} else if (!!document.createElement('audio').canPlayType) {
					try { new Audio(soundUrl).play(); } catch(e) { winPlayer(soundUrl); }
				} else window.open(soundUrl, '', 'width=1,height=1');
			}
		});
	}
	function winPlayer(objUrl) {
		$('#audiocatpch').html(' <bgsound src="' + objUrl + '">');
	}
	
	//캡차 이미지 요청 (캐쉬문제로 인해 이미지가 변경되지 않을수있으므로 요청시마다 랜덤숫자를 생성하여 요청)
	function changeCaptcha() {
		rand = Math.random();
		
		$('#catpcha').html('<img src="/1.Jsp-Project/captcha/CaptChaImg.jsp?rand=' + rand + '"/>');
	}
	
	$(document).ready(function() {
		
		changeCaptcha(); //캡차 이미지 요청
		
		$('#reLoad').click(function(){ changeCaptcha(); }); //새로고침버튼에 클릭이벤트 등록
		$('#soundOn').click(function(){ audioCaptcha(0); });	//음성듣기버튼에 클릭이벤트 등록
		$('#soundOnKor').click(function(){ audioCaptcha(1); }); //한글음성듣기 버튼에 클릭이벤트 등록
		
		//확인 버튼 클릭시
		$('#frmSubmit').click(function(){
			if ( !$('#answer').val() ) {
				alert('이미지에 보이는 숫자 또는 스피커를 통해 들리는 숫자를 입력해 주세요.');
			} else {
				$.ajax({
					url: '../captcha/captchaSubmit.jsp',
					type: 'POST',
					dataType: 'text',
					data: 'answer=' + $('#answer').val(),
					async: false,		
					success: function(resp) {
						alert(resp);
						$('#reLoad').click();
						$('#answer').val('');
					}
				});
			}
		});
	});