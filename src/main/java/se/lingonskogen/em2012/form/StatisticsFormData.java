package se.lingonskogen.em2012.form;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import se.lingonskogen.em2012.domain.Game;
import se.lingonskogen.em2012.domain.Prediction;
import se.lingonskogen.em2012.domain.Team;
import se.lingonskogen.em2012.domain.Tournament;
import se.lingonskogen.em2012.domain.User;

public class StatisticsFormData {
    
    private final List<TournamentFormData> tournamentFormDataList = new ArrayList<TournamentFormData>();
    
    public void addTournamentFormDataList(TournamentFormData data) {
        tournamentFormDataList.add(data);
    }
    
    public List<TournamentFormData> getTournamentFormDataList()
    {
        return tournamentFormDataList;
    }
    
    public static class TournamentFormData {

        private final Tournament tournament;

        private final List<GameFormData> gameFormDataList = new ArrayList<GameFormData>();
        
        private final List<UserScoreData> userScoreDataList = new ArrayList<UserScoreData>();
        
        public TournamentFormData(Tournament tournament)
        {
            this.tournament = tournament;
        }

        public Tournament getTournament()
        {
            return tournament;
        }

        public List<GameFormData> getGameFormDataList()
        {
            return gameFormDataList;
        }

        public List<GameFormData> getGameFormDataList1()
        {
            return gameFormDataList.subList(0, 8);
        }
        
        public List<GameFormData> getGameFormDataList2()
        {
            return gameFormDataList.subList(8, 16);
        }
        public List<GameFormData> getGameFormDataList3()
        {
            return gameFormDataList.subList(16, gameFormDataList.size());
        }

        public void addGameFormData(GameFormData data)
        {
            gameFormDataList.add(data);
        }
        
        public List<UserScoreData> getUserScoreDataList()
        {
            Collections.<UserScoreData>sort(userScoreDataList, new Comparator<UserScoreData>(){
                @Override
                public int compare(UserScoreData o1, UserScoreData o2)
                {
                    return -(o1.getScore().compareTo(o2.getScore()));
                }
            });
            return userScoreDataList;
        }

        public void addUserScoreData(UserScoreData data)
        {
            userScoreDataList.add(data);
        }
        
        public static class UserScoreData {

            private final User user;
            private Integer score;
            private final List<PredictionScoreFormData> predictionList = new ArrayList<PredictionScoreFormData>();
            
            public UserScoreData(User user)
            {
                this.user = user;
            }

            public User getUser()
            {
                return user;
            }

            public void setScore(Integer score)
            {
                this.score = score;
            }
            public Integer getScore() {
                return score;
            }

            public List<PredictionScoreFormData> getPredictionList()
            {
                return predictionList;
            }
            public List<PredictionScoreFormData> getPredictionList1()
            {
                if (predictionList.size() < 8)
                {
                    return Collections.emptyList();
                }
                return predictionList.subList(0, 8);
            }
            public List<PredictionScoreFormData> getPredictionList2()
            {
                if (predictionList.size() < 16)
                {
                    return Collections.emptyList();
                }
                return predictionList.subList(8, 16);
            }
            public List<PredictionScoreFormData> getPredictionList3()
            {
                if (predictionList.size() < 24)
                {
                    return Collections.emptyList();
                }
                return predictionList.subList(16, 24);
            }
            
            public static class PredictionScoreFormData {
                private Prediction prediction;
                private Integer score;
                public void setPrediction(Prediction prediction)
                {
                    this.prediction = prediction;
                }
                public Prediction getPrediction()
                {
                    return prediction;
                }
                public void setScore(Integer score)
                {
                    this.score = score;
                }
                public Integer getScore()
                {
                    return score;
                }
                
            }
        }
        
        public static class GameFormData {

            private final Game game;
            private Team homeTeam;
            private Team awayTeam;
            private Date kickoff;
            public GameFormData(Game game)
            {
                this.game = game;
            }

            public Game getGame()
            {
                return game;
            }

            public void setKickoff(Date kickoff)
            {
                this.kickoff = kickoff;
            }
            
            public String getKickoff()
            {
                SimpleDateFormat format = new SimpleDateFormat("d/M HH:mm");
                return format.format(kickoff);
            }
            
            public void setHomeTeam(Team homeTeam)
            {
                this.homeTeam = homeTeam;
            }

            public Team getHomeTeam()
            {
                return homeTeam;
            }

            public void setAwayTeam(Team awayTeam)
            {
                this.awayTeam = awayTeam;
            }

            public Team getAwayTeam()
            {
                return awayTeam;
            }
        }
    } 
}
