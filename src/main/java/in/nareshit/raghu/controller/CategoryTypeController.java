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

import in.nareshit.raghu.entity.CategoryType;
import in.nareshit.raghu.exception.CategoryTypeNotFoundException;
import in.nareshit.raghu.service.ICategoryTypeService;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
@Controller
@RequestMapping("/categorytype")
public class CategoryTypeController {
	@Autowired
	private ICategoryTypeService service;

	@GetMapping("/register")
	public String registerCategoryType(Model model) {
		return "CategoryTypeRegister";
	}

	@PostMapping("/save")
	public String saveCategoryType(@ModelAttribute CategoryType categorytype, Model model) {
		java.lang.Long id=service.saveCategoryType(categorytype);
		model.addAttribute("message","CategoryType created with Id:"+id);
		model.addAttribute("categorytype",new CategoryType()) ;
		return "CategoryTypeRegister";
	}

	@GetMapping("/all")
	public String getAllCategoryTypes(Model model,
			@RequestParam(value = "message", required = false) String message) {
		List<CategoryType> list=service.getAllCategoryTypes();
		model.addAttribute("list",list);
		model.addAttribute("message",message);
		return "CategoryTypeData";
	}

	@GetMapping("/delete")
	public String deleteCategoryType(@RequestParam Long id, RedirectAttributes attributes) {
		try {
			service.deleteCategoryType(id);
			attributes.addAttribute("message","CategoryType deleted with Id:"+id);
		} catch(CategoryTypeNotFoundException e) {
			e.printStackTrace() ;
			attributes.addAttribute("message",e.getMessage());
		}
		return "redirect:all";
	}

	@GetMapping("/edit")
	public String editCategoryType(@RequestParam Long id, Model model, RedirectAttributes attributes) {
		String page=null;
		try {
			CategoryType ob=service.getOneCategoryType(id);
			model.addAttribute("categorytype",ob);
			page="CategoryTypeEdit";
		} catch(CategoryTypeNotFoundException e) {
			e.printStackTrace() ;
			attributes.addAttribute("message",e.getMessage());
			page="redirect:all";
		}
		return page;
	}

	@PostMapping("/update")
	public String updateCategoryType(@ModelAttribute CategoryType categorytype,
			RedirectAttributes attributes) {
		service.updateCategoryType(categorytype);
		attributes.addAttribute("message","CategoryType updated");
		return "redirect:all";
	}
}
