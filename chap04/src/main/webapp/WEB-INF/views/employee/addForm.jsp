<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>새 사원 추가하기</title>
</head>
<body>


	<h3>새 사원 추가하기</h3>
	<!-- /chap04/employee/add_form 에서 ./ (상대경로)한 다음에 add 하면 /chap04/employee/add로 감. 근데 ./ 안 붙여놔도 됨!!!!! -->
	<form action="./add" method="POST">
	employee_id : <input type="number" placeholder="employee id" name="employee_id" value="${new_id }" readonly/><br /> <!-- readonly 값변경 못 함 -->
	last_name : <input type="text" placeholder="last name" name= "last_name"/><br />
	first_name : <input type="text" placeholder="email" name="email"/><br />
	hire_date : <input type="date" placeholder="hire date" name="hire_date"/><br />
	job : <br>
<!-- 
	<c:forEach items="${jobs }" var="job">
		<input type="radio" placeholder="job id"  name="job_id" value="${job.job_id }" id="${job.job_id }"/> 
		<label for="${job.job_id }">${job.job_title }(${job.job_id })</label><br />
	</c:forEach>
 -->
	
	<select name="job_id" id="">
		<c:forEach items="${jobs }" var="job">
			<option value="${job.job_id }">${job.job_title }(${job.job_id })</option>
		</c:forEach>
	</select>
	
	
	<input type="submit" value="insert"/>
	</form>
	

</body>
</html>