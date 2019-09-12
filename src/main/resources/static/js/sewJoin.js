/* 약관 동의 체크박스 전체선택 / 해제 */
$("#allAgree").click(function(){
        //클릭되었으면
    if($("#allAgree").prop("checked")){
        //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
        $("input[name=smallBox]").prop("checked",true);
        //클릭이 안되있으면
    }else{
        //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
        $("input[name=smallBox]").prop("checked",false);
    }
})