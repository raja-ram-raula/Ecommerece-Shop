package in.nareshit.raghu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import in.nareshit.raghu.entity.Brand;
import in.nareshit.raghu.entity.Category;
import in.nareshit.raghu.entity.CategoryType;
import in.nareshit.raghu.service.IBrandService;
import in.nareshit.raghu.service.ICategoryService;
import in.nareshit.raghu.service.ICategoryTypeService;
import in.nareshit.raghu.view.BrandsExportExcelView;
import in.nareshit.raghu.view.CategoryTypesExportExcelView;
import in.nareshit.raghu.view.categoriesExportExcelView;

@Controller
@RequestMapping("/emp")
public class EmployeeReports {

	@Autowired
	private IBrandService brandService;
	@Autowired
	private ICategoryTypeService categoryTypeService;
	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping("/reports")
	public String showReports(Model model)
	{
		long brandsCount = brandService.totalBrands();
		long categoryTypesCount = categoryTypeService.totalCategoryTypes();
		long categoriesCount = categoryService.totalCategoris();
	
		model.addAttribute("brands",brandsCount);
		model.addAttribute("categoryTypes",categoryTypesCount);
		model.addAttribute("categories",categoriesCount);
		
		return "EmployeeReportPage";
	}
	
	@GetMapping("/exportBrands")
	public ModelAndView exportBrands() {
		ModelAndView m = new ModelAndView();
		m.setView(new BrandsExportExcelView());
		List<Brand> list = brandService.getAllBrands();
		m.addObject("list", list);
		
		return m;
	}
	@GetMapping("/exportCategoryTypes")
	public ModelAndView exportCategoryTypes() {
		ModelAndView m = new ModelAndView();
		m.setView(new CategoryTypesExportExcelView());
		List<CategoryType> list = categoryTypeService.getAllCategoryTypes();
		m.addObject("list", list);
		
		return m;
	}
	
	@GetMapping("/exportcategories")
	public ModelAndView exportcategories() {
		ModelAndView m = new ModelAndView();
		m.setView(new categoriesExportExcelView());
		List<Category> list = categoryService.getAllCategorys();
		m.addObject("list", list);
		
		return m;
	}
	
}
