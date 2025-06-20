package piece.troops;

import piece.Troops;

public class Armored extends Troops {
    public Armored(String name, boolean faction) {
        super(name, 140, 35, 1, 1, 1, 1, faction);
    }
   
        @SuppressWarnings("unused")
	private boolean hasEngineer() {
	    return false;
	}
    
}
