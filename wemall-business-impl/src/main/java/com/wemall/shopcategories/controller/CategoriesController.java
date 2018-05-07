package com.wemall.shopcategories.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wemall.shopcategories.entity.Categories;
import com.wemall.shopcategories.service.CategoriesService;

@Controller
@RequestMapping("/categories")
public class CategoriesController {

	@Autowired
	private CategoriesService categoriesService;
	
	@ResponseBody
	@RequestMapping("/selectAllCategories")
	public List<Categories> selectAllCategories() {
		return categoriesService.selectAllCategories();
	}
}
