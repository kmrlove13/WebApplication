<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<%

	
%>
<body>
	<!--form의 주소는 절대방식, -->
	<form action ="<%=request.getContextPath() %>/login/loginCheck.jsp" method="post">
		<ul><!--VALUE> 초기값 설정, 널값이면 화이트스페이스로 바꿔주기-->
			<%
				String mem_id = request.getParameter("mem_id");
			%><!--Objects.toString(mem_id,"") 널문자 대신에 ""사용 할수 있음 이 메소드는 -->
			<li>ID : <input type="text" name ="mem_id" value="<%=Objects.toString(mem_id,"")%>"/></li>
			<li>PASS : <input type="text" name="mem_pass"/></li>
			<li><input type="submit" value ="로그인" /></li>	
		
		</ul>
	</form>

</body>
</html>