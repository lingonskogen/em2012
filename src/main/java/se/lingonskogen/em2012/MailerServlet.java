package se.lingonskogen.em2012;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import se.lingonskogen.em2012.domain.Coupon;
import se.lingonskogen.em2012.domain.CouponDao;
import se.lingonskogen.em2012.domain.Game;
import se.lingonskogen.em2012.domain.GameDao;
import se.lingonskogen.em2012.domain.Group;
import se.lingonskogen.em2012.domain.GroupDao;
import se.lingonskogen.em2012.domain.Prediction;
import se.lingonskogen.em2012.domain.PredictionDao;
import se.lingonskogen.em2012.domain.Team;
import se.lingonskogen.em2012.domain.TeamDao;
import se.lingonskogen.em2012.domain.Tournament;
import se.lingonskogen.em2012.domain.TournamentDao;
import se.lingonskogen.em2012.domain.User;
import se.lingonskogen.em2012.domain.UserDao;
import se.lingonskogen.em2012.services.PredictionService;
import se.lingonskogen.em2012.services.impl.MailService;
import se.lingonskogen.em2012.services.impl.MailService.ContentWriter;
import se.lingonskogen.em2012.services.impl.MailService.Template;

public class MailerServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(MailerServlet.class.getName());
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException
    {
        LOG.info("mailing predictions...");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/plain");
        final StringWriter writer = new StringWriter();
        
        TournamentDao tournamentDao = new TournamentDao();
        TeamDao teamDao = new TeamDao();
        GameDao gameDao = new GameDao();
        
        // set up maps
        List<Tournament> tournaments = tournamentDao.findAll();
        Map<String, Map<String, Team>> teamMap = new HashMap<String, Map<String, Team>>();
        Map<String, Map<String, Game>> gameMap = new HashMap<String, Map<String, Game>>();
        for (Tournament tournament : tournaments)
        {
            teamMap.put(tournament.getId(), new HashMap<String, Team>());
            List<Team> teams = teamDao.findAll(tournament.getId());
            for (Team team : teams)
            {
                teamMap.get(tournament.getId()).put(team.getId(), team);
            }
            gameMap.put(tournament.getId(), new HashMap<String, Game>());
            List<Game> games = gameDao.findAll(tournament.getId());
            for (Game game : games)
            {
                gameMap.get(tournament.getId()).put(game.getId(), game);
            }
        }
        
        GroupDao groupDao = new GroupDao();
        UserDao userDao = new UserDao();
        CouponDao couponDao = new CouponDao();
        PredictionDao predictionDao = new PredictionDao();
        
        List<Group> groups = groupDao.findAll();
        for (Group group : groups)
        {
            List<User> users = userDao.findAll(group.getId());
            for (User user : users)
            {
                List<Coupon> coupons = couponDao.findAll(group.getId(), user.getId());
                for (Coupon coupon : coupons)
                {
                    writer.write(String.format("[%s] %s (%s) - %s\r\n", group.getName(), user.getRealName(), user.getUserName(), coupon.getTournamentId()));
                    writer.write("\r\n");
                    List<Prediction> predictions = predictionDao.findAll(group.getId(), user.getId(), coupon.getId());
                    Map<Date, String> map = new HashMap<Date, String>();
                    for (Prediction prediction : predictions)
                    {
                        Game game = gameMap.get(prediction.getTournamentId()).get(prediction.getGameId());
                        Team home = teamMap.get(prediction.getTournamentId()).get(game.getHomeTeamId());
                        Team away = teamMap.get(prediction.getTournamentId()).get(game.getAwayTeamId());
                        Long homeScore = prediction.getHomeScore();
                        Long awayScore = prediction.getAwayScore();
                        String homeScoreStr = homeScore == null ? " " : homeScore.toString();
                        String awayScoreStr = awayScore == null ? " " : awayScore.toString();
                        map.put(game.getKickoff(), String.format("%s - %s : %s - %s\r\n", home.getCode(), away.getCode(), homeScoreStr, awayScoreStr));
                    }
                    ArrayList<Date> dates = new ArrayList<Date>(map.keySet());
                    Collections.sort(dates);
                    for (Date date : dates)
                    {
                        writer.write(map.get(date));
                    }
                    String winnerTeamId = coupon.getWinnerTeamId();
                    if (winnerTeamId != null)
                    {
                        Team winner = teamMap.get(coupon.getTournamentId()).get(winnerTeamId);
                        writer.write(String.format("Vinnare   : %s", winner.getName()));
                    }
                    else
                    {
                        writer.write(String.format("Champion  : <missing>"));
                    }
                    writer.write("\r\n");
                    writer.write("\r\n");
                }
            }
        }
        
        boolean show = req.getParameter("show") != null;
        if (show)
        {
            resp.getOutputStream().print(writer.toString());
        }
        else
        {
            MailService mailService = new MailService();
            try
            {
                mailService.sendMail(null, Template.UPD_COUPON, false,
                        new ContentWriter()
                        {
                            @Override
                            public String write() throws Exception
                            {
                                return writer.toString();
                            }
                        });
            }
            catch (Exception e)
            {
                LOG.log(Level.WARNING, "Unable to mail predictions", e);
            }
        }
    }

}
