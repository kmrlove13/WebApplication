<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>05/sessionDesc.jsp</title>
</head>
<body>
	<h4>HttpSession session 기본 객체</h4>
	<pre>
		2가지 의미
		통로: db에서의 세션(양피어를 대상으로 길을 터주는 의미, connection), 웹에서는 이 의미를 사용하기엔 부족함(http의 서비스중 connectless때문에)
			
		시간: 한명의 유저가 하나의 브라우저를 사용해서 어플리케이션을 사용시작(최초 요청) 할때부터 더이상 사용하지 않겠다는 종료이벤트가 발생() 할때까지의 기간
		종료이벤트가 발생이라는 이유 : 종료에 대한 원인 불확실성때문에 이벤트라고 명시함 그 원인들: 
			명시적으로 로그아웃 버튼을 선택했을때(쿠키삭제), 세션의 만료시간(일정시간동안 요청이 안들어오면 종료이벤트),  브라우저의 종료,
			더이상 세션 아이디가 서버에 전송되지 않으면 세선 소멸
			각 브라우저마다 쿠키를 저장하기 때문에 
		session 기본 객체: 한 세션 내에서 발생하는 모든 정보들을 캡슐화한 객체. 
						: 세션이 시작될때 객체가 생성되고(섹션ID), 세션이 종료될때 소멸됨
						:산발적인 요청에 의미를 부여하기 위한 단위
			
		*세션 라이프 사이클 
		 	클라이언트로부터 세션아이디가 포함되지 않은 요청(최초의 요청)이 발생하면 세션생성 > 서벅의 세션아이디생성>최초의 요청에 대한 응답에 함께 전송
		 	> 두번째 이후의 요청부터 다시 요청에 포함된 세션 아이디로 세션을 식별하고 유지
							
		생성 시점 : <%=new Date(session.getCreationTime()) %>
		세션 id(식별자) : <%=session.getId() %> %>			
		마지막 접속 시점 :<%= new Date(session.getLastAccessedTime())%>
		세션 만료 시간: <%=session.getMaxInactiveInterval()%>s
					<%--<%=session.setMaxInactiveInterval(4*60)%>  --%> 
		세션 만료 시간 설정 : set으로 설정, web.xml의<session-config> <session-timeout>2</session-timeout></session-config>
		세션 만료 시간: <%=session.getMaxInactiveInterval()%>s
							
							
							
							
	</pre>

</body>
</html>