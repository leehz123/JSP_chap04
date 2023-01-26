package chap04.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chap04.database.DBConnector;

public class EmployeeModifyProcess implements Process {
	//last_name first_name hire_date email job
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {

		Integer employee_id = Integer.parseInt(request.getParameter("employee_id"));
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		
		Integer salary = Integer.parseInt(request.getParameter("salary"));
		Double commission_pct = Double.parseDouble(request.getParameter("commission_pct"));
		String job_id = request.getParameter("job_id");
		Integer department_id = Integer.parseInt(request.getParameter("department_id"));
		
		
		
		System.out.println(employee_id);
		System.out.println(first_name);
		System.out.println(last_name);
		System.out.println(salary);
		System.out.println(commission_pct);
		System.out.println(job_id);
		System.out.println(department_id);
		
		
		
		
		String sql = "UPDATE employees2 SET "
				+ "first_name = ?"
				+ ", last_name = ?"
				+ ", salary = ?"
				+ ", commission_pct = ?"
				+ ", job_id = ?"
				+ ", department_id = ?"
				+" where employee_id = ?";
		
		
		try (
				Connection conn = DBConnector.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setString(1, first_name);
			pstmt.setString(2, last_name);
			pstmt.setInt(3, salary);
			pstmt.setDouble(4, commission_pct);
			pstmt.setString(5, job_id);
			pstmt.setInt(6, department_id);
			pstmt.setInt(7, employee_id);
			
			conn.setAutoCommit(false);
			
			System.out.println(pstmt.executeUpdate() + "행이 수정 되었습니다.");
			
			conn.commit();
			
		} catch (SQLException e) {
			System.out.println("데이터 수정 실패 ");
			e.printStackTrace();
		}
		
		//return "/employee/list"; //이렇게 안 쓰던데 이것도 되네.. (이거 내가 한 거. 고쳐야될듯 ) 
		return "redirect:/chap04/employee/list";
	}
}
