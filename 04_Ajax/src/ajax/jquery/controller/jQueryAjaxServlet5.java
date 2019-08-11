package ajax.jquery.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import ajax.jquery.model.vo.User;

@WebServlet("/jQueryTest5.do")
public class jQueryAjaxServlet5 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public jQueryAjaxServlet5() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<User> userList = new ArrayList<>();
		userList.add(new User(1, "박신우", "대한민국"));
		userList.add(new User(2, "타일러 러쉬", "미국"));
		userList.add(new User(3, "쯔위", "중국"));
		userList.add(new User(4, "모모", "일본"));
		userList.add(new User(5, "리사", "태국"));
		userList.add(new User(6, "알베르토 몬디", "이탈리아"));
		userList.add(new User(7, "샘 오취리", "가나"));
		
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		
		User user = null;
		for(int i = 0; i<userList.size();i++) {
			if(userList.get(i).getUserNo()==userNo) {
				user = userList.get(i);
				break;
			}
		}
		
		JSONObject userObj = null;
	
		if(user!=null) {
			userObj = new JSONObject();
			
			userObj.put("userNo", user.getUserNo());
			userObj.put("userName",user.getUserName());
			userObj.put("userNation", user.getUserNation());
		}
		System.out.println(userObj);
		
		response.setContentType("application/json; charset = utf-8");
		PrintWriter out = response.getWriter();
		out.println(userObj);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
