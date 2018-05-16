package com.admin.entity;

/**
 *
 * @author rico
 * @version 1.0
 * @since 1.0
 * */
public class TbDt implements java.io.Serializable
{
    // id       db_column: id 
	private java.lang.Long id;
    // pid       db_column: pid 
	private java.lang.Long pid;
    // code       db_column: code 
	private java.lang.String code;

	public TbDt(){
	}

	public TbDt(
		java.lang.Long id,
		java.lang.Long pid,
		java.lang.String code
	)
	{
		this.id = id;
		this.pid = pid;
		this.code = code;
	}

	public void setId(java.lang.Long value) 
	{
		this.id = value;
	}
	
	public java.lang.Long getId() 
	{
		return this.id;
	}
	public void setPid(java.lang.Long value) 
	{
		this.pid = value;
	}
	
	public java.lang.Long getPid() 
	{
		return this.pid;
	}
	public void setCode(java.lang.String value) 
	{
		this.code = value;
	}
	
	public java.lang.String getCode() 
	{
		return this.code;
	}
}

