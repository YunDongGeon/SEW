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
})

// 한개의 체크박스 선택 해제시 전체선택 체크박스도 해제
$(".smallBox").click(function(){
	if($("input[name='smallBox']:checked").length == 2){
		$("input[id='allAgree']").prop("checked", true);
	}else{
		$("input[id='allAgree']").prop("checked", false);
	}
})

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

var idRegx = /^[a-zA-Z0-9]{4,12}$/;

$("#memId").blur(function() {
    if ($("#memId").val() == ""){
    	$("#")
    	$("#idChkBox1").show();
    }
});


// 회원가입 유효성 검사
function inputsChk(form){
	var idRegx = /^[a-zA-Z0-9]{4,12}$/;
	var pwRegx = /^[a-zA-Z0-9]{4,12}$/;
	var emailRegx =  /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var phoneRegx = /^[0-9]*$/;
	
	$("#memId").on("propertychange change keyup paste input", function() {
	    var currentVal = $(this).val();
	    if(currentVal == oldVal) {
	        return;
	    }
	 
	    oldVal = currentVal;
	    alert("changed!");
	});
	
	if(form.memId.value==""){
		
		
	}

	if(form.memEmail.value==""){
		alert("이메일을 입력하십시오.");
		return false;
	}
	if (!emailRegx.test(form.memEmail.value)){
	  alert("이메일은 형식으로 입력해주세요.");
	  form.memEmail.focus();
	  return false;
	}
	
	var password = form.memPw;
	var re_password = form.pwChk;
	
	if(password.value==""){
		alert("비밀번호를 입력하십시오.");
		return false;
	}
	
	if(password.length<8 && password.length>16){
		alert("비밀번호를 8자~16자 내로 입력하십시오.")
		return false;
	}
	

	if(!password.value==re_password.value){
		alert("비밀번호가 다릅니다.");
		return false;
	}
	 
	if(form.phone.value==""){
		 alert("핸드폰번호를 입력하십시오");
		 return false;
		 
	}
	if(!phoneRegx.test(form.phone.value)){
		alert("형식에 맞게 입력하십시오");
		return false;		 
	}
	 
	if(form.birth.value==""){
		alert("생년월일을 입력하십시오.")
		return false;
	}
	 
	if(form.zipcode.value==""){
		alert("주소를 입력하십시오");
		return false; 
	}
	if(form.addr2.value==""){
		alert("상세주소를 입력하십시오.");
		return false;
	}

	form.submit();
}

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