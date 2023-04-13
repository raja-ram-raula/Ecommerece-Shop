package in.nareshit.raghu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.raghu.entity.ProductRating;

public interface ProductRatingRepository extends JpaRepository<ProductRating, Long> {

	@Query("SELECT COUNT(pr.id) FROM ProductRating pr JOIN pr.customer as customer JOIN pr.product as product WHERE customer.id=:custId AND product.id=:prodId")
	int getCustomerRating(Long custId,Long prodId);
	
	@Query("SELECT pr FROM ProductRating pr JOIN pr.product as product WHERE product.id=:prodId")
	List<ProductRating> getAllProductRatings(Long prodId);
}
