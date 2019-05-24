$(document).ready(function() {

	var dp = $("#prjFinishingDate");
	var nameInput = $("#prjName");

	dp.datepicker({
		format : 'yyyy-mm-dd',
	});

	//Set date to value of datepicker on changing date
	dp.on('change', function() {
		dp.attr("value", dp.val());
	});
	
	dp.siblings('div').on('click', function() {
		dp.trigger('focus');
	});
	
	nameInput.siblings('div').on('click', function() {
		nameInput.trigger('focus');
	});
});