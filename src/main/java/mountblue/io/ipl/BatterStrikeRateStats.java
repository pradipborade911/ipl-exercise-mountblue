package mountblue.io.ipl;

public class BatterStrikeRateStats {
    private String name = "Test";
    private int ballsFaced = 0;
    private int runsScored = 0;
    private double strikeRate = 0;

    public BatterStrikeRateStats(){

    }

    public void addRunsAndBalls( int runsScored, int ballsFaced){
        this.ballsFaced += ballsFaced;
        this.runsScored += runsScored;
        this.strikeRate = ((double)this.runsScored/(double)this.ballsFaced)*100;
    }

    public void addNameRunsAndBalls(String thisBatter, int runsScored, int ballsFaced){
        this.name = thisBatter;
        this.ballsFaced += ballsFaced;
        this.runsScored += runsScored;
        this.strikeRate = ((double)this.runsScored/(double)this.ballsFaced)*100;
    }

    public double getStrikeRate(){
        return this.strikeRate;
    }

    public String getName() {
        return this.name;
    }
}
