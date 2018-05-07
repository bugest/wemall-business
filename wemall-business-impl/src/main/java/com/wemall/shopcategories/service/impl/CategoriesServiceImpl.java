package com.wemall.shopcategories.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemall.shopcategories.dao.CategoriesDao;
import com.wemall.shopcategories.entity.Categories;
import com.wemall.shopcategories.service.CategoriesService;
@Service
public class CategoriesServiceImpl implements CategoriesService {
	
	@Autowired 
	private CategoriesDao categoriesDao;
	@Override
	public List<Categories> selectAllCategories() {
		// TODO Auto-generated method stub
		List<Categories> categories = categoriesDao.selectAllCategories();
		return categories;
	}

}
