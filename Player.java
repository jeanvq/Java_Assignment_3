// Abstract parent class for all players
public abstract class Player {
    protected String name;
    protected int skill;
    protected int confidence;
    protected int stamina;
    
    public Player(String name, int skill, int confidence, int stamina) {
        this.name = name;
        this.skill = skill;
        this.confidence = confidence;
        this.stamina = stamina;
    }
    
    // methods that subclasses must implement
    public abstract int calculateShotAccuracy();
    public abstract int calculateSaveAbility();
    public abstract String getPlayerType();
    
    // Getters and setters
    public String getName() { return name; }
    public int getSkill() { return skill; }
    public int getConfidence() { return confidence; }
    public int getStamina() { return stamina; }
    
    public void setConfidence(int confidence) { this.confidence = Math.max(0, Math.min(100, confidence)); }
    public void setStamina(int stamina) { this.stamina = Math.max(0, Math.min(100, stamina)); }
    
    public void displayStats() {
        System.out.println(ColorUtil.colorize("━━━ " + name + " Stats ━━━", ColorUtil.CYAN));
        System.out.println(ColorUtil.colorize("Type: ", ColorUtil.WHITE) + ColorUtil.colorize(getPlayerType(), ColorUtil.YELLOW));
        System.out.println(ColorUtil.colorize("Skill: ", ColorUtil.WHITE) + ColorUtil.colorize(skill + "/100", ColorUtil.GREEN));
        System.out.println(ColorUtil.colorize("Confidence: ", ColorUtil.WHITE) + ColorUtil.colorize(confidence + "/100", ColorUtil.BLUE));
        System.out.println(ColorUtil.colorize("Stamina: ", ColorUtil.WHITE) + ColorUtil.colorize(stamina + "/100", ColorUtil.RED));
    }
}
