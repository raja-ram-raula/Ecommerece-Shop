package in.nareshit.raghu.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.nareshit.raghu.constants.UserRole;
import in.nareshit.raghu.entity.Customer;
import in.nareshit.raghu.entity.User;
import in.nareshit.raghu.service.ICartItemService;
import in.nareshit.raghu.service.ICustomerService;
import in.nareshit.raghu.service.IUserService;
import in.nareshit.raghu.util.AppUtil;
import in.nareshit.raghu.util.MyMailUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService service;

	@Autowired
	private MyMailUtil mailUtil;
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private ICartItemService cartItemService;

	@GetMapping("/register")
	public String showReg() {
		return "UserRegister";
	}

	@PostMapping("/save")
	public String saveUser(
			@ModelAttribute User user,
			Model model
			) 
	{
		Long id = service.saveUser(user);
		model.addAttribute("message", "User created with id "+id);
		return "UserRegister";
	}


	@GetMapping("/all")
	public String showAllUser(Model model) {
		model.addAttribute("list", service.getAllUsers());
		return "UserData";
	}

	@ResponseBody
	@GetMapping("/validateMail")
	public String validateEmail(
			@RequestParam("email")String email
			) 
	{
		String message ="";
		if(service.findByEmail(email).isPresent()) {
			message = email + " already exist";
		}
		return message;
	}

	@GetMapping("/profile")
	public String showProfile() {
		return "UserProfile";
	}

	@GetMapping("/setup")
	public String setupUser(
			Authentication authentication,
			HttpSession session
			) 
	{
		//fetch current user object
		User user = service.findByEmail(authentication.getName()).get();

		String displayName = user.getDisplayName();
		session.setAttribute("displayName", displayName);

		//store in HttpSession
		session.setAttribute("userOb", user);

		String page = null;

		@SuppressWarnings("unchecked")
		String role = ((List<GrantedAuthority>)authentication.getAuthorities()).get(0).getAuthority();

		if(role.equals(UserRole.ADMIN.name())) {
			page = "/user/all";
		} else if(role.equals(UserRole.EMPLOYEE.name())) {
			page = "/brand/all";
		} else if(role.equals(UserRole.SALES.name())) {
			page = "/product/all";
		} else  if(role.equals(UserRole.CUSTOMER.name())) {
			//setting customerId in HttpSession for onetime
			Customer cust=customerService.findByEmail(authentication.getName());
			session.setAttribute("customer",cust);
			session.setAttribute("cartItemsCount",cartItemService.getCartItemsCount(cust.getId()));
			page = "/search/showBrands";
		}
		return "redirect:"+page;
	}

	@GetMapping("/login")
	public String showLoginPage() {
		return "UserLogin";
	}

	@GetMapping("/showPwdUpdate")
	public String showPwdUpdate() {
		return "UserPwdUpdate";
	}

	@PostMapping("/pwdUpdate")
	public String updatePwd(
			@RequestParam String password,
			HttpSession session,
			Model model
			) 
	{
		//read current user from session
		User user = (User) session.getAttribute("userOb");
		//read userId
		Long userId = user.getId();

		//make service call
		service.updateUserPwd(password, userId);
		// TODO : EMAIL TASK
		model.addAttribute("message", "Password Updated!");
		return "UserPwdUpdate";
		//return "redirect:logout"
	}

	@GetMapping("/showForgot")
	public String showForgot() {
		return "UserNewPwdGen";
	}

	@PostMapping("/genNewPwd")
	public String genNewPwd(
			@RequestParam String email,
			Model model) 
	{
		Optional<User> opt =  service.findByEmail(email);
		if(opt.isPresent()) {
			//read user object
			User user = opt.get();

			//Generate new Password
			String pwd = AppUtil.genPwd();

			//encode and update in DB
			service.updateUserPwd(pwd, user.getId());

			//send message to UI
			model.addAttribute("message", "Password Updated! Check your Inbox!!");

			//send email to user
			if(user.getId()!=null)
				new Thread(new Runnable() {
					public void run() {
						String text = "YOUR USERNAME IS: " + user.getEmail() +", AND NEW PASSWORD IS "+ pwd;
						mailUtil.send(user.getEmail(), "PASWORD UPDATED!", text);
					}
				}).start();

		} else { // if user not present
			model.addAttribute("message", "User Not Found!");
		}
		return "UserNewPwdGen";
	}
}
