package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/secondServlet")
public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Integer numberOne = (Integer) request.getAttribute("numberOne");
		Integer numberTwo = (Integer) request.getAttribute("numberTwo");
		System.out.println("number one: " + numberOne);
		System.out.println("number two: " + numberTwo);
		Integer sum = (Integer) request.getAttribute("sum");
		
		response.setContentType("text/plain");
		PrintWriter writer = response.getWriter();
		writer.println("Sum of the numbers: " + sum);
		writer.println(); writer.println();
		
		writer.println("Product fo the numbers: " + (numberOne * numberTwo));
		
		writer.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
