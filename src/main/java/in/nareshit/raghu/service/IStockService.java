package in.nareshit.raghu.service;

import java.util.List;

import in.nareshit.raghu.entity.Stock;

public interface IStockService {
	 
	Long createStock(Stock stock);
	void updateStock(Long id, Long count);
	Long getStockIdByProduct(Long productId);
	List<Stock> getStockDetails();

}
