package in.nareshit.raghu.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.raghu.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	/**
	 * This Query gets all Cart Items for a customer
	 */
	@Query("SELECT c FROM CartItem c JOIN c.customer as customer WHERE customer.id=:custId")
	List<CartItem> fetchCartItemsByCustomer(Long custId);

	/**
	 * This query gets cart item of customer for a selected product 
	 */
	@Query("SELECT c FROM CartItem c JOIN c.customer as customer JOIN c.product as product WHERE customer.id=:custId AND product.id=:prodId")
	Optional<CartItem> fetchCartItemByCustomerAndProduct(Long custId,Long prodId);
	
	/**
	 * Update cartItem Qty based on cartItemId
	 */
	@Query("UPDATE CartItem SET qty=qty+:newQty WHERE id=:cartItemId")
	@Modifying
	void updateCartItemQty(Long cartItemId,Integer newQty);

	@Query("SELECT COUNT(c) FROM CartItem c JOIN c.customer as customer WHERE customer.id=:custId")
	int getCartItemsCount(Long custId);
}
