package chap04.process;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chap04.database.DBConnector;

public class EmployeeAddProcess implements Process {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		Integer employee_id = Integer.parseInt(request.getParameter("employee_id"));
		String last_name = request.getParameter("last_name");
		String email = request.getParameter("email");
		String hire_date = request.getParameter("hire_date");
		String job_id = request.getParameter("job_id");

		
		
		
		String sql = "INSERT INTO employees2(employee_id, last_name, email, hire_date, job_id) VALUES(?, ?, ?, ?, ?)";
		
		try(
				Connection conn = DBConnector.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setInt(1, employee_id);
			pstmt.setString(2, last_name);
			pstmt.setString(3, email);
			
			//oreoaredStatement의 setDate는 jaav.sql.Date를 요구함
			//★★★java.sql.Date의 static method 중 valueOf에 html에서 받은 날짜 형식(input date형이 2022-07-13이렇게 넘김)을 그대로 넘기면 java.sql.Date의 인스턴스를 반환한다.★
			pstmt.setDate(4, Date.valueOf(hire_date));
			pstmt.setString(5, job_id);
			
			int row = pstmt.executeUpdate();
			System.out.println(row + "행이 추가되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
//		System.out.println(employee_id);
//		System.out.println(last_name);
//		System.out.println(email);
//		System.out.println(date);
//		System.out.println(job_id);
		
		//return null; 우리가 리턴 널을 갖고 있는데 널 갖고 포워드를 해서 에러 발생
		
		return "redirect:/chap04/employee/list"; //이번에는 리다이렉트 걸어볼게
	}
}
