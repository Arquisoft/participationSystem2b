package es.uniovi.asw.service;

import java.util.List;

import es.uniovi.asw.model.Category;

public interface CategoryService {

	public void addCategory(Category category);
	public void deleteCateogory(Category category);
	public void updateCategory(Category category);
	public List<Category> findAllCategories();
	public Category findById(Long categoria);
}
