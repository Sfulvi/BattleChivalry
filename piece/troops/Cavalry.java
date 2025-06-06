package piece.troops;

public class Cavalry extends Troops {
    public Cavalry(String name, boolean faction) {
        super(name, 120, 50, 1, 2, 1, 2, faction);	
    }
    
        @SuppressWarnings("unused")
	private boolean hasEngineer() {
	    return false;
	}
    
}
