package in.nareshit.raghu.service;

import java.util.List;
import java.util.Map;

import in.nareshit.raghu.entity.Category;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
public interface ICategoryService {
	Long saveCategory(Category category);
	void updateCategory(Category category);
	void deleteCategory(Long id);
	Category getOneCategory(Long id);
	List<Category> getAllCategorys();
	
	Map<Long,String> getCategoryIdAndName(String status);
	List<Category> getCategoryByCategoryType(Long id);
	long totalCategoris();
}
