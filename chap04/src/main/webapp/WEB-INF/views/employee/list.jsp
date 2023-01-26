<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style> a {color: black; text-decoration: none;} </style>
</head>
<body>

	<!-- EmployeeListProcess.java에서 마지막에 forward한 req와 resp가 넘어옴 (nextPath 가 여기 일 경우)-->

	<!-- 해당 객체의 속성에 getter가 있으면 EL로 편리하게 해당 속성을 꺼내 사용할 수 있다. -->
 	<ul>
		<c:forEach items="${employees }" var="employee">
			<li>${employee.first_name } ${employee.last_name } ${employee.employee_id }
			(<a href="./modifyForm?emp=${employee.employee_id }">수정</a>
			/<a href="./delete?emp=${employee.employee_id }">삭제</a>)</li>
		</c:forEach>
	</ul> 


	

</body>
</html>