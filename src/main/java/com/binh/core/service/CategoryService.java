package com.binh.core.service;

import java.util.List;

import com.binh.core.dto.request.CategoryRequest;
import com.binh.core.entity.Category;

import javassist.NotFoundException;

public interface CategoryService {
	/**
	 * Create a category
	 * 
	 * @param category A category to be created
	 * @return A Category
	 * @throws NotFoundException 
	 */
	public Category saveCategory(CategoryRequest category) throws NotFoundException;

	/**
	 * Get a Category by given code
	 * 
	 * @param code
	 * @return
	 */
	public Category getCategoryByCode(String code);

	/**
	 * Get all category
	 * @return
	 */
	public List<Category> getAll();
}