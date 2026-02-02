package piece;

import game.Battlefield;
import game.MyException;

public interface Units {
    
    public void move(Battlefield position, int orientation)throws MyException;
    public void attack(int targetX, int targetY, Battlefield field)throws MyException;
    public void attacked(int damage);
    public boolean isHost();
    public boolean isAlive();
    public void recharge();

    default void place(Units[][] battlefield, int y, int x) {
        java.util.Optional.of(this)
            .filter(u -> u instanceof Troops)
            .map(u -> (Troops) u)
            .ifPresent(t -> {
                t.setX(x);
                t.setY(y);
            });

        java.util.Optional.of(this)
            .filter(u -> u instanceof SiegeMachines)
            .map(u -> (SiegeMachines) u)
            .ifPresent(s -> {
                s.setX(x);
                s.setY(y);
            });
        
        battlefield[y][x] = this;
    }
}
