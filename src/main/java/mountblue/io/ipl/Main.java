package mountblue.io.ipl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class Main {
    public static final int MATCH_ID = 0;
    public static final int INNING = 1;
    public static final int BATTING_TEAM = 2;
    public static final int BOWLING_TEAM = 3;
    public static final int OVER = 4;
    public static final int BALL = 5;
    public static final int BATSMAN = 6;
    public static final int NON_STRIKER = 7;
    public static final int BOWLER = 8;
    public static final int IS_SUPER_OVER = 9;
    public static final int WIDE_RUNS = 10;
    public static final int BYE_RUNS = 11;
    public static final int LEGBYE_RUNS = 12;
    public static final int NOBALL_RUNS = 13;
    public static final int PENALTY_RUNS = 14;
    public static final int BATSMAN_RUNS = 15;
    public static final int EXTRA_RUNS = 16;
    public static final int TOTAL_RUNS = 17;
    public static final int PLAYER_DISMISSED = 18;
    public static final int DISMISSAL_KIND = 19;
    public static final int FIELDER = 20;

    public static final int ID = 0;
    public static final int SEASON = 1;
    public static final int CITY = 2;
    public static final int DATE = 3;
    public static final int TEAM1 = 4;
    public static final int TEAM2 = 5;
    public static final int TOSS_WINNER = 6;
    public static final int TOSS_DECISION = 7;
    public static final int RESULT = 8;
    public static final int DL_APPLIED = 9;
    public static final int WINNER = 10;
    public static final int WIN_BY_RUNS = 11;
    public static final int WIN_BY_WICKETS = 12;
    public static final int PLAYER_OF_MATCH = 13;
    public static final int VENUE = 14;
    public static final int UMPIRE1 = 15;
    public static final int UMPIRE2 = 16;
    public static final int UMPIRE3 = 17;

    public static void main(String[] args) {
        List<Delivery> deliveries = setDeliveriesData();
        List<Match> matches = setMatchesData();

        System.out.println("1. Number of matches played per year of all the years in IPL.");
        findMatchesPlayedPerSeason(matches);

        System.out.println("\n2. Number of matches won of all teams over all the years of IPL.");
        findMatchesWonPerTeamAllSeason(matches);

        System.out.println("\n3. For the year 2016 get the extra runs conceded per team.");
        findExtrasConcededPerTeamIn2016(matches, deliveries);

        System.out.println("\n4. For the year 2015 get the top economical bowlers.");
        findTheMostEconomicalBowlerIn2015(matches, deliveries);

        System.out.println("\n5. Find the highest number of times one player has been dismissed by another player");
        findBatterWithHighestDismissalsByBowler(deliveries);

        System.out.println("\n6. For the year 2012 find the batter with highest strike rate(Minimum of 20 balls faced)");
        findHighestStrikeRateBatterIn2012(matches, deliveries);

        System.out.println("\n7. Number of matches won of all teams per year of IPL.");
        getTeamWinsBySeason(matches);
    }

    private static List<Match> setMatchesData() {
        List<Match> matches = new ArrayList<>();

        try (BufferedReader matchesReader = new BufferedReader(new FileReader("/home/pradeep/mountblue/java/ipl/data/matches.csv"))) {
            String nextLine;

            matchesReader.readLine();

            while ((nextLine = matchesReader.readLine()) != null) {
                String[] nextRecord = nextLine.split(",", -1);

                Match match = new Match();

                match.setId(Integer.parseInt(nextRecord[ID]));
                match.setSeason(Integer.parseInt(nextRecord[SEASON]));
                match.setCity(nextRecord[CITY]);
                match.setDate(LocalDate.parse(nextRecord[DATE]));  // Assuming nextRecord[DATE] is a String that needs to be parsed
                match.setTeam1(nextRecord[TEAM1]);
                match.setTeam2(nextRecord[TEAM2]);
                match.setTossWinner(nextRecord[TOSS_WINNER]);
                match.setTossDecision(nextRecord[TOSS_DECISION]);
                match.setResult(nextRecord[RESULT]);
                match.setDlApplied(Boolean.valueOf(nextRecord[DL_APPLIED]));
                match.setWinner(nextRecord[WINNER]);
                match.setWinByRuns(Integer.parseInt(nextRecord[WIN_BY_RUNS]));
                match.setWinByWickets(Integer.parseInt(nextRecord[WIN_BY_WICKETS]));
                match.setPlayerOfMatch(nextRecord[PLAYER_OF_MATCH]);
                match.setVenue(nextRecord[VENUE]);
                match.setUmpire1(nextRecord[UMPIRE1]);
                match.setUmpire2(nextRecord[UMPIRE2]);
                match.setUmpire3(nextRecord[UMPIRE3]);

                matches.add(match);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }

    private static List<Delivery> setDeliveriesData() {
        List<Delivery> deliveries = new ArrayList<>();

        try (BufferedReader deliveriesReader = new BufferedReader(new FileReader("/home/pradeep/mountblue/java/ipl/data/deliveries.csv"))) {
            String nextLine;

            deliveriesReader.readLine();

            while ((nextLine = deliveriesReader.readLine()) != null) {
                String[] nextRecord = nextLine.split(",", -1);

                Delivery delivery = new Delivery();

                delivery.setMatchId(Integer.parseInt(nextRecord[MATCH_ID]));
                delivery.setInning(Integer.parseInt(nextRecord[INNING]));
                delivery.setBattingTeam(nextRecord[BATTING_TEAM]);
                delivery.setBowlingTeam(nextRecord[BOWLING_TEAM]);
                delivery.setOver(Integer.parseInt(nextRecord[OVER]));
                delivery.setBall(Integer.parseInt(nextRecord[BALL]));
                delivery.setBatsman(nextRecord[BATSMAN]);
                delivery.setNonStriker(nextRecord[NON_STRIKER]);
                delivery.setBowler(nextRecord[BOWLER]);
                delivery.setSuperOver(Boolean.valueOf(nextRecord[IS_SUPER_OVER]));
                delivery.setWideRuns(Integer.parseInt(nextRecord[WIDE_RUNS]));
                delivery.setByeRuns(Integer.parseInt(nextRecord[BYE_RUNS]));
                delivery.setLegbyeRuns(Integer.parseInt(nextRecord[LEGBYE_RUNS]));
                delivery.setNoballRuns(Integer.parseInt(nextRecord[NOBALL_RUNS]));
                delivery.setPenaltyRuns(Integer.parseInt(nextRecord[PENALTY_RUNS]));
                delivery.setBatsmanRuns(Integer.parseInt(nextRecord[BATSMAN_RUNS]));
                delivery.setExtraRuns(Integer.parseInt(nextRecord[EXTRA_RUNS]));
                delivery.setTotalRuns(Integer.parseInt(nextRecord[TOTAL_RUNS]));
                delivery.setPlayerDismissed(nextRecord[PLAYER_DISMISSED]);
                delivery.setDismissalKind(nextRecord[DISMISSAL_KIND]);
                delivery.setFielder(nextRecord[FIELDER]);

                deliveries.add(delivery);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return deliveries;
    }

    private static void getTeamWinsBySeason(List<Match> matches) {
        Map<Integer, Map<String, Integer>> teamWinsBySeason = new TreeMap<>();

        for (Match match : matches) {
            if (teamWinsBySeason.containsKey(match.getSeason())) {
                Map<String, Integer> seasonsWinRecord = teamWinsBySeason.get(match.getSeason());

                if (match.getResult().equals("no result")) {
                    seasonsWinRecord.put("No Result", seasonsWinRecord.getOrDefault("No Result", 0) + 1);
                    teamWinsBySeason.put(match.getSeason(), seasonsWinRecord);
                } else if (teamWinsBySeason.get(match.getSeason()).containsKey(match.getWinner())) {

                    seasonsWinRecord.put(match.getWinner(), seasonsWinRecord.get(match.getWinner()) + 1);

                    teamWinsBySeason.put(match.getSeason(), seasonsWinRecord);
                } else {

                    seasonsWinRecord.put(match.getWinner(), 1);

                    teamWinsBySeason.put(match.getSeason(), seasonsWinRecord);
                }
            } else {
                Map<String, Integer> winnerCount = new TreeMap<>();

                winnerCount.put(match.getWinner(), 1);

                teamWinsBySeason.put(match.getSeason(), winnerCount);
            }
        }

        for (Integer season : teamWinsBySeason.keySet()) {
            System.out.println("Season: " + season);

            for (String team : teamWinsBySeason.get(season).keySet())
                System.out.println("Team: " + team + ", Wins: " + teamWinsBySeason.get(season).get(team));
        }
    }

    private static void findHighestStrikeRateBatterIn2012(List<Match> matches, List<Delivery> deliveries) {
        int year = 2012;
        int minimumBallsPlayed = 20;

        TreeMap<String, Double> strikeRateByBatter = new TreeMap<>();

        HashMap<String, Integer> ballsFacedByBatter = new HashMap<>();
        HashMap<String, Integer> runsScoredByBatter = new HashMap<>();

        List<Integer> matchIds = new ArrayList<>();

        for (Match match : matches)
            if (year == match.getSeason())
                matchIds.add(match.getId());

        for (Delivery delivery : deliveries) {
            boolean isDesiredYear = false;
            for (Integer id : matchIds)
                if (delivery.getMatchId() == id) {
                    isDesiredYear = true;
                    break;
                }

            if (!isDesiredYear)
                continue;

            Integer runsScoredByBatterOnThisDelivery = delivery.getBatsmanRuns();

            runsScoredByBatter.put(delivery.getBatsman(), runsScoredByBatter.getOrDefault(delivery.getBatsman(), 0) + runsScoredByBatterOnThisDelivery);

            if (delivery.getNoballRuns() == 0 && delivery.getWideRuns() == 0) {
                ballsFacedByBatter.put(delivery.getBatsman(), ballsFacedByBatter.getOrDefault(delivery.getBatsman(), 0) + 1);
            } else
                ballsFacedByBatter.put(delivery.getBatsman(), ballsFacedByBatter.getOrDefault(delivery.getBatsman(), 0));
        }

        for (String batter : runsScoredByBatter.keySet()) {
            if (ballsFacedByBatter.get(batter) < minimumBallsPlayed)
                continue;

            Double strikeRate = 100 * ((double) runsScoredByBatter.get(batter) / (double) ballsFacedByBatter.get(batter));

            strikeRateByBatter.put(batter, strikeRate);
        }

        Double highestStrikeRate = Double.MIN_VALUE;
        String nameOfBatter = "Not Found";

        for (String batter : strikeRateByBatter.keySet()) {
            if (strikeRateByBatter.get(batter) > highestStrikeRate) {
                highestStrikeRate = strikeRateByBatter.get(batter);
                nameOfBatter = batter;
            }
        }

        System.out.println("Batsman: " + nameOfBatter + ", Strike Rate: " + highestStrikeRate);
    }

    private static void findBatterWithHighestDismissalsByBowler(List<Delivery> deliveries) {
        HashMap<String, HashMap<String, Integer>> dismissalsByBowler = new HashMap<>();

        for (Delivery delivery : deliveries) {
            if (delivery.getPlayerDismissed() == "")
                continue;

            if (delivery.getDismissalKind() == "run out")
                continue;

            if (dismissalsByBowler.containsKey(delivery.getBowler())) {
                if (dismissalsByBowler.get(delivery.getBowler()).containsKey(delivery.getBatsman())) {
                    HashMap<String, Integer> wicketsTakenByBowler = dismissalsByBowler.get(delivery.getBowler());
                    wicketsTakenByBowler.put(delivery.getBatsman(), wicketsTakenByBowler.get(delivery.getBatsman()) + 1);
                } else {
                    HashMap<String, Integer> wicketsTakenByBowler = dismissalsByBowler.get(delivery.getBowler());
                    wicketsTakenByBowler.put(delivery.getBatsman(), 1);
                    dismissalsByBowler.put(delivery.getBowler(), wicketsTakenByBowler);
                }
            } else {
                HashMap<String, Integer> wicketsTakenByBowler = new HashMap<>();

                wicketsTakenByBowler.put(delivery.getBatsman(), 1);
                dismissalsByBowler.put(delivery.getBowler(), wicketsTakenByBowler);
            }
        }

        class ResultingPair {
            String bowlingPlayer;
            String battingPlayer;
            int highestBatterDismissalsByBowler;

            public ResultingPair(String bowlingPlayer, String battingPlayer, int highestBatterDismissalsByBowler) {
                this.bowlingPlayer = bowlingPlayer;
                this.battingPlayer = battingPlayer;
                this.highestBatterDismissalsByBowler = highestBatterDismissalsByBowler;
            }

            @Override
            public String toString() {
                return "Bowler : " + bowlingPlayer + ",\tBatter : " + battingPlayer + ",\tNumber of dismissals : " + highestBatterDismissalsByBowler;
            }
        }

        ArrayList<ResultingPair> playersPairList = new ArrayList<>();

        int highestOutsCount = 0;

        for (String bowler : dismissalsByBowler.keySet()) {
            for (String playerDismissed : dismissalsByBowler.get(bowler).keySet()) {
                if (dismissalsByBowler.get(bowler).get(playerDismissed) > highestOutsCount) {
                    playersPairList.clear();
                    playersPairList.add(new ResultingPair(bowler, playerDismissed, dismissalsByBowler.get(bowler).get(playerDismissed)));
                    highestOutsCount = dismissalsByBowler.get(bowler).get(playerDismissed);
                } else if (dismissalsByBowler.get(bowler).get(playerDismissed) == highestOutsCount) {
                    playersPairList.add(new ResultingPair(bowler, playerDismissed, dismissalsByBowler.get(bowler).get(playerDismissed)));
                }
            }
        }

        for (ResultingPair pair : playersPairList)
            System.out.println(pair);
    }

    private static void findTheMostEconomicalBowlerIn2015(List<Match> matches, List<Delivery> deliveries) {
        int year = 2015;

        SortedMap<String, Double> bowlerEconomies = new TreeMap<>();

        HashMap<String, Integer> runsConcededByBowler = new HashMap<>();
        HashMap<String, Integer> ballsBowledByBowler = new HashMap<>();

        List<Integer> matchIds = new ArrayList<>();

        for (Match match : matches)
            if (year == match.getSeason())
                matchIds.add(match.getId());

        for (Delivery delivery : deliveries) {
            boolean isDesiredYear = false;

            for (Integer id : matchIds) {
                if (delivery.getMatchId() == id) {
                    isDesiredYear = true;
                    break;
                }
            }

            if (!isDesiredYear)
                continue;

            if (ballsBowledByBowler.containsKey(delivery.getBowler())) {
                runsConcededByBowler.put(
                        delivery.getBowler(),
                        runsConcededByBowler.get(delivery.getBowler())
                                + delivery.getTotalRuns()
                                - delivery.getByeRuns()
                                - delivery.getLegbyeRuns()
                                - delivery.getPenaltyRuns());

                if (delivery.getWideRuns() == 0 && delivery.getNoballRuns() == 0)
                    ballsBowledByBowler.put(delivery.getBowler(),
                            ballsBowledByBowler.get(delivery.getBowler()) + 1);
            } else {
                runsConcededByBowler.put(
                        delivery.getBowler(),
                        delivery.getTotalRuns()
                                - delivery.getByeRuns()
                                - delivery.getLegbyeRuns()
                                - delivery.getPenaltyRuns());

                if (delivery.getWideRuns() == 0 && delivery.getNoballRuns() == 0)
                    ballsBowledByBowler.put(delivery.getBowler(), 1);
                else
                    ballsBowledByBowler.put(delivery.getBowler(), 0);
            }
        }

        for (String bowler : ballsBowledByBowler.keySet()) {
            double overs = (double) ballsBowledByBowler.get(bowler) / 6.0;
            bowlerEconomies.put(bowler, (double) runsConcededByBowler.get(bowler) / overs);
        }

        Double highestEconomyByBowler = Double.MAX_VALUE;
        String nameOfBowler = "Not Found";

        for (String bowler : bowlerEconomies.keySet()) {
            if (bowlerEconomies.get(bowler) < highestEconomyByBowler) {
                highestEconomyByBowler = bowlerEconomies.get(bowler);
                nameOfBowler = bowler;
            }
        }

        System.out.println("Bowler: " + nameOfBowler + ", Economy: " + highestEconomyByBowler);
    }

    private static void findExtrasConcededPerTeamIn2016(List<Match> matches, List<Delivery> deliveries) {
        int year = 2016;

        HashMap<String, Integer> extrasConcededPerTeam = new HashMap<>();

        List<Integer> matchIds = new ArrayList<>();

        for (Match match : matches)
            if (year == match.getSeason())
                matchIds.add(match.getId());

        for (Delivery delivery : deliveries) {
            boolean isDesiredYear = false;

            for (Integer id : matchIds) {
                if (delivery.getMatchId() == id) {
                    isDesiredYear = true;
                    break;
                }
            }

            if (!isDesiredYear)
                continue;

            if (extrasConcededPerTeam.containsKey(delivery.getBowlingTeam())) {
                extrasConcededPerTeam.put(delivery.getBowlingTeam(), extrasConcededPerTeam.get(delivery.getBowlingTeam()) + delivery.getExtraRuns());
            } else {
                extrasConcededPerTeam.put(delivery.getBowlingTeam(), delivery.getExtraRuns());
            }
        }

        for (String team : extrasConcededPerTeam.keySet())
            System.out.println(team + ": " + extrasConcededPerTeam.get(team));
    }

    private static void findMatchesWonPerTeamAllSeason(List<Match> matches) {
        HashMap<String, Integer> matchesWonByTeam = new HashMap<>();

        for (Match match : matches) {
            if (match.getResult().equals("no result"))
                matchesWonByTeam.put("No Result", matchesWonByTeam.getOrDefault("No Result", 0) + 1);
            else
                matchesWonByTeam.put(match.getWinner(), matchesWonByTeam.getOrDefault(match.getWinner(), 0) + 1);
        }

        for (String team : matchesWonByTeam.keySet())
            System.out.println(team + ": " + matchesWonByTeam.get(team));
    }

    private static void findMatchesPlayedPerSeason(List<Match> matches) {
        Map<Integer, Integer> matchesPlayed = new TreeMap<>();

        for (Match match : matches)
            matchesPlayed.put(match.getSeason(), matchesPlayed.getOrDefault(match.getSeason(), 0) + 1);

        for (Integer season : matchesPlayed.keySet())
            System.out.println("Matches played in " + season + ": " + matchesPlayed.get(season));
    }

}