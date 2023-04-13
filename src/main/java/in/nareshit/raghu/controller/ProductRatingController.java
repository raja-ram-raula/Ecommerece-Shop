package in.nareshit.raghu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.nareshit.raghu.entity.Customer;
import in.nareshit.raghu.entity.ProductRating;
import in.nareshit.raghu.service.IProductRatingService;
import in.nareshit.raghu.service.IProductService;

@Controller
@RequestMapping("/rating")
public class ProductRatingController {

	@Autowired
	private IProductRatingService service;
	
	@Autowired
	private IProductService productService;
	
	@GetMapping("/add")
	@ResponseBody
	public String addRating(
			@RequestParam Long prodId,
			@RequestParam Integer starVal,
			@RequestParam String review,
			HttpSession session
			) 
	{
		ProductRating pr = new ProductRating();
		pr.setProduct(productService.getOneProduct(prodId));
		pr.setStarVal(starVal);
		pr.setReview(review);
		Customer cust =(Customer) session.getAttribute("customer");
		pr.setCustomer(cust);
		service.createProductRating(pr);
		return "Product Rated!!";
	}
	
	
}
