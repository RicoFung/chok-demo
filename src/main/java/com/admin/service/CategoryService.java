package com.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.admin.dao.CategoryDao;
import com.admin.dao.CategoryJdbcDao;
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
	@Autowired
	private CategoryJdbcDao jdbcDao;

	@Override
	public BaseDao<Category, Long> getEntityDao()
	{
		return dao;
	}

	// 采用jdbc的batchUpdate实现批量插入，既能保证事务也显著提升性能（平均 10000条/3s）
	public void imp2(CommonsMultipartFile files[]) throws Exception
	{
		log.info("开始做任务");
		long start = System.currentTimeMillis();
		for (CommonsMultipartFile file : files)
		{
			List<String[]> list = POIUtil.readExcel(file);
			jdbcDao.addBatch(list);
		}
		long end = System.currentTimeMillis();
		log.info("完成任务，耗时：" + (end - start) + "毫秒");
	}

//	多线程无法保证事务回滚
//	public void imp2(CommonsMultipartFile files[]) throws IOException, InterruptedException
//	{
//		log.info("开始做任务一");
//		long start = System.currentTimeMillis();
//		for (CommonsMultipartFile file : files)
//		{
//			List<String[]> list = POIUtil.readExcel(file);
//			for (String[] r : list)
//			{
//				dao.asyncAdd(r);
//			}
//		}
//		long end = System.currentTimeMillis();
//		log.info("完成任务一，耗时：" + (end - start) + "毫秒");
//	}

}
