package com.binh.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.binh.core.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {

}
