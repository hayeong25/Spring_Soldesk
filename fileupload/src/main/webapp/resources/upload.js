/**
 * uploadForm_ajax 스크립트
 */
$(function() {
	$("#uploadBtn").click(function() {
		console.log("ajax 파일 업로드 호출");
		
		// form 객체 생성
		let formData = new FormData();
		
		// 첨부파일 목록 가져오기
		let inputFile = $("[name='uploadFile']");
		console.log(inputFile);
		
		let files = inputFile[0].files;
		
		// form 객체에 첨부파일 추가
		for(let i = 0; i < files.length; i++) {
			formData.append("uploadFile", files[i]);
		}
		
		// form 객체 ajax로 전송
		/*
			processData:false
			=> 데이터를 일반적인 쿼리 스트링 형태로 변환할 것인지 결정
			=> 기본값은 true (application/x-www-form-urlencoded)			
		*/
		$.ajax({
			url:'uploadAjax',
			type:'post',
			processData:false,
			contentType:false,
			data:formData,
			dataType:'json',
			success:function(result) {
				showUploadFile(result);
			}
		})
	})
	
	function showUploadFile(result) {
		let uploadResult = $(".uploadResult ul");
		let str = "";
		
		$(result).each(function(idx, obj) {
			if(obj.fileType) { // 이미지 파일
				// 썸네일 이미지 경로
				let fileCallPath = encodeURIComponent(obj.uploadPath + "\\s_" + obj.uuid + "_" + obj.fileName);
				
				// 원본 파일 이미지 경로
				let oriPath = obj.uploadPath + "\\" + obj.uuid + "_" + obj.fileName;
				oriPath = oriPath.replace(new RegExp(/\\/g), "/");
				
				str += "<li><a href=\"javascript:showImage(\'" + oriPath + "\')\">";
				str += "<img src='/display?fileName=" + fileCallPath + "'></a>";
				str += "<div>" + obj.fileName + "<span data-file='" + fileCallPath + "' data-type='image'> x </span>";
				str += "</div></li>";
			}else { // txt 파일
				let fileCallPath = encodeURIComponent(obj.uploadPath + "\\" + obj.uuid + "_" + obj.fileName); 
				str += "<li><a href='/download?fileName=" + fileCallPath + "'>";
				str += "<img src='/resources/img/attach.png'></a>";
				str += "<div>" + obj.fileName + "<span data-file='" + fileCallPath + "' data-type='file'> x </span>";
				str += "</div></li>";
			}			
		});
		
		uploadResult.append(str);
	}
	
	// 첨부파일 삭제 (x 동작)
	$(".uploadResult").on("click", "span", function() {
		// span 태그 data- 속성 가져오기
		let targetFile = $(this).data("file");
		let type = $(this).data("type");

		// span 태그가 속해 있는 li 태그 가져오기
		let targetLi = $(this).closest("li");		
		$.ajax({
			url:'/deleteFile',
			data:{
				fileName: targetFile,
				type:type
			},
			type:'post',
			success:function(result) {
				console.log(result);
				targetLi.remove();
			}
		})
	})
	
	// 이미지 종료
	$(".bigPictureWrapper").on("click", function() {
		$(".bigPicture").animate({width:'0', height:'0'}, 1000);
		setTimeout(function() {
			$(".bigPictureWrapper").hide();
		}, 1000);
	})
})

function showImage(fileCallPath) {
	$(".bigPictureWrapper").css("display", "flex").show();
	
	$(".bigPicture").html("<img src='/display?fileName=" + fileCallPath + "'>").animate({width:'100%', height:'100%'}, 1000);
}