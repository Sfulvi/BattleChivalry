package piece.troops;

import piece.Troops;

public class Cavalry extends Troops {
    public Cavalry(String name, boolean faction) {
        super(name, 120, 50, 1, 2,  2, faction);	
    }
    
        @SuppressWarnings("unused")
	private boolean hasEngineer() {
	    return false;
	}
    
}
