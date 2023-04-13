package in.nareshit.raghu.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import in.nareshit.raghu.entity.CartItem;
import in.nareshit.raghu.entity.Customer;
import in.nareshit.raghu.entity.Order;
import in.nareshit.raghu.service.ICartItemService;
import in.nareshit.raghu.service.ICustomerService;
import in.nareshit.raghu.service.IOrderService;
import in.nareshit.raghu.util.OrderUtil;
import in.nareshit.raghu.view.CustomerInvoiceView;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private IOrderService service;
	@Autowired
	private ICartItemService cartItemService;
	@Autowired
	private OrderUtil orderUtil;
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private ServletContext sc;
	
	@GetMapping("/create")
	public String placeOrder(
			HttpSession session
			) 
	{
		Customer cust =(Customer) session.getAttribute("customer");
		List<CartItem> cartItems = cartItemService.viewAllItems(cust.getId());
		Order order = orderUtil.mapCartItemsToOrder(cartItems);
		order.setCustomer(cust);
		orderUtil.calculateGrandTotalOrder(order);
		order.setBillingAmt(order.getGrandTotal());
		service.placeOrder(order);
		cartItemService.deleteAllCartItems(cartItems);
		session.setAttribute("cartItemsCount", 0);
		return "redirect:customerOrders";
	}
	
	@GetMapping("/customerOrders")
	public String getCustomerOrders(
			HttpSession session,
			Model model
			)
	{
		Customer cust =(Customer) session.getAttribute("customer");
		List<Order> orders = service.getOrdersByCustomerId(cust.getId());
		orderUtil.calculateGrandTotal(orders);
		model.addAttribute("list", orders);
		model.addAttribute("custAddr", customerService.getCustomerAddress(cust.getId()).get(0));
		return "CustomerOrdersPage";
	}
	
	@GetMapping("/all")
	public String getAllOrders(Model model) {
		List<Order> orders = service.fetchAllOrders();
		model.addAttribute("list", orders);
		return "OrdersDataPage";
	}
	
	@GetMapping("/updateStatus")
	public String updateOrderStatus(
			@RequestParam Long id,
			@RequestParam String status
			)
	{
		service.updateOrderStatus(id, status);
		return "redirect:all";
	}
	
	@GetMapping("/invoice")
	public ModelAndView printOrderInvoice(
			@RequestParam Long orderId
			)
	{
		ModelAndView mav = new ModelAndView();
		mav.setView(new CustomerInvoiceView());
		Order order = service.getOrderById(orderId);
		orderUtil.calculateGrandTotalOrder(order);
		mav.addObject("order", order);
		return mav;
	}
	
	@GetMapping("/reports")
	public String showReports() 
	{
		List<Object[]> data = service.getOrderStatusAndCount();
		String path = sc.getRealPath("/");
		orderUtil.generatePie(data,path);
		orderUtil.generateBar(data,path);
		return "SalesReport";
	}
}
