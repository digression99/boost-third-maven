package kr.or.connect.api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.dao.TodoDao;

@WebServlet("/todo")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TodoDao todoDao = new TodoDao();
		
		String title = request.getParameter("title");
		String name = request.getParameter("name");
		int sequence = Integer.parseInt(request.getParameter("sequence"));
		
		System.out.println("in todo post");
		System.out.println("title : " + title);
		System.out.println("name : " + name);
		System.out.println("sequence : " + sequence);
		
		int insertCount = todoDao.addTodo(title, name, sequence);
		
		if (insertCount == 1) {
			response.sendRedirect("/secondproject");
		} else {
			// error case.
			// response.forward(ErrorServlet);
			response.sendRedirect("/secondproject");
		}
	}

}
