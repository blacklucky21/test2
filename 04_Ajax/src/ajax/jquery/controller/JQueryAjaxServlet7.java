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

@WebServlet("/jQueryTest7.do")
public class JQueryAjaxServlet7 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JQueryAjaxServlet7() {
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
		
		String userNo = request.getParameter("userNo");
		String[] ids =userNo.split(",");
		//[1,3,5]
		
		JSONArray userArr = new JSONArray();
		for(String id: ids) {
			for(int i = 0 ; i<userList.size();i++) {
				if(Integer.parseInt(id)==userList.get(i).getUserNo()) {
					User user = userList.get(i);
					
					JSONObject userObj = new JSONObject();
					userObj.put("userNo", user.getUserNo());
					userObj.put("userName",user.getUserName());
					userObj.put("userNation", user.getUserNation());
					
					userArr.add(userObj);
				}
			}
		}
		JSONObject result = new JSONObject();
		result.put("list", userArr);
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
