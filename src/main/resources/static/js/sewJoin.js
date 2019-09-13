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

function termsChk(form){
	if($("#allAgree").prop("checked")){
		form.action="joinInput";
		form.submit();
	}else if($("#termsAgree1").prop("checked") && $("#termsAgree2").prop("checked")){
		form.action="joinInput";
		form.submit();
	}else{
		alert("(필수) 이용약관을 체크해주세요.");
		return false;
	}	
}
