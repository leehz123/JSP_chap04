package chap04.process;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeDeleteProcess implements Process {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		
		//삭제되는 거 하면 됨 여기선 DAO못 쓰려나? 아닌가 DELETE sql문 하고 반환을 void로 바꾸면 되려나 
		
		
		
		return "redirect:/chap04/employee/list";
	}	
}
