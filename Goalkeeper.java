// Goalkeeper class - specializes in saving penalties
public class Goalkeeper extends Player {
    private int reflexes;
    private int positioning;
    
    public Goalkeeper(String name, int skill, int confidence, int stamina, int reflexes, int positioning) {
        super(name, skill, confidence, stamina);
        this.reflexes = reflexes;
        this.positioning = positioning;
    }
    
    @Override
    public int calculateShotAccuracy() {
        // Goalkeepers are not shooters, so return low accuracy
        return 20;
    }
    
    @Override
    public int calculateSaveAbility() {
        int baseSave = (skill + confidence + reflexes + positioning) / 4;
        int staminaEffect = Math.max(0, (stamina - 30) / 15);
        return Math.min(90, baseSave + staminaEffect);
    }
    
    @Override
    public String getPlayerType() {
        return "Goalkeeper";
    }
    
    public int getReflexes() { return reflexes; }
    public int getPositioning() { return positioning; }
}
