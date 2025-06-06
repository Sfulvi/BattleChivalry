package piece.troops;

public class Archer extends Troops {
    public Archer(String name, boolean faction) {
        super(name, 80, 40, 3, 1, 1, 2, faction);
    }
    
        @SuppressWarnings("unused")
	private boolean hasEngineer() {
	    return false;
	}
    
}
