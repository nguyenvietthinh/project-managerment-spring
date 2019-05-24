$(function() {
	$(".datetimepicker").datepicker();
});

function Project() {
	 $('#messMandatory').hide();
	 $('#comment').hide();
}

Project.prototype.doCreate = function() {
	
	this.clearAllErrors();
	var check = this.collectData();
	if (check == true) {
		 $('#comment').show();
		 $('#messMandatory').hide();
		 
	}else{
		
		$('#messMandatory').show();
	}
};

Project.prototype.collectData = function() {
	var projnum = this.getElement("projectnum").value;
	var projname = this.getElement("projectname").value;
	var cusname = this.getElement("cusname").value;
	var group = this.getValueDropdow("inputState");
	var members = this.getElement("members").value;
	
	var ok = true;
    if (!this.checkMandatoryField(projnum, "projectnum")) {
        ok = false;

    }
    if (!this.checkMandatoryField(projname, "projectname")) {
        ok = false;

    } 
    if (!this.checkMandatoryField(cusname, "cusname")) {
        ok = false;

    }
    if (!this.checkMandatoryField(members, "members")) {
        ok = false;
        
    }


    if (!ok) {
        return false;
    }
    else{
    	
    	var a = members.replace(',', '\n');
    	this.getElement("comment").value = a;
    	
    	return true;
    }
};

/**
 * get element by ID
 * 
 * @return element Done
 */
Project.prototype.getElement = function(element) {
	if (typeof (element) == 'string') {
		return document.getElementById(element);
	}
	return element;
};

/**
 * get value of dropdown
 * 
 * @return value Done
 */
Project.prototype.getValueDropdow = function(element) {
	var selector = this.getElement(element);
	var value = selector[selector.selectedIndex].value;

	return value;
};

/**
 * If the given "value" is empty, the field will be marked as error.
 * 
 * @return true if no error was marked.
 */
Project.prototype.checkMandatoryField = function(value, fieldId) {
	if (!value) {
		this.markError(fieldId, "This field is mandatory");
		return false;
	}
	
	return true;
};

Project.prototype.clearAllErrors = function() {
	var fields = [ "projectNumber", "projectName", "cusname" ];
	for ( var i in fields) {
		this.clearError(fields[i]);
	}
};

Project.prototype.markError = function(fieldId) {
	this.addClass(fieldId, "error");
}

Project.prototype.clearError = function(fieldId) {
	this.removeClass(fieldId, "error");
};

Project.prototype.addClass = function(elememt, classNameAdd) {
	var elem = this.getElement(elememt);
	
	if (this.classExists(elem, classNameAdd)) {
		return;
	}
	elem.className = elem.className + " " + classNameAdd;
};

Project.prototype.removeClass = function(element, classNameRemove) {
	var elem = this.getElement(element);
	if (!this.classExists(elem, classNameRemove)) {
		return;
	}
	elem.className = elem.className.replace((elem.className.indexOf(" "
			+ classNameRemove) >= 0 ? " " + classNameRemove : classNameRemove),
			"");
};

Project.prototype.classExists = function(element, classNameExists) {
	var elemClass = this.getElement(element).className;
	if (elemClass) {
		return (elemClass == classNameExists)
				|| (elemClass.indexOf(" " + classNameExists) >= 0)
				|| (elemClass.indexOf(" " + classNameExists + " ") >= 0)
				|| (elemClass.indexOf(classNameExists + " ") >= 0);
	}
	return false;
};

window.onload = function() {
	window.pageObj = new Project();
	pageObj.getElement("projectnum").focus();
	pageObj.getElement("btn_create").onclick = function() {
		pageObj.doCreate();
	}
}
// $( function() {
//   
// $('#members').autocomplete({
// source: "suggestion",
// minLength:1,
// matchContains: 'true',
// focus: function() {
// // prevent value inserted on focus
// return false;
// },
// select: function (event, ui) {
// var terms = split(this.value);
// terms.pop();
// terms.push(ui.item.value);
// terms.push('');
// this.value = terms.join(', ');
// return false;
// },
// autoSelectFirst: true
//
// });
//	
// function split(val) {
// return val.split(/,\s*/);
// }
//
// function extractLast(term) {
// return split(term).pop();
// }
// });


	$( document ).ready(function() {
		
		
			    function autocomplete($ctl, cb, freeInput) {
			        $ctl.autocomplete({
			            minLength: 1,
			            autoFocus: true,
			            messages: {
			                noResults: '',
			                results: function () {}
			            },
			            source:function(request, response) {
			            	// get text -> x
			            	var txtmember =  request.term;
			            	var subtxtmember = txtmember.substring(txtmember.lastIndexOf(', ')+1,);
			            	var sub = subtxtmember.substring(subtxtmember.indexOf(' ')+1);
			            	console.log("a", sub)
							$.ajax({
								url : "http://localhost:8080/suggestion",
								data : {
									member: sub
								},
								// data: x
								dataType : "json",
								success : function(data) {
//									
									console.log(data);
									response(data);
								}
							});
						},
			            select: function (e, ui) {
			                console.log("ui.item", ui.item)
			                cb(e, ui)
			                var terms = split(this.value);
				            terms.pop();
				            terms.push(ui.item.value);
				            terms.push('');
				            this.value = terms.join(', ');
				            return false;
			            },
			            change: function (e, ui) {
			            	
			                if (!(freeInput || ui.item)) e.target.value = "";
			            }
			        });
			    }
		function split(val) {
	        return val.split(/,\s*/);
	    }

	    function extractLast(term) {
	        return split(term).pop();
	    }
		autocomplete($('#members'),  function () {});
		(function($) {
			  $.fn.inputFilter = function(inputFilter) {
			    return this.on("input keydown keyup mousedown mouseup select contextmenu drop", function() {
			      if (inputFilter(this.value)) {
			        this.oldValue = this.value;
			        this.oldSelectionStart = this.selectionStart;
			        this.oldSelectionEnd = this.selectionEnd;
			      } else if (this.hasOwnProperty("oldValue")) {
			        this.value = this.oldValue;
			        this.setSelectionRange(this.oldSelectionStart, this.oldSelectionEnd);
			      }
			    });
			  };
			}(jQuery));
		$("#projectNumberN").inputFilter(function(value) {
			  return /^\d*$/.test(value); });
		
		$('#projectNumberN').blur(function()        
				{                   
				    if( !$(this).val() ) {                      
				    	 $(this).addClass('has-error'); 
				    	 $(this).val(-1); 
				    }
				    else{
				    	 $(this).removeClass('has-error');
				    }
				});
	});
