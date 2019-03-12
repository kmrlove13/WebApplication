<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>06/sessionTimer.jsp</title>
</head>
<body>
	<%
		int time = session.getMaxInactiveInterval();
		//SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
		//String timeStr =df.format(new Date(time));
	%>
	<%=time%>	
	<SCRIPT src="<%= request.getContextPath()%>/js/jquery-3.3.1.min.js"></SCRIPT>
	<SCRIPT type="text/javascript">
		//1초 단위로 시간을 discounts
		$(function() {
			var timeInfo = $("#timeInfo");
			var time = <%=time%>;
		 
			
		 	setInterval(function(){
		 		--time;
		 		var sec = (time/1000)%60 ;
			 	var min = sec/60;
				
			 	timeInfo.html(sec+":"+min);
			
				
			},1000);
		
		
			/* if(time==60){
				var infoAtt = $("#info").attr();
				
		
			}		 */
		
		
		
		
		});
	
	
	</SCRIPT>
	
	<pre>
	1. 세션의 만료 시간을 출력 02:00 서버사이드 , 나머지는 클라이언트에서 사용
	2. 초 단위로 시간을 discount
	3. 만료시간이 1분이 남은 경우 "연장하시겠습니까 "메세지, 연장 여부 결정 메시지 출력 
	4. 연장을 하는 경우 -타이머 리셋, 단순히 클라이언트 사이드에서 변경이 아니라, 서버사이드에서 변경되야됨 요청을 다시 날려야함 
		서버의 세션을 연장하기 위해 새로운 요청 발생 비동기 요청으로 발생
 	5. 연장을 안하는 경우 -타이머 그대로 disCount
		0초가 남으면 자동으로 로그아웃 상태로 바꿔야됨 새로고침만 하면 로그아웃 상태로 되어있을꺼얌 
		0초가 되는 순간, 현재 페이지가 refresh가 되도록 
	매 1초마다 새로고침 -> interval 함수 
	1분 남아서 새로고침 -> callback 함수
	</pre>	
		
	<div id="msgArea"> <!--/hide, /show-->
		<span id="timeInfo"></span>
		<span id="info" hidden="true">만료시간이 1분 남았는데 연장 할래?</span>
		<br/>
		<input type="button" value="연 장" class="btn" id="yesBtn" hidden="true"/>
		<input type="button" value="취 소" class="btn" id="noBtn" hidden="true"/>
	</div>		
		
</body>
</html>