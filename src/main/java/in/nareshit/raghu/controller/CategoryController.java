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

import in.nareshit.raghu.entity.Category;
import in.nareshit.raghu.exception.CategoryNotFoundException;
import in.nareshit.raghu.service.ICategoryService;
import in.nareshit.raghu.service.ICategoryTypeService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author:RAGHU SIR Generated F/w:SHWR-Framework
 */
@Controller
@RequestMapping("/category")
@Slf4j
public class CategoryController {
	
	//private static final Logger log = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private ICategoryService service;

	@Autowired
	private ICategoryTypeService categoryTypeService;
	
	private void commonUi(Model model) {
		model.addAttribute("categoryTypes", categoryTypeService.getCategoryTypeIdAndName());
	}
	
	@GetMapping("/register")
	public String registerCategory(Model model) {
		model.addAttribute("category", new Category());
		commonUi(model);
		return "CategoryRegister";
	}

	@PostMapping("/save")
	public String saveCategory(@ModelAttribute Category category, Model model) {
		java.lang.Long id = service.saveCategory(category);
		model.addAttribute("message", "Category created with Id:" + id);
		model.addAttribute("category", new Category());
		commonUi(model);
		return "CategoryRegister";
	}

	@GetMapping("/all")
	public String getAllCategorys(Model model, @RequestParam(value = "message", required = false) String message) {
		List<Category> list = service.getAllCategorys();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "CategoryData";
	}

	@GetMapping("/delete")
	public String deleteCategory(@RequestParam Long id, RedirectAttributes attributes) {
		log.info("ENTERED INTO DELETE METHOD");
		try {
			service.deleteCategory(id);
			attributes.addAttribute("message", "Category deleted with Id:" + id);
			log.debug("CATEGORY DELETED WITH ID: {}",id);
		} catch (CategoryNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			log.error("ERORR IS : {}", e.getMessage() );
		}
		log.info("ABOUT TO LEAVE DELETE METHOD");
		return "redirect:all";
	}

	@GetMapping("/edit")
	public String editCategory(@RequestParam Long id, Model model, RedirectAttributes attributes) {
		String page = null;
		try {
			Category ob = service.getOneCategory(id);
			model.addAttribute("category", ob);
			page = "CategoryEdit";
			commonUi(model);
		} catch (CategoryNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:all";
		}
		return page;
	}

	@PostMapping("/update")
	public String updateCategory(@ModelAttribute Category category, RedirectAttributes attributes) {
		service.updateCategory(category);
		attributes.addAttribute("message", "Category updated");
		return "redirect:all";
	}
}
