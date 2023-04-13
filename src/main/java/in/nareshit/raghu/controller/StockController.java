package in.nareshit.raghu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.nareshit.raghu.entity.Stock;
import in.nareshit.raghu.service.IProductService;
import in.nareshit.raghu.service.IStockService;

@Controller
@RequestMapping("/stock")
public class StockController {

	@Autowired
	private IStockService service;
	
	@Autowired
	private IProductService productService;
	
	private void commonUi(Model model) {
		model.addAttribute("products", productService.getProductIdAndName());
	}
	
	@GetMapping("/register")
	public String showAddPage(Model model) {
		commonUi(model);
		return "StockRegister";
	}

	@PostMapping("/save")
	public String createStock(
			@ModelAttribute Stock stock,
			Model model
			) 
	{
		String message = null;
		Long productId = stock.getProduct().getId();
		Long id = service.getStockIdByProduct(productId);
		if(id!=null) {
			service.updateStock(id, stock.getCount());
			message = " Stock Updated! ";
		} else {
			stock.setQoh(stock.getCount());
			stock.setSold(Long.valueOf(0L));
			service.createStock(stock);
			message = " Stock Added! ";
		}
		model.addAttribute("message", message);
		commonUi(model);
		return "StockRegister";
	}
}
