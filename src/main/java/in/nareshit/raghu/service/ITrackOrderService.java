package in.nareshit.raghu.service;

import java.util.List;

import in.nareshit.raghu.entity.TrackOrder;

public interface ITrackOrderService {

	void addTrackOrder(TrackOrder to);
	List<TrackOrder> getAllTrackOrdersByOrderid(Long orderId);
}
