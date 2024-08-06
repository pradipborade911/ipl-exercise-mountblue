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

        System.out.println("\n8. Most Runs in IPL 2016.");
        findLeadingScorerByTeamIn2016(matches, deliveries);

        System.out.println("\n8. Find the player with highest Strike Rate per team per year");
        findBestStrikerPerSeasonPerTeam(matches, deliveries);
    }

    private static List<Match> setMatchesData() {
        List<Match> matches = new ArrayList<>();
        String matchesPath = "./data/matches.csv";

        try (BufferedReader matchesReader = new BufferedReader(new FileReader(matchesPath))) {
            String nextLine;
            matchesReader.readLine();

            while ((nextLine = matchesReader.readLine()) != null) {
                String[] nextRecord = nextLine.split(",", -1);

                Match match = new Match();

                match.setId(Integer.parseInt(nextRecord[ID]));
                match.setSeason(Integer.parseInt(nextRecord[SEASON]));
                match.setCity(nextRecord[CITY]);
                match.setDate(LocalDate.parse(nextRecord[DATE]));
                match.setTeam1(nextRecord[TEAM1]);
                match.setTeam2(nextRecord[TEAM2]);
                match.setTossWinner(nextRecord[TOSS_WINNER]);
                match.setTossDecision(nextRecord[TOSS_DECISION]);
                match.setResult(nextRecord[RESULT]);
                match.setDlApplied(Boolean.parseBoolean(nextRecord[DL_APPLIED]));
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
        String deliveriessPath = "./data/deliveries.csv";

        try (BufferedReader deliveriesReader = new BufferedReader(new FileReader(deliveriessPath))) {
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
                delivery.setSuperOver(Boolean.parseBoolean(nextRecord[IS_SUPER_OVER]));
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

        teamWinsBySeason.forEach((season, winsPerTeam) -> {
            System.out.println("Season: " + season);
            winsPerTeam.forEach((team, wins) -> System.out.println("Team: " + team + ", Wins: " + wins));
        });
    }

    private static void findHighestStrikeRateBatterIn2012(List<Match> matches, List<Delivery> deliveries) {
        int year = 2012;
        int minimumBallsPlayed = 20;

        HashMap<String, Integer> ballsFacedByBatter = new HashMap<>();
        HashMap<String, Integer> runsScoredByBatter = new HashMap<>();
        Set<Integer> matchIds = new HashSet<>();

        for (Match match : matches) {
            if (year == match.getSeason())
                matchIds.add(match.getId());
        }

        for (Delivery delivery : deliveries) {
            if (!matchIds.contains(delivery.getMatchId()))
                continue;

            Integer runsScoredByBatterOnThisDelivery = delivery.getBatsmanRuns();
            runsScoredByBatter.put(delivery.getBatsman(), runsScoredByBatter.getOrDefault(delivery.getBatsman(), 0) + runsScoredByBatterOnThisDelivery);

            if (delivery.getNoballRuns() == 0 && delivery.getWideRuns() == 0) {
                ballsFacedByBatter.put(delivery.getBatsman(), ballsFacedByBatter.getOrDefault(delivery.getBatsman(), 0) + 1);
            } else
                ballsFacedByBatter.put(delivery.getBatsman(), ballsFacedByBatter.getOrDefault(delivery.getBatsman(), 0));
        }


        class BatterStrikeRateStats {
            String nameOfBatter;
            Double strikeRate;

            public BatterStrikeRateStats(String nameOfBatter, Double highestStrikeRate) {
                this.strikeRate = highestStrikeRate;
                this.nameOfBatter = nameOfBatter;
            }

            public String toString() {
                return "Batsman: " + nameOfBatter + ", Strike Rate: " + strikeRate;
            }
        }

        BatterStrikeRateStats bestBattersStats = new BatterStrikeRateStats("Test", 0.0);

        List<BatterStrikeRateStats> batterStrikeRateStatsList = new LinkedList<>();

        batterStrikeRateStatsList.add(bestBattersStats);

        runsScoredByBatter.forEach((batter, runs) -> {
            if (ballsFacedByBatter.get(batter) < minimumBallsPlayed)
                return;

            Double strikeRate = 100 * ((double) runs / (double) ballsFacedByBatter.get(batter));

            if (strikeRate > bestBattersStats.strikeRate) {
                batterStrikeRateStatsList.clear();

                bestBattersStats.strikeRate = strikeRate;
                bestBattersStats.nameOfBatter = batter;

                batterStrikeRateStatsList.add(bestBattersStats);
            } else if (strikeRate.equals(bestBattersStats.strikeRate)) {
                batterStrikeRateStatsList.add(new BatterStrikeRateStats(batter, strikeRate));
            }
        });

        batterStrikeRateStatsList.forEach(batter -> System.out.println(batter));
    }

    private static void findBatterWithHighestDismissalsByBowler(List<Delivery> deliveries) {
        HashMap<String, HashMap<String, Integer>> dismissalsByBowler = new HashMap<>();

        for (Delivery delivery : deliveries) {
            if (delivery.getPlayerDismissed().isEmpty() || delivery.getDismissalKind().equals("run out"))
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

        Map<String, Double> bowlerEconomies = new TreeMap<>();

        HashMap<String, Integer> runsConcededByBowler = new HashMap<>();
        HashMap<String, Integer> ballsBowledByBowler = new HashMap<>();

        Set<Integer> matchIds = new HashSet<>();

        for (Match match : matches)
            if (year == match.getSeason())
                matchIds.add(match.getId());

        for (Delivery delivery : deliveries) {
            if (!matchIds.contains(delivery.getMatchId()))
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

        Set<Integer> matchIds = new HashSet<>();

        matches.forEach(match -> {
            if (year == match.getSeason())
                matchIds.add(match.getId());
        });

        for (Delivery delivery : deliveries) {
            if (!matchIds.contains(delivery.getMatchId()))
                continue;

            if (extrasConcededPerTeam.containsKey(delivery.getBowlingTeam())) {
                extrasConcededPerTeam.put(delivery.getBowlingTeam(), extrasConcededPerTeam.get(delivery.getBowlingTeam()) + delivery.getExtraRuns());
            } else {
                extrasConcededPerTeam.put(delivery.getBowlingTeam(), delivery.getExtraRuns());
            }
        }

        extrasConcededPerTeam.forEach((team, extras) -> System.out.println(team + ": " + extras));
    }

    private static void findMatchesWonPerTeamAllSeason(List<Match> matches) {
        HashMap<String, Integer> matchesWonByTeam = new HashMap<>();

        for (Match match : matches) {
            if (match.getResult().equals("no result"))
                matchesWonByTeam.put("No Result", matchesWonByTeam.getOrDefault("No Result", 0) + 1);
            else
                matchesWonByTeam.put(match.getWinner(), matchesWonByTeam.getOrDefault(match.getWinner(), 0) + 1);
        }

        matchesWonByTeam.forEach((team, count) -> System.out.println(team + ": " + count));

    }

    private static void findMatchesPlayedPerSeason(List<Match> matches) {
        Map<Integer, Integer> matchesPlayed = new TreeMap<>();

        for (Match match : matches)
            matchesPlayed.put(match.getSeason(), matchesPlayed.getOrDefault(match.getSeason(), 0) + 1);

        matchesPlayed.forEach((season, numberOfmatchesPlayed) -> System.out.println("Matches played in " + season + ": " + numberOfmatchesPlayed));
    }

    private static void findLeadingScorerByTeamIn2016(List<Match> matches, List<Delivery> deliveries) {
        int year = 2016;

        Map<String, HashMap<String, Integer>> teamPlayerScoreMap = new HashMap<>();

        Set<Integer> matchIds = new HashSet<>();

        for (Match match : matches)
            if (year == match.getSeason())
                matchIds.add(match.getId());

        for (Delivery delivery : deliveries) {
            if (!matchIds.contains(delivery.getMatchId()))
                continue;

            String team = delivery.getBattingTeam();
            if (teamPlayerScoreMap.containsKey(team)) {
                HashMap<String, Integer> playerEntry = teamPlayerScoreMap.get(team);
                playerEntry.put(delivery.getBatsman(), playerEntry.getOrDefault(delivery.getBatsman(), 0) + delivery.getBatsmanRuns());
            } else {
                HashMap<String, Integer> playerEntry = new HashMap<>();
                playerEntry.put(delivery.getBatsman(), delivery.getBatsmanRuns());
                teamPlayerScoreMap.put(team, playerEntry);
            }


        }

        teamPlayerScoreMap.forEach((team, playerScores) -> {
            System.out.print("Team: " + team);
            int leadingScore = 0;
            String leadingPlayer = "Not found";
            for (String player : playerScores.keySet()) {
                if (playerScores.get(player) > leadingScore) {
                    leadingScore = playerScores.get(player);
                    leadingPlayer = player;
                }
            }
            System.out.println(", Player: " + leadingPlayer + ", Runs Scored: " + leadingScore);
        });
    }

    private static void findBestStrikerPerSeasonPerTeam(List<Match> matches, List<Delivery> deliveries) {
        HashMap<Integer, HashMap<String, HashMap<String, BatterStrikeRateStats>>> playerPerformanceData = new HashMap<>();

        HashMap<Integer, Integer> seasonsByMatchIds = new HashMap<>();

        for (Match match : matches) {
            seasonsByMatchIds.put(match.getId(), match.getSeason());
        }

        for (Delivery delivery : deliveries) {
            final Integer thisSeason = seasonsByMatchIds.get(delivery.getMatchId());
            final String thisTeam = delivery.getBattingTeam();
            final String thisBatter = delivery.getBatsman();
            final Integer runsScored = delivery.getBatsmanRuns();
            final Integer ballsBowled = delivery.getNoballRuns() == 0 && delivery.getWideRuns() == 0 ? 1 : 0;

            if (playerPerformanceData.containsKey(thisSeason)) {
                if (playerPerformanceData.get(thisSeason).containsKey(thisTeam)) {
                    if (playerPerformanceData.get(thisSeason).get(thisTeam).containsKey(thisBatter)) {
                        BatterStrikeRateStats batterStats = playerPerformanceData
                                .get(thisSeason)
                                .get(thisTeam)
                                .get(thisBatter);
                        batterStats.addNameRunsAndBalls(thisBatter, runsScored, ballsBowled);

                        HashMap<String, BatterStrikeRateStats> playerData = playerPerformanceData.get(thisSeason).get(thisTeam);
                        playerData.put(thisBatter, batterStats);

                        HashMap<String, HashMap<String, BatterStrikeRateStats>> teamData = playerPerformanceData.get(thisSeason);
                        teamData.put(thisTeam, playerData);

                        playerPerformanceData.put(thisSeason, teamData);
                    } else {
                        BatterStrikeRateStats batterStats = new BatterStrikeRateStats();
                        batterStats.addNameRunsAndBalls(thisBatter, runsScored, ballsBowled);

                        HashMap<String, BatterStrikeRateStats> playerData = playerPerformanceData.get(thisSeason).get(thisTeam);
                        playerData.put(thisBatter, batterStats);

                        HashMap<String, HashMap<String, BatterStrikeRateStats>> teamData = playerPerformanceData.get(thisSeason);
                        teamData.put(thisTeam, playerData);
                    }

                } else {
                    BatterStrikeRateStats batterStats = new BatterStrikeRateStats();
                    batterStats.addNameRunsAndBalls(thisBatter, runsScored, ballsBowled);

                    HashMap<String, BatterStrikeRateStats> playerData = new HashMap<>();
                    playerData.put(thisBatter, batterStats);

                    HashMap<String, HashMap<String, BatterStrikeRateStats>> teamData = playerPerformanceData.get(thisSeason);
                    teamData.put(thisTeam, playerData);
                }

            } else {
                BatterStrikeRateStats batterStats = new BatterStrikeRateStats();
                batterStats.addNameRunsAndBalls(thisBatter, runsScored, ballsBowled);

                HashMap<String, BatterStrikeRateStats> playerData = new HashMap<>();
                playerData.put(thisBatter, batterStats);

                HashMap<String, HashMap<String, BatterStrikeRateStats>> teamData = new HashMap<>();
                teamData.put(thisTeam, playerData);

                playerPerformanceData.put(thisSeason, teamData);
            }
        }

        for (Integer season : playerPerformanceData.keySet()) {
            System.out.println("Season: " + season);

            for (String team : playerPerformanceData.get(season).keySet()) {
                BatterStrikeRateStats bestStats = new BatterStrikeRateStats();

                for (String player : playerPerformanceData.get(season).get(team).keySet()) {
                    BatterStrikeRateStats thisBattersStats = playerPerformanceData.get(season).get(team).get(player);
                    if (thisBattersStats.getStrikeRate() > bestStats.getStrikeRate()) {
                        bestStats = thisBattersStats;
                    }
                }

                System.out.println("\t\t" + team + ", " + bestStats.getName() + ": " + bestStats.getStrikeRate());
            }
        }
    }

    private static void findBestStrikerEachSeasonPerTeam(List<Match> matches, List<Delivery> deliveries) {
        HashMap<Integer, HashMap<String, HashMap<String, BatterStrikeRateStats>>> playerPerformanceData = new HashMap<>();
        HashMap<Integer, Integer> seasonsByMatchIds = new HashMap<>();

        for (Match match : matches) {
            seasonsByMatchIds.put(match.getId(), match.getSeason());
        }

        for (Delivery delivery : deliveries) {
            final Integer thisSeason = seasonsByMatchIds.get(delivery.getMatchId());
            final String thisTeam = delivery.getBattingTeam();
            final String thisBatter = delivery.getBatsman();
            final int runsScored = delivery.getBatsmanRuns();
            final int ballsFaced = (delivery.getNoballRuns() == 0 && delivery.getWideRuns() == 0) ? 1 : 0;

            playerPerformanceData
                    .computeIfAbsent(thisSeason, k -> new HashMap<>())
                    .computeIfAbsent(thisTeam, k -> new HashMap<>())
                    .computeIfAbsent(thisBatter, k -> new BatterStrikeRateStats())
                    .addNameRunsAndBalls(thisBatter, runsScored, ballsFaced);
        }

        for (Map.Entry<Integer, HashMap<String, HashMap<String, BatterStrikeRateStats>>> seasonEntry : playerPerformanceData.entrySet()) {
            final Integer season = seasonEntry.getKey();
            System.out.println("Season: " + season);

            for (Map.Entry<String, HashMap<String, BatterStrikeRateStats>> teamEntry : seasonEntry.getValue().entrySet()) {
                final String team = teamEntry.getKey();
                final BatterStrikeRateStats bestStats = teamEntry.getValue().values().stream()
                        .max(Comparator.comparingDouble(BatterStrikeRateStats::getStrikeRate))
                        .orElse(new BatterStrikeRateStats());

                System.out.println("\t\t" + team + ", " + bestStats.getName() + ": " + bestStats.getStrikeRate());
            }
        }
    }
}