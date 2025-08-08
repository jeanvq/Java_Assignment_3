// Abstract parent class for all players
public abstract class Player {
    protected String name;
    protected int skill;
    protected int confidence;
    protected int stamina;
    protected String specialAbility;
    protected boolean abilityUsed;
    protected int abilityTimer;
    
    public Player(String name, int skill, int confidence, int stamina, String specialAbility) {
        this.name = name;
        this.skill = skill;
        this.confidence = confidence;
        this.stamina = stamina;
        this.specialAbility = specialAbility;
        this.abilityUsed = false;
        this.abilityTimer = 0;
    }
    
    // methods that subclasses must implement
    public abstract int calculateShotAccuracy();
    public abstract int calculateSaveAbility();
    public abstract void useSpecialAbility();
    public abstract String getPlayerType();
    
    // Getters and setters
    public String getName() { return name; }
    public int getSkill() { return skill; }
    public int getConfidence() { return confidence; }
    public int getStamina() { return stamina; }
    public String getSpecialAbility() { return specialAbility; }
    public boolean isAbilityUsed() { return abilityUsed; }
    public int getAbilityTimer() { return abilityTimer; }
    
    public void setConfidence(int confidence) { this.confidence = Math.max(0, Math.min(100, confidence)); }
    public void setStamina(int stamina) { this.stamina = Math.max(0, Math.min(100, stamina)); }
    public void setAbilityUsed(boolean used) { this.abilityUsed = used; }
    public void decrementAbilityTimer() { if (abilityTimer > 0) abilityTimer--; }
    public void setAbilityTimer(int timer) { this.abilityTimer = timer; }
    
    public void displayStats() {
        System.out.println(ColorUtil.colorize("━━━ " + name + " Stats ━━━", ColorUtil.CYAN));
        System.out.println(ColorUtil.colorize("Type: ", ColorUtil.WHITE) + ColorUtil.colorize(getPlayerType(), ColorUtil.YELLOW));
        System.out.println(ColorUtil.colorize("Skill: ", ColorUtil.WHITE) + ColorUtil.colorize(skill + "/100", ColorUtil.GREEN));
        System.out.println(ColorUtil.colorize("Confidence: ", ColorUtil.WHITE) + ColorUtil.colorize(confidence + "/100", ColorUtil.BLUE));
        System.out.println(ColorUtil.colorize("Stamina: ", ColorUtil.WHITE) + ColorUtil.colorize(stamina + "/100", ColorUtil.RED));
        System.out.println(ColorUtil.colorize("Special: ", ColorUtil.WHITE) + ColorUtil.colorize(specialAbility, ColorUtil.PURPLE));
        if (abilityTimer > 0) {
            System.out.println(ColorUtil.colorize("Ability Cooldown: " + abilityTimer + " turns", ColorUtil.RED));
        } else if (!abilityUsed) {
            System.out.println(ColorUtil.colorize("Special Ability: READY!", ColorUtil.GREEN));
        }
    }
}
