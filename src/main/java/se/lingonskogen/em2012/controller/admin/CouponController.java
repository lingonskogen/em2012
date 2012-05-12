package se.lingonskogen.em2012.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import se.lingonskogen.em2012.domain.Coupon;
import se.lingonskogen.em2012.domain.DaoException;
import se.lingonskogen.em2012.domain.Prediction;

@Controller
@RequestMapping("/admin/couponpage.html")
public class CouponController extends AbstractController {
	
	private final static String COUPON_PAGE = "couponpage";
	private final static String DELETE_BASE_LINK = "/admin/couponpage.html?action=delete";
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(@RequestParam(value = "action", defaultValue = "default") String action, 
						   @RequestParam(value = "couponId", defaultValue="") String couponId, 
						   @RequestParam(value = "userId", defaultValue="") String userId,
						   @RequestParam(value = "groupId", defaultValue="") String groupId, ModelMap model) {

		if(action.equals(DELETE_ACTION)) {
			try{
				// First delete all predictions
				List<Prediction> predictions = getPredictionService().findPredictions(groupId, userId, couponId);
				for(Prediction prediction : predictions) {
					getPredictionService().deletePrediction(prediction);
				}
				
				// Then delete the coupon
				getCouponService().deleteCoupon(couponId, userId, groupId);
				
			} catch(DaoException e) {
				model.addAttribute("errorMessage","Kunde inte ta bort kupungen");
			}
		}

		model.addAttribute("successMessage","Inne i delete");
		setParameters(model);
		return COUPON_PAGE;
	}

	private Map<String, CouponInfo> getCoupons() {
		Map<String, CouponInfo> t = new HashMap<String, CouponInfo>();
		
		for (Coupon coupon : getCouponService().getAvailableCoupons()) {
			String tName = getTournamentService().getTournamentName(coupon.getTournamentId());
			String gName = getGroupService().getGroupName(coupon.getGroupId());
			String uName = getUserService().getUserName(coupon.getUserId());

			String deleteLink = createDeleteLink(coupon.getId(), coupon.getUserId(), coupon.getGroupId());
			t.put(coupon.getId()+uName, new CouponInfo(tName, gName, uName, deleteLink));
		}

		return t;
	}
	
	private String createDeleteLink(final String couponId, final String userId, final String groupId) {
		return DELETE_BASE_LINK + "&couponId=" + couponId + "&userId=" + userId + "&groupId=" + groupId;
	}
	
	private void setParameters(final ModelMap model) {
		Map<String, CouponInfo> availableCoupons = getCoupons();
		
		// command object
		model.addAttribute("availableCoupons", availableCoupons);
	}

	public class CouponInfo {
		private String tournamentName;
		private String groupName;
		private String userName;
		private String deleteLink;
		
		public CouponInfo(final String tName, final String gName, final String uName, final String deleteLink) {
			this.tournamentName = tName;
			this.groupName = gName;
			this.userName = uName;
			this.deleteLink = deleteLink;
		}
		
		public String getTournamentName() {
			return tournamentName;
		}
		public void setTournamentName(String tournamentName) {
			this.tournamentName = tournamentName;
		}
		public String getGroupName() {
			return groupName;
		}
		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public void setDeleteLink(final String link) {
			this.deleteLink = link;
		}
		public String getDeleteLink() {
			return deleteLink;
		}
	}

}