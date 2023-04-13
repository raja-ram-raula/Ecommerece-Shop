package in.nareshit.raghu.service;

import java.util.List;
import java.util.Map;

import in.nareshit.raghu.entity.Brand;

public interface IBrandService {

	Long saveBrand(Brand b);
	void updateBrand(Brand b);
	void deleteBrand(Long id);
	Brand getOneBrand(Long id);
	List<Brand> getAllBrands();
	
	
	Map<Long,String> getBrandIdAndName();
	List<Object[]> getBrandIdAndImage();
	long totalBrands();
}
