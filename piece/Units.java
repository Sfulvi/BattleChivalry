package piece;

import game.Battlefield;
import game.MyException;

public interface Units {
    
    public void move(Battlefield position, int orientation)throws MyException;
    public void attack(int targetX, int targetY, Battlefield field)throws MyException;
    public boolean isHost();
    public void attacked(int damage);
    public boolean isAlive();
    public void recharge();

}
