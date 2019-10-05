$("input:text[numberOnly]").on("keyup", function() {
    $(this).val($(this).val().replace(/[^0-9]/g,""));
});

var genChk = 0;
var typeChk = 0;

//성별 유효성검사
$("#prodGen").on("change keyup paste", function() {	
	var prodGen = $("#prodGen").val();
	if(prodGen!="none"){
		$("#menTopSize").hide();
		$("#menBotSize").hide();
		$("#womenTopSize").hide();
		$("#womenBotSize").hide();
		genChk=1;
	}else{
		$("#menTopSize").hide();
		$("#menBotSize").hide();
		$("#womenTopSize").hide();
		$("#womenBotSize").hide();
		genChk=0;
	}
});

//분류 유효성검사
$("#prodType").on("change keyup paste", function() {	
	var prodType = $("#prodType").val();
	var prodGen = $("#prodGen").val();
	if(prodType!="none" && prodGen!="none"){
		if(prodType=="상의" && prodGen=="남성"){
			$("#topCat").show();
			$("#botCat").hide();
			$("#menTopSize").show();
			$("#menBotSize").hide();
			$("#womenTopSize").hide();
			$("#womenBotSize").hide();
		}else if(prodType=="하의" && prodGen=="남성"){
			$("#topCat").hide();
			$("#botCat").show();
			$("#menTopSize").hide();
			$("#menBotSize").show();
			$("#womenTopSize").hide();
			$("#womenBotSize").hide();
		}else if(prodType=="상의" && prodGen=="여성"){
			$("#topCat").hide();
			$("#botCat").show();
			$("#menTopSize").hide();
			$("#menBotSize").hide();
			$("#womenTopSize").show();
			$("#womenBotSize").hide();
		}else if(prodType=="하의" && prodGen=="여성"){
			$("#topCat").hide();
			$("#botCat").show();
			$("#menTopSize").hide();
			$("#menBotSize").hide();
			$("#womenTopSize").hide();
			$("#womenBotSize").show();
		}
		typeChk=1;
	}else{
		$("#topCat").hide();
		$("#botCat").hide();
		$("#menTopSize").hide();
		$("#menBotSize").hide();
		$("#womenTopSize").hide();
		$("#womenBotSize").hide();
		typeChk=0;
	}
});