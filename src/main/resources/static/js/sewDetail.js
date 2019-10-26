var prodNo = null;
var colorSelect = null;
var sizeSelect = null;
var optionCount = 0;
var totalAmount = 0;
var totalPrice = 0;

$(document).ready(function(){
	prodNo = $("#prodNo").val();	
});

$('#colorOption').change(function() {
	$("#sizeOption *").remove();	
	colorSelect = $(this).val();			
	$.ajax({
		async: true,
	    type : 'POST',
	    data : JSON.stringify({"prodNo": prodNo, "prodColor": colorSelect}),
	    url : "getSize.do",
	    dataType : "json",
	    contentType: "application/json; charset=UTF-8",
	    success : function(data) {
	        if (data.prodInven==null) {			        	
	        	$("#sizeOption").hide();			        	
	        } else {
	        	
	            //아이디가 존재할 경우				            
	            $("#sizeOption").show();	 
	            var arr = [];
	            arr.push(data.prodInven.prodSsize);
	            arr.push(data.prodInven.prodMsize);
	            arr.push(data.prodInven.prodLsize);
	            arr.push(data.prodInven.prodXLsize);
	            for (var i=0; i<4; i++){
	            	if(arr[i]==0){
	            		arr[i]="품절";	            		
	            	}
	            }

	            $("#sizeOption").append(
	            	`
	            	<option value="" selected disabled> = 사이즈 = </option>
            		<option value="S 사이즈" id="sSize">재고 : ${arr[0]} (S 사이즈)</option>
            		<option value="M 사이즈" id="mSize">재고 : ${arr[1]} (M 사이즈)</option>
            		<option value="L 사이즈" id="lSize">재고 : ${arr[2]} (L 사이즈)</option>
            		<option value="XL 사이즈" id="xlSize">재고 : ${arr[3]} (XL 사이즈)</option>
            		`		
	            );
	            if(arr[0]=="품절"){
	            	$("#sSize").css("color", "red");	                
	                $("#sSize").attr("disabled",true);
	            }
	            if(arr[1]=="품절"){
	            	$("#mSize").css("color", "red");	                
	                $("#mSize").attr("disabled",true);
	            }
	            if(arr[2]=="품절"){
	            	$("#lSize").css("color", "red");	                
	                $("#lSize").attr("disabled",true);
	            }
	            if(arr[3]=="품절"){
	            	$("#xlSize").css("color", "red");	                
	                $("#xlSize").attr("disabled",true);
	            }
	        }
	    },
	    error : function(error) {
	        alert("error : " + error);
	    }
	});
});

Array.prototype.contains = function(obj) {
    var i = this.length;
    while (i--) {
        if (this[i].innerHTML == obj) {
            return true;
        }
    }
    return false;
}

$('#sizeOption').change(function() {
	sizeSelect = $(this).val();
	if(sizeSelect != null){
		var sum = 0;
		var curAmount = Number($(".totalCount").text());
		var curTotal = $("#totalPrice").val();
		var itemName = colorSelect + " / " + sizeSelect;	
		var itemArr = $(".itemName").get();
		var prodPrice = $("#prodPrice").val();
		var prodPriceTxt = numberFormat(prodPrice);	
		if(itemArr.length>0){
			var itemStat = itemArr.contains(itemName);
			if (itemStat){
				$("#selectChk").show();
			}else{
				$("#selectChk").hide();
				$("#selectedList").append(
			    	`
						<li class="listItem">
							<input type="hidden" name="prodColor" value="${colorSelect}" readOnly>
							<input type="hidden" name="prodSize" value="${sizeSelect}" readOnly>
							<div class="itemBox">
								<p class="itemName">${itemName}</p>
								<div class="itemRow">
									<div class="itemCounter">
										<button type="button" class="pmBtn minus" onclick="minusAmount(this)">-</button>
										<input type="number" name="prodAmount" class="itemAmount" value="1" onkeyup="cal(this, this.form.prodPrice)">
										<button type="button" class="pmBtn plus" onclick="plusAmount(this)">+</button>
									</div>
									<span class="itemPriceBox">
										<span class="itemPrice">${prodPriceTxt}</span>원
										<input type="hidden" class="sumPrice" value="${prodPrice}" readonly>
										<i class="far fa-trash-alt delBtn" onclick="deleteBox(this)"></i>
									</span>
								</div>
							</div>												
						</li>
					`		
			    );
				optionCount += 1;
				$(".totalAmount").text(curAmount+=1);			
				sum = Number(curTotal)+Number(prodPrice);				
				$(".totalPriceTxt").text(numberFormat(sum));
				$("#totalAmount").val(curAmount);
				$("#totalPrice").val(sum);
			}	
		}else{
			$("#selectChk").hide();
			$("#selectedList").append(
		    	`
					<li class="listItem">
						<input type="hidden" name="prodColor" value="${colorSelect}" readOnly>
						<input type="hidden" name="prodSize" value="${sizeSelect}" readOnly>
						<div class="itemBox">
							<p class="itemName">${itemName}</p>
							<div class="itemRow">
								<div class="itemCounter">
									<button type="button" class="pmBtn minus" onclick="minusAmount(this)">-</button>
									<input type="number" name="prodAmount" class="itemAmount" value="1" onkeyup="cal(this, this.form.prodPrice)">
									<button type="button" class="pmBtn plus" onclick="plusAmount(this)">+</button>
								</div>
								<span class="itemPriceBox">
									<span class="itemPrice">${prodPriceTxt}</span>원
									<input type="hidden" class="sumPrice" value="${prodPrice}" readonly>
									<i class="far fa-trash-alt delBtn" onclick="deleteBox(this)"></i>
								</span>
							</div>
						</div>												
					</li>
				`		
		    );
			optionCount += 1;
			$(".totalAmount").text(curAmount+=1);
			sum = Number(curTotal)+Number(prodPrice);		
			$(".totalPriceTxt").text(numberFormat(sum));
			$("#totalAmount").val(curAmount);
			$("#totalPrice").val(sum);
		}	
	}
});

