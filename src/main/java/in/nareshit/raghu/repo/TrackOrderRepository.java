package in.nareshit.raghu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.raghu.entity.TrackOrder;

public interface TrackOrderRepository extends JpaRepository<TrackOrder, Long>{

	@Query("SELECT trc FROM TrackOrder trc JOIN trc.order as order WHERE order.id=:orderId")
	List<TrackOrder> findAllTrackOrderByOrderId(Long orderId);
}
