package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import GUI.BattleGroundGUI;

public class Game implements ActionListener, MouseListener {

    // action point massimi per turno
    private static final int MAX_AP = 10;
    
    private Battlefield battlefield;
    private BattleGroundGUI gui;
    private Player player1;
    private Player player2;

    private int x1, y1, x2, y2, direction;

    public Game(Battlefield battlefield) {
        
        this.battlefield = battlefield;
        resetCoordinates();
    }

    // metodo per gestire il click di una cella sul campo
    @Override
    public void mouseClicked(MouseEvent e) {

        JPanel clicked = (JPanel) e.getSource();
    
        if (x1 == -1)
        {
            // memorizza la prima casella selezionata
            x1 = (int) clicked.getClientProperty("x");
            y1 = (int) clicked.getClientProperty("y");
        }
        else
        {
            // memorizza la seconda casella selezionata
            x2 = (int) clicked.getClientProperty("x");
            y2 = (int) clicked.getClientProperty("y");
        }

        this.gui.showUnitStats(x1, y1);
    }

    // metodo per gestire il click di un bottone bussola o azione
    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch (e.getActionCommand()) {
            
            case "attack" ->
            {
                try
                {
                    this.battlefield.getUnit(x1, y1).attack(x2, y2, battlefield);
                }
                catch (MyException ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                finally
                {
                    resetCoordinates();
                }
            }
            case "move" ->
            {
                try
                {
                    this.battlefield.getUnit(x1, y1).move(battlefield, this.direction);
                    this.gui.updateGUI(this);
                }
                catch (MyException ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                finally
                {
                    resetCoordinates();
                }
            }
            case "recharge" ->
            {
                this.battlefield.getUnit(x1, y1).recharge();
                resetCoordinates();
            }
            case "skipturn" ->
            {
                endTurn();
                resetCoordinates();
            }
            case "1", "2", "3", "4", "5", "6", "7", "8" ->
            {
                // bottone bussola
                direction = Integer.parseInt(e.getActionCommand());
            }
        }
    }
    
    private void endTurn()
    {

    }

    private void resetCoordinates()
    {
        this.x1 = this.x2 = this.y1 = this.y2 = this.direction = -1;
    }
    
    public void setGUI(BattleGroundGUI gui)
    {
        this.gui = gui;
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}