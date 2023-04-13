package in.nareshit.raghu.service;

import java.util.List;

import in.nareshit.raghu.entity.ProductRating;

public interface IProductRatingService {

	Long createProductRating(ProductRating pr);
	boolean isCustomerRatedProduct(Long custId,Long prodId);
	List<ProductRating> getAllProductRatings(Long prodId);
}
