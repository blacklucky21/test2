// 1번

$('#nameBtn').click(function() {
	var name = $('#myName').val();
	$.ajax({
		url : 'jQueryTest1.do',
		data : {
			name : name
		},
		type : 'get',
		success : function(data) {
			console.log("서버 성공 시 호출되는 함수");
		},
		error : function(data) {
			console.log("서버 전송 실패 시 호출되는 함수");
		},
		complete : function(data) {
			console.log("무조건 호출되는 함수");
		}
	});
});// 1번 끝!

// 2번
$('#getServerTextBtn').click(function() {
	$.ajax({
		url : 'jQueryTest2.do',
		type : 'get',
		success : function(data) {
			$('#p1').text(data);
		},
		error : function(data) {
			console.log('실패');
		}
	});
});

// 3번
$('#plusBtn').click(function() {
	var firstNum = $('#firstNum').val();
	var secondNum = $('#secondNum').val();

	$.ajax({
		url : 'jQueryTest3.do',
		type : 'get',
		data : {
			firstNum : firstNum,
			secondNum : secondNum
		},
		success : function(data) {
			$('#p2').text(data);
		}
	});
});
// 3번 끝!

// 4번
$('#studentTest').click(function() {
	var student1 = $('#student1').val();
	var student2 = $('#student2').val();
	var student3 = $('#student3').val();

	var students = {
		student1 : student1,
		student2 : student2,
		student3 : student3
	};

	$.ajax({
		url : "jQueryTest4.do",
		type : "get",
		data : students,
		success : function(data) {
			console.log('성공');
		}
	});
});

// 5번
$('#getUserInfoBtn').click(
		function() {
			var userNo = $('#userNo2').val();
			$.ajax({
				url : 'jQueryTest5.do',
				data : {
					userNo : userNo
				},
				type : 'get',
				success : function(data) {
					var reusltStr = "";
					if (data != null) {
						resultStr = data.userNo + ", " + data.userName + ", "
								+ data.userNation;
					} else {
						resultStr = "해당 회원이 없습니다.";
					}
					$("#p3").text(resultStr);
					$("#textarea3").val(resultStr);
				},
				error : function(data) {
					console.log("실패");
				}
			});
		});

// 6번
$('#getUserInfoBtn2').click(
		function() {
			var userNo = $('#userNo2').val();

			$.ajax({
				url : 'jQueryTest6.do',
				type : 'get',
				data : {
					userNo : userNo
				},
				success : function(data) {
					var resultStr = "";

					for ( var i in data) {
						var user = data[i];
						resultStr += user.userNo + ", " + user.userName + ", "
								+ user.userNation + "\n";
					}
					$("#p4").text(resultStr);
					$("#textarea4").val(resultStr);
				}
			});
		});

// 7번
$("#getUserInfoBtn3").click(
		function() {
			var userNo = $('#userNo3').val();

			$.ajax({
				url : 'jQueryTest7.do',
				type : 'get',
				data : {
					userNo : userNo
				},
				success : function(data) {
					// data 는 JASONObject가 옴! data.list라고 해야 그 안에있는 list에 접근 할 수
					// 있음!
					var resultStr = "";
					for ( var i in data.list) {
						var user = data.list[i];
						resultStr += user.userNo + ", " + user.userName + ", "
								+ user.userNation + "\n";
					}
					$("#textarea5").val(resultStr);
				}
			});
		});

// 8번
$('#getUserInfoBtn4').click(
		function() {
			$.ajax({
				url : 'jQueryTest8.do',
				// JQueryAjaxServlet8
				type : 'get',
				data : {
					userName : $('#userName').val()
				},
				// 굳이 변수에 담지 않고 접근. 같은방식임
				success : function(data) {
					var resultStr = "";
					for ( var key in data) {
						var user = data[key];
						resultStr += user.userNo + ", " + user.userName + ","
								+ user.userNation + "\n";
					}
					$("#textarea6").val(resultStr);
				}
			});
		});

