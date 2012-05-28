package se.lingonskogen.em2012.controller;

import java.io.StringWriter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
import se.lingonskogen.em2012.services.PredictionService;
import se.lingonskogen.em2012.services.impl.MailService;
import se.lingonskogen.em2012.services.impl.MailService.ContentWriter;
import se.lingonskogen.em2012.services.impl.MailService.Template;

@Controller
@RequestMapping("/mypage.html")
public class UserPageController extends AbstractController
{

    private static final String PAGE_NAME = "mypage";
    private static final String DEFAULT_WINNER_TEAM_ID = "sweden";

    private Logger LOG = Logger.getLogger(UserPageController.class.getName());
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAutoGrowNestedPaths(false);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showPage(
            @RequestParam(value = "action", defaultValue = "") String action,
            final ModelMap model, final Principal principal) throws Exception {

        User user = null;
        if (principal != null) {
            String name = principal.getName();
            user = getUserService().getUser(name);
        }

        if (action != null && !action.equals("") && action.equals("createcoupon")) {
        	// Create coupon for user
            try {
            	createDefaultCoupon(user);
            } catch (Exception e) {
                LOG.log(Level.WARNING, "Error when " + user.getId() + " tried to create coupon", e);
            }
        }

        setupCoupon(user, model, null);
        setParameters(model, principal, user);

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
        model.addAttribute("userPoints", getUserPoints(user));

        super.setParameters(model, principal);
    }

    // Process the post request
    @RequestMapping(method = RequestMethod.POST)
    public String processForm(@ModelAttribute(value = "form") @Valid CouponForm form,
            BindingResult result, ModelMap model, Principal principal) throws DaoException {
System.out.println("Ska nu spara");
        User user = null;
        if (principal != null)  {
        	System.out.println("Principal INTE null");
        	String name = principal.getName();
            user = getUserService().getUser(name);
        }

        setParameters(model, principal, user);
        setupCoupon(user, model, form);
        model.addAttribute("submitAction", "Uppdatera");

        if (result.hasErrors())
        {
            int errorCount = result.getErrorCount();
            String msg = "Du angav felaktiga värden (" + errorCount + " st) så tipsraden sparades inte!";
            model.addAttribute("errorMessage", msg);

            return PAGE_NAME;
        }

        String groupId = user.getGroupId();
        String userId = user.getId();
        Coupon coupon = getCouponService().getCoupon(user.getId());
        String tournamentId = coupon.getTournamentId();
        String couponId = coupon.getId();
        System.out.println("Ska nu spara predictions");
        coupon.setWinnerTeamId(form.getWinnerTeamId());
        getCouponService().updateCoupon(coupon);
        for (PredictionFormData pfd : form.getPredictionMap().values()) {
            Prediction prediction = getPredictionService().newInstance(groupId, userId, couponId, tournamentId, pfd.getGameId(), pfd.getHomeScore(), pfd.getAwayScore());
            getPredictionService().updatePrediction(prediction);
        }
        
        setParameters(model, principal, user);
        return PAGE_NAME;
    }

    private void createDefaultCoupon(final User user) throws Exception {

        // Check that user does not have a coupon
        Coupon coupon = getCouponService().getCoupon(user.getId());
        if (coupon == null) {
            String groupId = user.getGroupId();
            String userId = user.getId();
            String tournamentId = getTournamentService()
                    .getAvailableTournaments().get(0).getId();
            coupon = getCouponService().newInstance(tournamentId, userId,
                    groupId, DEFAULT_WINNER_TEAM_ID);

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
        }
    }

