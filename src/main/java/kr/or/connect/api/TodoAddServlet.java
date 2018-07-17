package kr.or.connect.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.dao.TodoDao;

@WebServlet("/todo")
public class TodoAddServlet extends HttpServlet {
	
	private Logger log;
	private TodoDao todoDao;
	
	@Override
	public void destroy() {
		todoDao.closeConnection();
		super.destroy();
	}
	
	@Override
	public void init() throws ServletException {
		super.init();
		log = Logger.getGlobal();
		todoDao = TodoDao.getInstance();
	}

	// override annotation.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String name = request.getParameter("name");
		int sequence = Integer.parseInt(request.getParameter("sequence"));
		
		log.info("title : " + title);
		log.info("name : " + name);
		log.info("name : " + name);
		
		int insertCount = todoDao.insertTodo(title, name, sequence);
	
		if (insertCount == 1) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
		}
		
		try (PrintWriter out = response.getWriter()) {
			out.write(insertCount);
		} catch (Exception e) {
			throw new Error(e.getMessage());
		}
	}
}
