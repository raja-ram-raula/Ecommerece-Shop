package in.nareshit.raghu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nareshit.raghu.entity.CategoryType;
import in.nareshit.raghu.repo.CategoryTypeRepository;
import in.nareshit.raghu.service.ICategoryTypeService;
import in.nareshit.raghu.util.AppUtil;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
@Service
public class CategoryTypeServiceImpl implements ICategoryTypeService {
	@Autowired
	private CategoryTypeRepository repo;

	@Override
	@Transactional
	public Long saveCategoryType(CategoryType categorytype) {
		return repo.save(categorytype).getId();
	}

	@Override
	@Transactional
	public void updateCategoryType(CategoryType categorytype) {
		repo.save(categorytype);
	}

	@Override
	@Transactional
	public void deleteCategoryType(Long id) {
		repo.deleteById(id);
	}

	@Override
	@Transactional(
			readOnly = true
			)
	public CategoryType getOneCategoryType(Long id) {
		return repo.findById(id).get();
	}

	@Override
	@Transactional(
			readOnly = true
			)
	public List<CategoryType> getAllCategoryTypes() {
		return repo.findAll();
	}
	
	@Override
	public Map<Integer, String> getCategoryTypeIdAndName() {
		List<Object[]>  list = repo.getCategoryTypeIdAndName();
		return AppUtil.convertListToMap(list);
	}
	
	@Override
	public long totalCategoryTypes() {
		return repo.count();
	}
}
