package chap04.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Department {

	private Integer department_id;
	private String department_name;
	
	public Department(ResultSet rs) throws SQLException {
		department_id = rs.getInt("department_id");
		department_name = rs.getString("department_name");
	}

	
	//게터 세터 있어야 EL동작한다고!!!!!!!!!!
	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}	
	
	
}
