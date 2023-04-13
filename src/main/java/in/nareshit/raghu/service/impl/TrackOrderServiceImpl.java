package in.nareshit.raghu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.entity.TrackOrder;
import in.nareshit.raghu.repo.TrackOrderRepository;
import in.nareshit.raghu.service.ITrackOrderService;

@Service
public class TrackOrderServiceImpl implements ITrackOrderService {

	@Autowired
	private TrackOrderRepository repo;
	
	@Override
	public void addTrackOrder(TrackOrder to) {
		repo.save(to);
	}

	@Override
	public List<TrackOrder> getAllTrackOrdersByOrderid(Long orderId) {
		return repo.findAllTrackOrderByOrderId(orderId);
	}

}
