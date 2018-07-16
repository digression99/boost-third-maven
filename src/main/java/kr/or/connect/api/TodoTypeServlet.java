

package kr.or.connect.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.connect.dao.TodoDao;
import kr.or.connect.dto.TodoDto;

@WebServlet("/todo/type/*")
public class TodoTypeServlet extends HttpServlet {

	private HashMap<String, String> nextTypes;
	
	public TodoTypeServlet() {
		nextTypes = new HashMap<>();
		nextTypes.put("TODO", "DOING");
		nextTypes.put("DOING", "DONE");
		nextTypes.put("DONE", "DONE");
	}
	
	
	/**
	 * @author Created by Ilsik Kim on 2018. 7. 16.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get the id.
		String pathInfo = request.getPathInfo();
		String[] pathParts = pathInfo.split("/");
		String idStr = pathParts[1];
		long id = Long.parseLong(idStr);
		
		// get object and update.
		TodoDao dao = new TodoDao();	
		
		TodoDto todo = dao.getTodo(id);
		if (todo != null) {
			System.out.println("type : " + todo.getType());
			todo.setType(this.nextTypes.get(todo.getType()));

			int updateCount = dao.updateTodo(todo);
		
			// set response.
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json");
			
			if (updateCount == 1) {
				// send success response.
				response.setStatus(200);
			} else {
				response.setStatus(400);
			}

			try (
				PrintWriter out = response.getWriter()) {
				out.print(updateCount);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			response.setStatus(400);
		}
	}
}
