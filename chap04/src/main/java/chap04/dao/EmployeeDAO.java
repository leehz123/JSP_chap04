package chap04.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chap04.database.DBConnector;
import chap04.model.Employee;

public class EmployeeDAO {

	public static List<Employee> getList() {
		String sql = "SELECT * FROM employees2";
		List<Employee> list = new ArrayList<>();
		
		try(
				Connection conn = DBConnector.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
		) {
			
			while(rs.next()) {
				list.add(new Employee(rs));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
				
		return list;
	}
	
	
}
