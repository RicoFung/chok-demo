package com.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import chok.devwork.springboot.BaseDao;
import chok.devwork.springboot.BaseService;
import com.admin.dao.TbDtDao;
import com.admin.entity.TbDt;

@Service
public class TbDtService extends BaseService<TbDt,Long>
{
	@Autowired
	private TbDtDao dao;

	@Override
	public BaseDao<TbDt,Long> getEntityDao() 
	{
		return dao;
	}
}
