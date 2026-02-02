package piece.troops;

import game.Battlefield;
import game.MyException;
import piece.SiegeMachines;
import piece.Troops;

public class Engineer extends Troops {
    public Engineer(String name, boolean faction) {
        super(name, 20, 10, 1, 1, 2, faction);	
    }
    
    

    public void rechargeSiegeMachine(int machineX, int machineY, Battlefield field) throws MyException{

        
        // controlla le posizioni
        if (field.battlefield[machineY][machineX] instanceof SiegeMachines) { //casella sopra
            
            SiegeMachines machine = (SiegeMachines) field.battlefield[machineY][machineX];
            
            if (machine.getHasAttacked() == false) { // controllo se la macchina e' gia carica

                throw new MyException("macchina da assedio gia carica");

            } else if (getStamina() <= 0) { // controllo stamina

                throw new MyException("ingegnere senza stamina");

            } else {
                machine.recharge();
                setStamina(getStamina()-1);
                // tutto ok, ricarica
            }

            
        }else{
            throw new MyException("macchina da assedio non trovata");
        }
    }
        

        @SuppressWarnings("unused")
	private boolean hasEngineer() {
	    return false;
	}
    
}