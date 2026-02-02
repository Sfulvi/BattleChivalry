package piece.siegemachines;

import game.Battlefield;
import game.MyException;
import piece.SiegeMachines;
import piece.Units;

public class Catapult extends SiegeMachines {

    public Catapult(String name, boolean faction) {
        super(name, 50, 4, faction);

    }

    @Override
    public void attack(int x, int y, Battlefield field) throws MyException {

	    /* Controlli preliminari */

		if(y <= 2 || y >= 12){

	    	/* Errore: Non puoi colpire quella zona */

			throw new MyException("Non puoi colpire quella zona");

		}else if(field.battlefield[y][x] == null) {

	    	/* Errore: La zona é vuota */

			throw new MyException("La zona é vuota");
		
		}else if(field.battlefield[y][x].isHost() == getFaction()) {

	    	/* Errore: "Il fuoco amico non sará tollerato" */

			throw new MyException("Il fuoco amico non sará tollerato");

    	} else if (getHasAttacked() || !hasEngineer(field)) {

            /* Errore: La macchina da assedio sta ricaricando */

			throw new MyException("La macchina da assedio sta ricaricando");

    	} else {

			/* eseguo l'attacco*/
			int damage = getAtk();
        	Units targetUnit = field.battlefield[y][x];
			targetUnit.attacked(damage);

			//controllo vicini
			Units leftNeighbor = field.battlefield[y - 1][x];
			Units rightNeighbor = field.battlefield[y + 1][x];	  
			if (leftNeighbor != null) 
				leftNeighbor.attacked(damage / 2);	  
			if (rightNeighbor != null) 
				rightNeighbor.attacked(damage / 2);

			if (y > 0) { //sicuro ha caselle sopra
				Units topNeighbor = field.battlefield[y][x - 1];
				if (topNeighbor != null) 
					topNeighbor.attacked(damage / 2);
			}

			if (y < 8) { //sicuro ha caselle sotto
				Units bottomNeighbor = field.battlefield[y][x + 1];
				if (bottomNeighbor != null) 
						bottomNeighbor.attacked(damage / 2);
				}

			}

		}

	public boolean hasEngineer(Battlefield field) {
		return super.hasEngineer(field);
	}
    
}
