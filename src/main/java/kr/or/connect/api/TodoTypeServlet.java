

package kr.or.connect.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.dao.TodoDao;
import kr.or.connect.dto.TodoDto;
import kr.or.connect.util.TodoTypes;

@WebServlet("/todo/type/*")
public class TodoTypeServlet extends HttpServlet {
	
	private TodoDao todoDao;
	private Logger log;

	@Override
	public void destroy() {
		todoDao.closeConnection();
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		
		todoDao = TodoDao.getInstance();
		log = Logger.getGlobal();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get the id.
		String pathInfo = request.getPathInfo();
		String[] pathParts = pathInfo.split("/");
		String idStr = pathParts[1];
		long id = Long.parseLong(idStr);
		
		log.info("entered id : " + id);
		TodoDto todo = todoDao.selectTodo(id);
		
		if (todo != null) {
			log.info("type : " + todo.getType());
			
			String nextType = TodoTypes.next(todo.getType()).toString();
			log.info("changed type : " + nextType);
			todo.setType(TodoTypes.next(todo.getType()).toString());

			int updateCount = todoDao.updateTodo(todo);
		
			// set response.
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json");
			
			if (updateCount == 1) {
				// send success response.
				response.setStatus(HttpServletResponse.SC_OK);
			} else {
				response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
			}

			try (
				PrintWriter out = response.getWriter()) {
				out.print(updateCount);
			} catch (Exception e) {
				throw new Error(e.getMessage());
			}
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}
}
