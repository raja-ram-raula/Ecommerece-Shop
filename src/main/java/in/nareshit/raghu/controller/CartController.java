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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nareshit.raghu.entity.CartItem;
import in.nareshit.raghu.entity.Customer;
import in.nareshit.raghu.service.ICartItemService;
import in.nareshit.raghu.service.IProductService;
import in.nareshit.raghu.util.UserUtil;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private ICartItemService service;
	@Autowired
	private IProductService productService;
	
	@Autowired
	private UserUtil util;
	
	@GetMapping("/add")
	public String addItemToCart(
			Principal p,
			HttpSession session,
			@RequestParam Long prodId,
			RedirectAttributes attributes
			) 
	{
		if(util.isUserLoggedIn(p)) {
			Customer cust =(Customer) session.getAttribute("customer");
			//fist check is product added to cart already or not
			CartItem cartItem = service.getOneCartItem(cust.getId(), prodId);
			if(cartItem==null) {
				//add
				CartItem newCartItem = new CartItem();
				newCartItem.setCustomer(cust);
				newCartItem.setProduct(productService.getOneProduct(prodId));
				newCartItem.setQty(1);
				service.addCartItem(newCartItem);
				attributes.addAttribute("message","Product Added to cart!");
				Integer count =(Integer)session.getAttribute("cartItemsCount");
				count = count + 1;
				session.setAttribute("cartItemsCount", count);
			} else {
				attributes.addAttribute("message","Product Quantity increased!");
				service.updateQty(cartItem.getId(), 1);
			}
		} else {
			attributes.addAttribute("message","User must login to user cart!");
		}
		attributes.addAttribute("prodId",prodId);
		return "redirect:/search/productViewById";
	}
	
	@GetMapping("/all")
	public String showCartItems(
			Principal p,
			HttpSession session,
			Model model)
	{
		Customer cust =(Customer) session.getAttribute("customer");
		List<CartItem> list = service.viewAllItems(cust.getId());
		model.addAttribute("list", list);
		return "CartItemsPage";
	}
	
	@GetMapping("/remove")
	public String removeCartItem(
			@RequestParam Long cartItemId,
			HttpSession session
			)
	{
		service.removeCartitem(cartItemId);
		Integer count =(Integer)session.getAttribute("cartItemsCount");
		count = count - 1;
		session.setAttribute("cartItemsCount", count);
		return "redirect:all";
	}
	
	@GetMapping("/increase")
	public String increaseCartItemQty(
			@RequestParam Long cartItemId
			)
	{
		service.updateQty(cartItemId, 1);
		return "redirect:/cart/all";
	}
	
	@GetMapping("/decrease")
	public String decreaseCartItemQty(
			@RequestParam Long cartItemId
			)
	{
		service.updateQty(cartItemId, -1);
		return "redirect:/cart/all";
	}
}
