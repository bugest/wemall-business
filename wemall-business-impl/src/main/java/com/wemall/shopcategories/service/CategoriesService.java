package com.wemall.shopcategories.service;



import java.util.List;

import com.wemall.shopcategories.entity.Categories;
import com.wemall.shopcategories.model.CategoryModel;

public interface CategoriesService {
	List<Categories> selectAllCategories();
	
	List<CategoryModel> getCategoryModelList();

}
