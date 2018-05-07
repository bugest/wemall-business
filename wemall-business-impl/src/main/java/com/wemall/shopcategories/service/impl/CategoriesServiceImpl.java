package com.wemall.shopcategories.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemall.shopcategories.dao.CategoriesDao;
import com.wemall.shopcategories.entity.Categories;
import com.wemall.shopcategories.model.CategoryModel;
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

	@Override
	public List<CategoryModel> getCategoryModelList() {
		// TODO Auto-generated method stub
		List<Categories> categoriesList = selectAllCategories();
		// 首先增加全部商家
		CategoryModel categoryModel = new CategoryModel();
		categoryModel.setName("全部商家");
		categoryModel.setLevel(1);
		List<Integer> ids = new ArrayList<Integer>();
		for (Categories categories : categoriesList) {
			if (categories.getLevel() != null && categories.getLevel() == 1) {
				ids.add(categories.getId());
				// 如果
			}
		}
		categoryModel.setIds(ids);
		List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
		// 将全部商家的id加入
		categoryModelList.add(categoryModel);
		return categoryModelList;
	}

}
