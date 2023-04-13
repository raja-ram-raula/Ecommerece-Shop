package in.nareshit.raghu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.entity.ProductRating;
import in.nareshit.raghu.repo.ProductRatingRepository;
import in.nareshit.raghu.service.IProductRatingService;

@Service
public class ProductRatingService implements IProductRatingService {
	
	@Autowired
	private ProductRatingRepository repo;

	public Long createProductRating(ProductRating pr) {
		return repo.save(pr).getId();
	}

	public boolean isCustomerRatedProduct(Long custId, Long prodId) {
		return repo.getCustomerRating(custId, prodId)>0;
	}

	public List<ProductRating> getAllProductRatings(Long prodId) {
		return repo.getAllProductRatings(prodId);
	}

}
