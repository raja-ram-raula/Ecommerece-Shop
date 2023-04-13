package in.nareshit.raghu.service;

import java.util.List;

import in.nareshit.raghu.entity.Order;

public interface IOrderService {

	Long placeOrder(Order order);
	List<Order> getOrdersByCustomerId(Long id);
	List<Order> fetchAllOrders();
	void updateOrderStatus(Long id,String status);
	List<Order> findByOrderStatus(String status);
	Order getOrderById(Long id);
	List<Object[]> getOrderStatusAndCount();
}
