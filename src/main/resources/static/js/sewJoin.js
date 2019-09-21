/* 약관 동의 체크박스 전체선택 / 해제 */
$("#allAgree").click(function(){
        //클릭되었으면
    if($("#allAgree").prop("checked")){
        //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
        $("input[name='smallBox']").prop("checked",true);
        //클릭이 안되있으면
    }else{
        //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
        $("input[name='smallBox']").prop("checked",false);
    }
});

// 한개의 체크박스 선택 해제시 전체선택 체크박스도 해제
$(".smallBox").click(function(){
	if($("input[name='smallBox']:checked").length == 2){
		$("input[id='allAgree']").prop("checked", true);
	}else{
		$("input[id='allAgree']").prop("checked", false);
	}
});

$("input:text[numberOnly]").on("keyup", function() {
    $(this).val($(this).val().replace(/[^0-9]/g,""));
});

function termsChk(form){
	if($("#allAgree").prop("checked")){
		form.submit();
	}else if($("#termsAgree1").prop("checked") && $("#termsAgree2").prop("checked")){
		form.submit();
	}else{
		alert("(필수) 이용약관을 체크해주세요.");
		return false;
	}	
}

//아이디 유효성검사
var idRegx = /^[a-zA-Z0-9]$/;
$("#memId").on("change keyup paste", function() {	
	var memId = $("#memId").val();
	if($("#memId").val().length<4){
		$("#idChkBox1").show();
    	$("#idChkBox2").hide();
    	$("#idChkBox3").hide();
	} else {
		$.ajax({		
	        async: true,
	        type : 'POST',
	        data : memId,
	        url : "memInputIdChk",
	        dataType : "json",
	        contentType: "application/json; charset=UTF-8",
	        success : function(data) {
	            if (data.cnt > 0) {                
	                //아이디가 존제할 경우 빨깡으로 , 아니면 파랑으로 처리하는 디자인
	            	$("#idChkBox1").hide(); 
	            	$("#idChkBox2").show();
	            	$("#idChkBox3").hide();      
	            	$("#memInputSubmit").attr("disabled", true);
	            } else {
	                //아이디가 존제할 경우 빨깡으로 , 아니면 파랑으로 처리하는 디자인
	                //아이디가 중복하지 않으면  idck = 1
	            	$("#idChkBox1").hide(); 
	            	$("#idChkBox2").hide();
	            	$("#idChkBox3").show();
	            	$("#memInputSubmit").attr("disabled", false);
	            }
	        },
	        error : function(error) {            
	            alert("error : " + error);
	        }
	    });
		
	    if ($("#memId").val() == ""){    	
	    	$("#idChkBox1").show();
	    	$("#idChkBox2").hide();
	    	$("#idChkBox3").hide();
	    	$("#memInputSubmit").attr("disabled", true);
	    } else if(!idRegx.test($("#memId").val())){
	    	$("#idChkBox1").show();
	    	$("#idChkBox2").hide();
	    	$("#idChkBox3").hide();
	    	$("#memInputSubmit").attr("disabled", true);
	    }
	}	
});

//비밀번호 유효성검사
var pwRegx = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
$("#memPw").on("change keyup paste", function() {
    if ($("#memPw").val() == ""){    	
    	$("#pwChkBox").show();
    	$("#memInputSubmit").attr("disabled", true);
    } else if(!pwRegx.test($("#memPw").val())) { 
    	$("#pwChkBox").show();
    	$("#memInputSubmit").attr("disabled", true);
	} else {
    	$("#pwChkBox").hide();
    	$("#memInputSubmit").attr("disabled", false);
    }   
});

//비밀번호 확인 유효성검사
$("#pwChk").on("change keyup paste", function() {
    if ($("#pwChk").val() == ""){
    	$("#rePwChkBox1").show();
    	$("#memInputSubmit").attr("disabled", true);
    } else if($("#pwChk").val() != $("#memPw").val()) {
    	$("#rePwChkBox1").hide();
    	$("#rePwChkBox2").show();
    	$("#memInputSubmit").attr("disabled", true);
    } else {
    	$("#rePwChkBox1").hide();
    	$("#rePwChkBox2").hide();
    	$("#memInputSubmit").attr("disabled", false);
    }
});

// 이름 유효성검사
$("#memName").on("change keyup paste", function() {
    if ($("#memName").val() == ""){    	
    	$("#nameChkBox").show();
    	$("#memInputSubmit").attr("disabled", true);
    } else {
    	$("#nameChkBox").hide();
    	$("#memInputSubmit").attr("disabled", false);
    }   
});

// 생년월일 유효성검사
$("#memBirth").on("change keyup paste", function() {
	var dateStr = $("#memBirth").val();		
    var year = Number(dateStr.substr(0,4)); // 입력한 값의 0~4자리까지 (연)
    var month = Number(dateStr.substr(4,2)); // 입력한 값의 4번째 자리부터 2자리 숫자 (월)
    var day = Number(dateStr.substr(6,2)); // 입력한 값 6번째 자리부터 2자리 숫자 (일)
    var today = new Date(); // 날짜 변수 선언
    var yearNow = today.getFullYear(); // 올해 연도 가져옴

    if (dateStr.length <=8) {
		// 연도의 경우 1900 보다 작거나 yearNow 보다 크다면 false를 반환합니다.
	    if (1900 > year || year > yearNow){
	    	
	    	$('#birthChkBox').show();
	    	$("#memInputSubmit").attr("disabled", true);
	    	
	    }else if (month < 1 || month > 12) {
	    		
	    	$('#birthChkBox').show();
	    	$("#memInputSubmit").attr("disabled", true);
	    
	    }else if (day < 1 || day > 31) {
	    	
	    	$('#birthChkBox').show();
	    	$("#memInputSubmit").attr("disabled", true);
	    	
	    }else if ((month==4 || month==6 || month==9 || month==11) && day==31) {
	    	 
	    	$('#birthChkBox').show();
	    	$("#memInputSubmit").attr("disabled", true);
	    	 
	    }else if (month == 2) {
	    	 
	       	var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
	       	
	     	if (day>29 || (day==29 && !isleap)) {
	     		
	     		$('#birthChkBox').show();
	     		$("#memInputSubmit").attr("disabled", true);
	    	
			}else{
				$('#birthChkBox').hide();
				$("#memInputSubmit").attr("disabled", false);
			}//end of if (day>29 || (day==29 && !isleap))
	     	
	    }else{	    	
	    	$('#birthChkBox').hide();
	    	$("#memInputSubmit").attr("disabled", false);
		}//end of if		
	}
});


// 이메일  유효성  검사
var emailRegx =  /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

$("#memEmail").on("change keyup paste", function() {
	if ($("#memEmail").val() == ""){    	
    	$("#emailChkBox").show();
    	$("#memInputSubmit").attr("disabled", true);
    } else if(!emailRegx.test($("#memEmail").val())){
    	$("#emailChkBox").show();
    	$("#memInputSubmit").attr("disabled", true);
    } else {
    	$("#emailChkBox").hide();
    	$("#memInputSubmit").attr("disabled", false);
    }
});

//Daum postCode api
function findPostCode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                
            } 

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("addr1").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("addr2").focus();
        }
    }).open();
}