package in.nareshit.raghu.service;

import java.util.List;
import java.util.Map;

import in.nareshit.raghu.entity.CategoryType;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
public interface ICategoryTypeService {
	Long saveCategoryType(CategoryType categorytype);
	void updateCategoryType(CategoryType categorytype);
	void deleteCategoryType(Long id);
	CategoryType getOneCategoryType(Long id);
	List<CategoryType> getAllCategoryTypes();
	
	Map<Integer,String> getCategoryTypeIdAndName();
	long totalCategoryTypes();
}
