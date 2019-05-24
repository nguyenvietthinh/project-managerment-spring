function ProjectViewModel() {
	var self = this;

	self.prjName = ko.observable("");

	self.projects = ko.observableArray([]);
	
	self.query = function() {
		$.ajax({
			method : "POST",
			url : "/query",
			data : {
				name : self.prjName()		
			}
		}).done(
				function(data) {
			
					// If no data is returneds
					if (!data[0]) {
						showDialog('#alertDialog', 'Warning',
								"There are no project with name: "
										+ self.prjName());
					} else {
						data = convertDateOfProjectList(data);
						self.projects(data);
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
		dat[i].finishingDate = formatDateMMDDYYYY(dat[i].finishingDate);
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

	var criterion = $("#criterion").attr("value");

	// If ever queried in session
	if (criterion) {
		// If previous query is with empty criterion
		if (criterion == "#Queried") {
			criterion = "";
		}
		prjViewModel.prjName(criterion);
		prjViewModel.query();
	}

	// Redirect on row click
	$("table").on("click", "tr td", function() {
		var link = $(this).closest("tr").attr("href");
		window.location.href = link;
	});
});