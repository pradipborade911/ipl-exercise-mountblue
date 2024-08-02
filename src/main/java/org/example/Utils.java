package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Utils {
    public static List<Delivery> deliveries = new ArrayList<>();
    public static List<Match> matches = new ArrayList<>();

    public static void setData(){
        try(BufferedReader deliveriesReader = new BufferedReader( new FileReader("/home/pradeep/mountblue/java/ipl/data/deliveries.csv"));
            BufferedReader matchesReader = new BufferedReader( new FileReader("/home/pradeep/mountblue/java/ipl/data/matches.csv"))){
            String nextLine;
            deliveriesReader.readLine();
            while ((nextLine = deliveriesReader.readLine()) != null) {
                String[] nextRecord = nextLine.split(",", -1);
                Delivery delivery = new Delivery(Integer.valueOf(nextRecord[0]),
                        Integer.valueOf(nextRecord[1]),
                        nextRecord[2],
                        nextRecord[3],
                        Integer.valueOf(nextRecord[4]),
                        Integer.valueOf(nextRecord[5]),
                        nextRecord[6],
                        nextRecord[7],
                        nextRecord[8],
                        Boolean.valueOf(nextRecord[9]),
                        Integer.valueOf(nextRecord[10]),
                        Integer.valueOf(nextRecord[11]),
                        Integer.valueOf(nextRecord[12]),
                        Integer.valueOf(nextRecord[13]),
                        Integer.valueOf(nextRecord[14]),
                        Integer.valueOf(nextRecord[15]),
                        Integer.valueOf(nextRecord[16]),
                        Integer.valueOf(nextRecord[17]),
                        String.valueOf(nextRecord[18]),
                        nextRecord[19],
                        nextRecord[20]
                );
                deliveries.add(delivery);
            }
            matchesReader.readLine();
            while ((nextLine = matchesReader.readLine()) != null) {
                String[] nextRecord = nextLine.split(",", -1);
                Match match = new Match(Integer.valueOf(nextRecord[0]),
                        Integer.valueOf(nextRecord[1]),
                        nextRecord[2],
                        LocalDate.parse(nextRecord[3], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        nextRecord[4],
                        nextRecord[5],
                        nextRecord[6],
                        nextRecord[7],
                        nextRecord[8],
                        Boolean.valueOf(nextRecord[9]),
                        nextRecord[10],
                        Integer.valueOf(nextRecord[11]),
                        Integer.valueOf(nextRecord[12]),
                        nextRecord[13],
                        nextRecord[14],
                        nextRecord[15],
                        nextRecord[16],
                        nextRecord[17]
                );
                matches.add(match);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int matchesPlayedInSeason(int season){
        int numOfMatchesPlayed = 0;
        for(Match match : matches){
            if(match.getSeason() == season)
                ++numOfMatchesPlayed;
        }
        return numOfMatchesPlayed;
    }

    public static HashMap<Integer, HashMap<String, Integer>> matchesWonByeachTeamInSeason(){
        //<Season, <Team, Won>>
        HashMap<Integer, HashMap<String, Integer>> result = new HashMap<>();
        for(Match match : matches){
            if(result.containsKey(match.getSeason())){
                if(result.get(match.getSeason()).containsKey(match.getWinner())){
                    //get specific teams record for this season
                    HashMap<String, Integer> seasonsWinRecord = result.get(match.getSeason());
                    //update winner teams record
                    seasonsWinRecord.put(match.getWinner(), seasonsWinRecord.get(match.getWinner()) + 1);
                    //upadte result haspmap
                    result.put(match.getSeason(), seasonsWinRecord);
                }else{
                    //fetch records for specific team for this season
                    HashMap<String, Integer> seasonsWinRecord = result.get(match.getSeason());
                    //make new entry seasons record for new team
                    seasonsWinRecord.put(match.getWinner(), 1);
                    //make new entry in result haspmap
                    result.put(match.getSeason(), seasonsWinRecord);
                }
            }else{
                //create new team record pair
                HashMap<String, Integer> winnerCount = new HashMap<>();
                winnerCount.put(match.getWinner(), 1);
                result.put(match.getSeason(), winnerCount);
            }
        }
        return result;
    }

    public static HashMap<String, Integer> extrasConcededThisYearByTeams(Integer year){
        HashMap<String, Integer> result = new HashMap<>();
        //match ids of matches played in $(year)
        List<Integer> matchIds = new ArrayList<>();
        for(Match match : matches)
            if(year == match.getSeason())
                matchIds.add(match.getId());

        for(Delivery delivery : deliveries){
            boolean isDesiredYear = false;

            //check if delivery is from desried year
            for(Integer id : matchIds){
                if(delivery.getMatchId() == id) {
                    isDesiredYear = true;
                    break;
                }
            }
            //skip count if not desired year
            if(!isDesiredYear)
                continue;

            if(result.containsKey(delivery.getBowlingTeam())){
                //update extras conceded by this team
                result.put(delivery.getBowlingTeam(), result.get(delivery.getBowlingTeam()) + delivery.getExtraRuns());
            }else{
                //make new entry in result
                result.put(delivery.getBowlingTeam(), delivery.getExtraRuns());
            }
        }
        return result;
    }

    public static SortedMap<String, Double> econmiesOfBowlers(Integer year){
        SortedMap<String, Double> result = new TreeMap<>();
        HashMap<String, Integer> runsConcededByBowler = new HashMap<>();
        HashMap<String, Integer> ballsBowledByBowler = new HashMap<>();

        List<Integer> matchIds = new ArrayList<>();
        for(Match match : matches)
            if(year == match.getSeason())
                matchIds.add(match.getId());

        for(Delivery delivery : deliveries) {
            boolean isDesiredYear = false;

            //check if delivery is from desried year
            for (Integer id : matchIds) {
                if (delivery.getMatchId() == id) {
                    isDesiredYear = true;
                    break;
                }
            }

            //skip count if not desired year
            if (!isDesiredYear)
                continue;

            if(ballsBowledByBowler.containsKey(delivery.getBowler())){
                runsConcededByBowler.put(delivery.getBowler(), runsConcededByBowler.get(delivery.getBowler()) + delivery.getTotalRuns() - delivery.getByeRuns() - delivery.getLegbyeRuns());
                if(delivery.getWideRuns() == 0 && delivery.getNoballRuns() == 0)
                    ballsBowledByBowler.put(delivery.getBowler(), ballsBowledByBowler.get(delivery.getBowler()) + 1);
            }else{
                runsConcededByBowler.put(delivery.getBowler(), delivery.getTotalRuns() - delivery.getByeRuns() - delivery.getLegbyeRuns());
                if(delivery.getWideRuns() == 0 && delivery.getNoballRuns() == 0)
                    ballsBowledByBowler.put(delivery.getBowler(), 1);
                else
                    ballsBowledByBowler.put(delivery.getBowler(), 0);
            }
        }

        for(String bowler : ballsBowledByBowler.keySet()){
//            System.out.println(bowler + ", Balls bowled: " + ballsBowledByBowler.get(bowler) + ", Runs conceded: " + runsConcededByBowler.get(bowler));
            double overs = (double)ballsBowledByBowler.get(bowler) / 6.0;
            result.put(bowler, (double)runsConcededByBowler.get(bowler)/overs);
        }

        SortedMap<String, Double> result1 = new TreeMap<>(new SortByEconomy(result));
        result1.putAll(result);
        return result1;
    }

    public static HashMap<String, HashMap<String, Integer>> dismissalsByBowler(){
        HashMap<String, HashMap<String, Integer>> result = new HashMap<>();

        for(Delivery delivery : deliveries){
            //is this a wicket delivery,yes:no?skip:goAhead
            if(delivery.getPlayerDismissed() == "")
                continue;
            //is this a run out,yes:no?skip:goAhead
            if(delivery.getDismissalKind() == "run out")
                continue;

            if(result.containsKey(delivery.getBowler())){
                if(result.get(delivery.getBowler()).containsKey(delivery.getBatsman())){
                    HashMap<String, Integer> wicketsTakenByBowler = result.get(delivery.getBowler());
                    wicketsTakenByBowler.put(delivery.getBatsman(), wicketsTakenByBowler.get(delivery.getBatsman()) + 1);
                }else{
                    HashMap<String, Integer> wicketsTakenByBowler = result.get(delivery.getBowler());
                    wicketsTakenByBowler.put(delivery.getBatsman(), 1);
                    result.put(delivery.getBowler(), wicketsTakenByBowler);
                }
            }else{
                HashMap<String, Integer> wicketsTakenByBowler = new HashMap<>();
                wicketsTakenByBowler.put(delivery.getBatsman(), 1);
                result.put(delivery.getBowler() ,wicketsTakenByBowler);
            }
        }
        return result;
    }


}