package game;

import GUI.BattleGroundGUI;
import piece.Generals;
import piece.generals.Ghandi;
import piece.generals.JuliusCaesar;
import piece.generals.KingArthur;
import piece.generals.Leonida;
import piece.generals.NobunagaOda;
import piece.generals.Ragnar;
import piece.generals.SunTzu;


public class Game {
    
    // action point massimi per turno
    private static final int MAX_AP = 10;
    
    public static void main(String[] args) {
        
        Battlefield battlefield = new Battlefield();
        Player player1 = new Player("player1", true, MAX_AP, null);
        Player player2 = new Player("player2", false, MAX_AP, null);

        // acquisizione nome e generale primo giocatore
        System.out.println("Inserisci il nome del primo giocatore: ");
        String player1Name = System.console().readLine();
        player1.setName(player1Name);
        System.out.println("Inserisci il nome del generale del primo giocatore: ");
        String general1Name = System.console().readLine();
        // generale e esercito vengono creati insieme
        Generals general1 = switch (general1Name) {
            case "Sun Tzu" -> new SunTzu(true);
            case "Nobunaga Oda" -> new NobunagaOda(true);
            case "King Arthur" -> new KingArthur(true);
            case "Julius Caesar" -> new JuliusCaesar(true);
            case "Leonida" -> new Leonida(true);
            case "Ragnar" -> new Ragnar(true);
            case "Ghandi" -> new Ghandi(true);
            default -> throw new IllegalArgumentException("Generale non valido");
        };
        player1.setGeneral(general1);

        // acquisizione nome e generale secondo giocatore
        System.out.println("Inserisci il nome del secondo giocatore: ");
        String player2Name = System.console().readLine();
        player2.setName(player2Name);
        System.out.println("Inserisci il nome del generale del secondo giocatore: ");
        String general2Name = System.console().readLine();
        // generale e esercito vengono creati insieme
        Generals general2 = switch (general2Name) {
            case "Sun Tzu" -> new SunTzu(false);
            case "Nobunaga Oda" -> new NobunagaOda(false);
            case "King Arthur" -> new KingArthur(false);
            case "Julius Caesar" -> new JuliusCaesar(false);
            case "Leonida" -> new Leonida(false);
            case "Ragnar" -> new Ragnar(false);
            case "Ghandi" -> new Ghandi(false);
            default -> throw new IllegalArgumentException("Generale non valido");
        };
        player2.setGeneral(general2);

        battlefield.deploy(player1);
        battlefield.deploy(player2);

        BattleGroundGUI gui = new BattleGroundGUI(battlefield, general1Name, general2Name);
    }
}