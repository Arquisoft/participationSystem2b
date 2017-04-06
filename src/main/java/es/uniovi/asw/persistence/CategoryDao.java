package es.uniovi.asw.persistence;

import java.util.List;

import es.uniovi.asw.model.Category;

public interface CategoryDao {

	public void addCategory(Category category);
	public void deleteCateogory(Category category);
	public void updateCategory(Category category);
	public List<Category> findAllCategories();
	public Category findById(Long categoria);
}
