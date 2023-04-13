package in.nareshit.raghu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.raghu.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("UPDATE Order SET status=:status WHERE id=:id")
	@Modifying
	void updateOrderStatus(Long id,String status);
	
	@Query("SELECT ord FROM Order ord JOIN ord.customer as customer WHERE customer.id=:custId")
	List<Order> getOrdersByCustomer(Long custId);
	
	List<Order> findByStatus(String status);
	
	@Query("SELECT status, COUNT(status) FROM Order GROUP BY status")
	List<Object[]> getOrderStatusAndCount();
	
}
