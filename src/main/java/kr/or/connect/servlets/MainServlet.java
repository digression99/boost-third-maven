package kr.or.connect.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.dao.TodoDao;
import kr.or.connect.dto.TodoDto;

@WebServlet("/")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TodoDao todoDao = new TodoDao();
		
		List<TodoDto> todoList = todoDao.getTodos();
		
		List<TodoDto> typesTodo = new ArrayList<>();
		List<TodoDto> typesDoing = new ArrayList<>();
		List<TodoDto> typesDone = new ArrayList<>();
		
		for (TodoDto todo : todoList) {
			String type = todo.getType();
			
			if (type.equals("TODO")) typesTodo.add(todo);
			else if (type.equals("DOING")) typesDoing.add(todo); 
			else typesDone.add(todo);
		}
		
		request.setAttribute("typesTodo", typesTodo);
		request.setAttribute("typesDoing", typesDoing);
		request.setAttribute("typesDone", typesDone);
		
		RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
		rd.forward(request,response);
	}
}
