package piece.troops;

import game.Battlefield;
import game.MyException;
import piece.Troops;
import piece.Units;

public class Cavalry extends Troops {
    public Cavalry(String name, boolean faction) {
        super(name, 120, 50, 1, 2,  2, faction);	
    }
    
        @SuppressWarnings("unused")
	private boolean hasEngineer() {
	    return false;
	}
    
    /* Aggiornamento di move per ridurre gli stalli */
    @Override
    public void move(Battlefield field, int direction) throws MyException{
        int currentX = getX();
        int currentY = getY();
        int movement = getMov();
        int newX = -1;
        int newY = -1;
    
        switch (direction) {
            case 1 -> {
                // nw
                newX = currentX - 1;
                newY = currentY - 1;
            }
            case 2 -> {
                // north
                newX = currentX;
                newY = currentY - 1;
            }
            case 3 -> {
                // ne
                newX = currentX + 1;
                newY = currentY - 1;
            }
            case 4 -> {
                // east
                newX = currentX + movement;
                newY = currentY;
            }
            case 5 -> {
                // se
                newX = currentX + 1;
                newY = currentY + 1;
            }
            case 6 -> {
                // south
                newX = currentX;
                newY = currentY + 1;
            }
            case 7 -> {
                // sw
                newX = currentX - 1;
                newY = currentY + 1;
            }
            case 8 -> {
                // west
                newX = currentX - movement;
                newY=currentY;
            }
        }
    
        // verifica se la nuova posizione e' valida
        if (isValidMove(newX, newY, field)) {
            // valori nella truppa
            setX(newX);                                     
            setY(newY);

            Units[][] battlefield = field.getBattlefield();
            // sposto l'unita' nella nuova posizione
            battlefield[newY][newX] = this;   
            // aggiorno la vecchia posizione a null
            battlefield[currentY][currentX] = null;

            setStamina(getStamina() - 1);
        }
    }
}
