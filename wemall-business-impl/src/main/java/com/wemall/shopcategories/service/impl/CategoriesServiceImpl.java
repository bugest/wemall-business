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
		List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
		for (Categories categories : categoriesList) {
			if (categories.getLevel() != null && categories.getLevel() == 1) {
				ids.add(categories.getId());
				//同时创建了子
				CategoryModel cm = new CategoryModel();
				cm.setName(categories.getName());
				cm.setId(categories.getId());
				cm.setLevel(1);
				List<CategoryModel> categoryModelSubList = new ArrayList<CategoryModel>();
				//每个节点增加全部
				CategoryModel suball = new CategoryModel();
				suball.setId(cm.getId());
				suball.setName("全部");
				suball.setLevel(1);
				categoryModelSubList.add(suball);
				List<Integer> idss = new ArrayList<Integer>(); 
				idss.add(cm.getId());
				for (Categories cgl : categoriesList) {
					if(cgl.getParentId() != null && cgl.getParentId().equals(cm.getId())) {
						CategoryModel cmsub = new CategoryModel();
						cmsub.setId(cgl.getId());
						cmsub.setName(cgl.getName());
						cmsub.setLevel(1);
						idss.add(cgl.getId());
						categoryModelSubList.add(cmsub);	
					}
				}
				cm.setSubCategories(categoryModelSubList);
				cm.setIds(idss);
				categoryModelList.add(cm);
			}
		}
		categoryModel.setIds(ids);
		// 将全部商家的id加入
		categoryModelList.add(0,categoryModel);
		return categoryModelList;
	}

}
