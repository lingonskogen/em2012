package se.lingonskogen.em2012.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import se.lingonskogen.em2012.domain.Coupon;
import se.lingonskogen.em2012.domain.DaoException;
import se.lingonskogen.em2012.form.admin.SearchForm;

@Controller
@RequestMapping("/admin/couponpage.html")
public class CouponController extends AbstractAdminController {
	
	private final static String COUPON_PAGE = "/admin/couponpage";
//	private final static String DELETE_BASE_LINK = "/admin/couponpage.html?action=delete";
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(@RequestParam(value = "action", defaultValue = "default") String action, 
						   @RequestParam(value = "couponId", defaultValue="") String couponId, 
						   @RequestParam(value = "userId", defaultValue="") String userId,
						   @RequestParam(value = "groupId", defaultValue="") String groupId, ModelMap model) {

		/*if(action.equals(DELETE_ACTION)) {
			try{
				deleteCoupon(groupId, userId, couponId);
			} catch(DaoException e) {
				model.addAttribute("errorMessage","Kunde inte ta bort kupungen");
			}
		}*/

		SearchForm searchForm = new SearchForm();
		setParameters(model, searchForm);
		return COUPON_PAGE;
	}

	// Process the search form.
	@RequestMapping(method = RequestMethod.POST)
	public String processForm(@ModelAttribute(value="searchForm") @Valid SearchForm searchForm, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			System.out.println("searchForm-error in CouponController");
		}
				
		setParameters(model, searchForm);
		return COUPON_PAGE;
	}
	
	private Map<String, CouponInfo> getCoupons(final String groupId) {
		Map<String, CouponInfo> t = new HashMap<String, CouponInfo>();
		
		List<Coupon> coupons = getCouponService().getCoupons(groupId);  
		
		for (Coupon coupon : coupons) {
			String tName = getTournamentService().getTournamentName(coupon.getTournamentId());
			String gName = getGroupService().getGroupName(coupon.getGroupId());
			String uName = getUserService().getUserName(coupon.getUserId());

			//String deleteLink = createDeleteLink(coupon.getId(), coupon.getUserId(), coupon.getGroupId());
			t.put(coupon.getId()+uName+gName, new CouponInfo(tName, gName, uName));
		}

		return t;
	}
	
	/*private String createDeleteLink(final String couponId, final String userId, final String groupId) {
		return DELETE_BASE_LINK + "&couponId=" + couponId + "&userId=" + userId + "&groupId=" + groupId;
	}*/
		
	private void setParameters(final ModelMap model, final SearchForm searchForm) {
		super.setParameters(model);
		
		String groupId = searchForm.getGroupId();
		if(groupId == null || groupId.equals("default")) {
			groupId = null;
		} else {
			groupId = searchForm.getGroupId();
		}
		Map<String, CouponInfo> availableCoupons = getCoupons(groupId);
		Map<String, String> availableGroups = getAvailableGroups();
		// Map<String, String> availableUsers = getAvailableUsers(searchForm.getGroupId());
		
		availableGroups.put("default", "Choose group");
		
		// command object
		model.addAttribute("tournamentId", getTournamentId());
		model.addAttribute("availableCoupons", availableCoupons);
		// model.addAttribute("availableUsers", availableUsers);
		model.addAttribute("availableGroups", availableGroups);
		model.addAttribute("searchForm", searchForm);
	}

	public class CouponInfo {
		private String tournamentName;
		private String groupName;
		private String userName;
		
		public CouponInfo(final String tName, final String gName, final String uName) {
			this.tournamentName = tName;
			this.groupName = gName;
			this.userName = uName;
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
	}

	public String getPageId() {
		return "coupon";
	}

}