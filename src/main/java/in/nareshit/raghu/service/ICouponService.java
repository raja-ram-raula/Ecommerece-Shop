package in.nareshit.raghu.service;

import in.nareshit.raghu.entity.Coupon;
import java.lang.Long;
import java.util.List;
import java.util.Optional;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
public interface ICouponService {
	Long saveCoupon(Coupon coupon);
	void updateCoupon(Coupon coupon);
	void deleteCoupon(Long id);
	Coupon getOneCoupon(Long id);
	List<Coupon> getAllCoupons();
	Optional<Coupon> findByCode(String code);
}
