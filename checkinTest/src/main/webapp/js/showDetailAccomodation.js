
	
	//<!-- 클릭시 이미지 fa fa-caret-up 이걸로 바뀜 -->
	function whenClickReview() {
		console.log("review...click..");
		var upScroll = "<strong><i class='fa fa-caret-down' style='font-size:20px'></i> 리뷰</strong>";
		var downScroll = "<strong><i class='fa fa-caret-up' style='font-size:20px'></i> 리뷰</strong>";
		
		if ($("#reviewStatus").val() == 'up') {
			ajax_review(1);
			$("#showReview").html(downScroll);
			$("#reviewStatus").val('down');
			$("#review").css("display", "block");

		} else if ($("#reviewStatus").val() == "down") {
			
			$("#showReview").html(upScroll);
			$("#reviewStatus").val("up");
			$("#review").css("display", "none");

		}
	}

	function ajax_review(r){
		var params = "r="+r;
		$.ajax({
			type : "get",
			url : "/webapp/main/getReivews",
			data : params,
			dataType : 'json',
			contentType : 'applicaiton/json;charset=UTF-8',
			success : function(result) {
				alert("갔다 왔음");
				
			},
			error : function(e) {
				console.log(e.responseText);
				alert("죄송합니다. 리뷰 불러오기에 실패 했습니다.");
			}
		})
		
		
	}
	
	function whenClickAccor(id){
		var accor = document.getElementById(id);
		if(accor.className.indexOf("w3-hide") != -1){ //hide일때
			accor.className = accor.className.replace(" w3-hide"," w3-show");
		}else{
			accor.className = accor.className.replace(" w3-show"," w3-hide");
		}
	}
	