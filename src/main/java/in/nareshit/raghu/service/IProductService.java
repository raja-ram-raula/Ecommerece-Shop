package in.nareshit.raghu.service;

import java.util.List;
import java.util.Map;

import in.nareshit.raghu.entity.Product;

public interface IProductService {

	Long saveProduct(Product p);
	void updateProduct(Product p);
	void deleteProduct(Long id);
	Product getOneProduct(Long id);
	List<Product> getAllProducts();
	Map<Long,String> getProductIdAndName();
	
	List<Object[]> getProductsByBrands(Long brandId);
	List<Object[]> getProductsByCategory(Long catId);
	List<Object[]> getProductByNameMatching(String input);
}
