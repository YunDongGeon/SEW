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