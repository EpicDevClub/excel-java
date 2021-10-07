package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletOne")
public class ServletOne extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Integer numberOne = Integer.parseInt(request.getParameter("numberOne"));
		Integer numberTwo = Integer.parseInt(request.getParameter("numberTwo"));
		
		System.out.println("number one: " + numberOne);
		System.out.println("number two: " + numberTwo);
		if(numberOne == null || numberTwo == null)
		{
			response.setContentType("text/plain");
			response.getWriter().println("One of the numbers is missing, please try again.");
			return;
		}
		
		Integer sum = numberOne + numberTwo;
		request.setAttribute("sum", sum);
		
		request.setAttribute("numberOne", numberOne);
		request.setAttribute("numberTwo", numberTwo);
		
		this.getServletContext().getRequestDispatcher("/secondServlet").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
