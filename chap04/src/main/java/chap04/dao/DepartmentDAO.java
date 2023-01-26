package chap04.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chap04.database.DBConnector;
import chap04.model.Department;

public class DepartmentDAO {
	

	public static List<Department> getList() {
		
		String sql = "SELECT DISTINCT department_id, department_name FROM departments";
		List<Department> list = new ArrayList<>();
		try(
				Connection conn = DBConnector.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
		) {
			
			while(rs.next()) {
				list.add(new Department(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
