package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;
import javax.xml.ws.Response;

import org.apache.commons.lang.StringUtils;

import kr.or.ddit.enumpkg.OperatorType;
@WebServlet("/calculator.do")
public class CalculatorServlet extends HttpServlet {
	
	@FunctionalInterface
	public static interface MakeResult{
		public String makeResult(double result);
	}
	
	@FunctionalInterface
	public static interface MakeOperator{
		public double makeOp(String op, double leftOp, double rightOp);
		
	}
	
	public static enum CalculateType{
		
		PLAIN("text/plain;charset=UTF-8",(result)->{return result+"";}), 
		JSON("application/json;charset=UTF-8",(result)->{return "{\"result\":"+result+"}";}), 
		HTML("text/html;charset=UTF-8",(result)->{return "<span>"+result+"</span>";}),
		XML("application/xml;charset=UTF-8", (result)->{return "<result>"+result+"</result>";});
		
		private String mimeText;
		private MakeResult realMaker;
		private MakeOperator realOp;
		
		
		//text의 값을 구성할수있는 요소는 생성자 
		private CalculateType(String mimeText, MakeResult realMaker) {
			this.mimeText=mimeText;
			this.realMaker= realMaker;
			
		}
		
		//context타입을 어떤 식으로 내보낼것인가
		public String getResult(double leftOp, double rightOp, String realOp) {
			//고정값을 줄수없고 행동의 값을 주자
			//operator : 사칙연산 if문을 돌리거나, enum, functionalInterface를 이용 
			double result =0;
			result = new MakeOperator() {
					
			@Override
				public double makeOp(String op, double leftOp, double rightOp) {
				
					return leftOp+op+rightOp;
				}
			}
		};
			
			
			
			
//			if(operator.equals("+")) {
//				result =leftOp+rightOp;
//				
//			}else if(operator.equals("-")) {
//				result =leftOp+rightOp;
//				
//			}else if(operator.equals("*")) {
//				result =leftOp+rightOp;
//				
//			}else if(operator.equals("/")) {
//				result =leftOp+rightOp;
//				
//			}
			
			return realMaker.makeResult(result);
		}
		
		public String getMimeText() {
			
			return mimeText;
		}

		//외부에서 객체를 생성할 수 없기에 static
		public static CalculateType matches(String accept) {
			CalculateType matchedType = HTML;
			CalculateType[] types = values();
			accept = accept.toUpperCase();
			
			for(CalculateType tmp: types) {
				if(accept.contains(tmp.name())) {
					matchedType = tmp;
					break;
				}
			}
			return matchedType;
		}
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String content = null;
		
		//파라미터 확보 
		String operator = req.getParameter("operator");
		OperatorType opType	=null;	
		//플래그변수
		boolean valid = true;
		//플래그변수 없다면 try가 복잡해져
		
		//유효한값이 돌아오면 검증 완료, 유효하지 못한 값이 돌아오면 검증 실패
		//없다면 예외 발생, 있으면 상수 리턴
		
		try {
			 opType = OperatorType.valueOf(operator);
		}catch (IllegalArgumentException e) {
			valid= false;
		}
		String leftOp = req.getParameter("leftOp");
		String rightOp = req.getParameter("rightOp");
		
		double left = 1;
		double right= 2;
		//검증 필수파라미터 전송 여부, 데이터 타입
		//불통과 =sendError.400 
		if(StringUtils.isBlank(rightOp)||!StringUtils.isNumeric(rightOp)||StringUtils.isBlank(leftOp)||!StringUtils.isNumeric(leftOp)) {
			//resp.sendError(400,"제대로 입력해주세요");
			content="제대로 입력해주세요";
			
		}else {
		
			//통과 = 연산하고 결과 생성(html)
			left = Double.parseDouble(leftOp);
			right= Double.parseDouble(rightOp);
			
//			//double res = left+right;
//			
//			//ajax데이터 타입에 따라 변경해줘야됨 
//			//마임 ajax데이터 타입을 불러와서 타입을 설정
			String accept =req.getHeader("Accept");
			CalculateType type= CalculateType.matches(accept);
			String mime = type.getMimeText();
			
			String result = type.getResult(left, right, "+");
			
			
//			if(accept.contains("plain")) {
//				mime = "text/plain;charset=UTF-8";
//				content =res+"";
//				
//			}else if(accept.contains("json")) {
//				mime = "application/json;charset=UTF-8";
//				content ="{\"result\":"+res+"}";
//				
//			}else {
//				mime = "text/html;charset=UTF-8";
//				content ="<span>"+res+"</span>";
//			}
		
		//}
		
		resp.setContentType(mime);
		
		try(
			PrintWriter writer = resp.getWriter();	
		){
			writer.print(result);
			//writer.print(content);
		}
		
		
	
	}
}
