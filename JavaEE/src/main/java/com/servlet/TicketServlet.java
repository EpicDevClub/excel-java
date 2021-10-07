package com.servlet;

import javax.servlet.annotation.MultipartConfig;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.util.Map;
import java.util.LinkedHashMap;
import java.io.PrintWriter;


@WebServlet("/tickets")
@MultipartConfig //to set the multipart configuration for the file. to tell the browser that add the file acceptance in the servlet.
(
		fileSizeThreshold = 1024 * 1024 * 5,
		maxFileSize = 1024 * 1024 * 20,
		maxRequestSize = 1024 * 1024 * 40
		
)
public class TicketServlet extends HttpServlet 
{
	private volatile int TICKET_ID_SEQUENCE	 = 1; //ticket id.
	private Map<Integer, Ticket> ticketDatabase = new LinkedHashMap<>(); // database to store tickets.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String action = request.getParameter("action");  // getting request parameter named 'action'.
		
		if(action == null) // if the action is null,
			action = "list"; // set it to the default value of "list".
		//PrintWriter writer = response.getWriter();
		switch(action)
		{
		case "create":
			this.showTicketForm(response);
			break;
		case "view":
			this.viewTicket(request, response);
			break;
		case "download":
			this.downloadAttachment(request, response);
			break;
		case "list":
			default:
				this.listTickets(response);
				break;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String action = request.getParameter("action");
		if(action == null)
			action = "list";
		switch(action)
		{
		case "create":
			createTicket(request, response);
			break;
		case "list":
			default:
				response.sendRedirect("tickets");
				break;
		}
	}
	
	
	private void showTicketForm(HttpServletResponse resp) throws ServletException, IOException
	{
		PrintWriter writer =  this.writeHeader(resp);
		
		writer.append("<h2>Create a ticket</h2>");
		writer.append("<form method=\"POST\" action=\"tickets\"")
		.append("enctype=\"multipart/form-data\">");
		writer.append("<input type=\"hidden\" name=\"action\"") //hidden text field.
		.append("value=\"create\">"); //default value "create".
		writer.append("Your Name<br>"); 
		writer.append("<input type=\"text\" name=\"customerName\"> <br><br>");
		writer.append("Subject<br>");
		writer.append("<input type=\"text\" name=\"subject\"><br><br>");
		writer.append("Body<br>");
		writer.append("<textarea name=\"body\" rows=\"5\" cols=\"30\">")
		.append("</textarea><br><br>");
		writer.append("<b>Attachment</b><br><br>");
		writer.append("<input type=\"file\" name=\"file1\"><br><br>");
		writer.append("<input type=\"submit\" value=\"Submit\">");
		writer.append("</form>");
		
		writeFooter(writer);	
	}
	
	private void viewTicket(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String idString = req.getParameter("ticketId"); // getting request parameter ticketId.
		Ticket ticket = getTicket(idString, resp);
		if(ticket == null)
			return;
		PrintWriter writer = writeHeader(resp); //      /****   writer is called before  ****/
		writer.append("<h2>Ticket  #").append(idString)
		.append(": ").append(ticket.getSubject()).append("</h2>");
		writer.append("<i>Customer Name - ").append(ticket.getCustomerName())
		.append("</i><br><br>");
		
		if(ticket.getNumberOfAttachments() > 0)
		{
			writer.append("Attachments: ");
			int i = 0;
			for(Attachment attachment: ticket.getAttachments())
			{
				if(i++ > 0)
					writer.append(", ");
				writer.append("<a href=\"tickets?action=download&ticketId=") // this request will redirect to tickets with action value download.
				.append(idString).append("&attachment=")
				.append(attachment.getName()).append("\">")
				.append(attachment.getName()).append("</a>");
			}
		}
	}
	
	private void downloadAttachment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String idString = req.getParameter("ticketId");
		Ticket ticket = getTicket(idString, resp);
		if(ticket == null) return;
		String name = req.getParameter("attachment");
		if(name == null)
		{
			resp.sendRedirect("tickets?action=view&ticketId=" + idString);		
			return;
		}
		
		Attachment attachment = ticket.getAttachment(name);
		if(attachment == null)
		{
			resp.sendRedirect("ticket?action=view&ticketId=" + idString);
			return;
		}
		resp.setHeader("Content-Disposition", "attachment; filename=" + attachment.getName()); //
		resp.setContentType("application/octet-stream");
		
		ServletOutputStream stream = resp.getOutputStream();
		stream.write(attachment.getContents());
	}
	
	private void listTickets(HttpServletResponse resp) throws ServletException, IOException
	{
		PrintWriter writer = writeHeader(resp);
		
		writer.append("<h2>Tickets</h2>");
		writer.append("<a href=\"tickets?action=create\">One Create Ticket")
		.append("</a><br><br>");
		
		if(ticketDatabase.size() == 0)
			writer.append("<i>There are no tickets in the system.</i>");
		else
		{
			for(int id: ticketDatabase.keySet())
			{
				String idString = Integer.toString(id);
				Ticket ticket = ticketDatabase.get(id);
				writer.append("Ticket #").append(idString)
				.append(": <a href=\"tickets?action=veiw&ticketId=")
				.append(idString).append("\">").append(ticket.getSubject())
				.append("<a>(customer: ").append(ticket.getCustomerName())
				.append(")<br>");
			}
		}
		
		writeFooter(writer);
	}
	
	private void createTicket(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException
	{
		
		Ticket ticket = new Ticket();
		ticket.setCustomerName(req.getParameter("customerName"));
		ticket.setSubject(req.getParameter("subject"));
		ticket.setBody(req.getParameter("body"));
		
		Part filePart = req.getPart("file1");
		if(filePart != null && filePart.getSize() > 0)
		{
			Attachment attachment = processAttachment(filePart);
			if(attachment != null)
				ticket.addAttachment(attachment);
		}
		
		int id;
		synchronized(this)
		{
			id = TICKET_ID_SEQUENCE++;
			ticketDatabase.put(id, ticket);
		}
		
		resp.sendRedirect("tickets?action=view&ticketId=" + id);
	}
	
	
	private Attachment processAttachment(Part filePart) throws IOException
	{
		InputStream inputStream = filePart.getInputStream();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		final byte[] bytes = new byte[1024];
		int read;
		while((read = inputStream.read(bytes)) != -1)
		{
			outputStream.write(bytes, 0, read);
		}
		
		Attachment attachment = new Attachment();
		attachment.setName(filePart.getSubmittedFileName());
		attachment.setContents(outputStream.toByteArray());
		
		return attachment;
	}
	
	private Ticket getTicket(String idString, HttpServletResponse resp) throws ServletException, IOException
	{
		if(idString == null || idString.length() == 0)
		{
			resp.sendRedirect("tickets");
			return null;
		}
		
		try
		{
			Ticket ticket = ticketDatabase.get(Integer.parseInt(idString));
			if(ticket == null)
			{
				resp.sendRedirect("tickets");
				return null;
			}
			
			return ticket;
		}
		catch(Exception e)
		{
			resp.sendRedirect("tickets");
			return null;
		}
	}
	
	
	private PrintWriter writeHeader(HttpServletResponse resp) throws ServletException, IOException
	{
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.append("<!DOCTYPE html>\r\n")
		.append("<html>")
		.append("<head>")
		.append("<title>Customer Support</title>")
		.append("</title>")
		.append("<body>");
		
		return writer;
	}
	
	private void writeFooter(PrintWriter writer)
	{
		writer.append("</body>")
		.append("</html>");
	}
}
