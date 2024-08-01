package org.example;

public class Delivery {
    int matchId;
    int inning;
    String battingTeam;
    String bowlingTeam;
    int over;
    int ball;
    String batsman;
    String nonStriker;
    String bowler;
    boolean isSuperOver;
    int wideRuns;
    int byeRuns;
    int legbyeRuns;
    int noballRuns;
    int penaltyRuns;
    int batsmanRuns;
    int extraRuns;
    int totalRuns;
    String playerDismissed;
    String dismissalKind;
    String fielder;

    public Delivery(int matchId, int inning, String battingTeam, String bowlingTeam, int over, int ball, String batsman, String nonStriker, String bowler, boolean isSuperOver, int wideRuns, int byeRuns, int legbyeRuns, int noballRuns, int penaltyRuns, int batsmanRuns, int extraRuns, int totalRuns, String playerDismissed, String dismissalKind, String fielder) {
        this.matchId = matchId;
        this.inning = inning;
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.over = over;
        this.ball = ball;
        this.batsman = batsman;
        this.nonStriker = nonStriker;
        this.bowler = bowler;
        this.isSuperOver = isSuperOver;
        this.wideRuns = wideRuns;
        this.byeRuns = byeRuns;
        this.legbyeRuns = legbyeRuns;
        this.noballRuns = noballRuns;
        this.penaltyRuns = penaltyRuns;
        this.batsmanRuns = batsmanRuns;
        this.extraRuns = extraRuns;
        this.totalRuns = totalRuns;
        this.playerDismissed = playerDismissed;
        this.dismissalKind = dismissalKind;
        this.fielder = fielder;
    }

    public int getMatchId() {
        return matchId;
    }

    public int getInning() {
        return inning;
    }

    public String getBattingTeam() {
        return battingTeam;
    }

    public String getBowlingTeam() {
        return bowlingTeam;
    }

    public int getOver() {
        return over;
    }

    public int getBall() {
        return ball;
    }

    public String getBatsman() {
        return batsman;
    }

    public String getNonStriker() {
        return nonStriker;
    }

    public String getBowler() {
        return bowler;
    }

    public boolean isSuperOver() {
        return isSuperOver;
    }

    public int getWideRuns() {
        return wideRuns;
    }

    public int getByeRuns() {
        return byeRuns;
    }

    public int getLegbyeRuns() {
        return legbyeRuns;
    }

    public int getNoballRuns() {
        return noballRuns;
    }

    public int getPenaltyRuns() {
        return penaltyRuns;
    }

    public int getBatsmanRuns() {
        return batsmanRuns;
    }

    public int getExtraRuns() {
        return extraRuns;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public String getPlayerDismissed() {
        return playerDismissed;
    }

    public String getDismissalKind() {
        return dismissalKind;
    }

    public String getFielder() {
        return fielder;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public void setInning(int inning) {
        this.inning = inning;
    }

    public void setBattingTeam(String battingTeam) {
        this.battingTeam = battingTeam;
    }

    public void setBowlingTeam(String bowlingTeam) {
        this.bowlingTeam = bowlingTeam;
    }

    public void setOver(int over) {
        this.over = over;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    public void setBatsman(String batsman) {
        this.batsman = batsman;
    }

    public void setNonStriker(String nonStriker) {
        this.nonStriker = nonStriker;
    }

    public void setBowler(String bowler) {
        this.bowler = bowler;
    }

    public void setSuperOver(boolean superOver) {
        isSuperOver = superOver;
    }

    public void setWideRuns(int wideRuns) {
        this.wideRuns = wideRuns;
    }

    public void setByeRuns(int byeRuns) {
        this.byeRuns = byeRuns;
    }

    public void setLegbyeRuns(int legbyeRuns) {
        this.legbyeRuns = legbyeRuns;
    }

    public void setNoballRuns(int noballRuns) {
        this.noballRuns = noballRuns;
    }

    public void setPenaltyRuns(int penaltyRuns) {
        this.penaltyRuns = penaltyRuns;
    }

    public void setBatsmanRuns(int batsmanRuns) {
        this.batsmanRuns = batsmanRuns;
    }

    public void setExtraRuns(int extraRuns) {
        this.extraRuns = extraRuns;
    }

    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }

    public void setPlayerDismissed(String playerDismissed) {
        this.playerDismissed = playerDismissed;
    }

    public void setDismissalKind(String dismissalKind) {
        this.dismissalKind = dismissalKind;
    }

    public void setFielder(String fielder) {
        this.fielder = fielder;
    }
}
