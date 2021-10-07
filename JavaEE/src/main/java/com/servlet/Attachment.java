package com.servlet;

public class Attachment 
{
	private String name; //to store the name of the attachment.
	private byte[] contents;  //to store the contents of the file in the form of bytes.
	
	public String getName() //to get the name.
	{
		return name;
	}
	
	public void setName(String name) //to set the name.
	{
		this.name = name;
	}
	
	public byte[] getContents() //to get the bytes.
	{
		return contents;
	}
	
	public void setContents(byte[] contents) //to set the contents bytes.
	{
		this.contents = contents;
	}
}
