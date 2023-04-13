package in.nareshit.raghu.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nareshit.raghu.entity.Coupon;
import in.nareshit.raghu.entity.Order;
import in.nareshit.raghu.exception.CouponNotFoundException;
import in.nareshit.raghu.service.ICouponService;
import in.nareshit.raghu.service.IOrderService;
import in.nareshit.raghu.util.OrderUtil;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
@Controller
@RequestMapping("/coupon")
public class CouponController {
	@Autowired
	private ICouponService service;
	@Autowired
	private IOrderService orderService;
	@Autowired
	private OrderUtil orderUtil;

	@GetMapping("/register")
	public String registerCoupon(Model model) {
		model.addAttribute("coupon",new Coupon());
		return "CouponRegister";
	}

	@PostMapping("/save")
	public String saveCoupon(@ModelAttribute Coupon coupon, Model model) {
		java.lang.Long id=service.saveCoupon(coupon);
		model.addAttribute("message","Coupon created with Id:"+id);
		model.addAttribute("coupon",new Coupon()) ;
		return "CouponRegister";
	}

	@GetMapping("/all")
	public String getAllCoupons(Model model,
			@RequestParam(value = "message", required = false) String message) {
		List<Coupon> list=service.getAllCoupons();
		model.addAttribute("list",list);
		model.addAttribute("message",message);
		return "CouponData";
	}

	@GetMapping("/delete")
	public String deleteCoupon(@RequestParam Long id, RedirectAttributes attributes) {
		try {
			service.deleteCoupon(id);
			attributes.addAttribute("message","Coupon deleted with Id:"+id);
		} catch(CouponNotFoundException e) {
			e.printStackTrace() ;
			attributes.addAttribute("message",e.getMessage());
		}
		return "redirect:all";
	}

	@GetMapping("/edit")
	public String editCoupon(@RequestParam Long id, Model model, RedirectAttributes attributes) {
		String page=null;
		try {
			Coupon ob=service.getOneCoupon(id);
			model.addAttribute("coupon",ob);
			page="CouponEdit";
		} catch(CouponNotFoundException e) {
			e.printStackTrace() ;
			attributes.addAttribute("message",e.getMessage());
			page="redirect:all";
		}
		return page;
	}

	@PostMapping("/update")
	public String updateCoupon(@ModelAttribute Coupon coupon, RedirectAttributes attributes) {
		service.updateCoupon(coupon);
		attributes.addAttribute("message","Coupon updated");
		return "redirect:all";
	}
	
	@PostMapping("/apply")
	public String updateCoupon(
			@RequestParam String couponCode,
			@RequestParam Long orderId,
			RedirectAttributes attributes) {
	 	Optional<Coupon> opt = service.findByCode(couponCode);
	 	String message = null;
	 	if(opt.isPresent()) {
	 	 	Coupon c = opt.get();
	 	 	if(c.getTotalAllowed()<=0) {
	 	 		message = "THIS OFFER IS AVAILED BY MAX NO.OF USERS!";
	 	 	} else if(c.getExpDate().before(new Date(System.currentTimeMillis()))) {
	 	 		message = "THIS COUPON IS EXPIRED!";
	 	 	} else {
	 	 		Order order = orderService.getOrderById(orderId);
	 	 		
	 	 		/**coupon calculations*/
	 	 		Integer percentage = c.getPercentage();
	 	 		orderUtil.calculateGrandTotalOrder(order);
	 	 		double couponAmt = (order.getGrandTotal() * percentage) / 100.0;
	 	 		couponAmt = Math.min(couponAmt,c.getMaxAllowed());
	 	 		/**coupon calculations*/
	 	 		
	 	 		order.setCoupon(c);
	 	 		order.setCouponAmt(couponAmt);
	 	 		order.setBillingAmt(order.getGrandTotal()-couponAmt);
	 	 		orderService.placeOrder(order);
	 	 		
	 	 		//**/
	 	 		c.setTotalAllowed(c.getTotalAllowed()-1);
	 	 		service.updateCoupon(c);
	 	 		//**/
	 	 		
	 	 		message = "COUPON IS APPLIED!!";
	 	 	}
	 	} else {
	 		message = "COUPON CODE NOT EXIST!";
	 	}
		attributes.addAttribute("message",message);
		attributes.addAttribute("orderId",orderId);
		return "redirect:/payment/billPay";
	}
	
	@GetMapping("/remove")
	public String updateCoupon(
			@RequestParam Long orderId,
			RedirectAttributes attributes) {
		Order order = orderService.getOrderById(orderId);
		Coupon coupon = order.getCoupon();
		coupon.setTotalAllowed(coupon.getTotalAllowed()+1);		
		service.updateCoupon(coupon);
		order.setCoupon(null);
		order.setBillingAmt(order.getGrandTotal());
		orderService.placeOrder(order);
		attributes.addAttribute("message","COUPON IS REMOVED");
		attributes.addAttribute("orderId",orderId);
		return "redirect:/payment/billPay";
	}
}
