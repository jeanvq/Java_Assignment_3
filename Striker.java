// Striker class - Better shooter
public class Striker extends Player {
    private int powerShot;
    private int placement;
    
    public Striker(String name, int skill, int confidence, int stamina, int powerShot, int placement) {
        super(name, skill, confidence, stamina);
        this.powerShot = powerShot;
        this.placement = placement;
    }
    
    @Override
    public int calculateShotAccuracy() {
        int baseAccuracy = (skill + confidence + placement) / 3;
        int staminaEffect = Math.max(0, (stamina - 50) / 10);
        return Math.min(95, baseAccuracy + staminaEffect);
    }
    
    @Override
    public int calculateSaveAbility() {
        // Strikers are not goalkeepers, so return low save ability
        return 10;
    }
    
    @Override
    public String getPlayerType() {
        return "Striker";
    }
    
    public int getPowerShot() { return powerShot; }
    public int getPlacement() { return placement; }
}
