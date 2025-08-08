// Midfielder class - balanced player with unique abilities
public class Midfielder extends Player {
    private int vision;
    private int technique;
    
    public Midfielder(String name, int skill, int confidence, int stamina, int vision, int technique) {
        super(name, skill, confidence, stamina, "Mental Focus");
        this.vision = vision;
        this.technique = technique;
    }
    
    @Override
    public int calculateShotAccuracy() {
        int baseAccuracy = (skill + confidence + technique + vision) / 4;
        int staminaEffect = Math.max(0, (stamina - 40) / 12);
        return Math.min(85, baseAccuracy + staminaEffect);
    }
    
    @Override
    public int calculateSaveAbility() {
        int baseSave = (skill + vision) / 2;
        return Math.min(60, baseSave);
    }
    
    @Override
    public void useSpecialAbility() {
        if (!abilityUsed && abilityTimer == 0) {
            System.out.println(ColorUtil.colorize("🧠 " + name + " uses MENTAL FOCUS! 🧠", ColorUtil.PURPLE));
            System.out.println(ColorUtil.colorize("Technique and vision enhanced!", ColorUtil.GREEN));
            confidence += 15;
            technique += 10;
            vision += 10;
            abilityUsed = true;
            abilityTimer = 3; // 3 turn cooldown
        }
    }
    
    @Override
    public String getPlayerType() {
        return "Midfielder";
    }
    
    public int getVision() { return vision; }
    public int getTechnique() { return technique; }
}
