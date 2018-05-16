package com.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import chok.devwork.springboot.BaseDao;
import chok.devwork.springboot.BaseService;
import com.admin.dao.TbHdDao;
import com.admin.entity.TbHd;

@Service
public class TbHdService extends BaseService<TbHd,Long>
{
	@Autowired
	private TbHdDao dao;

	@Override
	public BaseDao<TbHd,Long> getEntityDao() 
	{
		return dao;
	}
}
