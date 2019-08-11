<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<style>
.test {
	width: 300px;
	min-height: 50px;
	border: 1px solid red;
}
</style>
</head>
<body>
	<h1>JavaScript를 통한 Ajax 구현</h1>

	<br>

	<h3>1. JavaScript ajax 테스트 - GET</h3>
	<button onclick="fn_get_test();">실행</button>
	<p class="test" id="test1"></p>
	<script type="text/javascript">
		var xhr;

		function fn_get_test() {
			//1. XMLHttpRequest 객체 생성
			xhr = getXMLHttpRequest();
			
			//2. open() : 생성한 httpRequest 객체를 이용해 서버 연결
			// open(요청 방식, url주소 및 쿼리 스트링, 비동기화 여부)
			xhr.open('get','<%=request.getContextPath()%>/javascript/textAjax.do?name=park&height=160', true);

			//3. 연결 후 각 연결 상태에 대한 핸들러 함수 지정
			xhr.onreadystatechange = myHandler;

			//4. 전송
			xhr.send(null);
			//get방식은 이미 url에 정보가 담겨있으므로 send(null)로 해도 정보가 잘 감!

		}

		//getXMLHttpRequest 객체 생성 함수
		function getXMLHttpRequest() {
			if (window.ActiveXObject) {//IE일 경우
				try {//IE 9버전 이상일 경우
					return new ActiveXObject("Msxml2.XMLHTTP");
				} catch (ex) {//IE 8버전 이하일 경우
					try {
						return new ActiveXObject("Microsoft.XMLHTTP");
					} catch (ex) {
						return null;
					}
				}
			} else if (window.XMLHttpRequest) {//IE 이외의 브라우저 일 경우
				return new XMLHttpRequest();
			} else {
				return null;
			}
		}

		var myHandler = function() {
			if (xhr.readyState == 4) {//전달이 완료 되었을 때
				if (xhr.status == 200) {//통신 연결도 완료되고, 데이터 전송도 완료된 경우
					console.log("ajax 처리 완료!");
					document.getElementById("test1").innerHTML = xhr.responseText;
				}
			}
		};
	</script>

	<br>

	<h3>2. JavaScript ajax 테스트 - POST</h3>
	<button onclick="fn_post_test();">실행</button>
	<p class="test" id="test2"></p>
	<script type="text/javascript">
		function fn_post_test() {
			var param="name=kim&height=180";
			
			// 1. XMLHttpRequest 객체 생성
			xhr= getXMLHttpRequest();
			
			//2. open()를 이용해 서버 연결
			xhr.open('post','<%=request.getContextPath()%>/javascript/textAjax.do',true);
			xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded;');
			xhr.onreadystatechange = myHandler2;
			xhr.send(param);
		}
		
		var myHandler2 = function(){
			if (xhr.readyState == 4) {//전달이 완료 되었을 때
				if (xhr.status == 200) {//통신 연결도 완료되고, 데이터 전송도 완료된 경우
					console.log("ajax 처리 완료!");
					document.getElementById("test2").innerHTML = xhr.responseText;
				}
			}
		};
	</script>
	<hr>

	<h1>JQuery를 통한 Ajax 구현</h1>

	<h3>1. 버튼 선택 시 전송 값 서버에 출력</h3>
	이름 :
	<input type="text" id="myName">
	<button id="nameBtn">이름 전송</button>

	<br>

	<h3>2. 버튼 선택 시 서버에서 보낸 값 사용자가 수신</h3>
	<button id="getServerTextBtn">서버에서 보낸 값 확인</button>
	<p class="test" id="p1"></p>

	<br>

	<h3>3. 서버로 기본형 전송 값이 있고, 결과로 문자열을 받아 처리</h3>
	<h4>두 개의 값을 더한 결과를 받아옴</h4>
	첫 번째 숫자 :
	<input type="text" id="firstNum">
	<br> 두 번째 숫자 :
	<input type="text" id="secondNum">
	<br>
	<button id="plusBtn">더하기</button>
	<p class="test" id="p2"></p>

	<br>

	<h3>4. Object 형태의 데이터를 서버에 전송, 서버에서 처리 후 서버 console로 출력</h3>
	학생1 :
	<input type="text" id="student1">
	<br> 학생2 :
	<input type="text" id="student2">
	<br> 학생3 :
	<input type="text" id="student3">
	<br>
	<button id="studentTest">결과확인</button>

	<br>

	<h3>5. 서버로 기본형 데이터 전송, 서버에서 객체 반환</h3>
	<h4>유저 번호 보내서 해당 유저 정보 가져오기</h4>
	유저 번호 :
	<input type="text" id="userNo">
	<br>
	<button id="getUserInfoBtn">정보 가져오기</button>
	<p class="test" id="p3"></p>
	<textarea class="test" id="textarea3" cols="40" rows="5"></textarea>

	<br>

	<h3>6. 서버로 기본 값 전송, 서버에서 리스트 객체 반환</h3>
	<h4>유저 번호 요청 --> 해당 유저가 있는 경우 유저 정보, 없는 경우 전체 가져오기</h4>
	유저 번호 :
	<input type="text" id="userNo2">
	<br>
	<button id="getUserInfoBtn2">정보 가져오기</button>
	<p class="test" id="p4"></p>
	<textarea class="test" id="textarea4" cols="40" rows="5"></textarea>

	<br>

	<h3>7. 서버로 데이터 여러 개 전송, 서버에서 리스트 객체 반환</h3>
	<h4>유저 번호 전송 --> 현재 있는 유저 정보만 모아서 출력</h4>
	<h4>10이상의 숫자는 ','로 쓸 수 없다고 설정</h4>
	유저 정보(번호,번호,번호) :
	<input type="text" id="userNo3">
	<br>
	<button id="getUserInfoBtn3">정보 가져오기</button>
	<br>
	<textarea class="test" id="textarea5" cols="40" rows="5"></textarea>

	<br>

	<h3>8. 서버로 데이터 여러 개 전송, 서버에서 맵 형태의 객체 반환</h3>
	<h4>유저 이름 전송 --> 현재 있는 유저 정보만 모아서 출력</h4>
	유저 정보(이름,이름,이름) :
	<input type="text" id="userName">
	<br>
	<button id="getUserInfoBtn4">정보 가져오기</button>
	<br>
	<br>
	<textarea id="textarea6" class="test" cols="40" rows="5"></textarea>

	<br>

	<h3>9. 서버 유저 정보로 표 구성하기</h3>
	<button id="userInfoBtn">유저 정보 불러오기</button>
	<br>
	<br>
	<table id="userInfoTable" border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>국적</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>

	<br>

	<h3>10. 서버에서 List 객체 반환 받아 select 태그를 이용해서 보여줌</h3>
	유저 이름 :
	<input type="text" id="selectUserName">
	<button id="selectListBtn">9번 예제</button>
	<br>
	<select id="selectListTest"></select>

	<br>

	<h3>11. Gson을 이용한 List 반환</h3>
	<button id="gsonListBtn">list 가져오기</button>
	<select id="gsonListSelect"></select>

	<br>

	<h3>12. Gson을 이용한 Map 반환</h3>
	<button id="gsonMapBtn">Map 가져오기</button>
	<select id="gsonMapSelect"></select>

	<script src="ajax/jquery.js"></script>

	<hr>

	<a href="autoComplete.jsp">자동완성 예제</a>
</body>
</html>