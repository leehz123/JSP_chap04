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

	<!-- EmployeeListProcess.java���� �������� forward�� req�� resp�� �Ѿ�� (nextPath �� ���� �� ���)-->

	<!-- �ش� ��ü�� �Ӽ��� getter�� ������ EL�� ���ϰ� �ش� �Ӽ��� ���� ����� �� �ִ�. -->
 	<ul>
		<c:forEach items="${employees }" var="employee">
			<li>${employee.first_name } ${employee.last_name } ${employee.employee_id }
			(<a href="./modifyForm?emp=${employee.employee_id }">����</a>
			/<a href="./delete?emp=${employee.employee_id }">����</a>)</li>
		</c:forEach>
	</ul> 


	

</body>
</html>