package piece.troops;

public class Engineer extends Troops {
    public Engineer(String name, boolean faction) {
        super(name, 20, 10, 1, 1, 1, 2, faction);	
    }
    

    public void rechargeSiegeMachine(int machineX, int machineY, Battlefield field) throws MyException{

        
        // controlla le posizioni
        if (field.battlefield[machineX][machineY] instanceof SiegeMachines) { //casella sopra
            setStamina(getStamina()-1);
            // funzione di ricarica
        }else{
            throw new MyException("bersaglio non supportato");
        }
    }
        

        @SuppressWarnings("unused")
	private boolean hasEngineer() {
	    return false;
	}
    
}