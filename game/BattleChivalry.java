package game;

import GUI.MainMenu;

public class BattleChivalry {

    public static void main(String[] args) {
        
        Battlefield battlefield = new Battlefield();
        Game game = new Game(battlefield);
        MainMenu gui = new MainMenu(battlefield, game);
    }
}
