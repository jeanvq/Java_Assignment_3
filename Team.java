// Team class representing football clubs and stadiums
public class Team {
    private String name;
    private String country;
    private String stadium;
    private Striker striker;
    private Goalkeeper goalkeeper;
    private Midfielder midfielder;
    private Defender defender;
    private int teamRating;
    private int goalsScored;
    private int goalsConceded;
    
    public Team(String name, String country, String stadium, 
                Striker striker, Goalkeeper goalkeeper, Midfielder midfielder, Defender defender) {
        this.name = name;
        this.country = country;
        this.stadium = stadium;
        this.striker = striker;
        this.goalkeeper = goalkeeper;
        this.midfielder = midfielder;
        this.defender = defender;
        this.teamRating = calculateTeamRating();
        this.goalsScored = 0;
        this.goalsConceded = 0;
    }
    
    private int calculateTeamRating() {
        return (striker.getSkill() + goalkeeper.getSkill() + 
                midfielder.getSkill() + defender.getSkill()) / 4;
    }
    
    public void displayTeamInfo() {
        System.out.println(ColorUtil.colorize("═══════════════════════════════════", ColorUtil.BOLD));
        System.out.println(ColorUtil.colorize("⚽ " + name + " ⚽", ColorUtil.BOLD + ColorUtil.YELLOW));
        System.out.println(ColorUtil.colorize("Country: " + country, ColorUtil.WHITE));
        System.out.println(ColorUtil.colorize("Stadium: " + stadium, ColorUtil.CYAN));
        System.out.println(ColorUtil.colorize("Team Rating: " + teamRating + "/100", ColorUtil.GREEN));
        System.out.println(ColorUtil.colorize("Goals: " + goalsScored + " | Conceded: " + goalsConceded, ColorUtil.BLUE));
        System.out.println(ColorUtil.colorize("═══════════════════════════════════", ColorUtil.BOLD));
        
        System.out.println("\n" + ColorUtil.colorize("SQUAD:", ColorUtil.UNDERLINE));
        striker.displayStats();
        System.out.println();
        goalkeeper.displayStats();
        System.out.println();
        midfielder.displayStats();
        System.out.println();
        defender.displayStats();
    }
    
    public Player selectShooter(int choice) {
        switch(choice) {
            case 1: return striker;
            case 2: return midfielder;
            case 3: return defender;
            default: return striker;
        }
    }
    
    // Getters
    public String getName() { return name; }
    public String getCountry() { return country; }
    public String getStadium() { return stadium; }
    public Striker getStriker() { return striker; }
    public Goalkeeper getGoalkeeper() { return goalkeeper; }
    public Midfielder getMidfielder() { return midfielder; }
    public Defender getDefender() { return defender; }
    public int getTeamRating() { return teamRating; }
    public int getGoalsScored() { return goalsScored; }
    public int getGoalsConceded() { return goalsConceded; }
    
    public void incrementGoalsScored() { goalsScored++; }
    public void incrementGoalsConceded() { goalsConceded++; }
    
    public void resetStats() {
        goalsScored = 0;
        goalsConceded = 0;
    }
}
