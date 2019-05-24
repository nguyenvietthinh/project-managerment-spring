function ProjectList() {

	this.numOfSelectedRow = 0;
	this.deleteButton = document.getElementsByClassName("deletelist");
	this.enableDisableDeleteButton();

}
var arrayChecked = new Array();
ProjectList.prototype.clickCheckBox = function(checkbox) {
	if (checkbox.checked) {

		var checkedItem = $(checkbox).val();
		
		arrayChecked.push(checkedItem);
		sessionStorage.setItem('navBtn', arrayChecked);
		var arrayValues = sessionStorage.navBtn.split(',');
		$("#deletes").val(sessionStorage.navBtn);

		this.numOfSelectedRow++;
	} else {
		var str = sessionStorage.navBtn;
		var checkedItem = $(checkbox).val();
		var array = str.split(',');
		var v = array.indexOf(checkedItem);
		
		for (var i = 0; i < array.length; i++) {
			if (array[i] === checkedItem) {
				array.splice(i, 1);
				i--;
			}
		}
		strItem = array.join(',');
		sessionStorage.setItem('navBtn', strItem);
		$("#deletes").val(strItem);
		arrayChecked = array;
		this.numOfSelectedRow--;
	}

	this.enableDisableDeleteButton();
	return this.arrayChecked;
}

ProjectList.prototype.enableDisableDeleteButton = function(index) {
	var elems = this.deleteButton;
	var inputs = document.getElementsByClassName('ckbox');
	var status = document.getElementsByClassName('statusaaa');
	// for (var i = 0, l = status.length; i < l; ++i) {
	// if(ko.contextFor($('.statusaaa').get(0)).$data.status == "NEW"){
	// this.deleteButton[i].style.visibility = "visible";
	// }
	// }
	if (this.numOfSelectedRow > 0) {
		// for (var i = 0, l = inputs.length; i < l; ++i) {
		// alert(ko.contextFor($('.statusaaa').get(0)).$data.status);
		// if (inputs[i].checked) {
		//				
		// elems[i].style.visibility = "visible";
		// } else {
		// elems[i].style.visibility = "hidden";
		// }
		// }
		elems[elems.length - 1].style.visibility = "visible"; // hidden has to
		// be a string

	} else {
		for (var i = 0, l = inputs.length; i < l; ++i) {
			// elems[i].style.visibility = "hidden";
			elems[elems.length - 1].style.visibility = "hidden";
		}
	}

	$('#count-checked').text(this.numOfSelectedRow + " items selected");
};

window.onload = function() {
	sessionStorage.setItem('navBtn', undefined);
	window.pageObj = new ProjectList();

};

function rowChkBoxClick(checkbox) {
	pageObj.clickCheckBox(checkbox);

}

