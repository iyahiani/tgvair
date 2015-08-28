/*function colorerJours(Date){
	var dates = #{calendar.datesList} ;
}*/ 


function resetForm(name){
	var form = document.getElementsByName(name)[0]; 
	form.submit() ;
	form.reset(); 
	return false ;
}