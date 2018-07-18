package kr.or.connect.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.connect.dao.TodoDao;
import kr.or.connect.dto.TodoDto;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		BufferedReader bufferedReader = new BufferedReader(
			new InputStreamReader(request.getInputStream()));

		String json = "";

		if (bufferedReader != null) {
			json = bufferedReader.readLine();
		}

		ObjectMapper mapper = new ObjectMapper();
		TodoDto todoDto = mapper.readValue(json, TodoDto.class);
		log.info("todo : " + todoDto);

		int insertCount = todoDao.insertTodo(
			todoDto.getTitle(), todoDto.getName(), todoDto.getSequence());

		response.setContentType("application/json");
		
		if (insertCount == 1) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
		}
		
		Map<String, Integer> returnObject = new HashMap<>();
		
		returnObject.put("insertCount", insertCount);
		
		String jsonReturnObject = mapper.writeValueAsString(returnObject);

		try (PrintWriter out = response.getWriter()) {
			out.write(jsonReturnObject);
		} catch (Exception e) {
			throw new Error(e.getMessage());
		}
	}
}
