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

@WebServlet("/jQueryTest8.do")
public class JQueryAjaxServlet8 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JQueryAjaxServlet8() {
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

		// j쿼리 data 속성의 userName을 말함
		String userName = request.getParameter("userName");
		// 이름, 이름, 이름
		String[] names = userName.split(",");

		User user = null;
		JSONObject userObj = null;// user을 담을 jsonObject
		JSONObject userMap = new JSONObject();// 클라이언트로 보낼 맵형태의 객체
		// JSONObject이미 맵형태였음//

		for (String name : names) {
			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getUserName().equals(name)) {
					user = userList.get(i);

					userObj = new JSONObject();
					userObj.put("userNo", user.getUserNo());
					userObj.put("userName", user.getUserName());
					userObj.put("userNation", user.getUserNation());

					userMap.put("user-" + name, userObj);
				}
			}
		}
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(userMap);
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
