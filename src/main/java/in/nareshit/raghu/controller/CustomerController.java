package in.nareshit.raghu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.nareshit.raghu.entity.Customer;
import in.nareshit.raghu.service.ICustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private ICustomerService service;
	
	//1. show Register page
	@GetMapping("/register")
	public String showReg() {
		return "CustomerRegister";
	}
	
	//2. save User
	@PostMapping("/save")
	public String saveUser(
			@ModelAttribute Customer appUser,
			Model model
			) 
	{
		Long id = service.saveCustomer(appUser);
		model.addAttribute("message", "Customer '"+id+"' is created");
		return "CustomerRegister";
	}
	//3. show data
	@GetMapping("/all")
	public String showData(Model model) {
		model.addAttribute("list", service.getAllCustomers());
		return "CustomerData";
	}
}
