package in.nareshit.raghu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nareshit.raghu.entity.Category;
import in.nareshit.raghu.repo.CategoryRepository;
import in.nareshit.raghu.service.ICategoryService;
import in.nareshit.raghu.util.AppUtil;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
	@Autowired
	private CategoryRepository repo;

	@Override
	@Transactional
	public Long saveCategory(Category category) {
		return repo.save(category).getId();
	}

	@Override
	@Transactional
	public void updateCategory(Category category) {
		repo.save(category);
	}

	@Override
	@Transactional
	public void deleteCategory(Long id) {
		repo.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Category getOneCategory(Long id) {
		return repo.findById(id).get();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Category> getAllCategorys() {
		return repo.findAll();
	}
	
	@Override
	public Map<Long, String> getCategoryIdAndName(String status) {
		List<Object[]> list = repo.getCategoryIdAndName(status);
		return AppUtil.convertListToMapLong(list);
	}
	
	@Override
	public List<Category> getCategoryByCategoryType(Long id) {
		return repo.getCategoryByCategoryType(id);
	}
	
	@Override
	public long totalCategoris() {
		return repo.count();
	}
}
