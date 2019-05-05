package com.admin.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.admin.dao.CategoryDao;
import com.admin.entity.Category;

import chok.devwork.springboot.BaseDao;
import chok.devwork.springboot.BaseService;
import chok.util.POIUtil;

@Service
public class CategoryService extends BaseService<Category, Long>
{
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private CategoryDao dao;

	@Override
	public BaseDao<Category, Long> getEntityDao()
	{
		return dao;
	}

	public void imp2(CommonsMultipartFile files[]) throws IOException, InterruptedException
	{
		log.info("开始做任务一");
		long start = System.currentTimeMillis();
		for (CommonsMultipartFile file : files)
		{
			List<String[]> list = POIUtil.readExcel(file);
			for (String[] r : list)
			{
				dao.asyncAdd(r);
			}
		}
		long end = System.currentTimeMillis();
		log.info("完成任务一，耗时：" + (end - start) + "毫秒");
	}

}
