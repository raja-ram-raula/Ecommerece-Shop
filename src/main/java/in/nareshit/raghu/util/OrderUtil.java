package in.nareshit.raghu.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

import in.nareshit.raghu.constants.OrderStatus;
import in.nareshit.raghu.entity.CartItem;
import in.nareshit.raghu.entity.Order;
import in.nareshit.raghu.entity.OrderItem;

@Component
public class OrderUtil {

	public Order mapCartItemsToOrder(List<CartItem> cartItems) {
		Order order = new Order();
		order.setStatus(OrderStatus.OPEN.name());
		List<OrderItem> orderItemsList = new ArrayList<>();
		for(CartItem item: cartItems) {
			OrderItem orderItem = new OrderItem();
			orderItem.setProduct(item.getProduct());
			orderItem.setDiscount(item.getProduct().getDiscount());
			orderItem.setQty(item.getQty());
			if(orderItem.getDiscount()==null)
				orderItem.setLineAmount(orderItem.getQty() * item.getProduct().getCost());
			else 
				orderItem.setLineAmount(orderItem.getQty() * 
						(item.getProduct().getCost()-orderItem.getDiscount()));
			orderItemsList.add(orderItem);
		}
		order.setOrderItems(orderItemsList);
		return order;
	}

	public void calculateGrandTotal(List<Order> orders) {
		for(Order order:orders) {
			calculateGrandTotalOrder(order);
		}
	}

	public void calculateGrandTotalOrder(Order order) {
		Double amount = order.getOrderItems().stream()
				.mapToDouble(ob->ob.getLineAmount())
				.sum();
		order.setGrandTotal(amount);

	}

	public void generatePie(List<Object[]> data, String path) {
		//1. create dataset
		DefaultPieDataset dataset = new DefaultPieDataset();
		for(Object[] ob : data) {
			//key -val
			dataset.setValue(ob[0].toString(), Double.valueOf(ob[1].toString()));
		}

		//2. create JFreeChart object using ChartFactory
		JFreeChart chart = ChartFactory.createPieChart("ORDER STATUS CHART", dataset);

		//3. create Image using JFreeChart object with ChartUtils
		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/orderA.jpg"), chart, 400, 400);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void generateBar(List<Object[]> data, String path) {
		//1. create dataset
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(Object[] ob:data) {
			dataset.setValue(Double.valueOf(ob[1].toString()), ob[0].toString(), "");
		}

		//2. create JFreeChart object using ChartFactory
		JFreeChart chart = ChartFactory.createBarChart("ORDER STATUS", "STATUS CODE", "COUNT", dataset);

		//3. create Image using JFreeChart object with ChartUtils
		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/orderB.jpg"), chart, 400, 400);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
