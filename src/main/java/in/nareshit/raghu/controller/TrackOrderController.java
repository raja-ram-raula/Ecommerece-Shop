package in.nareshit.raghu.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.nareshit.raghu.entity.TrackOrder;
import in.nareshit.raghu.service.ITrackOrderService;

@Controller
@RequestMapping("/track")
public class TrackOrderController {
	
	@Autowired
	private ITrackOrderService service;

	@GetMapping("/register")
	public String viewAddTrackPage(
			@RequestParam Long orderId,
			Model model
			) 
	{
		model.addAttribute("orderId", orderId);
		return "TrackOrderCreate";
	}
	
	@PostMapping("/add")
	public String AddOneTrackForOrder(
			@ModelAttribute TrackOrder trackOrder,
			Model model
			)
	{
		trackOrder.setDte(new Date());
		service.addTrackOrder(trackOrder);
		model.addAttribute("orderId", trackOrder.getOrder().getId());
		model.addAttribute("message", "NEW TRACKING IS ADDED!");
		return "TrackOrderCreate";
	}
	
	@GetMapping("/data")
	public String viewTrackForOrder(
			@RequestParam Long orderId,
			Model model
			) 
	{
		List<TrackOrder> list = service.getAllTrackOrdersByOrderid(orderId);
		model.addAttribute("list", list);
		return "TrackOrderData";
	}
}
