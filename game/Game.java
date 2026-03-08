package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import GUI.BattleGroundGUI;
import piece.SiegeMachines;
import piece.Troops;
import piece.Units;

public class Game implements ActionListener, MouseListener {

    // action point massimi per turno
    private static final int MAX_AP = 10;
    
    private Battlefield battlefield;
    private BattleGroundGUI gui;
    private Player player1;
    private Player player2;
    private boolean currentPlayer;

    private int x1, y1, x2, y2, direction;

    public Game(Battlefield battlefield) {
        
        this.battlefield = battlefield;
        this.currentPlayer = true;
        resetCoordinates();
    }

    // metodo per gestire il click di una cella sul campo
    @Override
    public void mouseClicked(MouseEvent e)
    {
        JPanel clicked = (JPanel) e.getSource();
    
        if (x1 == -1)
        {
            // memorizza la prima casella selezionata
            x1 = (int) clicked.getClientProperty("x");
            y1 = (int) clicked.getClientProperty("y");

            if (this.battlefield.getUnit(x1, y1).isHost() != this.currentPlayer)
            {
                JOptionPane.showMessageDialog(null, "unità avversaria");
                resetCoordinates();
            }
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
    public void actionPerformed(ActionEvent e)
    {    
        switch (e.getActionCommand()) {
            
            case "attack" ->
            {
                try
                {
                    if (this.x1 != -1 && this.x2 != -1)
                        if (this.battlefield.getUnit(x1, y1) != null)
                        {
                            // ho selezionato entrambe le caselle e la prima casella non è vuota
                            this.battlefield.getUnit(x1, y1).attack(x2, y2, battlefield);
                            
                            // se dopo l'attacco un generale muore finisce la partita
                            if (!this.player1.getGeneral().isAlive())
                            {
                                JOptionPane.showMessageDialog(null, "generale morto, vince " + this.player2.getName());
                                this.gui.closeGame();
                            }
                            else if (!this.player2.getGeneral().isAlive())
                            {
                                JOptionPane.showMessageDialog(null, "generale morto, vince " + this.player1.getName());
                                this.gui.closeGame();
                            }

                            decreaseAP();
                            this.gui.updateGUI(this);
                            this.gui.updatePlayerAP(this.currentPlayer);

                            if (depletedAP())
                                endTurn();
                        }
                        else
                            JOptionPane.showMessageDialog(null, "cella unità vuota");
                    else
                        JOptionPane.showMessageDialog(null, "seleziona prima entrambe le caselle");
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
                    if (this.x1 != -1 && this.direction != -1)
                        if (this.battlefield.getUnit(x1, y1) != null)
                        {
                            // ho selezionato casella e direzione e la casella non è vuota
                            this.battlefield.getUnit(x1, y1).move(battlefield, this.direction);
                            
                            decreaseAP();
                            this.gui.updateGUI(this);
                            this.gui.updatePlayerAP(this.currentPlayer);

                            if (depletedAP())
                                endTurn();
                        }
                        else
                            JOptionPane.showMessageDialog(null, "cella unità vuota");
                    else
                        JOptionPane.showMessageDialog(null, "seleziona prima la casella e la direzione sulla bussola");
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
                if (this.battlefield.getUnit(x1, y1) != null)
                    if (this.battlefield.getUnit(x1, y1) instanceof SiegeMachines)
                    {
                        this.battlefield.getUnit(x1, y1).recharge();

                        decreaseAP();
                        this.gui.updatePlayerAP(this.currentPlayer);

                            if (depletedAP())
                                endTurn();
                    }
                    else
                        JOptionPane.showMessageDialog(null, "solo le macchine d'assedio si possono ricaricare");
                else
                    JOptionPane.showMessageDialog(null, "cella unità vuota");
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
        if (this.currentPlayer)
        {
            this.currentPlayer = false;
            this.player2.setApDone(MAX_AP);
        }
        else
        {
            this.currentPlayer = true;
            this.player1.setApDone(MAX_AP);
        }

        for (Units[] units : this.battlefield.getBattlefield())
                for (Units unit : units)
                    if (unit instanceof Troops)
                        if (((Troops) unit).getFaction() == this.currentPlayer)
                            unit.recharge();
        
        this.gui.updateActivePlayer(currentPlayer);
    }

    private void decreaseAP()
    {
        if (currentPlayer)
            player1.setApDone(player1.getApDone() - 1);
        else
            player2.setApDone(player2.getApDone() - 1);
    }
    
    private boolean depletedAP()
    {
        if (currentPlayer)
            return player1.getApDone() == 0;
        else
            return player2.getApDone() == 0;
    }
    
    private void resetCoordinates()
    {
        this.x1 = this.x2 = this.y1 = this.y2 = this.direction = -1;
    }
    
    public void setGUI(BattleGroundGUI gui)
    {
        this.gui = gui;
    }
    public void setPlayers(Player player1, Player player2)
    {
        this.player1 = player1;
        this.player2 = player2;
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