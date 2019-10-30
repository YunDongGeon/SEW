cal();

function checkAll(){
	if($("#lb_chk").is(':checked')){
		$("input[name=cartProductIds]").prop("checked", true);
		$("input[name=cartNo]").prop("disabled", false);
	}else{
		$("input[name=cartProductIds]").prop("checked", false);
		$("input[name=cartNo]").prop("disabled", true);
	}
}

$("input[name=cartProductIds]").click(function(){
	var cartNo = $(this).parent('td').next().next().next().next().children('p').children('.cartNo');
	if($("input[name=cartProductIds]:checked").length == $("input[name=cartProductIds]").length){
		$("input[id='lb_chk']").prop("checked", true);		
	}else{
		$("input[id='lb_chk']").prop("checked", false);
	}
	if($(this).is(":checked")){		
		cartNo.prop("disabled", false);
	}else{
		cartNo.prop("disabled", true);
	}
});

function cal(){
	var totalPrice = 0;
	var totalDeli = 0;
	var totalPoint = 0;
	
	for(var i=0;i<$('.prodPrice').get().length;i++){
		totalPrice += Number(removeComma($('.prodPrice')[i].innerHTML));
		totalPoint += Math.floor(Number(removeComma($('.prodPrice')[i].innerHTML))*0.01);
	}
	$(".totalPoint").text(numberFormat(totalPoint));
	$(".totalPrice").text(numberFormat(totalPrice));
	for(var i=0;i<$('.deliPrice').get().length;i++){		
		if($.trim($('.deliPrice')[i].innerHTML)=="무료"){
			totalDeli += 0;
		} else {
			totalDeli += Number(removeComma($.trim($('.deliPrice')[i].innerHTML)));
		}
	}	
	$(".totalDeli").text(numberFormat(totalDeli));
	$(".sumTotal").text(numberFormat(totalPrice + totalDeli));
}

var cartNo = null;

$('#delSelected').click(function(e){
	e.preventDefault();
	$('.delCartPop').css("display", "block");
	$('.delCartItem').click(function() {
		$("form").attr("action", "/delSelected.do");
		$("form").unbind("submit").submit();
	});
});

$('.btn_ordel').click(function(e){
	e.preventDefault();
	cartNo = $(this).prev('.cartNo').val();
	$('.delCartPop').css("display", "block");
	$('.delCartItem').click(function() {
		$("form").attr("action", "/delCart.do");
		$("form").unbind("submit").submit();
	});
});

$('.closePop').click(function() {
	$('.delCartPop').css("display", "none");
});

Number.prototype.format = function(){
    if(this==0) return 0;

    var reg = /(^[+-]?\d+)(\d{3})/;
    var n = (this + '');

    while (reg.test(n)) n = n.replace(reg, '$1' + ',' + '$2');

    return n;
};

String.prototype.format = function(){
    var num = parseFloat(this);
    if( isNaN(num) ) return "0";
    return num.format();
};

jQuery('.format-money').text(function() {
    jQuery(this).text(
        jQuery(this).text().format()
    );
});

function removeComma(str) {
	return parseInt(str.replace(/,/g,""));
}

function numberFormat(inputNumber) {
   return inputNumber.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}