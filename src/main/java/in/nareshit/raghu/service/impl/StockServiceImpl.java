package in.nareshit.raghu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nareshit.raghu.entity.Stock;
import in.nareshit.raghu.repo.StockRepository;
import in.nareshit.raghu.service.IStockService;

@Service
public class StockServiceImpl implements IStockService {

	@Autowired
	private StockRepository repo;
	
	public Long createStock(Stock stock) {
		return repo.save(stock).getId();
	}

	@Transactional
	public void updateStock(Long id, Long count) {
		repo.updateStock(id, count);
	}

	public List<Stock> getStockDetails() {
		return repo.findAll();
	}
	
	public Long getStockIdByProduct(Long productId) {
		return repo.getStockIdByProduct(productId);
	}

}
