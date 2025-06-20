package test;

import game.Battlefield;
import game.MyException;
import game.Player;
import piece.generals.KingArthur;
import piece.generals.Leonida;

public class Test1 {
    public static void main(String[] args) {
        // Creazione players con scelta del generale
        Player player1 = new Player("G1", true, 0,new KingArthur(true));
        Player player2 = new Player("G2", false, 0,new Leonida(false));
        // Verifica creazione players
        System.out.println(player1.toString());
        System.out.println(player2.toString());
        // Creazione campo di battaglia
        Battlefield field = new Battlefield();
        System.out.println(field.toString());
        // Popolazione campo di battaglia
        field.deploy(player1);
        field.deploy(player2);
        System.out.println(field.toString());
        // Test di movimento su General
        System.out.println(field.battlefield[4][1].toString());
        try{
            field.battlefield[4][1].move(field, 8);
        }catch(MyException e){
            System.out.println(e);
        }
        System.out.println(field.toString());
        // Test di movimento su Troops
        System.out.println(field.battlefield[2][2].toString());
        try{
            field.battlefield[2][2].move(field, 3);
        }catch(MyException e){
            System.out.println(e);
        }
        System.out.println(field.toString());
    }
}
