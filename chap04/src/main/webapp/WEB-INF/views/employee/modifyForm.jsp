<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>직원 정보 수정</title>
<style>
th {
	text-align : justify;
}

</style>
</head>
<body>

	
	<!-- EL에서 .property를 하기 위해서는 해당 속성의 Getter가 구현돼 있어야 한다. 이걸 역으로 이용하는 방법도 있것지  -->
	<!-- getForm()메서드 만들어놓으면 작동함. 사실상 EL에 있는 프로퍼티는 객체를 호출하는 게 아니라 get~ 를 호출하는 것. -->
	<!-- ${employee.form } -->
	
	<form action="./modify" method="POST">
		<table>
			<tr>
				<th>employee_id</th>
				<td><input type="text" name="employee_id" value="${employee.employee_id }" 
				readonly/></td>
			</tr>
			<tr>
				<th>first_name</th>
				<td><input type="text" name="first_name" value="${employee.first_name }" /></td>
			</tr>
			<tr>
				<th>last_name</th>
				<td><input type="text" name="last_name" value="${employee.last_name }" /></td>
			</tr>
			<tr>
				<th>hire_date</th>
				<td><input type="text" name="hire_date" value="${employee.hire_date }" 
				readonly/></td>
			</tr>
			<tr>
				<th>salary</th>
				<td><input type="text" name="salary" value="${employee.salary }" /></td>
			</tr>
			<tr>
				<th>commission_pct</th>
				<td><input type="text" name="commission_pct" value="${employee.commission_pct }" /></td>
			</tr>
			<tr>
				<th>job_id</th>
				<td>
					<select name="job_id">
						<c:forEach items="${jobs }" var="job">
							<c:choose>
								<c:when test="${job.job_id == employee.job_id }">
									<option value="${job.job_id }" selected>${job.job_title } (${job.job_id })</option>
								</c:when>
								<c:otherwise>
									<option value="${job.job_id }">${job.job_title } (${job.job_id })</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>					
				</td>
			</tr>
			<tr>
				<th>department_id</th>
				<td>
					<select name="department_id">
						<c:forEach items="${departments }" var="department">
								<c:choose>
									<c:when test="${department.department_id == employee.department_id }">
										<option value="${department.department_id }" selected>${department.department_name } (${department.department_id })</option>
									</c:when>
									<c:otherwise>
										<option value="${department.department_id }">${department.department_name } (${department.department_id })</option>										
									</c:otherwise>
								</c:choose>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
		<input type="submit" value="수정하기" />
	</form>

</body>
</html>