var colorSelect = null;
var sizeSelect = null;
var optionCount = 0;
var totalAmount = 0;
var totalPrice = 0;

$('#colorOption').click(function() {
	$("#sizeOption *").remove();
	colorSelect = $(this).val();			
	$.ajax({
		async: true,
	    type : 'POST',
	    data : JSON.stringify({"prodNo": getParameterByName("prodNo"), "prodColor": colorSelect}),
	    url : "getSize.do",
	    dataType : "json",
	    contentType: "application/json; charset=UTF-8",
	    success : function(data) {
	        if (data.prodInven==null) {			        	
	        	$("#sizeOption").hide();			        	
	        } else {
	            //아이디가 존재할 경우				            
	            $("#sizeOption").show();	            
	            var small = data.prodInven.prodSsize;
	            var medium = data.prodInven.prodMsize;
	            var large = data.prodInven.prodLsize;
	            var xlarge = data.prodInven.prodXLsize;
	            
	            if(small=="0"){
	            	samll = "품절";
	            }
	            if(medium=="0"){
	            	medium = "품절";
	            }
	            if(large=="0"){
	            	large = "품절";
	            }
	            if(xlarge=="0"){
	            	xlarge = "품절";
	            }
	            $("#sizeOption").append(
	            	`
	            	<option value="" selected disabled> = 사이즈 = </option>
            		<option value="S 사이즈" id="sSize">재고 : ${small} (S 사이즈)</option>
            		<option value="M 사이즈" id="mSize">재고 : ${medium} (M 사이즈)</option>
            		<option value="L 사이즈" id="lSize">재고 : ${large} (L 사이즈)</option>
            		<option value="XL 사이즈" id="xlSize">재고 : ${xlarge} (XL 사이즈)</option>
            		`		
	            );
	            if(small=="품절"){
	            	var x = document.getElementById("sSize");
	                x.style.color = "red";
	                x.disabled = true;
	            }
	            if(medium=="품절"){
	            	var x = document.getElementById("mSize");
	                x.style.color = "red";
	                x.disabled = true;
	            }
	            if(large=="품절"){
	            	var x = document.getElementById("lSize");
	                x.style.color = "red"; 
	                x.disabled = true;
	            }
	            if(xlarge=="품절"){
	            	var x = document.getElementById("xlSize");
	                x.style.color = "red";
	                x.disabled = true;
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

$('#sizeOption').click(function() {
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
							<div class="itemBox">
								<p class="itemName">${itemName}</p>
								<div class="itemRow">
									<div class="itemCounter">
										<button type="button" class="pmBtn minus" onclick="minusAmount(this)">-</button>
										<input type="number" class="itemAmount" value="1" onkeyup="cal(this, this.form.prodPrice)">
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
				$("#totalPrice").val(Number(sum));
			}	
		}else{
			$("#selectChk").hide();
			$("#selectedList").append(
		    	`
					<li class="listItem">
						<div class="itemBox">
							<p class="itemName">${itemName}</p>
							<div class="itemRow">
								<div class="itemCounter">
									<button type="button" class="pmBtn minus" onclick="minusAmount(this)">-</button>
									<input type="number" class="itemAmount" value="1" onkeyup="cal(this, this.form.prodPrice)">
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
			$("#totalPrice").val(sum);
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
	}	
}

function deleteBox(self) {
	var prevAmount = $(self).parent("span").parent("div").children(".itemCounter").children(".itemAmount").val();
	var prevPrice = $(self).parent("span").children(".sumPrice").val();
	$($(self).parent("span").parent("div").parent("div").parent("li")).remove();	
	optionCount-=1;
	alert(optionCount);
	if(optionCount==0){
		$(".totalAmount").text(0);
		$(".totalPriceTxt").text(0);
	}else{
		$(".totalAmount").text(totalAmount-prevAmount);
		$(".totalPriceTxt").text(numberFormat(totalPrice-prevPrice));
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