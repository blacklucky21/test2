package ajax.jquery.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jQueryTest3.do")
public class JQueryAjaxServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JQueryAjaxServlet3() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int firstNum = Integer.parseInt(request.getParameter("firstNum"));
		int secondNum = Integer.parseInt(request.getParameter("secondNum"));
		
		int result = firstNum+secondNum;
	
		response.getWriter().println(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
