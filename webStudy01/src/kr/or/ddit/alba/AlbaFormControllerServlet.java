package kr.or.ddit.alba;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.vo.AlbaVO;


@WebServlet(urlPatterns="/albaForm.do",loadOnStartup=1)
public class AlbaFormControllerServlet extends HttpServlet {
	public static final String MAPATTR = "albaMap";
	//폼으로 연결만 하기때문에 get으로 
	//loadOnStartup 요청이 들어오지 않더라도 얘가 먼저 호출
	@Override
	public void init() throws ServletException {
		super.init();
	//db를 대신할 Map을 미리 호출하기 위해서 , 순서대로 들어갈수 있게 linked
		Map<String, AlbaVO> albaMap = new LinkedHashMap<String, AlbaVO>();
	//application scope로	
		getServletContext().setAttribute(MAPATTR, albaMap);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/WEB-INF/views/alba/albaForm.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}
	
}
