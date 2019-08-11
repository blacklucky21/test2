<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
<title>Insert title here</title>
</head>
<body>
	<h2>이름 필드 자동 완성</h2>
	<h3>회원 이름 검색1</h3>
	회원 명 :
	<input id="memberName" type='text'>
	<script type="text/javascript">
		//http://code.jquery.com/ ->jQuery UI탭 클릭-> UI1.12 uncompressed 스크립트 복사 
		/* script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js" 이거임*/

		$(function() {
			var names = [ "이민호", "김영희", "오영심", "이민기", "홍길동" ];
			//회원 이름을 저장한 배열

			$('#memberName').autocomplete({
				// id memberName 에 자동완성 기능을 생성할건데~ source 는 names 로 할것임
				source : names
			});
			//여기까지 하면 UI개구림.. 아까 홈페이지에서 smoothness 들어가서 URL복사
			// 한게 http://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css 이거
			// link rel="stylesheet"~로 head에 넣음

		});
	</script>

	<h3>회원 이름 검색 2</h3>
	<!-- json갔다오기! -->
	회원 명 :
	<input id="memberName2" type="text">
	<script type="text/javascript">
		$(function() {
			$.ajax({
				url : 'autoComplete.do',
				//AutoCompleteServlet
				type : 'get',
				success : function(data) {
					var names = new Array();
					//새로운 배열
					for (var i = 0; i < data.length; i++) {
						names[i] = data[i].userName;
					}
					$('#memberName2').autocomplete({
						source : names
					});
				}
			});
		})
	</script>
</body>
</html>