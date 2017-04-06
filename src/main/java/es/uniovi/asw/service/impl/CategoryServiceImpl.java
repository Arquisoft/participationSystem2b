package es.uniovi.asw.service.impl;

import java.util.List;

import es.uniovi.asw.model.Category;
import es.uniovi.asw.persistence.impl.CategoryDaoImpl;
import es.uniovi.asw.service.CategoryService;

public class CategoryServiceImpl implements CategoryService{

	private CategoryDaoImpl dao = new CategoryDaoImpl();
	@Override
	public void addCategory(Category category) {
		dao.addCategory(category);
		
	}

	@Override
	public void deleteCateogory(Category category) {
		dao.deleteCateogory(category);
	}

	@Override
	public void updateCategory(Category category) {
		dao.updateCategory(category);
	}

	@Override
	public List<Category> findAllCategories() {
		return dao.findAllCategories();
	}
	@Override
	public Category findById(Long categoria) {
		return dao.findById(categoria);
	}
	
	
	

}
