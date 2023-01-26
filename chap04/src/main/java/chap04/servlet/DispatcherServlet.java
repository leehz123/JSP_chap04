package chap04.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chap04.process.EmployeeAddFormProcess;
import chap04.process.EmployeeAddProcess;
import chap04.process.EmployeeDeleteProcess;
import chap04.process.EmployeeListProcess;
import chap04.process.EmployeeModifyFormProcess;
import chap04.process.EmployeeModifyProcess;
import chap04.process.Process;


//어떤 주소를 입력하더라도 1차로 여기 들어오게 될 것
//http://localhost:8888/chap04/를 입력하든 http://localhost:8888/chap04/asdf를 입력하든 http://localhost:8888/chap04/hgfd를 입력하든...
public class DispatcherServlet extends HttpServlet {
	
	//프로세스라 했던 걸 정식적인 용어로 service라고 하고 그 처리를 비즈니스 로직이라 부름(회사의 목적에 따라 그 처리가 달라지기 땜에 비로라고 부름. 그리고 비로를 구현한게 서ㅂ스) 
	//서비스보다 거대한 이름이 프로세스랭 
	
	
	
	private final static String REDIRECT = "redirect:";
	
	
	
	
	//새로운 주소가 들어왔을 떄 다른 주소에 따라 다른 프로세스로 매핑하고 싶으면 이런 식으로 해주면 됨
	HashMap<String, Process> uri_mapping;
	//uri_mapping 해시맵엔 주소에서 컨택스트패스를 뺀 뒷부분이 key, 프로세스가 value로 들어 가 있다.
	
	@Override
	public void init() throws ServletException {
		uri_mapping  = new HashMap<>();
		uri_mapping.put("/employee/list", new EmployeeListProcess());
		
		//url_mapping.put("/employee/add", new EmployeeAddProcess());
		//url_mapping.put("/employee/update", new EmployeeUpdateProcess()); 이렇게 쭉쭉 나아갈 수 있음
		
		//퀴즈 풀이 (addForm.jsp 퀴즈)
		uri_mapping.put("/employee/add_form", new EmployeeAddFormProcess());
		uri_mapping.put("/employee/add", new EmployeeAddProcess());

		//수정 삭제도 만들어보기
		uri_mapping.put("/employee/modify", new EmployeeModifyProcess());
		uri_mapping.put("/employee/modifyForm", new EmployeeModifyFormProcess());
		uri_mapping.put("/employee/delete", new EmployeeDeleteProcess());
	}
	

	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException {
		
		
		String uri = req.getRequestURI();//사용자가 입력한 주소를 기본주소(/chap04/)부터 가져오기. 
//		System.out.println(req.getRequestURI());
//		System.out.println("request uri : " + uri); //모든 처리에 대한 uri 주소를 여기서 볼 수 있게 될 것. 예시) /chap04/asdf 또는 /chap04/employee/list ....
//		System.out.println("remove context path : " + uri.substring(req.getContextPath().length())); //컨텍스트(/chap04)를 잘라보겠음
		
		/*
		 콘솔창 출력결과 
			/chap04/employee/list
			request uri : /chap04/employee/list
			remove context path : /employee/list
			JDBC Loading Success...
		 */

		

		//사용자가 접속한 주소에서 contextPath를 빼면 uri_mapping의 key값이 나올 거고 그에 해당하는 처리(process)를 꺼낸다.
		uri = uri.substring(req.getContextPath().length());
		Process process = uri_mapping.get(uri); //이 주소의 알맞은 프로세스가 나옴
		 
		
		
		String nextPath;
	
		if(process != null) { //사용자가 입력한 주소에 해당하는 프로세스가 null이 아니라면
			nextPath = process.process(req, resp); //어쩌고저쩌고프로세스 클래스들은 모두 프로세스 Process인터페이스를 구현할 거고 그 안의 process()메서드도 반드시 구현하게 돼 있음. 
			// new EmployeeListProcess.process(req, resp) 지 
		
			if(nextPath.startsWith(REDIRECT)) {
				resp.sendRedirect(nextPath.substring(REDIRECT.length()));
				return; //리디렉트 하니까 포워드 걸지 않고 끝내야됨. 
			}	
		} else {nextPath = "/WEB-INF/views/errorpage/not_found.jsp";}  //프로세스가 널이면 not_found페이지로 이동
		
		req.getRequestDispatcher(nextPath).forward(req, resp);	//위에서 process.process(req,resp) 코드 때문에 EmployeeListProcess갔다오면서
		//request.setAttribute("employees", employees);로  request편의 Attribute에 employee를 실어둠
		//그래서 여기 req에도 employee가 attribute형태로 실려 있는 거고 그게 nextPath 경로로 포워드되는 것!  	
	}	
}
