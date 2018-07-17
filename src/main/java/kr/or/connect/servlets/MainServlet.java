package kr.or.connect.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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

	private TodoDao todoDao;
//	private Logger log;

	@Override
	public void init() throws ServletException {
		super.init();
		todoDao = TodoDao.getInstance();
//		log = Logger.getGlobal();
	}

	@Override
	public void destroy() {
		this.todoDao.closeConnection();
		super.destroy();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		// set attributes.
		request.setAttribute("typesTodo", todoDao.selectTodosByType("TODO"));
		request.setAttribute("typesDoing", todoDao.selectTodosByType("DOING"));
		request.setAttribute("typesDone", todoDao.selectTodosByType("DONE"));

		RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
		rd.forward(request, response);
	}
}
