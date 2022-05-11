<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h3>도서 정보</h3>
		<button type="button" id="all" class="btn btn-secondary">도서목록</button>
		<button type="button" id="get" class="btn btn-primary">도서조회</button>
		<button type="button" id="delete" class="btn btn-danger">도서삭제</button>
		<button type="button" id="update" class="btn btn-success">도서수정</button>
		
		<div id="result">
			<table class="table">
				
			</table>
		</div>
		
		<div>
			<form method="post">
				<div>
					<label>코드</label>
					<input type="text" name="code" id="code" />
				</div>
				<div>
					<label>제목</label>
					<input type="text" name="title" id="title" />
				</div>
				<div>
					<label>저자</label>
					<input type="text" name="writer" id="writer" />
				</div>
				<div>
					<label>가격</label>
					<input type="text" name="price" id="price" />
				</div>
				<div>
					<button type="button" id="insert">입력</button>
				</div>
			</form>
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(function() {
			$("#all").click(function() {
				// AJAX 방식으로 데이터 요청
				$.ajax({
					url:'',
					method:'',
					data: {
						
					},
					dataType:'JSON',
					success:function() {
						// server 응답 코드(HTTP 상태 코드)가 200일 때
					}
				})
				
				// 가져올 데이터가 JSON이라면
				$.getJSON({
					url:'list',
					success:function(data) {
						// 본문 TABLE 영역 변경하기
						let result = $("#result table");
						let str = "";
						$.each(data, function(index, item) {
							str += "<tr>";
							str += "<td>" + item.code + "</td>";
							str += "<td>" + item.title + "</td>";
							str += "<td>" + item.writer + "</td>";
							str += "<td>" + item.price + "</td>";
							str += "</tr>";
						})
						result.html(str);
					}
				})
			}) /* all click */
			
			// /book/1000 + GET => 도서코드가 1000번인 도서 정보 가져오기
			$("#get").click(function() {
				$.getJSON({
					url: '1000',
					success:function(item) {
						let result = $("#result table");
						let str = "";
						str += "<tr>";
						str += "<td>" + item.code + "</td>";
						str += "<td>" + item.title + "</td>";
						str += "<td>" + item.writer + "</td>";
						str += "<td>" + item.price + "</td>";
						str += "</tr>";
						
						result.html(str);
					}
				})
			}) /* get click */
			
			$("#delete").click(function() {
				$.ajax({
					url:'2002',
					type: 'delete', // @DeleteMapping과 연결
					success: function(data) {
						alert(data);
					},
					error:function(xhr, status, error) {
						alert(xhr.responseText);
					}
				})
			}) /* delete click */
			
			$("#update").click(function() {
				let param = {price:100000};
				
				$.ajax({
					url:'2000',
					type:'put', // @PutMapping과 연결
					contentType:'application/json', // 보내는 타입(MediaType)을 지정하지 않으면 application/x-www-form-urlencoded로 가기 때문에 json으로 보낸다고 알려줘야 함
					data:JSON.stringify(param), // JSON.stringify() : 자바스크립트 객체를 JSON 형태로 반환
					success: function(data) {
						alert(data);
					},
					error:function(xhr, status, error) {
						alert(xhr.responseText);
					}
				})
			}) /* update click */
			
			$("#insert").click(function() {
				let param = {
						code:$("#code").val(),
						title:$("#title").val(),
						writer:$("#writer").val(),
						price:$("#price").val(),
				};
				
				/*
				데이터를 JSON 형태로 보낼 때
				$.ajax({
					url:'new',
					type:'post',
					contentType:'application/json',
					data:JSON.stringify(param),
					success: function(data) {
						alert(data);
					},
					error:function(xhr, status, error) {
						alert(xhr.responseText);
					}
				})
				*/
				
				$.ajax({
					url:'new2',
					type:'post',
					data:$("form").serialize(),
					success: function(data) {
						alert(data);
					},
					error:function(xhr, status, error) {
						alert(xhr.responseText);
					}
				})
			}) /* insert click */
		})
	</script>
</body>
</html>