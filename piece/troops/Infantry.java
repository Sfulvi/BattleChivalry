package piece.troops;

import piece.Troops;

public class Infantry extends Troops {
    public Infantry(String name, boolean faction) {
        super(name, 100, 40, 1, 1, 2 ,faction);
    }
    
        @SuppressWarnings("unused")
	private boolean hasEngineer() {
	    return false;
	}
    
}
