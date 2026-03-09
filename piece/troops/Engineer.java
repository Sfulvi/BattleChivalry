package piece.troops;

import game.Battlefield;
import game.MyException;
import piece.SiegeMachines;
import piece.Troops;

public class Engineer extends Troops {
    public Engineer(String name, boolean faction) {
        super(name, 20, 10, 1, 1, 2, faction);
    }

    /* Ricarico la macchina da assedio selezionata */
    public void rechargeSiegeMachine(int machineX, int machineY, Battlefield field) throws MyException{
        // controlla la posizione della macchina da assedio
        if (field.getUnit(machineX, machineY) instanceof SiegeMachines) {
            
            SiegeMachines machine = (SiegeMachines) field.getUnit(machineX, machineY);
            
            if (machine.getHasAttacked() == false) {
                // controllo se la macchina è già carica
                throw new MyException("Siege machine already loaded");

            } else if (getStamina() <= 0) {
                // controllo se l'ingegnere ha stamina
                throw new MyException("Engineer without stamina");
            } else {
                machine.recharge();
                setStamina(getStamina()-1);
                // tutto ok, ricarica
            }
        }else{
            throw new MyException("Unselected siege machine");
        }
    }
}