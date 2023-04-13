package in.nareshit.raghu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.constants.UserRole;
import in.nareshit.raghu.entity.Address;
import in.nareshit.raghu.entity.Customer;
import in.nareshit.raghu.entity.User;
import in.nareshit.raghu.repo.CustomerRepository;
import in.nareshit.raghu.service.ICustomerService;
import in.nareshit.raghu.service.IUserService;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepository repo;
	
	@Autowired
	private IUserService userService;
	
	public Long saveCustomer(Customer customer) {
		Long id = repo.save(customer).getId();
		if(id!=null) {
			User user = new User();
			user.setDisplayName(customer.getName());
			user.setEmail(customer.getEmail());
			user.setContact(customer.getMobile());
			user.setRole(UserRole.CUSTOMER);
			user.setAddress(customer.getAddress().get(0).toString());
			userService.saveUser(user);
		}
		return id;
	}

	public List<Customer> getAllCustomers() {
		return repo.findAll();
	}

	@Override
	public Customer findByEmail(String email) {
		return repo.findByEmail(email).get();
	}
	@Override
	public List<Address> getCustomerAddress(Long id) {
		return repo.getCustomerAddress(id);
	}
}
