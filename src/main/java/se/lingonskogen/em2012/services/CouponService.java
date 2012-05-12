package se.lingonskogen.em2012.services;

import java.util.List;

import se.lingonskogen.em2012.domain.Coupon;
import se.lingonskogen.em2012.domain.DaoException;

public interface CouponService {
	String createCoupon(final Coupon coupon) throws DaoException;
	Coupon newInstance(final String tournamentId, final String userId, final String groupId);
	List<Coupon> getAvailableCoupons();
	void deleteCoupon(final String couponId, final String userId, final String groupId) throws DaoException;
	void deleteCoupon(final Coupon coupon) throws DaoException;
	Coupon getCoupon(final String groupId, final String userId, final String couponId) throws DaoException;
}
