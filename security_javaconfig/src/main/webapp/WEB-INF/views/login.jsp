<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<link rel="stylesheet" href="/resources/css/login.css" />
</head>
<body>
<%--
	Customer Login Page 작성 규칙
	1) action="/login"으로 작성
	2) userid를 입력 받는 부분의 요소명은 username으로 반드시 지정
	3) 비밀번호를 입력 받는 부분의 요소명은 password로 반드시 지정
	4) POST 방식으로 가는 form은 CSRF Token 값을 반드시 포함해야 한다
	
	CSRF(cross-site request forgery) 공격
	- 서버가 신뢰하는 사용자를 통해 공격자가 원하는 명령을 대신 실행하게 함
	- 서버가 요청을 받아들이고 처리할 때 요청의 출처를 따지지 않기 때문에 그러한 허점을 노리는 공격 방식
	
	접근 제한
	1) 환경설정 파일에서 intercept-url 
--%>
	<div class="container center-contents">
		<div class="row">
			<form action="/login" method="post" class="form-signin">
				<h1 class="h2 mb-3 font-weight-normal">로그인</h1>
				<label for="username" class="sr-only">아이디</label>
				<input type="text" id="username" name="username" class="form-control" placeholder="아이디를 입력해 주세요" required autofocus />
				<label for="password" class="sr-only">비밀번호</label>
				<input type="password" id="password" name="password" class="form-control" placeholder="비밀번호를 입력해 주세요" required autofocus />
				<div class="checkbox mb-3">
					<label>
						<input type="checkbox" name="remember-me" />로그인 유지하기
					</label>
				</div>
				<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<div class="error-message">
					<span>${loginError}</span>
				</div>
			</form>
		</div>
	</div>
</body>
</html>