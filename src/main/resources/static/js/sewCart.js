cal();

function checkAll(){
	if($("#lb_chk").is(':checked')){
		$("input[name=cartProductIds]").prop("checked", true);
	}else{
		$("input[name=cartProductIds]").prop("checked", false);
	}
}

$("input[name=cartProductIds]").click(function(){
	if($("input[name=cartProductIds]:checked").length == $("input[name=cartProductIds]").length){
		$("input[id='lb_chk']").prop("checked", true);
	}else{
		$("input[id='lb_chk']").prop("checked", false);
	}
});

function delSelected(){
	$('.delCartPop').css("display", "block");	
	var chk_obj = $("input[name=cartProductIds]:checked");
	$('.delCartItem').click(function() {
		$(chk_obj).each(function() {		
			var checkboxValue = $(this).parent('td').next().next().next().next().children('p').children('.cartNo').val();
			delCartItem(checkboxValue);
		});
	});
}

function cal(){
	var totalPrice = 0;
	var totalDeli = 0;
	var totalPoint = 0;
	
	for(var i=0;i<$('.prodPrice').get().length;i++){
		totalPrice += Number(removeComma($('.prodPrice')[i].innerHTML));
		totalPoint += Math.floor(Number(removeComma($('.prodPrice')[i].innerHTML))*0.005);
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

$('.btn_ordel').click(function(){
	cartNo = $(this).prev('.cartNo').val();
	$('.delCartPop').css("display", "block");
});

$('.closePop').click(function() {
	$('.delCartPop').css("display", "none");
});

$('.delCartItem').click(function() {
	delCartItem(cartNo);
});

function delCartItem(self) {	
	$.ajax({
		async: true,
	    type : 'GET',
	    data : {"cartNo": self},
	    url : "delCart.do",
	    dataType : "json",
	    contentType: "application/json; charset=UTF-8",
	    success : function(data) {
	    	$(location).attr("href", "myCart.do");
	    },
	    error : function(error) {
//	        alert("error : " + error);
	    }
	});
}

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