package in.nareshit.raghu.service;

import java.util.List;

import in.nareshit.raghu.entity.Address;
import in.nareshit.raghu.entity.Customer;

public interface ICustomerService {

	Long saveCustomer(Customer user);
	List<Customer> getAllCustomers();
	Customer findByEmail(String email);
	List<Address> getCustomerAddress(Long id);
}
