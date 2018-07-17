function validateEmail(){
		var email=document.forms["edit_form"]["newEmail"].value;
		
		if (email==null || email==""){
			document.getElementById("emailErrorPicture").innerHTML= "<img src='./images/incorrect.png' alt='Enter e-mail'>";
			document.getElementById("emailErrorMessage").innerHTML= "Palun sisestage e-mail";
			return false;
		} else {
			document.getElementById("emailErrorPicture").innerHTML= "";
			var emailExp =/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			if (!email.match(emailExp)){
				document.getElementById("emailErrorPicture").innerHTML="<img src='./images/incorrect.png' alt='Incorrect'>";
				document.getElementById("emailErrorMessage").innerHTML= "Palun sisestage korrektne e-mail";
				return false;
			} else {
				document.getElementById("emailErrorPicture").innerHTML= "<img src='./images/correct.png' alt='Correct'>";
				document.getElementById("emailErrorMessage").innerHTML= "";
				return true;
			}
		}
}

function validateLawyerName(){
	var bureauname=document.forms["edit_form"]["newName"].value;
	if (bureauname==null || bureauname==""){
		document.getElementById("lawyerNamePicture").innerHTML= "<img src='images/incorrect.png' alt= 'Enter bureauname!'>";
		document.getElementById("lawyerNameMessage").innerHTML= "Palun sisestage b&#252roo nimi";
		return false;
	} /*else if(bureauname.indexof("+")!== -1){
		document.getElementById("bureauNamePicture").innerHTML= "<img src='images/incorrect.png' alt= 'Enter bureauname!'>";
		document.getElementById("bureauNameMessage").innerHTML= "Palun sisestage korrektne b&#252roo nimi";
		return false;
	} */else if (bureauname.match(/.[!,@,#,$,%,^,&,*,?,_,+,~,-,£,(,)]/)){
		document.getElementById("lawyerNamePicture").innerHTML= "<img src='images/incorrect.png' alt= 'Enter bureauname!'>";
		document.getElementById("lawyerNameMessage").innerHTML="S&#252mbolid pole lubatud!";
		return false;
	} else {
		document.getElementById("lawyerNamePicture").innerHTML= "<img src='images/correct.png' alt= 'Correct!'>";
		document.getElementById("lawyerNameMessage").innerHTML= "";
		return true;
	}	
}

function validateForm(){
	if (validateEmail()){
		document.getElementById("emailErrorMessage").innerHTML= "";
		if (validateLawyerName()){
			document.getElementById("lawyerNameMessage").innerHTML= "";
			return true;
		} else{
			document.getElementById("lawyerNameMessage").innerHTML= "Palun sisestage b&#252roo nimi";
			document.getElementById("confirmInput").innerHTML = "<img src='images/incorrect.png' alt= ''>";
		}
	} else{
		document.getElementById("emailErrorMessage").innerHTML= "Palun sisestage e-mail";
		document.getElementById("confirmInput").innerHTML = "<img src='images/incorrect.png' alt= ''>";
	}
	return false;
}