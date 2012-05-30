package se.lingonskogen.em2012.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import se.lingonskogen.em2012.domain.Prediction;
import se.lingonskogen.em2012.domain.User;

@Controller
@RequestMapping("/deletepage.html")
public class DeleteDataController extends AbstractController {

	private final static String DELETE_PAGE = "deletepage";
	
	@RequestMapping(method = RequestMethod.GET)
	public String start(@RequestParam(value = "action", defaultValue = "") String action,
			@RequestParam(value = "groupid", defaultValue = "") String groupId,
			@RequestParam(value = "userid", defaultValue = "") String userId,
			final ModelMap model, final Principal principal) throws Exception {   
	    
        User user = null;
        if (principal != null) {
            String name = principal.getName();
            user = getUserService().getUser(name);
        }
        // Only susgl user is allowed to delete data
        if (!user.getId().equals("susgl-ateles-se")) {
    	    model.addAttribute("statusMessage", "You are not allowed to delete data");
        	return DELETE_PAGE;
        }
        
        if (action.equals("prediction")) {
        	User deleteUser = getUserService().getUser(groupId, userId);
        	
        	if(deleteUser == null) {
        	    model.addAttribute("statusMessage", "Entered user does not exist");
        		return DELETE_PAGE;
        	}
        	StringBuilder str = new StringBuilder();
        	str.append("Deleting predictions for :" + userId + " in group " + groupId);
    	    // Delete predictions
            List<Prediction> predictions = getPredictionService().getPredictions(groupId, userId);
    	    int counter = 0;
            for( Prediction pred : predictions) {
    	    	counter++;
    	    	getPredictionService().deletePrediction(pred);
    	    }
            str.append("Deleted: " + counter + " predictions");
    	    model.addAttribute("statusMessage", str.toString());
        }
        

		return DELETE_PAGE;
	}

	@Override
	public String getCurrentPageId() {
		return DELETE_PAGE;
	}
		
				
}
