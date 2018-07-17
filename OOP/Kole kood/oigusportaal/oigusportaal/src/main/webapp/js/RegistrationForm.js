/**
 *Registration Form Field Validation 
 */


/*
 * Fucntions for controlling email entry
 * checking if email match
 * have problems with entry suffix different from .com and .ee  
 * */
function validateEmail(){
		var email=document.forms["registration_form"]["registerEmail"].value;
		
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
/*
 * confirm email. checks for empty filed and email
 */
	
function confirmEmail(){
		
	var email=document.forms["registration_form"]["registerEmail"].value;
	var confirmEmail=document.forms["registration_form"]["registerEmailConfirmation"].value;
		
	if (confirmEmail==null || confirmEmail==""){
			document.getElementById("confirmEmailPicture").innerHTML= "<img src='images/incorrect.png' alt= 'Confirm e-mail'>";
			document.getElementById("confirmEmailMessage").innerHTML= "Kinnitage e-mail!";
			return false;
	} else {
		
		if (email !== confirmEmail){
				document.getElementById("confirmEmailPicture").innerHTML ="<img src='images/incorrect.png' alt= 'Email doesn\'t match!'>";
				document.getElementById("confirmEmailMessage").innerHTML= "E-mailid ei &#252hti.";
				return false;
		} else {
			document.getElementById("confirmEmailPicture").innerHTML = "<img src='images/correct.png' alt= 'Correct'>";
			document.getElementById("confirmEmailMessage").innerHTML = "";
			return true;
		}
	}
}
	

/*
 * Validate password.
 * Checks for empty field
 * Checks if: 
 * length is smaller than 6
 * consist symbols
 * consist small lettes and numbers
 * consist capital letters and numbers
 */

function validatePassword(){ 
	
	var password=document.forms["registration_form"]["registerPassword"].value;
	
	if (password==null || password==""){
		document.getElementById("passwordPicture").innerHTML= "<img src='images/incorrect.png' alt= 'Enter password'>";
		document.getElementById("passwordMessage").innerHTML= "Palun sisestage parool! Minimaalselt 6 m&#228rki.";
		return false;
	} else if( password.indexOf(" ") !== -1 ){
		document.getElementById("passwordPicture").innerHTML= "<img src='images/incorrect.png' alt= 'Symbols'>";
		document.getElementById("passwordMessage").innerHTML="T&#252hikud pole lubatud!";
		return false; 
	} else if (password.match(/.[!,@,#,$,%,^,&,*,?,_,+,~,-,£,(,)]/)){
		document.getElementById("passwordPicture").innerHTML= "<img src='images/incorrect.png' alt= 'Enter bureauname!'>";
		document.getElementById("passwordMessage").innerHTML="S&#252mbolid pole lubatud!";
		return false;
	} else {
		
		if (password.length <6){
			document.getElementById("passwordPicture").innerHTML= "<img src='images/incorrect.png' alt= 'Passowrd must be atlest 6 characters long!'>";
			document.getElementById("passwordMessage").innerHTML="Parool peab olema v&#228hemalt 6 m&#228rki.";
			return false;
		} else { if(password.match(/[a-z]/)&&password.match(/\d+/)&& password.match(/[A-Z]/)){
					document.getElementById("passwordPicture").innerHTML= "<img src='images/correct.png' alt= 'Strong'>";
					document.getElementById("passwordMessage").innerHTML= "Tugev";
					return true;
				}else if(password.match(/[0-9]/)&&password.match(/[a-z]/)){
					document.getElementById("passwordPicture").innerHTML= "<img src='images/correctmedium.png' alt= 'Medium'>";
					document.getElementById("passwordMessage").innerHTML= "Keskmine";
					return true;
				}else if(password.match(/[0-9]/)&&password.match(/[A-Z]/)){
					document.getElementById("passwordPicture").innerHTML= "<img src='images/correctmedium.png' alt= 'Medium'>";
					document.getElementById("passwordMessage").innerHTML= "Keskmine";
					return true;
				}else {
					document.getElementById("passwordPicture").innerHTML= "<img src='images/correctweak.png' alt= 'Weak'>";
					document.getElementById("passwordMessage").innerHTML= "N&#245rk";
					return true;
				}
		}
	}
}

/*
 * Confirm password. Checking for password and empty field. 
 *  */

function confirmPassword(){
	
	var password=document.forms["registration_form"]["registerPassword"].value;
	var confirmPassword=document.forms["registration_form"]["registerPasswordConfirmation"].value;
		
	if (confirmPassword==null || confirmPassword==""){
			document.getElementById("confirmPasswordPicture").innerHTML=  "<img src='images/incorrect.png' alt= 'Confirm password'>";
			document.getElementById("confirmPasswordMessage").innerHTML=" Palun kinnitage e-mail";
			false;
	} else {
		
		if (confirmPassword!==password){
				document.getElementById("confirmPasswordPicture").innerHTML = "<img src='images/incorrect.png' alt= 'Password doesn\'t match!'>";
				document.getElementById("confirmPasswordMessage").innerHTML = "Paroolid ei &#252hti.";
				return false;
		} else {
			document.getElementById("confirmPasswordPicture").innerHTML = "<img src='images/correct.png' alt= 'Correct'>";
			document.getElementById("confirmPasswordMessage").innerHTML = "";
			return true;
		}
	}
}

/*
 * Validate Phone number. Checking for empty field. 
 *  */

function validateBureauName(){
	var bureauname=document.forms["registration_form"]["registerBureauName"].value;
	if (bureauname==null || bureauname==""){
		document.getElementById("bureauNamePicture").innerHTML= "<img src='images/incorrect.png' alt= 'Enter bureauname!'>";
		document.getElementById("bureauNameMessage").innerHTML= "Palun sisestage b&#252roo nimi";
		return false;
	} /*else if(bureauname.indexof("+")!== -1){
		document.getElementById("bureauNamePicture").innerHTML= "<img src='images/incorrect.png' alt= 'Enter bureauname!'>";
		document.getElementById("bureauNameMessage").innerHTML= "Palun sisestage korrektne b&#252roo nimi";
		return false;
	} */else if (bureauname.match(/.[!,@,#,$,%,^,&,*,?,_,+,~,-,£,(,)]/)){
		document.getElementById("bureauNamePicture").innerHTML= "<img src='images/incorrect.png' alt= 'Enter bureauname!'>";
		document.getElementById("bureauNameMessage").innerHTML="S&#252mbolid pole lubatud!";
		return false;
	} else {
		document.getElementById("bureauNamePicture").innerHTML= "<img src='images/correct.png' alt= 'Correct!'>";
		document.getElementById("bureauNameMessage").innerHTML= "";
		return true;
	}	
}

/*
 * Validate Phone number. Checking for numbers, length is 8 and empty field. 
 *  */
	
function validateRegistrycode(){	
	var regcode=document.forms["registration_form"]["registerRegcode"].value;
	if (regcode==null || regcode==""){
		document.getElementById("regcodePicture").innerHTML= "<img src='images/incorrect.png' alt= 'Enter registrycode!'>";
		document.getElementById("regcodeMessage").innerHTML= "Palun sisestage 8-numbriline registrikood!";
			return false;
	} else if( regcode.indexOf(" ") !== -1){
		document.getElementById("regcodePicture").innerHTML= "<img src='images/incorrect.png' alt= 'Enter registrycode!'>";
		document.getElementById("regcodeMessage").innerHTML= "T&#252hikud pole lubatud!";
		return false;
	} else if (regcode.indexOf("+") !== -1){
		document.getElementById("regcodePicture").innerHTML= "<img src='images/incorrect.png' alt= 'Enter postal code! Must contain 5 numbers!'>";
		document.getElementById("regcodeMessage").innerHTML="S&#252mbolid pole lubatud!";
		return false;
	} else {
			var numericExpression = /^[0-9]+$/;
			if (isNaN(regcode)){
				
				document.getElementById("regcodePicture").innerHTML = "<img src='images/incorrect.png' alt= 'Incorrect value! Must contain only numbers!'>";
				document.getElementById("regcodeMessage").innerHTML= "Palun sisetage ainult numbreid!";
				return false;
			} else{
					if (regcode.length==8){
						document.getElementById("regcodePicture").innerHTML = "<img src='images/correct.png' alt= 'Correct!'>";
						document.getElementById("regcodeMessage").innerHTML="";
						return true;
					}else if(regcode.length<8){
						document.getElementById("regcodePicture").innerHTML = "<img src='images/incorrect.png' alt= 'Too short! Must contain 8 numbers!'>";
						document.getElementById("regcodeMessage").innerHTML="Liiga l&#252hike. Palun sisestage 8 numbriline kood.";
						return false;
					}else {
						document.getElementById("regcodePicture").innerHTML = "<img src='images/incorrect.png' alt= 'Too long! Must contain 8 numbers!'>";
						document.getElementById("regcodeMessage").innerHTML=" Liiga pikk. Palun sisestage ainult 8 numbrit.";
						return false;
					}
			}
	}
}

/*
 * Validate street address. Checking for empty field. 
 *  */

function validateStreetAddress(){
	var streetaddress=document.forms["registration_form"]["registerStreetAddress"].value;
	if (streetaddress==null || streetaddress==""){
		
		document.getElementById("streetAddressPicture").innerHTML= "<img src='images/incorrect.png' alt= 'Enter street address!'>";
		document.getElementById("streetAddressMessage").innerHTML="Palun sisesta firma aadress!";
		return false;
		
	} else if (streetaddress.match(/.[!,@,#,$,%,^,&,*,?,_,+,~,-,£,(,)]/)){
		document.getElementById("streetAddressPicture").innerHTML= "<img src='images/incorrect.png' alt= 'Enter postal code! Must contain 5 numbers!'>";
		document.getElementById("streetAddressMessage").innerHTML="S&#252mbolid pole lubatud!";
		return false;
	} 	else {
		document.getElementById("streetAddressPicture").innerHTML= "<img src='images/correct.png' alt= 'Correct!'>";
		document.getElementById("streetAddressMessage").innerHTML="";
		return true;
	}
}
/*
 * Validate Phone number. Checking for numbers, length is 5 and empty field. 
 *  */

function validatePostalCode(){ 
	
	var postalcode = document.forms["registration_form"]["registerPostalcode"].value;
	
	if (postalcode==null || postalcode==""){
		document.getElementById("postalcodePicture").innerHTML= "<img src='images/incorrect.png' alt= 'Enter postal code! Must contain 5 numbers!'>";
		document.getElementById("postalcodeMessage").innerHTML="Palun sisestage 5-numbriline postiindeks.";
		return false;
	} else if( postalcode.indexOf(" ") !== -1 ){
		document.getElementById("postalcodePicture").innerHTML= "<img src='images/incorrect.png' alt= 'Enter postal code! Must contain 5 numbers!'>";
		document.getElementById("postalcodeMessage").innerHTML="T&#252hikud pole lubatud!";
		return false;
	} else if (postalcode.indexOf("+") !== -1 ){
		document.getElementById("postalcodePicture").innerHTML= "<img src='images/incorrect.png' alt= 'Enter postal code! Must contain 5 numbers!'>";
		document.getElementById("postalcodeMessage").innerHTML="S&#252mbolid pole lubatud!";
		return false;
	} else {
		if (isNaN(postalcode)){
			document.getElementById("postalcodePicture").innerHTML = "<img src='images/incorrect.png' alt= 'Incorrect value! Enter 5 numbers!'>";
			document.getElementById("postalcodeMessage").innerHTML="Palun sisestage 5-numbriline postiindeks";
			return false;
		} else{
			if (postalcode.length==5){
				document.getElementById("postalcodePicture").innerHTML = "<img src='images/correct.png' alt= 'Correct!'>";
				document.getElementById("postalcodeMessage").innerHTML="";
				return true;
			} else if (postalcode.length<5){
				document.getElementById("postalcodePicture").innerHTML = "<img src='images/incorrect.png' alt= 'Too short! Enter 5 numbers!'>";
				document.getElementById("postalcodeMessage").innerHTML="Liiga l&#252hike! Palun sisestage 5 numbrit!";
				return false;
			} else {
				document.getElementById("postalcodePicture").innerHTML = "<img src='images/incorrect.png' alt= 'Too long! Enter 5 numbers!'>";
				document.getElementById("postalcodeMessage").innerHTML="Liiga pikk! Palun sisestage 5 numbrit!";
			}
		}
	}
}

/*
 * Validate Phone number. Checking if only numbers or empty field. 
 *  */
function validatePhoneNumber(){
	
	var phone = document.forms["registration_form"]["registerPhone"].value;
	if (phone==null || phone==""){
		document.getElementById("phonePicture").innerHTML="<img src='images/incorrect.png' alt= 'Enter phone number!'>";
		document.getElementById("phoneMessage").innerHTML="Palun sisestage telefoninumber";
		return false;
	} else if( phone.indexOf(" ") !== -1 ){
		document.getElementById("phonePicture").innerHTML="<img src='images/incorrect.png' alt= 'Enter phone number!'>";
		document.getElementById("phoneMessage").innerHTML="T&#252hikud pole lubatud!";
		return false;
	} else if (phone.indexOf("+")!== -1){
		document.getElementById("phonePicture").innerHTML= "<img src='images/incorrect.png' alt= 'Enter postal code! Must contain 5 numbers!'>";
		document.getElementById("phoneMessage").innerHTML="S&#252mbolid pole lubatud!";
		return false;
	} else {
		/*var numericExpression = /^[0-9]+$/;*/
		if (isNaN(phone)){
			document.getElementById("phonePicture").innerHTML = "<img src='images/incorrect.png' alt= 'Incorrect phone number!'>";
			document.getElementById("phoneMessage").innerHTML="Palun sisestage ainult numbreid.";
			return false;
		} else{
			document.getElementById("phonePicture").innerHTML = "<img src='images/correct.png' alt= 'Correct!'>";
			document.getElementById("phoneMessage").innerHTML="";
			return true;
		}
	}
}

/*
 * Validating Region. Checking if something else than "Region" is selected.
 * */
function validateRegion(){
	var region  = document.forms["registration_form"]["registerRegion"].value;
	if (region == "0") {
		document.getElementById("registerRegionPicture").innerHTML = "<img src='images/incorrect.png' alt= 'Select region!'>";
		document.getElementById("registerRegionMessage").innerHTML= "Palun valige regioon!";
		return false;
		
	} else {
		document.getElementById("registerRegionPicture").innerHTML =  "<img src='images/correct.png' alt= 'Correct!'>";
		document.getElementById("registerRegionMessage").innerHTML="";
		return true;
	}
	
}

/*
 * Validating County. Checking if something else than "County" is selected. 
 * Opens City dropdown for selection. 
 * Displayes all counties.
 * */
function validateCounty(){
	var county  = document.forms["registration_form"]["registerCounty"].value;
	if (county == "0") {
		document.getElementById("registerCountyPicture").innerHTML = "<img src='images/incorrect.png' alt= 'Select county!'>";
		document.getElementById("registerCountyMessage").innerHTML= "Palun valige maakond!";
		return false;
	} else {
		document.getElementById("registerCountyPicture").innerHTML =  "<img src='images/correct.png' alt= 'Correct!'>";
		document.getElementById("registerCountyMessage").innerHTML="";
		return true;
	}
	
}

/*
 * Validating City. Checking if something else than "City" is selected.
 * Displayes all the cities.
 */
function validateCity(){
	var city = document.forms["registration_form"]["registerCity"].value;
	if (city == "0") {
		document.getElementById("registerCityPicture").innerHTML = "<img src='images/incorrect.png' alt= 'Select city!'>";
		document.getElementById("registerCityMessage").innerHTML= "Palun valige linn!";
		
		return false;
	} else {
		document.getElementById("registerCityPicture").innerHTML =  "<img src='images/correct.png' alt= 'Correct!'>";
		document.getElementById("registerCityMessage").innerHTML="";
		/*validateForm();*/
		return true;
	}
	
}


/* function to validate if it is ready to submit
 *  works only if some filed is change in form
 *  for example phonenumber field
 *  it should validate
 */
function validateForm(){ /*do you work?*/
	if (validateEmail()){
		document.getElementById("emailErrorMessage").innerHTML= "";
		if (confirmEmail()){
			document.getElementById("confirmEmailMessage").innerHTML= "";
			if (validatePassword()){
				document.getElementById("passwordMessage").innerHTML= "";
				if (confirmPassword()){
					document.getElementById("confirmPasswordMessage").innerHTML= "";
					if (validateBureauName()){
						document.getElementById("bureauNameMessage").innerHTML= "";
						if (validateRegistrycode()){
							document.getElementById("regcodeMessage").innerHTML= "";
							if (validateStreetAddress()){
								document.getElementById("streetAddressMessage").innerHTML= "";
								if (validatePostalCode()){
									document.getElementById("postalcodeMessage").innerHTML= "";
									if (validatePhoneNumber()){
										document.getElementById("phoneMessage").innerHTML= "";
										var region = document.forms["registration_form"]["registerRegion"].value;
										if (region != 0){
											document.getElementById("registerRegionMessage").innerHTML= "";
											var county = document.forms["registration_form"]["registerCounty"].value;
											if(county != 0){
												document.getElementById("registerCountyMessage").innerHTML= "";
												var city = document.forms["registration_form"]["registerCity"].value;
												if (city!= 0){
													document.getElementById("confirmInput").innerHTML = "<img src='images/correct.png' alt= 'Correct'>";
													document.getElementById("registerCityMessage").innerHTML= "";
													return true;
												} else{
													document.getElementById("registerCityMessage").innerHTML= "Palun valige linn.";
													document.getElementById("confirmInput").innerHTML = "<img src='images/incorrect.png' alt= ''>";
												}
											} else{
												document.getElementById("registerCountyMessage").innerHTML= "Palun valige maakond.";
												document.getElementById("confirmInput").innerHTML = "<img src='images/incorrect.png' alt= ''>";
											}
										} else {
											document.getElementById("registerRegionMessage").innerHTML= "Palun valige regioon.";
										}
									} else {
										document.getElementById("phoneMessage").innerHTML= "Palun sisestage telefoninumber.";
										document.getElementById("confirmInput").innerHTML = "<img src='images/incorrect.png' alt= ''>";
									}
								} else {
									document.getElementById("postalcodeMessage").innerHTML= "Palun sisestage postiindeks.";
									document.getElementById("confirmInput").innerHTML = "<img src='images/incorrect.png' alt= ''>";
								}
							} else {
								document.getElementById("streetAddressMessage").innerHTML= "Palun sisestage aadress.";
								document.getElementById("confirmInput").innerHTML = "<img src='images/incorrect.png' alt= ''>";
							}
						} else {
							document.getElementById("regcodeMessage").innerHTML= "Palun sisestage registrikood.";
							document.getElementById("confirmInput").innerHTML = "<img src='images/incorrect.png' alt= ''>";
						}
					} else {
						document.getElementById("bureauNameMessage").innerHTML= "Palun sisestage b&#252roonimi.";
						document.getElementById("confirmInput").innerHTML = "<img src='images/incorrect.png' alt= ''>";
					}
				} else {
					document.getElementById("confirmPasswordMessage").innerHTML= "Palun kinnitage parool.";
					document.getElementById("confirmInput").innerHTML = "<img src='images/incorrect.png' alt= ''>";
				}
			} else{
				document.getElementById("passwordMessage").innerHTML= "Parooli pikkus on v&#228hemalt 6.";
				document.getElementById("confirmInput").innerHTML = "<img src='images/incorrect.png' alt= ''>";
			}
		}else {
			document.getElementById("confirmEmailMessage").innerHTML= "Kinnitage e-mail!";
			document.getElementById("confirmInput").innerHTML = "<img src='images/incorrect.png' alt= ''>";
		}
	} else {
		document.getElementById("confirmInput").innerHTML = "<img src='images/incorrect.png' alt= ''>";
		document.getElementById("emailErrorMessage").innerHTML= "Palun sisestage e-mail.";
	}
	return false;
}

