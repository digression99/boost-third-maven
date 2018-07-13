package kr.or.connect.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/forward")
public class ForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String data1 = "my name is data1!";
		String data2 = "my name is data2!";
		request.setAttribute("data1", data1);
		request.setAttribute("data2", data2);
		
		RequestDispatcher rd = request.getRequestDispatcher("forward.jsp");
		rd.forward(request,response);
	}

}
