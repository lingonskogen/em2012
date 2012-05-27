package se.lingonskogen.em2012.services;

import java.util.List;

import se.lingonskogen.em2012.domain.Coupon;
import se.lingonskogen.em2012.domain.DaoException;

public interface CouponService {
	String createCoupon(final Coupon coupon) throws DaoException;
    void updateCoupon(Coupon coupon) throws DaoException;
	Coupon newInstance(final String tournamentId, final String userId, final String groupId, final String winnerTeamId);	
	void deleteCoupon(final String couponId, final String userId, final String groupId) throws DaoException;
	void deleteCoupon(final Coupon coupon) throws DaoException;
	Coupon getCoupon(final String groupId, final String userId, final String couponId) throws DaoException;
	// User id is now globally unique
	Coupon getCoupon(final String userId);
	
	List<Coupon> getCoupons(final String groupId, final String userId);	
	List<Coupon> getCoupons(final String groupId);
	List<Coupon> getCoupons();
}
