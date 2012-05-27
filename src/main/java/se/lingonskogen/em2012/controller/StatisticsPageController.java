package se.lingonskogen.em2012.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.lingonskogen.em2012.domain.Coupon;
import se.lingonskogen.em2012.domain.DaoException;
import se.lingonskogen.em2012.domain.Game;
import se.lingonskogen.em2012.domain.Prediction;
import se.lingonskogen.em2012.domain.Team;
import se.lingonskogen.em2012.domain.Tournament;
import se.lingonskogen.em2012.domain.User;
import se.lingonskogen.em2012.form.StatisticsFormData;
import se.lingonskogen.em2012.form.StatisticsFormData.TournamentFormData;
import se.lingonskogen.em2012.form.StatisticsFormData.TournamentFormData.GameFormData;
import se.lingonskogen.em2012.form.StatisticsFormData.TournamentFormData.UserScoreData;
import se.lingonskogen.em2012.form.StatisticsFormData.TournamentFormData.UserScoreData.PredictionScoreFormData;

@Controller
@RequestMapping("/statistics.html")
public class StatisticsPageController extends AbstractController {

	private static final String PAGE_NAME = "statpage";

	@RequestMapping(method = RequestMethod.GET)
	public String showPage(final ModelMap model, final Principal principal) {
		User user = null;
		if (principal != null) {
			String name = principal.getName();
			user = getUserService().getUser(name);
		}

		if (!hasCoupon(user))
		{
	        setParameters(model, principal);
	        model.addAttribute("noCoupon", true);
		    return PAGE_NAME;
		}
		if (!isRegistrationOpen()) {
			StatisticsFormData data = new StatisticsFormData();
			List<User> users = getUserService().getUsers(user.getGroupId());
			List<Coupon> coupons = getCouponService().getCoupons(
					user.getGroupId(), user.getId());
			for (Coupon coupon : coupons) {
				Tournament tournament = getTournamentService().getTournament(
						coupon.getTournamentId());
				TournamentFormData tData = new TournamentFormData(tournament);
				data.addTournamentFormDataList(tData);
				Map<String, List<Prediction>> map = new HashMap<String, List<Prediction>>();
				for (User u : users) {
					try {
						getCouponService().getCoupon(u.getGroupId(), u.getId(),
								coupon.getId());
						List<Prediction> predictions = getPredictionService()
								.getPredictions(u.getGroupId(), u.getId(),
										coupon.getId());
						map.put(u.getId(), predictions);
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				List<Game> games = getGameService().getSortedAvailableGames(
						tournament.getId());

				Map<String, Integer> scores = new HashMap<String, Integer>();
				for (Game game : games) {
					for (String userid : map.keySet()) {
						for (Prediction prediction : map.get(userid)) {
							if (game.getId().equals(prediction.getGameId())) {
								Integer score = calcScore(game, prediction);
								if (!scores.containsKey(userid)) {
									scores.put(userid, 0);
								}
								scores.put(userid, score + scores.get(userid));
							}
						}
					}
					GameFormData gData = new GameFormData(game);
					Team home = getTeamService().getTeam(
							game.getTournamentId(), game.getHomeTeamId());
					Team away = getTeamService().getTeam(
							game.getTournamentId(), game.getAwayTeamId());
					gData.setHomeTeam(home);
					gData.setAwayTeam(away);
					gData.setKickoff(game.getKickoff());
					tData.addGameFormData(gData);
				}
				
				// If we have a tournament winner - add points for correct winner
				if (tournament.getWinnerTeamId() != null && !tournament.getWinnerTeamId().equals("")) {
					for (String userid : scores.keySet()) {					
						Coupon userCoupon = getCouponService().getCoupon(userid);
						
						if(userCoupon.getWinnerTeamId().equals(tournament.getWinnerTeamId())) {
							scores.put(userid, 5 + scores.get(userid));	
						}
					}				
				}
				
				for (String userid : scores.keySet()) {
					User u = getUserService()
							.getUser(user.getGroupId(), userid);
					UserScoreData userScoreData = new UserScoreData(u);
					userScoreData.setScore(scores.get(userid));
					tData.addUserScoreData(userScoreData);
					List<Prediction> predictions = map.get(userid);
					for (Game game : games) {
						for (Prediction prediction : predictions) {
							if (game.getId().equals(prediction.getGameId())) {
								PredictionScoreFormData predictionScore = new PredictionScoreFormData();
								predictionScore.setPrediction(prediction);
								predictionScore.setScore(calcScore(game,
										prediction));
								userScoreData.getPredictionList().add(
										predictionScore);
							}
						}
					}
				}
			}
			model.addAttribute("data", data.getTournamentFormDataList().get(0));
		}
				
		setParameters(model, principal);
		return PAGE_NAME;
	}

	@Override
	public String getCurrentPageId() {
		return "statistics";
	}
}