package chap04.process;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chap04.dao.EmployeeDAO;



/*
 
 빌드패스 - 컨피겨 빌드패스 - 라이브러리 - 모듈어쩌고 - 애드 쟈 어쩌고 - JavaAWS - C:\JavaAWS\database\sqldeveloper-21.4.3.063.0100-x64\sqldeveloper\jdbc\lib 에 ojdbc8 추가
 그리고 프로퍼티스 가서 디플로이먼트 ~!~~~ 추가 
 
 */
public class EmployeeListProcess implements Process {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("employees", EmployeeDAO.getList());
		
		//다음으로 가야 할 페이지의 URL을 리턴한다.
		return "/WEB-INF/views/employee/list.jsp";
	}
}
