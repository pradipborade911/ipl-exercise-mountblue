package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");
        Utils.setData();

        boolean exit = false;
        try (Scanner sc = new Scanner(System.in)) {
            while (!exit) {
                System.out.print("Choose stat :" +
                        "\n 1 Number of matches played per year of all the years in IPL." +
                        "\n 2. Number of matches won of all teams over all the years of IPL." +
                        "\n 3. For the year 2016 get the extra runs conceded per team." +
                        "\n 4. For the year 2015 get the top economical bowlers." +
                        "\n 5. Find the highest number of times one player has been dismissed by another player" +
                        "\n 6. Find a batter with highest strike rate in year 2012" +
                        "\n 7. for exit\n");
                int choice = sc.nextInt(); sc.nextLine();
                switch (choice){
                    case 1 : {
                        for(int season = 2008; season <= 2016; season++){
                            System.out.println("Matches played in " + season + ": " + Utils.matchesPlayedInSeason(season));
                        }
                    }
                    break;
                    case 2: {
                        HashMap<Integer, HashMap<String, Integer>> winsPerSeasonByTeam = Utils.matchesWonByeachTeamInSeason();
                        for(int season : winsPerSeasonByTeam.keySet()){
                            System.out.println("Season: " + season);
                            HashMap<String, Integer> winsThisSeasonByTeam = winsPerSeasonByTeam.get(season);
                            for(String team : winsThisSeasonByTeam.keySet()){
                                System.out.print("Team: " + team);
                                System.out.println(", Wins: " + winsThisSeasonByTeam.get(team));
                            }
                        }
                    }
                    break;
                    case 3 : {
                        HashMap<String, Integer> extrasThisYear = Utils.extrasConcededThisYearByTeams(2016);
                        for(String team : extrasThisYear.keySet()){
                            System.out.print("Team: " + team);
                            System.out.println(", Extras: " + extrasThisYear.get(team));
                        }
                    }
                    break;
                    case 4 : {
                        Map<String, Double> economies = Utils.econmiesOfBowlers(2015);
                        for (String bowler : economies.keySet()) {
                            System.out.println(bowler + ":" + economies.get(bowler));
                        }
                    }
                    break;
                    case 5 : {
                        HashMap<String, HashMap<String, Integer>>result = Utils.dismissalsByBowler();
                        int highestNumberOfTimesOnePlayerHasBeenDismissedByAnotherPlayer = 0;
                        String bowlingPlayer = "NotFound";
                        String battingPlayer = "NotFound";
                        for(String bowler : result.keySet()){
                            for(String playerDismissed : result.get(bowler).keySet()){
                                if(result.get(bowler).get(playerDismissed) > highestNumberOfTimesOnePlayerHasBeenDismissedByAnotherPlayer) {
                                    highestNumberOfTimesOnePlayerHasBeenDismissedByAnotherPlayer = result.get(bowler).get(playerDismissed);
                                    battingPlayer = playerDismissed;
                                    bowlingPlayer = bowler;
                                }
                            }
                        }
                        System.out.println("Player Dismissed: " + battingPlayer +
                                "\nNumber of times player dismissed: " + highestNumberOfTimesOnePlayerHasBeenDismissedByAnotherPlayer +
                                "\nBowler: " + bowlingPlayer);
                    }
                    break;
                    case 6 : {
                        Utils.printHighestStrikeRateBatterByYear(2016);
                    }
                    break;
                    case 7 : {
                        exit = true;
                    }
                    break;
                }
            }
        }
    }
}