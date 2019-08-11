package ajax.jquery.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ajax.jquery.model.vo.User;

@WebServlet("/jQueryTest6.do")
public class jQueryAjaxServlet6 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public jQueryAjaxServlet6() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		JSONObject userobj = null;
		JSONArray userArr = new JSONArray();
		// 여러개의 JASONObject를 담는 역할을 함!
		
		if(user!=null) {
			userobj = new JSONObject();
			userobj.put("userNo",user.getUserNo());
			userobj.put("userNAme", user.getUserName());
			userobj.put("userNation", user.getUserNation());
			
			userArr.add(userobj);
		}else {
			for(User userInfo : userList) {
				userobj = new JSONObject();
				userobj.put("userNo",userInfo.getUserNo());
				userobj.put("userNAme", userInfo.getUserName());
				userobj.put("userNation", userInfo.getUserNation());
				
				userArr.add(userobj);
			}
		}
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(userArr);
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
