package se.lingonskogen.em2012.controller;

import java.io.StringWriter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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
import se.lingonskogen.em2012.domain.Game;
import se.lingonskogen.em2012.domain.Prediction;
import se.lingonskogen.em2012.domain.User;
import se.lingonskogen.em2012.form.CouponForm;
import se.lingonskogen.em2012.form.PredictionFormData;
import se.lingonskogen.em2012.form.admin.GameForm;
import se.lingonskogen.em2012.services.PredictionService;
import se.lingonskogen.em2012.services.impl.MailService;
import se.lingonskogen.em2012.services.impl.MailService.ContentWriter;
import se.lingonskogen.em2012.services.impl.MailService.Template;

@Controller
@RequestMapping("/mypage.html")
public class UserPageController extends AbstractController {

	private static final String PAGE_NAME = "mypage";
	private static final String DEFAULT_WINNER_TEAM_ID = "sweden";

	@RequestMapping(method = RequestMethod.GET)
	public String showPage(
			@RequestParam(value = "action", defaultValue = "") String action,
			final ModelMap model, final Principal principal) {

		User user = null;
		if (principal != null) {
			String name = principal.getName();
			user = getUserService().getUser(name);
		}
		
		
		if (action != null && !action.equals("")) {
			if (action.equals("createcoupon")) {
				// Create coupon for user
				createDefaultCoupon(user);
			}
		}

		setParameters(model, principal, user);
		setupCoupon(user, model);
		
		return PAGE_NAME;
	}

	
	private void setParameters(final ModelMap model, final Principal principal, final User user) {
		String groupName = getGroupService().getGroupName(user.getGroupId());
		
		model.addAttribute("user", user);
		model.addAttribute("groupName", groupName);
		model.addAttribute("position", getPosition(user));
		model.addAttribute("totalUsers", getTotalNumUsers(user.getGroupId()));
		model.addAttribute("hasCoupon", hasCoupon(user));
		model.addAttribute("couponUrl", getCouponUrl());
		
		super.setParameters(model, principal);
	}
	
	// Process the  post request
	@RequestMapping(method = RequestMethod.POST)
	public String processForm(@ModelAttribute(value = "form") @Valid CouponForm form,
			BindingResult result, ModelMap model, Principal principal) {
		User user = null;
		if (principal != null) {
			String name = principal.getName();
			user = getUserService().getUser(name);
		}
		
		if (result.hasErrors()) {
			return PAGE_NAME;
		}

		setParameters(model, principal, user);
		String winnerTemId = form.getWinnerTeamId();
		// TODO: Get message from message file
		//model.addAttribute("successMessage", "Game blev skapad");
		model.addAttribute("form", form);
		model.addAttribute("submitAction", "Uppdatera");
		
		return PAGE_NAME;
	}

