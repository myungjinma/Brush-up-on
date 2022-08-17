<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
</head>
<body>
<script>
	if(confirm("로그아웃 하시겠습니까?") == true){
		window.location.href='./mainPage.html';
		
	}else {
		history.back();
	}
</script>
</body>
</html>