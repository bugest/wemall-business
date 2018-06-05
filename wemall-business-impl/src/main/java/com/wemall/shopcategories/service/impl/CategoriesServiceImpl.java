package com.wemall.shopcategories.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemall.redis.service.impl.RedisTemplateUtil;
import com.wemall.shopcategories.dao.CategoriesDao;
import com.wemall.shopcategories.entity.Categories;
import com.wemall.shopcategories.model.CategoryModel;
import com.wemall.shopcategories.service.CategoriesService;

@Service
public class CategoriesServiceImpl implements CategoriesService {

	private CategoriesDao categoriesDao;

	private RedisTemplateUtil redisTemplateUtil;

	// 可以自定义构造函授
	@Autowired
	public CategoriesServiceImpl(CategoriesDao categoriesDao, RedisTemplateUtil redisTemplateUtil) {

		this.categoriesDao = categoriesDao;
		this.redisTemplateUtil = redisTemplateUtil;
		List<Categories> allCategories = categoriesDao.selectAllCategories();
		// 把目录写入缓存
		redisTemplateUtil.set("allCategories", allCategories);
	}

	public List<Categories> selectAllCategories() {
		// TODO Auto-generated method stub
		//优先从缓存读取
		@SuppressWarnings("unchecked")
		List<Categories> allCategories = (List<Categories>) redisTemplateUtil.get("allCategories");
		//List<Categories> categories = null;
		if (allCategories != null && allCategories.size() > 0) {
			return allCategories;
		} else {
			List<Categories> allCategorie = categoriesDao.selectAllCategories();
			//查到并且写入缓存
			redisTemplateUtil.set("allCategories", allCategorie);
			return allCategorie;
		}
	}

	public List<CategoryModel> getCategoryModelList() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Categories> allCategories = (List<Categories>) redisTemplateUtil.get("allCategories");
		List<Categories> categoriesList = null;
		if (allCategories != null && allCategories.size() > 0) {
			categoriesList = allCategories;
		} else {
			categoriesList = categoriesDao.selectAllCategories();
			//查到并且写入缓存
			redisTemplateUtil.set("allCategories", categoriesList);
		}
		if (categoriesList == null) {
			return null;
		}
		// 首先增加全部商家
		CategoryModel categoryModel = new CategoryModel();
		categoryModel.setName("全部商家");
		categoryModel.setLevel(1);
		List<Integer> ids = new ArrayList<Integer>();
		List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
		for (Categories categories : categoriesList) {
			if (categories.getLevel() != null && categories.getLevel() == 1) {
				ids.add(categories.getId());
				// 同时创建了子
				CategoryModel cm = new CategoryModel();
				cm.setName(categories.getName());
				cm.setId(categories.getId());
				cm.setLevel(1);
				List<CategoryModel> categoryModelSubList = new ArrayList<CategoryModel>();
				// 每个节点增加全部
				CategoryModel suball = new CategoryModel();
				suball.setId(cm.getId());
				suball.setName("全部");
				suball.setLevel(1);
				categoryModelSubList.add(suball);
				List<Integer> idss = new ArrayList<Integer>();
				idss.add(cm.getId());
				for (Categories cgl : categoriesList) {
					if (cgl.getParentId() != null && cgl.getParentId().equals(cm.getId())) {
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
		categoryModelList.add(0, categoryModel);
		return categoryModelList;
	}

	public Categories selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return categoriesDao.selectByPrimaryKey(id);
	}

}
