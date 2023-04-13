package in.nareshit.raghu.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.nareshit.raghu.entity.Category;
import in.nareshit.raghu.entity.CategoryType;
import in.nareshit.raghu.entity.Customer;
import in.nareshit.raghu.entity.Product;
import in.nareshit.raghu.service.IBrandService;
import in.nareshit.raghu.service.ICategoryService;
import in.nareshit.raghu.service.ICategoryTypeService;
import in.nareshit.raghu.service.IProductRatingService;
import in.nareshit.raghu.service.IProductService;

@Controller
@RequestMapping({"/search","/"})
public class SearchController {
	
	@Autowired
	private IBrandService brandService;
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ICategoryTypeService categoryTypeService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private IProductRatingService productRatingService;

	@GetMapping({"/","/showBrands"})
	public String showBrands(Model model) 
	{
		//get Brand Image and Id
		List<Object[]> list = brandService.getBrandIdAndImage();
		model.addAttribute("list", list);
		return "BrandPage";
	}
	
	@GetMapping("/products")
	public String showProductsByBrand(
			@RequestParam("brandId")Long brandId,
			Model model
			) 
	{
		List<Object[]> list = productService.getProductsByBrands(brandId);
		model.addAttribute("list", list);
		return "ProductsResult";
	}
	
	//show all CategoryTypes
	@GetMapping("/categories")
	public String showAllCategoryTypes(Model model) {
		List<CategoryType> list = categoryTypeService.getAllCategoryTypes();
		model.addAttribute("list", list);
		return "CategoryTypesPage";
	}

	//show all Categories by CategoryType
	@GetMapping("/getCategories")
	public String showAllCategoriesByType(
			@RequestParam Long catTypeId,
			Model model
			) 
	{
		List<Category> list = categoryService.getCategoryByCategoryType(catTypeId);
		model.addAttribute("list", list);
		return "CategoriesPage";
	}

	//show all Products by Category
	@GetMapping("/categoryProducts")
	public String showAllProductsByCategory(
			@RequestParam Long catId,
			Model model
			) 
	{
		List<Object[]> list = productService.getProductsByCategory(catId);
		model.addAttribute("list", list);
		return "ProductsResult";
	}
	
	@GetMapping("/byProduct")
	public String showAllProductsByName(
			@RequestParam String productname,
			Model model
			) 
	{
		List<Object[]> list = productService.getProductByNameMatching(productname);
		model.addAttribute("list", list);
		return "ProductsResult";
	}
	
	@GetMapping("/productViewById")
	public String viewOneProductById(
			@RequestParam Long prodId,
			@RequestParam(required = false) String message,
			HttpSession session,
			Principal principal,
			Model model
			)
	{
		Product pob = productService.getOneProduct(prodId);
		model.addAttribute("pob", pob);
		model.addAttribute("message", message);
		if(principal!=null) {
			Customer cust =(Customer) session.getAttribute("customer");
			model.addAttribute("isRated", productRatingService.isCustomerRatedProduct(cust.getId(), prodId));
		}
		
		model.addAttribute("ratingList",productRatingService.getAllProductRatings(prodId));
		return "ProductViewPage";
	}
}

