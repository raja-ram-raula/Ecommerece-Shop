package in.nareshit.raghu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.nareshit.raghu.entity.Brand;
import in.nareshit.raghu.service.IBrandService;

@Controller
@RequestMapping("/brand")
public class BrandController {

	@Autowired
	private IBrandService service;

	//1. show Register page
	@GetMapping("register")
	public String showReg() {
		return "BrandRegister";
	}
	
	//2. save brand 
	@PostMapping("/save")
	public String saveBrand(
			@ModelAttribute Brand brand,
			Model model
			) 
	{
		Long id = service.saveBrand(brand);
		String message = "Brand '"+id+"' is created";
		model.addAttribute("message", message);
		return "BrandRegister";
	}
	
	//3. fetch all brands
	@GetMapping("/all")
	public String getAllBrands(Model model) 
	{
		List<Brand> list = service.getAllBrands();
		model.addAttribute("list", list);
		return "BrandData";
	}
}