function ProjectViewModel() {
	var self = this;
	self.page = ko.observable("");
	self.prjName = ko.observable("");
	self.pageLength = ko.observable();
	self.projects = ko.observableArray([]);
	self.myFunction = function(index) {
		
	}

	function ProjectFilterStatus(name, id) {
		this.Name = name;
		this.Id = id;
	}
	this.statusFilters = ko.observableArray([
			new ProjectFilterStatus("Status", ""),
			new ProjectFilterStatus("New", "NEW"),
			new ProjectFilterStatus("Planned", "PLA"),
			new ProjectFilterStatus("In progress", "INP"),
			new ProjectFilterStatus("Finished", "FIN") ]);
	this.selectedStatus = ko.observable();
	self.query = function() {

		$.ajax({

					method : "POST",
					url : "/query",
					data : {
						name : self.prjName(),
						stt : self.selectedStatus(),
						page : self.page()
					}
				})
				.done(
						function(data) {
					
							if (!data.projectlist[0]) {

								page : self.page()-1;
								if(data.pageLength > 0){
									jQuery(function(){
										   jQuery('#pageDefault').click();
										});
										data.project = convertDateOfProjectList(data.projectlist);
										self.projects(data.projectlist);
										self.pageLength(data.pageLength - 1);
								}else{
									 showDialog('#alertDialog', 'Warning',
											 "There are no project with criterion: "
											 + self.prjName());
								}
								
							} else {

								data.project = convertDateOfProjectList(data.projectlist);
								self.projects(data.projectlist);
								self.pageLength(data.pageLength - 1);
								if(sessionStorage.navBtn === ""){
									sessionStorage.setItem('navBtn', undefined);
								}
								if (sessionStorage.navBtn != undefined) {
									var arrayValues = sessionStorage.navBtn
											.split(',');
									$("#deletes").val(sessionStorage.navBtn);
									$.each(arrayValues, function(i, val) {

										$("input[value='" + val + "']").prop(
												'checked', true);
										
									});
								}
								
								if(self.projects().length === 1){
									$("#addtr").remove();
									$(".table0").append("<tr  id='addtr' ><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
									
								}else if (self.projects().length === 2){
									$("#addtr").remove();
									
								}
								var status = document
										.getElementsByClassName('statusaaa');
								for (var i = 0, l = status.length; i < l; ++i) {
									if (ko.contextFor($('.statusaaa').get(i)).$data.status == "NEW") {
										$(".alone")[i].style.visibility = "visible";
										$(".statusaaa")[i].append("New")
									}
									else if (ko.contextFor($('.statusaaa').get(i)).$data.status == "PLA") {
										
										$(".statusaaa")[i].append("Planned")
									}
									else if (ko.contextFor($('.statusaaa').get(i)).$data.status == "INP") {
										
										$(".statusaaa")[i].append("In progress")
									}else{
										$(".statusaaa")[i].append("Finished")
									}
								}
							}
						});
	}

	self.pagination = function() {

		$.ajax({

					method : "POST",
					url : "/projectlistOnInit",
					data : {
						page : self.page()
					}
				})
				.done(
						function(data) {

							data = convertDateOfProjectList(data);
							self.projects(data);

							var status = document
									.getElementsByClassName('statusaaa');
							for (var i = 0, l = status.length; i < l; ++i) {
								if (ko.contextFor($('.statusaaa').get(i)).$data.status == "NEW") {
									$(".alone")[i].style.visibility = "visible";
								}
							}

						});
	}
}

function showDialog(dialog, title, content) {
	$(dialog).find(".title-content").text(title);
	$(dialog).find(".modal-body p").text(content);
	$(dialog).modal('show');
}

/*
 * Convert date of projects in list param Projects[] dat return Projects[] dat
 */
function convertDateOfProjectList(dat) {
	for (var i = 0; i < dat.length; i++) {
		dat[i].startDate = formatDateMMDDYYYY(dat[i].startDate);
	}

	return dat;
}

/*
 * Convert Date to String YYYY-MM-DD param Date inputDate return String
 * formatedDate
 */
function formatDateMMDDYYYY(inputDate) {
	// Add 0 to Day or Month if < 10
	function pad(s) {
		return (s < 10) ? '0' + s : s;
	}

	var inputDate = new Date(inputDate);
	var formatedDate = [ inputDate.getFullYear(),
			pad(inputDate.getMonth() + 1), pad(inputDate.getDate()) ].join("-");

	return formatedDate;
}

$(document).ready(function() {
	// Binding data

	var prjViewModel = new ProjectViewModel();
	ko.applyBindings(prjViewModel);
	var page = 0;
	prjViewModel.page(page);
	var criterion = "";
	prjViewModel.prjName(criterion);
	prjViewModel.query();
	

	$(".before").click(function() {
	
		if (prjViewModel.page() > 0) {
			var bfpage = prjViewModel.page() - 1;
			prjViewModel.page(bfpage);
			prjViewModel.query();
			var txt = bfpage + 1;
			var classPage = document.getElementsByClassName("lin");
			for (var i = 0; i < classPage.length; i++) {
				if (classPage[i].textContent == txt) {
					$(".lin").removeClass("hl");
					$(classPage[i]).addClass("hl");
					break;
				}
			}

		}
	});
	$(".after").click(function() {

		var afpage = prjViewModel.page() + 1;
		var text = $(".lin").text();
		var len = text.length;
		if (afpage < len) {
			prjViewModel.page(afpage);
			prjViewModel.query();
			var txt = afpage + 1;
			var classPage = document.getElementsByClassName("lin");
			for (var i = 0; i < classPage.length; i++) {
				if (classPage[i].textContent == txt) {
					$(".lin").removeClass("hl");
					$(classPage[i]).addClass("hl");
					break;
				}
			}
		}

	});
	$(".lin").click(function() {
		
		var inpage = $(this).text();
		$(".lin").removeClass("hl");
		$(this).addClass("hl");
		prjViewModel.page(inpage - 1);
		prjViewModel.query();
	});
	prjViewModel.myFunction = function(index) {
		var classPage = document.getElementsByClassName("lin");
		var inpage = index + 2;
		
		for (var i = 0; i < classPage.length; i++) {
			if (classPage[i].textContent == inpage) {
				$(".lin").removeClass("hl");
				$(classPage[i]).addClass("hl");
				prjViewModel.page(inpage - 1);
				prjViewModel.query();
				break;
			}
		}
		
	}

	
	var criterion = $("#criterion").attr("value");
	var sttcriterion = $("#sttcriterion").attr("value");
	// If ever queried in session
	
	if (criterion && sttcriterion) {

		// If previous query is with empty criterion
		if (criterion == "#Queried") {
			sessionStorage.setItem('navBtn', undefined);
			criterion = "";
		}
		if (sttcriterion == "#QueriedStt") {
			sessionStorage.setItem('navBtn', undefined);
			sttcriterion = "";
		}
		sessionStorage.setItem('navBtn', undefined);
		prjViewModel.selectedStatus(sttcriterion);
		prjViewModel.prjName(criterion);
		prjViewModel.query();

	}
	if (criterion) {

		// If previous query is with empty criterion
		if (criterion == "#Queried") {
			sessionStorage.setItem('navBtn', undefined);
			criterion = "";
		}
		sessionStorage.setItem('navBtn', undefined);
		prjViewModel.prjName(criterion);
		prjViewModel.query();

	}
	if (sttcriterion) {

		// If previous query is with empty criterion
		if (sttcriterion == "#QueriedStt") {
			sessionStorage.setItem('navBtn', undefined);
			sttcriterion = "";
		}
		sessionStorage.setItem('navBtn', undefined);
		prjViewModel.selectedStatus(sttcriterion);
		prjViewModel.query();

	}
	

	// Redirect on row click
	// $("table").on("click", "tr td", function() {
	// var link = $(this).closest("tr").attr("href");
	// window.location.href = link;
	// });

});
