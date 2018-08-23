

function checkAname(){
	
	var msg = "";
	var result = true;
	var data =$("#aname").val();
	var reg = /^[a-zA-Z가-힣0-9]{1,20}$/;  //표현식
	if(data == ""){
		msg = blankMsg;
		result= false;
	}
	
	if(!reg.test(data) && result == true){
		msg = wrongMsg;
		msg += " 1~20 글자 영어,한글,숫자만 입력하세요.";
		result= false;
	}
	$("#anameMsg").html(msg);
	return result;
}

function checkAddr(){
	var msg = "";
	var result = true;
	var data =$("#addr").val();
	if(data == ""){
		msg = blankMsg;
		result= false;
	}
	$("#addrMsg").html(msg);
	return result;
}


function checkTel(){
	var msg = "";
	var result = true;
	var data =$("#tel").val();
	var reg = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}$/;  //표현식
	if(data == ""){
		msg = blankMsg;
		result= false;
	}
	if(!reg.test(data) && result != false){
		msg = wrongMsg;
		result= false;
	}
	$("#telMsg").html(msg);
	return result;
}

function checkCheckIn(){
	var msg = "";
	var result = true;
	var data =$("#acheckin").val();
	if(data == ""){
		msg = blankMsg;
		result= false;
	}
	$("#checkinMsg").html(msg);
	return result;
}

function checkCheckOut(){
	var msg = "";
	var result = true;
	var data =$("#acheckout").val();
	if(data == ""){
		msg = blankMsg;
		result= false;
	}
	$("#checkoutMsg").html(msg);
	return result;
}

function checkMaxsleepdate(){
	var msg = "";
	var result = true;
	var data =$("#maxsleepdate").val();
	if(data == ""){
		msg = blankMsg;
		result= false;
	}
	$("#maxsleepdateMsg").html(msg);
	return result;
}

function checkMaxreservedate(){
	var msg = "";
	var result = true;
	var data =$("#maxreservedate").val();
	if(data == ""){
		msg = blankMsg;
		result= false;
	}
	$("#maxreservedateMsg").html(msg);
	return result;
}



function checkBlankForm(){
	var type =$("#typeSelector option:selected").val();
	var aname = $("#aname").val();
	var addr = $("#addr").val();
	var tel = $("#tel").val();
	var checkin = $("#acheckin").val();
	var checkout = $("#aheckout").val();
	var maxsleepdate =  $("#maxsleepdate").val();
	var maxreservedate =  $("#maxreservedate").val();
	var img1 =  $("#img1").val();
	var img2 =  $("#img2").val();
	var img3 =  $("#img3").val();
	var info = "";
	var notice =  $("#notice").val();
	$("input[name=info]:checked").each(function() {
		  info += $(this).val() +":";
	});

}
