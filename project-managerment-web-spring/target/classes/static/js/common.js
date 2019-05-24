
$(document).ready(function() {
var pathname = window.location.href ;
var substr = pathname.slice(0, 43);
var transtr = $(".help").text(); 
if(pathname == "http://localhost:8080/projectlist?lang=fr" || pathname == "http://localhost:8080/newproject?lang=fr" || substr == "http://localhost:8080/updateproject?lang=fr" || transtr == "Aidez-moi" ){
	
	$(".languageEN").removeClass("current");
	$(".language").addClass("current");
}

});