package com.admin.entity;

import java.util.List;

/**
 *
 * @author rico
 * @version 1.0
 * @since 1.0
 * */
public class TbHd implements java.io.Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8064491955809919136L;
	// id       db_column: id 
	private java.lang.Long id;
    // code       db_column: code 
	private java.lang.String code;
	
	private List<TbDt> tbDtList;

	public TbHd(){
	}

	public TbHd(
		java.lang.Long id,
		java.lang.String code
	)
	{
		this.id = id;
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
	public void setCode(java.lang.String value) 
	{
		this.code = value;
	}
	
	public java.lang.String getCode() 
	{
		return this.code;
	}

	public List<TbDt> getTbDtList()
	{
		return tbDtList;
	}

	public void setTbDtList(List<TbDt> tbDtList)
	{
		this.tbDtList = tbDtList;
	}
	
}

