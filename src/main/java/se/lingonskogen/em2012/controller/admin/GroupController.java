package se.lingonskogen.em2012.controller.admin;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.lingonskogen.em2012.domain.Group;
import se.lingonskogen.em2012.form.admin.GroupForm;

@Controller
@RequestMapping("/admin/grouppage.html")
public class GroupController extends AbstractAdminController {

    final private static String GROUP_PAGE = "grouppage";

	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		GroupForm groupForm = new GroupForm();
		setParameters(model, groupForm);
		
		// return form view
		return GROUP_PAGE;
	}

	private Map<String,String> getGroups() {
		Map<String, String> groups = new LinkedHashMap<String, String>();
		
		for (Group group : getGroupService().getAvailableGroups()) {
			groups.put(group.getName(), group.getId());
		}

		return groups;
	}
	
	private void setParameters(final ModelMap model, final GroupForm groupForm) {		
		Map<String, String> availableGroups = getGroups();
		
		// command object
		model.addAttribute("groupForm", groupForm);
		model.addAttribute("availableGroups", availableGroups);
	}
	
	// Process the form.
	@RequestMapping(method = RequestMethod.POST)
	public String processForm(@ModelAttribute(value="groupForm") @Valid GroupForm groupForm, BindingResult result, ModelMap model) {
		// set custom Validation by user
		//validator.validate(register, result);
		if (result.hasErrors()) {			
			setParameters(model, groupForm);
			
			return GROUP_PAGE;
		}
		
		// Register new Group
		Group group = getGroupService().newInstance(groupForm.getGroupName());
		
		try{
			getGroupService().createGroup(group);
		} catch (Exception e) {
			
			// TODO: Get error message from message file
			String errorMessage = "Det gick inte att skapa en Group";
			
			// Error when creating group
			setParameters(model, groupForm);
			model.addAttribute("errorMessage", errorMessage);
			
			return GROUP_PAGE;
		}
	
		setParameters(model, groupForm);
		// TODO: Get message from message file
		model.addAttribute("successMessage","Gruppen blev skapad");
		return GROUP_PAGE;
	}
}