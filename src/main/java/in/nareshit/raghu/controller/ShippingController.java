package in.nareshit.raghu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nareshit.raghu.entity.Shipping;
import in.nareshit.raghu.exception.ShippingNotFoundException;
import in.nareshit.raghu.service.IShippingService;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
@Controller
@RequestMapping("/shipping")
public class ShippingController {
	
	@Autowired
	private IShippingService service;

	@GetMapping("/register")
	public String registerShipping(Model model) {
		return "ShippingRegister";
	}

	@PostMapping("/save")
	public String saveShipping(@ModelAttribute Shipping shipping, Model model) {
		Long id=service.saveShipping(shipping);
		model.addAttribute("message","Shipping created with Id:"+id);
		return "ShippingRegister";
	}

	@GetMapping("/all")
	public String getAllShippings(Model model,
			@RequestParam(value = "message", required = false) String message) {
		List<Shipping> list=service.getAllShippings();
		model.addAttribute("list",list);
		model.addAttribute("message",message);
		return "ShippingData";
	}

	@GetMapping("/delete")
	public String deleteShipping(@RequestParam Long id, RedirectAttributes attributes) {
		try {
			service.deleteShipping(id);
			attributes.addAttribute("message","Shipping deleted with Id:"+id);
		} catch(ShippingNotFoundException e) {
			e.printStackTrace() ;
			attributes.addAttribute("message",e.getMessage());
		}
		return "redirect:all";
	}

	@GetMapping("/edit")
	public String editShipping(@RequestParam Long id, Model model, RedirectAttributes attributes) {
		String page=null;
		try {
			Shipping ob=service.getOneShipping(id);
			model.addAttribute("shipping",ob);
			page="ShippingEdit";
		} catch(ShippingNotFoundException e) {
			e.printStackTrace() ;
			attributes.addAttribute("message",e.getMessage());
			page="redirect:all";
		}
		return page;
	}

	@PostMapping("/update")
	public String updateShipping(@ModelAttribute Shipping shipping, RedirectAttributes attributes) {
		service.updateShipping(shipping);
		attributes.addAttribute("message","Shipping updated");
		return "redirect:all";
	}
}
