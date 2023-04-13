package in.nareshit.raghu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.raghu.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT id,name FROM Product ")
	//@Query("SELECT id,name FROM Product WHERE status='ACTIVE'")
	List<Object[]> getProductIdAndNames();
	
	@Query("SELECT p.id, p.imageLink, p.name,p.shortDesc,p.cost FROM  Product p INNER JOIN p.brand as brand WHERE brand.id=:brandId")
	List<Object[]> getProductsByBrands(Long brandId);
	
	@Query("SELECT p.id, p.imageLink, p.name,p.shortDesc,p.cost FROM  Product p INNER JOIN p.category as category WHERE category.id=:catId")
	List<Object[]> getProductsByCategory(Long catId);
	
	@Query("SELECT p.id, p.imageLink, p.name,p.shortDesc,p.cost FROM  Product p WHERE p.name LIKE CONCAT('%', :input, '%')")
	List<Object[]> getProductByNameMatching(String input);

}
