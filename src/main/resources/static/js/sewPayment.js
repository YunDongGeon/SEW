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