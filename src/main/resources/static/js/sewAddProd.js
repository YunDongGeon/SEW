$("input:text[numberOnly]").on("keyup", function() {
    $(this).val($(this).val().replace(/[^0-9]/));
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
			$(".prodInven").show();
		}else if(prodType=="하의" && prodGen=="남성"){
			$("#topCat").hide();
			$("#botCat").show();
			$(".prodInven").show();
		}else if(prodType=="상의" && prodGen=="여성"){
			$("#topCat").show();
			$("#botCat").hide();
			$(".prodInven").show();
		}else if(prodType=="하의" && prodGen=="여성"){
			$("#topCat").hide();
			$("#botCat").show();
			$(".prodInven").show();
		}
		typeChk=1;
	}else{
		$("#topCat").hide();
		$("#botCat").hide();
		$(".prodInven").hide();
		typeChk=0;
	}
});

$("#addColor").on("click", function(){	
	$("#itemList").append(
			`<li>
				색상
				<i style="float:right; margin-right: 100px;" class="far fa-trash-alt" onclick="deleteBox(this)"></i>
				<input class="form-control" type="text" name="prodColor">										
				S 사이즈 <input class="form-control" type="text" name="prodSsize" numberOnly>																				
				M 사이즈 <input class="form-control" type="text" name="prodMsize" numberOnly>
				L 사이즈 <input class="form-control" type="text" name="prodLsize" numberOnly>
				XL 사이즈 <input class="form-control" type="text" name="prodXLsize" numberOnly>
			</li>`);
});
function deleteBox(self){
	$($(self).parent("li")[0]).remove();	
}
