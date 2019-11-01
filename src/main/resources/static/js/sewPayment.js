cal();

$("#all_agree").click(function(){
	if($(this).is(':checked')){
		$("#buy_agree2").prop("checked", true);
		chkStat = 1;
	}else{
		$("#buy_agree2").prop("checked", false);
		chkStat = 0;
	}
});

$("#buy_agree2").click(function(){
	if($(this).is(":checked")){
		$("#all_agree").prop("checked", true);	
		chkStat = 1;
	}else{
		$("#all_agree").prop("checked", false);
		chkStat = 0;
	}
});

$("input[id=pay0]").click(function () {	
	$(".creditCard").css("display", "block");
	$(".virtualAccount").css("display", "none");
});

$("input[id=pay1]").click(function () {
	$(".creditCard").css("display", "none");
	$(".virtualAccount").css("display", "block");
});

$(".memPhone").text(function() {
	if($(this).text().length==11) {
		var fisrt = $(this).text().substring(0, 3);
		var middle = "****";
		var end = $(this).text().substring(7, 11);
		var newPhone = fisrt+"-"+middle+"-"+end;
		$(this).text(newPhone);
	} else {
		var fisrt = $(this).text().substring(0, 3);
		var middle = "***";
		var end = $(this).text().substring(6, 10);
		var newPhone = fisrt+"-"+middle+"-"+end;
		$(this).text(newPhone);
	}
});

$('.format-money').text(function() {
	$(this).text(numberFormat($(this).text()));
});

