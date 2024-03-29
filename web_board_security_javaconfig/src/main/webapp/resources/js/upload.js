/**
 * register.jsp, modify.jsp, read.jsp, uploadForm_ajax 스크립트
 */
$(function() {
	$(":file").change(function() {
		console.log("ajax 파일 업로드 호출");
		
		// form 객체 생성
		let formData = new FormData();
		
		// 첨부파일 목록 가져오기
		let inputFile = $("[name='uploadFile']");
		console.log(inputFile);
		
		let files = inputFile[0].files;
		
		// form 객체에 첨부파일 추가
		for(let i = 0; i < files.length; i++) {
			if(!checkExtension(files[i].name, files[i].size)) {
				return false;
			}
			formData.append("uploadFile", files[i]);
		}
		
		// form 객체 ajax로 전송
		/*
			processData:false
			=> 데이터를 일반적인 쿼리 스트링 형태로 변환할 것인지 결정
			=> 기본값은 true (application/x-www-form-urlencoded)			
		*/
		$.ajax({
			url:'/uploadAjax',
			type:'post',
			processData:false,
			contentType:false,
			beforeSend:function(xhr) {
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
			data:formData,
			dataType:'json',
			success:function(result) {
				console.log(result);
				$(":file").val("");
				showUploadFile(result);
			}
		})
	})
	
	// 첨부파일 삭제 (x 동작)
	/*$(".uploadResult").on("click", "span", function() {
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
	})*/
	
	// 이미지 종료
	$(".bigPictureWrapper").on("click", function() {
		$(".bigPicture").animate({width:'0', height:'0'}, 1000);
		setTimeout(function() {
			$(".bigPictureWrapper").hide();
		}, 1000);
	})
})

// 첨부파일 확장자 및 사이즈 확인
function checkExtension(fileName, fileSize) {
	// 확장자
	let regex = new RegExp("(.*?)\.(png|gif|jpg|txt)$");
	
	// 파일 크기
	let maxSize = 3145728; // 3MB
	
	if(!regex.test(fileName)) {
		alert('해당 종류의 파일은 업로드 할 수 없습니다.');
		return false;
	}
	
	if(fileSize > maxSize) {
		alert('3MB을 초과하는 파일은 업로드 할 수 없습니다.');
		return false;
	}
	
	return true;
}

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
			
			str += "<li data-path='" + obj.uploadPath + "' data-uuid='" + obj.uuid + "' data-filename='" + obj.fileName + "' data-type='" + obj.fileType + "'>";
			str += "<a href=\"javascript:showImage(\'" + oriPath + "\')\">";
			str += "<img src='/display?fileName=" + fileCallPath + "'></a>";
			str += "<div>" + obj.fileName;
			str += "<button type='button' class='btn btn-warning btn-circle' data-file='" + fileCallPath + "' data-type='image'>";
			str += "<i class='fa fa-times'></i></button>";
			str += "</div></li>";
		}else { // txt 파일
			let fileCallPath = encodeURIComponent(obj.uploadPath + "\\" + obj.uuid + "_" + obj.fileName); 
			str += "<li data-path='" + obj.uploadPath + "' data-uuid='" + obj.uuid + "' data-filename='" + obj.fileName + "' data-type='" + obj.fileType + "'>";
			str += "<a href='/download?fileName=" + fileCallPath + "'>";
			str += "<img src='/resources/img/attach.png'></a>";
			str += "<div>" + obj.fileName;
			str += "<button type='button' class='btn btn-warning btn-circle' data-file='" + fileCallPath + "' data-type='file'>";
			str += "<i class='fa fa-times'></i></button>";
			str += "</div></li>";
		}			
	});
	
	console.log("업로드 파일 경로 : " + str);
	
	uploadResult.append(str);
}

function showImage(fileCallPath) {
	$(".bigPictureWrapper").css("display", "flex").show();
	
	$(".bigPicture").html("<img src='/display?fileName=" + fileCallPath + "'>").animate({width:'100%', height:'100%'}, 1000);
}