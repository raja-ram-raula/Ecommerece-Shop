package in.nareshit.raghu.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.entity.Brand;
import in.nareshit.raghu.repo.BrandRepository;
import in.nareshit.raghu.service.IBrandService;
import in.nareshit.raghu.util.AppUtil;

@Service
public class BrandServiceImpl implements IBrandService {

	@Autowired
	private BrandRepository repo;
	
	public Long saveBrand(Brand b) {
		return repo.save(b).getId();
	}

	public void updateBrand(Brand b) {
		repo.save(b);
	}

	public void deleteBrand(Long id) {
		repo.deleteById(id);
	}

	public Brand getOneBrand(Long id) {
		Optional<Brand>  opt = repo.findById(id);
		if(opt.isPresent()) return opt.get();
		return null;
	}

	public List<Brand> getAllBrands() {
		return repo.findAll();
	}
	
	public Map<Long, String> getBrandIdAndName() {
		List<Object[]> list = repo.getBrandIdAndName();
		return AppUtil.convertListToMapLong(list);
	}
	
	@Override
	public List<Object[]> getBrandIdAndImage() {
		return repo.getBrandIdAndImage();
	}
	
	@Override
	public long totalBrands() {
		return repo.count();
	}

}
