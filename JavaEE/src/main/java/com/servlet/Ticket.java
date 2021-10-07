package com.servlet;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Collection;
public class Ticket
{
	private String customerName; // name of the customer.
	private String subject; // subject of the ticket.
	private String body; // msg body.
	private Map<String, Attachment> attachments = new LinkedHashMap<>(); // database to store the attachments.
	
	public String getCustomerName() // to get the customer.
	{
		return customerName;
	}
	
	public void setCustomerName(String customerName) // to set the name of the customer.
	{
		this.customerName = customerName;
	}
	
	public String getSubject() // to get the subject.
	{
		return subject; 
	}
	
	public void setSubject(String subject) // to set the subject.
	{
		this.subject = subject;
	}
	
	public String getBody() // to get the msg body.
	{
		return body;
	}
	public void setBody(String body) //to set the body.
	{
		this.body = body;
	}
	
	public Attachment getAttachment(String name)
	{
		return this.attachments.get(name);
	}
	public void addAttachment(Attachment attachment)
	{
		this.attachments.put(attachment.getName(), attachment);
	}
	public Collection<Attachment> getAttachments()
	{
		return this.attachments.values();
	}
	
	public int getNumberOfAttachments()
	{
		return this.attachments.size();
	}
}