    public void setupCoupon(final User user, final ModelMap model, CouponForm valueForm) throws DaoException
    {
        CouponForm form = new CouponForm();
        String tournamentId = getTournamentService().getAvailableTournaments().get(0).getId();
        List<Game> games = getGameService().getSortedAvailableGames(tournamentId);
        form = new CouponForm();
        form.setTeams(getTeamService().getAvailableTeams());

        for (int i = 0; i < games.size(); i++) {
            Game game = games.get(i);
            String homeTeamName = getTeamService().getTeamName(tournamentId, game.getHomeTeamId());
            String awayTeamName = getTeamService().getTeamName(tournamentId, game.getAwayTeamId());
            PredictionFormData formData = new PredictionFormData(game.getId(), game.getKickoff(), homeTeamName, awayTeamName, 0L, 0L);
            form.putPredictionFormData(game.getId(), formData);
        }
        
        form.setWinnerTeamId(DEFAULT_WINNER_TEAM_ID);
        model.addAttribute("form", form);

        // Get user coupon - if any
        Coupon coupon = getCouponService().getCoupon(user.getId());
        if (coupon != null) {
            form.setWinnerTeamId(coupon.getWinnerTeamId());

            String couponId = coupon.getId();

            List<Prediction> predictions = getPredictionService().getPredictions(user.getGroupId(),user.getId(), couponId);
            for (Prediction prediction : predictions) {
                form.getPrediction(prediction.getGameId()).setHomeScore(prediction.getHomeScore());
                form.getPrediction(prediction.getGameId()).setAwayScore(prediction.getAwayScore());
            }
        }
        
        if (valueForm != null)
        {
            for (String gameId : valueForm.getPredictionMap().keySet())
            {
                System.out.println(gameId + ": " + valueForm.getPrediction(gameId).getHomeScore() + " -" +
                		" " + valueForm.getPrediction(gameId).getAwayScore());
                if (valueForm.getPrediction(gameId).getHomeScore() != null)
                {
                    form.getPrediction(gameId).setHomeScore(valueForm.getPrediction(gameId).getHomeScore());
                }
                if (valueForm.getPrediction(gameId).getAwayScore() != null)
                {
                    form.getPrediction(gameId).setAwayScore(valueForm.getPrediction(gameId).getAwayScore());
                }
            }
            form.setWinnerTeamId(valueForm.getWinnerTeamId());
        }
        // Get text from messages
        String action = "Uppdatera";

        // form.setPredictions(predictions);
        model.addAttribute("form", form);
        model.addAttribute("submitAction", action);
    }

    private List<PredictionFormData> getFormData(final String tournamentId,
            final List<Prediction> predictions)
    {
        List<PredictionFormData> data = new ArrayList<PredictionFormData>();

        for (Prediction prediction : predictions)
        {
            Game game;
            try
            {
                game = getGameService().getGame(tournamentId,
                        prediction.getGameId());
            }
            catch (DaoException e)
            {
                continue;
            }

            String gameId = prediction.getGameId();
            String homeTeamName = getTeamService().getTeamName(tournamentId,
                    game.getHomeTeamId());
            String awayTeamName = getTeamService().getTeamName(tournamentId,
                    game.getAwayTeamId());

            if (gameId == null || homeTeamName == null || awayTeamName == null)
            {
                continue;
            }

            PredictionFormData d = new PredictionFormData(gameId,
                    game.getKickoff(), homeTeamName, awayTeamName,
                    prediction.getHomeScore(), prediction.getAwayScore());

            data.add(d);
        }
        Collections.<PredictionFormData> sort(data,
                new Comparator<PredictionFormData>()
                {
                    @Override
                    public int compare(PredictionFormData game0,
                            PredictionFormData game1)
                    {
                        return game0.getKickoffDate().compareTo(
                                game1.getKickoffDate());
                    }
                });
        return data;
    }

    private void mailCoupon(final User user) throws Exception
    {
        MailService mailService = new MailService();
        mailService.sendMail(user, Template.UPD_COUPON, false,
                new ContentWriter()
                {
                    @Override
                    public String write() throws Exception
                    {
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
                        for (Prediction prediction : predictions)
                        {
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

    private String getCouponUrl()
    {
        String url = "mypage.html?action=createcoupon";

        return url;
    }

    @Override
    public String getCurrentPageId()
    {
        return PAGE_NAME;
    }
}