package test;

import game.Battlefield;
import game.Player;
import piece.generals.*;

public class Test3 {
    public static void main(String[] args) {
        // Creazione players con scelta del generale
        Player player1 = new Player("G1", true, 0,new Ragnar(true));
        Player player2 = new Player("G2", false, 0,new Ragnar(false));
        // Creazione campo di battaglia
        Battlefield field = new Battlefield();
        // Popolazione campo di battaglia
        field.deploy(player1);
        field.deploy(player2);
        System.out.println(field.toString());

    }
}
