package com.wemall.shopcategories.model;

import java.io.Serializable;
import java.util.List;

public class CategoryModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private List<Integer> ids;
	private Integer level;
	private String name;
	private List<CategoryModel> subCategories;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CategoryModel> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<CategoryModel> subCategories) {
		this.subCategories = subCategories;
	}

}