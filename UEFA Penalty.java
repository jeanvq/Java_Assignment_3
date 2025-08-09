import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class PenaltyGameWithPlayerSelection {
    private Scanner scanner;
    private Random random;
    private Team playerTeam;
    private Team computerTeam;
    private List<Team> availableTeams;
    private int playerScore;
    private int computerScore;
    private int currentRound;
    private static final int MAX_ROUNDS = 5;
    private static final String[] GOAL_POSITIONS = {"Left", "Center", "Right"};

    public PenaltyGameWithPlayerSelection() {
        scanner = new Scanner(System.in);
        random = new Random();
        playerScore = 0;
        computerScore = 0;
        currentRound = 0;
        initializeTeams();
    }
    
    private void initializeTeams() {
        availableTeams = new ArrayList<>();
        
        // Real Madrid
        Striker rmStriker = new Striker("Mbappe", 95, 90, 85, 92, 89);
        Goalkeeper rmGK = new Goalkeeper("Thibaut Courtois", 89, 88, 90, 91, 87);
        Midfielder rmMid = new Midfielder("Guller", 92, 95, 80, 96, 94);
        Defender rmDef = new Defender("Ascensio", 85, 88, 92, 88, 90);
        availableTeams.add(new Team("Real Madrid", "Spain", "Santiago Bernabeu", rmStriker, rmGK, rmMid, rmDef));
        
        // Barcelona
        Striker bcnStriker = new Striker("Lewandowski", 94, 92, 83, 90, 91);
        Goalkeeper bcnGK = new Goalkeeper("ter Stegen", 88, 85, 89, 89, 86);
        Midfielder bcnMid = new Midfielder("Pedri", 89, 92, 85, 93, 91);
        Defender bcnDef = new Defender("Araujo", 86, 89, 94, 87, 88);
        availableTeams.add(new Team("Barcelona", "Spain", "Camp Nou", bcnStriker, bcnGK, bcnMid, bcnDef));
        
        // PSG
        Striker psgStriker = new Striker("Dembele", 96, 93, 88, 94, 88);
        Goalkeeper psgGK = new Goalkeeper("Donnarumma", 87, 86, 91, 88, 85);
        Midfielder psgMid = new Midfielder("Verratti", 88, 90, 82, 92, 89);
        Defender psgDef = new Defender("Marquinhos", 87, 91, 90, 85, 92);
        availableTeams.add(new Team("PSG", "France", "Parc des Princes", psgStriker, psgGK, psgMid, psgDef));
        
        // Manchester City
        Striker mcStriker = new Striker("Haaland", 93, 89, 90, 95, 85);
        Goalkeeper mcGK = new Goalkeeper("Ederson", 86, 87, 88, 85, 89);
        Midfielder mcMid = new Midfielder("De Bruyne", 94, 96, 84, 97, 93);
        Defender mcDef = new Defender("Dias", 88, 92, 89, 86, 94);
        availableTeams.add(new Team("Manchester City", "England", "Etihad Stadium", mcStriker, mcGK, mcMid, mcDef));
        
        // Bayern Munich
        Striker bmStriker = new Striker("Kane", 91, 88, 86, 89, 92);
        Goalkeeper bmGK = new Goalkeeper("Neuer", 90, 89, 87, 92, 90);
        Midfielder bmMid = new Midfielder("Kimmich", 90, 93, 88, 95, 91);
        Defender bmDef = new Defender("Upamecano", 84, 86, 91, 84, 87);
        availableTeams.add(new Team("Bayern Munich", "Germany", "Allianz Arena", bmStriker, bmGK, bmMid, bmDef));
    }

    public void displayWelcome() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║          PENALTY SHOOTOUT            ║");
        System.out.println("║              ⚽ MATCH ⚽               ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println();
        System.out.println("Welcome to the Penalty Shootout Match!");
        System.out.println("Choose your team for a " + MAX_ROUNDS + " vs " + MAX_ROUNDS + " penalty battle!");
        System.out.println();
        System.out.println("🎯 HOW TO PLAY:");
        System.out.println("• When you shoot: Select which player takes the penalty!");
        System.out.println("• Choose shot direction (1=Left, 2=Center, 3=Right)");
        System.out.println("• When opponent shoots: Choose where YOUR goalkeeper dives!");
        System.out.println("• Different players have different shooting abilities!");
        System.out.println();
    }
    
    public void displayTeamSelection() {
        System.out.println("╔══════════════ TEAM SELECTION ══════════════╗");
        for (int i = 0; i < availableTeams.size(); i++) {
            Team team = availableTeams.get(i);
            System.out.println((i + 1) + ". " + team.getName() + " (" + team.getCountry() + ")");
            System.out.println("   Stadium: " + team.getStadium());
        }
        System.out.println("╚════════════════════════════════════════════╝");
    }
    
    public Team selectTeam(String teamLabel) {
        System.out.println("\n" + teamLabel + " SELECTION:");
        displayTeamSelection();
        int choice = 0;
        while (choice < 1 || choice > availableTeams.size()) {
            System.out.print("Select " + teamLabel + " (1-" + availableTeams.size() + "): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > availableTeams.size()) {
                    System.out.println("Please enter a valid team number!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
        return availableTeams.get(choice - 1);
    }
    
    public void displayGoal() {
        System.out.println("    ┌─────────────────────┐");
        System.out.println("    │  1    │  2  │   3  │");
        System.out.println("    │ Left  │ Mid │Right │");
        System.out.println("    └─────────────────────┘");
        System.out.println();
    }
    
    // Display available players for penalty shooting
    public void displayShooterSelection() {
        System.out.println("╔══════════════ SELECT PENALTY TAKER ══════════════╗");
        System.out.println("1. " + playerTeam.getStriker().getName() + " (Striker)");
        System.out.println("   Skill: " + playerTeam.getStriker().getSkill() + 
                          " | Confidence: " + playerTeam.getStriker().getConfidence());
        
        System.out.println("2. " + playerTeam.getMidfielder().getName() + " (Midfielder)");
        System.out.println("   Skill: " + playerTeam.getMidfielder().getSkill() + 
                          " | Confidence: " + playerTeam.getMidfielder().getConfidence());
        
        System.out.println("3. " + playerTeam.getDefender().getName() + " (Defender)");
        System.out.println("   Skill: " + playerTeam.getDefender().getSkill() + 
                          " | Confidence: " + playerTeam.getDefender().getConfidence());
        
        System.out.println("╚══════════════════════════════════════════════════╝");
    }
    
    // Select which player takes the penalty
    public Player selectShooter() {
        displayShooterSelection();
        int choice = 0;
        while (choice < 1 || choice > 3) {
            System.out.print("Select penalty taker (1-3): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 3) {
                    System.out.println("Please enter 1, 2, or 3!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
        
        switch(choice) {
            case 1: return playerTeam.getStriker();
            case 2: return playerTeam.getMidfielder();
            case 3: return playerTeam.getDefender();
            default: return playerTeam.getStriker();
        }
    }

    // Player takes a penalty shot with selected player
    public boolean playerShot() {
        System.out.println("\n🎯 YOUR TURN TO SHOOT!");
        System.out.println("Penalty " + (currentRound + 1) + " of " + MAX_ROUNDS);
        
        // Select which player takes the penalty
        Player shooter = selectShooter();
        System.out.println("\n" + shooter.getName() + " (" + shooter.getPlayerType() + ") steps up to take the penalty!");
        
        displayGoal();
        int shotDirection = getPlayerChoice("Choose shot direction (1-3): ");
        int goalkeeperDirection = random.nextInt(3) + 1; // Computer goalkeeper AI
        
        // Calculate shooting accuracy based on player stats
        int shootingAccuracy = shooter.calculateShotAccuracy();
        boolean accurateShot = random.nextInt(100) < shootingAccuracy;
        
        System.out.println("\n⚽ " + shooter.getName() + " takes the shot...");
        System.out.println("Shot aimed: " + GOAL_POSITIONS[shotDirection - 1] + "!");
        System.out.println("Enemy goalkeeper dives " + GOAL_POSITIONS[goalkeeperDirection - 1] + "!");
        
        boolean isGoal;
        if (!accurateShot) {
            // Player missed regardless of goalkeeper position
            isGoal = false;
            System.out.println("\n😱 MISS! " + shooter.getName() + " sends it wide!");
            System.out.println("The pressure got to " + shooter.getName() + "!");
            displayMissAnimation();
        } else if (shotDirection != goalkeeperDirection) {
            // Accurate shot to different corner than goalkeeper
            isGoal = true;
            System.out.println("\n🎉 GOAL! ⚽ " + shooter.getName() + " finds the net!");
            System.out.println(playerTeam.getName() + " scores with " + shooter.getName() + "!");
            playerScore++;
            displayGoalAnimation();
        } else {
            // Accurate shot but goalkeeper guessed correctly
            isGoal = false;
            System.out.println("\n🥅 SAVED! Great reflexes deny " + shooter.getName() + "!");
            System.out.println("The goalkeeper read " + shooter.getName() + "'s shot perfectly!");
            displaySaveAnimation();
        }
        
        // Update player confidence based on result
        if (isGoal) {
            shooter.setConfidence(shooter.getConfidence() + 10);
        } else {
            shooter.setConfidence(shooter.getConfidence() - 5);
        }
        
        return isGoal;
    }
    
    // Computer takes a penalty shot, player controls goalkeeper
    public boolean computerShot() {
        System.out.println("\n🥅 YOUR TURN TO SAVE!");
        System.out.println(computerTeam.getName() + " penalty " + (currentRound + 1) + " of " + MAX_ROUNDS);
        
        // Computer randomly selects a player (for variety)
        Player[] computerPlayers = {computerTeam.getStriker(), computerTeam.getMidfielder(), computerTeam.getDefender()};
        Player computerShooter = computerPlayers[random.nextInt(3)];
        
        System.out.println(computerShooter.getName() + " (" + computerShooter.getPlayerType() + ") approaches the penalty spot!");
        
        displayGoal();
        int goalkeeperDirection = getPlayerChoice("Where does your goalkeeper dive? (1-3): ");
        int shotDirection = random.nextInt(3) + 1; // Computer shot AI
        
        // Calculate computer player's shooting accuracy
        int computerAccuracy = computerShooter.calculateShotAccuracy();
        boolean computerAccurateShot = random.nextInt(100) < computerAccuracy;
        
        System.out.println("\n⚽ " + computerShooter.getName() + " takes the shot...");
        System.out.println(computerTeam.getName() + " shoots " + GOAL_POSITIONS[shotDirection - 1] + "!");
        System.out.println("Your goalkeeper dives " + GOAL_POSITIONS[goalkeeperDirection - 1] + "!");
        
        boolean isGoal;
        if (!computerAccurateShot) {
            // Computer missed
            isGoal = false;
            System.out.println("\n😅 MISS! " + computerShooter.getName() + " blasts it over the bar!");
            System.out.println("What a let-off for " + playerTeam.getName() + "!");
            displayMissAnimation();
        } else if (shotDirection != goalkeeperDirection) {
            // Accurate shot, wrong dive
            isGoal = true;
            System.out.println("\n😞😭 GOAL... " + computerShooter.getName() + " scores!");
            System.out.println(computerTeam.getName() + " finds the net!");
            computerScore++;
            displayGoalAnimation();
        } else {
            // Accurate shot but you saved it!
            isGoal = false;
            System.out.println("\n🎉😎AMAZING SAVE! You denied " + computerShooter.getName() + "!");
            System.out.println("What a fantastic save by your goalkeeper!");
            displaySaveAnimation();
        }
        return isGoal;
    }
    
    private int getPlayerChoice(String prompt) {
        int choice = 0;
        while (choice < 1 || choice > 3) {
            System.out.print(prompt);
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 3) {
                    System.out.println("Please enter 1, 2, or 3!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
        return choice;
    }
    
    private void displayGoalAnimation() {
        System.out.println("    ┌─────────────────────┐");
        System.out.println("    │     ⚽ GOAL! ⚽     │");
        System.out.println("    │                     │");
        System.out.println("    └─────────────────────┘");
    }
    
    private void displaySaveAnimation() {
        System.out.println("    ┌─────────────────────┐");
        System.out.println("    │    🥅 SAVED! 🧤     │");
        System.out.println("    │                     │");
        System.out.println("    └─────────────────────┘");
    }
    
    private void displayMissAnimation() {
        System.out.println("    ┌─────────────────────┐");
        System.out.println("    │    😱 MISS! ❌      │");
        System.out.println("    │                     │");
        System.out.println("    └─────────────────────┘");
    }
    
    public void displayFinalScore() {
        System.out.println("\n" + "═".repeat(50));
        System.out.println("          PENALTY SHOOTOUT COMPLETE");
        System.out.println("═".repeat(50));
        System.out.println("FINAL SCORE:");
        System.out.println(playerTeam.getName() + ": " + playerScore);
        System.out.println(computerTeam.getName() + ": " + computerScore);
        System.out.println("═".repeat(50));
        
        if (playerScore > computerScore) {
            System.out.println("🏆 " + playerTeam.getName() + " WINS! 🏆");
            System.out.println("Congratulations! You won the penalty shootout!");
        } else if (computerScore > playerScore) {
            System.out.println("😞👎 " + computerTeam.getName() + " WINS! 😞");
            System.out.println("Better luck next time!");
        } else {
            System.out.println("🤝 IT'S A TIE! 🤝");
            System.out.println("What an incredible match!");
        }
        System.out.println("═".repeat(50));
    }
    
    public boolean playAgain() {
        System.out.print("\nWould you like to play again? (y/n): ");
        String response = scanner.nextLine().toLowerCase();
        return response.equals("y") || response.equals("yes");
    }
    
    public void resetGame() {
        playerScore = 0;
        computerScore = 0;
        currentRound = 0;
    }
    
    public void play() {
        displayWelcome();
        
        do {
            // Team selection
            playerTeam = selectTeam("YOUR TEAM");
            computerTeam = selectTeam("OPPONENT TEAM");
            
            while (playerTeam == computerTeam) {
                System.out.println("Please select a different team for the opponent!");
                computerTeam = selectTeam("OPPONENT TEAM");
            }
            
            System.out.println("\n🏟️ MATCH SETUP:");
            System.out.println("Your Team: " + playerTeam.getName());
            System.out.println("Opponent: " + computerTeam.getName());
            System.out.println("\nPress Enter to start the penalty shootout...");
            scanner.nextLine();
            
            // Penalty shootout - 5 rounds, alternating shots
            for (currentRound = 0; currentRound < MAX_ROUNDS; currentRound++) {
                System.out.println("\n" + "═".repeat(50));
                System.out.println("ROUND " + (currentRound + 1) + " of " + MAX_ROUNDS);
                System.out.println("Score: " + playerTeam.getName() + " " + playerScore + 
                                 " - " + computerScore + " " + computerTeam.getName());
                System.out.println("═".repeat(50));
                
                // Player shoots first
                playerShot();
                
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
                
                // Computer shoots
                computerShot();
                
                if (currentRound < MAX_ROUNDS - 1) {
                    System.out.println("\nPress Enter for next round...");
                    scanner.nextLine();
                }
            }
            
            displayFinalScore();
            
            if (playAgain()) {
                resetGame();
                System.out.println("\n" + "═".repeat(50));
                System.out.println("        STARTING NEW MATCH");
                System.out.println("═".repeat(50));
            }
            
        } while (currentRound == 0); // Continue if player wants to play again
        
        System.out.println("\nThanks for playing Penalty Shootout! ⚽");
        scanner.close();
    }
    
    public static void main(String[] args) {
        PenaltyGameWithPlayerSelection game = new PenaltyGameWithPlayerSelection();
        game.play();
    }
}
