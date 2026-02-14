package game;

import GUI.BattleGroundGUI;


public class Game {
    
   
    //costruttore che accetta i giocatori e il campo di battaglia
    public Game(Player player1, Player player2, Battlefield battlefield, String general1Name, String general2Name) {
        // Schiera gli eserciti sul campo di battaglia
        battlefield.deploy(player1);
        battlefield.deploy(player2);
        
        //avvia la GUI del campo di battaglia
        new BattleGroundGUI(battlefield, general1Name, general2Name, player1, player2);
    }
}