$('#cantAddCart').click(function(e){
	e.preventDefault();
	alert("로그인이 필요한 기능입니다.");
	$(location).attr("href", "login.do");
});

$('#addCart').click(function(e){
	e.preventDefault();
	if($("#colorOption").val()==null){
		alert("색상을 선택하세요.")
	}else{		
		if($("#sizeOption").val()==null){
			alert("사이즈를 선택해주세요.");
		}else{
			$('form').attr("action", "addCart.do")
			$("form").unbind("submit").submit();
			alert("장바구니에 등록되었습니다.");	
		}
	}	
});

function cal(amount, price) {
	totalAmount = 0;
	totalPrice = 0;
	if($(amount).val()==0){
		$($(amount).parent("div").parent("div").parent("div").parent("li")).remove();		
		optionCount-=1;
		for(var n=0;n<optionCount;n++){		
			totalAmount += Number(document.getElementsByClassName('itemAmount')[n].value);
			totalPrice += Number(document.getElementsByClassName('sumPrice')[n].value);
		}
		$(".totalAmount").text(totalAmount);
		$(".totalPriceTxt").text(numberFormat(totalPrice));
//		$("#totalAmount").val(curAmount);/
	}else{
		var total = Number($(amount).val()*$(price).val());
		$(amount).parent("div").parent("div").children(".itemPriceBox").children(".itemPrice").text(numberFormat(total));
		$(amount).parent("div").parent("div").children(".itemPriceBox").children(".sumPrice").val(total);
		for(var n=0;n<optionCount;n++){		
			totalAmount += Number(document.getElementsByClassName('itemAmount')[n].value);
			totalPrice += Number(document.getElementsByClassName('sumPrice')[n].value);
		}
		$(".totalAmount").text(totalAmount);
		$(".totalPriceTxt").text(numberFormat(totalPrice));
		$("#totalAmount").val(curAmount);
		$("#totalPrice").val(Number(sum));
	}	
}

function deleteBox(self) {
	var prevAmount = $(self).parent("span").parent("div").children(".itemCounter").children(".itemAmount").val();
	var prevPrice = $(self).parent("span").children(".sumPrice").val();
	$($(self).parent("span").parent("div").parent("div").parent("li")).remove();	
	optionCount-=1;
	if(optionCount==0){
		$(".totalAmount").text(0);
		$(".totalPriceTxt").text(0);
		$("#totalAmount").val(0);
		$("#totalPrice").val(0);
	}else{
		$(".totalAmount").text(totalAmount-prevAmount);
		$(".totalPriceTxt").text(numberFormat(totalPrice-prevPrice));
		$("#totalAmount").val(totalAmount-prevAmount);
		$("#totalPrice").val(totalPrice-prevPrice);
	}
}

function numberFormat(inputNumber) {
   return inputNumber.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function removeComma(str) {
	return parseInt(str.replace(/,/g,""));
}

function plusAmount(self) {
	totalAmount = 0;
	totalPrice = 0;
	var cur = Number($(self).prev().val());
	var prev = cur;
	$(self).prev().val(cur+=1);
	
	var oriPrice = Number($(self).parent("div").next("span").children(".sumPrice").val()/prev);
	
	var total = Number(cur * oriPrice);
	$(self).parent("div").next("span").children(".itemPrice").text(numberFormat(total));
	$(self).parent("div").next("span").children(".sumPrice").val(total);
	
	for(var n=0;n<optionCount;n++){		
		totalAmount += Number(document.getElementsByClassName('itemAmount')[n].value);
		totalPrice += Number(document.getElementsByClassName('sumPrice')[n].value);
	}
	$(".totalAmount").text(totalAmount);
	$(".totalPriceTxt").text(numberFormat(totalPrice));
	$("#totalAmount").val(totalAmount);
	$("#totalPrice").val(totalPrice);
}

function minusAmount(self) {
	totalAmount = 0;
	totalPrice = 0;
	var cur = Number($(self).next().val());
	var prev = cur;
	$(self).next().val(cur-=1);
	
	if(cur==0){
		$($(self).parent("div").parent("div").parent("div").parent("li")).remove();		
		optionCount-=1;
		for(var n=0;n<optionCount;n++){		
			totalAmount += Number(document.getElementsByClassName('itemAmount')[n].value);
			totalPrice += Number(document.getElementsByClassName('sumPrice')[n].value);
		}
		$(".totalAmount").text(totalAmount);
		$(".totalPriceTxt").text(numberFormat(totalPrice));
		$("#totalAmount").val(totalAmount);
		$("#totalPrice").val(totalPrice);
	} else {
		var oriPrice = Number($(self).parent("div").next("span").children(".sumPrice").val()/prev);
		var total = Number(cur * oriPrice);
		$(self).parent("div").next("span").children(".itemPrice").text(numberFormat(total));
		$(self).parent("div").next("span").children(".sumPrice").val(total);
		
		for(var n=0;n<optionCount;n++){		
			totalAmount += Number(document.getElementsByClassName('itemAmount')[n].value);
			totalPrice += Number(document.getElementsByClassName('sumPrice')[n].value);
		}
		$(".totalAmount").text(totalAmount);
		$(".totalPriceTxt").text(numberFormat(totalPrice));
		$("#totalAmount").val(totalAmount);
		$("#totalPrice").val(totalPrice);
	}
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

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}