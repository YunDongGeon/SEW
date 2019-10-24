var colorSelect = null;
var sizeSelect = null;

$('#colorOption').change(function() {
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

$('#sizeOption').change(function() {
	sizeSelect = $(this).val();
	var itemName = colorSelect + " / " + sizeSelect;	
	var itemArr = $(".itemName").get();
	var prodPrice = $("#prodPrice").val();
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
									<button type="button" class="pmBtn minus">-</button>
									<input type="number" class="itemAmount" value="1">
									<button type="button" class="pmBtn plus">+</button>
								</div>
								<span class="itemPriceBox">
									<span class="itemPrice">${prodPrice}</span>원
									<i class="far fa-trash-alt delBtn" onclick="deleteBox(this)"></i>
								</span>
							</div>
						</div>												
					</li>
				`		
		    );			
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
								<button type="button" class="pmBtn minus">-</button>
								<input type="number" class="itemAmount" value="1">
								<button type="button" class="pmBtn plus">+</button>
							</div>
							<span class="itemPriceBox">
								<span class="itemPrice">${prodPrice}</span>원
								<i class="far fa-trash-alt delBtn" onclick="deleteBox(this)"></i>
							</span>
						</div>
					</div>												
				</li>
			`		
	    );		
	}
	jQuery('.itemPrice').text(function() {
	    jQuery(this).text(
	        jQuery(this).text().format()
	    );
	});
	$(".itemPrice").attr('class','format-money');	
});

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function deleteBox(self){
	$($(self).parent("span").parent("div").parent("div").parent("li")).remove();	
}

$(".itemAmount").on("change keyup paste", function() {
	alert($(".itemAmount").val());
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
