package com.wemall.shopcategories.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.wemall.shopcategories.entity.Categories;

public interface CategoriesService {
	List<Categories> selectAllCategories();

}
