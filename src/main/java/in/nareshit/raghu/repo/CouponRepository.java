package in.nareshit.raghu.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.raghu.entity.Coupon;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
public interface CouponRepository extends JpaRepository<Coupon, Long> {
	
	Optional<Coupon> findByCode(String code);
}
