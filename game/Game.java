package game;

import GUI.MainMenu;

public class Game {

    public Battlefield battlefield;
    public Player player1;
    public Player player2;
    public int maxAp;

    // action point massimi per turno
    private static final int MAX_AP = 10;

    public static void main(String[] args) {
        
        Battlefield battlefield = new Battlefield();

        MainMenu gui = new MainMenu(battlefield);
    }
}