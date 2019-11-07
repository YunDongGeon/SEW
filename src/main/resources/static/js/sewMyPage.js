$(document).ready(function(){
	$.ajax({
		async: true,
	    type : 'POST',			    
	    url : "getMemPoint.do",
	    dataType : "json",
	    contentType: "application/json; charset=UTF-8",
	    success : function(data) {
	    	console.log(data.memPoint);
	    	$('.memPoint').text(data.memPoint);
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
			
			jQuery('.memPoint').text(function() {
			    jQuery(this).text(
			        jQuery(this).text().format()
			    );
			});
	    },
	    error : function(error) {
	        alert("error : " + error);
	    }
	});	
});
var start=4;
var end=8;
function addOrderList(){
	$.ajax({
		async: true,
	    type : 'POST',			
	    data : JSON.stringify({"start": start, "end": end}),
	    url : "addOrderList.do",	    
	    dataType : "json",
	    contentType: "application/json; charset=UTF-8",
	    success : function(data) {	
	    	var ol = JSON.parse(data.orderList);			
			if(ol==0){				
				start=Number(start-4);
				end=Number(end-4);
			}else{
				for(var i=0; i<ol.length; i++){
					var prodThumbName = ol[i].orderProdList[0].prodThumbName;
					var prodName = ol[i].orderProdList[0].prodName;
					var orderStat = ol[i].orderStat;			
					var orderDate = ol[i].orderDate;
					if(ol[i].orderProdList.length>1){
						var other = ol[i].orderProdList.length-1;
						if(orderStat=="배송 완료"){
							$("#listContentArea").append(									
								'<div class="goods_pay_section">'+
									'<div class="goods_group">'+
										'<ul class="goods_group_list">'+
											'<li class="goods_pay_item ">'+
												'<div class="goods_item">'+
													'<a href="#" class="goods_thumb">'+
														'<img src="/prodThumb/'+prodThumbName+'" width="90" height="90">'+										
													'</a>'+
													'<div class="goods_info">'+
														'<a href="#" class="goods">'+												
															'<p class="name">'+
																'<span>'+prodName+'</span>(외 <span>'+other+'</span>건)'+
															'</p>'+														
															'<ul class="info">'+
																'<li>'+
																	'<span class="format-money">'+ol[i].totalCost+'</span><span class="won">원</span>'+
																'</li>'+
																'<li class="date">'+
																	'<span>'+orderDate+'<span>'+
																'</li>'+
															'</ul>'+
														'</a>'+														
														'<p class="guide">'+orderStat+'</p>'+
													'</div>'+
												'</div>'+
												'<div class="button_item">'+
													'<button class="writeReviewBtn">리뷰쓰기</button>'+
												'</div>'+
											'</li>'+
										'</ul>'+
									'</div>'+
								'</div>'
							);	
						} else {
							$("#listContentArea").append(									
								'<div class="goods_pay_section">'+
									'<div class="goods_group">'+
										'<ul class="goods_group_list">'+
											'<li class="goods_pay_item ">'+
												'<div class="goods_item">'+
													'<a href="#" class="goods_thumb">'+
														'<img src="/prodThumb/'+prodThumbName+'" width="90" height="90">'+										
													'</a>'+
													'<div class="goods_info">'+
														'<a href="#" class="goods">'+												
															'<p class="name">'+
																'<span>'+prodName+'</span>(외 <span>'+other+'</span>건)'+
															'</p>'+														
															'<ul class="info">'+
																'<li>'+
																	'<span class="format-money">'+ol[i].totalCost+'</span><span class="won">원</span>'+
																'</li>'+
																'<li class="date">'+
																	'<span>'+orderDate+'<span>'+
																'</li>'+
															'</ul>'+
														'</a>'+														
														'<p class="guide">'+orderStat+'</p>'+
													'</div>'+
												'</div>'+										
											'</li>'+
										'</ul>'+
									'</div>'+
								'</div>'
							);		
						}
					} else {
						if(orderStat=="배송 완료"){
							$("#listContentArea").append(									
								'<div class="goods_pay_section">'+
									'<div class="goods_group">'+
										'<ul class="goods_group_list">'+
											'<li class="goods_pay_item ">'+
												'<div class="goods_item">'+
													'<a href="#" class="goods_thumb">'+
														'<img src="/prodThumb/'+prodThumbName+'" width="90" height="90">'+										
													'</a>'+
													'<div class="goods_info">'+
														'<a href="#" class="goods">'+
															'<p class="name">'+prodName+'</p>'+
															'<ul class="info">'+
																'<li>'+
																	'<span class="format-money">'+ol[i].totalCost+'</span><span class="won">원</span>'+
																'</li>'+
																'<li class="date">'+
																	'<span>'+orderDate+'<span>'+
																'</li>'+
															'</ul>'+
														'</a>'+														
														'<p class="guide">'+orderStat+'</p>'+
													'</div>'+
												'</div>'+	
												'<div class="button_item">'+
													'<button class="writeReviewBtn">리뷰쓰기</button>'+
												'</div>'+
											'</li>'+
										'</ul>'+
									'</div>'+
								'</div>'
							);	
						}else{
							$("#listContentArea").append(									
								'<div class="goods_pay_section">'+
									'<div class="goods_group">'+
										'<ul class="goods_group_list">'+
											'<li class="goods_pay_item ">'+
												'<div class="goods_item">'+
													'<a href="#" class="goods_thumb">'+
														'<img src="/prodThumb/'+prodThumbName+'" width="90" height="90">'+										
													'</a>'+
													'<div class="goods_info">'+
														'<a href="#" class="goods">'+
															'<p class="name">'+prodName+'</p>'+
															'<ul class="info">'+
																'<li>'+
																	'<span class="format-money">'+ol[i].totalCost+'</span><span class="won">원</span>'+
																'</li>'+
																'<li class="date">'+
																	'<span>'+orderDate+'<span>'+
																'</li>'+
															'</ul>'+
														'</a>'+														
														'<p class="guide">'+orderStat+'</p>'+
													'</div>'+
												'</div>'+										
											'</li>'+
										'</ul>'+
									'</div>'+
								'</div>'
							);	
						}					
					}
				}
			}			
	    },
	    error : function(error) {
	        alert("error : " + error);
	    }
	});
	start = start + 4;
	end = end + 4;
}
