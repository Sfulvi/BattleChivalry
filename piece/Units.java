package piece;

import game.Battlefield;
import game.MyException;

public interface Units {
    /* Funzione di movimento dell'entita' */
    public void move(Battlefield position, int orientation)throws MyException;
    /* Funzione di attacco dell'entita' */
    public void attack(int targetX, int targetY, Battlefield field)throws MyException;
    /* Funzione che si infligge il danno subito da un attacco */
    public void attacked(int damage);
    /* Funzione che mostra se e' di Player1 */
    public boolean isHost();
    /* Funzione che ritorna se l'entita' e' in vita */
    public boolean isAlive();
    /* Funzione di ricarica stamina */
    public void recharge();

    /* Funzione di posizionamento entita' nel campo di battaglia */
    public void place(Units[][] battlefield, int y, int x);
}