	private void createDefaultCoupon(final User user) {

		// Check that user does not have a coupon
		Coupon coupon = getCouponService().getCoupon(user.getId());
		if (coupon == null) {
			String groupId = user.getGroupId();
			String userId = user.getId();
			String tournamentId = getTournamentService()
					.getAvailableTournaments().get(0).getId();
			coupon = getCouponService().newInstance(tournamentId, userId,
					groupId, DEFAULT_WINNER_TEAM_ID);

			try {
				String couponId = getCouponService().createCoupon(coupon);
				if (couponId == null)
					throw new Exception("Error when creating coupon");

				// Create 0 - 0 predictions for user and all games
				List<Game> games = getGameService().getAvailableGames();
				for (Game game : games) {
					Prediction prediction = getPredictionService().newInstance(
							groupId, userId, couponId, tournamentId,
							game.getId(), Long.valueOf(0), Long.valueOf(0));

					getPredictionService().createPrediction(prediction);
				}
				mailCoupon(user);

			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setupCoupon(final User user, final ModelMap model) {
		String action = "";

		CouponForm form = new CouponForm();
		form.setTeams(getTeamService().getAvailableTeams());
		
		// Get user coupon - if any
		Coupon coupon = getCouponService().getCoupon(user.getId());
		List<PredictionFormData> predictions = new ArrayList<PredictionFormData>();
		
		String tournamentId = getTournamentService().getAvailableTournaments()
				.get(0).getId();

		if (coupon != null) {
			form.setWinnerTeamId(coupon.getWinnerTeamId());

			String c = coupon.getId();
			List<Prediction> preds = getPredictionService().getPredictions(
					user.getGroupId(), user.getId(), c);
			predictions = getFormData(tournamentId, preds);
			// Get text from messages
			action = "Uppdatera";
		} else {
			// coupon =
			// getCouponService().newInstance(getTournamentService().getAvailableTournaments().get(0).getId(),
			// user.getId(), user.getGroupId());

			List<Game> games = getGameService().getSortedAvailableGames(
					tournamentId);

			for (Game game : games) {
				String homeTeamName = getTeamService().getTeamName(
						tournamentId, game.getHomeTeamId());
				String awayTeamName = getTeamService().getTeamName(
						tournamentId, game.getAwayTeamId());
				Date kickoff = game.getKickoff();
				PredictionFormData predictionFormData = new PredictionFormData(
						game.getId(), kickoff, homeTeamName, awayTeamName,
						null, null);
				predictions.add(predictionFormData);
			}

			// TODO: Get text from messages
			action = "Skapa";
		}

		form.setPredictions(predictions);
		model.addAttribute("form", form);
		model.addAttribute("submitAction", action);
	}

	private List<PredictionFormData> getFormData(final String tournamentId,
			final List<Prediction> predictions) {
		List<PredictionFormData> data = new ArrayList<PredictionFormData>();

		for (Prediction prediction : predictions) {
			Game game;
			try {
				game = getGameService().getGame(tournamentId,
						prediction.getGameId());
			} catch (DaoException e) {
				continue;
			}

			String gameId = prediction.getGameId();
			String homeTeamName = getTeamService().getTeamName(tournamentId,
					game.getHomeTeamId());
			String awayTeamName = getTeamService().getTeamName(tournamentId,
					game.getAwayTeamId());

			if (gameId == null || homeTeamName == null || awayTeamName == null) {
				continue;
			}

			PredictionFormData d = new PredictionFormData(gameId,
					game.getKickoff(), homeTeamName, awayTeamName,
					prediction.getHomeScore(), prediction.getAwayScore());

			data.add(d);
		}
		Collections.<PredictionFormData> sort(data,
				new Comparator<PredictionFormData>() {
					@Override
					public int compare(PredictionFormData game0,
							PredictionFormData game1) {
						return game0.getKickoffDate().compareTo(
								game1.getKickoffDate());
					}
				});
		return data;
	}

	private void mailCoupon(final User user) throws Exception {
		MailService mailService = new MailService();
		mailService.sendMail(user, Template.UPD_COUPON, false,
				new ContentWriter() {
					@Override
					public String write() throws Exception {
						StringWriter writer = new StringWriter();
						Coupon coupon = getCouponService().getCoupon(
								user.getId());
						writer.write("Tournament: ");
						writer.write(getTournamentService().getTournamentName(
								coupon.getTournamentId()));
						writer.write("\r\n");
						writer.write("-----------\r\n");
						PredictionService ps = getPredictionService();
						List<Prediction> predictions = ps.getPredictions(
								coupon.getGroupId(), coupon.getUserId(),
								coupon.getId());
						for (Prediction prediction : predictions) {
							Game game = getGameService().getGame(
									coupon.getTournamentId(),
									prediction.getGameId());
							String homeTeamName = getTeamService().getTeamName(
									coupon.getTournamentId(),
									game.getHomeTeamId());
							String awayTeamName = getTeamService().getTeamName(
									coupon.getTournamentId(),
									game.getAwayTeamId());
							writer.write(homeTeamName);
							writer.write(" - ");
							writer.write(awayTeamName);
							writer.write(": ");
							writer.write(prediction.getHomeScore().toString());
							writer.write(" - ");
							writer.write(prediction.getAwayScore().toString());
							writer.write("\r\n");
						}
						writer.write("-----------\r\n");
						return writer.toString();
					}
				});
	}

	private String getCouponUrl() {
		String url = "mypage.html?action=createcoupon";

		return url;
	}

	@Override
	public String getCurrentPageId() {
		return PAGE_NAME;
	}
}