package piece.siegemachines;

public class Catapult extends SiegeMachines{

    public Catapult(String name, boolean faction) {
        super(name, 50, 4, true, 4, 0, faction, false);

    }

    
    public void attack(int x, int y, Battlefield field) {

  if(x <= 2 || y >= 12){

	    /* Errore: Non puoi colpire quella zona */

	    }else if(field.battlefield[x][y] == null) {

	    /* Errore: La zona é vuota */
		
	    }else if(field.battlefield[x][y].isHost() == getFaction()) {

	    /* Errore: "Il fuoco amico non sará tollerato" */

            } else if (getHasAttacked() || !hasEngineer(field)) {

            /* Errore: La macchina da assedio sta ricaricando */

            } else {

		/* eseguo l'attacco*/
		int damage = getAtk();
        	Units targetUnit = field.battlefield[x][y];
		targetUnit.attacked(damage);

		//controllo vicini
        	Units leftNeighbor = field.battlefield[x - 1][y];
        	Units rightNeighbor = field.battlefield[x + 1][y];	  
	  	if (leftNeighbor != null) 
			leftNeighbor.attacked(damage / 2);	  
	  	if (rightNeighbor != null) 
			rightNeighbor.attacked(damage / 2);

        	if (y > 0) { //sicuro ha caselle sopra
        	    Units topNeighbor = field.battlefield[x][y - 1];
                    if (topNeighbor != null) 
			topNeighbor.attacked(damage / 2);
                }

                if (y < 8) { //sicuro ha caselle sotto
                    Units bottomNeighbor = field.battlefield[x][y + 1];
                        if (bottomNeighbor != null) 
		        bottomNeighbor.attacked(damage / 2);
                }

	    }

    }

	public boolean hasEngineer(Battlefield field) {
		return super.hasEngineer(field);
	}
    
}
