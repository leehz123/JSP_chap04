package chap04.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chap04.dao.DepartmentDAO;
import chap04.dao.EmployeeDAO;
import chap04.dao.JobDAO;
import chap04.database.DBConnector;
import chap04.model.Employee;

public class EmployeeModifyFormProcess implements Process {

	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
	
		int emp_id = Integer.parseInt(request.getParameter("emp"));
		
		String sql = "SELECT * FROM employees2 WHERE employee_id = ?";
		
		//String sql2 = "SELECT DISTINCT job_id, job_title FROM jobs"; //DB와의 통신을 담당하는 역속계층? 여러 프로세스에 같은 쿼리문이 존재. 그래서 이걸 분리시켜주겠음. 
		request.setAttribute("jobs", JobDAO.getList());// 이러면 끝남 
		//이러면 유지보수가 훨씬 좋아짐. 수정할 일 생기면 JobDAO 가서 한번만 고치면 됨. 이곳저곳 어디 있나 찾으면서 수정할 필요 없이
		
		//디팔먼도 마찬가지
		request.setAttribute("departments", DepartmentDAO.getList());
		
//		//그리고 modifyForm 에 기존의 employee 정보를 띄워야 하는데 정보를 수정하고 나서 바뀐 데이터를 다시 올리기 위해서는 여기서 employee데이터를 업데이트 해줘야 됨
//		request.setAttribute("newEmployees", EmployeeDAO.getList());
		
		try(
				Connection conn = DBConnector.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
		){
			
			pstmt.setInt(1, emp_id);
			
			try(ResultSet rs = pstmt.executeQuery();) {
				request.setAttribute("employee", rs.next() ? new Employee(rs) : null );
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "/WEB-INF/views/employee/modifyForm.jsp";
		
	}
}

 