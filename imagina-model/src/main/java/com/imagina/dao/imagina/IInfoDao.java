package com.imagina.dao.imagina;

import java.util.Date;
import java.util.List;

import com.imagina.model.Info;

public interface IInfoDao {

	void save(Info info);

	Info getById(Integer id);
	
	Info getByIdFilled(Integer id);
	
	List<Info> getAllFiltered(Date date);
	
	List<Info> getAllFilteredFilled(Date date);
	
	List<Info> getAllFiltered(Integer sec);
	
	List<Info> getAllFilteredFilled(Integer sec);
	
	Info getLast();
	
	Info getLastFilled();
	
	int cleanFrom(Date date);
	
	int cleanFrom(Date date, Integer sec);
	
}