// 9번 - 서버 유저 정보로 표 구성하기
$("#userInfoBtn").click(function() {
	$.ajax({
		url : 'jQueryTest9.do',
		// JQueryAjaxServlet9
		type : 'get',
		success : function(data) {
			var $tableBody = $("#userInfoTable tbody");
			// $를 붙이는 순간 jQuery!
			// var tableBody : 자바스크립트 변수, 스크립트만 사용가능
			// var $tableBody : jQuert 변수, jQuery에서 사용하는 내장함수 모두 사용 가능
			// var tableBody = $("userInfoTable tbody");
			// ㄴ>이렇게 해도 jQuery변수임. 뒤에 받아오는 부분이 제이쿼리 이기때문에 자바스크립트 변수로 만들어졌다고 해도
			// 제이쿼리변수!
			// 굳이 $를 붙이는 이유는 앞부분만 봐도 판별할수 있기위함, 의미적으로 보여주기 위햐여 $를 붙임
			$tableBody.html("");
			// html 계속안찍히게 해주기 위해서 초기화 해줌
			// for문 처럼 각각의것을 처리해주는 함수가 있엇음^^:
			$.each(data, function(index, value) {
				var $tr = $('<tr>');
				var $noTd = $('<td>').text(value.userNo);
				var $nameTd = $('<td>').text(value.userName);
				var $nationTd = $('<td>').text(value.userNation);

				$tr.append($noTd);
				$tr.append($nameTd);
				$tr.append($nationTd);

				$tableBody.append($tr);
			});
		}
	});
});

// 10번 - 서버에서 List 객체 반환 받아 select 태그를 이용해서 보여줌
$("#selectListBtn").click(
		function() {
			$
					.ajax({
						url : 'jQueryTest10.do',
						// JQueryAjaxServlet10
						type : 'get',
						success : function(data) {
							$select = $("#selectListTest");
							$select.find('option').remove();

							for (var i = 0; i < data.length; i++) {
								var name = data[i].userName;
								var selected = (name == $('#selectUserName')
										.val()) ? "selected" : "";

								$select.append("<option value ='"
										+ data[i].UserNo + "'" + selected + ">"
										+ name + "</option>");
							}
						}
					});
		});

// 11. Gson을 이용한 List 반환(구글에서 만든 json) 해보면 어 이게 진짜 이러면 되는건가 싶게 간단할것임..
// https://mvnrepository.com/artifact/com.google.code.gson/gson/2.8.5
// 여기서 gson 다운받아서 lib에 넣으면 됨!
$('#gsonListBtn').click(function() {
	$.ajax({
		url : 'jQueryTest11.do',
		// JQueryAjaxServlet11
		type : 'get',
		success : function(data) {
			// console.log(data);
			/*
			 * 0: {userNo: 1, userName: "박신우", userNation: "대한민국"} 1: {userNo:
			 * 2, userName: "타일러 러쉬", userNation: "미국"} 2: {userNo: 3, userName:
			 * "쯔위", userNation: "중국"} 3: {userNo: 4, userName: "모모",
			 * userNation: "일본"} 4: {userNo: 5, userName: "리사", userNation:
			 * "태국"} 5: {userNo: 6, userName: "알베르토 몬디", userNation: "이탈리아"} 6:
			 * {userNo: 7, userName: "샘 오취리", userNation: "가나"} length: 7
			 * __proto__: Array(0)
			 */
			// 이렇게 찍힘
			// user - vo 의 field 에서 가져온것~
			$select = $("#gsonListSelect");
			$select.find('option').remove();

			for ( var i in data) {
				var $option = $('<option>');
				$option.val(data[i].userNo);
				$option.text(data[i].userName);

				$select.append($option);

			}
		}
	});
});

//12. Gson을 이용한 Map 반환
$("#gsonMapBtn").click(function() {
	$.ajax({
		url : 'jQueryTest12.do',
		// JQueryAjaxServlet12
		type : 'get',
		success : function(data) {
			$select = $("#gsonMapSelect");
			$select.find('option').remove();

			for ( var key in data) {
				var $option = $('<option>');
				$option.val(data[key].userNo);
				$option.text(data[key].userName);
				$select.append($option);
			}
		}
	});
});

// WebContent에 autoComplete.jsp 만들기~