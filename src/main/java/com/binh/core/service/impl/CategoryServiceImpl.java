package com.binh.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.binh.core.dto.request.CategoryRequest;
import com.binh.core.entity.Category;
import com.binh.core.repository.CategoryRepository;
import com.binh.core.service.CategoryService;
import com.binh.core.util.SlugUtil;

import javassist.NotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository repo;

	@Override
	public Category saveCategory(CategoryRequest categoryReq) throws NotFoundException {
		Category newCategory = new Category();
		newCategory.setCode(categoryReq.getCode());
		newCategory.setName(categoryReq.getName());
		newCategory.setPosition(categoryReq.getPosition());
		newCategory.setIsEnable(categoryReq.getIsEnable());
		newCategory.setIsVisible(categoryReq.getIsVisible());
		newCategory.setDescription(categoryReq.getDescription());
		newCategory.setMetaTagTitle(categoryReq.getMetaTagTitle());
		newCategory.setMetaTagDescription(categoryReq.getMetaTagDescription());
		newCategory.setMetaTagKeywords(categoryReq.getMetaTagKeywords());

		if (ObjectUtils.isEmpty(categoryReq.getSlug())) {
			newCategory.setSlug(SlugUtil.toSlug(categoryReq.getName()));
		}

		if (!ObjectUtils.isEmpty(categoryReq.getParent())) {
			Category parent = repo.findById(categoryReq.getParent())
					.orElseThrow(() -> new NotFoundException("Parent not found"));
			newCategory.setParent(parent);
			newCategory.setLevel(parent.getLevel() + 1);
		} else {
			newCategory.setLevel(0);
		}

		return repo.save(newCategory);
	}

	@Override
	public Category getCategoryByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> getAll() {
		return repo.findAll();
	}

}
