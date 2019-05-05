package com.admin.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryJdbcDao
{
	@Autowired
	//@Qualifier("mysqlJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public void addBatch(List<String[]> list)
	{
		jdbcTemplate.batchUpdate("insert into tb_category(name,sort) values (?,?)", new BatchPreparedStatementSetter()
		{
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException
			{
				String[] r = list.get(i);
				ps.setString(1, r[0]);
				ps.setString(2, r[1]);
			}

			@Override
			public int getBatchSize()
			{
				return list.size();
			}
		});
	}

}