function numberFormat(inputNumber) {
	return inputNumber.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function cal(){
	var totalPrice = 0;
	var totalProdListPrice = 0;
	var totalDeli = 0;
	var totalPoint = 0;
	var totalDiscount = 0;
	$('input[type="hidden"].totalProdPrice').each(function () {
	    totalPrice += Number($(this).val());	    
	});
	
	$('input[type="hidden"].totalProdListPrice').each(function () {
		totalProdListPrice += Number($(this).val());
	});
	
	$('input[type="hidden"].prodDeli').each(function () {
		totalDeli += Number($(this).val());
	});
	
	totalPoint = Math.floor(Number(totalPrice*0.01));
	
	totalDiscount = totalProdListPrice-totalPrice;
	
	$(".totalDiscountPrice").text(numberFormat(totalDiscount));
	$(".prePoint").text(numberFormat(totalPoint));
	$(".accPoint").val(totalPoint);
	$(".totalPrice").text(numberFormat(totalPrice));
	
	$(".totalDeliveryFee").text(numberFormat(totalDeli));
	$(".totalOrderListPrice").text(numberFormat(totalProdListPrice));
	$(".totalValue").val(totalPrice);
	$(".oriTotalValue").val(totalPrice);
	$(".totalListValue").val(totalProdListPrice);
	$(".totalDeliValue").val(totalDeli);
}

$(".pointAllUseButton").click(function (e) {	
	e.preventDefault();
	var oriTotalPrice = $(".oriTotalValue").val();	
	if( Number($('.memPoint').val()) > Number($(".totalValue").val()*0.2) ){
		$('.usedMemPoint').val(Number($(".totalValue").val()*0.2));
		$('.useMemPoint').val(numberFormat(Number($(".totalValue").val()*0.2)));
		$('.usingMemPoint').text(numberFormat(Number($(".totalValue").val()*0.2)));
		$(".remainMemPoint").val(Number($(".memPoint").val())-Number($(".usedMemPoint").val()));
	}else{
		$('.usedMemPoint').val($('.memPoint').val());
		$('.useMemPoint').val(numberFormat($('.memPoint').val()));
		$('.usingMemPoint').text(numberFormat($('.memPoint').val()));
		$(".remainMemPoint").val(Number($(".memPoint").val())-Number($(".usedMemPoint").val()));
	}
	$(".totalValue").val(Number(oriTotalPrice)-Number($('.usedMemPoint').val()));
	$(".totalPrice").text(numberFormat($(".totalValue").val()));
});

function inputNumberFormat(obj) {
	var oriTotalPrice = $(".oriTotalValue").val();
	var usedMemPoint= 0;
	if(obj.value==0){
		obj.value='';
		$('.usedMemPoint').val(0);
		$('.usingMemPoint').text(0);		
		$(".totalValue").val(Number(oriTotalPrice)-0);
		$(".totalPrice").text(numberFormat($(".totalValue").val()));
		$(".remainMemPoint").val(Number($(".memPoint").val())-Number($(".usedMemPoint").val()));
	}else{
		if(Number($('.memPoint').val()) > Number($(".totalValue").val()*0.2)){
			usedMemPoint = Number($(".totalValue").val()*0.2);
			$('.usedMemPoint').val(usedMemPoint);
			$('.useMemPoint').val(numberFormat(usedMemPoint));
			$('.usingMemPoint').text(numberFormat(usedMemPoint));
			$(".totalValue").val(Number(oriTotalPrice)-usedMemPoint);
			$(".totalPrice").text(numberFormat($(".totalValue").val()));
			$(".remainMemPoint").val(Number($(".memPoint").val())-Number(usedMemPoint));
		}else{
			if(Number(uncomma(obj.value))>Number($('.memPoint').val())){
				usedMemPoint = $('.memPoint').val();
				$('.usedMemPoint').val(usedMemPoint);
				$('.useMemPoint').val(numberFormat(usedMemPoint));
				$('.usingMemPoint').text(numberFormat(usedMemPoint));
				$(".totalValue").val(Number(oriTotalPrice)-usedMemPoint);
				$(".totalPrice").text(numberFormat($(".totalValue").val()));
				$(".remainMemPoint").val(Number($(".memPoint").val())-Number(usedMemPoint));
			}else{
				usedMemPoint = uncomma(obj.value);
				$('.usedMemPoint').val(uncomma(obj.value));
				obj.value = comma(usedMemPoint);
				$('.usingMemPoint').text(obj.value);
				$(".totalValue").val(Number(oriTotalPrice)-Number(usedMemPoint));
				$(".totalPrice").text(numberFormat($(".totalValue").val()));
				$(".remainMemPoint").val(Number($(".memPoint").val())-Number(usedMemPoint));
			}
		}
	}
}

function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

function uncomma(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');
}

var recvStat = 0;
var phStat = 0;
var addrStat = 0;
var cardStat = 0;
var chkStat = 0;


$(".btn_payment").click(function(e){
	e.preventDefault();	
	if(recvStat==0||phStat==0||addrStat==0||cartStat==0||chkStat==0){
		$(".error").show();		
		$('html').animate({ scrollTop : 0}, 600);
	}
	if($(".cardCompanyTypeCode").val()==null){
		$("#cardChkBox").show();
		cardStat = 0;
	} else {
		$("#cardChkBox").hide();
		cardStat = 1;
	}
});

$(".receiverName").on("change keyup paste", function(){
	if($(".receiverName").val()==""){
		$("#receiverChkBox").show();
		recvStat = 0;
	}else{
		$("#receiverChkBox").hide();
		recvStat = 1;
	}
});

$(".middleNum").on("change keyup paste", function(){
	if( $(".middleNum").val()=="" || $(".endNum").val()=="" ){
		$("#phoneChkBox").show();
		phStat = 0;
	}else{
		$("#phoneChkBox").hide();
		phStat = 1;
	}
});

$(".endNum").on("change keyup paste", function(){
	if($(".middleNum").val()==""||$(".endNum").val()==""){
		$("#phoneChkBox").show();
		phStat = 0;
	}else{
		$("#phoneChkBox").hide();
		phStat = 1;
	}
});

$(".addr_input2").on("change keyup paste", function(){
	if($(".zipcode").val()==""||$(".addr_input1").val()==""||$(".addr_input2").val()==""){
		$("#addrChkBox").show();
		addrStat = 0;
	}else{
		$("#addrChkBox").hide();
		addrStat = 1;
	}
});

$(".cardCompanyTypeCode").on("change", function(){
	if($(".cardCompanyTypeCode").val()==null){
		$("#cardChkBox").show();
		cardStat = 0;
	} else {
		$("#cardChkBox").hide();
		cardStat = 1;
	}
});

if(Number($(".totalValue").val())>100000){
	$("#monthGeneralPay").attr("disabled", false);
}

function closePop(self){
	$(self).parent('div').parent('div').css("display", "none");
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