package test;

import game.Battlefield;
import game.MyException;
import game.Player;
import piece.generals.*;

public class Test2 {
    public static void main(String[] args) {
        // Creazione players con scelta del generale
        Player player1 = new Player("G1", true, 0,new SunTzu(true));
        Player player2 = new Player("G2", false, 0,new Ghandi(false));
        // Creazione campo di battaglia
        Battlefield field = new Battlefield();
        // Popolazione campo di battaglia
        field.deploy(player1);
        field.deploy(player2);
        System.out.println(field.toString());

        try {
            field.getUnit(13, 4).move(field, 8);
            field.getUnit(12, 4).recharge();
            field.getUnit(12, 4).move(field, 8);
            field.getUnit(11, 4).recharge();
            field.getUnit(11, 4).move(field, 8);
            field.getUnit(10, 4).recharge();
            field.getUnit(10, 4).move(field, 8);
            field.getUnit(9, 4).recharge();
            field.getUnit(9, 4).move(field, 8);

            field.getUnit(2, 4).move(field, 4);
            field.getUnit(3, 4).recharge();
            field.getUnit(3, 4).move(field, 4);
            field.getUnit(4, 4).recharge();
            field.getUnit(4, 4).move(field, 4);
            field.getUnit(5, 4).recharge();
            field.getUnit(5, 4).move(field, 4);
            field.getUnit(6, 4).recharge();
            field.getUnit(6, 4).move(field, 4);
            System.out.println(field.toString());
        } catch (MyException e) {
            System.out.println(e);
        }

        // senza stamina
        try{
            field.getUnit(8, 4).attack(7,4,field);
        }catch(MyException e){
            System.out.println(e);
        }

        // Ghandi attacca Infantry di Sun Tzu
        try{
            field.getUnit(8, 4).recharge();
            field.getUnit(8, 4).attack(7,4,field);
        }catch(MyException e){
            System.out.println(e);
        }
        System.out.println("\n"+field.getUnit(7, 4).toString());

        // Cannone attacca Ghandi
        try{
            field.getUnit(0, 2).attack(8,4,field);
        }catch(MyException e){
            System.out.println(e);
        }
        System.out.println(field.toString());
    }
}
