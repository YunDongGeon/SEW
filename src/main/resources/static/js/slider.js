$(document).ready(function(){
    $('.slider').slick({ 
    	dots: true,
    	infinite: true,
    	speed: 500,    	
		slidesToShow: 1,
		slidesToScroll: 1,
		autoplay: true,
		autoplaySpeed: 4000,
		arrows: false
    });
});