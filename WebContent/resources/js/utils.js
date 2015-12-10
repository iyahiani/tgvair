/*function colorerJours(Date){
	var dates = #{calendar.datesList} ;
}*/

function resetForm(name) {
	var form = document.getElementsByName(name)[0];
	form.submit();
	form.reset();
	return false;
};

$(document)
		.ready(
				function() {
					PrimeFaces.widget.Dialog.prototype.originalHide = PrimeFaces.widget.Dialog.prototype.hide;
					PrimeFaces.widget.Dialog.prototype.hide = function() {
						var ajaxResponseArgs = arguments.callee.caller.arguments[2];
						if (ajaxResponseArgs
								&& ajaxResponseArgs.validationFailed) {
							return; // on validation error, prevent closing
						}
						this.originalHide();
					};
				});