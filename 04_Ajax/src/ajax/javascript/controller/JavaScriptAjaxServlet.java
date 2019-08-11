package ajax.javascript.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/javascript/textAjax.do")
public class JavaScriptAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JavaScriptAjaxServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	
		//2. 파라미터 값 가져오기(파라미터 핸들링)
		String name = request.getParameter("name");
		int height = Integer.parseInt(request.getParameter("height"));
		
		//3. 응답 객체 출력
		PrintWriter out = response.getWriter();
		out.append("전달받은 파라미터<br>")
			.append("name = "+name+"<br>")
			.append("height = "+  height);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
