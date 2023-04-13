package in.nareshit.raghu.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.raghu.entity.Address;
import in.nareshit.raghu.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByEmail(String email);
	@Query("SELECT c.address FROM Customer c WHERE c.id=:id")
	List<Address> getCustomerAddress(Long id);
}
