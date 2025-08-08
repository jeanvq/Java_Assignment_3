// Defender class - fourth subclass with defensive abilities
public class Defender extends Player {
    private int strength;
    private int leadership;
    
    public Defender(String name, int skill, int confidence, int stamina, int strength, int leadership) {
        super(name, skill, confidence, stamina);
        this.strength = strength;
        this.leadership = leadership;
    }
    
    @Override
    public int calculateShotAccuracy() {
        int baseAccuracy = (skill + confidence + strength) / 3;
        int staminaEffect = Math.max(0, (stamina - 45) / 10);
        return Math.min(75, baseAccuracy + staminaEffect);
    }
    
    @Override
    public int calculateSaveAbility() {
        int baseSave = (skill + leadership + strength) / 3;
        return Math.min(70, baseSave);
    }
    
    @Override
    public String getPlayerType() {
        return "Defender";
    }
    
    public int getStrength() { return strength; }
    public int getLeadership() { return leadership; }
}
