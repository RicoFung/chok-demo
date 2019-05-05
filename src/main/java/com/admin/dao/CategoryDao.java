package com.admin.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.admin.entity.Category;

import chok.devwork.springboot.BaseDao;

@Repository
public class CategoryDao extends BaseDao<Category, Long>
{
	@Resource // (name = "firstSqlSessionTemplate")
	private SqlSession		sqlSession;

	@Override
	protected SqlSession getSqlSession()
	{
		return sqlSession;
	}

	@Override
	public Class<Category> getEntityClass()
	{
		return Category.class;
	}

	@Async("asyncAddExecutor")
	public void asyncAdd(String[] r)
	{
		Category po = new Category();
		po.set("name", r[0]);
		po.set("sort", r[1]);
		this.add(po);
	}

}
